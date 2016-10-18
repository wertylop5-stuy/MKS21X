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
		System.out.println("isRight: " + t.isRight());
		Triangle t2 = new Triangle(new Point(0, 0), new Point(4, 0),
					   new Point(2, 3));
		System.out.println("isRight t2: " + t2.isRight());
		Triangle g = new Triangle(0, 0, 5, 0, 0, 12);
		System.out.println("Perim: " + g.getPerimeter());
		System.out.println("Area: " + g.getArea());
		System.out.println("isRight: " + g.isRight());
		
		g = new Triangle(0, 0, 0, 0, 0, 0);
		System.out.println("Perim: " + g.getPerimeter());
		System.out.println("Area: " + g.getArea());
		System.out.println("isRight: " + g.isRight());
		
		g = new Triangle(0, 0, 1, 1, 2, 2);
		System.out.println("Perim: " + g.getPerimeter());
		System.out.println("Area: " + g.getArea());
		System.out.println("isRight: " + g.isRight());
		
		g = new Triangle(0, 0, -6, 0, 0, -8);
		System.out.println("Perim: " + g.getPerimeter());
		System.out.println("Area: " + g.getArea());
		System.out.println("isRight: " + g.isRight());
    }
}
