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

	//index is the spot where the element will be inserted
	 private void shiftRight(int index) {
		 if (mSize == mData.length) {
			 grow();
		 }
		 for (int x = mSize - 1; x >= index; x--) {
			 mData[x+1] = mData[x];
		 }
	 }
	 
	 //index is the spot where the removed element was
	 private void shiftLeft(int index) {
		 for (int x = index; x < mSize - 1; x++) {
			 mData[x] = mData[x+1];
		 }
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
	 
	 //Add a value at a certain index
	 public void add(int n, int index) {
		 shiftRight(index);
		 mData[index] = n;
		 mSize++;
	 }
	 
	 public void remove(int index) {
		 shiftLeft(index);
		 mSize--;
	 }
	 
	 public void replace(int index, int n) {
		 mData[index] = n;
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
		int x;
		String res = "[";
		for (x = 0; x < mSize; x++) {
			res += mData[x];
			if (x != mSize - 1) res += ", ";
		}
		if (x != mData.length) res += ", ";
		for (; x < mData.length; x++) {
			res+= "_";
			if (x != mData.length - 1) res += ", ";
		}
		return res + "]";
    }
	
	public int get(int index) {
		 return mData[index];
	}
	
	public int size(){
		return mSize;
    }
	

    public static void main(String[] args) {
		SuperArray s = new SuperArray();
		for (int x = 0; x < 11; x++) {
			s.add(x);
		}
		//System.out.println(s.toStringDebug());
		s.add(4, 0);
		s.remove(1);
		System.out.println(s.toStringDebug());
		System.out.println(s.size());
    }
}
