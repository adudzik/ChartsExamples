package com.vaadin.charts;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.*;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.data.provider.ListDataProvider;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class VaadinChartsExamples {
    public Chart getGravityChart(Gravity data, Boolean logarithmicScale){
        Chart chart = new Chart();
        Configuration conf = chart.getConfiguration();
        conf.setTitle("Gravity acceleration");

        conf.getChart().setType(ChartType.LINE);

        ListDataProvider<Gravity.GravityInfo> dataProvider = new ListDataProvider<>(data.getGravityData());
        DataProviderSeries<Gravity.GravityInfo> gravity = new DataProviderSeries<>(dataProvider);

        gravity.setName("g");
        gravity.setX(Gravity.GravityInfo::getRadius);
        gravity.setY(Gravity.GravityInfo::getGravityAcc);

        conf.getxAxis().setTitle("Distance (km)");
        conf.getyAxis().setTitle("Value");
        conf.getyAxis().setMax(10);

        if(logarithmicScale)
            conf.getyAxis().setType(AxisType.LOGARITHMIC);

        conf.addSeries(gravity);

        return chart;
    }


    public Chart getWeatherChart(ChartsData data) {
        Chart chart = new Chart();
        Configuration conf = chart.getConfiguration();
        conf.setTitle("Turku, Finland 2013");

        conf.getChart().setType(ChartType.LINE);

        ListDataProvider<ChartsData.WeatherInfo> dataProvider = new ListDataProvider<>(data.getWeatherData());
        dataProvider.addFilter(point -> {
            LocalDateTime date = LocalDateTime.ofInstant(point.getInstant(), ZoneId.of("Europe/Helsinki"));
            return date.getDayOfWeek() == DayOfWeek.SUNDAY;
        });

        DataProviderSeries<ChartsData.WeatherInfo> temp = new DataProviderSeries<>(dataProvider);

        temp.setName("Temperature");
        temp.setX(ChartsData.WeatherInfo::getInstant);
        temp.setY(ChartsData.WeatherInfo::getMaxTemp);

        conf.getxAxis().setTitle("Date");
        conf.getxAxis().setType(AxisType.DATETIME);
        conf.getyAxis().setTitle("Temperature (°C)");

        DataProviderSeries<ChartsData.WeatherInfo> humidity = new DataProviderSeries<>(dataProvider);

        humidity.setName("Humidity");
        humidity.setX(ChartsData.WeatherInfo::getInstant);
        humidity.setY(ChartsData.WeatherInfo::getMeanHumidity);
        humidity.setPlotOptions(new PlotOptionsColumn());

        conf.addSeries(humidity);
        conf.addSeries(temp);

        YAxis humidityYAxis = new YAxis();
        humidityYAxis.setTitle("Humidity (%)");
        humidityYAxis.setMin(0);
        humidityYAxis.setOpposite(true);
        conf.addyAxis(humidityYAxis);
        humidity.setyAxis(humidityYAxis);

        return chart;
    }

    public Chart getShoesChart(ChartsData data) {
        Chart chart = new Chart();
        Configuration conf = chart.getConfiguration();
        conf.setTitle("Shoe size per age for boys and girls");
        conf.getChart().setType(ChartType.LINE);

        DataSeries girls = new DataSeries("Girls");
        for (ChartsData.ShoeSizeInfo shoeSizeInfo : data.getGirlsData()) {
            // Shoe size on the X-axis, age on the Y-axis
            girls.add(new DataSeriesItem(
                    shoeSizeInfo.getSize(),
                    shoeSizeInfo.getAgeMonths() / 12.0f));
        }
        conf.addSeries(girls);

        DataSeries boys = new DataSeries("Boys");
        for (ChartsData.ShoeSizeInfo shoeSizeInfo : data.getBoysData()) {
            // Shoe size on the X-axis, age on the Y-axis
            boys.add(new DataSeriesItem(
                    shoeSizeInfo.getSize(),
                    shoeSizeInfo.getAgeMonths() / 12.0f));
        }
        conf.addSeries(boys);

        PlotOptionsLine girlsOpts = new PlotOptionsLine();
        girlsOpts.setColor(SolidColor.HOTPINK);
        girls.setPlotOptions(girlsOpts);

        PlotOptionsLine boysOpts = new PlotOptionsLine();
        boysOpts.setColor(SolidColor.BLUE);
        boys.setPlotOptions(boysOpts);

        conf.getxAxis().setTitle("Shoe size (EU)");
        conf.getyAxis().setTitle("Age (Years)");
        return chart;
    }
}
