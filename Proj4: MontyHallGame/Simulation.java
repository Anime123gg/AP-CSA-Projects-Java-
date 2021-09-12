
/** Simulation class creates a GamePlayer object that runs 1000 times
 * This will run multiple times and print out the stats at the end
 * @author William Ngo
 * @author Noah Park
 * @author Jason Nguyen
 *
 */
public class Simulation {
	
	/** This constructor creates a GamePlayer that runs a set amount of times
	 * @param n The number of times the simulation runs
	 */
	// William did this part
	public Simulation(int n) {
		int repeat = n;
		System.out.println();
		System.out.println("Simulation Runs");
		GamePlayer sim = new GamePlayer(repeat);

	}
}