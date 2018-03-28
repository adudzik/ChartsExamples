package com.vaadin.charts;

import com.vaadin.ui.Component;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.vaadin.addon.JFreeChartWrapper;

public class JFreeExamples {
    public Component getGravityChart(Gravity gravityData){
        XYSeries series = new XYSeries("g");

        for(Gravity.GravityInfo gravityInfo: gravityData.getGravityData())
            series.add(gravityInfo.getRadius(), gravityInfo.getGravityAcc());

        NumberAxis axisX = new NumberAxis("Distance (km)");
        axisX.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        NumberAxis axisY = new NumberAxis("Value");
        axisY.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        XYPlot plot = new XYPlot(new XYSeriesCollection(series),
                axisX, axisY, new XYLineAndShapeRenderer(true, false));

        JFreeChart chart = new JFreeChart("Gravity Acceleration", JFreeChart.DEFAULT_TITLE_FONT, plot, true);

        return new JFreeChartWrapper(chart);
    }

    public Component getGravityChartLog(Gravity gravityData){
        XYSeries series = new XYSeries("g");

        for(Gravity.GravityInfo gravityInfo: gravityData.getGravityData())
            series.add(gravityInfo.getRadius(), gravityInfo.getGravityAcc());

        NumberAxis axisX = new NumberAxis("Distance (km)");
        axisX.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        LogAxis axisY = new LogAxis("Value");
        axisY.setBase(10);
        axisY.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        XYPlot plot = new XYPlot(new XYSeriesCollection(series),
                axisX, axisY, new XYLineAndShapeRenderer(true, false));

        JFreeChart chart = new JFreeChart("Gravity Acceleration", JFreeChart.DEFAULT_TITLE_FONT, plot, true);

        return new JFreeChartWrapper(chart);
    }
}
