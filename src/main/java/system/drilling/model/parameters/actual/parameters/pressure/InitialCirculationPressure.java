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
@DiscriminatorValue("initial_circulation_pressure")
public class InitialCirculationPressure extends Function {

    @Override
    protected Double function() throws CrossComputingException {
        return 0d;
    }

    @Override
    public void setupParameterName() {
        setParameterName("Initial circulation pressure");
    }

    @Override
    public void setupGroupName() {
        setGroupName("Pressure");
    }
}
