module com.example.lab6_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;



    opens com.example.lab6_javafx to javafx.fxml;
    exports com.example.lab6_javafx;
}