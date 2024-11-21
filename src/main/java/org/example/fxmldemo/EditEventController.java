package org.example.fxmldemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

import static org.example.fxmldemo.HelloApplication.fileManager;

public class EditEventController extends EventController {
    @FXML private BorderPane mainApplicationPane;

    static int selectedIndex;

    public void SetMainViewPane(BorderPane mainApplicationPane) {
        this.mainApplicationPane = mainApplicationPane;
    }

    public void loadEvent(ActionEvent actionEvent) throws IOException {
        selectedIndex = getEventsView().getSelectionModel().getSelectedIndex();
        Event event = fileManager.getEventList().getEvents().get(selectedIndex);

        // load basic event form
        if (event.getRepeatingDays() == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("basic-event-scene.fxml"));
            Parent layoutRoot = fxmlLoader.load();

            BasicEventController basicEventController;
            basicEventController = fxmlLoader.getController();

            basicEventController.SetMainViewPane(mainApplicationPane);

            mainApplicationPane.setCenter(layoutRoot);

            basicEventController.eventChange.setVisible(true);
            basicEventController.createEvent.setVisible(false);

            basicEventController.writeWelcomeMessage();
            basicEventController.writeDefaultValuesToEdit(event);
        }

        // load complex event form
        else {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("complex-event-scene.fxml"));
            Parent layoutRoot = fxmlLoader.load();

            ComplexEventController complexEventController;
            complexEventController = fxmlLoader.getController();

            complexEventController.SetMainViewPane(mainApplicationPane);

            mainApplicationPane.setCenter(layoutRoot);

            complexEventController.eventChange.setVisible(true);
            complexEventController.createEvent.setVisible(false);
            complexEventController.writeDefaultValuesToEdit(event);
        }
    }
}
