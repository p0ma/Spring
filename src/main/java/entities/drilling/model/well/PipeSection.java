package entities.drilling.model.well;

import entities.drilling.model.dto.PipeSectionDTO;

import javax.persistence.*;

@Entity
public class PipeSection {

    public static PipeSection build(PipeSectionDTO pipeSectionDTO) throws MyValidationException {
        PipeSection pipeSection = new PipeSection();
        pipeSection.setLength(pipeSectionDTO.getLength());
        pipeSection.setPipeType(PipeType.build(pipeSectionDTO.getOuterDiameter(), pipeSectionDTO.getThickness()));
        return pipeSection;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private PipeType pipeType;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Well well;

    public Well getWell() {
        return well;
    }

    public void setWell(Well well) {
        this.well = well;
    }

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
