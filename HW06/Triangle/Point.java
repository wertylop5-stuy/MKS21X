public class Point {
    private double x = 0;
    private double y = 0;

    public Point(){}
    
    // a constructor!
    public Point(double x, double y) {
        setX(x);
        setY(y);
    }

    //copy constructor
    public Point(Point toCopy) {
	x = toCopy.getX();
	y = toCopy.getY();
    }

    // accessor methods
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

    //mutator methods
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }
    
    public double distance(Point other) {
	return Math.sqrt(Math.pow(x - other.getX(), 2) + 
			 Math.pow(y - other.getY(), 2));
    }

    @Override
    public String toString() {
	return "Point @ (" + x + ", " + y + ")";
    }
}
