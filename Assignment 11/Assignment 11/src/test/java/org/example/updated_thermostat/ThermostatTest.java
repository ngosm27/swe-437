package org.example.updated_thermostat;

import org.example.utilities.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ThermostatTest {

    private Thermostat thermo;
    private ProgrammedSettings settings;

    @BeforeEach // Set up - Called before every test method.
    public void setUp()
    {
        thermo   = new Thermostat();
        settings = new ProgrammedSettings();
        thermo.setThresholdDiff (1);
    }

    // if (((curTemp < dTemp - thresholdDiff) || (Override && curTemp < overTemp - thresholdDiff)) && (timeSinceLastRun > minLag))
    // Predicate: (a || (b && c)) && d

    @Test
    public void testTTTT() {
        settings.setSetting (Period.MORNING, DayType.WEEKDAY, 70);
        thermo.setPeriod (Period.MORNING);
        thermo.setDay (DayType.WEEKDAY);
        thermo.setCurrentTemp (68);
        thermo.setTimeSinceLastRun (10);
        thermo.setMinLag (5);
        thermo.setOverride (true);
        thermo.setRegulator(true);  // changed
        thermo.setOverTemp (72);
        assertTrue (thermo.turnHeaterOn (settings));
        assertEquals (-2, thermo.getRunTime());
    }

    @Test
    public void testFFFF() {
        settings.setSetting (Period.MORNING, DayType.WEEKDAY, 70);
        thermo.setPeriod (Period.MORNING);
        thermo.setDay (DayType.WEEKDAY);
        thermo.setCurrentTemp (70);
        thermo.setTimeSinceLastRun (7);
        thermo.setMinLag (7);
        thermo.setOverride (false);
        thermo.setRegulator(false); // changed
        thermo.setOverTemp (70);
        assertFalse (thermo.turnHeaterOn (settings));
        assertEquals (0, thermo.getRunTime());  // changed - runtime isn't set; default = 0
    }

    @Test
    public void testPCtrue() {
        // Partial test for method turnHeaterOn() in class Thermostat
        // Criterion: PC
        // Value: True
        // Predicate: lines 28-30
        // Expected Output: true

        // Instantiate needed objects
        thermo   = new Thermostat();
        settings = new ProgrammedSettings();
        // Setting internal variable dTemp
        settings.setSetting (Period.MORNING, DayType.WEEKDAY, 69);
        thermo.setPeriod (Period.MORNING);
        thermo.setDay (DayType.WEEKDAY);
        // Clause a: curTemp < dTemp - thresholdDiff : true
        thermo.setCurrentTemp (63);
        thermo.setThresholdDiff (5);
        // Clause b: Override : true
        thermo.setOverride (true);
        // Clause c: curTemp < overTemp - thresholdDiff : true
        thermo.setOverTemp (70);
        // Clause d: timeSinceLastRun.greaterThan (minLag) : true
        thermo.setMinLag (10);
        thermo.setTimeSinceLastRun (12);
        // Run the test
        assertTrue (thermo.turnHeaterOn (settings));
        assertEquals(-7, thermo.getRunTime());  // changed
    }

    @Test
    public void testTTFTT() {
        settings.setSetting (Period.MORNING, DayType.WEEKDAY, 70);
        thermo.setPeriod (Period.MORNING);
        thermo.setDay (DayType.WEEKDAY);

        // Clause a: curTemp < dTemp - thresholdDiff : true
        thermo.setCurrentTemp (63);
        thermo.setThresholdDiff (5);
        // Clause b: Override : true
        thermo.setOverride (true);
        // Clause c: curTemp < overTemp - thresholdDiff : false
        thermo.setOverTemp (75);
        // Clause d: timeSinceLastRun.greaterThan (minLag) : true
        thermo.setMinLag (10);
        thermo.setTimeSinceLastRun (12);
        //Clause e: regulator: true
        thermo.setRegulator(true);
        assertTrue (thermo.turnHeaterOn (settings));
    }
    @Test
    public void testFTFTT() {
        settings.setSetting (Period.MORNING, DayType.WEEKDAY, 70);
        thermo.setPeriod (Period.MORNING);
        thermo.setDay (DayType.WEEKDAY);

        // Clause a: curTemp < dTemp - thresholdDiff : false
        thermo.setCurrentTemp (65);
        thermo.setThresholdDiff (5);
        // Clause b: Override : true
        thermo.setOverride (true);
        // Clause c: curTemp < overTemp - thresholdDiff : false
        thermo.setOverTemp (69);
        // Clause d: timeSinceLastRun.greaterThan (minLag) : true
        thermo.setMinLag (10);
        thermo.setTimeSinceLastRun (12);
        //Clause e: regulator: true
        thermo.setRegulator(true);
        assertFalse (thermo.turnHeaterOn (settings));
    }
    @Test
    public void testFTTTT() {
        settings.setSetting (Period.MORNING, DayType.WEEKDAY, 70);
        thermo.setPeriod (Period.MORNING);
        thermo.setDay (DayType.WEEKDAY);

        // Clause a: curTemp < dTemp - thresholdDiff : false
        thermo.setCurrentTemp (65);
        thermo.setThresholdDiff (5);
        // Clause b: Override : true
        thermo.setOverride (true);
        // Clause c: curTemp < overTemp - thresholdDiff : true
        thermo.setOverTemp (75);
        // Clause d: timeSinceLastRun.greaterThan (minLag) : true
        thermo.setMinLag (10);
        thermo.setTimeSinceLastRun (12);
        //Clause e: regulator: true
        thermo.setRegulator(true);
        assertTrue (thermo.turnHeaterOn (settings));
    }
    @Test
    public void testFFTTT() {
        settings.setSetting (Period.MORNING, DayType.WEEKDAY, 70);
        thermo.setPeriod (Period.MORNING);
        thermo.setDay (DayType.WEEKDAY);

        // Clause a: curTemp < dTemp - thresholdDiff : false
        thermo.setCurrentTemp (65);
        thermo.setThresholdDiff (5);
        // Clause b: Override : false
        thermo.setOverride (false);
        // Clause c: curTemp < overTemp - thresholdDiff : true
        thermo.setOverTemp (75);
        // Clause d: timeSinceLastRun.greaterThan (minLag) : true
        thermo.setMinLag (10);
        thermo.setTimeSinceLastRun (12);
        //Clause e: regulator: true
        thermo.setRegulator(true);
        assertFalse (thermo.turnHeaterOn (settings));
    }
    @Test
    public void testTTTFT() {
        settings.setSetting (Period.MORNING, DayType.WEEKDAY, 70);
        thermo.setPeriod (Period.MORNING);
        thermo.setDay (DayType.WEEKDAY);

        // Clause a: curTemp < dTemp - thresholdDiff : true
        thermo.setCurrentTemp (63);
        thermo.setThresholdDiff (5);
        // Clause b: Override : true
        thermo.setOverride (true);
        // Clause c: curTemp < overTemp - thresholdDiff : true
        thermo.setOverTemp (75);
        // Clause d: timeSinceLastRun.greaterThan (minLag) : false
        thermo.setMinLag (10);
        thermo.setTimeSinceLastRun (9);
        //Clause e: regulator: true
        thermo.setRegulator(true);
        assertFalse (thermo.turnHeaterOn (settings));
    }
    @Test
    public void testTTTTF() {
        settings.setSetting (Period.MORNING, DayType.WEEKDAY, 70);
        thermo.setPeriod (Period.MORNING);
        thermo.setDay (DayType.WEEKDAY);

        // Clause a: curTemp < dTemp - thresholdDiff : true
        thermo.setCurrentTemp (63);
        thermo.setThresholdDiff (5);
        // Clause b: Override : true
        thermo.setOverride (true);
        // Clause c: curTemp < overTemp - thresholdDiff : true
        thermo.setOverTemp (75);
        // Clause d: timeSinceLastRun.greaterThan (minLag) : true
        thermo.setMinLag (10);
        thermo.setTimeSinceLastRun (12);
        //Clause e: regulator: true
        thermo.setRegulator(false);
        assertTrue (thermo.turnHeaterOn (settings));
    }
}