package org.example.fxmldemo;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class DeleteUserController extends UserController{
    @FXML private BorderPane mainApplicationPane;

    public void SetMainViewPane(BorderPane mainApplicationPane) {
        this.mainApplicationPane = mainApplicationPane;
    }

    @Override
    void writeWelcomeMessage() {
        getWelcomeMessage().setText("Hello " + getCurrentFirstName() + " " + getCurrentLastName() + ". " +
                                    "Your account will be permanently erased.");
    }

    @FXML
    public void deleteUser() throws IOException {
        UserController.userFile.delete();

        getWelcomeMessage().setText("Your account has been erased. Therefore all events and calenders have been deleted");
        mainApplicationPane.setCenter(null);
    }

}
