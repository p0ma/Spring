package system.drilling.service;

import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import system.decision.support.logic.Conclusion;
import system.decision.support.logic.Predicate;
import system.decision.support.logic.operations.ExpressionWrapper;
import system.decision.support.logic.operations.LogicalOperation;
import system.drilling.model.ParametersModel;
import system.drilling.model.dto.ConclusionDTO;
import system.drilling.model.dto.PredicateDTO;
import system.drilling.model.dto.QuestionDTO;
import system.drilling.model.parameters.Parameter;
import system.drilling.model.parameters.actual.parameters.well.ActualWellDepth;
import system.drilling.repositories.LogicalOperationRepository;
import system.drilling.repositories.PredicateRepository;
import system.drilling.repositories.exceptions.LogicalOperationNotFoundException;
import system.drilling.repositories.exceptions.ParsingExpressionException;
import system.drilling.repositories.exceptions.PredicateNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class PredicateService {

    @Autowired
    private PredicateRepository predicateRepository;

    @Autowired
    private LogicalOperationService logicalOperationService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ParametersModelService parametersModelService;

    @Transactional
    public Predicate create(Predicate created) {
        return predicateRepository.save(created);
    }

    public Predicate createFromDto(PredicateDTO predicateDTO) throws ParsingExpressionException {
        Predicate predicate = new Predicate();
        if (predicateDTO.getFiresOnFalse() != null) {
            predicate.setFiresOnFalse(findById(predicateDTO.getFiresOnFalse()));
        }
        if (predicateDTO.getFiresOnTrue() != null) {
            predicate.setFiresOnTrue(findById(predicateDTO.getFiresOnTrue()));
        }
        ExpressionWrapper expression1 = new ExpressionWrapper();
        expression1.setExpression(predicateDTO.getExpression1().isEmpty() ? "p" : predicateDTO.getExpression1());
        ExpressionWrapper expression2 = new ExpressionWrapper();
        expression2.setExpression(predicateDTO.getExpression2().isEmpty() ? "p" : predicateDTO.getExpression2());
        try {
            new ExpressionBuilder(expression1.getExpression()).withVariable("p", 0).build();
            new ExpressionBuilder(expression2.getExpression()).withVariable("p", 0).build();
        } catch (UnknownFunctionException e) {
            throw new ParsingExpressionException("Cant parse expression");
        } catch (UnparsableExpressionException e) {
            throw new ParsingExpressionException(e.getMessage());
        }
        ParametersModel parametersModel = parametersModelService.getParametersModel();
        Parameter parameter1 = parametersModel.getParameter(predicateDTO.getLeft());
        if(parameter1 == null) {
            parameter1 = new ActualWellDepth();
        }
        Parameter parameter2 = parametersModel.getParameter(predicateDTO.getRight());
        if(parameter2 == null) {
            parameter2 = new ActualWellDepth();
        }
        expression1.setParameter(parameter1);
        expression2.setParameter(parameter2);
        LogicalOperation logicalOperation = LogicalOperation.getLogicalOperation(predicateDTO.getLogicalOperation());
        logicalOperation.setOperand1(expression1);
        logicalOperation.setOperand2(expression2);
        predicate.setLogicalOperation(logicalOperation);
        if(!predicateDTO.getName().isEmpty())  {
            predicate.setName(predicateDTO.getName());
        } else {
            predicate.setName("No name");
        }
        return predicate;
    }

    @Transactional(rollbackFor = PredicateNotFoundException.class)
    public Predicate delete(Long predicateId) throws PredicateNotFoundException {
        Predicate deleted = predicateRepository.findOne(predicateId);

        if (deleted == null) {
            throw new PredicateNotFoundException("No predicate with id " + predicateId + " has been found. Nothing to delete.");
        }

        predicateRepository.delete(deleted);
        return deleted;
    }

    @Transactional(readOnly = true)
    public List<Predicate> findAll() {
        return predicateRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Predicate findById(Long id) {
        return predicateRepository.findOne(id);
    }

    @Transactional(rollbackFor = PredicateNotFoundException.class)
    public Predicate update(Predicate updated) throws PredicateNotFoundException {
        Predicate predicate = predicateRepository.findOne(updated.getId());

        if (predicate == null) {
            throw new PredicateNotFoundException("No predicate with id " + updated.getId() + " has been found. Nothing to update.");
        }

        predicateRepository.save(updated);

        return updated;
    }

    public Predicate createFromDto(ConclusionDTO conclusionDTO) {
        Predicate predicate = new Predicate();
        Conclusion conclusion = new Conclusion();
        conclusion.setMessage(conclusionDTO.getMessage());
        predicate.setName(conclusionDTO.getName());
        predicate.setConclusion(conclusion);
        predicate.getConclusion().setIsAQuestion(false);
        return predicate;
    }

    public Predicate createFromDto(QuestionDTO questionDTO) {
        Predicate predicate = new Predicate();
        if (questionDTO.getFiresOnFalse() != null) {
            predicate.setFiresOnFalse(findById(questionDTO.getFiresOnFalse()));
        }
        if (questionDTO.getFiresOnTrue() != null) {
            predicate.setFiresOnTrue(findById(questionDTO.getFiresOnTrue()));
        }
        Conclusion conclusion = new Conclusion();
        conclusion.setMessage(questionDTO.getMessage());
        predicate.setName(questionDTO.getName());
        predicate.setConclusion(conclusion);
        predicate.getConclusion().setIsAQuestion(true);
        return predicate;
    }
}
