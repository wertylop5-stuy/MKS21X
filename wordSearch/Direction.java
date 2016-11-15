public enum Direction {
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
	
	Direction(int deltaX, int deltaY) {
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
