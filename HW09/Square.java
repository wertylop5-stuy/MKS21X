public class Square extends Rectangle {
	public Square(){}
	public Square(double len){super(len, len);}
	
	/**
	* Both length and width are set to len
	*/
	@Override
	public void setLength(double len) {
		super.setLength(len);
		super.setWidth(len);
	}
	
	/**
	* Same as setLength
	*/
	@Override
	public void setWidth(double wid) {
		super.setLength(wid);
		super.setWidth(wid);
	}
	
	@Override
	public String toString() {
		return "A Square with side=" + getLength() + 
		", which is a subclass of " + super.toString();
	}
	
	public static void main(String[] args) {
		Square c = new Square(2.0);
		System.out.println(c.getArea());		//4
		System.out.println(c.getPerimeter());	//8
		
		Square t = new Square();
		System.out.println(t.getArea());		//1
		System.out.println(t.getPerimeter());	//4
		System.out.println(t);
	}
}