package entities.drilling.model.dto;

public class PipeSectionDTO {
    private String length;
    private String outerDiameter;
    private String thickness;

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getOuterDiameter() {
        return outerDiameter;
    }

    public void setOuterDiameter(String outerDiameter) {
        this.outerDiameter = outerDiameter;
    }

    public String getThickness() {
        return thickness;
    }

    public void setThickness(String thickness) {
        this.thickness = thickness;
    }
}
