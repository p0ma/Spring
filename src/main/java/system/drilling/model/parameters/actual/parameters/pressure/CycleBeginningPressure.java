package system.drilling.model.parameters.actual.parameters.pressure;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import system.drilling.model.parameters.CrossComputingException;
import system.drilling.model.parameters.Function;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("cycle_beginning_pressure")
public class CycleBeginningPressure extends Function {

    @Override
    public void setupUnit() {
        this.unit = "bar";
    }

    @Override
    protected Double function() throws CrossComputingException {
        return function(getParameterValue(PumpingPressure.class),
                getParameterValue(PipesPressure.class));
    }

    @Override
    public String getFormula() {
        return "pumpingPressure + pipesPressure";
    }

    private Double function(Double pumpingPressure, Double pipesPressure) {
        Double result = pumpingPressure + pipesPressure;
        return result;
    }

    @Override
    public void setupParameterName() {
        setParameterName("Cycle beginning pressure");
    }

    @Override
    public void setupGroupName() {
        setGroupName("Pressure");
    }
}