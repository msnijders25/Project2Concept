module com.example.project2concept {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.project2concept to javafx.fxml;
    exports com.example.project2concept;
}