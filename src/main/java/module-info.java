module org.example.fxmldemo {
    requires javafx.controls;
    requires javafx.fxml;
//    requires com.fasterxml.jackson.databind;
    requires jackson.databind;


    opens org.example.fxmldemo to javafx.fxml;
    exports org.example.fxmldemo;
    requires java.sql;
//    requires com.google.gson;
}