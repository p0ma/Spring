package entities.drilling.model.parameters.actual.parameters.well;

import entities.drilling.model.parameters.CrossComputingException;
import entities.drilling.model.parameters.Function;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("ActualWellDepth")
public class ActualWellDepth extends Function {

    public ActualWellDepth() {
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    protected Double function() throws CrossComputingException {
        try {
            return parametersModel.getWorkingDataSet().getWell().getLength();
        } catch (NullPointerException e) {
            return 0d;
        }
    }
}
