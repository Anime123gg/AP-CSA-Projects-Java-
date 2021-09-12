import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Numpad implements ActionListener {

	private JButton[] buttons = new JButton[12];
	private String output = "";
	private JPanel panel = new JPanel();
	private JFrame frame = new JFrame();
	private JLabel label = new JLabel();
	private JLabel update = new JLabel("Current Number is: " + output);
	public void createNumpad(String num) {
		label.setText(num);
		label.setFont(new Font("Open Sans", 0, 40));
		update.setFont(new java.awt.Font("Open Sans", 0, 20));
		panel.setLayout(new GridLayout(4,3));
	    for(int i = 0; i < buttons.length; i++ ) {
	    	if(i == 9) {
	    		buttons[i] = new JButton("Enter");
	    	}
	    	else if(i == 10) {
	    		buttons[i] = new JButton("0");
	    	}
	    	else if(i == 11) {
	    		buttons[i] = new JButton("Backspace");
	    	}
	    	else {
	    		buttons[i] = new JButton(String.valueOf(i+1));
	    	}
	       buttons[i].setFont(new java.awt.Font("Open Sans", 0, 14));
	       buttons[i].setPreferredSize(new java.awt.Dimension(100, 50));
	       buttons[i].addActionListener(this);
	       panel.add(buttons[i]);
	    }
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(350,700);
	JPanel out = new JPanel();
	out.setLayout(new GridLayout(4, 1));
	out.add(label);
	out.add(update);
	out.add(panel);
	frame.add(out);
	frame.setVisible(true);
	}
	
	public int getInput() {
		return Integer.parseInt(output);
	}
	public void reset() {
		label.setText("");
	}
	public void erase() {
		frame.setVisible(false);
		frame.removeAll();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < buttons.length; i++) {
			if(i == 9) {
				if(e.getSource() == buttons[i]) {
					output = label.getText();
					reset();
					update.setText("Current Number is: " + output);
					frame.revalidate();
				}
			}
			else if(i == 10) {
				if(e.getSource() == buttons[i]) {
					label.setText(label.getText() + "0");
					frame.revalidate();
				}
			}
			else if(i == 11) {
				if(e.getSource() == buttons[i]) {
					label.setText(label.getText().substring(0, label.getText().length()-1));
					frame.revalidate();
				}
	    	}
			else {
				if(e.getSource() == buttons[i]) {
					label.setText(label.getText() + "" + (i + 1));
					frame.revalidate();
				}
			}
		}	
	}
}