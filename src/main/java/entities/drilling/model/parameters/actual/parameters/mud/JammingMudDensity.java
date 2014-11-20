package entities.drilling.model.parameters.actual.parameters.mud;

import entities.drilling.model.parameters.CrossComputingException;
import entities.drilling.model.parameters.Function;
import entities.drilling.model.parameters.actual.parameters.fluid.FluidInflow;
import entities.drilling.model.parameters.actual.parameters.pressure.PipesPressure;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("jamming_mud_density")
public class JammingMudDensity extends Function {

    @Override
    public void setupUnit() {
        this.unit = "kg/m<sup>3</sup>";
    }

    @Override
    protected Double function() throws CrossComputingException {
        return function(getParameterValue(MudDensity.class),
                getParameterValue(PipesPressure.class),
                getParameterValue(FluidInflow.class));
    }

    @Override
    public String getFormula() {
        return "mudDensity + pipesPressure * Math.pow(10, 6) / (fluidInflow * 9.81)";
    }

    private Double function(Double mudDensity, Double pipesPressure,
                            Double fluidInflow) {
        Double result = mudDensity +
                pipesPressure * Math.pow(10, 6) /
                        (fluidInflow * 9.81);
        return result;
    }

    @Override
    public void setupParameterName() {
        setParameterName("Jamming mud density");
    }

    @Override
    public void setupGroupName() {
        setGroupName("Mud");
    }
}
