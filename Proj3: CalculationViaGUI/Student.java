/**
 * Last name: Nguyen, Ngo
 * First name: Alexander, William
 * Student ID: 12108173, 12019029
 * Period:4
 * 
 * @author William Ngo, Alex Nguyen
 */
public class Student {
	
	private String name; //student's name
	private  int one;   //student's first test score
	private  int two;   //student's second test score
	private  int three; //student's third test score
	private  int high;
	private  int low;
	/**
	 * Initializes the first 3 test scores of the student to all be 0 and the name as Bob
	 * William
	 */
	public Student() {
		name = "Bob";
		one = 0;
		two = 0;
		three = 0;
	}
	/**
	 * Initializes the first 3 test scores of the student
	 * @param sName - (String) student name
	 * @param myOne - (int) First test score
	 * @param myTwo - (int) Second test score
	 * @param myThree - (int) Third test score
	 * Alex
	 */
	public Student(String sName , int myOne, int myTwo, int myThree) {
		name = sName;
		one = myOne;
		two = myTwo;
		three = myThree;
	}
	
	/**
	 * Returns the object's name
	 * @return name - (String) name of the object
	 * William
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the 1st test
	 * @return one - (int) the first test score
	 * William
	 */
	public int getOne() {
		return one;
	}
	
	/**
	 * Returns the 2nd test
	 * @return two -  (int) the second test score
	 * William
	 */
	public int getTwo() {
		return two;
	}
	
	/**
	 * Returns the 3rd test
	 * @return three - (int) the third test score
	 * William
	 */
	public int getThree() {
		return three;
	}
	
	/**
	 * This compares sets the highest score of a student to the variable high.
	 * Alex
	 */
	public void setHigh() {
		high=one;
		if (one<two) {
			high = two;
		}
		if (two<three) {
			high = three;
		}
		if (one>three && two<three) {
			high = one;
		}
		
	}
	/**
	 * This just makes sure there is a value for high and returns it
	 * @return high - (int) returns highest test score
	 * Alex
	 */
	public int getHigh() {
		setHigh();
		return high;
	
	}
	
	/**
	 * This compares sets the lowest score of a student to the variable low.
	 * William
	 */
	
	public void setLow() {
		low= one;
		if (one>two) {
			low = two;
		}
		if (two>three) {
			low = three;
		}
		if (one<three && two>three ) {
			low = one;
		}
		
	}
	/**
	 * This just makes sure there is a value for low and returns it
	 * @return low - (int) lowest test score
	 * Alex
	 */
	public int getLow() {
		setLow();
		return low;
	
	}
	
	/**
	 * This calculates and returns the average test score of the student
	 *  
	 * @return avg - (int) the average score of the student's 3 tests
	 * William
	 */
	public int average() {
		int avg = (getOne()+getTwo()+getThree())/3;
		return avg;
	}
	
	/**
	 * This compares the averages of 2 students and prints out a string 
	 * that states who had the higher average and what that average was. 
	 * @param p - (Student object) Student 1 
	 * @param q - (Student object) Student 2
	 * @return better - (String) says who had the higher avg and what the avg was
	 * Alex
	 */
	public String betterAvg(Student p, Student q) {
		String better;

		 if (p.average()>q.average()) {
			better = p.getName() +" has the highest average of "+ p.average(); 
		 }
		 else {
			 better = q.getName() +" has the highest average of "+ q.average();
		 }
		 return better;
	}
	/**
	 * This compares the highest scores of 2 students and prints out a string 
	 * that states who had the highest score and what that highest score was. 
	 * @param p - (Student object) Student 1 
	 * @param q - (Student object) Student 2
	 * @return better - (String) says who had the highest score and what the highest score was
	 * William
	 */
	public String betterHigh(Student p, Student q) {
		String better;

		 if (p.getHigh()>q.getHigh()) {
			better = p.getName() +" has the highest score of "+ p.getHigh(); 
		 }
		 else {
			 better = q.getName() +" has the highest score of "+ q.getHigh();
		 }
		 return better;
	}
	/**
	 * This compares the lowest scores of 2 students and prints out a string 
	 * that states who had the lowest score and what that lowest score was. 
	 * @param p - (Student object) Student 1 
	 * @param q - (Student object) Student 2
	 * @return better - (String) says who had the lowest score and what the lowest score was
	 * Alex
	 */
	public String betterLow(Student p, Student q) {
		String better;

		 if (p.getLow()<q.getLow()) {
			better = p.getName() +" has the lowest score of "+ p.getLow(); 
		 }
		 else {
			 better = q.getName() +" has the lowest score of "+ q.getLow();
		 }
		 return better;
	}

		
		
}
