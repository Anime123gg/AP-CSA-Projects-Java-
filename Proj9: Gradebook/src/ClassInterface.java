import java.util.*;
import java.io.*;

/**
 * This is a class that contain an arrays for students and a "master" gradebook named ultrabook. 
 * Class will respond according to the user input, and has built in error-checking to continue 
 * running if the user inputs something invalid.
 * 
 * @author Steven, William, Ryan
 */
public class ClassInterface {
	private ArrayList<Student> students = new ArrayList<Student>();
	private Gradebook ultrabook;
	
	private Scanner userScan = new Scanner(System.in);
	private File file;
	private Scanner fileScan;
	private boolean doneRun = false;
	
	/**
	 * Default Constructor.
	 * 
	 * @author Steven
	 */
	public ClassInterface() {
		
	}
	
	/**
	 * Method that prints out the introduction at the start of the program.
	 * 
	 * @author Steven
	 */
	public void printIntroduction() {
		System.out.println("Welcome to the gradebook program!");
		System.out.println("The first thing you have to do is set the gradebook type.");
		System.out.println("There are two types of gradebooks: The total points system, and the categorical system.");
		System.out.println();
	}
	
	/**
	 * Method that prints out the choices the user that the user have, depending on the gradebook instance.
	 * 
	 * @author Steven
	 */
	public void printSelections() {
		System.out.println("--------------------------------------------------------------------------------");
		if (ultrabook instanceof CategoryGradebook) {
			System.out.println("Current gradebook type: Category System\n");
		} else {
			System.out.println("Current gradebook type: Total Points System\n");
		}
		System.out.println("What would you like to do? (Type in the number next to the option)");
		System.out.println();
		System.out.println("1. Set gradebook type (Warning: This will clear the assignments and categories, but not students)");
		System.out.println("2. Add a Student");
		System.out.println("3. Remove a Student");
		System.out.println("4. Add an Assignment");
		System.out.println("5. Remove an Assignment");
		System.out.println();
		System.out.println("6. Open a file (Warning: This will clear/change the current gradebook and students)");
		System.out.println("7. Print one Student's name, id, assignments, and grade");
		System.out.println("8. Print all Student names, id, and their grades");
		System.out.println("9. Print each Student and their Assignments");
		System.out.println("10. Print the averages of each Assignment");
		System.out.println();
		
		if (ultrabook instanceof CategoryGradebook) {
			System.out.println("11. Drop lowest assignment of a category (Can only be done once per category)");
			System.out.println("12. Print Categories");
			System.out.println("13. Add a Category");
			System.out.println("14. Remove a Category (and its assignemnts)");
			System.out.println("15. Exit the program");
		} else {
			System.out.println("11. Drop lowest assignment (Can only be done once)");
			System.out.println("12. Exit the program");
		}
		
		System.out.println("--------------------------------------------------------------------------------");
		
	}
	
	
	/**
	 * Method that takes in the user input, checks its validity, and perform the action corresponding to the 
	 * integer that they inputted. 
	 * 
	 * @author William
	 */
	public void activateChoice() {
		boolean validSelection = false;
		while (!validSelection) {
			int choice = askInt();
			userScan.nextLine();
			
			if (choice == 1) {
				setGradebookType();		
				
			} else if (choice == 2){
				addStudent();			
				
			} else if (choice == 3){
				removeStudent();		
				
			} else if (choice == 4) {
				manualAddAssignment();
				
			} else if (choice == 5) {
				manualRemoveAssignment();			
				
			} else if(choice == 6) {
				setFile();
				openFile();	
				
			} else if (choice == 7) {
				printOneStudent();	
				
			} else if (choice == 8) {
				printStudentGrades();	
			
			} else if (choice == 9){	
				printStudentAssignments();	
		
			}  else if (choice == 10) {
				printAllAverages();	
			
			}  else if (choice == 11 && ultrabook instanceof CategoryGradebook) {
				cateDropLowest();
			
			} else if (choice == 11) {
				totalPtsDropLowest();
			
			} else if (choice == 12 && ultrabook instanceof CategoryGradebook) {
				printCategories();	
			
			} else if (choice == 12) {
				doneRun = true;		
			
			} else if (choice == 13 && ultrabook instanceof CategoryGradebook) {
				manualaddCategory();
				validSelection = true;
			
			} else if (choice == 14 && ultrabook instanceof CategoryGradebook) {
				manualRemoveCategory();
				validSelection = true;
			
			} else if (choice == 15 && ultrabook instanceof CategoryGradebook) {
				doneRun = true;
				validSelection = true;			
			
			} else {
				System.out.println("Error: Not a valid choice.");		
			}			
			
			for (int i = 1; i <= 12; i++) {
				if (choice == i) {
					validSelection = true;
				} 
			}	
		}	
	}
	
	
	/**
	 * Method that sets the gradebook type and reset the students' assignments (and categories if applicable)
	 * 
	 * @author William
	 */
	public void setGradebookType() {
		System.out.println("Set the gradebook type (1 = total points, 2 = category):");
		
		boolean done = false;
		while (!done) {
			int choice = askInt();
			userScan.nextLine();
//			assignments = new ArrayList<String>();
			
			if (choice == 1) {

				ultrabook = new TotalPointsGradebook();
				done = true;
				for (int i = 0; i < students.size(); i++) {
					students.get(i).setGradebook(new TotalPointsGradebook());
				}
				
			} else if (choice == 2) {

				ultrabook = new CategoryGradebook();
				done = true;
				for (int j = 0; j < students.size(); j++) {
					students.get(j).setGradebook(new CategoryGradebook());
				}
			} else {
				System.out.println("Error: Not a valid choice.");
			}
		}
		
		System.out.println("Gradebook typed setted.");
		 
	}
	
