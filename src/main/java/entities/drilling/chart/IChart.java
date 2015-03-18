package entities.drilling.chart;

import java.util.List;

public interface IChart {
    List<DPoint> getPoints();

    String getHeader();

    String getPointFormat();
}
