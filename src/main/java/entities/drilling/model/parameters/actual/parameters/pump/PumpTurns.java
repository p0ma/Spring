package entities.drilling.model.parameters.actual.parameters.pump;

import entities.drilling.model.parameters.CrossComputingException;
import entities.drilling.model.parameters.Function;
import entities.drilling.model.parameters.actual.parameters.drill_column.DrillColumnInnerVolume;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("pump_turns")
public class PumpTurns extends Function {

    public void setupRound() {
        round = 0;
    }

    @Override
    protected Double function() throws CrossComputingException {
        return function(getParameterValue(DrillColumnInnerVolume.class),
                getParameterValue(PumpPerformance.class));
    }

    private Double function(Double drillColumnInnerVolume, Double pumpPerformance) {
        Double result = Math.ceil(drillColumnInnerVolume / pumpPerformance * Math.pow(10, 3));
        return result;
    }
}
