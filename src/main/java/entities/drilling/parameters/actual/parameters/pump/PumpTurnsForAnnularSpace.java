package entities.drilling.parameters.actual.parameters.pump;

import entities.drilling.parameters.Function;
import entities.drilling.parameters.actual.parameters.well.VolumeOpenTrunkAnnularSpace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Scope(value = "prototype")
@Entity
@DiscriminatorValue("PumpTurnsForAnnularSpace")
public class PumpTurnsForAnnularSpace extends Function {

    public void setupRound() {
        round = 0;
    }

    @Override
    protected Double function() {
        double volumeOpenTrunkAnnularSpace = getParameterValue(VolumeOpenTrunkAnnularSpace.class),
                pumpPerformance = getParameterValue(PumpPerformance.class),
                result;
        result = Math.ceil(volumeOpenTrunkAnnularSpace / pumpPerformance * Math.pow(10, 3));
        return result;
    }
}
