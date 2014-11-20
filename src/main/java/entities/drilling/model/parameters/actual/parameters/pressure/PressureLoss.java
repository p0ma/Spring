package entities.drilling.model.parameters.actual.parameters.pressure;

import entities.drilling.model.parameters.CrossComputingException;
import entities.drilling.model.parameters.Function;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("pressure_loss")
public class PressureLoss extends Function {

    @Override
    protected Double function() throws CrossComputingException {
        return function(getParameterValue(CycleBeginningPressure.class),
                getParameterValue(CycleEndingPressure.class));
    }

    private Double function(Double cycleBeginningPressure, Double cycleEndingPressure) {
        Double result = cycleBeginningPressure - cycleEndingPressure;
        return result;
    }
}
