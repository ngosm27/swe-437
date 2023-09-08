import java.net.HttpURLConnection;
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
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.io.*;
import java.util.*;

public class ConversionGui extends Application {
    private final int conversionCount = 7;
    private final TextField[] imperialTextFields = new TextField[conversionCount];
    private final TextField[] metricTextFields = new TextField[conversionCount];
    private final String SERVLET_URL = "https://cs.gmu.edu:8443/offutt/servlet/conversion";

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

        String[] imperialAbbrev = new String[] {
            "F",
            "in",
            "ft",
            "mi",
            "gal",
            "oz",
            "lb"
        };

        String[] metricAbbrev = new String[] {
            "C",
            "cm",
            "m",
            "km",
            "L",
            "g",
            "kg"
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
                try {
                    // store user input data
                    StringBuilder postData = new StringBuilder();
                    double value1 = 0;
                    double value2 = 0;
                    for (int i = 0; i < conversionCount; i++) {
                        if (imperialTextFields[i].getText().length() > 0) {
                            value1 = Double.parseDouble(imperialTextFields[i].getText());
                            postData.append(imperialAbbrev[i]).append("=").append(value1).append("&");
                        }
                        if (metricTextFields[i].getText().length() > 0) {
                            value2 = Double.parseDouble(metricTextFields[i].getText());
                            postData.append(metricAbbrev[i]).append("=").append(value2).append("&");
                        }
                    }

                    byte[] postDataBytes = postData.toString().getBytes(StandardCharsets.UTF_8);

                    // connect to servlet
                    URL url = new URL(SERVLET_URL);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);

                    // send user input data
                    try (OutputStream os = connection.getOutputStream()) {
                        os.write(postDataBytes);
                    }

                    // read response
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {

                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }

                        // stores the html content of the response as a string to be parsed
                        String responseBody = response.toString();

                        // find the updated values and to refresh the UI
                        int indexStart = responseBody.indexOf("value=\"");
                        int indexEnd = responseBody.indexOf(">", indexStart + 1);
                        int count = 0;

                        int curr = 0;

                        while (indexStart != -1 && count < conversionCount * 2) {
                            if (count % 2 == 0) {
                                imperialTextFields[curr].setText(responseBody.substring(indexStart + 7, indexEnd - 1));
                            } else {
                                metricTextFields[curr].setText(responseBody.substring(indexStart + 7, indexEnd - 1));
                                curr++;
                            }
                            indexStart = responseBody.indexOf("value=\"", indexStart + 1);
                            indexEnd = responseBody.indexOf(">", indexStart + 1);
                            count++;
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // clear button clears the fields
        Button clearBtn = new Button();
        clearBtn.setText("Clear Form");
        clearBtn.setOnAction(new EventHandler < ActionEvent > () {
            @Override
            public void handle(ActionEvent event) {
                for (int i = 0; i < conversionCount; i++) {
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