	/**
	 * Method that asks the user what they want to name the student, and that student's ID.
	 * 
	 * @author Steven
	 */
	public void addStudent() {
		String name;
		int iD;	
		System.out.println("Type in the student's name: ");
		name = userScan.nextLine();
		System.out.println("Type in the student's ID: ");
		iD = askInt();
		
		if (ultrabook instanceof CategoryGradebook) {
			students.add(new Student(name, iD, new CategoryGradebook()));
			for (int i = 0; i < ultrabook.getCategorySize(); i++) {
				Gradebook temp = students.get(students.size()-1).getGradebook();
				temp.addCategory(ultrabook.getCategory(i), ultrabook.getWeight(i), ultrabook.getDropped(i));
			}

						
		} else { // If it's a total points style 
			students.add(new Student(name, iD, new TotalPointsGradebook()));
		}
		
		System.out.println("Student added.");
	}
	
	/**
	 * Method that removes a student from the system.
	 * 
	 * @author Steven 
	 */
	public void removeStudent() {
		boolean validName = false;
		String name = "";
		int index = -1;
		
		if (students.size() == 0) {
			System.out.println("Error: There are no students in the system.");
		} else {
			System.out.println("Type in the name of the student you want to remove");
			for (int i = 0; i < students.size(); i++) {
				System.out.println(students.get(i).getName() + " \t ID: " + students.get(i).getID());
			}
			
			while(!validName) {
				name = userScan.nextLine();
				for (int i = 0; i < students.size(); i++) {
					if (students.get(i).getName().equals(name)) {
						validName = true;
						index = i;
					}
				}
				if (!validName) {
					System.out.println("Error: That name is not registered in the system");
				}
			}
			
			students.remove(index);
			System.out.println("Student removed.");
			
		}
		
		
	}
	
