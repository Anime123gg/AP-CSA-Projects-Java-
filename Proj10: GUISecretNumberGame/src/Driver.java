/**
 * Driver class, where the program actually starts running. 
 * Runs a program that plays mastermind, a game about guessing
 * the correct 5 digit secret number.
 * 
 * @author - Matthew C
 */
public class Driver {
	
	/**
	 * Main method that runs the game Mastermind, and have the user inputs into the console to progress the game.
	 * 
	 * @author - Matthew C
	 * @param args - an array of string arguments passed into the main functions
	 */
	public static void main (String[] args) {
		GameFunctions game = new GameFunctions();
		game.runGame();	
		
	}
	
}
