package com.weather.api.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.weather.api.views.weather.WeatherMainView;

@SpringUI(path = "/main")
public class MainView extends UI {

    private VerticalLayout mainLayout;
    private Button bookingButton;
    private Button weaterButton;


    protected void init(VaadinRequest vaadinRequest) {
        mainLayout();
        setHeader();
        locateFormButtons();
    }

    private void mainLayout() {
        mainLayout = new VerticalLayout();
        mainLayout.setWidth("100%");
        mainLayout.setSpacing(true);
        mainLayout.setMargin(true);
        mainLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(mainLayout);
    }

    private void setHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        Label title = new Label("Booking Service");
        header.addComponent(title);
        mainLayout.addComponents(header);
    }

    private void locateFormButtons() {
        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setWidth(null);
        buttonsLayout.setSpacing(true);
        bookingButton = new Button("Booking Service");
        weaterButton = new Button("Weather Service");
        buttonsLayout.addComponent(bookingButton);
        buttonsLayout.addComponent(weaterButton);
        buttonsLayout.addComponents(bookingButton, weaterButton);

        weaterButton.addClickListener(e -> getUI().getPage().setLocation("/weather"));
        bookingButton.addClickListener(e -> getUI().getPage().setLocation("/book"));

        mainLayout.addComponent(buttonsLayout);

    }
}
