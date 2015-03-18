package service;

import com.springapp.mvc.media.ParameterDTO;
import entities.auth.User;
import entities.drilling.parameters.Parameter;
import entities.drilling.well.MyValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.ParameterRepository;
import repositories.exceptions.ParameterNotFoundException;
import repositories.exceptions.UserNotFoundException;

import java.util.List;

@Service
public class ParameterService {

    @Autowired
    private ParameterRepository parameterRepository;

    @Autowired
    private UserService userService;

    @Transactional(rollbackFor = ParameterNotFoundException.class)
    public Parameter delete(Long id) throws ParameterNotFoundException {
        Parameter deleted = parameterRepository.findOne(id);
        if (deleted == null) {
            throw new ParameterNotFoundException();
        }
        parameterRepository.delete(deleted);
        return deleted;
    }

    @Transactional(readOnly = true)
    public List<Parameter> findAll() {
        return parameterRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Parameter findById(Long id) throws ParameterNotFoundException {
        Parameter parameter = parameterRepository.findOne(id);
        if (parameter == null) {
            throw new ParameterNotFoundException();
        }
        return parameter;
    }

    @Transactional(rollbackFor = ParameterNotFoundException.class)
    public Parameter update(Parameter updated) throws ParameterNotFoundException {
        Parameter parameter = parameterRepository.findOne(updated.getId());
        if (parameter == null) {
            throw new ParameterNotFoundException();
        }
        parameterRepository.save(updated);
        return updated;
    }

    @Transactional(readOnly = false, rollbackFor = {ParameterNotFoundException.class, UserNotFoundException.class})
    public Parameter setParameterValue(User user, ParameterDTO parameterDTO) throws MyValidationException,
            NumberFormatException, ParameterNotFoundException, UserNotFoundException {
        Parameter parameter = userService.findById(user.getId()).getWorkingDataSet().getParametersModel()
                .initParameters().getParameter(parameterDTO.getName());
        parameter.setParameterValue(Double.parseDouble(parameterDTO.getVal()));
        update(parameter);
        return parameter;
    }
}
