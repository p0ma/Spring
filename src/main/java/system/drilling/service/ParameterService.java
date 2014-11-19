package system.drilling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import system.drilling.model.parameters.Parameter;
import system.drilling.repositories.ParameterRepository;
import system.drilling.repositories.exceptions.ParameterNotFoundException;

import java.util.List;

@Service
public class ParameterService {

    @Autowired
    private ParameterRepository parameterRepository;

    @Transactional(rollbackFor = ParameterNotFoundException.class)
    public Parameter delete(Long id) throws ParameterNotFoundException {

        Parameter deleted = parameterRepository.findOne(id);

        if (deleted == null) {
            throw new ParameterNotFoundException("No parameter with id " + id + " found. Nothing to delete.");
        }

        parameterRepository.delete(deleted);
        return deleted;
    }

    @Transactional(readOnly = true)
    public List<Parameter> findAll() {
        return parameterRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Parameter findById(Long id) {
        return parameterRepository.findOne(id);
    }

    @Transactional(rollbackFor = ParameterNotFoundException.class)
    public Parameter update(Parameter updated) throws ParameterNotFoundException {

        Parameter parameter = parameterRepository.findOne(updated.getId());

        if (parameter == null) {
            throw new ParameterNotFoundException("No parameter with id " + updated.getId() + " found. Nothing to update.");
        }

        parameterRepository.save(updated);

        return updated;
    }
}
