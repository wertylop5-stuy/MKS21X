class Student {
    private String firstName;
    private String lastName;
    private String osis;
    private int age;
    private double gpa;
    
    public Student(String first, String last,
		   String id, int years) {
	firstName = first;
	lastName = last;
	osis = id;
	age = years;
	gpa = 0.0;
    }
    
    void printStudent() {
	System.out.println("Name: " + firstName + " " + lastName + "\n" + 
			   "id/osis: " + osis + "\n" + 
			   "age: " + age + "\n" +
			   "gpa: " + gpa);
    }
    
    void lowerGPA(double amount) {
	gpa -= amount;
    }
    
    void raiseGPA(double amount) {
	gpa += amount;
    }
}

public class Driver {
    public static void main(String args[]) {
	Student s = new Student("Joe", "Shmoe", "29", 2);
	s.printStudent();
	s.raiseGPA(1.0);
	s.printStudent();
	s.lowerGPA(2.0);
	s.printStudent();
    }
}
