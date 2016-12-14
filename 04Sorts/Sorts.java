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
	    System.out.println("current: " + data[i] + " " + i);
	    if (data[i] < data[i-1]) {
		System.out.println("from " + data[i] + " to " + i);
		temp = data[i];
		j = i;
		while (j > 0 && data[j] < data[j-1]) {
		    data[j] = data[j-1];
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
    
    public static void main(String[] args) {
	int[] a = {1, 0, -1, 45, 21, 6};
	int[] b = {0, 1, 1, 2, 3, 4};
	int[] ans = {1, 2, 3, 4, 5, 6};
	resetArrays(a, b, ans);
	
	Arrays.sort(ans);
	selectionSort(b);
	System.out.println(Arrays.equals(b, ans));
	
	resetArrays(a, b, ans);
	Arrays.sort(ans);
	insertionSort(b);
	System.out.println(Arrays.equals(b, ans));
	for (int x:b) System.out.println(x);
    }
}
