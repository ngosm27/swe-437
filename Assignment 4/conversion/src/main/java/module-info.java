module com.example.assignment4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.assignment4 to javafx.fxml;
    exports com.example.assignment4;
}