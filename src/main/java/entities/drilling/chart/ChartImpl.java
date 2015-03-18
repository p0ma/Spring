package entities.drilling.chart;

import java.util.ArrayList;
import java.util.List;

public class ChartImpl implements IChart {

    private String header;
    private String pointFormat;

    private List<DPoint> points = new ArrayList<DPoint>();

    public void addPoint(DPoint added) {
        points.add(added);
    }

    public boolean removePoint(DPoint removed) {
        return points.remove(removed);
    }

    @Override
    public List<DPoint> getPoints() {
        return points;
    }

    @Override
    public String getHeader() {
        return header;
    }

    @Override
    public String getPointFormat() {
        return pointFormat;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setPointFormat(String pointFormat) {
        this.pointFormat = pointFormat;
    }
}
