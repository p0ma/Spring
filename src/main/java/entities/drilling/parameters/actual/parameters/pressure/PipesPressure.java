package entities.drilling.parameters.actual.parameters.pressure;

import entities.drilling.parameters.InputValue;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("pipes_pressure")
public class PipesPressure extends InputValue {
}
