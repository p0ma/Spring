package repositories;

import entities.drilling.model.well.PipeSection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PipeSectionRepository extends JpaRepository<PipeSection, Long>{
}
