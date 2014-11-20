package entities.drilling.model.parameters.actual.parameters.well;

import entities.drilling.model.parameters.Parameter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("well_trunk_depth")
public class WellTrunkDepth extends Parameter {
    @Override
    public void setupUnit() {
        this.unit = "m";
    }

    @Override
    public void setupParameterName() {
        setParameterName("Trunk depth");
    }

    @Override
    public void setupGroupName() {
        setGroupName("Well");
    }
}
