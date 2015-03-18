package entities.drilling.parameters.actual.parameters.solid;

import entities.drilling.parameters.InputValue;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("mud_density_during_solid_test")
public class MudDensityDuringSolidTest extends InputValue {
}
