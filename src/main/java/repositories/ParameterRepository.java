package repositories;

import entities.drilling.model.parameters.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParameterRepository extends JpaRepository<Parameter, Long>{

}
