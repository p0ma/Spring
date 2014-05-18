package system.drilling.model.parameters.actual.parameters;

import org.springframework.stereotype.Component;
import system.drilling.model.parameters.Parameter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Component
@Entity
@Table(name = "volume2_parameter")
@DiscriminatorValue("volume2")
public class Volume2 extends Parameter {

    public Volume2() {
        super();
    }

    public Volume2(Double value) {
        super(value);
    }
}
