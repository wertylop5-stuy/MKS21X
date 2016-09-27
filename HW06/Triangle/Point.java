public class Point {
    private int x = 0;
    private int y = 0;

    public Point(){}
    
    // a constructor!
    public Point(int x, int y) {
        setX(x);
        setY(y);
    }

    //copy constructor
    public Point(Point toCopy) {
	x = toCopy.getX();
	y = toCopy.getY();
    }

    // accessor methods
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    //mutator methods
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    
    public double distance(Point other) {
	return Math.sqrt(Math.pow(x - other.getX(), 2) + 
			 Math.pow(y - other.getY(), 2));
    }
}
