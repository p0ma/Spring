package system.drilling.model.parameters.actual.parameters.fluid;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import system.drilling.model.parameters.Parameter;
import system.drilling.model.well.MyValidationException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("fluid_critical_volume")
public class FluidCriticalVolume extends Parameter {

    private static final double max_value = 3000;
    private static final double min_value = 100;

    @Override
    public void setParameterValue(Double value) throws MyValidationException{
        if(value > max_value) throw MyValidationException.messageTemplate(
                MyValidationException.LESS_EQUAL,
                this.getParameterName(),
                max_value);
        if(value < min_value) throw  MyValidationException.messageTemplate(
                MyValidationException.GREATER_EQUAL,
                this.getParameterName(),
                min_value);
        setValue(value);
    }

    @Override
    public void setupParameterName() {
        setParameterName("Fluid critical volume");
    }

    @Override
    public void setupGroupName() {
        setGroupName("Fluid");
    }
}
