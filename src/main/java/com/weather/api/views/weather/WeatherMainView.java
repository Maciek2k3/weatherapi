package com.weather.api.views.weather;


import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ClassResource;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import com.weather.api.config.AdminConfig;
import com.weather.api.domian.Mail;
import com.weather.api.service.SmpleEmailService;
import com.weather.api.domian.dto.WeatherDto;
import com.weather.api.service.WeatherService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@SpringUI(path = "/weather")
public class WeatherMainView extends UI {

    public final String SUBJECT = "Today weather";
    private VerticalLayout mainLayout;
    private NativeSelect<String> unitSelect;
    private TextField cityTextField;
    private Button serchButton;
    private HorizontalLayout dashboard;
    private Label location;
    private Label currentTemp;
    private HorizontalLayout mainDescription;
    private Label weatherDescription;
    private Label maxWeather;
    private Label minWeather;
    private Label humidity;
    private Label pressure;
    private Label wind;
    private Label feelsLike;
    private Image iconImage;
    private HorizontalLayout mailLayout;
    private Button mailButton;
    private TextField mailText;

    @Autowired
    private WeatherService weatherService;
    @Autowired
    private SmpleEmailService smpleEmailService;
    @Autowired
    private AdminConfig adminConfig;


    protected void init(VaadinRequest vaadinRequest) {
        mainLayout();
        setHeader();
        setLogo();
        setForm();
        dashboardTitle();
        dasshBoardDetails();
        serchButton.addClickListener(clickEvent -> {
            if (!cityTextField.getValue().equals("")) {
                try {
                    updateUI();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else
                Notification.show("Please Enter city name");

        });

        setMailHeader();
        mailForm();
        sendMail();

    }

    private void updateUI() throws JSONException {
        String city = cityTextField.getValue();
        String defaultUnit;
        String chosenUnit;
        if (unitSelect.getValue().equals("F")) {
            unitSelect.setValue("F");
            defaultUnit = "\u00b0" + "F";
            chosenUnit = "imperial";
        } else {
            defaultUnit = "\u00b0" + "C";
            unitSelect.setValue("C");
            chosenUnit = "metric";
        }
        WeatherDto weatherDto = weatherService.getWeather(city, chosenUnit);
        location.setValue("Currently in " + city);

        minWeather.setValue("MinTemp: " + weatherDto.getTempMin() + defaultUnit);
        maxWeather.setValue("MaxTemp: " + weatherDto.getTempMax() + defaultUnit);
        pressure.setValue("Pressure: " + weatherDto.getPressure() + "HPa");
        humidity.setValue("Humidity: " + weatherDto.getHumidity() + "%");
        wind.setValue("Wind: " + weatherDto.getSpeed() + " knots");
        feelsLike.setValue("FeelsLike " + weatherDto.getFeelsLike() + defaultUnit);
        currentTemp.setValue(weatherDto.getTemp() + defaultUnit);
        iconImage.setSource(new ExternalResource("http://openweathermap.org/img/wn/" + weatherDto.getIconCode() + "@2x.png"));
        weatherDescription.setValue(weatherDto.getWeatherDescription());
        mainLayout.addComponents(dashboard, mainDescription);
    }


    private void mainLayout() {
        iconImage = new Image();
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
        Label title = new Label("Weather APP");
        header.addComponent(title);
        mainLayout.addComponents(header);

    }

    private void setLogo() {
        HorizontalLayout logo = new HorizontalLayout();
        logo.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        logo.setSpacing(true);
        logo.setMargin(true);
        Image image = new Image(null, new ClassResource("/static/weatherLoggo.png"));
        logo.setWidth("256px");
        logo.setHeight("256px");

        logo.addComponent(image);
        mainLayout.addComponents(logo);

    }

    private void setForm() {
        HorizontalLayout formLayout = new HorizontalLayout();

        formLayout.setSpacing(true);
        formLayout.setMargin(true);


        cityTextField = new TextField();
        cityTextField.setWidth("80%");
        formLayout.addComponent(cityTextField);

        unitSelect = new NativeSelect<>();
        ArrayList<String> items = new ArrayList<>();
        items.add("C");
        items.add("F");
        unitSelect.setItems(items);
        unitSelect.setValue(items.get(0));
        formLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        formLayout.addComponent(unitSelect);

        serchButton = new Button();
        serchButton.setIcon(VaadinIcons.SEARCH);
        formLayout.addComponent(serchButton);


        mainLayout.addComponents(formLayout);


    }

    private void dashboardTitle() {
        dashboard = new HorizontalLayout();
        dashboard.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        location = new Label("Currently in Krakow ");
        location.addStyleName(ValoTheme.LABEL_H2);
        location.addStyleName(ValoTheme.LABEL_LIGHT);

        currentTemp = new Label("10 °C");
        currentTemp.setStyleName(ValoTheme.LABEL_BOLD);
        currentTemp.setStyleName(ValoTheme.LABEL_H1);

        dashboard.addComponents(location, iconImage, currentTemp);


    }

    private void dasshBoardDetails() {
        mainDescription = new HorizontalLayout();
        mainDescription.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        VerticalLayout decriptionLayout = new VerticalLayout();
        decriptionLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        weatherDescription = new Label("Decription: Clear Skies");
        weatherDescription.setStyleName(ValoTheme.LABEL_SUCCESS);
        decriptionLayout.addComponents(weatherDescription);

        minWeather = new Label("Min:53");
        decriptionLayout.addComponents(minWeather);

        maxWeather = new Label("Max:53");
        decriptionLayout.addComponents(maxWeather);

        VerticalLayout pressureLayout = new VerticalLayout();
        pressureLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        pressure = new Label("Pressure 321Pa");
        pressureLayout.addComponents(pressure);

        humidity = new Label("Humidity: 23");
        pressureLayout.addComponents(humidity);

        wind = new Label("Wind: 23");
        pressureLayout.addComponents(wind);

        feelsLike = new Label("FeelsLike: 23");
        pressureLayout.addComponents(feelsLike);

        mainDescription.addComponents(decriptionLayout, pressureLayout);


    }

    private void setMailHeader() {
        HorizontalLayout mailHeader = new HorizontalLayout();
        mailHeader.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
        Label labelMail = new Label("Send Mail");
        mailHeader.addComponent(labelMail);
        mainLayout.addComponents(mailHeader);

    }

    private void mailForm() {
        mailLayout = new HorizontalLayout();
        mailLayout.setSpacing(true);
        mailLayout.setMargin(true);

        mailText = new TextField();
        mailText.setWidth("80%");
        mailLayout.addComponent(mailText);

        mailButton = new Button();
        mailButton.setIcon(VaadinIcons.ENTER);
        mailLayout.addComponent(mailButton);
        mainLayout.addComponent(mailLayout);

    }

    private void sendMail() {
        mailButton.addClickListener(clickEvent -> {
            if (!mailText.getValue().equals("")) {
                smpleEmailService.send(new Mail(mailText.getValue(), adminConfig.getAdminMail(), SUBJECT, "New weather"));
                Notification.show("Mail was sent");
            } else
                Notification.show("Please enter mail");
        });

    }


}
