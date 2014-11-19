package system.drilling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import system.drilling.model.WorkingDataSet;

public interface WorkingDataSetRepository extends JpaRepository<WorkingDataSet, Long> {

}
