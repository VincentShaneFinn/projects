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

public class FloatTests {

	Float[] singleArr = { 2.0f };
    Float[] evenArr = { 0f,1.1f,2.2f,3.3f,4.4f,5.5f };
    Float[] oddArr = { 0f,1.1f,2.2f,3.3f,4.4f,5.5f,6.6f,7.7f,8.8f,9.9f,10.1f };
    Float[] evenArrDuplicates = { 1.1f,1.1f,2.2f,2.2f,3.3f,3.3f };
    Float[] oddArrDuplicates = { 1.1f,2.2f,2.2f,3.3f,3.3f,3.3f,4.4f,4.4f,5.5f,5.5f,5.5f };
    Float[] evenArrFullSpectrum = {-Float.MAX_VALUE, -7.1f, -Float.MIN_VALUE, 0f,Float.MIN_VALUE, 1.2f, 8.1f, Float.MAX_VALUE };
    Float[] oddArrFullSpectrum = {-Float.MAX_VALUE, -11.1f,-11f, -1.1f, -Float.MIN_VALUE, 0f, Float.MIN_VALUE, 5.1f, 1002.2f, 1002.3f, Float.MAX_VALUE };
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
	
	//tests an array of length 1
    @Test
    public void testSingleArr() {
    	Float[] arr = { 2.2f };
        assertThat(BinarySearch.find(arr, 2.2f), is(0));
    }
    //tests not if an item is not found in array
    @Test
    public void testSingleArrNF() {
    	Float[] arr = { 2.2f };
  	    thrown.expect(NoSuchElementException.class);
  	    BinarySearch.find(arr, 3.0f);
    } 
    @Test
    public void testEvenArrNF() {
    	Float[] arr = { 0f,1.1f,2.2f,3.3f,4.4f,5.5f };
  	    thrown.expect(NoSuchElementException.class);
  	    BinarySearch.find(arr, 12.1f);
    } 
    @Test
    public void testOddArrayNF() {
    	Float[] arr = { 0f,1.1f,2.2f,3.3f,4.4f,5.5f,6.6f,7.7f,8.8f,9.9f,10.1f };
    	thrown.expect(NoSuchElementException.class);
     	BinarySearch.find(arr, 20.1f);
    }

    @Test
    public void testMiddleItemEvenArray() {
    	Float[] arr = { 0f,1.1f,2.2f,3.3f,4.4f,5.5f };
        assertThat(BinarySearch.find(arr, arr[2]), is(2));
        assertThat(BinarySearch.find(arr, arr[3]), is(3));
    }
    @Test
    public void testMiddleItemOddArray() {
    	Float[] arr = { 0f,1.1f,2.2f,3.3f,4.4f,5.5f,6.6f,7.7f,8.8f,9.9f,10.1f };
        assertThat(BinarySearch.find(arr, arr[(int)Math.floor(arr.length/2)]), is((int)Math.floor(arr.length/2)));
    }
    
    @Test
    public void testFirstItemEvenArray() {
    	Float[] arr = { 10f,20f,30f,40f,60f,80f };
        assertThat(BinarySearch.find(arr, arr[0]), is(0));
    }
    @Test
    public void testFirstItemOddArray() {
    	Float[] arr = { 0f,1f,2f,3f,4f,5f,6f,7f,8f,9f,10f };
        assertThat(BinarySearch.find(arr, arr[0]), is(0));
    }
    
    @Test
    public void testLastItemEvenArray() {
    	Float[] arr = { 1.1f,2.1f,3.1f,4.1f,6.1f,8.1f };
        assertThat(BinarySearch.find(arr, arr[arr.length - 1]), is(arr.length - 1));
        assertThat(BinarySearch.find(arr, arr[arr.length - 1]), is(arr.length - 1));
    }
    @Test
    public void testLastItemOddArray() {
    	Float[] arr = { 1.9f,2.9f,3.9f,4.9f,5.9f,11.9f,12.9f };
        assertThat(BinarySearch.find(arr, arr[5]), is(5));
    }

    //tests that every item in an array of even length with duplicates can find an index
    @Test
    public void testEvenDuplicatesArray() {
    	Float[] arr = { 1f,1f,2f,2f,3f,3f };
    	assertThat(BinarySearch.find(arr, 1f), anyOf(is(0), is(1)));
    	assertThat(BinarySearch.find(arr, 2f), anyOf(is(2), is(3)));
    	assertThat(BinarySearch.find(arr, 3f), anyOf(is(4), is(5)));
    }
    
