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
public class ColorPanelGrayRect extends JPanel{
	/**
	 * Constructor that sets background color
	 * @param bColor - background color
	 * Starting code
	 */
	public ColorPanelGrayRect(Color backColor){
		setBackground(backColor);
	}
	/**
	 * Overloaded constructor that sets background color and dimensions
	 * @param bColor - background color
	 * @param width - width
	 * @param height - height
	 * Starting code
	 */
	public ColorPanelGrayRect(Color backColor, int width, int height){
		setBackground(backColor);
		setPreferredSize(new Dimension(width, height));
	}
	
	/**
	 * Draws a gray rectangle in the middle of each of the black and white panels
	 * @param g - graphics
	 * @author Alexander
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.gray);
		int rectWidth =   40;
		int rectHeight =  50;
		int startX = (getWidth()/2)-(rectWidth/2);
		int startY = (getHeight()/2)-(rectHeight/2);
		
		g.fillRect(startX,startY,rectWidth,rectHeight);
		}
	
}




