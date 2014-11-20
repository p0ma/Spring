package entities.drilling.model.parameters.actual.parameters.drill_column;

import entities.drilling.model.parameters.CrossComputingException;
import entities.drilling.model.parameters.Function;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope("prototype")
@Entity
@DiscriminatorValue("drill_column_inner_volume")
public class DrillColumnInnerVolume extends Function {

    @Override
    protected Double function() throws CrossComputingException {
        try {
            return parametersModel.getWorkingDataSet().getWell().getInnerVolume();
        } catch (NullPointerException e) {
            return 0d;
        }
    }
}
