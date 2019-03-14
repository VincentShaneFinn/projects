package edu.drexel.se320;

import edu.drexel.se320.Min;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertTrue;

@RunWith(Theories.class)
public class TestMinTheory {

    @DataPoints
    public static int[] bounds = {Integer.MIN_VALUE, -4, 0, 6, Integer.MAX_VALUE};

    @Theory
    public void testMinTheories(int a, int b){
        int c = Min.min(a,b);
        System.out.println("A: " + a + ", B: " + b);
        assertTrue("min less than first argument", c <= a);
        //assertTrue("min less than second argument", a <= b);
    }

}
