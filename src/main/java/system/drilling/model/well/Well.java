package system.drilling.model.well;


import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;


@Component
@Entity
public class Well {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Cascade(value = {CascadeType.ALL})
    @OneToMany(fetch = FetchType.EAGER, targetEntity = PipeSection.class, orphanRemoval = true)
    private List<PipeSection> pipeSections = new LinkedList<PipeSection>();

    public Well() {
    }

    public double getInnerVolume() {
        double volume = 0;
        for (PipeSection pipeSection : pipeSections) {
            volume += pipeSection.getInnerVolume();
        }
        return volume;
    }

    public double getLength() {
        double length = 0;
        for (PipeSection pipeSection : pipeSections) {
            length += pipeSection.getLength();
        }
        return length;
    }

    /*public double getInnerVolume() {
        double innerVolume = 0;
        Collections.sort(pipeSections, new Comparator<PipeSection>() {
            @Override
            public int compare(PipeSection o1, PipeSection o2) {
                if (o1.getOrderNumber() == o2.getOrderNumber())
                    return 0;
                else if (o1.getOrderNumber() > o2.getOrderNumber())
                    return 1;
                else
                    return -1;
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        });
        for (PipeSection pipeSection : pipeSections) {
            innerVolume += pipeSection.getInnerVolume();
        }
        return innerVolume;
    }*/


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PipeSection> getPipeSections() {
        return pipeSections;
    }

    public void setPipeSections(List<PipeSection> pipeSections) {
        this.pipeSections = pipeSections;
    }

    public void removePipeSection(PipeSection pipeSection) {
        for (PipeSection p : pipeSections) {
            if (p.getId().equals(pipeSection.getId())) {
                pipeSections.remove(p);
                break;
            }
        }
    }

    public static Well build() {
        return new Well();
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
