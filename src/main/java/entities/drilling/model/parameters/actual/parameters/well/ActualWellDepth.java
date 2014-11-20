package entities.drilling.model.parameters.actual.parameters.well;

import entities.drilling.model.parameters.CrossComputingException;
import entities.drilling.model.parameters.Function;
import entities.drilling.model.well.PipeSection;
import entities.drilling.model.well.Well;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("actual_well_depth")
public class ActualWellDepth extends Function {

    public ActualWellDepth() {
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    protected Double function() throws CrossComputingException {
        try {
            Well well = parametersModel.getWorkingDataSet().getWell();
            List<PipeSection> pipeSections = well.getPipeSections();
            int i = 0;
            for (PipeSection pipeSection : pipeSections) {
                i++;
            }
            Double val = well.getLength();
            return parametersModel.getWorkingDataSet().getWell().getLength();
        } catch (NullPointerException e) {
            return 0d;
        }
    }
}
