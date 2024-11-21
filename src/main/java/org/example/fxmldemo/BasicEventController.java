package org.example.fxmldemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.text.ParseException;

import static org.example.fxmldemo.HelloApplication.fileManager;

public class BasicEventController extends EventController {
    private BorderPane mainApplicationPane;

    @FXML private Label welcomeMessage;
    @FXML public Button createEvent;
    @FXML public Button submitAllEvents;

    @FXML public Button eventChange;

    public void SetMainViewPane(BorderPane mainApplicationPane) {
        this.mainApplicationPane = mainApplicationPane;
    }

    public void writeWelcomeMessage() {
        welcomeMessage.setText("Date: " + UserController.localDate + ", Current Day: " + UserController.dayOfWeek);
    }

    public void submitBasicEvent(ActionEvent actionEvent) throws IOException, ParseException {
        String nameEvent = getNameField().getText();
        String category = getCategoryField().getText();
        String location = getLocationField().getText();
        String startTime = getStartTimeField().getText();
        String endTime = getEndTimeField().getText();
        String date = getDateField().getText();

        Event event = new Event();

        event.setName(nameEvent);
        event.setCategory(category);
        event.setLocation(location);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setStartDate(date);
        event.setEndRepeatingDate(date);
        event.setRepeatingDays(null);

        fileManager.getEventList().getEvents().add(event);

        getNameField().clear();
        getCategoryField().clear();
        getLocationField().clear();
        getStartTimeField().clear();
        getEndTimeField().clear();
        getDateField().clear();
    }

    public void submitAllEvents(ActionEvent actionEvent) {
        fileManager.saveEventsFile();
        mainApplicationPane.setCenter(null);
    }

    // on clicked to submit event change
    public void editEventChange() {
        String nameEvent = getNameField().getText();
        String category = getCategoryField().getText();
        String location = getLocationField().getText();
        String startTime = getStartTimeField().getText();
        String endTime = getEndTimeField().getText();
        String date = getDateField().getText();

        Event event = new Event();

        event.setName(nameEvent);
        event.setCategory(category);
        event.setLocation(location);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setStartDate(date);
        event.setEndRepeatingDate(date);
        event.setRepeatingDays(null);

        fileManager.getEventList().getEvents().set(EditEventController.selectedIndex, event);
        fileManager.saveEventsFile();

        mainApplicationPane.setCenter(null);
    }

    public void writeDefaultValuesToEdit(Event event) {
        getNameField().setText(event.getName());
        getCategoryField().setText(event.getCategory());
        getLocationField().setText(event.getLocation());
        getStartTimeField().setText(event.getStartTime());
        getEndTimeField().setText(event.getEndTime());
        getDateField().setText(event.getStartDate());

        submitAllEvents.setVisible(false);
    }
}
