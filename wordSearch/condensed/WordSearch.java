import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

enum Direction {
	//Format (y, x)
	NORTH(-1, 0),
	NORTHEAST(-1, 1),
	EAST(0, 1), 
	SOUTHEAST(1, 1),
	SOUTH(1, 0),
	SOUTHWEST(1, -1),
	WEST(0, -1),
	NORTHWEST(-1, -1);
	
	private int mDeltaX;
	private int mDeltaY;
	
	Direction(int deltaY, int deltaX) {
		mDeltaX = deltaX;
		mDeltaY = deltaY;
	}
	
	public int getDeltaX() {
		return mDeltaX;
	}
	
	public int getDeltaY() {
		return mDeltaY;
	}
}

public class WordSearch{
	private static final String USAGE_STRING = 
		WordSearch.class.getSimpleName() + 
		" Usage: java " + WordSearch.class.getSimpleName() + 
		" [rows cols filename [randomSeed [answers]]]";
	
    private char[][]mData;
	
	private List<String> mWordsToAdd;
	private List<String> mWordsAdded;
	
	private Random mRandgen;
	private String mDataFile;
	private int mSeed;

    /**Initialize the grid to the size specified 

     *fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public WordSearch(int rows, int cols, String filename){
    	this(rows, cols, filename, null);
    }
	
	public WordSearch(int rows, int cols,
			String filename, Integer seed) {
		this(rows, cols, filename, seed, false);
	}
	
	public WordSearch(int rows, int cols,
			String filename, Integer seed, boolean printKey) {
		if (rows <= 0) {
    		throw new IllegalArgumentException("zero or negative row: " + rows);
    	}
    	if (cols <= 0) {
    		throw new IllegalArgumentException("zero or negative col: " + cols);
    	}
    	
    	mData = new char[rows][cols];
		mWordsToAdd = new ArrayList<>();
		mWordsAdded = new ArrayList<>();
		
		if (seed != null) {
			mSeed = seed;
			mRandgen = new Random(seed);
		}
		else {
			mSeed = (int)(Math.random() * 100000);
			mRandgen = new Random(mSeed);
		}
		
    	clear();
		try {
			loadWords(filename);
		}
		catch (FileNotFoundException e) {
			System.err.println("File " + filename + " does not exist!");
			System.exit(1);
		}
		
		fillWithWords();
		if (!printKey) {
			fillInRemaining();
		}
	}

    /**Set all values in the WordSearch to underscores'_'*/
    private void clear(){
    	for (int x = 0; x < mData.length; x++) {
    		for (int y = 0; y < mData[x].length; y++) {
    			mData[x][y] = '_';
    		}
    	}
    }
	
	private void fillInRemaining() {
		for (int x = 0; x < mData.length; x++) {
			for (int y = 0; y < mData[x].length; y++) {
				if (mData[x][y] == '_') {
					mData[x][y] =
						(char)(mRandgen.nextInt('Z' - 'A' + 1) + 'A');
				}
			}
		}
	}
	
	//Aim for 2 different pos with each word with 4 directions each
	private void fillWithWords() {
		Direction d;
		int dirLen = Direction.values().length;
		int row = 0, col = 0;
		boolean hasAdded = false;
		
		//8 tries for each word
		int x = 0;
		while (x < mWordsToAdd.size()) {
			hasAdded = false;
			for (int runs = 0; runs < 8 && !hasAdded; runs++) {
				if (runs % 4 == 0) {
					row = mRandgen.nextInt(mData.length);
					col = mRandgen.nextInt(mData[row].length);
				}
				d = Direction.values()[mRandgen.nextInt(dirLen)];
				if (addSingleWord(mWordsToAdd.get(x), row, col, d)) {
					mWordsAdded.add(mWordsToAdd.remove(x));
					hasAdded = true;
				}
			}
			if (!hasAdded) {
				x++;
			}
		}
	}
	
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
	private boolean addSingleWord(String word,int row, int col, Direction direction){
    	int counter;
    	int tRow, tCol; 
    	String runs = word;
    	for (	int turn = 0;
    			turn < 2;
    			runs = new StringBuilder(word).reverse().toString(), turn++) {
    		if ((mData[row].length - col) < runs.length()) continue;
    		
			try {
				tRow = row;
				tCol = col;
				counter = 0;
				for (counter = 0; counter < runs.length(); counter++) {
					if (mData[tRow][tCol] != '_' && 
						mData[tRow][tCol] != runs.charAt(counter)) {
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
	
	private void loadWords(String filename)
		throws FileNotFoundException {
		Scanner in = new Scanner(new File(filename));
		while (in.hasNext()) {
			mWordsToAdd.add(in.next().toUpperCase());
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
	
	public int getSeed() {
		return mSeed;
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
	
	public static void main(String[] args) {
		WordSearch w = null;
		if (args.length < 3 || args.length > 5) {
			System.err.println(USAGE_STRING);
			System.exit(1);
		}
		else if (args.length == 3) {
			try {
				w = new WordSearch(
					Integer.parseInt(args[0]),
					Integer.parseInt(args[1]),
					args[2]
				);
			}
			catch (NumberFormatException e) {
				System.err.println(USAGE_STRING);
				System.exit(1);
			}
		}
		else if (args.length == 4) {
			try {
				w = new WordSearch(
					Integer.parseInt(args[0]),
					Integer.parseInt(args[1]),
					args[2],
					Integer.parseInt(args[3])
				);
			}
			catch (NumberFormatException e) {
				System.err.println(USAGE_STRING);
				System.exit(1);
			}
		}
		else if (args.length == 5) {
			try {
				w = new WordSearch(
					Integer.parseInt(args[0]),
					Integer.parseInt(args[1]),
					args[2],
					Integer.parseInt(args[3]),
					args[4].equals("key")
				);
			}
			catch (NumberFormatException e) {
				System.out.println(USAGE_STRING);
				System.exit(1);
			}
			if (!args[4].equals("key")) {
				System.err.println(
					WordSearch.class.getSimpleName() +
					": argument answers must have value \"key\", returning full board"
				);
			}
		}
		
		System.out.println("seed: " + w.getSeed());
		System.out.println(w);
	}
}

