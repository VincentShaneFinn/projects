//package edu.drexel.se320.MyTests;
//
//import edu.drexel.se320.BinarySearch;
//import edu.drexel.se320.Min;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.anyOf;
//import static org.hamcrest.Matchers.is;
//import static org.hamcrest.Matchers.greaterThanOrEqualTo;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//import java.util.Arrays;
//import java.util.NoSuchElementException;
//
//import org.junit.rules.ExpectedException;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.experimental.theories.DataPoints;
//import org.junit.experimental.theories.Theories;
//import org.junit.experimental.theories.Theory;
//
//@RunWith(Parameterized.class)
//public class FloatTestsParameters {
//
//	Float[] singleArr = { 2.0f };
//    Float[] evenArr = { 0f,1.1f,2.2f,3.3f,4.4f,5.5f };
//    Float[] oddArr = { 0f,1.1f,2.2f,3.3f,4.4f,5.5f,6.6f,7.7f,8.8f,9.9f,10.1f };
//    Float[] evenArrDuplicates = { 1.1f,1.1f,2.2f,2.2f,3.3f,3.3f };
//    Float[] oddArrDuplicates = { 1.1f,2.2f,2.2f,3.3f,3.3f,3.3f,4.4f,4.4f,5.5f,5.5f,5.5f };
//    Float[] evenArrFullSpectrum = {Float.MIN_VALUE, -7.1f, 0f, 1.2f, 8.1f, Float.MAX_VALUE };
//    Float[] oddArrFullSpectrum = {Float.MIN_VALUE, -11.1f,-11f, -1.1f, 0f, 5.1f, 1002.2f, 1002.3f, Float.MAX_VALUE };
//    
//    @Parameterized.Parameters
//	public static Iterable<Object[]> data() {
//	    return Arrays.asList(new Object[][]{
//	                    {0, 0}, {1, 1}, {2,3}, {3,4}, {4,5}
//	            }
//	    );
//	}
//    
//	@Rule
//    public ExpectedException thrown = ExpectedException.none();
//	
//	//tests an array of length 1
//    @Test
//    public void testSingleArr() {
//        assertThat(BinarySearch.find(singleArr, 2f), is(0));
//    }
//    //tests not if an item is not found in a length 1 array
//    @Test
//    public void testSingleArrNF() {
//  	  thrown.expect(NoSuchElementException.class);
//  	  BinarySearch.find(singleArr, 3f);
//    } 
//    
//    //tests that every item in an array of even length can be found
//    @Test
//    public void testEvenArray() {
//    	for (int i = 0; i < evenArr.length; i++) {
//    		assertThat(BinarySearch.find(evenArr, evenArr[i]), is(i));
//    	}
//    }
//    
//    //tests that every item in an array of odd length can be found
//    @Test
//    public void testOddArray() {
//    	for (int i = 0; i < oddArr.length; i++) {
//    		assertThat(BinarySearch.find(oddArr, oddArr[i]), is(i));
//    	}
//    }
//    //double check we get what we expect if an item cant be found in even and odd arrays
//    @Test
//    public void testEvenArrayNF() {
//    	 thrown.expect(NoSuchElementException.class);
//     	  BinarySearch.find(evenArr, 10f);
//    }
//    @Test
//    public void testOddArrayNF() {
//    	 thrown.expect(NoSuchElementException.class);
//     	  BinarySearch.find(oddArr, 20f);
//    }
//    
//    //tests that every item in an array of even length with duplicates can find an index
//    @Test
//    public void testEvenDuplicatesArray() {
//    	for (int i = 0; i < evenArrDuplicates.length; i++) {
//    		assertThat(BinarySearch.find(evenArrDuplicates, evenArrDuplicates[i]), greaterThanOrEqualTo(0));
//    	}
//    }
//    
//    //tests that every item in an array of odd length with duplicates can find an index
//    @Test
//    public void testOddDuplicatesArray() {
//    	for (int i = 0; i < oddArrDuplicates.length; i++) {
//    		assertThat(BinarySearch.find(oddArrDuplicates, oddArrDuplicates[i]), greaterThanOrEqualTo(0));
//    	}
//    }
//    
//    @Test
//    public void testFullIntegerSpectrumEven() {
//    	for (int i = 0; i < evenArrFullSpectrum.length; i++) {
//    		assertThat(BinarySearch.find(evenArrFullSpectrum, evenArrFullSpectrum[i]), is(i));
//    		System.out.println(i);
//    	}
//    }
//    @Test
//    public void testFullIntegerSpectrumOdd() {
//    	for (int i = 0; i < oddArrFullSpectrum.length; i++) {
//    		assertThat(BinarySearch.find(oddArrFullSpectrum, oddArrFullSpectrum[i]), is(i));
//    		System.out.println(i);
//    	}
//    }
//  
//}
//
//
