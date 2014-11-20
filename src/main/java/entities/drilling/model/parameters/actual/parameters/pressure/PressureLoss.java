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
    public void setupUnit() {
        this.unit = "bar";
    }

    @Override
    protected Double function() throws CrossComputingException {
        return function(getParameterValue(CycleBeginningPressure.class),
                getParameterValue(CycleEndingPressure.class));
    }

    @Override
    public String getFormula() {
        return "cycleBeginningPressure - cycleEndingPressure";
    }

    private Double function(Double cycleBeginningPressure, Double cycleEndingPressure) {
        Double result = cycleBeginningPressure - cycleEndingPressure;
        return result;
    }

    @Override
    public void setupParameterName() {
        setParameterName("Pressure loss");
    }

    @Override
    public void setupGroupName() {
        setGroupName("Pressure");
    }
}
