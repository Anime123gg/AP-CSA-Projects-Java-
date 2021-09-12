import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class that contain the main game functions for Mastermind. Holds the secret number
 * and the number of times the user have guessed.
 * 
 * @author - Steven P, Hayk M, Matthew C
 */
public class GameFunctions implements ActionListener{
	
	private SecretNumber num = new SecretNumber();
    private int guessCount = 0;
    private boolean gameOver;

    //First JFrame to select the secret number
    private JFrame secret = new JFrame();
    private JPanel p = new JPanel();
    private JButton butS = new JButton("Submit Secret Number");
    private JLabel sVerdict = new JLabel();
    private JTextField sTextField = new JTextField();


    //Second JFrame to guess the secret number
    private JFrame guess = new JFrame();
    private JPanel p1 = new JPanel();
    private JLabel tr = new JLabel("Try number:        0");
    private JLabel correctD = new JLabel("Digits found:      0");
    private JLabel correctP = new JLabel("Correct Position:  0");
    private JButton butG = new JButton("Submit Guess");
    private JLabel gVerdict = new JLabel();
    private Numpad gNumpad = new Numpad();
    private JCheckBox check = new JCheckBox("Click to confirm you are ready to submit guess");

    //End JFrame
    private JFrame result = new JFrame();
    private JPanel p2 = new JPanel();
    private JLabel res = new JLabel(" ");
    //gif if you want hayk
    private JButton butQ = new JButton("Quit");
    private JButton butR = new JButton("Play Again");
	
	/**
     * Method takes in a user's input, processes it, and ensures it's valid before saving it as
     * the game's secret number. Otherwise, it will demand that the user gives a valid input.
     * 
     * @author - Steven P
     * @param sc - scanner that scans for users input
     */
    public boolean getSecretNumber(String userInput) {
        try {
            if (Integer.parseInt(userInput) == -1) {
                num = new SecretNumber();
                return true;
            } else if (checkSecretValidity(userInput)) {
                num = new SecretNumber(Integer.parseInt(userInput));
                return true;
            } else {
                sVerdict.setText("'" + userInput + "' is not a valid secret number.");
                return false;
            }

        } catch (Exception e) {
                sVerdict.setText("'" + userInput + "' is not a valid secret number.");
                return false;
        }
    }
		
		//Statement below is used for testing purposes!
		//System.out.println("Resolved, the secret number is: " + num);

	
	/**
	 * Checks the user input and returns a boolean indicating whether or not the user's input is valid as a secret number.
	 * 
	 * @author - Hayk M
	 * @param input - String that is checked for a valid number
	 * @return boolean - true if input can be a secret number, false otherwise
	 */
	public boolean checkSecretValidity(String input) {
		
		int convertedInput;
			try {
				convertedInput = Integer.parseInt(input);
				
				if (input.length() == 5 && num.checkUnique(convertedInput)) {
					return true;
				} else {
					return false;
				}

			} catch (Exception e) {
				return false;
			}
	}
	
	/**
	 * A method that checks the guess, returning 0 if the wrong amount of digits are used, 1 if the input is indeed a 5 digit, 
	 * or -1 if the input is NOT a string that consists of only numbers.
	 * 
	 * @author - Steven P
	 * @param input - String to be checked as a guess
	 * @return int - value of 0, 1, or -1
	 */
	public int checkGuessValidity(String input) {	
		try {	
			Integer.parseInt(input);
			if (input.length() != 5) {	
				return 0;
				
			} else if (input.length() == 5) {
				return 1;
				
			} 
			
		} catch (Exception e) {
			return -1;
		}	
		return -1;
	}
	
	/**
	 * Reads and checks the user's guess, before printing out the result. Unless the user inputs the wrong
	 * number of digits, all guesses count as an attempt.
	 * 
	 * @author - Hayk M
	 * @param sc - Scanner used to read the user's input.
	 */
	public void getGuess(String userInput) {
        if (checkGuessValidity(userInput) == 0) { 
            gVerdict.setText("'" + userInput +"' must have a length of 5");

        } else if (checkGuessValidity(userInput) == 1) {
            guessCount++;
            SecretNumber guess = new SecretNumber(Integer.parseInt(userInput));

            tr.setText("Try number:       " + String.format("%4s", "" + guessCount));
            correctD.setText("Digits found:     " + String.format("%4s", num.correctDigits(guess)));
            correctP.setText("Correct Position: " + String.format("%4s", "" + num.correctPositions(guess)) );
            System.out.println();
            if (num.isSame(guess)) {
                res.setText("You won in " + guessCount + " tries!");
                gameOver = true;
            } else if (guessCount == 32) {
                res.setText("Maximum tries of 32 reached. Game Over!");
                res.setText("The secret number was actually " + num);
                gameOver = true;
            }

        } else {
            guessCount++;
            tr.setText("Try number:       " + String.format("%4s", "" + guessCount));
            correctD.setText("Digits found:     " + String.format("%4s",  "" + 0) );
            correctP.setText("Correct Position: " + String.format("%4s", "" + 0));

            if (guessCount == 32) {
                res.setText("Maximum tries of 32 reached. Game Over!");
                res.setText("The secret number was actually " + num);
                gameOver = true;
            }
        }
	}
				
		
	

	
	
