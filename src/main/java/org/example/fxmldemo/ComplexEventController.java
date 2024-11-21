package org.example.fxmldemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

import java.util.List;

import static org.example.fxmldemo.HelloApplication.fileManager;

public class ComplexEventController extends EventController{

    private BorderPane mainApplicationPane;

    @FXML private Label welcomeMessage;
    @FXML private TextArea repeatingDaysField;
    @FXML private TextArea endRepeatingDateField;

    @FXML public Button eventChange;
    @FXML public Button createEvent;
    @FXML public Button submitAllEvents;

    public void SetMainViewPane(BorderPane mainApplicationPane) {
        this.mainApplicationPane = mainApplicationPane;
    }

    public TextArea getRepeatingDaysField() {
        return repeatingDaysField;
    }

    public TextArea getEndRepeatingDateField() {
        return endRepeatingDateField;
    }

    public void writeWelcomeMessage() {
        welcomeMessage.setText("Date: " + UserController.localDate + ", Current Day: " + UserController.dayOfWeek);
    }

    public void submitComplexEvent(ActionEvent actionEvent) {
        String nameEvent = getNameField().getText();
        String category = getCategoryField().getText();
        String location = getLocationField().getText();
        String startTime = getStartTimeField().getText();
        String endTime = getEndTimeField().getText();
        String date = getDateField().getText();
        String endRepeatingDate = getEndRepeatingDateField().getText();

        List<String> repeatingDays = readRepeatingDays();

        Event event = new Event();

        event.setName(nameEvent);
        event.setCategory(category);
        event.setLocation(location);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setStartDate(date);
        event.setEndRepeatingDate(endRepeatingDate);
        event.setRepeatingDays(repeatingDays);

        fileManager.getEventList().getEvents().add(event);

        getNameField().clear();
        getCategoryField().clear();
        getLocationField().clear();
        getStartTimeField().clear();
        getEndTimeField().clear();
        getDateField().clear();
        getRepeatingDaysField().clear();
        getEndRepeatingDateField().clear();
    }

    public void submitAllEvents(ActionEvent actionEvent) {
        fileManager.saveEventsFile();

        mainApplicationPane.setCenter(null);
    }

    private List<String> readRepeatingDays() {
        String days = repeatingDaysField.getText();
        //List<String> repeatingDays = List.of(days.split(","));

        return List.of(days.split(","));
//        return repeatingDays;
    }

    public void writeDefaultValuesToEdit(Event event) {
        getNameField().setText(event.getName());
        getCategoryField().setText(event.getCategory());
        getLocationField().setText(event.getLocation());
        getStartTimeField().setText(event.getStartTime());
        getEndTimeField().setText(event.getEndTime());
        getDateField().setText(event.getStartDate());

        String days = concatRepeatingDays(event);

        getRepeatingDaysField().setText(days);
        getEndRepeatingDateField().setText(event.getEndRepeatingDate());

        submitAllEvents.setVisible(false);
    }

    public void editEventChange() {
        String nameEvent = getNameField().getText();
        String category = getCategoryField().getText();
        String location = getLocationField().getText();
        String startTime = getStartTimeField().getText();
        String endTime = getEndTimeField().getText();
        String date = getDateField().getText();
        String endRepeatingDate = getEndRepeatingDateField().getText();

        List<String> repeatingDays = readRepeatingDays();

        Event event = new Event();

        event.setName(nameEvent);
        event.setCategory(category);
        event.setLocation(location);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setStartDate(date);
        event.setEndRepeatingDate(endRepeatingDate);
        event.setRepeatingDays(repeatingDays);

        fileManager.getEventList().getEvents().set(EditEventController.selectedIndex, event);
        fileManager.saveEventsFile();

        mainApplicationPane.setCenter(null);
    }

    private String concatRepeatingDays(Event event) {
        return String.join(",", event.getRepeatingDays());
    }

}
