import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

public class WordSearch{
    private char[][]mData;
	
	private List<String> mWordsToAdd;
	private List<String> mWordsAdded;
	
	private Random mRandgen;
	
	private String mDataFile;

    /**Initialize the grid to the size specified 

     *fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public WordSearch(int rows, int cols, String filename){
    	if (rows <= 0) {
    		throw new IllegalArgumentException("zero or negative row: " + rows);
    	}
    	if (cols <= 0) {
    		throw new IllegalArgumentException("zero or negative col: " + cols);
    	}
    	
    	mData = new char[rows][cols];
		mWordsToAdd = new ArrayList<>();
		mWordsAdded = new ArrayList<>();
		mRandgen = new Random();
		
    	clear();
		try {
			loadWords(filename);
		}
		catch (FileNotFoundException e) {
			System.err.println("File " + filename + " does not exist!");
			System.exit(1);
		}
		
		fillWithWords();
    }
	
	public WordSearch(int rows, int cols, String filename, int seed) {
		if (rows <= 0) {
    		throw new IllegalArgumentException("zero or negative row: " + rows);
    	}
    	if (cols <= 0) {
    		throw new IllegalArgumentException("zero or negative col: " + cols);
    	}
    	
    	mData = new char[rows][cols];
		mWordsToAdd = new ArrayList<>();
		mWordsAdded = new ArrayList<>();
		mRandgen = new Random(seed);
		
    	clear();
		try {
			loadWords(filename);
		}
		catch (FileNotFoundException e) {
			System.err.println("File " + filename + " does not exist!");
			System.exit(1);
		}
		
		fillWithWords();
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
	
	//Aim for 4 different pos with each word with 3 directions each
	public void fillWithWords() {
		Direction d;
		int dirLen = Direction.values().length;
		int row, col;
		for (String s : mWordsToAdd) {
			row = mRandgen.nextInt() % mData.length;
			col = mRandgen.nextInt() % mData[row].length;
			
			for (int runs = 0; runs < 4; runs++) {
				d = Direction.values()[mRandgen.nextInt() % dirLen];
				if (addSingleWord(s, row, col, d)) {
					mWordsAdded.add(mWordsToAdd.remove(s));
					break;
				}
			}
		}
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
     *The word is added according to the direction parameter and will attempt
	 *to comply with existing letters. The method will also reverse the word
	 *to see if it fits as well
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
	 *@param direction the direction the word will be added
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     */
	public boolean addSingleWord(String word,int row, int col, Direction direction){
    	int counter;
    	int tRow, tCol; 
    	String runs = word;
    	for (	int turn = 0;
    			turn < 2;
    			runs = new StringBuilder(word).reverse().toString(), turn++) {
    		//System.out.println("current runs: " + runs);
    		if ((mData[row].length - col) < runs.length()) continue;
    		//using an exception would be better for this (more versatile
    		
			try {
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
					tRow += direction.getDeltaY();
					tCol += direction.getDeltaX();
				}
			}
			catch(ArrayIndexOutOfBoundsException e) {
				return false;
			}
    		
    		if (counter == runs.length()) {
    			for (counter = 0; counter < runs.length(); counter++) {
    				mData[row][col] = runs.charAt(counter);
    				col += direction.getDeltaX();
					row += direction.getDeltaY();
    			}
    			return true;
    		}
    		else continue;
    	}
    	return false;
    }
	
	public void loadWords(String filename)
		throws FileNotFoundException {
		Scanner in = new Scanner(new File(filename));
		while (in.hasNext()) {
			mWordsToAdd.add(in.next());
		}
	}
	
	public void printWordsToAdd() {
		for (String s : mWordsToAdd) {
			System.out.println(s);
		}
	}
	public void printAddedWords() {
		for (String s : mWordsAdded) {
			System.out.println(s);
		}
	}
	
	public static void main(String[] args) {
		//WordSearch w = new WordSearch(3, 3);
		//System.out.println(w);
		//w.addSingleWord("abc", 0, 0, Direction.NORTHEAST);
		//w.addSingleWord("abc", 2, 0);
		/*for (Direction d : Direction.values()) {
			w.addSingleWord("ab", 1, 1, d);
		}*/
		//w.addSingleWord("ab", 1, 1, Direction.NORTHEAST);
		//System.out.println(w);
		
		//WordSearch b = new WordSearch(10, 20);
		/*b.addSingleWord("abc", 1, 0, Direction.EAST);
		b.addSingleWord("cfg", 1, 2, Direction.EAST);
		b.addSingleWord("eta", 1, 4, Direction.WEST);
		b.addSingleWord("rty", 1, 5, Direction.SOUTHWEST);
		b.addSingleWord("caer", 1, 2, Direction.);
		b.addSingleWord("abc", 1, 0, Direction.);*/
		
		/*b.addSingleWord("abc", 0, 1);
		b.addSingleWord("abcdefghij", 0, 2);
		b.addSingleWord("cp", 2, 1);
		b.addSingleWord("bcpg", 1, 1);
		b.addSingleWord("yut", 6, 1);
		b.addSingleWord("guy", 4, 1);
		b.addSingleWord("aaa", 0, 0);
		b.addSingleWord("fug", 5, 0);
		b.addSingleWord("appleton", 3, 6, Direction.EAST);
		b.addSingleWord("fried", 6, 6, Direction.EAST);
		b.addSingleWord("latex", 3, 9, Direction.SOUTH);
		b.addSingleWord("rapport", 0, 7, Direction.SOUTH);
		b.addSingleWord("extra", 7, 6, Direction.EAST);
		b.addSingleWord("nigeria", 3, 13, Direction.EAST);
		b.addSingleWord("falling", 6, 6, Direction.NORTHEAST);
		b.addSingleWord("rate", 9, 4, Direction.NORTHEAST);*/
		
		//ones that wont work
		/*b.addSingleWord("pixie", 7, 8, Direction.);		
		b.addSingleWord("dragon", 1, 5, Direction.);
		b.addSingleWord("triskaidekaphobia", 1, 5, Direction.);
		b.addSingleWord("triskaidekaphobia", 1, 5, Direction.);*/
		
		//System.out.println(b);
		
		/*try {
			Scanner in = new Scanner(new File("words.txt"));
			while (in.hasNext()) {
				System.out.println(in.nextLine());
			}
		}
		catch (FileNotFoundException e) {
			
		}
		*/
	}
}

