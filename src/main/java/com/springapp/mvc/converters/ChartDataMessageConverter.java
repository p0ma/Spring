package com.springapp.mvc.converters;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import com.springapp.mvc.media.ChartData;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import system.drilling.model.parameters.actual.parameters.pump.PumpPoint;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;


public class ChartDataMessageConverter extends AbstractHttpMessageConverter<ChartData> {

    public ChartDataMessageConverter() {
    }

    public ChartDataMessageConverter(MediaType supportedMediaType) {
        super(supportedMediaType);
    }

    public ChartDataMessageConverter(MediaType... supportedMediaTypes) {
        super(supportedMediaTypes);
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    protected ChartData readInternal(Class<? extends ChartData> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        CSVReader reader = new CSVReader(new InputStreamReader(httpInputMessage.getBody()));
        List<String[]> rows = reader.readAll();
        int size = rows.size() / 2;
        PumpPoint[] chartData = new PumpPoint[size];
        int i = 0;
        int j = 0;
        for (String[] row : rows) {
            chartData[i].setTurn((int) Double.parseDouble(row[j++]));
            chartData[i].setPressure((int) Double.parseDouble(row[j++]));
        }
        return new ChartData(chartData);
    }

    @Override
    protected void writeInternal(ChartData chartData, HttpOutputMessage httpOutputMessage)
            throws IOException, HttpMessageNotWritableException {
        CSVWriter writer = new CSVWriter(new OutputStreamWriter(httpOutputMessage.getBody()));
        for (PumpPoint pumpPoint : chartData.getChartData()) {
            writer.writeNext(new String[]{Integer.toString(pumpPoint.getTurn()),
                    Double.toString(pumpPoint.getPressure())});
        }
        writer.close();
    }
}
