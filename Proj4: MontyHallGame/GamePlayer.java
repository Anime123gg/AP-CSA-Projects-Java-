import java.util.Scanner;

/** GamePlayer class calls the Game class constructor for the user to play
 * Also creates a constutor for the simulation that repeats multiple times
 * Creates methods for summary and stats used by the Simulation class
 * @author William Ngo
 * @author Noah Park
 * @author Jason Nguyen
 *
 */
public class GamePlayer {
	private int win1 = 0;
	private int lose1 = 0;
	private int win2 = 0;
	private int lose2 = 0;
	private int total = 0;
	
	/** This constructor plays the game through user input 
	 *  This constructor will keep on playing until the user stops it
	 */
	// Jason did this part
	public GamePlayer() {
		System.out.println("Welcome to the Monty Hall Game! The goal is to choose the door with a car and not the goats.");
		System.out.println("You will get the option to swap your door after the host reveals one door with a goat. Good Luck!");
		int input = 0;
		while (input != 2 && input != -1) {
			Game g = new Game();
			this.setCounter(g.getResult());
			this.summary();
			input = this.retry();
		}
	}
	
	/** This constructor plays the game a certain amount of times
	 * @param repeat The number of times the game runs
	 */
	// William did this part
	public GamePlayer(int repeat) {
		for (int i = 0; i < repeat; i++) {
			int one = (int) (Math.random() * 3) + 1;
			int two = (int) (Math.random() * 2) + 1;
			int three = (int) (Math.random() * 3) + 1 ;
			Game g = new Game(one, two);
			this.setCounter(g.getResult(), g.getSwap());
		}
		this.stats();
		this.summary();
	}

	/** Counts the number of times you win/lose and the total amount of games
	 * @param out The result from the game
	 */
	// Noah did this part
	public void setCounter(String out) {
		if (out.equals("You Win!")){
			win1++;
			total++;
		}
		else if (out.equals("You Lose!")){
			lose1++;
			total++;
		}

	}
	
	/** Counts the number of times you win/lose and the total number of games
	 * Also counts if you use strategy one or strategy 2 based on the swap boolean
	 * @param out The result of the game
	 * @param swap Boolean that stores if you swapped or not
	 */
	// Jason did this part
	public void setCounter(String out, boolean swap) {
		if (out.equals("You Win!") && swap == true){
			win1++;
			total++;
		}
		else if (out.equals("You Lose!") && swap == true){
			lose1++;
			total++;
		}
		else if (out.equals("You Win!") && swap == false){
			win2++;
			total++;
		}
		else {
			lose2++;
			total++;
		}
	}

	/** Prints a summary of wins, losses, total games, and win %
	 * 
	 */
	// William did this part
	public void summary() {
		System.out.println();
		System.out.println("Number of Wins: " + (win1 + win2));
		System.out.println("Number of Losses: " + (lose1 + lose2));
		System.out.println("Number of Games: " + total);
		System.out.println("Win %: " + String.format("%.2f", (double) (win1 + win2) * 100 / total));
	}
	
	/** Prints total number of games and wins, losses, games, and win % of each strategy
	 * 
	 */
	// Noah did this part
	public void stats() {
		System.out.println("Total Number of Games: " + total);
		System.out.println();
		System.out.println("Number of Games for Strategy 1: " + (win1 + lose1));
		System.out.println("Number of Wins for Strategy 1: " + win1);
		System.out.println("Number of Losses for Strategy 1: " + lose1);
		System.out.println("Win % of Strategy 1: " + String.format("%.2f", (double) win1 * 100 / (win1 + lose1)));
		System.out.println();
		System.out.println("Number of Games for Strategy 2: " + (win2 + lose2));
		System.out.println("Number of Wins for Strategy 2: " + win2);
		System.out.println("Number of Losses for Strategy 2: " + lose2);
		System.out.println("Win % of Strategy 2: " + String.format("%.2f", (double) win2 * 100 / (win2 + lose2)));
	}

	/** Determines if the user wants to play again
	 * Also has try/catch when a user doesn't enter 1 or 2
	 * @return An integer that is used to play again or not in constructor
	 */
	// Jason did this part
	public int retry() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Would you like to play again? Press 1 for yes, 2 for no");
		try {
			int input = scan.nextInt();
			while (input != 1 && input != 2) {
				input = scan.nextInt();
				System.out.println("Error: Type 1 for yes or 2 for no");
				input = scan.nextInt();
			}
			return input;
		}
		catch(Exception e) {
			System.out.println("bad very bad Game Over");
		}
		return -1;
	}
}


