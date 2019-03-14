package edu.drexel.se320;

import java.util.NoSuchElementException;

public class BinarySearch {

    public static <T extends Comparable<T>> int find(T[] array, T elem) {
    	if(array == null) {
    		throw new IllegalArgumentException();
    	}
    	else if (array.length <= 0) {
    		throw new IllegalArgumentException();
    	}
    	else if (elem == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	//binary search requires a sorted array, checks the middle element, and if not there, it removes the half that could not contain the item and tries again
    	// if duplicates, can return any index
    	int leftI = 0;
    	int rightI = array.length - 1;
    	while (leftI <= rightI) {
    		int m = (int) Math.floor((leftI + rightI) / 2);
    		if(array[m].compareTo(elem) < 0) {
    			leftI = m + 1;
    		}
    		else if(array[m].compareTo(elem) > 0) {
    			rightI = m - 1;
    		}
    		else {
    			return m;
    		}
    	}
    	throw new NoSuchElementException();

        //return elem.compareTo(elem);
    }
}
