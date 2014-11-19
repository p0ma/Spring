package system.drilling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import system.drilling.model.well.PipeSection;
import system.drilling.repositories.PipeSectionRepository;
import system.drilling.repositories.exceptions.PipeSectionNotFoundException;

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

    @Transactional(rollbackFor = PipeSectionNotFoundException.class)
    public int[] swapOrder(Long fromId, Long toId) throws PipeSectionNotFoundException{
        PipeSection from = findById(fromId);
        PipeSection to = findById(toId);
        int temp = from.getOrderNumber();
        from.setOrderNumber(to.getOrderNumber());
        to.setOrderNumber(temp);
        pipeSectionRepository.save(from);
        pipeSectionRepository.save(to);
        int[] newOrder = new int[] {from.getOrderNumber(), to.getOrderNumber()};
        return newOrder;
    }
}
