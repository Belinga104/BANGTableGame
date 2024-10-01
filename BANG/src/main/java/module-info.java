module com.example.bang {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.example.bang to javafx.fxml;
    exports com.example.bang.Scenes to javafx.graphics;
}