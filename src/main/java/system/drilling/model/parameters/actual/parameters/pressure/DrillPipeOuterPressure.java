package system.drilling.model.parameters.actual.parameters.pressure;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import system.drilling.model.parameters.Parameter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("drill_pipe_outer_pressure")
public class DrillPipeOuterPressure extends Parameter {
    @Override
    public void setupParameterName() {
        setParameterName("Drill pipe outer pressure");
    }

    @Override
    public void setupGroupName() {
        setGroupName("Pressure");
    }
}
