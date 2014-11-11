package system.drilling.model.parameters.actual.parameters.fluid;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import system.drilling.model.parameters.Parameter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("fluid_inflow")
public class FluidInflow extends Parameter {

    @Override
    public void setupUnit() {
        this.unit = "l";
    }

    @Override
    public void setupParameterName() {
        setParameterName("Fluid inflow");
    }

    @Override
    public void setupGroupName() {
        setGroupName("Fluid");
    }
}
