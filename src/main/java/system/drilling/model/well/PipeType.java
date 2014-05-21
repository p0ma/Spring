package system.drilling.model.well;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class PipeType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double outerDiameter;
    private double thickness;

    public PipeType() {
    }

    public PipeType(double outerDiameter, double thickness) {
        setOuterDiameter(outerDiameter);
        setThickness(thickness);
    }

    public double getOuterDiameter() {
        return outerDiameter;
    }

    public void setOuterDiameter(double outerDiameter) {
        if(outerDiameter > thickness) this.outerDiameter = outerDiameter;
    }

    public double getThickness() {
        return thickness;
    }

    public void setThickness(double thickness) {
        if(thickness < outerDiameter) this.thickness = thickness;
    }

    public double getInnerDiameter() {
        return outerDiameter - thickness;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
