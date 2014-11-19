package system.drilling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import system.decision.support.logic.operations.LogicalOperation;

public interface LogicalOperationRepository extends JpaRepository<LogicalOperation, Long>{

}
