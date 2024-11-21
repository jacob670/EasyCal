package org.example.fxmldemo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.example.fxmldemo.HelloApplication.fileManager;

public class FileManager {
    private EventList eventList = new EventList();

    public EventList getEventList() {
        return eventList;
    }

    public void loadEventsFile() {
        File file = new File("events.json");
        ObjectMapper mapper = new ObjectMapper();

        try {
            eventList = mapper.readValue(file, EventList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveEventsFile() {
        File file = new File("events.json");
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, eventList);
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public List<Event> getEventType(String category) {
        List<Event> foundEvents = new ArrayList();

        for (Event event : fileManager.getEventList().events) {
            if (event.getCategory().equals(category)) {
                foundEvents.add(event);
            }
        }
        return foundEvents;
    }
}
