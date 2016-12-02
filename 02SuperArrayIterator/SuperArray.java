import java.util.Iterator;

public class SuperArray 
		implements Iterable<String> {
    private String[] mData;
    private int mSize;
	
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
    
    @Override
    public Iterator<String> iterator() {
    	return new Iterator<String>() {
    		private SuperArray mArray = SuperArray.this;
    		private int mCurPos = 0;
    		
    		@Override
    		public boolean hasNext() {
    			return mCurPos < mSize;
    		}
    		
    		@Override
    		public String next() {
    			return mData[mCurPos++];
    		}
    		
    		@Override
    		public void remove() {
    			throw new UnsupportedOperationException("Cannot ");
    		}
    	};
    }
    
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
    
    public boolean add(String n){
		if (mSize == mData.length) {
			grow();
		}
		mData[mSize++] = n;
		return true;
    }
	
    public void add(int index, String n) {
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
	
	@Override
    public String toString(){
		String res = "[";
		for (int x = 0; x < mSize; x++) {
			res += mData[x];
			if (x != mSize - 1) res += ", ";
		}
		return res + "]";
    }
    
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
}
