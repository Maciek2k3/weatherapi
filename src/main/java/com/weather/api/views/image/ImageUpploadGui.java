package com.weather.api.views.image;


import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.weather.api.service.ImageUploaderService;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI(path = "/upload")
public class ImageUpploadGui extends UI {

    private TextField textFieldSource;
    private TextField textFieldId;
    private Button button;
    private Long numId;
    private VerticalLayout mainLayout;

    @Autowired
    private ImageUploaderService imageUploader;


    @Override
    protected void init(VaadinRequest vaadinRequest) {
        mainLayout();
        setHeader();
        uploadPicture();

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
        Label title = new Label("UPPLOADER");
        header.addComponent(title);
        mainLayout.addComponents(header);
    }

    private void uploadPicture() {
        HorizontalLayout uploadLayout = new HorizontalLayout();
        uploadLayout.setSpacing(true);
        TextField textFieldSource = new TextField("SOURCE");
        TextField textFieldId = new TextField("ID");
        Button button = new Button("upload");

        uploadLayout.addComponents(textFieldSource, textFieldId, button);
        textFieldId.setValue("1");

        button.addClickListener(buttonClickEvent ->
        {
            Long numId = Long.parseLong(String.valueOf(textFieldId.getValue()));
            String uploadedImage = imageUploader.uploadFileAndSaveToDb(textFieldSource.getValue(),numId);
            Image image = new Image();
            image.setSource(new ExternalResource(uploadedImage));
            uploadLayout.addComponent(image);
            Notification.show("Succesfull upload picture");


        });

        mainLayout.addComponent(uploadLayout);


    }
}