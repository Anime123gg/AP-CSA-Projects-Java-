import java.util.Scanner;

/**
 * 12091029, 12094694, 12108173
 * @author - William Ngo, Daniel Das, Alexander Nguyen
 * Game class that contains the methods that make the game function
 */
public class Game {
	private int tries;
	private int[] secretNumber = new int[5];	
	private int[] playerNumber = new int[5];
	private Scanner scan = new Scanner(System.in);
	
	/**
	 * Constructor that takes in user input to decide the secret number
	 * @author - Daniel Das
	 */
	public Game() {
		
		System.out.println("Enter the secret number as a 5 digit number");
		System.out.println("where all digits are unique, or enter -1");
		System.out.println("to have this program select the secret number.");
		System.out.println("\nSelect your own secret number or enter -1: ");
		String input = scan.next();
		this.check(input);
		
	}
	
	/**
	 * Method that checks the input and determines if it is a valid secret number or if it is -1 and should be randomized
	 * @param input - input string being checked
	 * @author - Alexander Nguyen
	 */
	private void check(String input) {

		while(this.allIntAndFive(input)==false && input.equals("-1")==false ) {
			System.out.println("\'"+ input + "\'"+  " is not a valid secret number.");
			input = scan.next();
		}
		if (input.equals("-1")) {
			this.randomize();
		}
		else {
			this.enterArray(Integer.parseInt(input),secretNumber);
		}

	}


	/**
	 * Method that checks if the inputted string contains exactly 5 unique integers
	 * @param input - inputted string being checked
	 * @return boolean - true if string is 5 unique integers, false if it isn't
	 * @author - William Ngo
	 */
	private boolean allIntAndFive(String input) {
		if (input.length() !=5) {
			return false;
		}
		
		else if (this.allInt(input)==false){
			return false;
		}
		else if (this.allUnique(input)==false){
			return false;
		}
		else {
			return true;
		}
		
	}

	/**
	 * Method that checks if the input string is all int
	 * @param input - String that is checked
	 * @return boolean - true if the string is all int, false if it isn't
	 * @author - Daniel Das
	 */
	private boolean allInt(String input) {
		for (int i=0;i<5;i++) {
			
			if (input.charAt(i) < 48 ||  input.charAt(i) > 57 ) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Method that checks if the input string contains unique numbers
	 * @param input - String that is checked
	 * @return boolean - true if the string is all unique, false if it isn't
	 * @author - Alexander Nguyen
	 */
	private boolean allUnique(String input) {
		for (int i=0;i<5;i++) {
			
			for (int n=i+1;n<4;n++) {
				
				if (input.charAt(i) == input.charAt(n) ) {
					return false;
				}
				
			}
		}
		return true;	
	}

	/**
	 * Method that enters a 5 digit number into an int array with a length of 5
	 * @param a - number being entered into the array
	 * @param array - array that the number is inputted in
	 * @author - William Ngo
	 */
	private void enterArray (int a, int[] array ) {
		int fifth = a % 10;
		a /= 10;
		int fourth = a % 10;
		a /= 10;
		int third = a % 10;
		a /= 10;
		int second = a % 10;
		a /= 10;
		int first = a;
		array[0] = first;
		array[1] = second;
		array[2] = third; 
		array[3] = fourth;
		array[4] = fifth;
	}
	
	/**
	 * Method that creates a random secret number
	 * @author - Daniel Das
	 */
	private void randomize () {
		int[] availableNumbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		boolean[] numbersUsed = {false,false,false,false,false,false,false,false,false,false};
		int index = 0;
		for (int i = 0; i < 5; i++) {
			index = (int)(Math.random() * 10);
			while (numbersUsed[index] == true) {
                index = (int)(Math.random() * 10);
            }
			if (numbersUsed[index]==false) {
				secretNumber[i] = availableNumbers[index];
				numbersUsed[index] = true;
			}
		}
	}

	/**
	 * Method that calculates the number of correct numbers that the user guessed
	 * @param guess - players guess that is being checked
	 * @return int - number of correct numbers
	 * @author - Alexander Nguyen
	 */
	private int numbers(int[] guess) {
		int numbersCorrect = 0;
		boolean[] numbersFound = new boolean[5];
		//this loop goes through the guess array
		for (int i=0;i<5;i++) {
			//this loop goes through the secret number array
			for (int j=0;j<5;j++) {
				if (guess[i] == secretNumber[j]) {
					if (!numbersFound[j]) {
						numbersCorrect++;
						numbersFound[j]=true;
					}
				}
			}
		}
		return numbersCorrect;
}
	
	/**
	 * Method that calculates the number of digits in the correct position
	 * @param guess - array that contains the user's guess
	 * @return int - number of digits in the correct position
	 * @author - William Ngo
	 */
	private int position (int[] guess) {
		int numPosition = 0;
		for (int i = 0; i < 5; i++) {
			if (secretNumber[i] == guess[i]) {
				numPosition++;
			}
		}
		return numPosition;
	}
	

	/**
	 * Method that runs 1 guess
	 * @param guess - user's guess
	 * @author - Daniel Das
	 */
	public void guess (int[] guess) {
		tries++;
		System.out.println("Try number:       " + tries);
		System.out.println("Digits found:     " + numbers(guess));
		System.out.println("Correct position: " + position(guess));
	}
	
	/**
	 * Method that runs a while loop to repeat the game
	 * Checks if the game was won or lost
	 * @author - Alexander Nguyen
	 */
	public void playGame () {
		do {
			System.out.println("Enter your 5 digit guess: ");
			String playerNumS= scan.next();
			try {
			int playerNum = Integer.parseInt(playerNumS);
			while (playerNumS.length() != 5) {
				
				System.out.println("'" + playerNum + "' is not a 5 digit number");
				System.out.println("Enter your 5 digit guess: ");
				playerNumS = scan.next();
				playerNum = Integer.parseInt(playerNumS);
			}
			enterArray(playerNum, playerNumber);
			guess(playerNumber);
			}
			catch(Exception E) {
				System.out.println("'" + playerNumS + "' is not a 5 digit number");
			}
		} while (position(playerNumber) < 5 && tries < 32);  // Plays the game until all numbers are in the correct position or number tries is 32
			
		
		if (tries < 32) {
			System.out.println("You won in " + tries + " tries!");
		}
		else {
			int secretNum = secretNumber[0] * 10000 + secretNumber[1] * 1000 + secretNumber[2] * 100 + secretNumber[3] * 10 + secretNumber[4];
			System.out.println("You lost! The secret number was " + secretNum );
		}
	
	}
	
	/**
	 * Get method for tries
	 * @return tries
	 * @author - William Ngo
	 */
	public int getTries () {
		return tries;
	}
	
	/**
	 * Get method for secretNumber
	 * @return secretNumber
	 * @author - Daniel Das
	 */
	public int[] getSecretNumber () {
		return secretNumber;
	}
	
	/**
	 * Get method for playerNumber
	 * @return playerNumber
	 * @author - Alexander Nguyen
	 */
	public int[] getPlayerNumber () {
		return playerNumber;
	}
	
	/**
	 * Set method for tries
	 * @param a - new tries value
	 * @author - William Ngo
	 */
	public void setTries (int a) {
		tries = a;
	}
	
	/**
	 * Set method for secretNumber
	 * @param b - new secretNumber
	 * @author - Daniel Das
	 */
	public void setSecretNumber (int[] b) {
		secretNumber = b;
	}
	
	/**
	 * Set method for playerNumber
	 * @param c - new playerNumber
	 * @author - Alexander Nguyen
	 */
	public void setPlayerNumber (int[] c) {
		playerNumber = c;
	}


}
