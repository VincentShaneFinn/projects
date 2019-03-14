package edu.drexel.se320.MyTests;

import edu.drexel.se320.BinarySearch;
import edu.drexel.se320.Min;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;

public class IntegerTests {
    
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	//tests an array of length 1
    @Test
    public void testSingleArr() {
    	Integer[] arr = { 2 };
        assertThat(BinarySearch.find(arr, 2), is(0));
    }
    //tests not if an item is not found in array
    @Test
    public void testSingleArrNF() {
    	Integer[] arr = { 2 };
  	    thrown.expect(NoSuchElementException.class);
  	    BinarySearch.find(arr, 3);
    } 
    @Test
    public void testEvenArrNF() {
    	Integer[] arr = { 2,3,4,5 };
  	    thrown.expect(NoSuchElementException.class);
  	    BinarySearch.find(arr, 1);
    } 
    @Test
    public void testOddArrayNF() {
    	Integer[] arr = { 0,3,5,7,10 };
    	thrown.expect(NoSuchElementException.class);
     	BinarySearch.find(arr, 20);
    }

    @Test
    public void testMiddleItemEvenArray() {
        Integer[] arr = { 0,1,2,3,4,5 };
        assertThat(BinarySearch.find(arr, arr[2]), is(2));
        assertThat(BinarySearch.find(arr, arr[3]), is(3));
    }
    @Test
    public void testMiddleItemOddArray() {
        Integer[] arr = { 0,1,2,3,4,5,6,7,8,9,10 };
        assertThat(BinarySearch.find(arr, arr[(int)Math.floor(arr.length/2)]), is((int)Math.floor(arr.length/2)));
    }
    
    @Test
    public void testFirstItemEvenArray() {
        Integer[] arr = { 10,20,30,40,60,80 };
        assertThat(BinarySearch.find(arr, arr[0]), is(0));
    }
    @Test
    public void testFirstItemOddArray() {
        Integer[] arr = { 0,1,2,3,4,5,6,7,8,9,10 };
        assertThat(BinarySearch.find(arr, arr[0]), is(0));
    }
    
    @Test
    public void testLastItemEvenArray() {
        Integer[] arr = { 1,2,3,4,6,8 };
        assertThat(BinarySearch.find(arr, arr[arr.length - 1]), is(arr.length - 1));
        assertThat(BinarySearch.find(arr, arr[arr.length - 1]), is(arr.length - 1));
    }
    @Test
    public void testLastItemOddArray() {
        Integer[] arr = { 1,2,3,4,5,11,12 };
        assertThat(BinarySearch.find(arr, arr[5]), is(5));
    }

    //tests that every item in an array of even length with duplicates can find an index
    @Test
    public void testEvenDuplicatesArray() {
        Integer[] arr = { 1,1,2,2,3,3 };
    	assertThat(BinarySearch.find(arr, 1), anyOf(is(0), is(1)));
    	assertThat(BinarySearch.find(arr, 2), anyOf(is(2), is(3)));
    	assertThat(BinarySearch.find(arr, 3), anyOf(is(4), is(5)));
    }
    
    //tests that every item in an array of odd length with duplicates can find an index
    @Test
    public void testOddDuplicatesArray() {
        Integer[] arr = { 1,2,2,3,3,3,4,4,5,5,5 };
    	assertThat(BinarySearch.find(arr, 1), is(0));
    	assertThat(BinarySearch.find(arr, 2), anyOf(is(1), is(2)));
    	assertThat(BinarySearch.find(arr, 3), anyOf(is(3), is(4), is(5)));
    	assertThat(BinarySearch.find(arr, 4), anyOf(is(6), is(7)));
    	assertThat(BinarySearch.find(arr, 5), anyOf(is(8), is(9), is(10)));
    }
    
    Integer[] arrEven = {Integer.MIN_VALUE, -7, 0, 1, 8, Integer.MAX_VALUE }; //even
    Integer[] arrOdd = {Integer.MIN_VALUE, -11, -1, 0, 5, 1002, Integer.MAX_VALUE }; //odd
    Integer[] arrDisplacedZero = {Integer.MIN_VALUE, -7, -5, -4, -3, -2, -1, 0, 1, 8, Integer.MAX_VALUE }; //even
    Integer[] arrNegativesOnly = {Integer.MIN_VALUE, -1200, -1002, -100, -16, -3, 0 }; //odd
    
    @Test
    public void testFindZero() {
    	assertThat(BinarySearch.find(arrEven, 0), is(2));
    	assertThat(BinarySearch.find(arrOdd, 0), is(3));
    	assertThat(BinarySearch.find(arrDisplacedZero, 0), is(7));
    	assertThat(BinarySearch.find(arrNegativesOnly, 0), is(6));
    }
    @Test
    public void testFindPositives() {
    	assertThat(BinarySearch.find(arrEven, 1), is(3));
    	assertThat(BinarySearch.find(arrEven, 8), is(4));
    	assertThat(BinarySearch.find(arrOdd, 5), is(4));
    	assertThat(BinarySearch.find(arrOdd, 1002), is(5));
    	assertThat(BinarySearch.find(arrDisplacedZero, 1), is(8));
    	assertThat(BinarySearch.find(arrDisplacedZero, 8), is(9));    	
    }
    @Test
    public void testFindNegatives() {
    	assertThat(BinarySearch.find(arrEven, -7), is(1));
    	assertThat(BinarySearch.find(arrOdd, -11), is(1));
    	assertThat(BinarySearch.find(arrOdd, -1), is(2));
    	assertThat(BinarySearch.find(arrDisplacedZero, -7), is(1));
    	assertThat(BinarySearch.find(arrDisplacedZero, -5), is(2));
    	assertThat(BinarySearch.find(arrDisplacedZero, -4), is(3));
    	assertThat(BinarySearch.find(arrDisplacedZero, -3), is(4));
    	assertThat(BinarySearch.find(arrDisplacedZero, -2), is(5));
    	assertThat(BinarySearch.find(arrDisplacedZero, -1), is(6));
    	assertThat(BinarySearch.find(arrNegativesOnly, -1200), is(1));
    	assertThat(BinarySearch.find(arrNegativesOnly, -1002), is(2));
    	assertThat(BinarySearch.find(arrNegativesOnly, -100), is(3));
    	assertThat(BinarySearch.find(arrNegativesOnly, -16), is(4));
    	assertThat(BinarySearch.find(arrNegativesOnly, -3), is(5));
    }
    @Test
    public void testFindMaxValue() {
    	assertThat(BinarySearch.find(arrEven, Integer.MAX_VALUE), is(arrEven.length-1));
    	assertThat(BinarySearch.find(arrOdd, Integer.MAX_VALUE), is(arrOdd.length-1));
    	assertThat(BinarySearch.find(arrDisplacedZero, Integer.MAX_VALUE), is(arrDisplacedZero.length-1));
    }
    @Test
    public void testFindMinValue() {
    	assertThat(BinarySearch.find(arrEven, Integer.MIN_VALUE), is(0));
    	assertThat(BinarySearch.find(arrOdd, Integer.MIN_VALUE), is(0));
    	assertThat(BinarySearch.find(arrDisplacedZero, Integer.MIN_VALUE), is(0));
    	assertThat(BinarySearch.find(arrNegativesOnly, Integer.MIN_VALUE), is(0));
    }

}


