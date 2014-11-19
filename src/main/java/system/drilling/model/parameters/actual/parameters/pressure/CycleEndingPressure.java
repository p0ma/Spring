package system.drilling.model.parameters.actual.parameters.pressure;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import system.drilling.model.parameters.CrossComputingException;
import system.drilling.model.parameters.Function;
import system.drilling.model.parameters.actual.parameters.mud.JammingMudDensity;
import system.drilling.model.parameters.actual.parameters.mud.MudDensity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("cycle_ending_pressure")
public class CycleEndingPressure extends Function {

    @Override
    public void setupUnit() {
        this.unit = "bar";
    }

    @Override
    protected Double function() throws CrossComputingException {
        return function(getParameterValue(MudDensity.class),
                getParameterValue(PumpingPressure.class),
                getParameterValue(JammingMudDensity.class));
    }

    @Override
    public String getFormula() {
        return "pressureLoss * jammingMudDensity / mudDensity";
    }

    private Double function(Double mudDensity, Double pumpingPressure,
                            Double jammingMudDensity) {
        Double result = pumpingPressure * jammingMudDensity / mudDensity;
        return result;
    }

    @Override
    public void setupParameterName() {
        setParameterName("Cycle ending pressure");
    }

    @Override
    public void setupGroupName() {
        setGroupName("Pressure");
    }
}
