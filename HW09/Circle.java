public class Circle extends Shape{
	private double mRadius;
	
	public Circle(){mRadius = 1.0;}
	
	public Circle(double radius) {
		setRadius(radius);
	}
	
	@Override
	public double getArea() {
		return Math.PI * Math.pow(mRadius, 2);
	}
	
	/**
	* Returns the circumference
	*/
	@Override
	public double getPerimeter() {
		return Math.PI * mRadius * 2;
	}
	
	/**
	* Returns radius
	*/
	public double getRadius(){return mRadius;}
	
	/**
	* Checks if radius is less than zero.
	* If it is, radius defaults to zero
	*/
	public void setRadius(double radius) {
		mRadius = radius < 0.0 ? 0.0 : radius;
	}
	
	@Override
	public String toString() {
		return "A Circle with radius=" + mRadius +
				", which is a subclass of " + super.toString();
	}
	
	public static void main(String[] args) {
		Circle c = new Circle(2.0);
		System.out.println(c.getArea());		//4PI
		System.out.println(c.getPerimeter());	//4PI
		
		Circle t = new Circle();
		System.out.println(t.getArea());		//PI
		System.out.println(t.getPerimeter());	//2PI
		System.out.println(t);
	}
}