//https://www.vainolo.com/2015/10/31/changing-the-font-of-a-joptionpane/
//http://www.java2s.com/Tutorials/Java/Swing_How_to/JOptionPane/Create_Multiple_input_in_JOptionPane_showInputDialog.htm
import java.awt.*;
import javax.swing.*;

/**Last Name: Nguyen, Ngo
 * First Name: Alexander William
 * ID Number: 12108173, 12019029
 * Period: 4
 * 
 * 
 * @author William Ngo, Alex Nguyen
 *
 */
public class Example {
	
	static JTextField nameCoord = new JTextField(5);
	static JTextField oneCoord = new JTextField(5);
	static JTextField twoCoord = new JTextField(5);
	static JTextField threeCoord = new JTextField(5);
	static JPanel myPanel = new JPanel();
	static JLabel nameLabel;
	static JLabel oneLabel;
	static JLabel twoLabel;
	static JLabel threeLabel;

	/**
	 * Method that initializes the text field's color, tool tip texts, margins, and font
	 * @param coord - JTextField
	 * @param text - String for setToolTipText
	 * Alex
	 */
	public static void initializeTextFields(JTextField coord, String text, Color c) {
		coord.setCaretColor(c);
		coord.setForeground(c);
		coord.setToolTipText(text);
		coord.setMargin(new Insets(5,5,5,5));
		coord.setFont(new Font("Courier", Font.ITALIC, 20));
	}
	
	/**
	 * Method that sets font and adds the JLabel and JTextField to the JPanel
	 * @param l - JLabel
	 * @param coord - JTextField
	 * William
	 */
	public static void formatLabel (JLabel l, JTextField coord) {
		l.setFont(new Font("Courier", 0, 20));
	    myPanel.add(l);
	    myPanel.add(coord);
	}
	
	/**
	 * Gets the student name and the 3 test scores
	 * Alex
	 */
	public static void getTestScores() {
		JLabel nameLabel = new JLabel("Student name: ");
		formatLabel (nameLabel, nameCoord);
		JLabel oneLabel = new JLabel("First test score: ");
		formatLabel (oneLabel, oneCoord);
	    JLabel twoLabel = new JLabel("Second test score: ");
		formatLabel (twoLabel, twoCoord);
		JLabel threeLabel = new JLabel("Third test score: ");
		formatLabel (threeLabel, threeCoord);
	}
	
	/**
	 * Sets the text of the JTextFields to blank
	 * William
	 */
	public static void setTextBlank() {
	    nameCoord.setText("");
		oneCoord.setText("");
		twoCoord.setText("");
		threeCoord.setText("");
	}
	
	/**
	 * Main function that uses JOptionPane dialog windows to take user input and output the summary
	 * @param args
	 * Alex
	 */
	public static void main(String[] args) {
		//Initialize Text Fields
		setTextBlank();
		initializeTextFields(nameCoord, "Enter student name: ", Color.orange);
		initializeTextFields(oneCoord, "Enter first test score: ", Color.red);
		initializeTextFields(twoCoord, "Enter second test score: ", Color.green);
		initializeTextFields(threeCoord, "Enter third test score: ", Color.magenta);

	    //Get first student test scores
		myPanel.setLayout(new BoxLayout (myPanel, BoxLayout.Y_AXIS));
		getTestScores();

	    int result = JOptionPane.showConfirmDialog(null, myPanel,
	        "Please enter the student name and test scores", JOptionPane.OK_CANCEL_OPTION);

	    Student s1 = new Student(nameCoord.getText(), Integer.parseInt(oneCoord.getText()), Integer.parseInt(twoCoord.getText()), Integer.parseInt(threeCoord.getText()));
	    
	    //initializeTextFields();
	    setTextBlank();
		
	   //get second student test scores
		myPanel = new JPanel();
		myPanel.setLayout(new BoxLayout (myPanel, BoxLayout.Y_AXIS));
		getTestScores();
	  
	    result = JOptionPane.showConfirmDialog(null, myPanel,
	        "Please enter student name and test scores", JOptionPane.OK_CANCEL_OPTION);
		
	    Student s2 = new Student(nameCoord.getText(), Integer.parseInt(oneCoord.getText()), Integer.parseInt(twoCoord.getText()), Integer.parseInt(threeCoord.getText()));

	    //Display summary
	    javax.swing.UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.ITALIC, 20));
		JOptionPane.showMessageDialog(null, s1.getName() + "\nAverage: " + s1.average() +
				"\nHighest Score: " + s1.getHigh() + "\nLowest Score: " + s1.getLow() + "\n" + 
				s2.getName() + "\nAverage: " + s2.average() + "\nHighest Score: " + s2.getHigh() + "\nLowest Score: " + s2.getLow() + "\n\n" +
				s1.betterAvg(s1,  s2) + "\n" + s1.betterHigh(s1,s2) + "\n" + s1.betterLow(s1, s2), "Summary", 1);

	}
}
