package org.example.fxmldemo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.Arrays;
import java.util.List;

import static org.example.fxmldemo.HelloApplication.fileManager;

public class ViewCalenderController{
    @FXML
    private BorderPane mainApplicationPane;

    public void SetMainViewPane(BorderPane mainApplicationPane) {
        this.mainApplicationPane = mainApplicationPane;
    }

    @FXML private GridPane gridPane;
    @FXML private Label nameLabel;
    @FXML private Label categoryLabel;
    @FXML private Label locationLabel;
    @FXML private Label timeLabel;
    @FXML private Label dateLabel;
    @FXML private Label repeatingDaysLabel;
    @FXML private Label endRepeatDate;

    public void initializeDefaultEventPage() {
        int rowIndex = 1;
        for (Event event : fileManager.getEventList().getEvents()) {
            nameLabel = new Label(event.getName());
            categoryLabel = new Label(event.getCategory().toUpperCase());
            locationLabel = new Label(event.getLocation());
            timeLabel = new Label(event.getStartTime() + "/" + event.getEndTime());
            dateLabel = new Label(event.getStartDate());

            if (event.getRepeatingDays() == null) {
                repeatingDaysLabel = new Label("");
                endRepeatDate = new Label("");
            }
            else {
                String days = concatRepeatingDays(event);
                repeatingDaysLabel = new Label(days);
                endRepeatDate = new Label(event.getEndRepeatingDate());
            }

            List<Label> labels = Arrays.asList(nameLabel, categoryLabel, locationLabel, timeLabel,
                    dateLabel, repeatingDaysLabel, endRepeatDate);

            for (Label label : labels) {
                label.setFont(new Font("Times New Roman", 16));
            }

            gridPane.add(nameLabel, 0, rowIndex);
            gridPane.add(categoryLabel, 1, rowIndex);
            gridPane.add(locationLabel, 2, rowIndex);
            gridPane.add(timeLabel, 3, rowIndex);
            gridPane.add(dateLabel, 4, rowIndex);
            gridPane.add(repeatingDaysLabel, 5, rowIndex);
            gridPane.add(endRepeatDate,6, rowIndex);

            rowIndex++;
        }
    }
    public String concatRepeatingDays(Event event) {
        return String.join(",", event.getRepeatingDays());
    }
}
