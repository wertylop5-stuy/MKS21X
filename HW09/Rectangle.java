public class Rectangle extends Shape {
	private double mLength;
	private double mWidth;
	
	public Rectangle() {mLength = mWidth = 1.0;}
	public Rectangle(double wid, double len) {
		setLength(len);
		setWidth(wid);
	}
	
	@Override
	public String toString() {
		return "A Rectangle with width=" + mWidth +
		" and length=" + mLength +
		", which is a subclass of " + super.toString();
	}
	
	@Override
	public double getArea() {
		return mLength * mWidth;
	}
	
	@Override
	public double getPerimeter() {
		return mLength * 2 + mWidth * 2;
	}
	
	public double getLength(){return mLength;}
	public double getWidth(){return mWidth;}
	
	public void setLength(double len) {
		mLength = len < 0 ? 0.0 : len;
	}
	public void setWidth(double wid){
		mWidth = wid < 0 ? 0.0 : wid;
	}
	
	public static void main(String[] args) {
		Rectangle c = new Rectangle(2.0, 3.0);
		System.out.println(c.getArea());		//6
		System.out.println(c.getPerimeter());	//10
		
		Rectangle t = new Rectangle();
		System.out.println(t.getArea());		//1
		System.out.println(t.getPerimeter());	//4
		System.out.println(t);
	}
}