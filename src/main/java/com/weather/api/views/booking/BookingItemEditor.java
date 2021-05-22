package com.weather.api.views.booking;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.weather.api.domian.BookingItem;
import com.weather.api.domian.HotelGroup;
import com.weather.api.service.BookingItemService;
import com.weather.api.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;



@SpringUI(path = "/book")
public class BookingItemEditor extends UI {
    private VerticalLayout mainLayout;
    private Button saveItem;
    private Button delete;
    private Button update;
    private NativeSelect<String> hotelStars;
    private NativeSelect<Long> hotel;
    TextField id = new TextField("ID");
    TextField title = new TextField("TITLE");
    TextField content = new TextField("CONTENT");
    TextField price = new TextField("PRICE");
    private Image imageItem;
    private Long numIdpicture;
    final Grid<BookingItem> grid;

    @Autowired
    private BookingItemService bookingItemService;

    @Autowired
    private ImageService imageService;


    public BookingItemEditor(BookingItemService bookingItemService) {
        this.grid = new Grid<>(BookingItem.class);
    }


    protected void init(VaadinRequest vaadinRequest) {
        id.setValue("1");
        price.setValue("1");
        mainLayout();
        setHeader();
        uploadLayout();
        actions();
        find();
        setFindAll();
        list();


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

    private void uploadLayout() {
        HorizontalLayout uploadLayout = new HorizontalLayout();
        uploadLayout.setWidth(null);
        uploadLayout.setSpacing(true);
        hotelStars = new NativeSelect<>("HOTEL STARS");
        hotel = new NativeSelect<>("HOTEL");

        mainLayout.addComponent(uploadLayout);
        ArrayList<String> hotelStarsItems = new ArrayList<>();
        hotelStarsItems.add("1Stars");
        hotelStarsItems.add("2Stars");
        hotelStars.setItems(hotelStarsItems);
        hotelStars.setValue(hotelStarsItems.get(0));
        uploadLayout.addComponents(id, title, content, price, hotelStars, hotel);
        ArrayList<Long> hotelIdItems = new ArrayList<>();
        hotelIdItems.add(1L);
        hotelIdItems.add(2l);
        hotel.setItems(hotelIdItems);
        hotel.setValue(hotelIdItems.get(0));


    }

    private void actions() {
        HorizontalLayout actionLayout = new HorizontalLayout();
        actionLayout.setWidth(null);
        actionLayout.setSpacing(true);
        Button saveItem = new Button("Save");
        Button delete = new Button("Delete");
        Button update = new Button("Update");
        actionLayout.addComponents(saveItem, delete, update);

        saveItem.addClickListener(clickEvent -> {
            if (!title.getValue().equals("")) {
                if (!content.getValue().equals("")) {
                    saveData();
                    Notification.show("Added");
                } else {
                    Notification.show("Please enter data");
                }
            } else {
                Notification.show("Please enter data");
            }
        });
        delete.addClickListener(clickEvent -> {
            deleteItem();
            Notification.show("Deleted");
        });
        update.addClickListener(clickEvent -> {
            if (!title.getValue().equals("")) {
                saveData();
                Notification.show("Added");
            } else
                Notification.show("Please enter data");
        });
        mainLayout.addComponent(actionLayout);


    }

    private void find() {
        HorizontalLayout findLayout = new HorizontalLayout();
        Image imageItem = new Image();
        imageItem.setWidth("256px");
        imageItem.setHeight("256px");
        findLayout.setWidth(null);
        findLayout.setSpacing(true);
        Button findAll = new Button("FindAll");
        Button findById = new Button("FindByID");
        findLayout.addComponents(findAll, findById);
        mainLayout.addComponent(findLayout);
        findAll.addClickListener(clickEvent -> list());
        findById.addClickListener(clickEvent -> {
            findOne();
            numIdpicture = Long.parseLong(String.valueOf(id.getValue()));
            imageItem.setSource(new ExternalResource(imageService.getPictureAdress(numIdpicture)));

        });

        findLayout.addComponent(imageItem);
    }

    private void setFindAll() {

        HorizontalLayout gridLayout = new HorizontalLayout();
        grid.setColumnOrder("id", "title", "content", "price", "hotelGroup");
        gridLayout.addComponent(grid);
        mainLayout.addComponent(gridLayout);

    }

    private void list() {
        grid.setItems(bookingItemService.findAllBookings());

    }

    private void saveData() {
        Long numId = Long.parseLong(String.valueOf(id.getValue()));
        Double doublePrice = Double.parseDouble(String.valueOf(price.getValue()));
        HotelGroup hotelGroup = new HotelGroup(hotel.getValue(), hotelStars.getValue());
        bookingItemService.saveBookingItem(new BookingItem(numId, title.getValue(), content.getValue(), doublePrice, hotelGroup));

    }

    private void deleteItem() {
        Long numId = Long.parseLong(String.valueOf(id.getValue()));
        bookingItemService.deleteBooking(numId);
    }

    private void findOne() {
        Long numId = Long.parseLong(String.valueOf(id.getValue()));
        grid.setItems(bookingItemService.findBookingById(numId).get());


    }


}



