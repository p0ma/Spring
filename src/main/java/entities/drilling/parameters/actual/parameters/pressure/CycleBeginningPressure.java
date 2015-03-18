package entities.drilling.parameters.actual.parameters.pressure;

import entities.drilling.parameters.Function;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("CycleBeginningPressure")
public class CycleBeginningPressure extends Function {

    @Override
    protected Double function() {
        return function(getParameterValue(PumpingPressure.class),
                getParameterValue(PipesPressure.class));
    }

    private Double function(Double pumpingPressure, Double pipesPressure) {
        Double result = pumpingPressure + pipesPressure;
        return result;
    }
}
