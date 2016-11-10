public abstract class Shape {
	public abstract double getArea();
	public abstract double getPerimeter();
	
	public double getSemiPerimeter(){return getPerimeter() / 2.0;}
	
	@Override
	public String toString(){return "Shape";}
	
	public static void main(String[] args) {
		Shape s = new Shape(){
			public double getArea(){return 5.0;}
			public double getPerimeter(){return 9.0;}
		};
		
		System.out.println(s.getArea());
	}
}