package entities.drilling.model.parameters.actual.parameters.shoe;

import entities.drilling.model.parameters.Parameter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("shoe_vertical_depth")
public class ShoeVerticalDepth extends Parameter {
}
