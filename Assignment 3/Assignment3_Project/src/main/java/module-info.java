module com.example.assignment3_project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.assignment3_project to javafx.fxml;
    exports com.example.assignment3_project;
}