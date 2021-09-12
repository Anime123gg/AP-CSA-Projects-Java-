import java.awt.*;
import java.util.Random;

import javax.swing.*;

/**
 * Last name: Nguyen, Ngo
 * First name: Alexander, William
 * Student ID: 12108173, 12019029
 * Period:4
 * 
 * Program creates a window with random colored panels
 */
public class GUIWindow {
	
	static int rows;
	static int cols;
	
	/**
	 * Method to take user input for rows and columns
	 * Initial values are randomized
	 * @author William
	 */
	public static void getInput() {

	       String  inputStr = JOptionPane.showInputDialog("Number of rows", (int)(Math.random()*10+1));
	       if (inputStr  == null) return;
	       rows = Integer.parseInt(inputStr);
	       inputStr = JOptionPane.showInputDialog("Number of columns", (int)(Math.random()*10+1));
	       if (inputStr  == null) return;
	       cols = Integer.parseInt(inputStr);
	}
	
	/**
	 * Method that creates one random colored panel
	 * @return - random color panel
	 * @author Alexander
	 */
	public static ColorPanelRandomColor createRandomColorPanel() {
		Random gen = new Random();
		int  red = gen.nextInt(256);
        int  green = gen.nextInt(256);
        int  blue = gen.nextInt(256);
        Color backColor = new Color(red,green,blue);
        
        ColorPanelRandomColor panel = new ColorPanelRandomColor(backColor,100,100);
		return panel;
		
	}
	
	/**
	 * Method that creates a checker board of random color panels
	 * @param pane - Container
	 * @author William
	 */
	public static void randomColorCheckerBoard(Container pane) {
	       pane.setLayout(new GridLayout(rows,cols));

	       for   (int  i = 1; i<= rows*cols; i++){
	          pane.add(createRandomColorPanel());
	       }
	}
	
	/**
	 * Method that creates JFrame object and applies the container pane to the JFrame object created
	 * Sets the JFrame object as visible
	 * @author Alexander
	 */
	public static void guiWindowRandomCheckeredBoard() {
		getInput();
		JFrame theGUI = new JFrame();
		theGUI.setTitle("theGUI");
		theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = theGUI.getContentPane();
		randomColorCheckerBoard(pane);
		theGUI.pack();   
	    theGUI.setVisible(true);
	}
	
	/**
	 * Method that creates one black and one white color panel and adds it to the container pane
	 * @param pane - Container
	 * @author William
	 */
	public static void createBlackWhitePanel(Container pane) {
        Color backColor1 = new Color(0,0,0);
        Color backColor2 = new Color(255,255,255);
        
        ColorPanelGrayRect panel1 = new ColorPanelGrayRect(backColor1,200,250);
        ColorPanelGrayRect panel2 = new ColorPanelGrayRect(backColor2,200,250);
        pane.setLayout(new GridLayout(1,2));
		pane.add(panel1);
		pane.add(panel2);
		
	}
	
	/**
	 * Method that creates a JFrame object and applies the container pane to the JFrame object created
	 * Sets the JFrame object as visible
	 * @author Alexander
	 */
	public static void guiWindowBlackWhiteBoard() {
		JFrame theGUI = new JFrame();
		theGUI.setTitle("Illusion");
		theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = theGUI.getContentPane();
		createBlackWhitePanel(pane);
		theGUI.pack();   
	    theGUI.setVisible(true);
	}

	/**
	 * Main program to create the two windows with a grid of colors and a black and white illusion
	 * @param agrs - arguments
	 * @author William & Alexander
	 */
	public static void main(String[] args) {	      
		       
		guiWindowRandomCheckeredBoard();
		guiWindowBlackWhiteBoard();
	}

}
