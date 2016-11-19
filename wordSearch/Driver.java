public class Driver {
	private static final String USAGE_STRING = 
		Driver.class.getSimpleName() + 
		" Usage: java " + Driver.class.getSimpleName() + 
		" [rows cols filename [randomSeed [answers]]]";
	
	public static void main(String[] args) {
		/*WordSearch w = new WordSearch(7, 7, "words.txt");
		//System.out.println("words loaded");
		//w.printWordsToAdd();
		//w.fillWithWords();
		System.out.println("words to add");
		w.printWordsToAdd();
		System.out.println("words added");
		w.printAddedWords();
		System.out.println(w);*/
		
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
			if (!args[4].equals("key")) {
				System.err.println(
					Driver.class.getSimpleName() +
					": argument answers must have value \"key\", returning full board"
				);
			}
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
		}
		
		System.out.println("seed: " + w.getSeed());
		System.out.println(w);
	}
}