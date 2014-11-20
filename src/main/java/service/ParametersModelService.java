package service;

import entities.auth.User;
import entities.drilling.model.ParametersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import repositories.ParametersModelRepository;
import repositories.exceptions.ParametersModelNotFoundException;

import java.util.List;

@Service
public class ParametersModelService {

    @Autowired
    private ParametersModelRepository parametersModelRepository;

    @Autowired
    private UserService userService;

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

    @Transactional(propagation = Propagation.NEVER)
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

    @Transactional(readOnly = true)
    public ParametersModel findByUser(User user) {
        return userService.findById(user.getId()).getWorkingDataSet().getParametersModel();
    }
}
