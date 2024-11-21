package org.example.fxmldemo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.example.fxmldemo.HelloApplication.fileManager;

public class SearchEventController {
    @FXML
    private BorderPane mainApplicationPane;

    public void SetMainViewPane(BorderPane mainApplicationPane) {
        this.mainApplicationPane = mainApplicationPane;
    }

    @FXML private TextArea searchBox;
    @FXML private ListView listView;

    private Event resultedEvent = null;

    public void searchByCategory() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("search-category-scene.fxml"));
        Parent layoutRoot = fxmlLoader.load();

        SearchEventController searchEventController;
        searchEventController = fxmlLoader.getController();

        searchEventController.SetMainViewPane(mainApplicationPane);

        mainApplicationPane.setCenter(layoutRoot);
    }

    public void searchByName() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("search-name-scene.fxml"));
        Parent layoutRoot = fxmlLoader.load();

        SearchEventController searchEventController;
        searchEventController = fxmlLoader.getController();

        searchEventController.SetMainViewPane(mainApplicationPane);

        mainApplicationPane.setCenter(layoutRoot);
    }

    public void searchEvent() {
        fileManager.loadEventsFile();
        String searchTerm = searchBox.getText();

        for (Event event : fileManager.getEventList().getEvents()) {
            if (searchTerm.toLowerCase().equals(event.getName().toLowerCase())) {
                resultedEvent = event;
                break;
            }
            else {
                resultedEvent = null;
            }
        }

        if (resultedEvent != null) {
            writeEventToScreen();
        }
        else {
            searchBox.setText(searchTerm + " is not a current event. Please enter the name of the event to get the details.");
        }

    }

    // search by name
    private void writeEventToScreen() {
        if (resultedEvent.getRepeatingDays() == null) {
            listView.getItems().add("NAME: " + resultedEvent.getName() + ",       TYPE: " + resultedEvent.getCategory() + ",      LOCATION: " + resultedEvent.getLocation() + ",      START TIME: " + resultedEvent.getStartTime() +
                    ",      END TIME: " + resultedEvent.getEndTime() + ",       DATE: " + resultedEvent.getStartDate());
        } else {
            listView.getItems().add("NAME: " + resultedEvent.getName() + ",       TYPE: " + resultedEvent.getCategory() + ",      LOCATION: " + resultedEvent.getLocation() + ",      START TIME: " + resultedEvent.getStartTime() +
                    ",      END TIME: " + resultedEvent.getEndTime() + ",       DATE: " + resultedEvent.getStartDate() + ",         REPEATING DAYS: " + resultedEvent.getRepeatingDays() + ",       END REPEATING DATE: " + resultedEvent.getEndRepeatingDate());
        }
    }

    public void searchCategory() {

        fileManager.loadEventsFile();
        List<String> categories = Arrays.asList("important", "business", "social", "personal");
        String searchCategory = searchBox.getText();

        processSearch(searchCategory, "important", CalenderController.importantEvents);
        processSearch(searchCategory, "business", CalenderController.businessEvents);
        processSearch(searchCategory, "social", CalenderController.socialEvents);
        processSearch(searchCategory, "personal", CalenderController.personalEvents);

        if (!categories.contains(searchCategory)) {
            searchBox.setText(searchCategory + " is not a correct category");
        }
    }

    // search by event category
    private void processSearch(String searchCategory, String term, List<Event> events) {
        if (searchCategory.equals(term)) {
            if (events.size() == 0) {
                searchBox.setText("There are no current events with the category " + searchCategory);
            }

            for (Event event : events) {
                if (event.getRepeatingDays() == null) {
                    listView.getItems().add("NAME: " + event.getName() + ",       TYPE: " + event.getCategory() + ",      LOCATION: " + event.getLocation() + ",      START TIME: " + event.getStartTime() +
                            ",      END TIME: " + event.getEndTime() + ",       DATE: " + event.getStartDate());
                } else {
                    listView.getItems().add("NAME: " + event.getName() + ",       TYPE: " + event.getCategory() + ",      LOCATION: " + event.getLocation() + ",      START TIME: " + event.getStartTime() +
                            ",      END TIME: " + event.getEndTime() + ",       DATE: " + event.getStartDate() + ",         REPEATING DAYS: " + event.getRepeatingDays() + ",       END REPEATING DATE: " + event.getEndRepeatingDate());
                }
            }
        }
    }
}
