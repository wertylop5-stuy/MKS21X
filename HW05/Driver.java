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
	Student s = new Student("Joe", "Shmoe", "123456789", 2);
	s.printStudent();
	s.raiseGPA(1.0);
	s.printStudent();
	s.lowerGPA(2.0);
	s.printStudent();
	
	Student a = new Student("Davy", "Jones", "987654321", 2);
	a.printStudent();
	a.lowerGPA(0.1);
	a.printStudent();
	a.raiseGPA(4.0);
	a.printStudent();
	
    }
}
