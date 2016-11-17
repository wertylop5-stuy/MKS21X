public class Driver {
	public static void main(String[] args) {
		WordSearch w = new WordSearch(10, 10, "words.txt");
		w.printWordsToAdd();
	}
}