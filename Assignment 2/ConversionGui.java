import java.util.Properties;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;

public class ConversionGui extends Application {
    final TextField[] imperialTextFields = new TextField[7];
    final TextField[] metricTextFields = new TextField[7];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Measurement Conversion");

        VBox root = new VBox(10);

        // grid of text fields
        String[] imperialUnits = new String[] {
                "Fahrenheit (F�)",
                "Inch (in)",
                "Feet (ft)",
                "Mile (mi)",
                "Gallon (gal)",
                "Ounce (oz)",
                "Pound (lb)"
        };
        String[] metricUnits = new String[] {
                "Celsius (C�)",
                "Centimeter (cm)",
                "Meter (m)",
                "Kilometer (km)",
                "Liter (L)",
                "Gram (g)",
                "Kilogram (kg)"
        };

        GridPane gridpane = new GridPane();

        for (int i = 0; i < metricUnits.length; i++) {
            TextField tf_imperial = new TextField();
            TextField tf_metric = new TextField();

            gridpane.add(new Label("   " + imperialUnits[i] + ": "), 1, i);
            gridpane.add(tf_imperial, 2, i);
            gridpane.add(new Label("     <----->     "), 3, i);
            gridpane.add(new Label(metricUnits[i] + ": "), 4, i);
            gridpane.add(tf_metric, 5, i);

            imperialTextFields[i] = tf_imperial;
            metricTextFields[i] = tf_metric;
        }

        // convert button performs the conversion
        Button convertBtn = new Button();
        convertBtn.setText("Convert");
        convertBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // convert();
            }
        });

        // clear button clears the fields
        Button clearBtn = new Button();
        clearBtn.setText("Clear Form");
        clearBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (int i = 0; i < imperialTextFields.length; i++) {
                    imperialTextFields[i].clear();
                    metricTextFields[i].clear();
                }
            }
        });

        // bottom row containing both buttons
        HBox btnGrp = new HBox(10);
        btnGrp.getChildren().addAll(convertBtn, clearBtn);

        root.getChildren().addAll(gridpane, new VBox(10), btnGrp);

        primaryStage.setScene(new Scene(root, 600, 225));
        primaryStage.show();
    }

}