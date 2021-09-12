package ColorPanel;
import javax.swing.*;
import java.awt.*;
public class ColorPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	//initial pop up
	public static void main(String[] args) { 
		JFrame theGUI = new JFrame();              // 1st variable
		theGUI.setTitle("GUI Program");
		theGUI.setSize(300,200);
		theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ColorPanel panel = new ColorPanel();       // 2nd variable
		Container pane = theGUI.getContentPane();  // 3rd variable
		pane.add(panel);
		theGUI.setVisible(true);
		}
	
	//inside the pop up / screen
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int width = getWidth();       // 4th variable, get width of screen
		int height = getHeight();     // 5th variable, get height of screen
		int widthCenter = width/2;    // 6th variable , horizontal center
		int heightCenter = height/2;  // 7th variable , vertical center
		
		//  the box
			g.setColor(Color.ORANGE);
			
			int startSideRectX = widthCenter -(widthCenter /2); //8th variable,starts drawing away from X middle
			int startSideRectY = heightCenter - (heightCenter * 9 / 10); //9th variable,start drawing away from Y middle
			
			int rectLength = 2*(widthCenter -(widthCenter /2));  //29th variable(new), makes the hori. side of the rectangle centered
			
			int endSideRectX = rectLength ;						     //10th variable,
			int endSideRectY = heightCenter*4/10;                        //11th variable, makes the height a of the rectangle 
																	     //12th variable,a percentage of the screen
			g.drawRect(startSideRectX, startSideRectY, endSideRectX , endSideRectY);
			
		//the text inside the box
			g.setColor(Color.red);
			Font font = new Font("Courier", Font.BOLD, width/20);  // 13th variable, 
			                                            //make the text proportional to the x-value of screen
			g.setFont(font);
			
			// I need to center the text differently since they are not similar in length
			int wordCenterX = widthCenter -(widthCenter *35/100);  // 14th variable
			int numberCenterX = widthCenter -(widthCenter *25/100);  // 15th variable
			
			//The word needs to be above the number
			int wordCenterY = heightCenter/4;  // 16th variable
			int numberCenterY = heightCenter*4/10;   // 17th variable
		
			g.drawString("Hello, world!", wordCenterX , wordCenterY );
			g.setColor(Color.magenta);
			g.drawString( "("+widthCenter + "," + heightCenter+")", numberCenterX , numberCenterY);
		
			
			//I made this since the codes were getting too long horizontally
			//These are just the arrow points that I used over and over again
			
			// X-Values of the lines
			int leftPointX = widthCenter - (widthCenter * 5 / 10); // 18th variable
			int rightPointX = widthCenter + (widthCenter * 5 / 10); // 19th variable
			
			// Y- Values of the lines
			int topPointY = heightCenter - (heightCenter * 2 / 10);  // 20th variable
			int bottomPointY = heightCenter + (heightCenter * 2 / 10);  // 21th variable
			
			//X-Values of the bottom left Arrow
			int bottomLeftArrowPointX = widthCenter - (widthCenter *2/3); // 22th variable
			
			//X-Values of the bottom right Arrow
			int bottomRightArrowPointX = widthCenter + (widthCenter *2/3); // 23th variable
			
			//X-Values of the top left Arrow
			int topLeftArrowPointX = widthCenter - (widthCenter /3); // 24th variable
			
			//X-Values of the top right Arrow
			int topRightArrowPointX = widthCenter + (widthCenter /3); // 25th variable
			
			//Y-Values of the lowest Arrows
			int topArrowPointY = heightCenter - (heightCenter * 4 / 10); // 26th variable
			
			//Y-Values of middle Arrows
			int middleArrowPointY = heightCenter; // 27th variable
					
			//Y-Values of the top Arrows
			int bottomArrowPointY = heightCenter + (heightCenter * 4 / 10); // 28th variable
			
			
			
		//The lines
			g.setColor(Color.black);
		g.drawLine( leftPointX , topPointY  , rightPointX  , topPointY ); // top line
		
		g.setColor(Color.blue);
		g.drawLine( leftPointX , bottomPointY  , rightPointX  , bottomPointY );   // bottom line
		
		
		//bottom left arrow
		g.drawLine( leftPointX , bottomPointY  , bottomLeftArrowPointX, bottomArrowPointY);
		g.drawLine( leftPointX , bottomPointY  , bottomLeftArrowPointX, middleArrowPointY  );
		
		//bottom right arrow
		g.drawLine( rightPointX, bottomPointY  , bottomRightArrowPointX, bottomArrowPointY);
		g.drawLine( rightPointX, bottomPointY  , bottomRightArrowPointX, middleArrowPointY );
		
		//top left arrow
		g.setColor(Color.black);
		g.drawLine( leftPointX ,  topPointY  , topLeftArrowPointX, middleArrowPointY);
		g.drawLine( leftPointX ,  topPointY  , topLeftArrowPointX, topArrowPointY );
		
		//top right arrow
		g.drawLine( rightPointX , topPointY  , topRightArrowPointX, middleArrowPointY);
		g.drawLine( rightPointX , topPointY  , topRightArrowPointX, topArrowPointY );
		
		
	}
	
	
}
