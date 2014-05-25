package system.drilling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import system.drilling.model.well.PipeSection;

public interface PipeSectionRepository extends JpaRepository<PipeSection, Long>{
}
