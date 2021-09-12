import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GPHotelDriver {
	/**
	 * Main method that uses a scanner object to read from the text file and prints formatted data
	 * @param args
	 * Alexander, William
	 */
	public static void main(String[] args) {
		try {
			File myObj = new File("Services.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				System.out.println(GPHotel.line(data));
			}
			System.out.println();
			GPHotel.summary();
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
