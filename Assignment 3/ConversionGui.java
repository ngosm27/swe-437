import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;

public class ConversionGui extends Application {
    final int conversionCount = 7;
    final TextField[] imperialTextFields = new TextField[conversionCount];
    final TextField[] metricTextFields = new TextField[conversionCount];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Measurement Conversion");

        VBox root = new VBox(10);

        // grid of text fields
        String[] imperialUnits = new String[] {
            "Fahrenheit (\u00B0" + "F)",
            "Inch (in)",
            "Feet (ft)",
            "Mile (mi)",
            "Gallon (gal)",
            "Ounce (oz)",
            "Pound (lb)"
        };
        String[] metricUnits = new String[] {
            "Celsius (\u00B0" + "C)",
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
        convertBtn.setOnAction(new EventHandler < ActionEvent > () {

            @Override
            public void handle(ActionEvent event) {
                convert();
            }
        });

        // clear button clears the fields
        Button clearBtn = new Button();
        clearBtn.setText("Clear Form");
        clearBtn.setOnAction(new EventHandler < ActionEvent > () {
            @Override
            public void handle(ActionEvent event) {
                clearFields();
            }
        });

        // radio buttons controlling the dec points displayed
        // all buttons share same radio group to ensure only one option can be chosen
        HBox radioGrp = new HBox(10);
        //radioGrp.setSpacing(30);
        radioGrp.getChildren().addAll(new Label("   Number of decimal places: "));

        // option to let users select the number of decimal places to show (0-4)
        final ToggleGroup group = new ToggleGroup();
        RadioButton[] decOption = new RadioButton[5];
        for (int i = 0; i < 5; i++) {
            decOption[i] = new RadioButton();

            // set default to be 0 decimal places
            if (i == 0) decOption[i].setSelected(true);

            decOption[i].setText("" + i);
            decOption[i].setToggleGroup(group);
            radioGrp.getChildren().addAll(new HBox(20), decOption[i]);
        }

        // bottom row containing both buttons
        HBox btnGrp = new HBox(10);
        btnGrp.getChildren().addAll(new HBox(10), convertBtn, clearBtn);

        root.getChildren().addAll(gridpane, new VBox(10), radioGrp, btnGrp);

        primaryStage.setScene(new Scene(root, 600, 255));
        primaryStage.show();
    }
    
    // clear text fields
    public void clearFields(){
        for (int i = 0; i < imperialTextFields.length; i++) {
            imperialTextFields[i].clear();
            metricTextFields[i].clear();
        }
    }

    public void convert() {
        // store all user input from imperial text fields
        String[] imperialAsStr = new String[conversionCount];
        for(int i = 0; i < conversionCount; i++){
            imperialAsStr[i] = imperialTextFields[i].getText();
            
            // check if field is valid
            // process all imperial -> metric conversion results
            if (imperialAsStr[i].length() > 0) {
                metricTextFields[i].setText(String.valueOf(convertX2Y(true, i, imperialAsStr[i], 0)));
            }
        }

        // store all user input from metric text fields
        String[] metricAsStr = new String[conversionCount];
        for(int i = 0; i < conversionCount; i++){
            metricAsStr[i] = metricTextFields[i].getText();
            
            // check if field is valid
            // process all metric -> imperial conversion results
            if (metricAsStr[i].length() > 0) {
                imperialTextFields[i].setText(String.valueOf(convertX2Y(false, i, metricAsStr[i], 0)));
            }
        }
    }
    
    private float convertX2Y(boolean isImperial2Metric, int index, String str, int numOfDecimals){
        float num1, num2 = 0; // temporary variables
        int n; // temporary variable
        
        // round to 2 digits past decimal
        num1 = (Float.valueOf(str).floatValue());
        n = Math.round(num1 * (float) 100.0);
        num1 = (float)(n / (float) 100.0);
        
        // perform the correlating conversion; indicated by the index
        if(isImperial2Metric){
            if(index == 0) num2 = (float)(((num1 - 32.0) * 5.0) / 9.0);     // farenheit to celsius
            else if(index == 1) num2 = (float)(num1 * 2.54);                // inch to centimeter
            else if(index == 2) num2 = (float)(num1 * 0.3048);              // feet to meter
            else if(index == 3) num2 = (float)(num1 * 1.609);               // mile to kilometer
            else if(index == 4) num2 = (float)(num1 * 3.785);               // gallon to liter
            else if(index == 5) num2 = (float)(num1 * 28.35);               // ounce to gram
            else if(index == 6) num2 = (float)(num1 * 0.4536);              // pound to kilogram
        }
        else{
            if(index == 0) num2 = (float)((num1 * 9.0 / 5.0) + 32.0);       // celsius to farenheit
            else if(index == 1) num2 = (float)(num1 * 0.3937);              // centimeter to inch
            else if(index == 2) num2 = (float)(num1 / 0.3048);              // meter to feet
            else if(index == 3) num2 = (float)(num1 * 0.6214);              // kilometer to mi
            else if(index == 4) num2 = (float)(num1 / 3.785);               // liter to gallon
            else if(index == 5) num2 = (float)(num1 / 28.35);               // gram to ounce
            else if(index == 6) num2 = (float)(num1 * 2.205);               // kilogram to pound
        }
        
        // back to 2 digits
        n = Math.round(num2 * (float) 100.0);
        num2 = (float)(n / (float) 100.0);
        
        return (num2);
    }
}