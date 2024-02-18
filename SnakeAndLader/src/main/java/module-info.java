module com.example.snakeandlader {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snakeandlader to javafx.fxml;
    exports com.example.snakeandlader;
}