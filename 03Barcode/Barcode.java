//import java.util.stream;
import java.util.regex.*;
import java.util.List;
import java.util.Arrays;

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
		else throw new IllegalArgumentException("incorrectly formatted zip");
		
		mCheckDigit = checkSum();
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
		return Pattern.matches("^(\\d){5}$", zip);
	}




	//postcondition: format zip + check digit + barcode 
	//ex. "084518  |||:::|::|::|::|:|:|::::|||::|:|"
	     
	public static String toCode(String zip) {
		String res = "|";
		int checkDigit = (new Barcode(zip)).getCheckDigit();
		System.out.println(zip);
		System.out.println(checkDigit);
		
		for (char c : zip.toCharArray()) {
			res += BAR_DIGITS[Character.digit(c, 10)];
			System.out.println(BAR_DIGITS[Character.digit(c, 10)] + " and " +
			c);
		}
		return res +
			BAR_DIGITS[/*new Integer(checkDigit).toString().charAt(0)*/checkDigit] + "|";
	}
	
	
	
	
	public static String toZip(String code) {
		/*
		Will test if right length, has | at start and end
		and has no illegal characters
		*/
		if (!Pattern.matches("^\\|[:|]{30}\\|$", code))
			throw new IllegalArgumentException("Barcode incorrect length/illegal characters");
		
		List<String> digits = Arrays.asList(BAR_DIGITS);
		String res = "";
		int sum = 0;
		/*
		Will convert to number, throw error if digit
		no correspond to number or sum of digits % 10 doesn't equal
		the checksum
		*/
		int i;
		for (i = 1; i < code.length() - 5; i+= 5) {
			if (!digits.contains(code.substring(i, i+5)))
				throw new IllegalArgumentException("Malformed barcode");
			res += "" + (sum += digits.indexOf(code.substring(i, i+5)));
		}
		if (digits.indexOf(code.substring(i, i+5)) != sum % 10)
			throw new IllegalArgumentException("Checksum doesn't match");
		return res;
	}
	
	
	
	
	/**
	* 
	* Ex: 
	*@return Returns barcode in format: "084518 |||:::|::|::|::|:|:|::::|||::|:|"
	*
	*
	*/
	@Override
	public String toString() {
		return mZip + " " + toCode(mZip);
	}
	
	
	
	
	// postcondition: compares the zip + checkdigit, in numerical order. 
	@Override
	public int compareTo(Barcode other) {
		return (mZip + checkSum())
			.compareTo(other.getZip() + other.getCheckDigit());
	}
	
	
	
	
	//Getters
	public String getZip() { return mZip; }
	public int getCheckDigit() { return checkSum(); }
	
	public static void main(String[] args) {
		//Barcode b = new Barcode("12345");
		//System.out.println();
		//b = new Barcode("12");
		//b = new Barcode("retyu");
		//b = new Barcode("123456");
		//b = new Barcode("12t45");
		/*System.out.println(new Barcode("12345"));
		System.out.println(toCode("00000"));
		Barcode c = new Barcode("08451");*/
		System.out.println(toCode("08451")
			/*(.equals("|||:::|::|::|::|:|:|::::|||::|:|")*/);
		System.out.println("|||:::|::|::|::|:|:|::::|||::|:|");
		/*System.out.println(b.compareTo(c));
		System.out.println(c.compareTo(b));
		System.out.println(c.compareTo(c));*/
	}
}
