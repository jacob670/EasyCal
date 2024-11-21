package org.example.fxmldemo;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class UserExistsSceneController extends UserController {

    @FXML private BorderPane mainApplicationPane;

    public void SetMainViewPane(BorderPane mainApplicationPane) {
        this.mainApplicationPane = mainApplicationPane;
    }

    @FXML
    public void handleUserButton() {
        processSubmitButton();
        mainApplicationPane.setCenter(null);
    }

    @Override
    public void writeWelcomeMessage() {
        getWelcomeMessage().setText("Welcome back " + getApplicationUser().getFirstName() + " " + getApplicationUser().getLastName() + "!" + " " +
                                " The Current Date is " + getLocalDate() + " and it is " + getDayOfWeek());
        System.out.println("Hello");
    }

    public void writeDefaultValuesToForm() {
        getFirstNameField().setText(getCurrentFirstName());
        getLastNameField().setText(getCurrentLastName());
        getCompanyField().setText(getCurrentCompany());
        getPositionField().setText(getCurrentPosition());

        if (getCurrentFullTimeStatus().equals("true")) {
            setCurrentFullTimeStatus("yes");
            getFullTimeField().setText(getCurrentFullTimeStatus());
        }
        else {
            setCurrentFullTimeStatus("no");
            getFullTimeField().setText(getCurrentFullTimeStatus());
            System.out.println(getCurrentFirstName());
        }
    }
}
