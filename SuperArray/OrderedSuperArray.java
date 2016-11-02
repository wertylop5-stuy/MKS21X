public class OrderedSuperArray extends SuperArray {
    public OrderedSuperArray() {}
    public OrderedSuperArray(int capacity) { super(capacity); }
    public OrderedSuperArray(int[] ar) {
	for (int x : ar) {
	    add(ar[x]);
	}
    }
    
    public int set(int index, int n) {
	throw new UnsupportedOperationException("Cannot set an ordered array");
    }
    
    public void add(int index, int n) {
	throw new UnsupportedOperationException("Cannot modify ordered array");	
    }
    
    public boolean add(int n) {
	super.add(findPlace(n), n);
	return true;
    }
    
    //some sort of binary serach algorithm
    private int findPlace(int i, int start, int end) {
	
	
	return -1;
    }
    
    public static void main(String[] args) {
	OrderedSuperArray a = new OrderedSuperArray(3);
	System.out.println(a.size());
	//a.set(0, 1);
	a.add(1, 1);
    }
}
