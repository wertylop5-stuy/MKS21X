public class Sorts {
    public static String name(){ return "06.Lin.Stanley"; }
    
    /**
     *Selection sort on an array of ints
     *@param the array to be sorted
     */
    public static void selectionSort(int[] data) {
	int smallestIndex = 0;
	//int smallestNum = Integer.MAX_VALUE;
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
	
    }
    
    private static void swap(int[] data, int i, int j) {
	int temp = data[i];
	data[i] = data[j];
	data[j] = temp;
    }
    
    public static void main(String[] args) {
	int[] a = {1, 0, -1, 45, 21, 6};
	selectionSort(a);
	for (int x : a) System.out.println(x);
    }
}
