package com.weather.api.views;


import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ClassResource;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import com.weather.api.service.WeatherService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@SpringUI(path = "/weather")
public class MainView extends UI {
    @Autowired
    private WeatherService weatherService;

    private VerticalLayout mainLayout;
    private NativeSelect<String> unitSelect;
    private TextField cityTextField;
    private Button serchButton;
    private HorizontalLayout dashboard;
    private Label location;
    private Label currentTemp;
    private HorizontalLayout mainDescription;
    private Label weatherDescription;
    private Label MaxWeather;
    private Label MinWeather;
    private Label Humidity;
    private Label Pressure;
    private Label Wind;
    private Label FeelsLike;
    private Image iconImage;


    @Override
    protected void init(VaadinRequest vaadinRequest) {
        mainLayout();
        setHeader();
        setLogo();
        setForm();
        dashboardTitle();
        dasshBoardDetails();
        serchButton.addClickListener(clickEvent -> {
            if (!cityTextField.getValue().equals("")){
                try {
                    updateUI();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else
                    Notification.show("Please Enter city name");
        });

    }

    private void updateUI() throws JSONException {
        String city=cityTextField.getValue();
        String defaultUnit;
        weatherService.setCityName(city);
        if (unitSelect.getValue().equals("F")){
            weatherService.setUnit("imperials");
            unitSelect.setValue("F");
            defaultUnit="\u00b0"+"F";
        }else {
            weatherService.setUnit("metric");
            defaultUnit="\u00b0"+"C";
            unitSelect.setValue("C");
        }

        location.setValue("Currently in "+city);
        JSONObject mainObject=weatherService.returnMain();
        int temp=mainObject.getInt("temp");
        currentTemp.setValue(temp+defaultUnit);

        String iconCode=null;
        String weatherDescriptionNew=null;
        JSONArray jsonArray=weatherService.returnWeatherArray();
        for (int i= 0; i<jsonArray.length();i++) {
        JSONObject weatherObj=jsonArray.getJSONObject(i);
        iconCode=weatherObj.getString("icon");
        weatherDescriptionNew=weatherObj.getString("description");
        }
        iconImage.setSource(new ExternalResource("http://openweathermap.org/img/wn/"+iconCode+"@2x.png"));
        weatherDescription.setValue("Description: "+weatherDescriptionNew);
        MinWeather.setValue("MinTemp: "+ weatherService.returnMain().getInt("temp_min")+"°"+unitSelect.getValue());
        MaxWeather.setValue("MaxTemp: "+ weatherService.returnMain().getInt("temp_max")+"°"+unitSelect.getValue());
        Pressure.setValue("Pressure: "+weatherService.returnMain().getInt("pressure")+"HPa");
        Humidity.setValue("Humidity: "+weatherService.returnMain().getInt("humidity")+"%");
        Wind.setValue("Wind: "+weatherService.returnWind().getInt("speed")+" knots");
        FeelsLike.setValue("FeelsLike "+weatherService.returnMain().getDouble("feels_like")+"°"+unitSelect.getValue());

        mainLayout.addComponents(dashboard,mainDescription);
    }


    private void mainLayout() {
        iconImage=new Image();
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
    private void setLogo(){
        HorizontalLayout logo=new HorizontalLayout();
        logo.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        logo.setSpacing(true);
        logo.setMargin(true);
        Image image=new Image(null,new ClassResource("/static/weatherLoggo.png"));
        logo.setWidth("256px");
        logo.setHeight("256px");

        logo.addComponent(image);
        mainLayout.addComponents(logo);

    }
    private void setForm() {
        HorizontalLayout formLayout=new HorizontalLayout();

        formLayout.setSpacing(true);
        formLayout.setMargin(true);


        //mainLayout.addComponents(unitSelect);

        cityTextField=new TextField();
        cityTextField.setWidth("80%");
        formLayout.addComponent(cityTextField);

        unitSelect=new NativeSelect<>();
        ArrayList<String> items=new ArrayList<>();
        items.add("C");
        items.add("F");
        unitSelect.setItems(items);
        unitSelect.setValue(items.get(0));
        formLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        formLayout.addComponent(unitSelect);

        serchButton=new Button();
        serchButton.setIcon(VaadinIcons.SEARCH);
        formLayout.addComponent(serchButton);



        mainLayout.addComponents(formLayout);


    }
    private void dashboardTitle(){
        dashboard=new HorizontalLayout();
        dashboard.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        location=new Label("Currently in Krakow ");
        location.addStyleName(ValoTheme.LABEL_H2);
        location.addStyleName(ValoTheme.LABEL_LIGHT);

        currentTemp=new Label("10 °C");
        currentTemp.setStyleName(ValoTheme.LABEL_BOLD);
        currentTemp.setStyleName(ValoTheme.LABEL_H1);

        dashboard.addComponents(location,iconImage,currentTemp);


    }

    private void dasshBoardDetails(){
        mainDescription=new HorizontalLayout();
        mainDescription.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        VerticalLayout decriptionLayout=new VerticalLayout();
        decriptionLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        weatherDescription=new Label("Decription: Clear Skies");
        weatherDescription.setStyleName(ValoTheme.LABEL_SUCCESS);
        decriptionLayout.addComponents(weatherDescription);

        MinWeather=new Label("Min:53");
        decriptionLayout.addComponents(MinWeather);

        MaxWeather=new Label("Max:53");
        decriptionLayout.addComponents(MaxWeather);

        VerticalLayout pressureLayout= new VerticalLayout();
        pressureLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        Pressure=new Label("Pressure 321Pa");
        pressureLayout.addComponents(Pressure);

        Humidity=new Label("Humidity: 23");
        pressureLayout.addComponents(Humidity);

        Wind=new Label("Wind: 23");
        pressureLayout.addComponents(Wind);

        FeelsLike=new Label("FeelsLike: 23");
        pressureLayout.addComponents(FeelsLike);

        mainDescription.addComponents(decriptionLayout,pressureLayout);


    }



}
