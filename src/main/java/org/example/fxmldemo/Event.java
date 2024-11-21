package org.example.fxmldemo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Event{
    private String name;
    private String category;
    private String location;
    private LocalTime startTime;
    private LocalDate startDate;
    private LocalTime endTime;
    private LocalDate endRepeatingDate;
    private List<String> repeatingDays;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartTime() {
        return startTime.toString();
    }

    public void setStartTime(String startTime){
        this.startTime = LocalTime.parse(startTime, DateTimeFormatter.ISO_LOCAL_TIME);
    }

    public String getStartDate() {
        return startDate.toString();
    }

    public void setStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public String getEndTime() {
        return endTime.toString();
    }

    public void setEndTime(String endTime) {
        this.endTime = LocalTime.parse(endTime, DateTimeFormatter.ISO_LOCAL_TIME);
    }

    public String getEndRepeatingDate() {
        return endRepeatingDate.toString();
    }

    public void setEndRepeatingDate(String endRepeatingDate) {
        this.endRepeatingDate = LocalDate.parse(endRepeatingDate, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public List<String> getRepeatingDays() {
        return repeatingDays;
    }

    public void setRepeatingDays(List<String> repeatingDays) {
        this.repeatingDays = repeatingDays;
    }
}
