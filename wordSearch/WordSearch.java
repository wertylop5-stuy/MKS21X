public class WordSearch{
    private char[][]mData;

    /**Initialize the grid to the size specified 

     *fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public WordSearch(int rows,int cols){
    	if (rows <= 0) {
    		throw new IllegalArgumentException("zero or negative row: " + rows);
    	}
    	if (cols <= 0) {
    		throw new IllegalArgumentException("zero or negative col: " + cols);
    	}
    	
    	mData = new char[rows][cols];
    	clear();
    }

    /**Set all values in the WordSearch to underscores'_'*/
    private void clear(){
    	for (int x = 0; x < mData.length; x++) {
    		for (int y = 0; y < mData[x].length; y++) {
    			mData[x][y] = '_';
    		}
    	}
    }

    /**The proper formatting for a WordGrid is created in the toString.
     *@return a String with each character separated by spaces, and rows
     *separated by newlines.
     */
    public String toString(){
    	String res = "";
    	for (int x = 0; x < mData.length; x++) {
    		for (int y = 0; y < mData[x].length; y++) {
    			res += "" + mData[x][y];
    			if (y < mData[x].length - 1) res += " ";
    		}
    		res += "\n";
    	}
    	return res;
    }

	/*
	Start at row, col
	if (ar.len - startIndex) < word.len return false
	if underscore
		increment counter
	else if letter
		if letter of current space == current letter in word
			increment counter
		else return false
	if counter reaches end of word
		start replacing letters/underscores with the word
	
	if false first time, run again with reverse string
	else return false
	*/
    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from left to right, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     */
     //modfiy to one function
     //chekc direction, set a variable to change thee direction every time it moves
     //ex: delta row and delta col
    public boolean addWordHorizontal(String word,int row, int col){
    	int counter;
    	int tRow, tCol; 
    	String runs = word;
    	for (	int turn = 0;
    			turn < 2;
    			runs = new StringBuilder(word).reverse().toString(), turn++) {
    		//System.out.println("current runs: " + runs);
    		if ((mData[row].length - col) < runs.length()) continue;
    		//using an exception would be better for this (more versatile
    		
    		tRow = row;
    		tCol = col;
    		counter = 0;
    		for (counter = 0; counter < runs.length(); counter++) {
				if (mData[tRow][tCol] != '_' && 
    				mData[tRow][tCol] != runs.charAt(counter)) {
    				//System.out.println("grid: " + mData[tRow][tCol]);
    				//System.out.println("runs: " + runs.charAt(counter));
    				break;
    			}
    			tCol++;
			}
    		
    		if (counter == runs.length()) {
    			for (counter = 0; counter < runs.length(); counter++) {
    				mData[row][col] = runs.charAt(counter);
    				col++;
    			}
    			return true;
    		}
    		else continue;
    	}
    	return false;
    }


   /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from top to bottom, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     */
    public boolean addWordVertical(String word,int row, int col){
    	int counter;
    	int tRow, tCol; 
    	String runs = word;
    	for (	int turn = 0;
    			turn < 2;
    			runs = new StringBuilder(word).reverse().toString(), turn++) {
    		//System.out.println("current runs: " + runs);
    		if ((mData.length - row) < runs.length()) continue;
    		
    		tRow = row;
    		tCol = col;
    		counter = 0;
    		for (counter = 0; counter < runs.length(); counter++) {
				if (mData[tRow][tCol] != '_' && 
    				mData[tRow][tCol] != runs.charAt(counter)) {
    				//System.out.println("grid: " + mData[tRow][tCol]);
    				//System.out.println("runs: " + runs.charAt(counter));
    				break;
    			}
    			tRow++;
			}
    		
    		if (counter == runs.length()) {
    			for (counter = 0; counter < runs.length(); counter++) {
    				mData[row][col] = runs.charAt(counter);
    				row++;
    			}
    			return true;
    		}
    		else continue;
    	}
    	return false;
    }
	
	
	public static void main(String[] args) {
		WordSearch w = new WordSearch(3, 3);
		//System.out.println(w);
		//w.addWordHorizontal("abc", 0, 0);
		//w.addWordHorizontal("abc", 2, 0);
		//w.addWordHorizontal("a", 1, 2);
		//System.out.println(w);
		
		WordSearch b = new WordSearch(10, 20);
		//b.addWordHorizontal("abc", 1, 0);
		//b.addWordHorizontal("cfg", 1, 2);
		//b.addWordHorizontal("eta", 1, 4);
		//b.addWordHorizontal("rty", 1, 5);
		//b.addWordHorizontal("caer", 1, 2);
		//b.addWordVertical("abc", 1, 0);
		b.addWordVertical("abc", 0, 1);
		b.addWordVertical("abcdefghij", 0, 2);
		b.addWordVertical("cp", 2, 1);
		b.addWordVertical("bcpg", 1, 1);
		b.addWordVertical("yut", 6, 1);
		b.addWordVertical("guy", 4, 1);
		b.addWordHorizontal("aaa", 0, 0);
		b.addWordHorizontal("fug", 5, 0);
		b.addWordHorizontal("appleton", 3, 6);
		b.addWordHorizontal("fried", 6, 6);
		b.addWordVertical("latex", 3, 9);
		b.addWordVertical("rapport", 0, 7);
		b.addWordHorizontal("extra", 7, 6);
		b.addWordHorizontal("nigeria", 3, 13);
		
		//ones that wont work
		b.addWordVertical("pixie", 7, 8);		
		b.addWordHorizontal("dragon", 1, 5);
		b.addWordHorizontal("triskaidekaphobia", 1, 5);
		b.addWordVertical("triskaidekaphobia", 1, 5);
		
		System.out.println(b);
	}
}

