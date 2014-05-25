package system.drilling.model.parameters.actual.parameters.mud;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import system.drilling.model.parameters.Parameter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("mud_volume")
public class MudVolume extends Parameter {
    @Override
    public void setupParameterName() {
        setParameterName("Mud volume");
    }

    @Override
    public void setupGroupName() {
        setGroupName("Mud");
    }
}
