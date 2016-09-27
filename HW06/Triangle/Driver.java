public class Driver {
    public static void main(String args[]) {
	Point a = new Point();
	a.setX(5);
	a.setY(3);
	Point b = new Point(a);
	b.setX(6);

	System.out.println("a: " + a.getX() + " b: " + b.getX());
    }
}
