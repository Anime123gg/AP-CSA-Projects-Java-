public class Driver {
	
	/** Main method creates a GamePlayer and Simulation object to play the game as user and simulation 1000 times
	 * @param args Command Line Arguments
	 */
	// Noah did this part
	public static void main(String[] args) {
		GamePlayer game = new GamePlayer();
		int n = 1000;
		Simulation sim = new Simulation(n);
	}
}
