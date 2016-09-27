public class Driver {
    public static void main(String args[]) {
	Point a = new Point();
	a.setX(1);
	a.setY(0);
	Point b = new Point(a);
	b.setX(0);

	System.out.println("a: " + a.getX() + " b: " + b.getX());
	a.distance(b);
	System.out.println(a.distance(b));
	
	Triangle t = new Triangle(a, b, new Point(0, 5));
	System.out.println("Perim: " + t.getPerimeter());
	System.out.println("Area: " + t.getArea());
    }
}
