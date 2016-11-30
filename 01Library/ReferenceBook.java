public class ReferenceBook
		extends LibraryBook {
	private String mCollection;
	
	public ReferenceBook(String author, String title,
			String isbn, String callNumber,
			String collection) {
		super(author, title, isbn, callNumber);
		mCollection = collection;
	}
	
	@Override
	public void checkout(String patron, String due) {
		System.err.println(
			"Cannot check out a reference book");
	}
	
	@Override
	public void returned() {
		System.err.println(
			"Impossible to return a reference book");
	}
	
	@Override
	public String circulationStatus() {
		return "non-circulating reference book";
	}
	
	@Override
	public String toString() {
		return super.toString() + "," + mCollection;
	}
	
	public String getCollection() {return mCollection;}
	public void setCollection(String collection) {
		mCollection = collection;
	}
}