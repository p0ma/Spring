package system.drilling.model.well;

import javax.persistence.*;

@Entity
public class PipeSection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = PipeType.class, orphanRemoval = true)
    @JoinColumn(name = "pipe_type")
    private PipeType pipeType;
    private double length;

    private int orderNumber;

    public PipeSection() {

    }

    public PipeSection(PipeType pipeType, double length, int orderNumber) throws MyValidationException {
        setPipeType(pipeType);
        setLength(length);
        setOrderNumber(orderNumber);
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

    public void setLength(double length) throws MyValidationException {
        if (length > 0) this.length = length;
        else throw new MyValidationException("Length must be greater than 0");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getInnerVolume() {
        return pipeType.getCrossSectionalArea() * length;
    }


}
