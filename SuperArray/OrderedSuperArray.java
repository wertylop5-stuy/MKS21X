public class OrderedSuperArray extends SuperArray {
    public OrderedSuperArray() {}
    public OrderedSuperArray(int capacity) { super(capacity); }
    public OrderedSuperArray(int[] ar) {
		for (int x : ar) {
			add(x);
			//super.add(x);
		}
    }
    
	@Override
    public int set(int index, int n) {
		throw new UnsupportedOperationException("Cannot set an ordered array");
    }
    
	@Override
    public void add(int index, int n) {
		throw new UnsupportedOperationException("Cannot modify ordered array");	
    }
    
	@Override
    public boolean add(int n) {
		super.add(findPlace(n, 0, size()), n);
		return true;
    }
    
    //some sort of binary serach algorithm
	//Use with add
    private int findPlace(int i, int start, int end) {
		//System.out.println("start: " + start + "\nend: " + end);
		if (start == end) {
			if (start == size()) return start;
			return (i < get(start) ? start : start + 1); 
		}
		int mid = (start + (end - 1)) / 2;
		//System.out.println("mid: " + mid);
		
		int tmp = get(mid);
		//System.out.println("val at mid: " + tmp);
		
		if (i == tmp) return mid;
		if (i < tmp) return findPlace(i, start, mid);
		else if (i > tmp) return findPlace(i, mid + 1, end);
		
		return -1;
    }
	
	@Override
	public int indexOf(int n) {
		return findPlace(n, 0, size(), 1);
	}
	
	@Override
	public int lastIndexOf(int n) {
		return findPlace(n, 0, size(), -1);
	}
	
	//Use with indexOf
	//positive direction is forward, neg is backward
    private int findPlace(int i, int start, int end, int direction) {
		//System.out.println("start: " + start + "\nend: " + end);
		if (start == end) {
			return start;
		}
		int mid = (start + (end - 1)) / 2;
		//System.out.println("mid: " + mid);
		
		int tmp = get(mid);
		//System.out.println("val at mid: " + tmp);
		
		if (i == tmp) {
			int res = mid;
			if (direction > 0) {
				while(mid >= start && get(mid) == i) res = mid--;
			}
			else {
				while(mid < end && get(mid) == i) res = mid++;
			}
			return res;
		}
		if (i < tmp) return findPlace(i, start, mid, direction);
		else if (i > tmp) return findPlace(i, mid + 1, end, direction);
		
		return -1;
    }
    
    public static void main(String[] args) {
		OrderedSuperArray a = new OrderedSuperArray(3);
		//System.out.println(a.size());
		//a.set(0, 1);
		//a.add(1, 1);
		int[] t = {0, 2, 3, 4, 5, 6, 7, 8};
		OrderedSuperArray b = new OrderedSuperArray(t);
		System.out.println(b.toStringDebug());
		b.add(1);
		System.out.println(b.toStringDebug());
		b.add(20);
		System.out.println(b.toStringDebug());
		b.add(0);
		System.out.println(b.toStringDebug());
		b.add(0);
		System.out.println(b.toStringDebug());
		
		System.out.println(b.indexOf(0) + "\n");//0
		System.out.println(b.indexOf(1) + "\n");//3
		System.out.println(b.lastIndexOf(0) + "\n");//2
		System.out.println(b.lastIndexOf(1) + "\n");//3
		
		int[] y = {-1, 0, 1, 2, 2, 2, 2, 6, 8, 8, 8, 8, 9};
		b = new OrderedSuperArray(y);
		System.out.println(b.indexOf(2) + "\n");//3
		System.out.println(b.indexOf(8) + "\n");//8
		System.out.println(b.lastIndexOf(2) + "\n");//6
		System.out.println(b.lastIndexOf(8) + "\n");//11
		/*
		System.out.println(b.findPlace(1, 0, b.size()) + "\n");//1
		System.out.println(b.findPlace(4, 0, b.size()) + "\n");//3
		System.out.println(b.findPlace(20, 0, b.size()) + "\n");//8
		System.out.println(b.findPlace(-1, 0, b.size()) + "\n");//0
		System.out.println(b.findPlace(0, 0, b.size()) + "\n");//0
		System.out.println(b.findPlace(8, 0, b.size()) + "\n");//7
		System.out.println(b.findPlace(6, 0, b.size()) + "\n");//5
		*/
		//int[] s = {-21, -3, -1, 0, 4, 9, 37, 155, 674, 1000};
		//b = new OrderedSuperArray(s);
		/*
		System.out.println(b.findPlace(-21, 0, b.size()) + "\n");//0
		System.out.println(b.findPlace(1000, 0, b.size()) + "\n");//9
		System.out.println(b.findPlace(1001, 0, b.size()) + "\n");//10
		System.out.println(b.findPlace(999, 0, b.size()) + "\n");//9
		System.out.println(b.findPlace(756, 0, b.size()) + "\n");//9
		System.out.println(b.findPlace(0, 0, b.size()) + "\n");//3
		System.out.println(b.findPlace(-10, 0, b.size()) + "\n");//1
		System.out.println(b.findPlace(-67, 0, b.size()) + "\n");//0
		System.out.println(b.findPlace(-20, 0, b.size()) + "\n");//1
		System.out.println(b.findPlace(11, 0, b.size()) + "\n");//6
		*/
    }
}
