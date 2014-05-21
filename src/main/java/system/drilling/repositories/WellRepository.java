package system.drilling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import system.drilling.model.well.PipeSection;
import system.drilling.model.well.Well;

public interface WellRepository extends JpaRepository<Well, Long>{

}
