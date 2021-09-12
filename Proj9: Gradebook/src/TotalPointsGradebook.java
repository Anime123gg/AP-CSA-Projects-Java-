/**
 * A total points style gradebook that inherits the fields and methods of Gradebook. 
 * Holds a boolean to indicate whether or not an assignment has been dropped already, 
 * and methods to interact with assignments for its specific type.
 * 
 * @author Steven, William, Ryan
 */
public class TotalPointsGradebook extends Gradebook {
	private boolean dropped = false;
	
	/**
	 * Default constructor.
	 * 
	 * @author Ryan
	 */
	public TotalPointsGradebook() {
		
	}
	
	/**
	 * Returns a copy of this TotalPointsGradebook
	 * 
	 * @author William
	 * @return A copy of this TotalPointsGradebook
	 */
	public TotalPointsGradebook clone() {
		TotalPointsGradebook copy = new TotalPointsGradebook();
		
		for (int i = 0; i < getArrayLength(); i++) {
			copy.addAssignment(getAssignment(i));
		}
		
		return copy;
	}
	
	/**
	 * Calculates and returns the grade of this gradebook using the added assignments 
	 * and the total points system.
	 * 
	 * @author William
	 * @return The calculated grade of this gradebook
	 */
	public double getGrade() {
		double totalEarned = 0;
		double totalPossible = 0;
		if (getArrayLength() == 0) {
			return 0;
		} else {
			for (int i = 0; i < getArrayLength(); i++) {
				totalEarned += getAssignment(i).getEarned();
				totalPossible += getAssignment(i).getPoints();
			}
			return totalEarned/totalPossible;
		}
	}
	
	/**
	 * Drops the most impactful assignment that is lowering the user's grade, then change the 
	 * boolean dropped to indicate an assignment was dropped when successful. If there are no 
	 * assignments, nothing happens, and if there's only one assignment, it will remove it even 
	 * if it lowers the overall grade.
	 * 
	 * @author Steven
	 */
	public void dropLowestAssignment() {
		int lowestIndex = 0;
		double curGrade = getGrade();
		
		if (getArrayLength() == 0) {
			System.out.println("Error: No assignments found.");
			
		} else {
			for (int i = 0; i < getArrayLength(); i++) {
				TotalPointsGradebook temp = clone();
				temp.removeAssignment(i);
				
				if (temp.getGrade() > curGrade) {
					lowestIndex = i;
					curGrade = temp.getGrade();
				}
				
			}
			
			removeAssignment(lowestIndex);
			dropped = true;
		}
		
	}
	
	/**
	 * Getter method that returns whether or not this gradebook already dropped an assignment
	 * 
	 * @author Ryan
	 * @return The private boolean dropped
	 */
	public boolean getDropped() {
		return dropped;
	}
}