    //tests that every item in an array of odd length with duplicates can find an index
    @Test
    public void testOddDuplicatesArray() {
    	Float[] arr = { 1.5f,2.5f,2.5f,3.5f,3.5f,3.5f,4.5f,4.5f,5.5f,5.5f,5.5f };
    	assertThat(BinarySearch.find(arr, 1.5f), is(0));
    	assertThat(BinarySearch.find(arr, 2.5f), anyOf(is(1), is(2)));
    	assertThat(BinarySearch.find(arr, 3.5f), anyOf(is(3), is(4), is(5)));
    	assertThat(BinarySearch.find(arr, 4.5f), anyOf(is(6), is(7)));
    	assertThat(BinarySearch.find(arr, 5.5f), anyOf(is(8), is(9), is(10)));
    }
    
    Float[] arrEven = {-Float.MAX_VALUE, -7.1f, -Float.MIN_VALUE, 0f,Float.MIN_VALUE, 1.2f, 8.1f, Float.MAX_VALUE }; //even
    Float[] arrOdd = {-Float.MAX_VALUE, -11.1f,-11f, -1.1f, -Float.MIN_VALUE, 0f, Float.MIN_VALUE, 5.1f, 1002.2f, 1002.3f, Float.MAX_VALUE }; //odd
    Float[] arrDisplacedZero = {-Float.MAX_VALUE, -7.1f,-5f, -Float.MIN_VALUE, 0f,Float.MIN_VALUE, Float.MAX_VALUE }; //even
    Float[] arrNegativesOnly = {-Float.MAX_VALUE, -1200f, -1002f, -100f, -16f, -3f, -Float.MIN_VALUE, 0f }; //odd
    
    @Test
    public void testFindZero() {
    	assertThat(BinarySearch.find(arrEven, 0f), is(3));
    	assertThat(BinarySearch.find(arrOdd, 0f), is(5));
    	assertThat(BinarySearch.find(arrDisplacedZero, 0f), is(4));
    	assertThat(BinarySearch.find(arrNegativesOnly, 0f), is(7));
    }
    @Test
    public void testFindPositives() {
    	assertThat(BinarySearch.find(arrEven, 1.2f), is(5));
    	assertThat(BinarySearch.find(arrEven, 8.1f), is(6));
    	assertThat(BinarySearch.find(arrOdd, 5.1f), is(7));
    	assertThat(BinarySearch.find(arrOdd, 1002.2f), is(8));
    	assertThat(BinarySearch.find(arrOdd, 1002.3f), is(9));
    }
    @Test
    public void testFindNegatives() {
    	assertThat(BinarySearch.find(arrEven, -7.1f), is(1));
    	assertThat(BinarySearch.find(arrOdd, -11.1f), is(1));
    	assertThat(BinarySearch.find(arrOdd, -11f), is(2));
    	assertThat(BinarySearch.find(arrOdd, -1.1f), is(3));
    	assertThat(BinarySearch.find(arrDisplacedZero, -7.1f), is(1));
    	assertThat(BinarySearch.find(arrDisplacedZero, -5f), is(2));
    	assertThat(BinarySearch.find(arrNegativesOnly, -1200f), is(1));
    	assertThat(BinarySearch.find(arrNegativesOnly, -1002f), is(2));
    	assertThat(BinarySearch.find(arrNegativesOnly, -100f), is(3));
    	assertThat(BinarySearch.find(arrNegativesOnly, -16f), is(4));
    	assertThat(BinarySearch.find(arrNegativesOnly, -3f), is(5));
    }
    @Test
    public void testFindMaxValues() {
    	assertThat(BinarySearch.find(arrEven, Float.MAX_VALUE), is(arrEven.length-1));
    	assertThat(BinarySearch.find(arrOdd, Float.MAX_VALUE), is(arrOdd.length-1));
    	assertThat(BinarySearch.find(arrDisplacedZero, Float.MAX_VALUE), is(arrDisplacedZero.length-1));
    	
    	assertThat(BinarySearch.find(arrEven, -Float.MAX_VALUE), is(0));
    	assertThat(BinarySearch.find(arrOdd, -Float.MAX_VALUE), is(0));
    	assertThat(BinarySearch.find(arrDisplacedZero, -Float.MAX_VALUE), is(0));
    }
    @Test
    public void testFindMinValues() {
    	assertThat(BinarySearch.find(arrEven, Float.MIN_VALUE), is(4));
    	assertThat(BinarySearch.find(arrOdd, Float.MIN_VALUE), is(6));
    	assertThat(BinarySearch.find(arrDisplacedZero, Float.MIN_VALUE), is(5));

    	assertThat(BinarySearch.find(arrEven, -Float.MIN_VALUE), is(2));
    	assertThat(BinarySearch.find(arrOdd, -Float.MIN_VALUE), is(4));
    	assertThat(BinarySearch.find(arrDisplacedZero, -Float.MIN_VALUE), is(3));
    	assertThat(BinarySearch.find(arrNegativesOnly, -Float.MIN_VALUE), is(6));
    }
  
}


