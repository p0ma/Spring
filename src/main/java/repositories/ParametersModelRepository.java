package repositories;

import entities.drilling.model.ParametersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParametersModelRepository extends JpaRepository<ParametersModel, Long> {

    public ParametersModel findById(Long id);

}
