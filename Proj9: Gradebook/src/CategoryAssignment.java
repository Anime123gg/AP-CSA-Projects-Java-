/**
 * Subclass of Assignment, that inherits its predecessor's fields and methods, 
 * on top of having another field for the category it belongs to.
 * 
 * @author Ryan
 */
public class CategoryAssignment extends Assignment {
	private String category;
	
	/**
	 * Constructor that creates an Assignment object with a name, points earned, 
	 * points possible, and a category that it belogns to.
	 * 
	 * @author Ryan
	 * @param nm - Name of the assignment
	 * @param ern - Points earned in the assignment
	 * @param pts - Points possible in the assignment
	 * @param cate - Category the assignment belongs to
	 */
	public CategoryAssignment(String nm, double ern, double pts, String cate) {
		super(nm, ern, pts);
		category = cate;
	}
	
	/**
	 * Getter method that returns the name of the category this assignment 
	 * belongs to.
	 * 
	 * @author Ryan
	 * @return Category that the assignment belongs to
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * Mutator method that sets the category of the assignment
	 * 
	 * @author Ryan
	 * @param cate - New category that is to be set
	 */
	public void setCategory(String cate) {
		category = cate;
	}
	
	/**
	 * Calls the parent class's toString, and adds its category to it before 
	 * returning the String
	 * 
	 * @author Ryan
	 * @return String that represents the assignment
	 */
	public String toString() {
		return super.toString() + ",\t" + category;
	}
}
