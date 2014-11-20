package entities.drilling.model.parameters.actual.parameters.solid;

import entities.drilling.model.parameters.Parameter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("mud_density_during_solid_test")
public class MudDensityDuringSolidTest extends Parameter {
    @Override
    public void setupUnit() {
        this.unit = "kg/m<sup>3</sup>";
    }

    @Override
    public void setupParameterName() {
        setParameterName("Mud density during solid test");
    }

    @Override
    public void setupGroupName() {
        setGroupName("Solid");
    }
}
