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
		System.out.println(zip);
		Pattern code = Pattern.compile("^(\\d){5}$");
		Matcher m = code.matcher(zip);
		
		if (m.matches()) mZip = zip;
		else throw new IllegalArgumentException("incorrectly formatted zip");
		
		mCheckDigit = checkSum();
	}

	// postcondition: Creates a copy of a bar code.
	public Barcode clone() {
		return null;
	}


	// postcondition: computes and returns the check sum for _zip
	private int checkSum() {
		int sum = 0;
		for (char c : mZip.toCharArray()) {
			sum += Character.digit(c, 10);
		}
		return sum % 10;
	}

	//postcondition: format zip + check digit + barcode 
	//ex. "084518  |||:::|::|::|::|:|:|::::|||::|:|"
	@Override     
	public String toString() {
		String res = "|";
		for (char c : mZip.toCharArray()) {
			res += getZipDigit(c);
		}
		return res + "|";
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
			default: throw new IllegalArgumentException();
		}
	}

//git add *.java;git commit -m "Started 03Barcode";git push origin master
	// postcondition: compares the zip + checkdigit, in numerical order. 
	@Override
	public int compareTo(Barcode other) {
		return 0;
	}
	
	public static void main(String[] args) {
		Barcode b = new Barcode("12345");
		//b = new Barcode("12");
		//b = new Barcode("retyu");
		//b = new Barcode("123456");
		//b = new Barcode("12t45");
		System.out.println(b);
	}
}
