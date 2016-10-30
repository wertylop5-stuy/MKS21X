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
    public boolean add(int n){
		if (mSize == mData.length) {
			grow();
		}
		mData[mSize++] = n;
		return true;
    }
	 
    //Add a value at a certain index
    public void add(int index, int n) {
		shiftRight(index);
		mData[index] = n;
		mSize++;
    }
	 
    public int remove(int index) {
		int temp = mData[index];
		shiftLeft(index);
		mSize--;
		return temp;
    }
	 
    public int set(int index, int n) {
		int temp = mData[index];
		mData[index] = n;
		return temp;
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
    
    public int[] toArray() {
		int[] res = new int[mSize];
		for (int x = 0; x < mSize; x++) {
			res[x] = mData[x];
		}
		return res;
    }
	
	public int indexOf(int i) {
		for (int x = 0; x < mSize; x++) {
			if (mData[x] == i) return x;
		}
		return -1;
	}
	
	public int lastIndexOf(int i) {
		for (int x = mSize - 1; x > -1; x--) {
			if (mData[x] == i) return x;
		}
		return -1;
	}

    public void clear() { mSize = 0; }
    
    public boolean isEmpty() { return mSize == 0; }
	
    public int get(int index) { return mData[index]; }
	
    public int size(){ return mSize; }

    public static void main(String[] args) {
		SuperArray s = new SuperArray();
		for (int x = 0; x < 11; x++) {
			s.add(x);
		}
		//System.out.println(s.toStringDebug());
		s.add(0, 1);
		int f = s.set(1, 29);
		f = s.remove(3);
		System.out.println(s.toStringDebug());
		System.out.println(f);
		int[] test = s.toArray();
		int[] test1 = new SuperArray().toArray();
		System.out.println("testing toArray");
		for (int x : test) System.out.print(x + " ");
		System.out.println();
		for (int x : test1) System.out.print(x + " ");
		System.out.println();
		System.out.println("testing indexOf");
		System.out.println(s.indexOf(29));		//1
		System.out.println(s.indexOf(101));		//-1
		System.out.println(s.indexOf(1));		//0
		System.out.println(s.lastIndexOf(1));	//2
		System.out.println(s.lastIndexOf(234));	//-1
    }
}
