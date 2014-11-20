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