	/**
	 * Method that asks the user to input information of the assignment that they want to add, 
	 * and adds it according to user input
	 * 
	 * @author Ryan
	 */
	public void manualAddAssignment() {
		boolean done = false;
		String nm = "";
		double possible = -1;
		double erned = -1;
		String cateName = "";
		
		if (students.size() == 0) {
			System.out.println("Error: There are no students to add the assignment to.");
		} else if (ultrabook instanceof CategoryGradebook && ultrabook.getCategorySize() == 0) {
			System.out.println("Error: There are no categories to add assignments in.");	
		} else {
			nm = askName();
			possible = askPointsPossible();
			
			if (this.ultrabook instanceof CategoryGradebook) {
				System.out.println("Type in the category you want to put this under.");
				printCategories();
				userScan.nextLine();
				while (!done) {
					cateName = userScan.nextLine();
					if (checkIfCateExists(cateName)) {
						done = true;
					} else {
						System.out.println("Error: Category does not exist");
					}
				}
			}
			
			for (int i = 0; i < students.size(); i++) {
				erned = -1;
				System.out.println("Type in the points earned by " + students.get(i).getName());
				while (erned < 0) {
					erned = askDouble();
					if (erned < 0) {
						System.out.println("Error: Points earned cannot be negative.");
					}
				}
				if (this.ultrabook instanceof CategoryGradebook) {
					students.get(i).getGradebook().addAssignment(new CategoryAssignment(nm, erned, possible, cateName));
				} else {
					students.get(i).getGradebook().addAssignment(new Assignment(nm, erned, possible));
				}
			}
			
			if (this.ultrabook instanceof CategoryGradebook) {
				ultrabook.addAssignment(new CategoryAssignment(nm, 0, possible, cateName));
			} else {
				ultrabook.addAssignment(new Assignment(nm, 0, possible));
			}
			
			System.out.println("Assignment added successfully");
		}
		
	}
	
	/**
	 * Method which asks the user to type in the name of the assignment. The assignment 
	 * entered in cannot have the same name with another assignment.
	 * 
	 * @author Steven
	 * @return String that is a valid name for the assignment
	 */
	public String askName() {
		System.out.println("Type in the name of the assignment: ");
		while (true) {
			String nm = userScan.nextLine();
			if (!checkIfAssignmentExists(nm)) {
				return nm;
			} else {
				System.out.println("Error: There's already an assignment with that name.");
			}
		}
	}
	
