package entities.drilling.parameters.actual.parameters.mud;

import entities.drilling.parameters.Function;
import entities.drilling.parameters.actual.parameters.fluid.FluidInflow;
import entities.drilling.parameters.actual.parameters.pressure.PipesPressure;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("JammingMudDensity")
public class JammingMudDensity extends Function {

    @Override
    protected Double function() {
        return function(getParameterValue(MudDensity.class),
                getParameterValue(PipesPressure.class),
                getParameterValue(FluidInflow.class));
    }

    private Double function(Double mudDensity, Double pipesPressure,
                            Double fluidInflow) {
        Double result = mudDensity +
                pipesPressure * Math.pow(10, 6) /
                        (fluidInflow * 9.81);
        return result;
    }
}
