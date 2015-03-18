package entities.drilling.chart;

import com.springapp.mvc.media.ChartData;
import com.springapp.mvc.media.ChartInfo;
import com.springapp.mvc.media.ChartLocalization;

import java.util.List;

public class ChartInfoBuilderImpl implements IChartInfoBuilder {
    @Override
    public ChartInfo build(IChart chart) {
        ChartInfo chartInfo = new ChartInfo();
        List<DPoint> pointsList = chart.getPoints();
        DPoint[] arrayArray = pointsList.toArray(new DPoint[pointsList.size()]);
        chartInfo.chartData = new ChartData(arrayArray);
        chartInfo.header = chart.getHeader();
        chartInfo.pointFormat = chart.getPointFormat();
        chartInfo.chartLocalization = new ChartLocalization();
        return chartInfo;
    }
}
