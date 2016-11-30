public class Book {
    private String mAuthor;
    private String mTitle;
    private String mIsbn;
    
    public Book() {}
    public Book(String author, String title, String isbn) {
		mAuthor = author;
		mTitle = title;
		mIsbn = isbn;
    }
    
	@Override
	public String toString() {
		return mTitle + "," + mAuthor + "," + mIsbn;
	}
	
    public String getAuthor(){ return mAuthor;}
    public void setAuthor(String author) { mAuthor = author;}
    
    public String getTitle() {return mTitle;}
    public void setTitle(String title) {mTitle = title;}
    
    public String getIsbn() {return mIsbn;}
    public void setIsbn(String isbn) {mIsbn = isbn;}
	
	public static void main(String[] args) {
		LibraryBook l = new ReferenceBook("Bill", "yougrt", "45124566");
	}
}
