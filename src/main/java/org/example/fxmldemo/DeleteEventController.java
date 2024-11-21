package org.example.fxmldemo;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

import static org.example.fxmldemo.HelloApplication.fileManager;

public class DeleteEventController extends EventController{
//    @FXML public ListView eventsView;

    @FXML private BorderPane mainApplicationPane;

    public void SetMainViewPane(BorderPane mainApplicationPane) {
        this.mainApplicationPane = mainApplicationPane;
    }

    public void deleteEvent() {
        int selectedIndex = getEventsView().getSelectionModel().getSelectedIndex();
        getEventsView().getItems().remove(selectedIndex);

        fileManager.getEventList().events.remove(selectedIndex);
        fileManager.saveEventsFile();

        mainApplicationPane.setCenter(null);
    }
}
