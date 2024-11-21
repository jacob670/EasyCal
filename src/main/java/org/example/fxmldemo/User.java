package org.example.fxmldemo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class User implements UserJsonHandler{
    private String firstName, lastName;
    private String company;
    private String position;

    private boolean isFullTime;

    public User(String firstName, String lastName, String company, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.position = position;
    }

    public User(String firstName, String lastName, String company, String position, boolean isFullTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.position = position;
        this.isFullTime = isFullTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompany() {
        return company;
    }

    public String getPosition() {
        return position;
    }

    public boolean isFullTime() {
        return isFullTime;
    }

    @Override
    public void createUserDataFile(ObjectMapper objectMapper, UserJsonHandler userJsonHandler, File file) {
        try {
            ObjectNode rootNode = objectMapper.createObjectNode();

            ObjectNode personNode = objectMapper.createObjectNode()
                    .put("firstName", this.firstName)
                    .put("lastName", this.lastName)
                    .put("company", this.company)
                    .put("position", this.position)
                    .put("isFullTime", this.isFullTime);

            rootNode.set("ApplicationUser", personNode);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, rootNode);

        }
        catch (IOException e) {
            System.err.println("Error: " + e);
        }
    }

    public static String getJsonValue(ObjectMapper objectMapper, File file, String valueToFind) {
        String value = null;
        try {
            ObjectNode objectNode = (ObjectNode) objectMapper.readTree(file);
            JsonNode userNode = objectNode.get("ApplicationUser");

            value = userNode.get(valueToFind).asText();
        }
        catch (IOException e) {
            System.err.println("Error: " + e);
        }
        return value;
    }
}
