/*
 * With Array length of 10 InsertionSort took 0 milliseconds or 0 seconds 
 * while Arrays.sort() took 1 milliseconds or 0 seconds.
 * 
 * With Array length of 100 InsertionSort took 0 milliseconds or 0 seconds 
 * while Arrays.sort() took 1 milliseconds or 0 seconds.
 * 
 * With Array length of 1000 InsertionSort took 6 milliseconds or 0 seconds 
 * while Arrays.sort() took 2 milliseconds or 0 seconds.
 * 
 * With Array length of 10000 InsertionSort took 59 milliseconds or 0 seconds 
 * while Arrays.sort() took 6 milliseconds or 0 seconds.
 * 
 * With Array length of 100000 InsertionSort took 3777 milliseconds or 3 seconds 
 * while Arrays.sort() took 52 milliseconds or 0 seconds.
 * 
 * 
 */

import java.util.Arrays;

public class ArraySortTesting {

	public static void main(String[] args) {
		//set length of arrays
		int length = 100000;
		// Create two identical arrays
		int[] A = new int[length];
		int[] B = new int[length];
		// Fill arrays with the same Random integers
		int x;
		for (int i =0; i < length; i++) {
			x = (int)(Integer.MAX_VALUE * Math.random());
			A[i] = x;
			B[i] = x;
		}
		long startTime = System.currentTimeMillis();
		insertionSort(A);
		long runTime = System.currentTimeMillis() - startTime;
		System.out.println("Insertion sort took " + runTime + 
				" Millisecond or "  + runTime/1000 + " Seconds");
		
		startTime = System.currentTimeMillis();
		Arrays.sort(B);
		runTime = System.currentTimeMillis() - startTime;
		System.out.println("Arrays.sort() took " + runTime + 
				" Millisecond or "  + runTime/1000 + " Seconds");
	}
	//Insertion function from book
	static void insertionSort(int[] A) {
		// Sort the array A into increasing order.
		int itemsSorted; // Number of items that have been sorted so far.
		for (itemsSorted = 1; itemsSorted < A.length; itemsSorted++) {
			// Assume that items A[0], A[1], ... A[itemsSorted-1]
			// have already been sorted. Insert A[itemsSorted]
			// into the sorted part of the list.
			int temp = A[itemsSorted];
			int loc = itemsSorted - 1;
			// The item to be inserted.
			// Start at end of list.
			while (loc >= 0 && A[loc] > temp) {
				A[loc + 1] = A[loc]; // Bump item from A[loc] up to loc+1.
				loc = loc - 1;
				// Go on to next location.
			}
			A[loc + 1] = temp; // Put temp in last vacated space.
		}
	}

}
