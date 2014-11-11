package system.drilling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import system.drilling.model.ParametersModel;
import system.drilling.repositories.ParametersModelRepository;
import system.drilling.repositories.exceptions.ParametersModelNotFoundException;

import java.util.List;

@Service
public class ParametersModelService {

    @Autowired
    private ParametersModelRepository parametersModelRepository;

    @Transactional(readOnly = true)
    public ParametersModel getParametersModel() {
        List<ParametersModel> parametersModelList = parametersModelRepository.findAll();
        if (!parametersModelList.isEmpty()) {
            return parametersModelList.get(0);
        } else {
            ParametersModel parametersModel = new ParametersModel();
            parametersModel.initParameters();
            parametersModelRepository.save(parametersModel);
            return parametersModel;
        }
    }

    @Transactional(rollbackFor = ParametersModelNotFoundException.class)
    public ParametersModel delete(Long parametersModelId) throws ParametersModelNotFoundException {

        ParametersModel deleted = parametersModelRepository.findOne(parametersModelId);

        if (deleted == null) {
            throw new ParametersModelNotFoundException("No parameters parametersModel with id " + parametersModelId + " has been found. Nothing to delete.");
        }

        parametersModelRepository.delete(deleted);
        return deleted;
    }

    @Transactional(readOnly = true)
    public List<ParametersModel> findAll() {
        return parametersModelRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ParametersModel findById(Long id) {
        return parametersModelRepository.findOne(id);
    }

    @Transactional(rollbackFor = ParametersModelNotFoundException.class)
    public ParametersModel update(ParametersModel updated) throws ParametersModelNotFoundException {

        ParametersModel person = parametersModelRepository.findOne(updated.getId());

        if (person == null) {
            throw new ParametersModelNotFoundException("No parameters parametersModel with id " + updated.getId() + " has been found. Nothing to update.");
        }

        parametersModelRepository.save(updated);

        return updated;
    }
}
