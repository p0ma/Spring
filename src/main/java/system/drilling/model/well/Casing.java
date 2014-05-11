package system.drilling.model.well;

import org.springframework.stereotype.Component;

@Component
public class Casing {
    private double width;
    private double height;

    public Casing() {

    }

    public Casing(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
