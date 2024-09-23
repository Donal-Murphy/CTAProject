package ie.atu.sw;

import java.util.Arrays;

public class Analyser {
	private static Summary summaries[] = null;
	
	public Summary[] analysis(Data[] data, String[] sortType, int[]arrSize) {
		summaries = new Summary[sortType.length*arrSize.length];
		int count = 0;
		for(String type: sortType) {
			for(int n: arrSize) {
				int i =0;
				long[] times = new long[10];
				for (Data entry:data) {
					if (entry.sortType().equals(type) && n == entry.size()) {
						times[i] = entry.t_exec();
						i++;
					}
				}	
				float avg = Arrays.stream(times).sum() / times.length;
				Arrays.sort(times);
				long worst = times[9];
				long best = times[0];
				summaries[count] = new Summary(type, n, avg, best, worst);
				
				count++;
			}
		}
			
		return summaries;
	}
	
	public static void printFormattedSummaryAvg(Summary[] summaries, String[] sortTypes, int[] arrSizes) {
		//Print size row
        System.out.format("%-16s", "Size");
        for (int size : arrSizes) {
            System.out.format("%-16d", size);
        }
        System.out.println();
        
        for (String sortType : sortTypes) {
        	//Print name of sortType
            System.out.format("%-16s", sortType);
            for (int size : arrSizes) {
                for (Summary s : summaries) {
                    if (s.sortType().equals(sortType) && s.size() == size) {
                    	System.out.format("%-16.3f", s.avg() / 1_000_000.0);
                    }
                }
                //double average = (count > 0) ? (double) sum / count : 0;
                //System.out.format("%-16.3f", average / 1_000_000.0);
            }
            System.out.println();
        }
    }
	
	public static void printFormattedSummaryBest(Summary[] summary, String[] sortTypes, int[] sizes) {
		//Print size row
        System.out.format("%-16s", "Size");
        for (int size : sizes) {
            System.out.format("%-16d", size);
        }
        System.out.println();
        
        for (String sortType : sortTypes) {
        	//Print name of sortType
            System.out.format("%-16s", sortType);
            for (int size : sizes) {
                int count = 0;
                for (Summary s : summary) {
                    if (s.sortType().equals(sortType) && s.size() == size) {
                    	System.out.format("%-16.3f", s.best() / 1_000_000.0);
                        count++;
                    }
                }
                //double average = (count > 0) ? (double) sum / count : 0;
                //System.out.format("%-16.3f", average / 1_000_000.0);
            }
            System.out.println();
        }
    }
	
	public static void printFormattedSummaryWorst(Summary[] summary, String[] sortTypes, int[] sizes) {
		//Print size row
        System.out.format("%-16s", "Size");
        for (int size : sizes) {
            System.out.format("%-16d", size);
        }
        System.out.println();
        
        for (String sortType : sortTypes) {
        	//Print name of sortType
            System.out.format("%-16s", sortType);
            for (int size : sizes) {
                int count = 0;
                for (Summary s : summary) {
                    if (s.sortType().equals(sortType) && s.size() == size) {
                    	System.out.format("%-16.3f", s.worst() / 1_000_000.0);
                        count++;
                    }
                }
                //double average = (count > 0) ? (double) sum / count : 0;
                //System.out.format("%-16.3f", average / 1_000_000.0);
            }
            System.out.println();
        }
    }

}
