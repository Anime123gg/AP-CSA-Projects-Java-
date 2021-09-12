import javax.swing.*;
import java.awt.*;
/**
 * Last name: Nguyen, Ngo
 * First name: Alexander, William
 * Student ID: 12108173, 12019029
 * Period:4
 * 
 * A color panel whose background is a color provided by the client
 * A client specified preferred size is optional
 */
public class ColorPanelRandomColor extends JPanel{
	/**
	 * Constructor that sets background color
	 * @param bColor - background color
	 * Starting code
	 */
	public ColorPanelRandomColor(Color backColor){
		setBackground(backColor);
	}
	/**
	 * Overloaded constructor that sets background color and dimensions
	 * @param bColor - background color
	 * @param width - width
	 * @param height - height
	 * Starting code
	 */
	public ColorPanelRandomColor(Color backColor, int width, int height){
		setBackground(backColor);
		setPreferredSize(new Dimension(width, height));
	}
	
	/**
	 * Writes the RGB values in the upper left corner of each color panel
	 * @param g - graphics
	 * @author William
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.yellow);
		int red = getBackground().getRed();
		int green = getBackground().getGreen();
		int blue = getBackground().getBlue();
		g.drawString(red+ "," + green + "," +blue, 0, 15);
		}
	
}
