/*
auto expand array
add to the end/begin
remove elems
toString
get/set at index
*/
public class SuperArray {
    private int[] mData;
    private int mSize;

    //constructor make an empty superArray should make size 0, but the data capacity 10.
    public SuperArray() {
	mData = new int[10];
	mSize = 0;
    }
    
    public int size(){
	return mSize;
    }

    //1
    /**add the value n to the next available slot in the superArray.
     *this will change the size. This function should always work
     *And will resize the SuperArray if needed.*/
     public void add(int n){
	 if (mSize == mData.length) {
	     grow();
	 }
	 mData[mSize++] = n;
     }


     //2
     /**Resize the data, by making a new array, then copying over elements, use this as your data.*/
    private void grow(){
	int[] temp = new int[mData.length*2];
	for (int x = 0; x < mData.length; x++) {
	    temp[x] = mData[x];	 
	}
	mData = temp;
    }

    //3
    /**format the super array like this :   [ 1, 3, 6, 8, 23, 99, -4, 5] 
     *commas between... square bracket start/end and no comma at end.*/
    public String toString(){
	String res = "[";
	for (int x = 0; x < mSize; x++) {
	    res += mData[x];
	    if (x != mSize - 1) res += ", ";
	}
	return res + "]";
    }

    //4
    /**format the super array like this :   [ 1, 8, 23, 99, -4, 5, _, _, _, _]  
     *(capacity is 10, but only 6 are used)
     *commas between... square bracket start/end and no comma at end.
     *unused slots should be printed as _ (underscores) */
    public String toStringDebug(){
	return null;
    }

    public static void main(String[] args) {
	SuperArray s = new SuperArray();
	for (int x = 0; x < 10; x++) {
	    s.add(x);
	}
	System.out.println(s);
    }
}
