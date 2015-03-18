package entities.drilling.chart;

import com.springapp.mvc.media.ChartInfo;

public interface IChartInfoBuilder {
    ChartInfo build(IChart chart);
}
