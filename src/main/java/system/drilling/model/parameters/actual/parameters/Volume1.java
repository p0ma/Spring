package system.drilling.model.parameters.actual.parameters;

import org.springframework.stereotype.Component;
import system.drilling.model.parameters.Parameter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Component
@Entity
@Table(name = "volume1_parameter")
@DiscriminatorValue("volume1")
public class Volume1 extends Parameter {

    public Volume1() {
        super();
    }

    public Volume1(Double value) {
        super(value);
    }
}
