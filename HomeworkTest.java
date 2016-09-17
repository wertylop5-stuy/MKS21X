public class HomeworkTest {
    //Uses the equation F = (9/5)C + 32
    public static double celsiusToFahrenheit(double celsius) {
	return (9/5.0) * celsius + 32.0;
    }
    
    //Uses the equation C = (F - 32)(5 / 9)
    public static double fahrenheitToCelsius(double fah) {
	return (fah - 32) * (5.0 / 9);
    }
    
    public static void main(String args[]) {
	System.out.println(celsiusToFahrenheit(-40)); //-40
	System.out.println(celsiusToFahrenheit(0));   //32
	System.out.println(celsiusToFahrenheit(20));  //68
	System.out.println(fahrenheitToCelsius(-40)); //-40
	System.out.println(fahrenheitToCelsius(50));  //10
	System.out.println(fahrenheitToCelsius(0));   //-17.78
    }
}
