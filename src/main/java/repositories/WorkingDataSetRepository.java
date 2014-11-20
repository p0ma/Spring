package repositories;

import entities.drilling.model.WorkingDataSet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkingDataSetRepository extends JpaRepository<WorkingDataSet, Long> {

}
