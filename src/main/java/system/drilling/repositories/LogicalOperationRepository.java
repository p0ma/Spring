package system.drilling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import system.decision.support.logic.operations.LogicalOperation;
import system.drilling.model.well.Well;

public interface LogicalOperationRepository extends JpaRepository<LogicalOperation, Long>{

}
