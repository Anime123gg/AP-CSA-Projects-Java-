import java.util.ArrayList;

/**
 * A generic gradebook that holds an ArrayList of Assignment objects. Has methods 
 * to interact with that ArrayList. Also has empty methods to perform actions specific to 
 * one particular type of gradebook.
 * 
 * @author Steven, William, Ryan
 */
public class Gradebook {
	private ArrayList<Assignment> asgnArray = new ArrayList<Assignment>();
	
	/**
	 * Default constructor.
	 * 
	 * @author William
	 */
	public Gradebook() {
		
	}
	
	/**
	 * Method that adds an assignment to asgnArray
	 * 
	 * @author William
	 * @param asgn - Assignment object to be added
	 */
	public void addAssignment(Assignment asgn) {
		asgnArray.add(asgn);
	}
	
	/**
	 * Method that removes an assignment from asgnArray
	 * 
	 * @author Ryan	 
	 * @param index - Index of the Assignment to be removed
	 */
	public void removeAssignment(int index) {
		asgnArray.remove(index);
	}
	
	/**
	 * Method which returns an Assignment object from asgnArray
	 * 
	 * @author Ryan
	 * @param index - Index of the Assignment to be returned
	 * @return - Assignment object of asgnArray
	 */
	public Assignment getAssignment(int index) {
		return asgnArray.get(index);
	}
	
	/**
	 * Method which returns the length of the asgnArray ArrayList
	 * 
	 * @author William
	 * @return - An int that is the ArrayList's length
	 */
	public int getArrayLength() {
		return asgnArray.size();
	}
	
	/**
	 * Prints out all the assignments with their corresponding points earned, and points possible. 
	 * 
	 * @author Steven
	 */
	public void printAssignments() {
		for (int i = 0; i < asgnArray.size(); i++) {
			System.out.println(asgnArray.get(i));
		}
	}
	
	/**
	 * Empty method meant to be overrided by getGrade() methods 
	 * in both of its subclasses, which calculates the grade.
	 * 
	 * @author Steven
	 * @return 0.0, a placeholder double
	 */
	public double getGrade() {
		return 0.0;
	}
	
	/**
	 * Empty method meant to be overrided by the dropLowestAssignment() 
	 * method in the subclass TotalPointsGradebook, which drops the lowest 
	 * most impactful assignment of the gradebook.
	 * 
	 * @author Steven
	 */
	public void dropLowestAssignment() {
		
	}
	
	/**
	 * Empty method meant to be overrided by the dropLowestAssignment(String cate) 
	 * method in the subclass CategoryGradebook, which drops the lowest most 
	 * impactful assignment of a particular category of a gradebook.
	 * 
	 * @author Steven
	 * @param cate - The category that will have its lowest assignment dropped
	 */
	public void dropLowestAssignment(String cate) {
		
	}
	
	/**
	 * Empty method meant to be overrided by the addCategory(String cate, double wgt), 
	 * method in the subclass CategoryGradebook, which adds a category with name cate 
	 * and weight wgt
	 * 
	 * @author Steven
	 * @param cate - name of the category 
	 * @param wgt - weight of the category
	 */
	public void addCategory(String cate, double wgt) {
		
	}
	
	/**
	 * Empty method meant to be overrided by the addCategory(String cate, double wgt, 
	 * dropped drp), method in the subclass CategoryGradebook, which adds a category 
	 * with name cate, weight wgt and whether or not it has a dropped before. 
	 * 
	 * @author Steven
	 * @param cate - name of the category 
	 * @param wgt - weight of the category
	 * @param drp - whether or not the category has an assignment dropped by it
	 */
	public void addCategory(String cate, double wgt, boolean drp) {
		
	}
	
	
	/**
	 * Empty method meant to be overrided by the getCategorySize() method in the subclass 
	 * CategoryGradebook, which returns the number of categories in the gradebook.
	 * 
	 * @author Steven
	 * @return 0, a placeholder integer
	 */
	public int getCategorySize() {
		return 0;
	}
	
	/**
	 * Empty method meant to be overrided by the getCategory(int index) method in the 
	 * subclass CategoryGradebook, which returns the category of the specified index 
	 * in the gradebook.
	 * 
	 * @author Steven
	 * @param index - Index of the category to be returned
	 * @return "", a placeholder empty String
	 */
	public String getCategory(int index) {
		return "";
	}
	
	/**
	 * Empty method meant to be overrided by the getCategoryIndex(String cate) method in 
	 * the subclass CategoryGradebook, which returns the index of the category with the 
	 * specified name.
	 * 
	 * @author Steven
	 * @param cate - Name of the category being searched
	 * @return -1, a placeholder integer
	 */
	public int getCategoryIndex(String cate) {
		return -1;
	}
	
	/**
	 * Empty method meant to be overrided by the getWeight(int index) method in the subclass 
	 * CategoryGradebook, which returns the weight of a category located at the specified index
	 * 
	 * @author Steven
	 * @param index - Index of the category's weight being returned
	 * @return 0.0, a placeholder double
	 */
	public double getWeight(int index) {
		return 0.0;
	}
	
	/**
	 * Empty method meant to be overrided by the getDropped() method in the TotalPointsGradebook 
	 * subclass, which returns a boolean indicating whether an assignment has been dropped by 
	 * the gradebook already.
	 * 
	 * @author Steven
	 * @return false, a placeholder boolean
	 */
	public boolean getDropped() {
		return false;
	}
	
	/**
	 * Empty method meant to be overrided by the getDropped(int index) method in the CategoryGradebook 
	 * subclass, which is meant to return the boolean indicating whether or not an assignment was already
	 * dropped in the category of the specified index.
	 * 
	 * @author Steven
	 * @param index - Index of the category being checked
	 * @return false, a placeholder boolean
	 */
	public boolean getDropped(int index) {
		return false;
	}
	
	
	/**
	 * Empty method meant to be overrided by the removeCategory(int index) method in the CategoryGradebook 
	 * subclass, which is remove a category, and all assignments belonging to that category.
	 * 
	 * @author Steven
	 * @param index - Index of the category being removed
	 */
	public void removeCategory(int index) {
		
	}
	
		
}
