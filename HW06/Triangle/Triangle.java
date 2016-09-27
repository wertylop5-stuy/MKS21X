public class Triangle{
    private Point v1, v2, v3;

    public Triangle(){}
    
    //make copies of the parameters, do not just say v1 = a, etc.
    public Triangle(Point a, Point b, Point c){
        //1. Explain why we want this, and how you will do it.
	v1 = new Point(a);
    }

    
    public Triangle(double x1,double y1,
		    double x2,double y2,
		    double x3,double y3) {

    }
    

    //format: "Triangle @ (1,1) (3,2) (5,6)"
    public String toString(){
	return "Triangle @ (" + v1.getX() + "," + v1.getY() + ") " +
	    "(" + v2.getX() + "," + v2.getY() + ") " +
	    "(" + v3.getX() + "," + v3.getY() + ")";
    }

    public double getPerimeter(){
	return 0.0;
    }

    public double getArea(){
	return 0.0;
        //2. Explain how to do this with the tools you have in Point/Triangle. 
    }

    //do not use == to check if doubles are the same, because of rounding errors + irrational numbers.

    //Instead write a function to check if values are very close together.  

    private boolean isCloseEnough(double a,double b){

        //3. Explain how to calculate. 

        //Are 100,000,001 and 100,000,000 close together? they are 1 apart. 

        //Are 1 and 2 close together? they are also 1 apart
	return false;
    }

    //helper function for isRight, assume that ab is the longest side
    private boolean pycheck(Point a, Point b, Point c){
	return false;
        //Optional but helpful.
    }

    //It is suggested that you use pycheck 

    //Return true when the triangle is a right triangle, false otherwise.
    public boolean isRight(){
	return false;
    }

}
