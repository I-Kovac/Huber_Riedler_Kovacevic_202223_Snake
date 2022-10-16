module com.example.huber_riedler_kovacevic_202223_snake {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.huber_riedler_kovacevic_202223_snake to javafx.fxml;
    exports com.example.huber_riedler_kovacevic_202223_snake;
    exports com.example.huber_riedler_kovacevic_202223_snake.controller;
    opens com.example.huber_riedler_kovacevic_202223_snake.controller to javafx.fxml;
}