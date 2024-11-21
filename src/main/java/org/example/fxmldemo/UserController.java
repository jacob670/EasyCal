package org.example.fxmldemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalDate;

public abstract class UserController {
    private UserJsonHandler userJsonHandler = null;
    private ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public static LocalDate localDate = LocalDate.now();
    public static DayOfWeek dayOfWeek = DayOfWeek.from(LocalDate.now());

    public static File userFile = new File("userData.json");

    private String currentFirstName = User.getJsonValue(this.objectMapper, userFile, "firstName");
    private String currentLastName = User.getJsonValue(this.objectMapper, userFile, "lastName");
    private String currentCompany = User.getJsonValue(this.objectMapper, userFile, "company");
    private String currentPosition = User.getJsonValue(this.objectMapper, userFile, "position");
    private String currentFullTimeStatus = User.getJsonValue(this.objectMapper, userFile, "isFullTime");

    private User applicationUser = new User(currentFirstName, currentLastName, currentCompany, currentPosition);

    abstract void writeWelcomeMessage();

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField companyField;
    @FXML private TextField positionField;
    @FXML private TextField fullTimeField;
    @FXML private Label welcomeMessage;

    public TextField getFirstNameField() {
        return firstNameField;
    }

    public TextField getLastNameField() {
        return lastNameField;
    }

    public TextField getCompanyField() {
        return companyField;
    }

    public TextField getPositionField() {
        return positionField;
    }

    public TextField getFullTimeField() {
        return fullTimeField;
    }

    public Label getWelcomeMessage() {
        return welcomeMessage;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public File getUserFile() {
        return userFile;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public String getCurrentFirstName() {
        return currentFirstName;
    }

    public String getCurrentLastName() {
        return currentLastName;
    }

    public String getCurrentCompany() {
        return currentCompany;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public String getCurrentFullTimeStatus() {
        return currentFullTimeStatus;
    }

    public User getApplicationUser() {
        return applicationUser;
    }

    public void setCurrentFullTimeStatus(String currentFullTimeStatus) {
        this.currentFullTimeStatus = currentFullTimeStatus;
    }

    public void processSubmitButton() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String company = companyField.getText();
        String position = positionField.getText();
        boolean isFullTime = fullTimeField.getText().equals("yes");

        // creates new instance of User with form data and creates + writes to userData.json file
        userJsonHandler = new User(firstName, lastName, company, position, isFullTime);
        userJsonHandler.createUserDataFile(getObjectMapper(), userJsonHandler, getUserFile());
    }
}