package service;

import entities.drilling.model.ParametersModel;
import entities.drilling.model.well.Well;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import repositories.WellRepository;
import repositories.exceptions.WellNotFoundException;

import java.util.List;

@Service
public class WellService {

    @Autowired
    private WellRepository wellRepository;

    @Autowired
    private ParametersModelService parametersModelService;

    @Transactional
    public Well create(Well created) {
        return wellRepository.save(created);
    }

    @Transactional(rollbackFor = WellNotFoundException.class)
    public Well delete(Long id) throws WellNotFoundException {
        Well deleted = wellRepository.findOne(id);

        if (deleted == null) {
            throw new WellNotFoundException("No well with id " + id + " found. Nothing to delete.");
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

    @Transactional(propagation = Propagation.NEVER)
    public Well findByParametersModel(ParametersModel parametersModel) {
        parametersModel = parametersModelService.findById(parametersModel.getId());
        Well well = parametersModel.getWorkingDataSet().getWell();
        return well;

    }
}
