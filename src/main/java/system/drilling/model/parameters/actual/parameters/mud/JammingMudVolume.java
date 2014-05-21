package system.drilling.model.parameters.actual.parameters.mud;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import system.drilling.model.parameters.CrossComputingException;
import system.drilling.model.parameters.Function;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("jamming_mud_volume")
public class JammingMudVolume extends Function {

    @Override
    protected Double function() throws CrossComputingException {
        return null;
    }
}
