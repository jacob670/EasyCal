package org.example.fxmldemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application{
    static Stage primaryStage = null;
    static FileManager fileManager = new FileManager();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainpage-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene mainScene = new Scene(root, 1200, 800);

        MainController helloController = fxmlLoader.getController();

        stage.setTitle("Easy Cal! An easy to use calender system!");
        stage.setScene(mainScene);
        stage.show();
        primaryStage = stage;

        helloController.writeWelcomeMessage();

        // loads events.json
        fileManager.loadEventsFile();

        helloController.outputListImportantEvents();

        // saves the events
        fileManager.saveEventsFile();
    }

    public static void main(String[] args) {
        launch();
    }
}