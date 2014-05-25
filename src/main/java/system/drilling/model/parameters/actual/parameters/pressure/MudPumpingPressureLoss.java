package system.drilling.model.parameters.actual.parameters.pressure;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import system.drilling.model.parameters.Parameter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("mud_pumping_pressure_loss")
public class MudPumpingPressureLoss extends Parameter {
    @Override
    public void setupParameterName() {
        setParameterName("Mud pumping pressure loss");
    }

    @Override
    public void setupGroupName() {
        setGroupName("Pressure");
    }
}
