import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
/**Last Name: Nguyen, Ngo
 * First Name: Alexander William
 * ID Number: 12108173, 12019029
 * Period: 4
 * 
 * The purpose of this class is to read the file given and formats it accordingly
 * The Grand Palace Hotel only offers Lodging, Conference, Dinner as Services.
 * @author William Ngo, Alex Nguyen
 * 
 */
public class GPHotel {
	private static int start=0; //used to detect where the part method can start finding a certain symbol
	private static int lCount=0; //number of lodging services
	private static double lAmount=0; //total cost of lodging
	private static int cCount=0;     //number of conference services
	private static double cAmount=0; //total cost for the conference services
	private static int dCount=0;     //number of dinners
	private static double dAmount=0; //total cost of dinner
	
	/**
	 * This method returns a portion of the information of a particular service
	 * Precondition: The str inputed states the name of the service first, then the type of serve,
	 * then the cost, and then finally the date at which the service was requested.
	 * @param str - the string containing information about a service
	 * @param symbol - the thing that separates the information from each other
	 * @return part - the portion of the information
	 */
	public static String part(String str, String symbol) {
		int index = str.indexOf(symbol, start);
		String part;
		if (index == -1) {
			part = str.substring(start);
			return part;
		}
		part = str.substring(start, index);
		start = index + 2;
		return part;
	}
	
	/**
	 * This method prints out the summary of the services provided at Grand Palace Hotel
	 * It also reformats the info so that the amount for each services would be correctly formated as
	 * dollars (with 2 decimal points) 
	 * Alexander
	 */
	public static void summary() {
		String dinnerAmount = String.format("%.2f", dAmount);
		String lodgeAmount = String.format("%.2f", lAmount);
		String confAmount = String.format("%.2f", cAmount);
		System.out.println("***************************Summary**************************");
		System.out.println("Dinner\n  Number: " + dCount + "\n  Amount: " + dinnerAmount);
		System.out.println("Lodging\n  Number: " + lCount + "\n  Amount: " + lodgeAmount);
		System.out.println("Conference\n  Number: " + cCount + "\n  Amount: " + confAmount);

	}
	
	/**
	 * Method that detects which service is needed and adds to the number of each service and the total price
	 * @param type - type of service
	 * @param amt - amount of money
	 * William
	 */
	private static void detect(String type, double amt) {
		if (type.equals("Lodging")){
			lCount++;
			lAmount+=amt;
		}
		else if (type.equals("Conference")){
			cCount++;
			cAmount+=amt;
		}
		else if (type.equals("Dinner")){
			dCount++;
			dAmount+=amt;
		}
	}
	
	/**
	 * Method that formats the name, service, dollar amount, and date of each sale
	 * Uses the part method to extract each piece of information
	 * @param str - Raw string from the text file
	 * @return - Formatted string
	 * Alexander
	 */
	public static String line(String str) {
		String name = String.format("%15s", part(str, ":"));
		String type = part(str, ",");
		type.trim();
		String num = part(str, ",");
		double d = Double.parseDouble(num);
		detect(type,d);
		type = String.format("%15s", type);
		num = String.format("%.2f", d);
		num = "$" + num;
		num = String.format("%15s", num);
		String date = String.format("%15s", part(str, ","));
		String result = name + type + num + date;
        start=0;
        return result;
	}

}
