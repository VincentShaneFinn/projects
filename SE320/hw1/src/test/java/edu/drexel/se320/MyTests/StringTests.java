package edu.drexel.se320.MyTests;

import edu.drexel.se320.BinarySearch;
import edu.drexel.se320.Min;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;

public class StringTests {

	@Test
	public void testCharacterArray() {
		String[] arr = {"a","b","c"};
		assertThat(BinarySearch.find(arr, arr[0]), is(0));
		assertThat(BinarySearch.find(arr, arr[1]), is(1));
		assertThat(BinarySearch.find(arr, arr[2]), is(2));
	}
	
	@Test
	public void testStringArray() {
		String[] arr = {"cat","dog","monkey", "zebra" };
		assertThat(BinarySearch.find(arr, arr[0]), is(0));
		assertThat(BinarySearch.find(arr, arr[1]), is(1));
		assertThat(BinarySearch.find(arr, arr[2]), is(2));
		assertThat(BinarySearch.find(arr, arr[3]), is(3));
	}
	
	@Test
	public void testSpecialCharacters() {
		String[] arr = {"$","%","^", "&", " ", "" };
		Arrays.sort(arr);
		assertThat(BinarySearch.find(arr, arr[0]), is(0));
		assertThat(BinarySearch.find(arr, arr[1]), is(1));
		assertThat(BinarySearch.find(arr, arr[2]), is(2));
		assertThat(BinarySearch.find(arr, arr[3]), is(3));
		assertThat(BinarySearch.find(arr, arr[4]), is(4));
		assertThat(BinarySearch.find(arr, arr[5]), is(5));
	}
	
	@Test
	public void testSameCharactersAtFront() {
		String[] arr = {"##4","##5","##6", "###", "!!1", "!!2" };
		Arrays.sort(arr);
		assertThat(BinarySearch.find(arr, arr[0]), is(0));
		assertThat(BinarySearch.find(arr, arr[1]), is(1));
		assertThat(BinarySearch.find(arr, arr[2]), is(2));
		assertThat(BinarySearch.find(arr, arr[3]), is(3));
		assertThat(BinarySearch.find(arr, arr[4]), is(4));
		assertThat(BinarySearch.find(arr, arr[5]), is(5));
	}
	
	@Test
	public void testUpperCase() {
		String[] arr = {"upper","Upper"};
		Arrays.sort(arr);
		assertThat(BinarySearch.find(arr, arr[0]), is(0));
		assertThat(BinarySearch.find(arr, arr[1]), is(1));
	}
	
	@Test
	public void testUpperCaseNF() {
		thrown.expect(NoSuchElementException.class);
		String[] arr = {"upper"};
		BinarySearch.find(arr, "Upper");
	}
	
	//Tests that I was writing to prove strings work the same as int and float if the array is sorted
	
	String[] singleArr = { "2" };
    String[] evenArr = { "0","1.1","2.2","3.3","4.4","5.5" };
    String[] oddArr = { "0", "1.1", "10.1","2.2","3.3","4.4","5.5","6.6","7.7","8.8","9.9"}; // "0" is > "."
    String[] evenArrDuplicates = { "1","1","2","2","3","3" };
    String[] oddArrDuplicates = { "1.1","2.2","2.2","3.3","3.3","3.3","4.4","4.4","5.5","5.5","5.5" };
    String[] evenArrFullSpectrum = {"-7.1", "0","1.2", "8.1" };
    String[] oddArrFullSpectrum = {"-1.1", "-11","-11.1", "0", "1002.2", "1002.3", "5.1" };
    
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	//tests an array of length 1
    @Test
    public void testSingleArr() {
        assertThat(BinarySearch.find(singleArr, "2"), is(0));
    }
    //tests not if an item is not found in a length 1 array
    @Test
    public void testSingleArrNF() {
  	  thrown.expect(NoSuchElementException.class);
  	  BinarySearch.find(singleArr, "3");
    } 
    
    //tests that every item in an array of even length can be found
    @Test
    public void testEvenArray() {
    	validateAllElements(evenArr);
    }
    
    //tests that every item in an array of odd length can be found
    @Test
    public void testOddArray() {
    	validateAllElements(oddArr);
    }
    //double check we get what we expect if an item cant be found in even and odd arrays
    @Test
    public void testEvenArrayNF() {
    	 thrown.expect(NoSuchElementException.class);
     	  BinarySearch.find(evenArr, "10");
    }
    @Test
    public void testOddArrayNF() {
    	 thrown.expect(NoSuchElementException.class);
     	  BinarySearch.find(oddArr, "20");
    }
    
    //tests that every item in an array of even length with duplicates can find an index
    @Test
    public void testEvenDuplicatesArray() {
    	for (int i = 0; i < evenArrDuplicates.length; i++) {
    		assertThat(BinarySearch.find(evenArrDuplicates, evenArrDuplicates[i]), greaterThanOrEqualTo(0));
    	}
    }
    
    //tests that every item in an array of odd length with duplicates can find an index
    @Test
    public void testOddDuplicatesArray() {
    	for (int i = 0; i < oddArrDuplicates.length; i++) {
    		assertThat(BinarySearch.find(oddArrDuplicates, oddArrDuplicates[i]), greaterThanOrEqualTo(0));
    	}
    }
    
    @Test
    public void testFullIntegerSpectrumEven() {
    	validateAllElements(evenArrFullSpectrum);
    }
    @Test
    public void testFullIntegerSpectrumOdd() {
    	validateAllElements(oddArrFullSpectrum);
    }
    
    //helper function to repeat test for every element in array
    //to help debug for now just add a print statement before this method to help find which element was bad
    public void validateAllElements(String[] arr) {
    	for (int i = 0; i < arr.length; i++) {
    		//System.out.println("Array: " + arr + " element: " + i);
    		assertThat(BinarySearch.find(arr, arr[i]), is(i));
    	}
    }
  
}


