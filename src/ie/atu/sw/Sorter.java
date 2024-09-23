package ie.atu.sw;

import java.util.Arrays;

/**
 * The purpose of this class is to execute various sorting algorithms on a given
 * integer array of size n.
 */

public class Sorter {

	/*
	 * Method for sorting an int array of size n using the bubble sort algorithm:
	 */
	public int[] bubbleSort(int[] arr, int n) {
		boolean swapped = false; // Indicates that array elements have been swapped
		do {
			swapped = false;
			for (int i = 1; i < n; i++) { // Iterate through the array
				if (arr[i - 1] > arr[i]) {
					int temp = arr[i - 1]; // Store the larger number
					arr[i - 1] = arr[i]; // Swap the values
					arr[i] = temp;
					swapped = true; // Swap detected
				}
			}
		} while (swapped); // Repeat until no more swaps detected
		return arr;
	}

	/*
	 * Method for sorting an int array of size n using the selection sort algorithm:
	 */
	public int[] selectionSort(int[] arr, int n) {

		// Iterate through array to penultimate value
		for (int i = 0; i < (n - 1); i++) {
			int minIndex = i; // Set initial minIndex

			// Iterate through array starting @ arr[i+1]
			for (int j = i + 1; j < n; j++) {
				if (arr[j] < arr[minIndex]) { // If arr[j] < minIndex
					minIndex = j; // set arr[j] to be the new minIndex
				}
			}
			int temp = arr[minIndex]; // Store smaller value
			arr[minIndex] = arr[i]; // Swap values
			arr[i] = temp;
		}
		return arr;
	}

	/*
	 * Method for sorting an int array of size n using the insertion sort algorithm:
	 */
	public int[] insertionSort(int[] arr, int n) {
		int i = 1;
		while (i < n) { // Iterate through the array
			int j = i;
			// Execute sort if arr[j-1] > arr[j]. Stop when j = 0
			while (j > 0 && (arr[j - 1] > arr[j])) {
				int temp = arr[j]; // Store larger number
				arr[j] = arr[j - 1]; // Swap values
				arr[j - 1] = temp;
				j--; // Iterate backwards
			}
			i++; // Iterate forwards
		}

		return arr;
	}

	/*
	 * Mergesort algorithm method used to split an int array of size n into sub-arrays
	 * until they are of minimum size (1 or 2 elements)
	 */
	public int[] mergeSort(int[] arr, int n) {
		// Return the array if it only contains 0-1 elements
		if (n < 2) {
			return arr;
		}
		int mid = n / 2; // Find midpoint of array

		// Split the array into sub-arrays around the midpoint
		int[] left = Arrays.copyOfRange(arr, 0, mid);
		int[] right = Arrays.copyOfRange(arr, mid, n);
		//System.out.println(Arrays.toString(left));
		//System.out.println(Arrays.toString(right));

		// Recursively split the 2 sub-arrays
		left = mergeSort(left, left.length);
		right = mergeSort(right, right.length);

		// Sort & merge the 2 sub-arrays
		return merge(left, right);
	}

	/*
	 *  Sub-method of mergesort method to sort min-sized arrays & then merge the sorted arrays
	 */
	private int[] merge(int[] left, int[] right) {
		int i = 0;
		int j = 0;
		int k = 0;
		int[] merged = new int[left.length + right.length]; // Empty array to store merged arrays

		// Merge the left and right arrays in sorted order into the original array
		while (i < left.length && j < right.length) {
			/*
			 * Compare left[i] & right[j]. Store smaller value in arr[k]. Iterate to next
			 * index in original array + left/right array depending on which had the smaller
			 * value
			 */
			if (left[i] <= right[j]) { // left[i] is smaller
				merged[k] = left[i];
				k++; i++;
			} else {
				merged[k] = right[j]; // right[j] is smaller
				k++; j++;
			}
		} 
		// Place any elements remaining in left arr, in the event that the left array is longer
		while (i < left.length) {
			merged[k] = left[i];
			k++; i++;
		}
		// Place any elements remaining in right arr, in the event that the right array is longer
		while (j < right.length) {
			merged[k] = right[j];
			k++; j++;
		}
		return merged;
	}
	
	//Radix Sort method that takes in an array of size n and sorts based on the radix sort algorithm
	public int[] radixSort(int[] arr, int n) {
		
		// Get maximum value in array
		int max = 0;
		for(int i = 1; i < n; i++) {
			if(arr[i-1] < arr[i]) {
				max = arr[i];
			}
			//System.out.println(max);
		}
		
		//Get number of digits in max value
		int count = 0;
		while (max != 0) {
			max = max/10;
			count++;
		}
		//System.out.println(count);
		// Calculate place value (1,10,100..) based on no. of digits
		int placeValue = 1;
		while(count > 0) {
			countSort(arr, placeValue, n);	//Sort based on place value
			placeValue *= 10;
			count--;
		}
		return arr;
	}
	
	/*
	 * Sub-method of RadixSort that takes in an array of size n & sorts based on the digit 
	 * in the place value.
	 */
	private int[] countSort(int[] arr, int placeValue, int n) {
		int[] newArr = new int[n];
		int[] freqArr = new int[10];
		
		for (int i=0; i<n; i++) {
			int digit = arr[i] / placeValue % 10;
			freqArr[digit]++;
		}
		
		for (int i = 1; i < 10; i++) {
			freqArr[i] += freqArr[i-1]; 
		}
		
		for (int i = n -1; i >= 0; i--) {
			int digit = arr[i] / placeValue % 10;
			newArr[(freqArr[digit] - 1)] = arr[i];
			freqArr[digit]--;
		}
		System.arraycopy(newArr, 0, arr, 0, n);
		
		return arr;
	}

}
