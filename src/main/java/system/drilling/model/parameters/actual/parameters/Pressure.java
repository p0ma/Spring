package system.drilling.model.parameters.actual.parameters;

import org.springframework.stereotype.Component;
import system.drilling.model.parameters.CrossComputingException;
import system.drilling.model.parameters.Function;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Component
@Entity
@Table(name = "pressure_parameter")
@DiscriminatorValue("pressure")
public class Pressure extends Function {

    @Override
    protected void function() throws CrossComputingException {
        setValue(
                getParameterValue(Volume.class)
                * getParameterValue(MudVolume.class)
                        *  10
        );
    }
}
