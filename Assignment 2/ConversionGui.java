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
                convert();
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

    public void convert() {

        // Get the values from the textboxes in the form
        // Most are probably empty
        String FAsStr = imperialTextFields[0].getText();
        String CAsStr = metricTextFields[0].getText();
        String inAsStr = imperialTextFields[1].getText();
        String cmAsStr = metricTextFields[1].getText();
        String ftAsStr = imperialTextFields[2].getText();
        String mAsStr = metricTextFields[2].getText();
        String miAsStr = imperialTextFields[3].getText();
        String kmAsStr = metricTextFields[3].getText();
        String galAsStr = imperialTextFields[4].getText();
        String LAsStr = metricTextFields[4].getText();
        String ozAsStr = imperialTextFields[5].getText();
        String gAsStr = metricTextFields[5].getText();
        String lbAsStr = imperialTextFields[6].getText();
        String kgAsStr = metricTextFields[6].getText();

        int n; // temporary number
        float num1, num2; // temporary numbers

        // Save the converted values into a container to add to form

        // temperature
        if (FAsStr != null && FAsStr.length() > 0) { // Convert farenheit to celsius
            metricTextFields[0].setText(String.valueOf(convertF2C(FAsStr)));
        }

        if (CAsStr != null && CAsStr.length() > 0) { // Convert celsius to farenheit
            imperialTextFields[0].setText(String.valueOf(convertC2F(CAsStr)));
        }

        // small distance
        if (inAsStr != null && inAsStr.length() > 0) { // Convert inches to centimeters
            metricTextFields[1].setText(String.valueOf(convertIn2Cm(inAsStr)));
        }

        if (cmAsStr != null && cmAsStr.length() > 0) { // Convert centimeters to inches
            imperialTextFields[1].setText(String.valueOf(convertCm2In(cmAsStr)));
        }

        // medium distance
        if (ftAsStr != null && ftAsStr.length() > 0) { // Convert feet to meters
            metricTextFields[2].setText(String.valueOf(convertF2M(ftAsStr)));
        }

        if (mAsStr != null && mAsStr.length() > 0) { // Convert meters to feet
            imperialTextFields[2].setText(String.valueOf(convertM2F(mAsStr)));
        }

        // large distance
        if (miAsStr != null && miAsStr.length() > 0) { // Convert miles to kilometers
            metricTextFields[3].setText(String.valueOf(convertM2K(miAsStr)));
        }

        if (kmAsStr != null && kmAsStr.length() > 0) { // Convert kilometers to miles
            imperialTextFields[3].setText(String.valueOf(convertK2M(kmAsStr)));
        }

        // volume
        if (galAsStr != null && galAsStr.length() > 0) { // Convert gallons to liters
            metricTextFields[4].setText(String.valueOf(convertG2L(galAsStr)));
        }

        if (LAsStr != null && LAsStr.length() > 0) { // Convert liters to gallons
            imperialTextFields[4].setText(String.valueOf(convertL2G(LAsStr)));
        }

        // small weight
        if (ozAsStr != null && ozAsStr.length() > 0) { // Convert ounces to grams
            metricTextFields[5].setText(String.valueOf(convertOz2G(ozAsStr)));
        }

        if (gAsStr != null && gAsStr.length() > 0) { // Convert grams to ounces
            imperialTextFields[5].setText(String.valueOf(convertG2Oz(gAsStr)));
        }

        // medium weight
        if (lbAsStr != null && lbAsStr.length() > 0) { // Convert pounds to kilograms
            metricTextFields[6].setText(String.valueOf(convertLb2K(lbAsStr)));
        }
        if (kgAsStr != null && kgAsStr.length() > 0) { // Convert kilograms to pounds
            imperialTextFields[6].setText(String.valueOf(convertK2Lb(kgAsStr)));
        }
    }

    private float convertF2C(String FAsStr) { // Convert farenheit to celsius
        float num1, num2; // temporary variables
        int n; // temporary variable
        // Round to 2 digits past decimal
        num1 = (Float.valueOf(FAsStr).floatValue());
        n = Math.round(num1 * (float) 100.0);
        num1 = (float) (n / (float) 100.0);
        // Convert
        num2 = (float) (((num1 - 32.0) * 5.0) / 9.0);
        // Back to 2 digits
        n = Math.round(num2 * (float) 100.0);
        num2 = (float) (n / (float) 100.0);
        return (num2);
    }

    private float convertC2F(String CAsStr) { // Convert celsius to farenheit
        float num1, num2; // temporary variables
        int n; // temporary variable
        // Round to 2 digits past decimal
        num1 = (Float.valueOf(CAsStr).floatValue());
        n = Math.round(num1 * (float) 100.0);
        num1 = (float) (n / (float) 100.0);
        // Convert
        num2 = (float) ((num1 * 9.0 / 5.0) + 32.0);
        // Back to 2 digits
        n = Math.round(num2 * (float) 100.0);
        num2 = (float) (n / (float) 100.0);
        return (num2);
    }

    // small distance
    private float convertIn2Cm(String inAsStr) { // Convert inches to centimeters
        float num1, num2; // temporary variables
        int n; // temporary variable
        // Round to 2 digits past decimal
        num1 = (Float.valueOf(inAsStr).floatValue());
        n = Math.round(num1 * (float) 100.0);
        num1 = (float) (n / (float) 100.0);
        // Convert
        num2 = (float) (num1 * 2.54);
        // Back to 2 digits
        n = Math.round(num2 * (float) 100.0);
        num2 = (float) (n / (float) 100.0);
        return (num2);
    }

    private float convertCm2In(String cmAsStr) { // Convert centimeters to inches
        float num1, num2; // temporary variables
        int n; // temporary variable
        // Round to 2 digits past decimal
        num1 = (Float.valueOf(cmAsStr).floatValue());
        n = Math.round(num1 * (float) 100.0);
        num1 = (float) (n / (float) 100.0);
        // Convert
        num2 = (float) (num1 * 0.3937);
        // Back to 2 digits
        n = Math.round(num2 * (float) 100.0);
        num2 = (float) (n / (float) 100.0);
        return (num2);
    }

    // medium distance
    private float convertF2M(String ftAsStr) { // Convert feet to meters
        float num1, num2; // temporary variables
        int n; // temporary variable
        // Round to 2 digits past decimal
        num1 = (Float.valueOf(ftAsStr).floatValue());
        n = Math.round(num1 * (float) 100.0);
        num1 = (float) (n / (float) 100.0);
        // Convert
        num2 = (float) (num1 * 0.3048);
        // Back to 2 digits
        n = Math.round(num2 * (float) 100.0);
        num2 = (float) (n / (float) 100.0);
        return (num2);
    }

    private float convertM2F(String mAsStr) { // Convert meters to feet
        float num1, num2; // temporary variables
        int n; // temporary variable
        // Round to 2 digits past decimal
        num1 = (Float.valueOf(mAsStr).floatValue());
        n = Math.round(num1 * (float) 100.0);
        num1 = (float) (n / (float) 100.0);
        // Convert
        num2 = (float) (num1 / 0.3048);
        // Back to 2 digits
        n = Math.round(num2 * (float) 100.0);
        num2 = (float) (n / (float) 100.0);
        return (num2);
    }

    // large distance
    private float convertM2K(String miAsStr) { // Convert miles to kilometers
        float num1, num2; // temporary variables
        int n; // temporary variable
        // Round to 2 digits past decimal
        num1 = (Float.valueOf(miAsStr).floatValue());
        n = Math.round(num1 * (float) 100.0);
        num1 = (float) (n / (float) 100.0);
        // Convert
        num2 = (float) (num1 * 1.609);
        // Back to 2 digits
        n = Math.round(num2 * (float) 100.0);
        num2 = (float) (n / (float) 100.0);
        return (num2);
    }

    private float convertK2M(String kmAsStr) { // Convert kilometers to miles
        float num1, num2; // temporary variables
        int n; // temporary variable
        // Round to 2 digits past decimal
        num1 = (Float.valueOf(kmAsStr).floatValue());
        n = Math.round(num1 * (float) 100.0);
        num1 = (float) (n / (float) 100.0);
        // Convert
        num2 = (float) (num1 * 0.6214);
        // Back to 2 digits
        n = Math.round(num2 * (float) 100.0);
        num2 = (float) (n / (float) 100.0);
        return (num2);
    }

    // volume
    private float convertG2L(String galAsStr) { // Convert gallons to liters
        float num1, num2; // temporary variables
        int n; // temporary variable
        // Round to 2 digits past decimal
        num1 = (Float.valueOf(galAsStr).floatValue());
        n = Math.round(num1 * (float) 100.0);
        num1 = (float) (n / (float) 100.0);
        // Convert
        num2 = (float) (num1 * 3.785);
        // Back to 2 digits
        n = Math.round(num2 * (float) 100.0);
        num2 = (float) (n / (float) 100.0);
        return (num2);
    }

    private float convertL2G(String LAsStr) { // Convert liters to gallons
        float num1, num2; // temporary variables
        int n; // temporary variable
        // Round to 2 digits past decimal
        num1 = (Float.valueOf(LAsStr).floatValue());
        n = Math.round(num1 * (float) 100.0);
        num1 = (float) (n / (float) 100.0);
        // Convert
        num2 = (float) (num1 / 3.785);
        // Back to 2 digits
        n = Math.round(num2 * (float) 100.0);
        num2 = (float) (n / (float) 100.0);
        return (num2);
    }

    // small weight
    private float convertOz2G(String ozAsStr) { // Convert ounces to grams
        float num1, num2; // temporary variables
        int n; // temporary variable
        // Round to 2 digits past decimal
        num1 = (Float.valueOf(ozAsStr).floatValue());
        n = Math.round(num1 * (float) 100.0);
        num1 = (float) (n / (float) 100.0);
        // Convert
        num2 = (float) (num1 * 28.35);
        // Back to 2 digits
        n = Math.round(num2 * (float) 100.0);
        num2 = (float) (n / (float) 100.0);
        return (num2);
    }

    private float convertG2Oz(String gAsStr) { // Convert grams to ounces
        float num1, num2; // temporary variables
        int n; // temporary variable
        // Round to 2 digits past decimal
        num1 = (Float.valueOf(gAsStr).floatValue());
        n = Math.round(num1 * (float) 100.0);
        num1 = (float) (n / (float) 100.0);
        // Convert
        num2 = (float) (num1 / 28.35);
        // Back to 2 digits
        n = Math.round(num2 * (float) 100.0);
        num2 = (float) (n / (float) 100.0);
        return (num2);
    }

    // medium weight
    private float convertLb2K(String lbAsStr) { // Convert pounds to kilograms
        float num1, num2; // temporary variables
        int n; // temporary variable
        // Round to 2 digits past decimal
        num1 = (Float.valueOf(lbAsStr).floatValue());
        n = Math.round(num1 * (float) 100.0);
        num1 = (float) (n / (float) 100.0);
        // Convert
        num2 = (float) (num1 * 0.4536);
        // Back to 2 digits
        n = Math.round(num2 * (float) 100.0);
        num2 = (float) (n / (float) 100.0);
        return (num2);
    }

    private float convertK2Lb(String kgAsStr) { // Convert kilograms to pounds
        float num1, num2; // temporary variables
        int n; // temporary variable
        // Round to 2 digits past decimal
        num1 = (Float.valueOf(kgAsStr).floatValue());
        n = Math.round(num1 * (float) 100.0);
        num1 = (float) (n / (float) 100.0);
        // Convert
        num2 = (float) (num1 * 2.205);
        // Back to 2 digits
        n = Math.round(num2 * (float) 100.0);
        num2 = (float) (n / (float) 100.0);
        return (num2);
    }
}