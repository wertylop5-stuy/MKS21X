/*
  auto expand array
  add to the end/begin
  remove elems
  toString
  get/set at index
*/

/*
TODO
need to fix toStringDebug
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
		int[] temp;
		if (mData.length < 1) {
			temp = new int[10];
		}
		else temp = new int[mData.length*2];
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
	@Override
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
		if (mData.length == 0) return "[]";
		//if (mData.length == 1) return "[_]";
		int x;
		String res = "[";
		for (x = 0; x < mSize; x++) {
			res += mData[x];
			if (x != mSize - 1) res += ", ";
		}
		if (x == 0) res += "_";
		if (x != mData.length && mData.length > 1) res += ", ";
		for (x++; x < mData.length; x++) {
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
    
    public void trimToSize() {
		mData = toArray();
    }
    
    public void clear() { mSize = 0; }
    
    public boolean isEmpty() { return mSize == 0; }
	
    public int get(int index) { return mData[index]; }
	
    public int size(){ return mSize; }
	//public int capacity(){ return mData.length; }
    
    public static void main(String[] args) {
		SuperArray s = new SuperArray();
		SuperArray t = new SuperArray();
		SuperArray q = new SuperArray();
		int f;
		
		for (int x = 0; x < 10; x++) {
			s.add(x);
			q.add(x);
		}
		//Standard created new array
		System.out.println(s.toStringDebug());
		System.out.println(t.toStringDebug());
		System.out.println(q.toStringDebug());
		
		System.out.println("addition");
		s.add(0, 1);
		System.out.println(s.toStringDebug());
		s.add(s.size() - 1, -67);	//Remember this shifts things
		System.out.println(s.toStringDebug());
		s.add(0);
		System.out.println(s.toStringDebug());
		
		System.out.println("trimming");
		s.trimToSize();
		System.out.println("s: " + s.toStringDebug());
		
		t.trimToSize();
		System.out.println("t: " + t.toStringDebug());
		
		q.trimToSize();
		System.out.println("q: " + q.toStringDebug());
		
		System.out.println("removing");
		f = s.remove(0);
		System.out.println(s.toStringDebug()+" rem "+f);
		f = s.remove(s.size() - 1);
		System.out.println(s.toStringDebug()+" rem "+f);
		s.remove(4);
		System.out.println(s.toStringDebug());
		
		System.out.println("setting");
		f = s.set(1, 29);
		System.out.println(s.toStringDebug()+" replaced " + f);
		f = s.set(0, 12345678);
		System.out.println(s.toStringDebug()+" replaced "+f);
		f = s.set(s.size() - 1, 90);
		System.out.println(s.toStringDebug()+" replaced "+f);
		
		System.out.println("toArray");
		s.add(2);
		int[] test = s.toArray();
		int[] test1 = new SuperArray().toArray();
		for (int x : test) System.out.print(x + " ");
		System.out.println();
		for (int x : test1) System.out.print(x + " ");
		System.out.println();
		
		System.out.println("testing indexOf");
		System.out.println(s.toStringDebug());
		System.out.println(s.indexOf(2));		//2
		System.out.println(s.indexOf(89));		//-1
		System.out.println(s.indexOf(5));		//4
		System.out.println(s.lastIndexOf(8));	//2
		System.out.println(s.lastIndexOf(234));	//-1
		System.out.println(s.lastIndexOf(2));	//7
		
		System.out.println("string debug");
		t.add(7);
		t.trimToSize();
		System.out.println(t.toStringDebug());
		System.out.println(t.size());
		
		System.out.println("clear");
		t.clear();
		t.add(4);
		System.out.println(t.toStringDebug());
		t.clear();
		System.out.println(t.toStringDebug());
		t.add(6);
		t.add(4);
		System.out.println(t.toStringDebug());
    }
}
