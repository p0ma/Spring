package system.drilling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import system.decision.support.logic.Predicate;
import system.drilling.repositories.PredicateRepository;
import system.drilling.repositories.exceptions.PredicateNotFoundException;

import java.util.List;

@Service
public class PredicateService {

    @Autowired
    private PredicateRepository predicateRepository;

    @Transactional
    public Predicate create(Predicate created) {

        return predicateRepository.save(created);
    }

    @Transactional(rollbackFor = PredicateNotFoundException.class)
    public Predicate delete(Long predicateId) throws PredicateNotFoundException {
        Predicate deleted = predicateRepository.findOne(predicateId);

        if (deleted == null) {
            throw new PredicateNotFoundException();
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
            throw new PredicateNotFoundException();
        }

        predicateRepository.save(updated);

        return updated;
    }
}
