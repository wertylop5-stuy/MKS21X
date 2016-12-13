public class Sorts {
    public static String name(){ return "06.Lin.Stanley"; }
    
    /**
     *Selection sort on an array of ints
     *@param the array to be sorted
     */
    public static void selectionSort(int[] data) {
	int temp = 0;
	//int sortedIndex = 0;
	int smallestIndex = 0;
	int smallestNum = Integer.MAX_VALUE;
	//	while (sortedIndex < data.length) {
        for (int sortedIndex = 0; sortedIndex < data.length; sortedIndex++) {
	    smallestNum = Integer.MAX_VALUE;
	    //Selects the smallest non-sorted number
	    for (int counter = sortedIndex; counter < data.length; counter++) {
		if (data[counter] < smallestNum) {
		    smallestIndex = counter;
		    smallestNum = data[counter];
		}
	    }
	    //Swap integers
	    temp = data[sortedIndex];
	    data[sortedIndex] = data[smallestIndex];
	    data[smallestIndex] = temp;
	}
    }
    
    public static void main(String[] args) {
	int[] a = {1, 0, -1, 45, 21, 6};
	selectionSort(a);
	for (int x : a) System.out.println(x);
    }
}
