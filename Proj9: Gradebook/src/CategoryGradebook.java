import java.util.ArrayList;

/**
 * A category style gradebook that inherits the fields and methods of Gradebook. 
 * Holds additional ArrayLists for its categories and attributes related to it, and
 * methods to interact with it alongside the assignments.
 * 
 * @author Steven, William, Ryan
 */
public class CategoryGradebook extends Gradebook {
	private ArrayList<String> categories = new ArrayList<String>();
	private ArrayList<Double> weight = new ArrayList<Double>();
	private ArrayList<Boolean> dropped = new ArrayList<Boolean>();
	
	/**
	 * Default constructor.
	 * 
	 * @author Ryan
	 */
	public CategoryGradebook() {
		
	}
	
	/**
	 * Returns a copy of this CategoryGradebook.
	 * 
	 * @author William
	 * @return Copy of this gradebook
	 */
	public CategoryGradebook clone() {
		CategoryGradebook copy = new CategoryGradebook();
		for (int i = 0; i < getArrayLength(); i++) {
			copy.addAssignment(getAssignment(i));
		}
		for (int j = 0; j < categories.size(); j++) {
			copy.addCategory(categories.get(j), weight.get(j));
		}
		return copy;
	}
	
	/**
	 * Adds a category using the given name and weight.
	 * 
	 * @author Ryan
	 * @param cate - The name of the category being added
	 * @param wgt - The weight of the category being added
	 */
	public void addCategory(String cate, double wgt) {
		categories.add(cate);
		weight.add(wgt);
		dropped.add(false);
	}
	
	/**
	 * Adds a category using the given name, weight, and boolean.
	 * 
	 * @author Ryan
	 * @param cate - The name of the category being added
	 * @param wgt - The weight of the category being added
	 * @param drp - Whether this category been dropped already
	 */
	public void addCategory(String cate, double wgt, boolean drp) {
		categories.add(cate);
		weight.add(wgt);
		dropped.add(drp);
	}
	
	/**
	 * Removes the category of the given index
	 * 
	 * @author Ryan
	 * @param index - Index of the category to be removed
	 */
	public void removeCategory(int index) {
		String cateRemoved = categories.remove(index);
		weight.remove(index);
		dropped.remove(index);
		
		for (int i = 0; i < getArrayLength(); i++) {
			CategoryAssignment asgn = (CategoryAssignment)getAssignment(i);
			if (asgn.getCategory().equals(cateRemoved)) {
				removeAssignment(i);
				i--;
			}
		}
		
	}
	
	/**
	 * Getter method that returns the category of the specified index
	 * 
	 * @author Steven
	 * @param index - Index of the category to be returned
	 */
	public String getCategory(int index) {
		return categories.get(index);
	}
	
	/**
	 * Getter method that returns the weight of the category of the 
	 * specified index
	 * 
	 * @author Steven
	 * @param index - Index of the category's weight being returned
	 */
	public double getWeight(int index) {
		return weight.get(index);
	}
	
	/**
	 * Getter method that returns the length of the category ArrayList
	 * 
	 * @author William
	 * @return An int equal to the length of the category ArrayList
	 */
	public int getCategorySize() {
		return categories.size();
	}
	
	/**
	 * Getter method that returns the boolean indicating whether or not an 
	 * assignment of the category specified on the index has been dropped.
	 * 
	 * @author Ryan
	 * @param index - Index of the category's boolean being returned 
	 */
	public boolean getDropped(int index) {
		return dropped.get(index);
	}
	
	/**
	 * Mutator method that sets the boolean of whether or not the category 
	 * has been dropped or not.
	 * 
	 * @author Ryan
	 * @param index - Index of the category getting its boolean set
	 * @param b - boolean to set the category's drop status to
	 */
	public void setDropped(int index, boolean b) {
		dropped.set(index, b);
	}
	
	/**
	 * Calculates the grade using the assignments and categories of the 
	 * gradebook. Categories without any assignments in them are ignored, and 
	 * do not count towards the calculation of the final grade. 
	 * 
	 * @author Steven
	 * @return The grade of the gradebook
	 */
	public double getGrade() {
		double grade = 0;
		double weightTotal = 0;
		
		for (int i = 0; i < categories.size(); i++) {
			if (checkZeroPoints(categories.get(i))) {
				grade += calculateOneCategory(categories.get(i));
				weightTotal += weight.get(i);
			}
		}
		
		if (weightTotal == 0) {
			return 0;
		}
		return grade/weightTotal;
	}
	
	/**
	 * Checks if the points possible of the specified category is not 
	 * equal to 0.
	 * 
	 * @author Ryan
	 * @param cate - Name of the category being checked
	 * @return Boolean indicating that the possible points is not 0
	 */
	public boolean checkZeroPoints(String cate) {
		double pointsPossible = 0;
		for (int i = 0; i < getArrayLength(); i++) {	
			if ( cate.equals(((CategoryAssignment)getAssignment(i)).getCategory()) ) {
				pointsPossible += getAssignment(i).getPoints();
			}
			
		}
		
		if (pointsPossible != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Calculates and returns the weighted "grade" of the specified category. 
	 * 
	 * @author William
	 * @param cate - Name of the category whose weighted grade is being calculated
	 * @return The weighted "grade" of the specified category 
	 */
	public double calculateOneCategory(String cate) {
		double pointsEarned = 0;
		double pointsPossible = 0;
		
		for (int i = 0; i < getArrayLength(); i++) {
			
			if ( cate.equals(((CategoryAssignment)getAssignment(i)).getCategory()) ) {
				pointsEarned += getAssignment(i).getEarned();
				pointsPossible += getAssignment(i).getPoints();
			}
			
		}
		return (pointsEarned/pointsPossible) * weight.get(getCategoryIndex(cate));	
	}
	
	/**
	 * Returns the index of the specified category name. Returns -1 if the category 
	 * is not found.
	 * 
	 * @author William
	 * @param cate - Name of the category whose index is being searched for
	 * @return Integer that is the index of the category, or -1
	 */
	public int getCategoryIndex(String cate) {
		for (int i = 0; i < categories.size(); i++) {
			if (cate.equals(categories.get(i))) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Drops the most impactful assignment of a specified category that is lowering 
	 * the overall grade, then change a boolean to indicate that an assignment was dropped 
	 * in the category. If there are no assignments in the category or the category isn't 
	 * found, nothing happens.If there is only one assignment in the category, it will 
	 * remove it, even if it lowers the overall grade. 
	 * 
	 * @author Steven
	 * @param cate - Name of the category whose assignment is being removed 
	 */
	public void dropLowestAssignment(String cate) {
		int lowestIndex = 0;
		double curGrade = getGrade();
		boolean changed = false;
		
		for (int i = 0; i < getArrayLength(); i++) {
			CategoryGradebook temp = clone();
			CategoryAssignment asgn = (CategoryAssignment)(temp.getAssignment(i));
			
			
			if ( asgn.getCategory().equals(cate) ) {
				temp.removeAssignment(i);
				
				if (changed == false) {
					changed = true;
					lowestIndex = i;
					curGrade = temp.getGrade();
					
				} else if (temp.getGrade() > curGrade) {
					lowestIndex = i;
					curGrade = temp.getGrade();
				}
			}
		}
		
		if (changed) {
			removeAssignment(lowestIndex);
			dropped.set(getCategoryIndex(cate), true);
		} else {
			System.out.println("Error: Category not found or no assignment in category.");
		}
		
	}
	
	
	
}
