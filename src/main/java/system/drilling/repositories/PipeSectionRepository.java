package system.drilling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import system.drilling.model.well.PipeSection;

public interface PipeSectionRepository extends JpaRepository<PipeSection, Long>{
}
