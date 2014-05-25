package system.drilling.model.parameters.actual.parameters.well;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import system.drilling.model.parameters.Parameter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("outer_gird_volume")
public class OuterGirdVolume extends Parameter{
    @Override
    public void setupParameterName() {
        setParameterName("Outer gird volume");
    }

    @Override
    public void setupGroupName() {
        setGroupName("Well");
    }
}
