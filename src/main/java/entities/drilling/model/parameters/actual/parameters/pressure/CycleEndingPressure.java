package entities.drilling.model.parameters.actual.parameters.pressure;

import entities.drilling.model.parameters.CrossComputingException;
import entities.drilling.model.parameters.Function;
import entities.drilling.model.parameters.actual.parameters.mud.JammingMudDensity;
import entities.drilling.model.parameters.actual.parameters.mud.MudDensity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("cycle_ending_pressure")
public class CycleEndingPressure extends Function {

    @Override
    protected Double function() throws CrossComputingException {
        return function(getParameterValue(MudDensity.class),
                getParameterValue(PumpingPressure.class),
                getParameterValue(JammingMudDensity.class));
    }

    private Double function(Double mudDensity, Double pumpingPressure,
                            Double jammingMudDensity) {
        Double result = pumpingPressure * jammingMudDensity / mudDensity;
        return result;
    }
}
