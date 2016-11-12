public class ArrayMethods {
	//sum of values in row x
	public static int rowSum(int[][] ar, int x) {
		int sum = 0;
		for (int a : ar[x]) {
			sum += a;
		}
		return sum;
	}
	
	//sum of values in column x
	public static int columnSum(int[][] ar, int x){
		int sum = 0;
		for (int a = 0; a < ar.length; a++) {
			if (!(x >= ar[a].length)) {
				sum += ar[a][x];
			}
		}
		return sum;
	}
	
	//sum of all rows stored in array
	public static int[] allRowSums(int[][] ar){
		int[] res = new int[ar.length];
		for (int x = 0; x < ar.length; x++) {
			res[x] = rowSum(ar, x);
		}
		return res;
	}
	
	private static int[] doubleArray(int[] a) {
		int[] res = new int[2*a.length];
		System.arraycopy(a, 0, res, 0, a.length);
		return res;
	}
	//sum of all rows stored in array
	/*
	start at column 0, go indefinite
		go through each row
			if current column outside each rows length, end execution
			if array too small, double size
			add value to sum
		add value to array
	
		
	*/
	public static int[] allColumnSums(int[][] ar){
		int[] res = new int[ar.length];
		boolean hasBeenAccessed;
		int currentSum;
		
		for(int y = 0;; y++) {
			currentSum = 0;
			hasBeenAccessed = false;
			for (int x = 0; x < ar.length; x++) {
				if (y >= ar[x].length) continue;
				//System.out.println(ar[x][y]);
				
				if (y >= res.length) {
					res = doubleArray(res);
				}
				
				hasBeenAccessed = true;
				currentSum += ar[x][y];
			}
			if (!hasBeenAccessed) return res;
			else res[y] = currentSum;
			
		}
		//return res;
	}
	
	//return if all rows have same sum
	public static boolean isRowMagic(int[][] ar){
		int[] res = allRowSums(ar);
		for (int x = 0; x < res.length - 1; x++) {
			if (!(res[x] == res[x+1])) return false;
		}
		return true;
	}
	
	//return if all columns have same sum
	public static boolean isColumnMagic(int[][] ar){
		int[] res = allColumnSums(ar);
		for (int x = 0; x < res.length - 1; x++) {
			if (!(res[x] == res[x+1])) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		int[][] a = {
			{10	, 20, 20},
			{1	, 7	, 4, 0},
			{0	, 0	, 0, 0},
			{-1	, 1}
		};
		int[][] b = {{}};
		int[][] c = {{1}, {-1, 1, 1}};
		System.out.println("rowSum");
		System.out.println(rowSum(a, 0));
		System.out.println(rowSum(a, 1));
		System.out.println(rowSum(a, 2));
		System.out.println(rowSum(a, 3));
		
		System.out.println("columnSum");
		System.out.println(columnSum(a, 0));
		System.out.println(columnSum(a, 1));
		System.out.println(columnSum(a, 2));
		System.out.println(columnSum(a, 3));
		
		System.out.println("emptySums");
		System.out.println(columnSum(b, 0));
		System.out.println(rowSum(b, 0));
		
		System.out.println("allRowSums");
		for (int x : allRowSums(a)) {
			System.out.print(x + " ");
		}
		System.out.println();
		for (int x : allRowSums(b)) {
			System.out.print(x + " ");
		}
		System.out.println();
		
		System.out.println("isRowMagic");
		System.out.println(isRowMagic(a));
		System.out.println(isRowMagic(b));
		System.out.println(isRowMagic(c));
		
		System.out.println("allColumnSums");
		for (int x : allColumnSums(a)) {
			System.out.print(x + " ");
		}
	}
}