	/**
	 * Method which asks the user to type in the possible points earned by the assignment. 
	 * The only restriction is that the possible points cannot be negative.
	 * 
	 * @author Steven
	 * @return Double that is a valid points total for the assignment
	 */
	public double askPointsPossible() {
		System.out.println("Type in the possible total points: ");
		while (true) {
			double possible = askDouble();
			if (possible < 0) {
				System.out.println("Error: Points possible cannot be negative.");
			} else {
				return possible;
			}
		}
	}
	
	
	/**
	 * Helper method that returns a boolean indicating whether the assignment exists or not
	 *
	 * @author Ryan
	 * @param name - The name used to check the assignment's existence
	 * @return Boolean indicating whether the assignment exists or not
	 */
	public boolean checkIfAssignmentExists(String name) {
		for (int i = 0; i < ultrabook.getArrayLength(); i++) {
			if (name.equals(ultrabook.getAssignment(i).getName())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method which asks the user which assignment that want to remove, and removes it.
	 * 
	 * @author Ryan
	 */
	public void manualRemoveAssignment() {
		boolean validAsgn = false;
		String asgn = "";
		
		if (students.size() != 0) {
			if (ultrabook.getArrayLength() == 0) {
				System.out.println("Error: There are no assignments to begin with.");
			} else {
				System.out.println("The following assignments are in the gradebook: ");
				for (int i = 0; i < ultrabook.getArrayLength(); i++) {
					System.out.println(ultrabook.getAssignment(i).getName());
				}
				System.out.println();
				System.out.println("Type in the assignment to be removed: ");
				
				while (!validAsgn) {
					asgn = userScan.nextLine();
					for (int i = 0; i < ultrabook.getArrayLength(); i++) {
						if (ultrabook.getAssignment(i).getName().equals(asgn)) {
							validAsgn = true;
						} 
					}
					if (!validAsgn) {
						System.out.println("Error: That assignment doesn't exist");
					}
				}
		
				for (int i = 0; i < students.size(); i++) {
					Gradebook temp = students.get(i).getGradebook();
					
					for (int j = 0; j < temp.getArrayLength(); j++) {
						if (temp.getAssignment(j).getName().equals(asgn)) {
							temp.removeAssignment(j);
						}
					}
				}
				
				for (int k = 0; k < ultrabook.getArrayLength(); k++) {
					if (ultrabook.getAssignment(k).getName().equals(asgn)) {
						ultrabook.removeAssignment(k);
					}
				}	
				System.out.println("Assignment removed.");
			}
		} else {
			System.out.println("Error: There are no students in the system.");
		}
	}
	
	/**
	 * Method that prints out each student's name, ID, and their assignments.
	 * 
	 * @author Steven
	 */
	public void printStudentAssignments() {
		if (students.size() != 0) {
			for (int i = 0; i < students.size(); i++) {
				System.out.println(students.get(i).getName() + " \t ID: " + students.get(i).getID());
				if (students.get(i).getGradebook().getArrayLength() != 0) {
					students.get(i).getGradebook().printAssignments();
				} else {
					System.out.println("This student has no assignments.");
				}
				System.out.println("--------------------------------------------------------------------------------");
			}
		} else {
			System.out.println("Error: There are no students in the system.");
		}
	}
	
	/**
	 * Method which prints out the name, ID, assignments, and grades of one 
	 * student, selected by the user.
	 * 
	 * @author William
	 */
	public void printOneStudent() {
		boolean validName = false;
		String name = "";
		int index = -1;
		
		if (students.size() == 0) {
			System.out.println("Error: There are no students in the system.");
		} else {
			System.out.println("Type in the name of the student you want to examine");
			for (int i = 0; i < students.size(); i++) {
				System.out.println(students.get(i).getName() + " \t ID: " + students.get(i).getID());
			}
			
			while(!validName) {
				name = userScan.nextLine();
				for (int i = 0; i < students.size(); i++) {
					if (students.get(i).getName().equals(name)) {
						validName = true;
						index = i;
					}
				}
				if (!validName) {
					System.out.println("Error: That name is not registered in the system");
				}
			}
			
			System.out.println("--------------------------------------------------------------------------------");	
			System.out.println(students.get(index).getName() + " \t ID: " + students.get(index).getID());
			students.get(index).getGradebook().printAssignments();
			System.out.println();
			System.out.println("Grade: " + String.format("%.2f", students.get(index).getGradebook().getGrade() * 100) + "%");
			System.out.println("--------------------------------------------------------------------------------");	
		}	
	}
	
	/**
	 * Method that checks for the inputted name to see if the category exists, and returns 
	 * a boolean based on its results
	 * 
	 * @author Ryan
	 * @param str - Name of the category being searched for
	 * @return Boolean indicating whether or not the name of the category was found
	 */
	public boolean checkIfCateExists(String str) {
		for (int i = 0; i < ultrabook.getCategorySize(); i++) {
			if (str.equals(ultrabook.getCategory(i))) {
				return true;
			}
		}
		return false;
				
//		if (ultrabook.getGradebook().getCategoryIndex(str) == -1){
//			return false;
//		} else {
//			return true;
//		}
		
	}
	
	/**
	 * Method that sets the csv file being used via user input
	 * 
	 * @author William
	 */
	public void setFile() {
		students = new ArrayList<Student>();
		
		System.out.println("Type in a file: ");
		boolean done = false;
		
		while (!done) {
			try {
				String input = userScan.nextLine();
				file = new File(input);
				fileScan = new Scanner(file);
				done = true;
			} catch (Exception e){
				System.out.println("Error: Not a valid file.");
			}
		}
		
	}
	
	/**
	 * Method that processes the file using String arrays. Adds the assignments in it, and categories 
	 * if applicable.If the file being opened is not meant for the current type of gradebook, it will warn the 
	 * user that the file is not compatible, and add nothing.
	 * 
	 * @author William
	 */
	public void openFile() {
		String[] holder = fileScan.nextLine().split(",");
		
		if (holder[0].equals("Category")) {
			ultrabook = new CategoryGradebook();
			openCateFile(holder);
		} else if (holder[0].equals("Total Points")) {
			ultrabook = new TotalPointsGradebook();
			openTotalPtsFile(holder);
		} else {		
			System.out.println("Error: File is not compatable.");
		}
	}
	
	/**
	 * Processes the category style file, adding new students, the categories, and their assignments.
	 * 
	 * @author William
	 * @param holder - String array used to process the file
	 */
	public void openCateFile(String[] holder) {
		while (!holder[0].equals("end")) {	
			holder = fileScan.nextLine().split(",");
			if (!holder[0].equals("end")) {
				students.add(new Student(holder[0], convertStringtoInt(holder[1]), new CategoryGradebook()));
			}			
		}	
		holder[0] = "";		
		while (!holder[0].equals("end")) {
			
			holder = fileScan.nextLine().split(",");
			if (!holder[0].equals("end")) {
				for (int i = 0; i < students.size(); i++) {
					students.get(i).getGradebook().addCategory(holder[0], ((double)convertStringtoInt(holder[1])/100));
				}
				ultrabook.addCategory(holder[0],((double)convertStringtoInt(holder[1])/100));
			}	
		}	
		while (fileScan.hasNextLine()) {
			holder = fileScan.nextLine().split(",");
			String name = holder[0];
			double possible = convertStringtoInt(holder[1]);
			String cate = holder[2];
			

			ultrabook.addAssignment(new CategoryAssignment(name, 0, possible, cate));
			
			for (int j = 0; j < students.size(); j++) {
				double earned = convertStringtoInt(holder[j + 3]);

				CategoryAssignment asgn = new CategoryAssignment(name, earned, possible, cate);
				
				students.get(j).getGradebook().addAssignment(asgn);
			}
			
		}
		System.out.println("File processed.");
	}
	
	/**
	 * Processes the total points style file, adding new students and their assignments.
	 * 
	 * @author William
	 * @param holder - String array used to process the file 
	 */
	public void openTotalPtsFile(String[] holder) {
		while (!holder[0].equals("end")) {
			holder = fileScan.nextLine().split(",");
			if (!holder[0].equals("end")) {
				students.add(new Student(holder[0], convertStringtoInt(holder[1]), new TotalPointsGradebook()));
			}
		}
		holder[0] = "";
		
		while (fileScan.hasNextLine()) {
			holder = fileScan.nextLine().split(",");
			String name = holder[0];
			double possible = convertStringtoInt(holder[1]);
			
			ultrabook.addAssignment(new Assignment(name, 0, possible));
			
			for (int j = 0; j < students.size(); j++) {
				double earned = convertStringtoInt(holder[j + 2]);
				Assignment asgn = new Assignment(name, earned, possible);
				students.get(j).getGradebook().addAssignment(asgn);
			}	
		}
		System.out.println("File processed.");
	}
	
	/**
	 * Prints out the student's names, id, and their grade
	 * 
	 * @author Ryan
	 */
	public void printStudentGrades() { 
		double averageGrade = 0;
		
		if (students.size() != 0) {
			for (int i = 0; i < students.size(); i++) {
				System.out.println(students.get(i));
				averageGrade += students.get(i).getGradebook().getGrade();
			}
			System.out.println();
			System.out.println("The average grade is: " + String.format("%.2f",(averageGrade/students.size() * 100)) + "%");
			
		} else {
			System.out.println("Error: There are no students in the system.");
		}
		
	}
	
	/**
	 * Method that drops the most impactful assignment pulling the grade down of the 
	 * category that the user wants to drop from. If there's only one assignment, it 
	 * will remove it even if the overall grade drops. Also prints out the grade before and 
	 * after the removal. If there are no categories, or if all the categories already 
	 * have an assignment removed from them, it will do nothing.
	 * 
	 * @author Steven
	 */
	public void cateDropLowest() {
		String cate = "";
		boolean done = false;
		
		if (!checkAvailableDrop()) {
			System.out.println("Error: There are no more available categories to drop from.");
		} else if (students.size() == 0){
			System.out.println("Error: There are no students to drop for.");
		} else {
			System.out.println("Type the category to drop the lowest from");	
			for (int i = 0; i < ultrabook.getCategorySize(); i++) {
				if (!ultrabook.getDropped(i)) {
					System.out.println(ultrabook.getCategory(i));
				}
			}
			
			while (!done) {		
				cate = userScan.nextLine();	
				if (!(checkIfCateExists(cate))) {
					System.out.println("Error: Category does not exist");		
				} else if (ultrabook.getDropped(ultrabook.getCategoryIndex(cate))){
					System.out.println("Error: You already dropped an assignment from this category.");		
				} else {
					done = true;
				}
			}		
			
			System.out.println("These are the students and grades before the removal:");
			printStudentGrades();
			System.out.println();
			for (int i = 0; i < students.size(); i++) {
				students.get(i).getGradebook().dropLowestAssignment(cate);
			}		
			((CategoryGradebook)ultrabook).setDropped(ultrabook.getCategoryIndex(cate), true);
			System.out.println("These are the students and grade after the removal:");
			printStudentGrades();
		}
		
		
	}
	
	/**
	 * Returns a boolean indicating whether or not there are any categories that are available 
	 * to drop from
	 * 
	 * @author Steven
	 * @return Boolean indicating if there's a category to drop from
	 */
	public boolean checkAvailableDrop() {
		boolean available = false;
		
		for (int i = 0; i < ultrabook.getCategorySize(); i++) {
			if (!ultrabook.getDropped(i)) {
				available = true;
			}
		}
		return available;
	}
	
	/**
	 * Method that drops the most impactful assignment dragging the grade down. If there is only 
	 * one assignment, it will drop it even if it lowers the overall grade.
	 * 
	 * @author Steven
	 */
	public void totalPtsDropLowest() {
	
		if (students.size() == 0) {
			System.out.println("Error: There are no students to drop for");
		} else if (!ultrabook.getDropped()) {
			if (ultrabook.getArrayLength() == 0) {
				System.out.println("Error: There are no assignments to remove!");
			} else {
				System.out.println("These are the students and grades before the removal:");
				printStudentGrades();
				System.out.println();
				for (int i = 0; i < students.size(); i++) {
					students.get(i).getGradebook().dropLowestAssignment();
				}		
				System.out.println("These are the students and grade after the removal:");
				printStudentGrades();
			}
		} else {
			System.out.println("Error: You have already dropped an assignment previously.");
		}
	}
	

	
	/**
	 * Method that prints out the categories and their weights in the system
	 * 
	 * @author William
	 */
	public void printCategories() {
		System.out.println("The following categories are in the system: ");
		
		if (ultrabook.getCategorySize() == 0) {
			System.out.println("None. There are no categories in the system yet!");
		} else {
			for (int i = 0; i < ultrabook.getCategorySize(); i++) {
				System.out.println(ultrabook.getCategory(i) + ",\t Weight: " + ultrabook.getWeight(i) * 100 + "%");
			}
		}
		
	}
	
	/**
	 * Method that adds a category with a name and weight inputted by the user.
	 * 
	 * @author William
	 */
	public void manualaddCategory() {
		boolean done = false;
		String cate = "";
		double wgt = -1;
		

		System.out.println("Type in the name of the category");
		while (!done) {
			cate = userScan.nextLine();
			if (checkIfCateExists(cate)) {
				System.out.println("Error: That category already exists.");
			} else {
				done = true;
			}	
		}
		
		done = false;
		System.out.println("Type in the percent weight of the category in between 0 and 100 (ex: 40 -> 40%)");
		System.out.println("Ideally, the total of the weights would add up to 100%.");
		while (!done) {
			wgt = askDouble();
			if (wgt >= 0 && wgt <= 100) {
				done = true;
			} else {
				System.out.println("Error: Input is not between 0 and 100");
			}
		}
		
		ultrabook.addCategory(cate, wgt/100);
		
		for (int i = 0; i < students.size(); i++) {
			students.get(i).getGradebook().addCategory(cate, wgt/100);
		}
		System.out.println("Category added successfully");
	}
	
	/**
	 * Method that asks the user the category that it wants to remove, then removes it and 
	 * all of the assignments of each student belonging to that category.
	 * 
	 * @author Ryan
	 */
	public void manualRemoveCategory() {
		boolean done = false;
		String cate = "";
		
		if (ultrabook.getCategorySize() == 0) {		
			System.out.println("There are no categories to remove");
		} else {
			System.out.println("Type the category you want to remove");	
			printCategories();
			
			while (!done) {		
				cate = userScan.nextLine();	
				if (!(checkIfCateExists(cate))) {
					System.out.println("Error: Category does not exist");		 	
				} else {
					done = true;
				}
			}
			
			for (int i = 0; i < students.size(); i++) {
				Gradebook temp = students.get(i).getGradebook();
				for (int j = 0; j < temp.getCategorySize(); j++) {
					if (temp.getCategory(j).equals(cate)) {
						temp.removeCategory(j);
					}
				}
				
			}
			
			ultrabook.removeCategory(ultrabook.getCategoryIndex(cate));
			System.out.println("Category removed.");
		}
		
		
	}
	
	
	
	/**
	 * Helper method with the sole purpose of converting the decimal strings in the file 
	 * into its double form, but only works normally with values less than 1. 
	 * 
	 * Note from Steven: ...turns out we can use parsing, why fix it if it ain't broke.
	 * 
	 * @author Ryan
	 * @param str - String being converted to a double
	 * @return The double form of the string
	 */
	public double convertStringToDeci(String str) {
		double temp = 0;
		int index = str.length() - 1;
		int places = 1;
		
		while (index >= 0 && !(str.charAt(index) == '.')) {
			temp += ((double)(str.charAt(index)) - 48) * places;
			places *= 10;
			index--;
		}
		return temp/places;
	}
	
	/**
	 * Helper method with the sole purpose of converting the String integers in the file 
	 * into its integer form, but only works normally with whole numbers.
	 * 
	 * Note from Steven: ...turns out we can use parsing, why fix it if it ain't broke.
	 * 
	 * @author William
	 * @param str - String being converted to an int
	 * @return The integer form of the string
	 */
	public int convertStringtoInt(String str) {
		int temp = 0;
		int index = str.length() - 1;
		int places = 1;
		
		while (index >= 0) {
			temp += ((int)(str.charAt(index)) - 48) * places;
			places *= 10;
			index--;
		}
		
		return temp;
	}
	
	/**
	 * A method which filters out inputs other than integers (ex: Strings), and returns the valid 
	 * integer that the user inputs. If anything other than an integer was inputed, it prints an 
	 * error statement, and waits for the user to type in another input.
	 * 
	 * @author Steven
	 * @return An valid integer inputed by the user
	 */
	public int askInt() {
		boolean valid = false;
		int input = 0;
		while (!valid) {
			try {
				input = userScan.nextInt();
				return input;
			} catch (Exception e) {
				userScan.nextLine();
				System.out.println("Error: Input is not an integer.");
			}
		}
		return input;
	}
	
	

	/**
	 * A method which filters out inputs other than doubles (ex: Strings), and returns the valid 
	 * double that the user inputs. If anything other than a double was inputed, it prints an 
	 * error statement, and waits for the user to type in another input.
	 * 
	 * @author Steven
	 * @return A valid double inputted by the user
	 */
	public double askDouble() {
		boolean valid = false;
		double input = 0;
		while (!valid) {
			try {
				input = userScan.nextDouble();
				return input;
			} catch (Exception e) {
				userScan.nextLine();
				System.out.println("Error: Input is not a double.");
			}
		}
		return input;
	}
	
	/**
	 * Prints out the average percentage of each assignment in the system.
	 * 
	 * @author Steven
	 */
	public void printAllAverages() {
		if (ultrabook.getArrayLength() != 0) {
			for (int i = 0; i < ultrabook.getArrayLength(); i++) {
				double pointsEarned = 0;
				double pointsPossible = 0;
				
				for (int j = 0; j < students.size(); j++) {
					Gradebook temp = students.get(j).getGradebook();
					
					for (int k = 0; k < temp.getArrayLength(); k++) {
						if (ultrabook.getAssignment(i).getName().equals(temp.getAssignment(k).getName())) {
							pointsEarned += temp.getAssignment(k).getEarned();
							pointsPossible += temp.getAssignment(k).getPoints();
						}
						
					}		
				}
				System.out.println(ultrabook.getAssignment(i).getName() + " average: " + (String.format("%.2f", (pointsEarned/pointsPossible) * 100)) + "%");
			}
		} else {
			System.out.println("Error: There are no assignments.");
		}
		
	}
	
	
	/**
	 * The primary method which uses the methods in the class in order to run the gradebook program 
	 * that can interact with the user. Upon exiting the program, it gives a farewell.
	 * 
	 * @author Steven
	 */
	public void runProgram() {
		printIntroduction();
		setGradebookType();		
		while(!doneRun) {
			printSelections();
			activateChoice();
		}
		System.out.println("Program terminated. Have a great day!");
	}
	
}
