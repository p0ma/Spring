package system.drilling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import system.drilling.model.parameters.Parameter;
import system.drilling.model.well.Well;

public interface ParameterRepository extends JpaRepository<Parameter, Long>{

}
