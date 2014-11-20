package entities.drilling.model.dto;

public class PipeSectionDTO {
    private double length;
    private double outerDiameter;
    private double thickness;

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getOuterDiameter() {
        return outerDiameter;
    }

    public void setOuterDiameter(double outerDiameter) {
        this.outerDiameter = outerDiameter;
    }

    public double getThickness() {
        return thickness;
    }

    public void setThickness(double thickness) {
        this.thickness = thickness;
    }
}
