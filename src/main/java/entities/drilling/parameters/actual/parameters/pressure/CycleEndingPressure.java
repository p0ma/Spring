package entities.drilling.parameters.actual.parameters.pressure;

import entities.drilling.parameters.Function;
import entities.drilling.parameters.actual.parameters.mud.JammingMudDensity;
import entities.drilling.parameters.actual.parameters.mud.MudDensity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("CycleEndingPressure")
public class CycleEndingPressure extends Function {

    @Override
    protected Double function() {
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
