/**
 * A Student class that holds a name, id, and a personal gradebook used to track the student's assignments.
 * Has methods that interact with those fields, and a modified toString.
 * 
 * @author Steven
 */
public class Student {
	private String name;
	private int id;
	private Gradebook gradebook;
	
	/**
	 * Constructor that creates an instance of the Student with a given name, ID, 
	 * and gradebook.
	 * 
	 * @author Steven
	 * @param nm - name of the student
	 * @param i - id of the student
	 * @param gb - gradebook of the student
	 */
	public Student(String nm, int i, Gradebook gb) {
		name = nm;
		id = i;
		gradebook = gb;
	}
	
	/**
	 * Getter method for the Student's name.
	 * 
	 * @author Steven
	 * @return The Student's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter method for the Student's ID.
	 * 
	 * @author Steven
	 * @return The Student's ID
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Getter method for the Student's personal gradebook.
	 * 
	 * @author Steven
	 * @return The Student's Gradebook
	 */
	public Gradebook getGradebook() {
		return gradebook;
	}
	
	/**
	 * Mutator method that sets the gradebook of the student.
	 * 
	 * @author Steven
	 * @param gb - new Gradebook to be set
	 */
	public void setGradebook(Gradebook gb) {
		gradebook = gb;
	}
	
	/**
	 * Overriding toString method that prints out the Student's name, ID, and grade.
	 * 
	 * @author Steven
	 */
	public String toString() {
		return name + " ID: " + id + "\t Grade: " + String.format("%.2f",gradebook.getGrade() * 100)  + "%";
	}
	
}
