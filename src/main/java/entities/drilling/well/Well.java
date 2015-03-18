package entities.drilling.well;


import entities.drilling.model.WorkingDataSet;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public double getLength() {
        double length = 0;
        for (PipeSection pipeSection : pipeSections) {
            length += pipeSection.getLength();
        }
        return length;
    }

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
        PipeSection pipeSection = null;
        for (PipeSection p : pipeSections) {
            if (p.getId().equals(id)) {
                pipeSection = p;
                pipeSections.remove(p);
                break;
            }
        }
        if (pipeSection != null) {
            reorder();
        }
        return pipeSection;
    }

    public void sortByOrder() {
        Collections.sort(pipeSections, new Comparator<PipeSection>() {
            @Override
            public int compare(PipeSection o1, PipeSection o2) {
                return new Integer(o1.getOrderNumber()).compareTo(o2.getOrderNumber());
            }
        });
    }

    private void reorder() {
        sortByOrder();
        int i = 1;
        for (PipeSection p : pipeSections) {
            p.setOrderNumber(i++);
        }
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
        pipeSection.setOrderNumber(pipeSections.size() + 1);
        pipeSections.add(pipeSection);
    }
}
