import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class PrimeTester {
    private ArrayList<Integer> p;

    @Before
    public void setup() {
        p = new ArrayList<>();
    }

    @After
    public void cleanup() { // Optional to include b/c of garbage collector
        p = null;
    }

    @Test
    public void testComputePrime() {
        p.computePrime(5);  // Compute the first 5 prime numbers

        assertTrue("computePrimes failed", p.getFirstPrime() == 2);
        assertTrue("computePrimes failed", p.getNextPrime() == 3);
        assertTrue("computePrimes failed", p.getNextPrime() == 5);
        assertTrue("computePrimes failed", p.getNextPrime() == 7);
        assertTrue("computePrimes failed", p.getNextPrime() == 11);
    }
}