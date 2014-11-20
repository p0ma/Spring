package entities.drilling.model.parameters.actual.parameters.pressure;

import entities.drilling.model.parameters.Parameter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("pipes_pressure")
public class PipesPressure extends Parameter {

    @Override
    public void setupUnit() {
        this.unit = "bar";
    }

    @Override
    public void setupParameterName() {
        setParameterName("Pipes pressure");
    }

    @Override
    public void setupGroupName() {
        setGroupName("Pressure");
    }
}
