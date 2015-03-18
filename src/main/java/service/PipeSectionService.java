package service;

import entities.auth.User;
import entities.drilling.well.PipeSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.PipeSectionRepository;
import repositories.exceptions.PipeSectionNotFoundException;
import repositories.exceptions.UserNotFoundException;

import java.util.List;

@Service
public class PipeSectionService {

    @Autowired
    private PipeSectionRepository pipeSectionRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public PipeSection create(PipeSection created) {

        return pipeSectionRepository.save(created);
    }

    @Transactional(rollbackFor = PipeSectionNotFoundException.class)
    public PipeSection delete(Long pipeSectionId) throws PipeSectionNotFoundException {
        PipeSection deleted = pipeSectionRepository.findOne(pipeSectionId);

        if (deleted == null) {
            throw new PipeSectionNotFoundException();
        }

        pipeSectionRepository.delete(deleted);
        return deleted;
    }

    @Transactional(readOnly = true)
    public List<PipeSection> findAll() {
        return pipeSectionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public PipeSection findById(Long id) {
        return pipeSectionRepository.findOne(id);
    }

    @Transactional(rollbackFor = PipeSectionNotFoundException.class)
    public PipeSection update(PipeSection updated) throws PipeSectionNotFoundException {
        PipeSection predicate = pipeSectionRepository.findOne(updated.getId());

        if (predicate == null) {
            throw new PipeSectionNotFoundException();
        }

        pipeSectionRepository.save(updated);

        return updated;
    }

    @Transactional(readOnly = true)
    public List<PipeSection> getPipeSections(User user) throws UserNotFoundException {
        return userService.findById(user.getId()).getWorkingDataSet().getWell().getPipeSections();
    }
}
