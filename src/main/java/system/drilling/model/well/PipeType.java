package system.drilling.model.well;

public class PipeType {
    private double innerDiam;
    private double thickness;

    public PipeType(double innerDiam, double thickness) {
        this.innerDiam = innerDiam;
        this.thickness = thickness;
    }

    public double getInnerDiam() {
        return innerDiam;
    }

    public void setInnerDiam(float innerDiam) {
        this.innerDiam = innerDiam;
    }

    public double getThickness() {
        return thickness;
    }

    public void setThickness(float thickness) {
        this.thickness = thickness;
    }
}
