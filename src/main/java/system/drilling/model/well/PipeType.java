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

    public PipeType(double outerDiameter, double thickness) throws MyValidationException {
        setOuterDiameter(outerDiameter);
        setThickness(thickness);
    }

    public double getOuterDiameter() {
        return outerDiameter;
    }

    public void setOuterDiameter(double outerDiameter) throws MyValidationException {
        if (outerDiameter <= 0)
            throw new MyValidationException("Outer diameter (" + outerDiameter + ") must be greater than 0");
        if (outerDiameter > thickness) this.outerDiameter = outerDiameter;
        else
            throw new MyValidationException("Outer diameter (" + outerDiameter + ") must be greater than thickness (" + thickness + ")");
    }

    public double getThickness() {
        return thickness;
    }

    public void setThickness(double thickness) throws MyValidationException {
        if (thickness <= 0) throw new MyValidationException("Thickness (" + thickness + ") must be greater than 0");
        if (thickness < outerDiameter) this.thickness = thickness;
        else
            throw new MyValidationException("Thickness (" + thickness + ") must be less than outer diameter (" + outerDiameter + ")");
    }

    public double getInnerDiameter() {
        return outerDiameter - thickness;
    }

    public double getCrossSectionalArea() {
        return Math.pow(getInnerDiameter() / 1000, 2) / 4 * Math.PI;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
