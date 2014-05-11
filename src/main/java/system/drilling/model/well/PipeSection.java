package system.drilling.model.well;

public class PipeSection {
    private PipeType pipeType;
    private double length;

    public PipeSection() {

    }

    public PipeType getPipeType() {
        return pipeType;
    }

    public void setPipeType(PipeType pipeType) {
        this.pipeType = pipeType;
    }

    public double getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }


}
