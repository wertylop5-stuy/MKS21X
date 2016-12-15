import java.util.Arrays;
public class Sorts {
    public static String name(){ return "06.Lin.Stanley"; }
    
    /**
     *Selection sort on an array of ints
     *@param the array to be sorted
     */
    public static void selectionSort(int[] data) {
		int smallestIndex = 0;
		for (int sortedIndex = 0; sortedIndex < data.length; sortedIndex++) {
			smallestIndex = sortedIndex;
			
			//Selects the smallest non-sorted number
			for (int counter = sortedIndex; counter < data.length; counter++) {
				if (data[counter] < data[smallestIndex]) {
					smallestIndex = counter;
				}
			}
			
			//Swap integers
			swap(data, sortedIndex, smallestIndex);
		}
    }
    
    
    public static void insertionSort(int[] data) {
		int temp = 0;
		int j = 0;
		
		//Go thru all elements
		for (int i = 1; i < data.length; i++) {
			
			//Is the current less than the previous?
			if (data[i] < data[i-1]) {
				
				//save current val
				temp = data[i];
				j = i;
				
				//is current less than previous
				while (j > 0 && temp < data[j-1]) {
					data[j] = data[j-1];
					
					//change index
					j--;
				}
				data[j] = temp;
			}
		}
    }
    
    private static void swap(int[] data, int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
    }

    private static void resetArrays(int[] master, int[] a, int[] b) {
		System.arraycopy(master, 0, a, 0, a.length);
		System.arraycopy(master, 0, b, 0, b.length);
    }
	private static void resetArrays(int[] master, int[] a/*, int[] b*/) {
		System.arraycopy(master, 0, a, 0, a.length);
		//System.arraycopy(master, 0, b, 0, b.length);
    }
    
    public static void main(String[] args) {
		int[] a = {1, 0, -1, 45, 21, 6};
		int[] c = {0, 0, 1, 0, 0, 0};
		int[] e = {1, 2, 3, 4, 5, 6};
		int[] g = {6, 5, 4, 3, 2, 1};
		
		int[] z = {1};
		
		int[] b = {0, 1, 1, 2, 3, 4};
		int[] d = {9};
		int[] ans = {1, 2, 3, 4, 5, 6};
		int[] as = {8};
		
		resetArrays(a, b, ans);
		Arrays.sort(ans);
		selectionSort(b);
		System.out.println(Arrays.equals(b, ans));
		
		resetArrays(c, b, ans);
		Arrays.sort(ans);
		selectionSort(b);
		System.out.println(Arrays.equals(b, ans));
		
		resetArrays(a, b, ans);
		Arrays.sort(ans);
		insertionSort(b);
		System.out.println(Arrays.equals(b, ans));
		
		resetArrays(e, b, ans);
		Arrays.sort(ans);
		insertionSort(b);
		System.out.println(Arrays.equals(b, ans));
		
		resetArrays(g, b, ans);
		Arrays.sort(ans);
		insertionSort(b);
		System.out.println(Arrays.equals(b, ans));
		
		resetArrays(z, d, as);
		Arrays.sort(as);
		insertionSort(d);
		selectionSort(d);
		System.out.println(Arrays.equals(d, as));
		
    }
}
