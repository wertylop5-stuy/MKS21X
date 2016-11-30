public class CirculatingBook
		extends LibraryBook {
	private String mCurrentHolder;
	private String mDueDate;
	
	public CirculatingBook(String author, String title,
				String isbn, String callNumber) {
		mCurrentHolder = mDueDate = null;
	}
	
	@Override
	public checkout(String patron, String due) {
		mCurrentHolder = patron;
		mDueDate = due;
	}
	
	@Override
	public String circulationStatus("") {
		
	}
	
	public String getCurrentHolder() {return mCurrentHolder;}
	public void setCurrentHolder(String currentHolder) {
		mCurrentHolder = currentHolder;
	}
	
	public String getDueDate() {return mDueDate;}
	public void setDueDate(String dueDate) {
		mDueDate - dueDate;
	}
}