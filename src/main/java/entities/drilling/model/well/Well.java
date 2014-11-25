package entities.drilling.model.well;


import entities.drilling.model.WorkingDataSet;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Well {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(targetEntity = PipeSection.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "well", orphanRemoval = true)
    private List<PipeSection> pipeSections = new ArrayList<PipeSection>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "well")
    private WorkingDataSet workingDataSet;

    public WorkingDataSet getWorkingDataSet() {
        return workingDataSet;
    }

    public void setWorkingDataSet(WorkingDataSet workingDataSet) {
        this.workingDataSet = workingDataSet;
    }

    public Well() {
    }

    public double getInnerVolume() {
        double volume = 0;
        for (PipeSection pipeSection : pipeSections) {
            volume += pipeSection.getInnerVolume();
        }
        return volume;
    }

    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
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

    public PipeSection removePipeSection(Long id) {
        for (PipeSection p : pipeSections) {
            if (p.getId().equals(id)) {
                pipeSections.remove(p);
                return p;
            }
        }
        return null;
    }

    public static Well build() {
        return new Well();
    }

    public int[] swapOrder(Long fromId, Long toId) {
        PipeSection from = findById(fromId);
        PipeSection to = findById(toId);
        if (from != null && to != null) {
            int temp = from.getOrderNumber();
            from.setOrderNumber(to.getOrderNumber());
            to.setOrderNumber(temp);
            int[] newOrder = new int[]{from.getOrderNumber(), to.getOrderNumber()};
            return newOrder;
        }
        return null;
    }

    private PipeSection findById(Long id) {
        for (PipeSection pipeSection : pipeSections) {
            if (pipeSection.getId().equals(id)) {
                return pipeSection;
            }
        }
        return null;
    }

    public void addPipeSection(PipeSection pipeSection) {
        pipeSections.add(pipeSection);
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
