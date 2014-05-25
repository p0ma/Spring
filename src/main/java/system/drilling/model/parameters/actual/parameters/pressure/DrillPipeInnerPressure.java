package system.drilling.model.parameters.actual.parameters.pressure;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import system.drilling.model.parameters.Parameter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("drill_pipe_inner_pressure")
public class DrillPipeInnerPressure extends Parameter {
    @Override
    public void setupParameterName() {
        setParameterName("Drill pipe inner pressure");
    }

    @Override
    public void setupGroupName() {
        setGroupName("Pressure");
    }
}
