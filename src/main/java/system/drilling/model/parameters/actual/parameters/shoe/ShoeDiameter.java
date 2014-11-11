package system.drilling.model.parameters.actual.parameters.shoe;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import system.drilling.model.parameters.Parameter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("shoe_diameter")
public class ShoeDiameter extends Parameter {

    @Override
    public void setupUnit() {
        this.unit = "mm";
    }

    @Override
    public void setupParameterName() {
        setParameterName("Diameter");
    }

    @Override
    public void setupGroupName() {
        setGroupName("Shoe");
    }
}
