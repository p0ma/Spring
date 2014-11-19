package system.drilling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import system.decision.support.logic.InferenceModel;

public interface InferenceModelRepository extends JpaRepository<InferenceModel, Long>{

}
