package entities.drilling.model.parameters.actual.parameters.drill_column;

import entities.drilling.model.parameters.CrossComputingException;
import entities.drilling.model.parameters.Function;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope("prototype")
@Entity
@DiscriminatorValue("DrillColumnInnerVolume")
public class DrillColumnInnerVolume extends Function {

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    protected Double function() throws CrossComputingException {
        try {
            return parametersModel.getWorkingDataSet().getWell().getInnerVolume();
        } catch (NullPointerException e) {
            return 0d;
        }
    }
}
