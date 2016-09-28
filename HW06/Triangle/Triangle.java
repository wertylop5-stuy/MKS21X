public class Triangle{
    private Point v1, v2, v3;

    public Triangle(){}
    
    //make copies of the parameters, do not just say v1 = a, etc.
    public Triangle(Point a, Point b, Point c){
        //1. Explain why we want this, and how you will do it.
	/*We want to make copies because if we don't, people can
	 access our private variables from outside the class
	 
	 I made a copy constructor in the Point class that takes a 
	 Point object as an argument and copies its members into the
	 new object.
	 */
	v1 = new Point(a);
	v2 = new Point(b);
	v3 = new Point(c);
    }
    
    public Triangle(double x1,double y1,
		    double x2,double y2,
		    double x3,double y3) {
	v1 = new Point(x1, y1);
	v2 = new Point(x2, y2);
	v3 = new Point(x3, y3);
    }
    

    //format: "Triangle @ (1,1) (3,2) (5,6)"
    public String toString(){
	return "Triangle @ (" + v1.getX() + "," + v1.getY() + ") " +
	    "(" + v2.getX() + "," + v2.getY() + ") " +
	    "(" + v3.getX() + "," + v3.getY() + ")";
    }

    public double getPerimeter(){
	return v1.distance(v2) + v2.distance(v3) + v3.distance(v1);
    }

    //Uses heron's formula
    public double getArea(){
        //2. Explain how to do this with the tools you have in Point/Triangle. 
	/*
	 We can use the distance method in the Point class to 
	 get the side lengths of the Triangle. With this, 
	 getting the semiperimeter is simple with the getPerimeter
	 method.
	 */
	double s = getPerimeter() / 2.0;
	return Math.sqrt(s * (s - v1.distance(v2)) *
			 (s - v2.distance(v3)) *
			 (s - v3.distance(v1)));
    }

    //do not use == to check if doubles are the same, because of rounding errors + irrational numbers.

    //Instead write a function to check if values are very close together.  
    //must be within (1/1000)%
    private boolean isCloseEnough(double a,double b){
        //3. Explain how to calculate.
	/*
	  Get the absolute value of the diffence of the two values.
	  Get value of largest # / 1000 (epsilon)
	  If abs val less than epsilon, true, else false;
	 */
	double epsilon;
	//System.out.println(a + " " + b);
	if (a > b) epsilon = a / 1000;
	else epsilon = b / 1000;
	//System.out.println(epsilon);
	return Math.abs(a - b) <= epsilon;
    }

    //helper function for isRight, assume that ab is the longest side
    private boolean pyCheck(Point a, Point b, Point c){
	System.out.println(a + " " + b + " " + c);
	System.out.println(Math.sqrt( Math.pow(b.distance(c), 2) +
				      Math.pow(c.distance(a), 2)) + " " + 
			   a.distance(b));
	return (isCloseEnough(
			  Math.sqrt( Math.pow(b.distance(c), 2) +
				     Math.pow(c.distance(a), 2)),
			  a.distance(b)));
    }

    //It is suggested that you use pycheck 
    //Return true when the triangle is a right triangle, false otherwise
    public boolean isRight(){
	double ab = v1.distance(v2);
	double bc = v2.distance(v3);
	double ca = v3.distance(v1);

	if (ab >= bc) {
	    System.out.println(1);
	    if (ab >= ca) return pyCheck(v1, v2, v3);
	    return pyCheck(v1, v3, v2);
	}
	else if (bc >= ca) {
	    System.out.println(2);
	    return pyCheck(v2, v3, v1);
	}
	System.out.println(3);
	return pyCheck(v3, v1, v2);
    }

    public static void main(String args[]) {
	Triangle t = new Triangle(new Point(0, 0),
				  new Point(3, 0),
				  new Point(0, 4));
	System.out.println("isCloseEnough tests");
	System.out.println(t.isCloseEnough(1, 2));
	System.out.println(t.isCloseEnough(1000, 999));
	System.out.println(t.isCloseEnough(100000, 99999.0002));
	System.out.println(t.isCloseEnough(0, 0));
	System.out.println("Testing pyCheck");
	System.out.println(t);
	System.out.println(t.pyCheck(t.v1, t.v2, t.v3));
    }

}
