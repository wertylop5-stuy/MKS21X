  /** 
Precondition:
the puzzle grid contains the distinct values 0 through side^2
   *  @return true 
		if the tiles in the puzzle are all arranged in increasing order   
		
		(the hole value 0 may be in any position);
   *    false otherwise
   */ 

public class DoneGoofed {
	//side is 4
	public static boolean isDone1(int[][] values) {
		int counter = 0;
		boolean foundZero = false;
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (values[x][y] == 0 && !foundZero) foundZero = true;
				else if (values[x][y] == counter+1) counter++;
				else return false;
			}
		}
		return true;
	}
	
	/*
	looks if the element ahead is equal to the current element+1
		how you think you'll do it
	if end of line, check if first element in next row is current+1
		
	*/
	public static boolean isDone2(int[][] values) {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				System.out.print(values[x][y] + " ");
				System.out.println(values[x+y/3][y+1%3]);
				System.out.println(x + " " + (y) + " indexes");
				System.out.println(x+y/3 + " " + (y+1%3) + " indexes2");
				if (!(values[x][y] == values[x+y/3][y+1%3]+1 || 
					values[x][y] * values[x+y/4][y+1%4] == 0)) return false;
			}
		}
		return true;
	}
	
	public static boolean isDone3(int[][] values) {
		//counter / 4 + x.
		//x < 4
		/*for (int x = 0; x < 16; x++) {
			if (values[x/4][x%4] == ) 
		}*/
		return true;
	}
	
	public static int[][] randomAr() {
		return new int[][]
		{
			{0, 4, 7, 2},
			{1, 3, 9, 10},
			{11, 5, 15, 13},
			{12, 14, 6, 8}
		};
	}
	
	public static int[][] orderedAr() {
		return new int[][]
		{
			{0, 1, 2, 3},
			{4, 5, 6, 7},
			{8, 9, 10, 11},
			{12, 13, 14, 15}
		};
	}
	
	public static void main(String[] args) {
		int[][] val1 = randomAr();
		System.out.println(isDone1(val1));	//false
		System.out.println(isDone2(val1));	//false
		val1 = orderedAr();
		System.out.println(isDone1(val1));	//true
		System.out.println(isDone2(val1));	//true
	}
}
