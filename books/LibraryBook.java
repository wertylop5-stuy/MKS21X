public abstract class LibraryBook<T>
    extends Book
    implements Comparable<T> {
    private String mCallNumber;
    
    LibraryBook(String author, String title, String isbn, String callNumber) {
	super(author, title, isbn);
	mCallNumber = callNumber;
    }
    
    abstract void checkout();
    abstract void return();
    abstract void circulationStatus();
    
    public int compareTo(
    
    public String getCallNumber() {return mCallNumber;}
    public void setCallNumber(String callNumber) {mCallNumber = callNumber;}
}
