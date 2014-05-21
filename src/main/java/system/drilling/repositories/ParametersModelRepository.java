package system.drilling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import system.drilling.model.ParametersModel;

public interface ParametersModelRepository extends JpaRepository<ParametersModel, Long> {
}
