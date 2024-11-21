package org.example.fxmldemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MainController extends UserController {

    @FXML private BorderPane mainApplicationPane;

    public void SetMainViewPane(BorderPane mainApplicationPane) {
        this.mainApplicationPane = mainApplicationPane;
    }

    @FXML private Label introMessage;
    @FXML private Label timeMessage;
    @FXML private Label currentTime;
    @FXML private ListView eventsView;

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("h:mm a");
    private LocalTime localTime = LocalTime.now();

    @FXML
    protected void submitUserPage() throws IOException {
        // if userData.json is not in directory/exists, set the scene to a unique UserController

        if (UserController.userFile.exists()) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user-exists-scene.fxml"));
            Parent layoutRoot = fxmlLoader.load();

            UserExistsSceneController userExistsSceneController;
            userExistsSceneController = fxmlLoader.getController();

            userExistsSceneController.SetMainViewPane(mainApplicationPane);

            mainApplicationPane.setCenter(layoutRoot);

            userExistsSceneController.writeDefaultValuesToForm();
            userExistsSceneController.writeWelcomeMessage();

        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user-does-not-exist-scene.fxml"));
            Parent layoutRoot = fxmlLoader.load();

            UserDoesNotExistController userDoesNotExistController;
            userDoesNotExistController = fxmlLoader.getController();

            userDoesNotExistController.SetMainViewPane(mainApplicationPane);

            mainApplicationPane.setCenter(layoutRoot);
            userDoesNotExistController.writeWelcomeMessage();
        }
    }

    @FXML
    protected void deleteUserPage() throws IOException {
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
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("delete-user-scene.fxml"));
            Parent layoutRoot = fxmlLoader.load();

            DeleteUserController deleteUserController;
            deleteUserController = fxmlLoader.getController();

            deleteUserController.SetMainViewPane(mainApplicationPane);

            mainApplicationPane.setCenter(layoutRoot);

            deleteUserController.writeWelcomeMessage();
        }
    }

    @FXML
    protected void addEventPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("choose-event-scene.fxml"));
        Parent layoutRoot = fxmlLoader.load();

        EventController eventController;
        eventController = fxmlLoader.getController();

        eventController.SetMainViewPane(mainApplicationPane);

        mainApplicationPane.setCenter(layoutRoot);
    }

    @FXML
    protected void deleteEventPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("delete-event-scene.fxml"));
        Parent layoutRoot = fxmlLoader.load();

        DeleteEventController deleteEventController;
        deleteEventController = fxmlLoader.getController();

        deleteEventController.SetMainViewPane(mainApplicationPane);

        mainApplicationPane.setCenter(layoutRoot);

        // outputs in list view from EventController. Inherits
        deleteEventController.outputEventList();
    }

    @FXML
    protected void editEventPage() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("edit-event-scene.fxml"));
        Parent layoutRoot = fxmlLoader.load();

        EditEventController editEventController;
        editEventController = fxmlLoader.getController();

        editEventController.SetMainViewPane(mainApplicationPane);

        mainApplicationPane.setCenter(layoutRoot);

        // outputs in list view from EventController. Inherits
        editEventController.outputEventList();
    }

    @FXML
    protected void viewCalenderPage() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view-calender-scene.fxml"));
        Parent layoutRoot = fxmlLoader.load();

        ViewCalenderController viewCalenderController;
        viewCalenderController = fxmlLoader.getController();

        viewCalenderController.SetMainViewPane(mainApplicationPane);

        mainApplicationPane.setCenter(layoutRoot);

        viewCalenderController.initializeDefaultEventPage();
    }

    @FXML
    protected void viewSearchEventPage() throws IOException {
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
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("search-event-scene.fxml"));
            Parent layoutRoot = fxmlLoader.load();

            SearchEventController searchEventController;
            searchEventController = fxmlLoader.getController();

            searchEventController.SetMainViewPane(mainApplicationPane);

            mainApplicationPane.setCenter(layoutRoot);
        }
    }



    @Override
    public void writeWelcomeMessage() {
        if (!UserController.userFile.exists()) {
            introMessage.setText("Welcome User. Please create a user by directing yourself the User tab.");
        }

        else {
            if (getCurrentFullTimeStatus().equals("yes")) {
                introMessage.setText("Welcome back " + getCurrentFirstName() + " " + getCurrentLastName() + "! You currently work for " +
                        getCurrentCompany() + " " + "and your status is a " + getCurrentPosition() + ". You are currently full time.");
            }
            else {
                introMessage.setText("Welcome back " + getCurrentFirstName() + " " + getCurrentLastName() + "! You currently work for " +
                        getCurrentCompany() + " " + "and your status is a " + getCurrentPosition() + ". You are currently not full time.");
            }
        }

        timeMessage.setText(getDayOfWeek() + ", " + getLocalDate());
        currentTime.setText("TIME: " + dtf.format(localTime));
    }

    public void outputListImportantEvents() {
        for (Event event : CalenderController.importantEvents) {
            eventsView.getItems().add("NAME: " + event.getName() + ",       TYPE: " + event.getCategory() + ",      LOCATION: " + event.getLocation() + ",      START TIME: " + event.getStartTime() +
                    ",      END TIME: " + event.getEndTime() + ",       DATE: " + event.getStartDate());
        }
    }

    public void exitProgram(ActionEvent actionEvent) {
        System.exit(0);
    }
}