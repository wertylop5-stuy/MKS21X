public class CirculatingBook
		extends LibraryBook {
	private String mCurrentHolder;
	private String mDueDate;
	
	public CirculatingBook(String author, String title,
				String isbn, String callNumber) {
		super(author, title, isbn, callNumber);
		mCurrentHolder = mDueDate = null;
	}
	
	@Override
	public void checkout(String patron, String due) {
		mCurrentHolder = patron;
		mDueDate = due;
	}
	
	@Override
	public void returned() {
		mCurrentHolder = mDueDate = null;
	}
	
	@Override
	public String circulationStatus() {
		if (mCurrentHolder == null) {
			return "Book available on shelves";
		}
		return mCurrentHolder + "," + mDueDate;
	}
	
	public String getCurrentHolder() {return mCurrentHolder;}
	public void setCurrentHolder(String currentHolder) {
		mCurrentHolder = currentHolder;
	}
	
	public String getDueDate() {return mDueDate;}
	public void setDueDate(String dueDate) {
		mDueDate = dueDate;
	}
}
