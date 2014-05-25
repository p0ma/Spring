package system.drilling.model.parameters.actual.parameters.well;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import system.drilling.model.parameters.Parameter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("diameter_well")
public class DiameterWell extends Parameter {
    @Override
    public void setupParameterName() {
        setParameterName("Diameter well");
    }

    @Override
    public void setupGroupName() {
        setGroupName("Well");
    }
}
