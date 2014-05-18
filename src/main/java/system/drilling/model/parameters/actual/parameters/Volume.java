package system.drilling.model.parameters.actual.parameters;

import org.springframework.stereotype.Component;
import system.drilling.model.parameters.CrossComputingException;
import system.drilling.model.parameters.Function;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Component
@Entity
@Table(name = "volume_parameter")
@DiscriminatorValue("volume")
public class Volume extends Function {

    public Volume() {
        super();
    }

    public Volume(Double value) {
        super(value);
    }

    @Override
    protected void function() throws CrossComputingException {
        setValue(((Double) getParameterValue(Volume1.class) +
                (Double) getParameterValue(Volume2.class) +
                (Double) getParameterValue(MudVolume.class)));
    }
}
