//import java.util.stream;
import java.util.regex.*;

public class Barcode implements Comparable<Barcode>{
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
		Pattern code = Pattern.compile("^(\\d){5}$");
		Matcher m = code.matcher(zip);
		return m.matches();
	}

	//postcondition: format zip + check digit + barcode 
	//ex. "084518  |||:::|::|::|::|:|:|::::|||::|:|"
	@Override     
	public String toString() {
		String res = "|";
		for (char c : mZip.toCharArray()) {
			res += getZipDigit(c);
		}
		return res +
			getZipDigit(new Integer(mCheckDigit).toString().charAt(0)) + "|";
	}
	
	private String getZipDigit(char c) {
		switch(Character.digit(c, 10)) {
			case 1:
				return ":::||";
			case 2:
				return "::|:|";
			case 3:
				return "::||:";
			case 4:
				return ":|::|";
			case 5:
				return ":|:|:";
			case 6:
				return ":||::";
			case 7:
				return "|:::|";
			case 8:
				return "|::|:";
			case 9:
				return "|:|::";
			case 0:
				return "||:::";
			default:
				throw new NumberFormatException("This shouldn't happen: " + c);
		}
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
