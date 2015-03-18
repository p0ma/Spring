package entities.drilling.parameters.actual.parameters.well;

import entities.drilling.parameters.Function;
import entities.drilling.well.PipeSection;
import entities.drilling.well.Well;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("VolumeOpenTrunkAnnularSpace")
public class VolumeOpenTrunkAnnularSpace extends Function {
    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    protected Double function() {
        double wellIntermediateColumnDepth = getParameterValue(WellIntermediateColumnDepth.class),
                wellIntermediateColumnDiameter = getParameterValue(WellIntermediateColumnDiameter.class),
                wellDiameter = getParameterValue(WellDiameter.class);
        final Well well = parametersModel.getWorkingDataSet().getWell();
        double result = 0, lengthLeft;
        for (PipeSection pipeSection : well.getPipeSections()) {
            if (wellIntermediateColumnDepth > pipeSection.getLength()) {
                result += (Math.pow(wellIntermediateColumnDiameter / 1000, 2) / 4 * Math.PI -
                        pipeSection.getPipeType().getInnerCrossSectionalArea()) * pipeSection.getLength();
                wellIntermediateColumnDepth -= pipeSection.getLength();
            } else {
                if (wellIntermediateColumnDepth > 0) {
                    result += (Math.pow(wellIntermediateColumnDiameter / 1000, 2) / 4 * Math.PI -
                            pipeSection.getPipeType().getInnerCrossSectionalArea()) * wellIntermediateColumnDepth;
                    lengthLeft = pipeSection.getLength() - wellIntermediateColumnDepth;
                    wellIntermediateColumnDepth = 0;
                    result += (Math.pow(wellDiameter / 1000, 2) / 4 * Math.PI -
                            pipeSection.getPipeType().getInnerCrossSectionalArea()) * lengthLeft;
                } else {
                    result += (Math.pow(wellDiameter / 1000, 2) / 4 * Math.PI -
                            pipeSection.getPipeType().getInnerCrossSectionalArea()) * pipeSection.getLength();
                }
            }
        }
        return result;
    }
}