	/**
	 * Runs the game Mastermind, and allows the user to replay it as many times as they want.
	 * 
	 * @author - Matthew C
	 */
	public void runGame() {
		getS();
	}
		
		
	
	
	//Result JFrame
	public void getR() {
		makeGUIR();
    	p2.add(res);
    	p2.add(butQ);
    	p2.add(butR);
        result.add(p2);
        result.setVisible(true);
	}
	
	
	/**
     * Adds the format of the JFrame and also the label and its style
     * @author Matthew
     */
    private void makeGUIR() {
    	result.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	result.setSize(500,200);
        p2.setSize(200,200);
        p2.setVisible(true);
        res.setFont(new Font("", 7, 16));
        
        makeButtonsR(result);

        p2.setLayout(new BoxLayout (p2, BoxLayout.Y_AXIS));

    }
    
    /**
     * Makes the buttons for the 3 doors, and sets up the listener
     * @param pan - the JFrame that the buttons will go on
     * @author Daniel
     */
    private void makeButtonsR(JFrame pan) {
      
    	butQ.setBounds(pan.getWidth()/2 - 65,100,100,200);  
        butQ.addActionListener(this);
        pan.add(butQ);
        
        butR.setBounds(pan.getWidth()/2 - 65,100,100,200);  
        butR.addActionListener(this);
        pan.add(butR);
    }
    
	//Guess JFrame
	public void getG() {
		makeGUIG();
		JLabel g1 = new JLabel(" Enter your 5-digit guess");
		g1.setFont(new Font("", 7, 16));
    	p1.add(g1);
    	p1.add(tr);
    	p1.add(correctD);
    	p1.add(correctP);
        p1.add(butG);
        p1.add(gVerdict);
        guess.add(p1);
        guess.setVisible(true);
	}
	
	
	/**
     * Adds the format of the JFrame and also the label and its style
     * @author Matthew
     */
    private void makeGUIG() {
    	guess.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	guess.setSize(500,200);
        p1.setSize(200,200);
        p1.setVisible(true);
        gNumpad = new Numpad();
        gNumpad.createNumpad("");
        
        makeButtonsG(guess);

        p1.setLayout(new BoxLayout (p1, BoxLayout.Y_AXIS));

    }
    
    /**
     * Makes the buttons for the 3 doors, and sets up the listener
     * @param pan - the JFrame that the buttons will go on
     * @author Daniel
     */
    private void makeButtonsG(JFrame pan) {
      
        butG.setBounds(pan.getWidth()/2 - 65,100,100,200);  
        butG.addActionListener(this);
        pan.add(butG); 
        
        

    }

	//Secret JFram
	
	/**
     * Makes the panel with the buttons on it
     * @author Hayk
     */
    public void getS() {
    	
    	makeGUIS();
    	JLabel s1 = new JLabel(" Enter the secret number as a 5 digit number\r\n");
        JLabel s2 = new JLabel(" where all digits are unique, or enter -1\r\n");
        JLabel s3 = new JLabel(" to have this program select the secret number.\r\n");
        JLabel s4 = new JLabel(" Select your own valid secret number or enter -1:");
        s1.setFont(new Font("", 7, 16));
        s2.setFont(new Font("", 7, 16));
        s3.setFont(new Font("", 7, 16));
        s4.setFont(new Font("", 7, 16));
    	p.add(s1);
    	p.add(s2);
    	p.add(s3);
    	p.add(s4);
    	p.add(sTextField);
    	p.add(check);
        p.add(butS);
        p.add(sVerdict);
        secret.add(p);
        secret.setVisible(true);	
        
    }
	/**
     * Adds the format of the JFrame and also the label and its style
     * @author Matthew
     */
    private void makeGUIS() {
    	secret.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	secret.setSize(500,220);
        p.setSize(200,200);
        p.setVisible(true);
        makeButtonsS(secret);

        p.setLayout(new BoxLayout (p, BoxLayout.Y_AXIS));

    }
    
    /**
     * Makes the buttons for the 3 doors, and sets up the listener
     * @param pan - the JFrame that the buttons will go on
     * @author Daniel
     */
    private void makeButtonsS(JFrame pan) {
      
        butS.setBounds(pan.getWidth()/2 - 65,100,100,200);  
        butS.addActionListener(this);
        pan.add(butS); 
        
        

    }
    public void reset() {
        guessCount = 0;
        gameOver = false;
        sTextField.setText("");
        gNumpad.reset();
        sVerdict.setText("");
        gVerdict.setText("");
        tr.setText("Try number:       " + String.format("%4s", ""+guessCount));
        correctD.setText("Digits found:     " + String.format("%4s",  "" + 0) );
        correctP.setText("Correct Position: " + String.format("%4s", "" + 0));
        secret = new JFrame();
        guess = new JFrame();
        result = new JFrame();
        p = new JPanel();
        p1 = new JPanel();
        p2= new JPanel();
        butS = new JButton("Submit Secret Number");
        butG = new JButton("Submit Guess");
        butQ = new JButton("Quit");
        butR = new JButton("Play Again");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource() == butS && check.isSelected() ) {
            boolean valid = getSecretNumber(sTextField.getText() );
             check.setSelected(false);
            if (valid) {
                secret.setVisible(false);
                getG();
            }
        }
        if (e.getSource() == butG ) {
            getGuess(gNumpad.getInput() + "");
            if (gameOver) {
                guess.setVisible(false);
                getR();
            }
        }
        if (e.getSource() == butQ) {
            result.setVisible(false);
            gNumpad.erase();
        }
        if (e.getSource() == butR) {
            result.setVisible(false);
            reset();
            gNumpad.erase();
            runGame();
        }

    }

	
}