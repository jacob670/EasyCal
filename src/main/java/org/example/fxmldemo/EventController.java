package org.example.fxmldemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.io.IOException;

import static org.example.fxmldemo.HelloApplication.fileManager;

public class EventController {
    @FXML private BorderPane mainApplicationPane;

    @FXML private TextArea nameField;
    @FXML private TextArea categoryField;
    @FXML private TextArea locationField;
    @FXML private TextArea startTimeField;
    @FXML private TextArea endTimeField;
    @FXML private TextArea dateField;
    @FXML private ListView eventsView;

    public void SetMainViewPane(BorderPane mainApplicationPane) {
        this.mainApplicationPane = mainApplicationPane;
    }

    public ListView getEventsView() {
        return eventsView;
    }

    public TextArea getNameField() {
        return nameField;
    }

    public TextArea getCategoryField() {
        return categoryField;
    }

    public TextArea getLocationField() {
        return locationField;
    }

    public TextArea getStartTimeField() {
        return startTimeField;
    }

    public TextArea getEndTimeField() {
        return endTimeField;
    }

    public TextArea getDateField() {
        return dateField;
    }

    public void createComplexEvent(ActionEvent actionEvent) throws IOException {
        if (!UserController.userFile.exists()) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user-does-not-exist-scene.fxml"));
            Parent layoutRoot = fxmlLoader.load();

            UserDoesNotExistController userDoesNotExistController;
            userDoesNotExistController = fxmlLoader.getController();

            userDoesNotExistController.SetMainViewPane(mainApplicationPane);

            mainApplicationPane.setCenter(layoutRoot);

            userDoesNotExistController.writeWelcomeMessage();
        }
        else {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("complex-event-scene.fxml"));
            Parent layoutRoot = fxmlLoader.load();

            ComplexEventController complexEventController;
            complexEventController = fxmlLoader.getController();

            complexEventController.SetMainViewPane(mainApplicationPane);
            mainApplicationPane.setCenter(layoutRoot);

            complexEventController.writeWelcomeMessage();

            complexEventController.eventChange.setVisible(false);
            complexEventController.createEvent.setVisible(true);
        }
    }

    public void createBasicEvent(ActionEvent actionEvent) throws IOException {
        if (!UserController.userFile.exists()) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user-does-not-exist-scene.fxml"));
            Parent layoutRoot = fxmlLoader.load();

            UserDoesNotExistController userDoesNotExistController;
            userDoesNotExistController = fxmlLoader.getController();

            userDoesNotExistController.SetMainViewPane(mainApplicationPane);

            mainApplicationPane.setCenter(layoutRoot);

            userDoesNotExistController.writeWelcomeMessage();
        }
        else {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("basic-event-scene.fxml"));
            Parent layoutRoot = fxmlLoader.load();

            BasicEventController basicEventController;
            basicEventController = fxmlLoader.getController();

            basicEventController.SetMainViewPane(mainApplicationPane);
            mainApplicationPane.setCenter(layoutRoot);

            basicEventController.eventChange.setVisible(false);
            basicEventController.createEvent.setVisible(true);
            basicEventController.writeWelcomeMessage();
        }
    }

    public void outputEventList() {
        File file = new File("events.json");

        if (file.exists()) {
            for (Event event : fileManager.getEventList().getEvents()) {
                if (event.getRepeatingDays() == null) {
                    eventsView.getItems().add("NAME: " + event.getName() + ",       TYPE: " + event.getCategory() + ",      LOCATION: " + event.getLocation() + ",      START TIME: " + event.getStartTime() +
                            ",      END TIME: " + event.getEndTime() + ",       DATE: " + event.getStartDate());
                } else {
                    eventsView.getItems().add("NAME: " + event.getName() + ",       TYPE: " + event.getCategory() + ",      LOCATION: " + event.getLocation() + ",      START TIME: " + event.getStartTime() +
                            ",      END TIME: " + event.getEndTime() + ",       DATE: " + event.getStartDate() + ",         REPEATING DAYS: " + event.getRepeatingDays() + ",       END REPEATING DATE: " + event.getEndRepeatingDate());
                }
            }
        }

        else {
            System.out.println("events.json does not exist");
        }
    }
}
