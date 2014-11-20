package service;

import entities.drilling.model.well.PipeSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.PipeSectionRepository;
import repositories.exceptions.PipeSectionNotFoundException;

import java.util.List;

@Service
public class PipeSectionService {

    @Autowired
    private PipeSectionRepository pipeSectionRepository;

    @Transactional
    public PipeSection create(PipeSection created) {

        return pipeSectionRepository.save(created);
    }

    @Transactional(rollbackFor = PipeSectionNotFoundException.class)
    public PipeSection delete(Long pipeSectionId) throws PipeSectionNotFoundException {
        PipeSection deleted = pipeSectionRepository.findOne(pipeSectionId);

        if (deleted == null) {
            throw new PipeSectionNotFoundException(
                    "No pipe section with id " + pipeSectionId + " has been found. Nothing to delete.");
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
            throw new PipeSectionNotFoundException(
                    "No pipe section with id " + updated.getId() + " has been found. Nothing to update.");
        }

        pipeSectionRepository.save(updated);

        return updated;
    }
}
