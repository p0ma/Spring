package system.drilling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import system.drilling.model.well.PipeSection;
import system.drilling.repositories.PipeSectionRepository;
import system.drilling.repositories.exceptions.WellNotFoundException;

import java.util.List;

@Service
public class PipeSectionService {

    @Autowired
    private PipeSectionRepository pipeSectionRepository;

    @Transactional(readOnly = true)
    public PipeSection getWell(){
        List<PipeSection> pipeSectionsList = pipeSectionRepository.findAll();
        if(!pipeSectionsList.isEmpty()) {
            return pipeSectionsList.get(0);
        }
        else {
            PipeSection pipeSection = new PipeSection();
            pipeSectionRepository.save(pipeSection);
            return pipeSection;
        }
    }

    @Transactional
    public PipeSection create(PipeSection created) {

        return pipeSectionRepository.save(created);
    }

    @Transactional(rollbackFor = WellNotFoundException.class)
    public PipeSection delete(Long wellId) throws WellNotFoundException {
        PipeSection deleted = pipeSectionRepository.findOne(wellId);

        if (deleted == null) {
            throw new WellNotFoundException();
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

    @Transactional(rollbackFor = WellNotFoundException.class)
    public PipeSection update(PipeSection updated) throws WellNotFoundException {
        PipeSection predicate = pipeSectionRepository.findOne(updated.getId());

        if (predicate == null) {
            throw new WellNotFoundException();
        }

        pipeSectionRepository.save(updated);

        return updated;
    }
}
