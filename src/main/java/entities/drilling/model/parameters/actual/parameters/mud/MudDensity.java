package entities.drilling.model.parameters.actual.parameters.mud;

import entities.drilling.model.parameters.InputValue;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("mud_density")
public class MudDensity extends InputValue {
}