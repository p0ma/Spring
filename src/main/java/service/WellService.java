package service;

import entities.auth.User;
import entities.drilling.model.dto.PipeSectionDTO;
import entities.drilling.model.well.MyValidationException;
import entities.drilling.model.well.PipeSection;
import entities.drilling.model.well.Well;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.WellRepository;
import repositories.exceptions.PipeSectionNotFoundException;
import repositories.exceptions.UserNotFoundException;
import repositories.exceptions.WellNotFoundException;

import java.util.List;

@Service
public class WellService {

    @Autowired
    private WellRepository wellRepository;

    @Autowired
    private ParametersModelService parametersModelService;

    @Autowired
    private UserService userService;

    @Autowired
    private PipeSectionService pipeSectionService;

    @Transactional
    public Well create(Well created) {
        return wellRepository.save(created);
    }

    @Transactional(rollbackFor = WellNotFoundException.class)
    public Well delete(Long id) throws WellNotFoundException {
        Well deleted = wellRepository.findOne(id);

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

    @Transactional
    public PipeSection removePipeSection(User user, Long id) throws PipeSectionNotFoundException,
            UserNotFoundException {
        PipeSection removed = userService.findById(user.getId()).getWorkingDataSet().getWell().removePipeSection(id);
        if (removed == null) {
            throw new PipeSectionNotFoundException();
        } else {
            return removed;
        }
    }

    @Transactional
    public void addPipeSection(User user, PipeSectionDTO pipeSectionDTO) throws MyValidationException,
            UserNotFoundException {
        Well well = userService.findById(user.getId()).getWorkingDataSet().getWell();
        PipeSection pipeSection = PipeSection.build(pipeSectionDTO);
        pipeSection.setWell(well);
        pipeSectionService.create(pipeSection);
    }

    @Transactional
    public int[] reorderPipes(User user, Long fromId, Long toId) throws WellNotFoundException, UserNotFoundException {
        Well well = userService.findById(user.getId()).getWorkingDataSet().getWell();
        int[] newOrder = well.swapOrder(fromId, toId);
        if (newOrder != null) {
            update(well);
            return newOrder;
        } else {
            return null;
        }
    }
}
