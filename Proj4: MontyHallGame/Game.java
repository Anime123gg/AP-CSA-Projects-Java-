import java.util.Scanner;

/** The Game class creates a single game that either the user plays or a simulation plays.
 * Uses overloaded methods for the user and simulation playing the game
 * @author William Ngo
 * @author Noah Park
 * @author Jason Nguyen
 */
public class Game {
	private int select;
	private int winner;
	private Scanner scan;
	private String result;
	private boolean swap = false;
	
	/** Default constructor that runs the game one time 
	 * The user will input the values of the door they choose
	 */
	// William did this part
	public Game() {
		scan = new Scanner(System.in);
		this.assign();
		this.pick();
		this.swapOrStay();
		this.check();
		System.out.println(this.getResult());
	}

	/** This constructor runs the game one time
	 * The code will create a random number from 1-3 for the door choice and if they want to swap or not
	 * @param one A random number from 1-3 that will be the initial door choice
	 * @param two A random number from 1-2 that will determine if you want to swap or not
	 */
	// Noah did this part
	public Game(int one, int two) {
		this.assign();
		this.pick(one);
		this.swapOrStay(two);
		this.check();
		System.out.println(this.getResult());
		
	}
	
	/** Pick method prompts user for a door from 1-3 and stores it in a variable
	 *  If there is a number other than 1-3, it will ask again
	 */
	// Jason did this part
	public void pick() {
		System.out.println("Pick a door (1, 2 or 3): ");
		select = scan.nextInt();
		while (select != 1 && select != 2 && select != 3) {
			System.out.println("Error: Pick a new door (1,2, or 3)");
			select = scan.nextInt();
		}
	}
	
	/** This pick method passes a random number that will be the selected door and stores it
	 * If the random number isn't 1-3, it will generate another random number
	 * @param one A random number from 1-3 for the door choice
	 */
	// William did this part
	public void pick(int one) {
		select = one;
		while (select != 1 && select != 2 && select != 3) {
			select = (int) (Math.random() * 3) + 1;
		}
	}
	
	/** Assigns a random door with the car
	 * 
	 */
	// Noah did this part
	public void assign() {
		winner = (int) (Math.random() * 3) + 1;
	}
	
	/** This method chooses the door that has a goat
	 *  If the remaining 2 doors are both goats, then it will randomly choose a door with a goat
	 * @return The door number that has a goat
	 */
	// Jason did this part
	public int reduceDoor() {
		int d = (int) (Math.random() * 3) + 1;
		
		while (d == select || d == winner) {
			d = (int) (Math.random() * 3) + 1;
		}
		
		return d;
	}
	
	/** This method selects the door to swap if the user chooses to
	 * @param door The reduced door 
	 */
	// Jason did this part
	public void chooseDoor(int door) {
		if (select == 1 && door == 2) {
			select = 3;
		}
		else if (select == 1 && door == 3) {
			select = 2;
		}
		else if (select == 2 && door == 1) {
			select = 3;
		}
		else if (select == 2 && door == 3) {
			select = 1;
		}
		else if (select == 3 && door == 1) {
			select = 2;
		}
		else {
			select = 1;
		}
	}
	
	/** This method selects the door to swap based on the simulation results
	 * @param door The reduced door
	 * @param r The simulation choice to swap or not
	 */
	// Noah did this part
	public void chooseDoor(int door, int r) {
		if (r == 1) {
			if (select == 1 && door == 2) {
				select = 3;
			}
			else if (select == 1 && door == 3) {
				select = 2;
			}
			else if (select == 2 && door == 1) {
				select = 3;
			}
			else if (select == 2 && door == 3) {
				select = 1;
			}
			else if (select == 3 && door == 1) {
				select = 2;
			}
			else {
				select = 1;
			}
			swap = true;
		}
	}
	
	/** Prompts the user if they want to switch or not after revealing a door with a goat
	 *  If the user says yes, then it will automatically swap their door with the other door that wasn't revealed yet
	 */
	// William did this part
	public void swapOrStay() {
		int door = this.reduceDoor();
		System.out.println("Door " + door + " has a goat. Would you like to swap? (Enter y (for yes) and n (for no))");
		String r = scan.next();
		while (!r.equalsIgnoreCase("y") && !r.equalsIgnoreCase("n")) {
			System.out.println("Error: Type y for yes or n for no");
			r = scan.next();
		}
		if (r.equalsIgnoreCase("y")) {
			this.chooseDoor(door);
		}
		System.out.println("Your current door is now Door " + select);
	}

	/** This swap method takes a random number that determines if the swap occurs or not
	 *  If the number is 1, then it swaps in the simulation
	 * @param two A random number 1-2 to see if the simulation swaps or not
	 */
	// Noah did this part
	public void swapOrStay(int two) {
		int door = this.reduceDoor();
		int r = two;
		this.chooseDoor(door, r);
	}
	
	/** Checks if the selected door is a winner and has a car
	 *  Also prints the correct door with the car
	 */
	// Jason did this part
	public void check() {
		if (select == winner) {
			System.out.println("The correct door was Door " + winner);
			result = "You Win!";
		}
		else {
			System.out.println("The correct door was Door " + winner);
			result = "You Lose!";
		}
	}
	
	/** Returns the result after playing the game
	 * @return A string with the result
	 */
	// William did this part
	public String getResult() {
		return result;
	}
	
	/** Returns a boolean if the user/simulation swapped or not
	 * @return A boolean to see if user/sim swapped or not
	 */
	// Noah did this part
	public boolean getSwap() {
		return swap;
	}
	
}

	
	

