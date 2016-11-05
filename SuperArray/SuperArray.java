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
    private String[] mData;
    private int mSize;
	
    //constructor make an empty superArray should make size 0, but the data capacity 10.
    public SuperArray() {
		mData = new String[10];
		mSize = 0;
    }
    
    public SuperArray(int initCapacity) {
	if (initCapacity < 0)
	    throw new IllegalArgumentException("Bad index: " + initCapacity);
		mData = new String[initCapacity];
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
		String[] temp;
		if (mData.length < 1) {
			temp = new String[10];
		}
		else temp = new String[mData.length*2];
		for (int x = 0; x < mData.length; x++) {
			temp[x] = mData[x];	 
		}
		mData = temp;
    }

    //1
    /**add the value n to the next available slot in the superArray.
     *this will change the size. This function should always work
     *And will resize the SuperArray if needed.*/
    public boolean add(String n){
		if (mSize == mData.length) {
			grow();
		}
		mData[mSize++] = n;
		return true;
    }
	 
    //Add a value at a certain index
    public void add(int index, String n) {
		//cuz you can add am element to the end, so not >=
		if (index < 0 || index > size())
			throw new IndexOutOfBoundsException(""+index);
		if (mSize == mData.length) {
			grow();
		}
		shiftRight(index);
		mData[index] = n;
		mSize++;
    }
	 
    public String remove(int index) {
		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException(""+index);
		String temp = mData[index];
		shiftLeft(index);
		mSize--;
		return temp;
    }
	 
    public String set(int index, String n) {
		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException(""+index);
		String temp = mData[index];
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
		if (x == 0) {
			res += "_";
			x++;
		}
		if (x != mData.length && mData.length > 1) res += ", ";
		for (; x < mData.length; x++) {
			res+= "_";
			if (x != mData.length - 1) res += ", ";
		}
		return res + "]";
    }
    
    public String[] toArray() {
		String[] res = new String[mSize];
		for (int x = 0; x < mSize; x++) {
			res[x] = mData[x];
		}
		return res;
    }
	
	public int indexOf(String i) {
		for (int x = 0; x < mSize; x++) {
			if (mData[x].equals(i)) return x;
		}
		return -1;
	}
	
	public int lastIndexOf(String i) {
		for (int x = mSize - 1; x > -1; x--) {
			if (mData[x].equals(i)) return x;
		}
		return -1;
	}
    
    public void trimToSize() {
		mData = toArray();
    }
    
    public void clear() { mSize = 0; }
    
    public boolean isEmpty() { return mSize == 0; }
	
    public String get(int index) {
		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException("" + index);
		return mData[index];
    }
	
    public int size(){ return mSize; }
	public int capacity(){ return mData.length; }
    
    public static void main(String[] args) {
		SuperArray s = new SuperArray();
		SuperArray t = new SuperArray();
		SuperArray q = new SuperArray();
		String f;
		
		for (int x = 0; x < 10; x++) {
			s.add(""+x);
			q.add(""+x);
		}
		//Standard created new array
		System.out.println(s.toStringDebug());
		System.out.println(t.toStringDebug());
		System.out.println(q.toStringDebug());
		
		System.out.println("addition");
		s.add(0, "1");
		System.out.println(s.toStringDebug());
		s.add(s.size() - 1, "-67");	//Remember this shifts things
		System.out.println(s.toStringDebug());
		s.add("0");
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
		System.out.println(s.size());
		System.out.println(s.capacity());
		f = s.remove(s.size() - 1);
		System.out.println(s.toStringDebug()+" rem "+f);
		s.remove(4);
		System.out.println(s.toStringDebug());
		
		System.out.println("setting");
		f = s.set(1, "29");
		System.out.println(s.toStringDebug()+" replaced " + f);
		f = s.set(0, "12345678");
		System.out.println(s.toStringDebug()+" replaced "+f);
		f = s.set(s.size() - 1, "90");
		System.out.println(s.toStringDebug()+" replaced "+f);
		
		System.out.println("toArray");
		s.add("2");
		String[] test = s.toArray();
		String[] test1 = new SuperArray().toArray();
		for (String x : test) System.out.print(x + " ");
		System.out.println();
		for (String x : test1) System.out.print(x + " ");
		System.out.println();
		
		System.out.println("testing indexOf");
		System.out.println(s.toStringDebug());
		System.out.println(s.indexOf("2"));		//2
		System.out.println(s.indexOf("89"));		//-1
		System.out.println(s.indexOf("5"));		//4
		System.out.println(s.lastIndexOf("8"));	//2
		System.out.println(s.lastIndexOf("234"));	//-1
		System.out.println(s.lastIndexOf("2"));	//7
		
		System.out.println("string debug");
		t.add("7");
		t.trimToSize();
		System.out.println(t.toStringDebug());
		System.out.println(t.size());
		
		System.out.println("clear");
		t.clear();
		t.add("4");
		System.out.println(t.toStringDebug());
		t.clear();
		System.out.println(t.toStringDebug());
		t.add("6");
		t.add("4");
		System.out.println(t.toStringDebug());
		
		System.out.println("exceptions");
		SuperArray o;
		//o = new SuperArray(-1);
		o = new SuperArray(5);
		//o.get(-1);
		//o.get(0);
		//o.get(10);
		o.add("2");
		o.add("1");
		//o.set(-1, 5);
		//o.set(5, 4);
		//o.set(9, 0);
		//o.add(-1, 5);
		o.add(2, "4");//legal
		//o.add(6, 0);
		//o.add(10, 5);
		//o.remove(-1);
		//o.remove(3);
		//o.remove(10);
		
		
    }
}
