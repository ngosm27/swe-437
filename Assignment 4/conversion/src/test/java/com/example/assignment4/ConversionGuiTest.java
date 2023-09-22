package com.example.assignment4;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConversionGuiTest {
    private ConversionGui gui;

    // run an instance of the app
    @BeforeAll
    public static void setUpClass() throws InterruptedException {
        try {
            Platform.runLater(() -> {});
        } catch (Exception ex) {
            Platform.startup(() -> {});
        }
    }
    @BeforeEach
    void setUp() {
        gui = new ConversionGui();
    }

    // tests that the fields are being properly cleared
    @Test
    void clearFieldsTest() {
        gui.clearFields();

        // iterate through all text fields to ensure they have been cleared properly
        for(int i = 0; i < gui.conversionCount; i++){
            if(gui.imperialTextFields[i].getText().length() > 0
                    || gui.metricTextFields[i].getText().length() > 0){
                fail("Text fields should be empty after clearing");
            }
        }
    }

    // tests fahrenheit to celsius conversion
    @Test
    void convertF2CTest() {
        assertTrue((float)37.77778 == gui.convertX2Y(true, 0, "100.0"), "f to c conversion failed");
    }

    // tests celsius to fahrenheit conversion
    @Test
    void convertC2FTest() {
        assertTrue((float)212.0 == gui.convertX2Y(false, 0, "100.0"), "c to f conversion failed");
    }

    // tests inch to centimeter conversion
    @Test
    void convertIn2CmTest() {
        assertTrue((float)254.0 == gui.convertX2Y(true, 1, "100.0"), "in to cm conversion failed");
    }

    // tests centimeter to inch conversion
    @Test
    void convertCm2InTest() {
        assertTrue((float)39.37 == gui.convertX2Y(false, 1, "100.0"), "cm to in conversion failed");
    }

    // tests feet to meter conversion
    @Test
    void convertF2MTest() {
        assertTrue((float)30.48 == gui.convertX2Y(true, 2, "100.0"), "ft to m conversion failed");
    }

    // tests meter to feet conversion
    @Test
    void convertM2FTest() {
        assertTrue((float)328.08398 == gui.convertX2Y(false, 2, "100.0"), "m to ft conversion failed");
    }

    // tests mile to kilometer conversion
    @Test
    void convertM2KTest() {
        assertTrue((float)160.9 == gui.convertX2Y(true, 3, "100.0"), "mi to km conversion failed");
    }

    // tests kilometer to mile conversion
    @Test
    void convertK2MTest() {
        assertTrue((float)62.14 == gui.convertX2Y(false, 3, "100.0"), "km to mi conversion failed");
    }

    // tests gallon to liter conversion
    @Test
    void convertG2LTest() {
        assertTrue((float)378.5 == gui.convertX2Y(true, 4, "100.0"), "gal to L conversion failed");
    }

    // tests liter to gallon conversion
    @Test
    void convertL2GTest() {
        assertTrue((float)26.42008 == gui.convertX2Y(false, 4, "100.0"), "L to gal conversion failed");
    }

    // tests ounce to gram conversion
    @Test
    void convertOz2GTest() {
        assertTrue((float)2835.0 == gui.convertX2Y(true, 5, "100.0"), "g to oz conversion failed");
    }

    // tests gram to ounce conversion
    @Test
    void convertG2OzTest() {
        assertTrue((float)3.5273368 == gui.convertX2Y(false, 5, "100.0"), "oz to g conversion failed");
    }

    // tests pound to kilogram conversion
    @Test
    void convertLb2KTest() {
        assertTrue((float)45.36 == gui.convertX2Y(true, 6, "100.0"), "lb to kg conversion failed");
    }

    // tests kilogram to pound conversion
    @Test
    void convertK2LbTest() {
        assertTrue((float)220.5 == gui.convertX2Y(false, 6, "100.0"), "kg to lb conversion failed");
    }

    // tests hour to second conversion
    @Test
    void convertH2STest() {
        assertTrue((float)360000.0 == gui.convertX2Y(true, 7, "100.0"), "hr to sec conversion failed");
    }

    // tests second to hour conversion
    @Test
    void convertS2HTest() {
        assertTrue((float)0.027777778 == gui.convertX2Y(false, 7, "100.0"), "sec to hour conversion failed");
    }

    // test that result displays 0 decimal places
    @Test
    void ZeroDecimalPlaceTest() {
        assertEquals("378", gui.formatToDecimalPlaces(0, (float)378.5), "Result should display 0 decimal place");
    }

    // test that result displays 1 decimal places
    @Test
    void OneDecimalPlaceTest() {
        assertEquals("378.5", gui.formatToDecimalPlaces(1, (float)378.5), "Result should display 1 decimal place");
    }

    // test that result displays 2 decimal places
    @Test
    void TwoDecimalPlaceTest() {
        assertEquals("378.50", gui.formatToDecimalPlaces(2, (float)378.5), "Result should display 2 decimal place");
    }

    // test that result displays 3 decimal places
    @Test
    void ThreeDecimalPlaceTest() {
        assertEquals("378.500", gui.formatToDecimalPlaces(3, (float)378.5), "Result should display 3 decimal place");
    }

    // test that result displays 4 decimal places
    @Test
    void FourDecimalPlaceTest() {
        assertEquals("378.5000", gui.formatToDecimalPlaces(4, (float)378.5), "Result should display 4 decimal place");
    }
}