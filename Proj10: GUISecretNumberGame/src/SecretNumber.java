/**
 * A class dedicated to methods related to the secret number, or 5 digit values.
 * Holds the 5 digit value in an array.
 *
 *@author - Steven P, Hayk M, Matthew C
 */
public class SecretNumber {
	private int[] secretNum = new int[5];
	
	/**
	 * Creates a secret number object with a randomly generated secret number array.
	 * The secret number's array will only consist of unique, non-matching values.
	 * 
	 * @author - Steven P
	 */
	public SecretNumber() {
		boolean valid = false;
		
		while (!valid) {
			for (int i = 0; i < secretNum.length; i++) {
				secretNum[i] = (int)(Math.random() * 10);
			}
			
			int number = secretNum[0] * 10000 + secretNum[1] * 1000 + secretNum[2] * 100 + secretNum[3] * 10 + secretNum[4];
			
			if (checkUnique(number)) {
				valid = true;
			}
			
		}
		
		
	}
	
	/**
	 * Creates a secret number object, and takes in an integer, and sets it as the secret number using an array.
	 * 
	 * @author - Steven P
	 * @param input - value to be split up and individual digits to be inserted into an array
	 */
	public SecretNumber(int input) {
		makeSecretNumber(input, 4);
	}
	
	public void makeSecretNumber (int input, int index) {
		if (index == 0) {
			secretNum[index] = input;
		}
		else {
			secretNum[index] = input % 10;
			makeSecretNumber(input/10, index-1);
		}
	}
	
	/**
	 * Checks how many matching digits values there are between this secret number and the indicated secret number object.
	 * 
	 * @author - Hayk M
	 * @param input - secretNumber object that is compared against the current object
	 * @return digits - int value that indicates how many digits were the same between current object and input
	 */
	public int correctDigits(SecretNumber input) {
		int digits = 0;
		boolean[] checkList = {false, false, false, false, false, false, false, false, false, false};
		
		
		for (int i = 0; i < secretNum.length; i++) {
			for (int j = secretNum.length - 1; j >= 0; j--) {
				if ( (secretNum[j] == input.get(i)) && !checkList[input.get(i)] ) {
					checkList[input.get(i)] = true;
					digits++;
				}
			}
			
		}
		
		return digits;
		
		
	}
	
	
	/**
	 * Returns the number of matching positions the indicated secret number share with this secret number.
	 * 
	 * @author - Matthew C
	 * @param input - SecretNumber object whose positions will be checked against the current one
	 * @return positions - int value which indicates the amount of positions which are correct between this object and input
	 */
	public int correctPositions(SecretNumber input) {
		int positions = 0;
		
		for (int i = secretNum.length - 1; i >= 0; i--) {
			if (input.get(i) == secretNum[i]) {
				positions++;
			}
		
		}
		
		return positions; 
		
	}
	
	
	/**
	 * Checks if the secret number is the same as the value represented by the secret number.
	 * 
	 * @author - Hayk M
	 * @param input - SecretNumber object to be checked
	 * @return boolean - true if this object is the same as input, false otherwise
	 */
	public boolean isSame(SecretNumber input) {
		if (correctPositions(input) == 5) {
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * Returns a boolean indicating whether or not all the digits of the potential secret number is unique.
	 * 
	 * @author - Steven P
	 * @param test - int value to be checked digit by digit
	 * @return boolean - true if tested input is unique, false otherwise
	 */
	public boolean checkUnique(int test) {
		int[] numList = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		boolean[] checkList = {false, false, false, false, false, false, false, false, false, false};
		
		int remain = test;
		int checkDigit = remain % 10;
		
		while (remain > 0) {
			for (int i = 0; i < numList.length; i++) {
				if (checkDigit == numList[i]) {
					if (checkList[i]) {
						return false;
					} else {
						checkList[i] = true;
						remain /= 10;
						checkDigit = remain % 10;
					}
				}
			}
		}
		return true;
	}
	
	
	/**
	 * Getter method that returns a value of one of the elements within the secretNum array.
	 * 
	 * @author - Matthew C
	 * @param i - index of SecretNumber to be pulled
	 * @return int - value corresponding to index i of secretNum[]
	 */
	public int get(int i) {
		return secretNum[i];
	}
	
	
	/**
	 * toString dedicated to returning the secret number for testing purposes.
	 * 
	 * @author - Steven P
	 * @return String - fully assembled string 
	 */
	public String toString() {
		return "" + secretNum[0] + secretNum[1] + secretNum[2] + secretNum[3] + secretNum[4]; 
	}
	
}