package system.drilling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import system.decision.support.logic.Predicate;

public interface PredicateRepository extends JpaRepository<Predicate, Long>{
}
