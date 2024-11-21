package org.example.fxmldemo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public interface UserJsonHandler {
    void createUserDataFile(ObjectMapper objectMapper, UserJsonHandler userJsonHandler, File file);
}
