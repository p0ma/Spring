package system.drilling.model.well;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Well {
    private List<PipeSection> pipeSections;

    @Autowired
    private Casing casing;

    public Well() {

    }

    public Object getValue() {
        return (Double) (casing.getHeight() * casing.getWidth());
    }

    /*public void ComputeMudVolume()
    {
        float casingHeightLeft = casing.getHeight();
        float mudVolumeInPipe = 0, mudVolumeOutPipe = 0;
        for(PipeSection pipeSection : pipeSections)
        {
            if (casingHeightLeft > 0)
                if (casingHeightLeft > pipeSection.getLength())
                {
                    casingHeightLeft -= pipeSection.getLength();
                    mudVolumeOutPipe += pipeSection.getLength() *
                            SectionalAreaOutPipe(pipeSection);
                    mudVolumeInPipe += pipeSection.getLength() *
                            SectionalAreaInPipe(pipeSection);
                }
                else
                {
                    mudVolumeOutPipe += casingHeightLeft *
                            SectionalAreaOutPipe(pipeSection);
                    mudVolumeInPipe += casingHeightLeft *
                            SectionalAreaInPipe(pipeSection);
                    float pipeHeightLeft = pipeSection.getLength() - casingHeightLeft;
                    casingHeightLeft = 0;
                    mudVolumeOutPipe += pipeHeightLeft *
                            SectionalAreaOutPipe(pipeSection);
                    mudVolumeInPipe += pipeHeightLeft *
                            SectionalAreaInPipe(pipeSection);
                }
            else
            {
                mudVolumeOutPipe += pipeSection.getLength() *
                        SectionalAreaOutPipe(pipeSection);
                mudVolumeInPipe += pipeSection.getLength() *
                        SectionalAreaInPipe(pipeSection);
            }
        }
        _mudVolumeInPipe = mudVolumeInPipe;
        _mudVolumeOutPipe = mudVolumeOutPipe;
        _mudVolume = _mudVolumeOutPipe + _mudVolumeInPipe;
        _mudVolumeWithGird = _mudVolume + _oilWell.GirdVolume;
    }*/
}
