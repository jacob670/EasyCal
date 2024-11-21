package org.example.fxmldemo;

import java.util.List;

import static org.example.fxmldemo.HelloApplication.fileManager;

public class CalenderController {
    public static List<Event> importantEvents = fileManager.getEventType("important");
    public static List<Event> businessEvents = fileManager.getEventType("business");
    public static List<Event> socialEvents = fileManager.getEventType("social");
    public static List<Event> personalEvents = fileManager.getEventType("personal");
}
