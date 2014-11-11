package system.drilling.model.parameters.actual.parameters.mud;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import system.drilling.model.parameters.Parameter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("mud_density")
public class MudDensity extends Parameter {

    @Override
    public void setupUnit() {
        this.unit = "kg/m<sup>3</sup>";
    }

    @Override
    public void setupParameterName() {
        setParameterName("Density");
    }

    @Override
    public void setupGroupName() {
        setGroupName("Mud");
    }
}