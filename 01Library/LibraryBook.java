public abstract class LibraryBook<T extends LibraryBook>
    extends Book
    implements Comparable<T> {
    private String mCallNumber;
    
    LibraryBook(String author, String title,
				String isbn, String callNumber) {
		super(author, title, isbn);
		mCallNumber = callNumber;
    }
    
    abstract void checkout(String patron, String due);
    abstract void returned();
    abstract String circulationStatus();
    
	@Override
    public int compareTo(LibraryBook b) {
		return mCallNumber.compareTo(b.getCallNumber());
	}
	
	@Override
	public String toString() {
		return super.toString() + "," + mCallNumber +
			circulationStatus();
	}
	
    public String getCallNumber() {return mCallNumber;}
    public void setCallNumber(String callNumber){
		mCallNumber = callNumber;
	}
}
