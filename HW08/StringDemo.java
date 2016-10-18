public class StringDemo {
    public static void main(String args[]) {
	String s = "Hello happy people and welcome to";
	System.out.println(s.charAt(0));//H
	System.out.println(s.charAt(s.length()-1));//o
	System.out.println(s.length());//Exactly what I think
	System.out.println(s.equals("fe"));//false
	System.out.println(s.equals(new String(s)));//true
	System.out.println(s.compareTo("rt"));//negative (H is before r)
	System.out.println(s.compareTo("a"));//negative (H is before a, unicode)
	System.out.println(s.compareTo("A"));//Positive (H is after a)
	System.out.println(s.indexOf("Hello"));//0
	System.out.println(s.indexOf(" h"));//5
	System.out.println(s.indexOf(5));//-1
	System.out.println(s.substring(0));//whole string
	System.out.println(s.substring(0, 1));//first character

	/*
Exception in thread "main" java.lang.StringIndexOutOfBoundsException: String index out of range: 34
	at java.lang.String.substring(String.java:1963)
	at StringDemo.main(StringDemo.java:17)
This means that the given index wasn't within the boundaries of the string.
I can find it using the line number. In the future, I should check to see if the index is valid.
*/
	try{
System.out.println(s.substring(0, s.length() + 1));//IndexOutOfBounds
	}catch (IndexOutOfBoundsException e){}

	/*
Exception in thread "main" java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
	at StringDemo.thing(StringDemo.java:52)
	at StringDemo.main(StringDemo.java:17)
This means I tried to convert an object to a type it doesn't have. I can see if I try to make any unreasonable casts i my code. In the future, I can check what things I am converting.
*/
	try{
	    thing(new Integer(6));}
	catch(ClassCastException e){}


	/*
StringDemo.java:51: error: cannot find symbol
	Steing w;
	^
  symbol:   class Steing
  location: class StringDemo
1 error
This means that I either misspelled something, or I didn't import something.
I can check for misspellings or by ensuring I import something before using it.
In the future, I know to look for my spelling.
*/
	//Steing w;

	
	/*
StringDemo.java:56: error: incompatible types: String cannot be converted to Integer
	String t = (Integer)"f";
	                    ^
1 error
This means I tried to convert something to a type that it isn't. It differs from the ClassCastException in that it is not a function call used at runtime. I can avoid this in the future in the same manner I ensured that the ClassCAstException wouldn't happen.
*/
	//String t = (Integer)"f";

	/*
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 8
	at StringDemo.main(StringDemo.java:65)
This is similar to the IndexOutOfBoundsException except that I used an array.
I can deal with it in a similar manner I can deal with the IndexOutOfBoundsException.
*/
	try{
	char t[] = {'1', '2'};
	t[8] = '9';
	}catch(ArrayIndexOutOfBoundsException e) {}
    }

    public static void thing(Object t) {
	//Terrible things will happen
	String r = (String)t;
    }
}
