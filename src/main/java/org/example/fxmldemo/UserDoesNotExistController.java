package org.example.fxmldemo;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class UserDoesNotExistController extends UserController{
    @FXML private BorderPane mainApplicationPane;

    public void SetMainViewPane(BorderPane mainApplicationPane) {
        this.mainApplicationPane = mainApplicationPane;
    }

    public void handleUserButton() {
        processSubmitButton();
        mainApplicationPane.setCenter(null);
        writeWelcomeMessage();
    }

    @Override
    public void writeWelcomeMessage() {
        getWelcomeMessage().setText("Welcome New User! Once you hit the submit button, your account will be created!");
    }
}
