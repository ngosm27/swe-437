package com.example.assignment4;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import java.text.DecimalFormat;

public class ConversionGui extends Application {

    // define all units with their abbreviations
    final String[] imperialUnits = new String[]{
            "Fahrenheit (\u00B0" + "F)",
            "Inch (in)",
            "Feet (ft)",
            "Mile (mi)",
            "Gallon (gal)",
            "Ounce (oz)",
            "Pound (lb)",
            "Hour (hr)",
            "Miles per Hour (mph)",
            "Kelvin (\u00B0" + "K)"
    };
    final String[] metricUnits = new String[]{
            "Celsius (\u00B0" + "C)",
            "Centimeter (cm)",
            "Meter (m)",
            "Kilometer (km)",
            "Liter (L)",
            "Gram (g)",
            "Kilogram (kg)",
            "Second (s)",
            "Kilometers per Hour (kph)",
            "Celsius (\u00B0" + "C)"
    };

    final int conversionCount = Math.min(imperialUnits.length, metricUnits.length); // number of conversion options
    final TextField[] imperialTextFields = new TextField[]{
            new TextField("fahrenheit"),
            new TextField("inch"),
            new TextField("feet"),
            new TextField("mile"),
            new TextField("gallon"),
            new TextField("ounce"),
            new TextField("pound"),
            new TextField("hour"),
            new TextField("mph"),
            new TextField("kelvin")
    };                                                                              // text fields of first column of units
    final TextField[] metricTextFields = new TextField[]{
            new TextField("celsuis"),
            new TextField("centimeter"),
            new TextField("meter"),
            new TextField("kilometer"),
            new TextField("liter"),
            new TextField("gram"),
            new TextField("kilogram"),
            new TextField("second"),
            new TextField("kph"),
            new TextField("celsius")
    };                                                                              // text fields of second column of units
    final ToggleGroup group = new ToggleGroup();                                    // group of radio buttons

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Measurement Conversion");

        VBox root = new VBox(10);

        // grid of text fields, use the units w/ abbreviations as label
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
                clearFields();
            }
        });

        // radio buttons controlling the dec points displayed
        // all buttons share same radio group to ensure only one option can be chosen
        HBox radioGrp = new HBox(10);
        //radioGrp.setSpacing(30);
        radioGrp.getChildren().addAll(new Label("   Number of decimal places: "));

        // option to let users select the number of decimal places to show (0-4)
        RadioButton[] decOption = new RadioButton[5];
        for (int i = 0; i < 5; i++) {
            decOption[i] = new RadioButton();

            // set default selected radio button to be 2 decimal places
            if (i == 2) decOption[i].setSelected(true);

            decOption[i].setText("" + i);
            decOption[i].setToggleGroup(group);
            radioGrp.getChildren().addAll(new HBox(20), decOption[i]);
        }

        // bottom row containing both buttons
        HBox btnGrp = new HBox(10);
        btnGrp.getChildren().addAll(new HBox(10), convertBtn, clearBtn);

        root.getChildren().addAll(gridpane, new VBox(10), radioGrp, btnGrp);

        primaryStage.setScene(new Scene(root, 650, 335));
        primaryStage.show();
    }

    // clear text fields
    public void clearFields() {
        for (int i = 0; i < imperialTextFields.length; i++) {
            imperialTextFields[i].clear();
            metricTextFields[i].clear();
        }
    }

    // initiate conversion
    // convert text fields into strings and call the convertX2Y method
    public void convert() {
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        String selectedValue = selectedRadioButton.getText();
        int numDecPoints = Integer.parseInt(selectedValue);

        // store all user input from imperial and metric text fields
        String[] imperialAsStr = new String[conversionCount];
        String[] metricAsStr = new String[conversionCount];

        for (int i = 0; i < conversionCount; i++) {
            // extract strings
            imperialAsStr[i] = imperialTextFields[i].getText();
            metricAsStr[i] = metricTextFields[i].getText();

            // process all imperial -> metric conversion results
            if (imperialAsStr[i].length() > 0) {
                float metricConvValue = convertX2Y(true, i, imperialAsStr[i]);
                //System.out.println("index " + i + ": " + metricConvValue);
                metricTextFields[i].setText(formatToDecimalPlaces(numDecPoints, metricConvValue));
            }

            // process all metric -> imperial conversion results
            if (metricAsStr[i].length() > 0) {
                float imperialConvValue = convertX2Y(false, i, metricAsStr[i]);
                System.out.println("index " + i + ": " + imperialConvValue);
                imperialTextFields[i].setText(formatToDecimalPlaces(numDecPoints, imperialConvValue));
            }
        }
    }

    // convert unit and format end result
    protected float convertX2Y(boolean isImperial2Metric, int index, String str) {
        float num1, num2 = 0;       // temporary variables
        int n;                      // temporary variable

        num1 = (Float.valueOf(str).floatValue());

        // perform the correlating conversion; indicated by the index
        if (isImperial2Metric) {
            if (index == 0) num2 = (float) (((num1 - 32.0) * 5.0) / 9.0);           // fahrenheit to celsius
            else if (index == 1) num2 = (float) (num1 * 2.54);                      // inch to centimeter
            else if (index == 2) num2 = (float) (num1 * 0.3048);                    // feet to meter
            else if ((index == 3) || (index == 8)) num2 = (float) (num1 * 1.609);   // mile (per hour) to kilometer (per hour)
            else if (index == 4) num2 = (float) (num1 * 3.785);                     // gallon to liter
            else if (index == 5) num2 = (float) (num1 * 28.35);                     // ounce to gram
            else if (index == 6) num2 = (float) (num1 * 0.4536);                    // pound to kilogram
            else if (index == 7) num2 = (float) (num1 * 3600);                      // hour to second
            else if (index == 9) num2 = (float) (num1 - 273.15);                    // kelvin to celsius
        } else {
            if (index == 0) num2 = (float) ((num1 * 9.0 / 5.0) + 32.0);             // celsius to fahrenheit
            else if (index == 1) num2 = (float) (num1 * 0.3937);                    // centimeter to inch
            else if (index == 2) num2 = (float) (num1 / 0.3048);                    // meter to feet
            else if ((index == 3) || (index == 8)) num2 = (float) (num1 * 0.6214);  // kilometer (per hour) to mi (per hour)
            else if (index == 4) num2 = (float) (num1 / 3.785);                     // liter to gallon
            else if (index == 5) num2 = (float) (num1 / 28.35);                     // gram to ounce
            else if (index == 6) num2 = (float) (num1 * 2.205);                     // kilogram to pound
            else if (index == 7) num2 = (float) (num1 / 3600);                      // second to hour
            else if (index == 9) num2 = (float) (num1 + 273.15);                    // celsius to kelvin
        }

        return (num2);
    }

    // format conversion result to specified number of decimal places
    protected String formatToDecimalPlaces(int numDecPoints, float value){
        // Format specifier
        String[] format = {"0", "0.0", "0.00", "0.000", "0.0000"};
        DecimalFormat df = new DecimalFormat(format[numDecPoints]);

        return df.format(value);
    }
}