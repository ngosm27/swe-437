package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConversionTests {

    // selenium chrome web driver
    private WebDriver driver;

    static int count = 9;

    // input values
    private  boolean buttonClicked;
    private String buttonVal;
    private String degFVal;
    private String degCVal;
    private String inVal;
    private String cmVal;
    private String ftVal;
    private String mVal;
    private String miVal;
    private String kmVal;
    private String galVal;
    private String LVal;
    private String ozVal;
    private String gVal;
    private String lbVal;
    private String kgVal;

    // **************************************************************************
    // input measurement values and expected results defined here for readability
    // **************************************************************************
    final String [][] measurements = {
            {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},

            {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
            {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},

            {"-1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
            {"1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
            {"", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},

            {"0", "-1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
            {"0", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
            {"0", "", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},

            {"0", "0", "-1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
            {"0", "0", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
            {"0", "0", "", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},

            {"0", "0", "0", "-1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
            {"0", "0", "0", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
            {"0", "0", "0", "", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},

            {"0", "0", "0", "0", "-1", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
            {"0", "0", "0", "0", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
            {"0", "0", "0", "0", "", "0", "0", "0", "0", "0", "0", "0", "0", "0"},

            {"0", "0", "0", "0", "0", "-1", "0", "0", "0", "0", "0", "0", "0", "0"},
            {"0", "0", "0", "0", "0", "1", "0", "0", "0", "0", "0", "0", "0", "0"},
            {"0", "0", "0", "0", "0", "", "0", "0", "0", "0", "0", "0", "0", "0"},

            {"0", "0", "0", "0", "0", "0", "-1", "0", "0", "0", "0", "0", "0", "0"},
            {"0", "0", "0", "0", "0", "0", "1", "0", "0", "0", "0", "0", "0", "0"},
            {"0", "0", "0", "0", "0", "0", "", "0", "0", "0", "0", "0", "0", "0"},

            {"0", "0", "0", "0", "0", "0", "0", "-1", "0", "0", "0", "0", "0", "0"},
            {"0", "0", "0", "0", "0", "0", "0", "1", "0", "0", "0", "0", "0", "0"},
            {"0", "0", "0", "0", "0", "0", "0", "", "0", "0", "0", "0", "0", "0"},

            {"0", "0", "0", "0", "0", "0", "0", "0", "-1", "0", "0", "0", "0", "0"},
            {"0", "0", "0", "0", "0", "0", "0", "0", "1", "0", "0", "0", "0", "0"},
            {"0", "0", "0", "0", "0", "0", "0", "0", "", "0", "0", "0", "0", "0"},

            {"0", "0", "0", "0", "0", "0", "0", "0", "0", "-1", "0", "0", "0", "0"},
            {"0", "0", "0", "0", "0", "0", "0", "0", "0", "1", "0", "0", "0", "0"},
            {"0", "0", "0", "0", "0", "0", "0", "0", "0", "", "0", "0", "0", "0"},

            {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "-1", "0", "0", "0"},
            {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "1", "0", "0", "0"},
            {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "", "0", "0", "0"},

            {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "-1", "0", "0"},
            {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "1", "0", "0"},
            {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "", "0", "0"},

            {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "-1", "0"},
            {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "1", "0"},
            {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "", "0"},

            {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "-1"},
            {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "1"},
            {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", ""},
    };

    // TO-DO: set expected values
    final String [][] expected = {
            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"},

            {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
            {"", "", "", "", "", "", "", "", "", "", "", "", "", "",},

            {"32.0", "-18.33", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"},
            {"32.0", "-17.22", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"},
            {"32.0", "", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"},

            {"30.2", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"},
            {"33.8", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"},
            {"", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"},

            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"},
            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"},
            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"},

            {"32.0", "-17.78", "", "-0.39", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"},
            {"32.0", "-17.78", "", "0.39", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"},
            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"},

            {"32.0", "-17.78", "", "0.0", "0.0", "-0.3", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"},
            {"32.0", "-17.78", "", "0.0", "0.0", "0.3", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"},
            {"32.0", "-17.78", "", "0.0", "0.0", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"},

            {"32.0", "-17.78", "", "0.0", "-1.65", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"},
            {"32.0", "-17.78", "", "0.0", "1.65", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"},
            {"32.0", "-17.78", "", "0.0", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"},

            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "-1.61", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"},
            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "1.61", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"},
            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"},

            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "-0.62", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"},
            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.62", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"},
            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"},

            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "-3.78", "0.0", "0.0", "0.0", "0.0"},
            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "3.79", "0.0", "0.0", "0.0", "0.0"},
            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "", "0.0", "0.0", "0.0", "0.0"},

            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "0.0", "-0.26", "0.0", "0.0", "0.0", "0.0", "0.0"},
            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.26", "0.0", "0.0", "0.0", "0.0", "0.0"},
            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "0.0", "", "0.0", "0.0", "0.0", "0.0", "0.0"},

            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "-28.35", "0.0", "0.0"},
            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "28.35", "0.0", "0.0"},
            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "null", "0.0", "0.0"},

            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "-0.04", "0.0", "0.0", "0.0"},
            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.04", "0.0", "0.0", "0.0"},
            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "", "0.0", "0.0", "0.0"},

            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "-0.45"},
            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.45"},
            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "", "0.0", ""},

            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "-2.2", "0.0"},
            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "2.21", "0.0"},
            {"32.0", "-17.78", "", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "", "0.0"},
    };


    @BeforeEach
    void setUp() {
        count++;
        driver = new ChromeDriver();
        driver.get("https://cs.gmu.edu:8443/offutt/servlet/conversion");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    // **************************************************************************
    // test model coverage: base choice coverage
    // total number of tests: 1 + (1 + 1 + 3(14)) = 45
    //
    // test0: base
    // test1: set first arg
    // test2: set second arg
    // test3-5: set third arg
    // test6-8: set fourth arg
    // test9-11: set fifth arg
    // test12-14: set sixth arg
    // test15-17: set seventh arg
    // test18-20: set eighth arg
    // test21-23: set ninth arg
    // test24-26: set tenth arg
    // test27-29: set eleventh arg
    // test30-32: set twelfth arg
    // test33-35: set thirteenth arg
    // test36-38: set fourteenth arg
    // test39-41: set fifteenth arg
    // test42-44: set sixteenth arg
    // **************************************************************************

    @Test
    void test0() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(0);
    }

    @Test
    void test1() {
        buttonClicked = false;
        buttonVal = "Convert";
        performTest(1);
    }

    @Test
    void test2() {
        buttonClicked = true;
        buttonVal = "Clear form";
        performTest(2);
    }

    @Test
    void test3() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(3);
    }

    @Test
    void test4() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(4);
    }

    @Test
    void test5() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(5);
    }

    @Test
    void test6() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(6);
    }

    @Test
    void test7() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(7);
    }

    @Test
    void test8() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(8);
    }

    @Test
    void test9() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(9);
    }

    @Test
    void test10() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(10);
    }

    @Test
    void test11() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(11);
    }

    @Test
    void test12() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(12);
    }

    @Test
    void test13() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(13);
    }

    @Test
    void test14() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(14);
    }

    @Test
    void test15() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(15);
    }

    @Test
    void test16() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(16);
    }

    @Test
    void test17() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(17);
    }

    @Test
    void test18() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(18);
    }

    @Test
    void test19() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(19);
    }

    @Test
    void test20() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(20);
    }

    @Test
    void test21() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(21);
    }

    @Test
    void test22() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(22);
    }

    @Test
    void test23() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(23);
    }

    @Test
    void test24() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(24);
    }

    @Test
    void test25() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(25);
    }

    @Test
    void test26() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(26);
    }

    @Test
    void test27() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(27);
    }

    @Test
    void test28() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(28);
    }

    @Test
    void test29() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(29);
    }

    @Test
    void test30() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(30);
    }

    @Test
    void test31() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(31);
    }

    @Test
    void test32() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(32);
    }

    @Test
    void test33() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(33);
    }

    @Test
    void test34() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(34);
    }

    @Test
    void test35() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(35);
    }

    @Test
    void test36() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(36);
    }

    @Test
    void test37() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(37);
    }

    @Test
    void test38() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(38);
    }

    @Test
    void test39() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(39);
    }

    @Test
    void test40() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(40);
    }

    @Test
    void test41() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(41);
    }

    @Test
    void test42() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(42);
    }

    @Test
    void test43() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(43);
    }

    @Test
    void test44() {
        buttonClicked = true;
        buttonVal = "Convert";
        performTest(44);
    }

    // **************************************************************************
    // helper methods
    // **************************************************************************

    // starts the actual test (enter values, click respective button and check results)
    public void performTest(int testIndex){
        enterMeasurements(measurements[testIndex]);
        triggerEvent();
        findAllElem();
        checkValues(expected[testIndex]);
    }

    // enter the measurements into the web driver
    public void enterMeasurements(String [] arr){
        driver.findElement(By.name("F")).sendKeys(arr[0]);
        driver.findElement(By.name("C")).sendKeys(arr[1]);
        driver.findElement(By.name("in")).sendKeys(arr[2]);
        driver.findElement(By.name("cm")).sendKeys(arr[3]);
        driver.findElement(By.name("ft")).sendKeys(arr[4]);
        driver.findElement(By.name("m")).sendKeys(arr[5]);
        driver.findElement(By.name("mi")).sendKeys(arr[6]);
        driver.findElement(By.name("km")).sendKeys(arr[7]);
        driver.findElement(By.name("gal")).sendKeys(arr[8]);
        driver.findElement(By.name("L")).sendKeys(arr[9]);
        driver.findElement(By.name("oz")).sendKeys(arr[10]);
        driver.findElement(By.name("g")).sendKeys(arr[11]);
        driver.findElement(By.name("lb")).sendKeys(arr[12]);
        driver.findElement(By.name("kg")).sendKeys(arr[13]);
    }

    // trigger event accordingly (convert/clear button click)
    public void triggerEvent(){
        if(buttonClicked){
            if(buttonVal.equals("Convert")){
                driver.findElement(By.name("submit")).click();
            }
            else if(buttonVal.equals("Clear form")){
                driver.findElement(By.name("clear")).click();
            }
        }
    }

    // extracts all measurement values in the text fields
    public void findAllElem(){
        degFVal = driver.findElement(By.name("F")).getAttribute("value");
        degCVal = driver.findElement(By.name("C")).getAttribute("value");
        inVal = driver.findElement(By.name("in")).getAttribute("value");
        cmVal = driver.findElement(By.name("cm")).getAttribute("value");
        ftVal = driver.findElement(By.name("ft")).getAttribute("value");
        mVal = driver.findElement(By.name("m")).getAttribute("value");
        miVal = driver.findElement(By.name("mi")).getAttribute("value");
        kmVal = driver.findElement(By.name("km")).getAttribute("value");
        galVal = driver.findElement(By.name("gal")).getAttribute("value");
        LVal = driver.findElement(By.name("L")).getAttribute("value");
        ozVal = driver.findElement(By.name("oz")).getAttribute("value");
        gVal = driver.findElement(By.name("g")).getAttribute("value");
        lbVal = driver.findElement(By.name("lb")).getAttribute("value");
        kgVal = driver.findElement(By.name("kg")).getAttribute("value");
    }

    // check output matches expected values
    public void checkValues(String [] arr){
        assertTrue(degFVal.equals(arr[0]));
        assertTrue(degCVal.equals(arr[1]));
        assertTrue(inVal.equals(arr[2]));
        assertTrue(cmVal.equals(arr[3]));
        assertTrue(ftVal.equals(arr[4]));
        assertTrue(mVal.equals(arr[5]));
        assertTrue(miVal.equals(arr[6]));
        assertTrue(kmVal.equals(arr[7]));
        assertTrue(galVal.equals(arr[8]));
        assertTrue(LVal.equals(arr[9]));
        assertTrue(ozVal.equals(arr[10]));
        assertTrue(gVal.equals(arr[11]));
        assertTrue(lbVal.equals(arr[12]));
        assertTrue(kgVal.equals(arr[13]));
    }
}