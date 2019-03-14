package edu.drexel.se320.MyClasses;

import java.util.Arrays;

import edu.drexel.se320.BinarySearch;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
        Integer[] arr = { 0, 1, 2 };
        System.out.println(Arrays.toString(arr) + " Find: " + 1 );
        System.out.println("Index: " + BinarySearch.find(arr, 1));
        
        String[] arr2 = { "a", "b", "c", "foo" };
        System.out.println(Arrays.toString(arr2) + " Find: c" );
        System.out.println("Index: " + BinarySearch.find(arr2, "c"));
            
	}
}
