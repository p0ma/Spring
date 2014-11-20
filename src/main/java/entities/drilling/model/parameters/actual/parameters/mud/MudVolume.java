package entities.drilling.model.parameters.actual.parameters.mud;

import entities.drilling.model.parameters.Parameter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("mud_volume")
public class MudVolume extends Parameter {

    @Override
    public void setupUnit() {
        this.unit = "l";
    }

    @Override
    public void setupParameterName() {
        setParameterName("Mud volume");
    }

    @Override
    public void setupGroupName() {
        setGroupName("Mud");
    }
}
