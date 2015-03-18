package entities.drilling.chart;

import entities.drilling.model.ParametersModel;
import entities.drilling.parameters.actual.parameters.pressure.CycleBeginningPressure;
import entities.drilling.parameters.actual.parameters.pressure.CycleEndingPressure;
import entities.drilling.parameters.actual.parameters.pressure.PressureLoss;
import entities.drilling.parameters.actual.parameters.pump.PumpTurns;
import entities.drilling.parameters.actual.parameters.pump.PumpTurnsForAnnularSpace;
import localization.LocalizationUtils;

public class WaitingAndWeightingChartBuilder implements ChartBuilder {

    private ParametersModel parametersModel;

    public WaitingAndWeightingChartBuilder(ParametersModel parametersModel) {
        this.parametersModel = parametersModel;
    }

    private int turnsScale(int turns) {
        return (int) Math.ceil(turns / 50d);
    }

    @Override
    public IChart getChart() {
        double cycleBeginningPressure = parametersModel.getParameterValue(CycleBeginningPressure.class),
                cycleEndingPressure = parametersModel.getParameterValue(CycleEndingPressure.class),
                pressureLoss = parametersModel.getParameterValue(PressureLoss.class);
        int pumpTurnsForAnnularSpace = parametersModel.getParameterValue(PumpTurnsForAnnularSpace.class).intValue();

        int pumpTurns = parametersModel.getParameterValue(PumpTurns.class).intValue(),
                turnsScale = turnsScale(pumpTurns);
        ChartImpl chartImpl = new ChartImpl();
        chartImpl.setHeader(LocalizationUtils.getMessage("chart.waitingAndWeighting.header"));
        chartImpl.setPointFormat(LocalizationUtils.getMessage("chart.pointFormat"));

        chartImpl.addPoint(new DPoint(0, cycleBeginningPressure));
        int pumpTurnsTemp = pumpTurns - turnsScale;
        while (pumpTurnsTemp > 0) {
            int turns = pumpTurns - pumpTurnsTemp;
            double pressure = cycleBeginningPressure - (pressureLoss / pumpTurns) * (turns);
            chartImpl.addPoint(new DPoint(
                    turns + pumpTurnsForAnnularSpace,
                    pressure
            ));
            pumpTurnsTemp -= turnsScale;
        }
        int turns = pumpTurns;
        chartImpl.addPoint(new DPoint(
                turns + pumpTurnsForAnnularSpace,
                cycleEndingPressure
        ));
        chartImpl.addPoint(new DPoint(pumpTurnsForAnnularSpace + pumpTurns, cycleEndingPressure));
        return chartImpl;
    }
}
