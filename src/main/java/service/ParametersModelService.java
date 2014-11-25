package service;

import entities.auth.User;
import entities.drilling.model.ParametersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.ParametersModelRepository;
import repositories.exceptions.ParametersModelNotFoundException;
import repositories.exceptions.UserNotFoundException;

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
            throw new ParametersModelNotFoundException();
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
            throw new ParametersModelNotFoundException();
        }
        parametersModelRepository.save(updated);
        return updated;
    }

    @Transactional(readOnly = true)
    public ParametersModel findByUser(User user) throws UserNotFoundException, ParametersModelNotFoundException {
        ParametersModel parametersModel = userService.findById(user.getId()).getWorkingDataSet().getParametersModel();
        if (parametersModel == null) {
            throw new ParametersModelNotFoundException();
        }
        parametersModel.initParameters();
        update(parametersModel);
        return parametersModel;
    }
}
