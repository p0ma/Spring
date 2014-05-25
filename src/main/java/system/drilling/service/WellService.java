package system.drilling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import system.drilling.model.dto.PipeSectionDTO;
import system.drilling.model.well.PipeSection;
import system.drilling.model.well.PipeType;
import system.drilling.model.well.Well;
import system.drilling.repositories.PipeSectionRepository;
import system.drilling.repositories.WellRepository;
import system.drilling.repositories.exceptions.PipeSectionNotFoundException;
import system.drilling.repositories.exceptions.WellNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class WellService {

    @Autowired
    private WellRepository wellRepository;

    @Autowired
    private PipeSectionRepository pipeSectionRepository;

    @Autowired
    private PipeSectionService pipeSectionService;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Well addPipeSection(PipeSectionDTO pipeSectionDTO) throws Exception{
        PipeSection pipeSection = new PipeSection();
        pipeSection.setLength(pipeSectionDTO.getLength());
        PipeType pipeType = new PipeType();
        pipeType.setOuterDiameter(pipeSectionDTO.getOuterDiameter());
        pipeType.setThickness(pipeSectionDTO.getThickness());
        pipeSection.setPipeType(pipeType);
        Well well = getWell();
        well.getPipeSections().add(pipeSection);
        try {
            update(well);
        } catch (Exception e) {
            e.printStackTrace();
        }
        reorderPipeSections();
        return well;
    }

    private List<PipeSection> reorderPipeSections(List<PipeSection> pipeSections) {
        Collections.sort(pipeSections,
                new Comparator<PipeSection>() {
                    @Override
                    public int compare(PipeSection o1, PipeSection o2) {
                        if (o1.getOrderNumber() == o2.getOrderNumber())
                            return 0;
                        else if (o1.getOrderNumber() > o2.getOrderNumber())
                            return 1;
                        else
                            return -1;
                    }

                    @Override
                    public boolean equals(Object obj) {
                        return false;  //To change body of implemented methods use File | Settings | File Templates.
                    }
                });
        return pipeSections;
    }

    @Transactional(readOnly = true)
    public Well getWell(){
        List<Well> wellList = wellRepository.findAll();
        Well well;
        if(!wellList.isEmpty()) {
            well = wellList.get(0);
            well.setPipeSections(reorderPipeSections(well.getPipeSections()));
            return well;
        }
        else {
            well = new Well();
            wellRepository.save(well);
            return well;
        }
    }

    @Transactional
    public Well create(Well created) {
        return wellRepository.save(created);
    }

    @Transactional(rollbackFor = WellNotFoundException.class)
    public Well delete(Long wellId) throws WellNotFoundException {
        Well deleted = wellRepository.findOne(wellId);

        if (deleted == null) {
            throw new WellNotFoundException("No well with id " + wellId + " has been found. Nothing to delete.");
        }

        wellRepository.delete(deleted);
        return deleted;
    }

    @Transactional(readOnly = true)
    public List<Well> findAll() {
        return wellRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Well findById(Long id) {
        return wellRepository.findOne(id);
    }

    @Transactional(rollbackFor = WellNotFoundException.class)
    public Well update(Well updated) throws WellNotFoundException {
        Well predicate = wellRepository.findOne(updated.getId());

        if (predicate == null) {
            throw new WellNotFoundException("No predicate with id " + updated.getId() + " has been found. Nothing to update.");
        }

        wellRepository.save(updated);

        return updated;
    }

    @Transactional(rollbackFor = {PipeSectionNotFoundException.class, WellNotFoundException.class})
    public void deletePipeSection(Long id) throws PipeSectionNotFoundException, WellNotFoundException {
        Well well = getWell();

        PipeSection pipeSection = pipeSectionRepository.findOne(id);

        well.removePipeSection(pipeSection);

        update(well);
    }

    @Transactional
    public void reorderPipeSections() {
        List<PipeSection> pipeSections = pipeSectionRepository.findAll();
        pipeSections = reorderPipeSections(pipeSections);
        int orderNumber = 0;
        for(PipeSection pipeSection : pipeSections) {
            pipeSection.setOrderNumber(orderNumber++);
            pipeSectionRepository.save(pipeSection);
        }
    }
}
