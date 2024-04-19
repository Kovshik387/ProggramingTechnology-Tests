module com.example.proggramingtechnologytests {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires mail;


    opens com.example.proggramingtechnologytests to javafx.fxml;
    exports com.example.proggramingtechnologytests;
}