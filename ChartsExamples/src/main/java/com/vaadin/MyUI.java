package com.vaadin;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.charts.*;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        VaadinChartsExamples vaadinChartsExamples = new VaadinChartsExamples();
        Gravity gravityData = new Gravity();
        /*
        ChartsData data = new ChartsData();

        layout.addComponent(vaadinChartsExamples.getShoesChart(data));
        layout.addComponent(vaadinChartsExamples.getWeatherChart(data));

        Component linearScale = vaadinChartsExamples.getGravityChart(gravityData, false);
        Component logarithmicScale = vaadinChartsExamples.getGravityChart(gravityData, true);

        linearScale.setWidth(1000, Unit.PIXELS);
        logarithmicScale.setWidth(1000, Unit.PIXELS);

        layout.addComponent(linearScale);

        Component jFreeLinear = new JFreeExamples().getGravityChart(gravityData);
        Component jFreeLog = new JFreeExamples().getGravityChartLog(gravityData);

        layout.addComponent(jFreeLinear);

        Button setLogarithmicScaleButton = new Button("Logarithmic Y");
        setLogarithmicScaleButton.setDescription("Change Y-axis scale to logarithmic");
        setLogarithmicScaleButton.setDisableOnClick(true);

        Button setLinearScaleButton = new Button("Linear Y");
        setLinearScaleButton.setDescription("Change Y-axis scale to linear");
        setLinearScaleButton.setDisableOnClick(true);
        setLinearScaleButton.setVisible(false);

        setLogarithmicScaleButton.addClickListener(e ->{
                setLinearScaleButton.setVisible(true);
                layout.replaceComponent(linearScale, logarithmicScale);
                layout.replaceComponent(jFreeLinear, jFreeLog);
                setLinearScaleButton.setEnabled(true);
        });

        setLinearScaleButton.addClickListener(e -> {
                layout.replaceComponent(logarithmicScale, linearScale);
                layout.replaceComponent(jFreeLog, jFreeLinear);
                setLogarithmicScaleButton.setEnabled(true);
        });

        layout.addComponents(setLogarithmicScaleButton, setLinearScaleButton);
        */

        layout.addComponent(new CompareMultipleSeries().getChart());
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
