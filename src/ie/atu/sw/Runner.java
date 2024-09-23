package ie.atu.sw;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Runner {
	
	private static final int[] arrSize = new int[]{100,250,500,750,1000,1250,2500,3750,5000,6250,7500,8750,10_000,20_000};
	private static final String[] sortType = new String[] {"BubbleSort","InsertionSort","SelectionSort","MergeSort","RadixSort"};
	private static Data data[] = null;
	private static final int reps = 10;
	
	private static int[] arrGen(int n) {
		
		int[] arr = new int[n];
		ThreadLocalRandom rand = ThreadLocalRandom.current();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt(0, 1000000);
		}
		return arr;
	}
	
	public static void main(String[] args) {
		data = new Data[reps*arrSize.length*sortType.length];
		int count = 0;
		
		Sorter test = new Sorter();
		for (int n : arrSize) {

			int[] unsortedArr = arrGen(n);
			long startTime = 0;
			long endTime = 0;
			long execTime = 0;
			int[] result = new int[n];
			for (int j = 0; j<reps; j++){
				
				int[] arr = Arrays.copyOf(unsortedArr, n); // move to algo
				startTime = java.lang.System.nanoTime();
				result = test.bubbleSort(arr,n);
				endTime = java.lang.System.nanoTime();
				execTime = endTime - startTime;
				data[count] = new Data("BubbleSort",n,unsortedArr,result,startTime,endTime,execTime);
				count++;
				
				arr = Arrays.copyOf(unsortedArr, n);
				startTime = java.lang.System.nanoTime();
				result = test.insertionSort(arr,n);
				endTime = java.lang.System.nanoTime();
				execTime = endTime - startTime;
				data[count] = new Data("InsertionSort",n,unsortedArr,result,startTime,endTime,execTime);
				count++;
				
				arr = Arrays.copyOf(unsortedArr, n);
				startTime = java.lang.System.nanoTime();
				result = test.selectionSort(arr,n);
				endTime = java.lang.System.nanoTime();
				execTime = endTime - startTime;
				data[count] = new Data("SelectionSort",n,unsortedArr,result,startTime,endTime,execTime);
				count++;
				
				arr = Arrays.copyOf(unsortedArr, n);
				startTime = java.lang.System.nanoTime();
				result = test.mergeSort(arr,n);
				endTime = java.lang.System.nanoTime();
				execTime = endTime - startTime;
				data[count] = new Data("MergeSort",n,unsortedArr,result,startTime,endTime,execTime);
				count++;
				
				arr = Arrays.copyOf(unsortedArr, n);
				startTime = java.lang.System.nanoTime();
				result = test.radixSort(arr,n);
				endTime = java.lang.System.nanoTime();
				execTime = endTime - startTime;
				data[count] = new Data("RadixSort",n,unsortedArr,result,startTime,endTime,execTime);
				count++;
				}
				
			}
		
		Analyser analys = new Analyser();
		Summary[] s = analys.analysis(data, sortType, arrSize);
		
		System.out.println("Best:");
		analys.printFormattedSummaryBest(s, sortType, arrSize);
		System.out.println('\n' + "Worst:");
		analys.printFormattedSummaryWorst(s, sortType, arrSize);
		System.out.println('\n' + "Average:");
		analys.printFormattedSummaryAvg(s, sortType, arrSize);
		
		
		//for(Summary summary:s) {
		//System.out.println(summary.type() + '\t' + summary.n() + '\t' + summary.avg());
		//System.out.println("unsorted: " + Arrays.toString(data.unsorted()));
		//System.out.println("sorted: " + Arrays.toString(data.sorted()));
	//}
		
		//for(Data data:data) {
			//System.out.println(data);
			//System.out.println("unsorted: " + Arrays.toString(data.unsorted()));
			//System.out.println("sorted: " + Arrays.toString(data.sorted()));
		//}
	}
}
