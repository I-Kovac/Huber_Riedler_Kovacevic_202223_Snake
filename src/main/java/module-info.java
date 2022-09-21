module com.example.huber_riedler_kovacevic_202223_snake {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.huber_riedler_kovacevic_202223_snake to javafx.fxml;
    exports com.example.huber_riedler_kovacevic_202223_snake;
}