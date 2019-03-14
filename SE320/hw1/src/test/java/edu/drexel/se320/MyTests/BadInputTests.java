package edu.drexel.se320.MyTests;

import edu.drexel.se320.BinarySearch;
import edu.drexel.se320.Min;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.rules.ExpectedException;
import org.junit.Rule;
import org.junit.Test;

public class BadInputTests {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testBothNullInput() {
        thrown.expect(IllegalArgumentException.class);
        
        Integer[] arr = null;
        Integer elem = null;
        
        BinarySearch.find(arr, elem);
    }
    
    @Test
    public void testNullArrayInput() {
        thrown.expect(IllegalArgumentException.class);
        
        Integer[] arr = null;
        Integer elem = 1;
        
        BinarySearch.find(arr, elem);
    }
    
    @Test
    public void testEmptyArrayInput() {
        thrown.expect(IllegalArgumentException.class);
        
        Integer[] arr = {};
        Integer elem = null;
        
        BinarySearch.find(arr, elem);
    }
    
    @Test
    public void testNullElemInput() {
        thrown.expect(IllegalArgumentException.class);
        
        Integer[] arr = {1,2,3};
        Integer elem = null;
        
        BinarySearch.find(arr, elem);
    }
    
    @Test
    public void testUnsortedArrayCanFind() {
        
        Integer[] arr = {1,2,4,0,3};
        Integer elem = 1;
        
        assertThat(BinarySearch.find(arr, elem),is(0));
    }
    
    @Test
    public void testUnsortedArrayCanttFind() {        
    	thrown.expect(NoSuchElementException.class);
    	
    	Integer[] arr = {1,2,4,0,3};
        Integer elem = 0;
        
        BinarySearch.find(arr, elem);
    }
}


