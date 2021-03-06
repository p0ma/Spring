package repositories;

import entities.drilling.well.Well;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WellRepository extends JpaRepository<Well, Long>{

}
