package system.drilling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import system.drilling.model.well.PipeSection;
import system.drilling.model.well.PipeType;
import system.drilling.model.well.Well;
import system.drilling.repositories.WellRepository;
import system.drilling.repositories.exceptions.WellNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class WellService {

    @Autowired
    private WellRepository wellRepository;

    @Transactional(readOnly = true)
    public Well getWell(){
        List<Well> wellList = wellRepository.findAll();
        if(!wellList.isEmpty()) {
            return wellList.get(0);
        }
        else {
            Well well = new Well();
            List<PipeSection> pipeSectionList = new ArrayList<PipeSection>();
            pipeSectionList.add(new PipeSection(
                    new PipeType(130, 10), 100, 1)
            );
            pipeSectionList.add(new PipeSection(
                    new PipeType(120, 9), 200, 2)
            );

            pipeSectionList.add(new PipeSection(
                    new PipeType(120, 9), 200, 3)
            );

            well.setPipeSections(pipeSectionList);
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
            throw new WellNotFoundException();
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
            throw new WellNotFoundException();
        }

        wellRepository.save(updated);

        return updated;
    }
}
