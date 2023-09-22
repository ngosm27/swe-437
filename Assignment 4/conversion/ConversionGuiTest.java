package conversion;

import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import javafx.application.Application;

public class ConversionGuiTest {


    /** Fixture initialization (common initialization
     *  for all tests). **/
    @Before public void setUp() {
    }


    /** A test checks the clear method works**/
    @Test public void testClear() {
        ConversionGui gui = new ConversionGui();
        gui.clearFields();
        
        for (int i = 0; i < gui.conversionCount; i++) {
            if(gui.imperialTextFields[i].getText().length() > 0 
                || metricTextFields[i].getText().length() > 0){
                fail("Text fields not cleared properly");
            }
        }
        assertTrue("", true== true);
    }
}
