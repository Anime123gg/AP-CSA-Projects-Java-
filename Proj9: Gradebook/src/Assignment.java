/**
 * An assignment, which has a name, the number of points earned, 
 * and the points possible. Also has methods that interact with these 
 * items.
 * 
 * @author William, Ryan
 */
public class Assignment {
	private String name;
	private double points;
	private double earned;
	
	/**
	 * Constructor to make an assignment, setting its name, points earned, 
	 * and the total possible points.
	 * 
	 * @author Ryan
	 * @param nm - Name of the assignment
	 * @param ern - Points earned on the assignment
	 * @param pts - Possible points that can be earned
	 */
	public Assignment(String nm, double ern, double pts) {
		name = nm;
		points = pts;
		earned = ern;
	}
	
	
	/**
	 * Getter method that returns the name of the assignment
	 * 
	 * @author William
	 * @return Name of the method
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Mutation method that sets the name of the assignment
	 * 
	 * @author William
	 * @param name - New name to set the assignment to
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter method that returns the possible points that can be earned 
	 * on the assignment.
	 * 
	 * @author Ryan
	 * @return Possible points that can be earned on the assignment
	 */
	public double getPoints() {
		return points;
	}
	
	/**
	 * Mutation method that sets the possible points that can be earned 
	 * on the assignment
	 * 
	 * @author Ryan
	 * @param points - New possible points to set the assignment to
	 */
	public void setPoints(double points) {
		this.points = points;
	}
	
	
	/**
	 * Getter method that returns the points earned on the assignment
	 * 
	 * @author William
	 * @return Points earned on the assignment
	 */
	public double getEarned() {
		return earned;
	}
	
	/**
	 * Mutation method that sets the earned points on the assignment 
	 * 
	 * @author William
	 * @param earned - New earned points to set the assignment to
	 */
	public void setEarned(double earned) {
		this.earned = earned;
	}
	
	/**
	 * Getter method that returns a decimal representing how much points was earned divided by 
	 * the possible points
	 * 
	 * @author Ryan
	 * @return A decimal representing how much of the points was earned out of the possible points
	 */
	public double getDecimal() {
		return earned/points;
	}
	
	/**
	 * Overriding method that returns a String containing the assignment's name, 
	 * points earned, and points possible in a presetted format.
	 * 
	 * @author Ryan
	 * @return String representing this Assignment object
	 */
	public String toString() {
		return name + ",\t " + earned + "/" + points;
	}
	
}
