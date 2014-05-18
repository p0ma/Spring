package system.drilling.model.parameters.actual.parameters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import system.drilling.model.parameters.CrossComputingException;
import system.drilling.model.parameters.Function;
import system.drilling.model.well.Well;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Component
@Entity
@Table(name = "mud_volume_parameter")
@DiscriminatorValue("mud_volume")
public class MudVolume extends Function {

    @Autowired
    @Transient
    private Well well;

    public MudVolume() {
        super(null);
    }

    public MudVolume(Double value) {
        super(value);
    }

    @Override
    public void function() throws CrossComputingException {
        setValue(
                (Double) getParameterValue(Volume1.class)
                        *
                        (Double) well.getValue()
        );
    }
}
