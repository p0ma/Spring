package system.drilling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import system.decision.support.logic.Predicate;
import system.decision.support.logic.operations.LogicalOperation;
import system.drilling.model.parameters.Parameter;
import system.drilling.repositories.LogicalOperationRepository;
import system.drilling.repositories.ParameterRepository;
import system.drilling.repositories.exceptions.LogicalOperationNotFoundException;
import system.drilling.repositories.exceptions.PredicateNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class LogicalOperationService {

    @Autowired
    private LogicalOperationRepository logicalOperationRepository;

    @Autowired
    private ParameterRepository parameterRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public LogicalOperation create(LogicalOperation created) {
        return logicalOperationRepository.save(created);
    }

    @Transactional(rollbackFor = LogicalOperationNotFoundException.class)
    public LogicalOperation delete(Long predicateId) throws LogicalOperationNotFoundException {
        LogicalOperation deleted = logicalOperationRepository.findOne(predicateId);

        if (deleted == null) {
            throw new LogicalOperationNotFoundException("No predicate with id " + predicateId + " has been found. Nothing to delete.");
        }

        logicalOperationRepository.delete(deleted);
        return deleted;
    }

    @Transactional(readOnly = true)
    public List<LogicalOperation> findAll() {
        return logicalOperationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public LogicalOperation findById(Long id) {
        return logicalOperationRepository.findOne(id);
    }

    @Transactional(rollbackFor = LogicalOperationNotFoundException.class)
    public LogicalOperation update(LogicalOperation updated) throws LogicalOperationNotFoundException {
        LogicalOperation predicate = logicalOperationRepository.findOne(updated.getId());

        if (predicate == null) {
            throw new LogicalOperationNotFoundException("No predicate with id " + updated.getId() + " has been found. Nothing to update.");
        }

        logicalOperationRepository.save(updated);

        return updated;
    }
}
