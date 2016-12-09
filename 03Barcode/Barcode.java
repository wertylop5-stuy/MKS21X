//import java.util.stream;
import java.util.regex.*;

//TODO finish toZip
public class Barcode implements Comparable<Barcode>{
	public static final String[] BAR_DIGITS = {
		"||:::",
		"::|:|",
		"::||:",
		":|::|",
		":|:|:",
		":||::",
		"|:::|",
		"|::|:",
		"|:|::",
		":::||"
	};
	
	private String mZip;
	private int mCheckDigit;

	// constructors
	//precondtion: _zip.length() = 5 and zip contains only digits.
	//postcondition: throws a runtime exception zip is not the correct length
	//               or zip contains a non digit
	//               _zip and _checkDigit are initialized.
	public Barcode(String zip) {
		//System.out.println(zip);
		
		if (isValidZip(zip)) mZip = zip;
		else throw new NumberFormatException("incorrectly formatted zip");
		
		mCheckDigit = checkSum();
	}
	
	// postcondition: Creates a copy of a bar code.
	public Barcode clone() {
		return new Barcode(mZip);
	}


	// postcondition: computes and returns the check sum for _zip
	private int checkSum() {
		int sum = 0;
		for (char c : mZip.toCharArray()) {
			sum += Character.digit(c, 10);
		}
		return sum % 10;
	}
	
	private boolean isValidZip(String zip) {
		/*Pattern code = Pattern.compile("^(\\d){5}$");
		Matcher m = code.matcher(zip);
		return m.matches();*/
		return Pattern.matches("^(\\d){5}$", zip);
	}

	//postcondition: format zip + check digit + barcode 
	//ex. "084518  |||:::|::|::|::|:|:|::::|||::|:|"
	     
	public static String toCode(String zip, int checkDigit) {
		String res = "|";
		for (char c : zip.toCharArray()) {
			res += getZipDigit(c);
		}
		return res +
			getZipDigit(new Integer(checkDigit).toString().charAt(0)) + "|";
	}
	
	public static String toZip(String code) {
		/*
		Will test if right length, has | at start and end
		and has no illegal characters
		*/
		if (!Pattern.matches("^\\|[:|]{30}\\|$", code))
			throw new IllegalArgumentException("malformed barcode");
		
		String res = "";
		/*
		Will convert to number, throw error if digit
		no correspond to number or
		*/
		for (int i = 1; i < code.length() - 5; i+= 5) {
			if (!)
		}
	}
	
	@Override
	public String toString() {
		return toCode();
	}
	
	private String getZipDigit(char c) {
		return BAR_DIGITS[Character.digit(c)];
	}
	
	// postcondition: compares the zip + checkdigit, in numerical order. 
	@Override
	public int compareTo(Barcode other) {
		return (mZip + mCheckDigit)
			.compareTo(other.getZip() + other.getCheckDigit());
	}
	
	//Getters
	public String getZip() { return mZip; }
	public int getCheckDigit() { return mCheckDigit; }
	
	public static void main(String[] args) {
		Barcode b = new Barcode("12345");
		//b = new Barcode("12");
		//b = new Barcode("retyu");
		//b = new Barcode("123456");
		//b = new Barcode("12t45");
		System.out.println(b);
		Barcode c = new Barcode("08451");
		System.out.println(c.toString()
			.equals("|||:::|::|::|::|:|:|::::|||::|:|"));
		System.out.println(b.compareTo(c));
		System.out.println(c.compareTo(b));
		System.out.println(c.compareTo(c));
	}
}
