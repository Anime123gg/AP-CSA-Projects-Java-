import images.APImage;
import images.Pixel;
import java.util.Scanner;
/**
* 12091029, 12091037, 12090958
* @author - William Ngo, Lawrence Lim, Danee Dang
* Game class that contains the methods that make the game function
*/
public class TestEdges{
   private APImage image;
   private Scanner scan;

   /** Default Constructor for the TestEdges class
    *
    */
   public TestEdges() {
       image = new APImage("smokey.jpg");
       scan = new Scanner(System.in);
       int input = 16;
       while (input!=15) {
           System.out.println("What would you like to do?");
           this.menu();
           do {
               input= this.getValidNum();
               if(input<1|| input>15) {
                   System.out.println("Not a valid input. Try again!");
               }
           }
           while (input<1 || input>15);
           this.checkOption(input);
       }
       System.out.println();
   }

   /**
    * This method returns the menu of the program.
    */
   public void menu() {
       System.out.println("1. Select another image file (default is smoky.jpg)");
       System.out.println("2. Convert image to black and white");
       System.out.println("3. Convert image to grayscale");
       System.out.println("4. Convert  image to luminance grayscale");
       System.out.println("5. Rotate image left 90 degrees, right 90 degrees and 180 degrees");
       System.out.println("6. Convert image to an old-fashioned image");
       System.out.println("7. Darken and brighten the image");
       System.out.println("8. Do color filtering on the image");
       System.out.println("9. Posterize the image");
       System.out.println("10. Convert image to look like a photographic negative.");
       System.out.println("11. Sharpen the image");
       System.out.println("12. Blur the image");
       System.out.println("13. Shrink the image");
       System.out.println("14. Enlarge the image");
       System.out.println("15. Exit the program");
   }

   /** This method takes the user input and runs a method according to the user input.
    * @param num - the method chosen
    */
   public void checkOption(int num) {
       if (num >= 8) {
           if (num >= 12) {
               if (num == 12) {
                   this.blur();
               }
               else if (num == 13) {
                   shrink();
               }
               else if (num == 14) {
                   enlarge();
               }
               else{
                   System.out.println("Thank you for using our program! See you later!");
               }
           }
           else {
               if (num==8) {
                   filter();
               }
               else if (num==9) {
                   posterize();
               }
               else if (num==10) {
                   negative();
               }
               else {
                   this.sharpen();
               }
           }
       }
       else {
           if (num>=5) {
               if (num==5) {
                   rotateAllThree();
               }
               else if (num==6) {
                   this.oldFashion();
               }
               else{
                   this.darkBright();
               }
           }
           else {
               if (num==1) {
                   this.selectOtherImage();
               }
               else if (num==2) {
                   this.blackAndWhite();
               }
               else if (num==3) {
                   this.lnLGrayScale(num);
               }
               else {
                   this.lnLGrayScale(num);
               }
           }
       }
   }

   /** This method changes the image if the user requests for a different image.
    *
    */
   public void selectOtherImage() {
       System.out.println("Select a number: ");
       System.out.println("1. smokey.jpg \n2. butterfly1.jpg\n3. koala.jpg\n4. redMotorcycle.jpg"+
               "\n5. seagull.jpg\n6. swan.jpg\n7. arch.jpg");
       int num = 16;
       while (num<1 || num>7) {
           try {
               num = scan.nextInt();
               if (num<1 || num>7) {
                   System.out.println("Not valid. Try again");
               }
           }
           catch(Exception e) {
               System.out.println("Not valid. Try again");
           }
       }
       if (num==1) {
           image = new APImage("smokey.jpg");
       }
       else if (num==2) {
           image = new APImage("butterfly1.jpg");
       }
       else if (num==3) {
           image = new APImage("koala.jpg");
       }
       else if (num==4) {
           image = new APImage("redMotorcycle.jpg");
       }
       else if (num==5) {
           image = new APImage("seagull.jpg");
       }
       else if (num==6) {
           image = new APImage("swan.jpg");
       }
       else {
           image = new APImage("arch.jpg");
       }
       image.draw();
   }
   /** This method converts an image to black and white.
    *
    */
   public void blackAndWhite() {
       image.draw();
       APImage clone1 = image.clone();
       clone1.draw();
       for (Pixel p : clone1) {
           int red = p.getRed();
           int green = p.getGreen();
           int blue = p.getBlue();
           int average = (red + green + blue) / 3;
           if (average < 128) {
               p.setRed(0);
               p.setGreen(0);
               p.setBlue(0);
           } else {
               p.setRed(255);
               p.setGreen(255);
               p.setBlue(255);
           }
       }
       System.out.print("Press return to continue:");
       scan.nextLine();
       clone1.draw();

   }
   /** This method converts the colors of an image to a grayscale by setting each
    * pixel to the average of rgb and dividing by 3.
    *
    * @return clone1 - the converted image.
    */
   public APImage grayScale() {
       APImage clone1 = image.clone();
       clone1.draw();
       for (Pixel p : clone1) {
           int red = p.getRed();
           int green = p.getGreen();
           int blue = p.getBlue();
           int average = (int) ((red+green+blue)/3);
           p.setRed(average);
           p.setGreen(average);
           p.setBlue(average);
       }
       return clone1;

   }
   /**
    * This method converts the colors of the image to a grayscale using the corresponding percentages.
    * @param num - the type of grayscale chosen.
    */
   public void lnLGrayScale(int num) {
       image.draw();
       APImage clone1 = image.clone();
       clone1.draw();
       for (Pixel p : clone1) {
           int red = p.getRed();
           int green = p.getGreen();
           int blue = p.getBlue();
           int average =0;

           if (num==3) {
               average = (int) ((red+green+blue)/3);
           }
           else{
               average = (int)((red*0.299) + (green*0.587) + (blue*0.114))/3;
           }
           p.setRed(average);
           p.setGreen(average);
           p.setBlue(average);
       }
       System.out.print("Press return to continue:");
       scan.nextLine();
       clone1.draw();

   }
   /**
    * This method rotates the image 90 deg left, 90 deg right, and 180 deg.
    */
   public void rotateAllThree() {
       image.draw();
       APImage clone1 = rotateNinety("left");
       APImage clone2 = rotateNinety("right");
       APImage clone3 = rotateOneEighty();
       clone1.draw();
       clone2.draw();
       clone3.draw();

   }

   /**
    * This method rotates the image 90 deg, either to the left or the right
    * @param rotate - specifies whether to rotate left or right
    * @return after - the rotated image
    */
   public APImage rotateNinety(String rotate) {
       APImage after = new APImage(image.getHeight(), image.getWidth());


       if (rotate.equals("left"))
       {
           for (int y = 0; y < image.getHeight(); y++)
           {
               for (int x = 0; x < image.getWidth(); x++)
               {
                   Pixel p = image.getPixel(x, y);
                   Pixel c = p.clone();
                   after.setPixel(y, after.getHeight()-1 -x, c);

               }
           }
       }
       else
       {
           for (int y = 0; y < image.getHeight(); y++)
           {
               for (int x = 0; x < image.getWidth(); x++)
               {
                   Pixel p = image.getPixel(x, y);
                   Pixel c = p.clone();
                   after.setPixel(after.getWidth()-1 - y, x, c);

               }
           }
       }

       return after;
   }
   /**
    * This method rotates an image 180 deg.
    * @return after - the rotated image
    */
   public APImage rotateOneEighty() {
       APImage after = image.clone();
       for (int y = 0; y < image.getHeight(); y++)
       {
           for (int x = 0; x < image.getWidth(); x++)
           {
               Pixel p = image.getPixel(x, y);
               Pixel c = p.clone();
               after.setPixel(after.getWidth()-1 - x, after.getHeight()-1 -y, c);
           }

       }
       return after;

   }


   /**
    * This method converts the image to an old fashion image.
    */
   public void oldFashion() {
       image.draw();
       APImage clone1 = this.grayScale();
       for (Pixel p : clone1) {
           int red = p.getRed();
           int green = p.getGreen();
           int blue = p.getBlue();
           if (red < 63){
               red = (int)(red * 1.1);
               blue = (int)(blue * 0.9);
           }
           else if (red < 192){
               red = (int)(red * 1.15);
               blue = (int)(blue * 0.85);
           }
           else{
               red = (int) Math.min((red * 1.08), 255);
               blue = (int)(blue * 0.93);
           }
           p.setRed(red);
           p.setGreen(green);
           p.setBlue(blue);
       }
       System.out.print("Press return to continue:");
       scan.nextLine();
       clone1.draw();
   }

   /** This method converts the image to a darker or brighter image based on the user input
    *
    */
   public void darkBright() {
       APImage clone1 = image.clone();
       System.out.println("Would you like to darken the image or brighten it? press d to darken or b to brighten. Enter -1 to stop");
       System.out.println("If you go over the maximum darkness/brightness, the maximun will be used.");
       String input = scan.next();
       int num = 0;
       while(!(input.equals("-1"))){
           if (input.equals("d")) {
               System.out.println("How much would you like to darken it by?");
               num = this.getValidNum();
               filtering(clone1, -1*num, -1*num, -1*num);
           }
           else if (input.equals("b")) {
               System.out.println("How much would you like to brighten it by?");
               num = this.getValidNum();
               filtering(clone1, num, num, num);
           }
           else {
               System.out.println("Invalid input.Try again");
           }
           image.draw();
           clone1.draw();
           System.out.println("What you you like to do next? d to darken,b to brighten, -1 to quit");
           input = scan.next();
       }
   }
   /**
    * This method makes sure the user inputs a valid value
    * @return num - a valid input
    */
   public int getValidNum() {
       String num="";
       boolean valid =false;
       num = scan.next();    
       while(valid==false) {
    	   valid=true;             
    	   for (int i=0;i<num.length();i++) {
   			if (num.charAt(i) < 48 ||  num.charAt(i) > 57 ) {
   				valid=false;
   				}
   			}
    	   if(valid==false) {
    		   System.out.println("Invalid response. Try another input");
    		   num = scan.next(); 
    	   }
       }
       int n = 0;
       for (int i=0;i<num.length();i++) {
  			n += (int) (Math.pow(10, num.length()-(i+1)) *  (num.charAt(i)-48))  ;
  		}
       return n;
   }

   /**
    *  This method asks for the user input and calls the filtering 
    *  method to filter the image based on the user's input.
    */
   public void filter() {
       int red;
       int green;
       int blue;
       APImage clone = image.clone();


       System.out.println("Please enter 3 numbers separated by spaces corresponding to the amount you want to increase / decrease each RGB value by.");
       red = this.getValidNum();
       green = this.getValidNum();
       blue = this.getValidNum();

       filtering(clone, red, green, blue);
       image.draw();
       clone.draw();
   }
   /**
    *  This method filters the image using the given parameters.
    * @param img - to be filtered
    * @param redChange - the amount to increase/decrease red value
    * @param greenChange - the amount to increase/decrease green value
    * @param blueChange - the amount to increase/decrease blue value
    */
   public void filtering(APImage img, int redChange, int greenChange, int blueChange) {
       for (Pixel p : img) {
           int red = p.getRed();
           int green = p.getGreen();
           int blue = p.getBlue();

           if(red + redChange <= 255 && red + redChange >= 0){
               red += redChange;
           }

           if(green + greenChange <= 255 && green + greenChange >= 0){
               green += greenChange;
           }

           if(blue + blueChange <= 255 && blue + blueChange >= 0){
               blue += blueChange;
           }

           p.setRed(red);
           p.setGreen(green);
           p.setBlue(blue);
       }
   }
/**
 * This method filters one pixel based on the given parameters.
 * @param p - the pixel to be filtered
 * @param img - the pixel from the image
 * @param redChange - the amount to increase/decrease red value
 * @param greenChange - the amount to increase/decrease green value
 * @param blueChange - the amount to increase/decrease blue value
 */
   public void filteringOnePixel(Pixel p,APImage img, int redChange, int greenChange, int blueChange) {
       int red = p.getRed();
       int green = p.getGreen();
       int blue = p.getBlue();

       if(red + redChange <= 255 && red + redChange >= 0){
           red += redChange;
       }

       if(green + greenChange <= 255 && green + greenChange >= 0){
           green += greenChange;
       }

       if(blue + blueChange <= 255 && blue + blueChange >= 0){
           blue += blueChange;
       }

       p.setRed(red);
       p.setGreen(green);
       p.setBlue(blue);
   }
  /**
   * This method converts the image to only use 2 colors based on the user input.
   */
   public void posterize() {
       image.draw();
       APImage clone = image.clone();
       int red1 = (int) (Math.random() * 255 + 1);
       int green1 = (int) (Math.random() * 255 + 1);
       int blue1 = (int) (Math.random() * 255 + 1);

       int red2 = (int) (Math.random() * 255 + 1);
       int green2 = (int) (Math.random() * 255 + 1);
       int blue2 = (int) (Math.random() * 255 + 1);

       for (Pixel p : clone){
           int red = p.getRed();
           int green = p.getGreen();
           int blue = p.getBlue();
           int average = (red + green + blue) / 3;

           if (average < 128){
               p.setRed(red1);
               p.setGreen(green1);
               p.setBlue(blue1);
           }else{
               p.setRed(red2);
               p.setGreen(green2);
               p.setBlue(blue2);
           }
       }
       clone.draw();

   }
   /**
    * This method converts the image to grayscale and then resets each RGB component to 255 minus that component. 

    */
   public void negative() {
       image.draw();
       APImage clone = this.grayScale();
       for (Pixel p : clone) {
           p.setRed(255 - p.getRed());
           p.setGreen(255 - p.getGreen());
           p.setBlue(255 - p.getBlue());
       }
       clone.draw();
   }
   /**
    * This method sharpens an image based on a user input.
    */
   public void sharpen() {
       System.out.print("Enter an integer threshold: ");
       int threshold = this.getValidNum();
       System.out.print("Enter a degree of sharpness(int): ");
       int sharp = this.getValidNum();
       APImage sketch = this.edgeDetect(threshold);
       APImage clone = image.clone();
       for (int y = 0; y < image.getHeight(); y++){
           for (int x = 0; x < image.getWidth(); x++){
               if(sketch.getPixel(x, y).getRed()==0) {
                   this.filteringOnePixel(clone.getPixel(x, y),clone, -1*sharp, -1*sharp, -1*sharp);
               }
               else {

               }
           }
       }
       image.draw();
       clone.draw();
   }
   /**
    * This method detects the edge of pixel based on the given threshold.
    * @param threshold
    * @return
    */
   public APImage edgeDetect(int threshold) {

       // Create a blank image to receive the edges
       int width = image.getWidth();
       int height = image.getHeight();
       APImage theSketch = new APImage(width, height);
       // Visit all pixels except for the left column and
       // bottom row
       for (int y = 0; y < height - 1; y++){
           for (int x = 1; x < width; x++){
               // Obtain info for a pixel and its left and bottom neighbors
               Pixel oldPixel = image.getPixel(x, y);
               Pixel leftPixel = image.getPixel(x - 1, y);
               Pixel bottomPixel = image.getPixel(x, y + 1);
               int oldAve = (oldPixel.getRed() +
                       oldPixel.getGreen() +
                       oldPixel.getBlue()) / 3;
               int leftAve = (leftPixel.getRed() +
                       leftPixel.getGreen() +
                       leftPixel.getBlue()) / 3;
               int bottomAve = (bottomPixel.getRed() +
                       bottomPixel.getGreen() +
                       bottomPixel.getBlue()) / 3;
               // If difference is below threshold, reset to white
               Pixel newPixel = theSketch.getPixel(x, y);
               if (Math.abs(oldAve - leftAve) <= threshold ||
                       Math.abs(oldAve - bottomAve) <= threshold){
                   newPixel.setRed(255);
                   newPixel.setGreen(255);
                   newPixel.setBlue(255);
               }
           }
       }
       return theSketch;

   }
 /**
  * This method blurs the image and calls on the twoByTwoAvg to compute the value for the RGB pixel
  */
   public void blur() {
       APImage clone = image.clone();
       for (int y = 1; y < image.getHeight()-1; y++){
           for (int x = 1; x < image.getWidth()-1; x++){
               int avgR = this.twoByTwoAvg(x,y, 'r');
               int avgG = this.twoByTwoAvg(x,y, 'g');
               int avgB = this.twoByTwoAvg(x,y, 'b');
               Pixel p = new Pixel(avgR,avgG,avgB);
               clone.setPixel(x, y,p);
           }
       }
       image.draw();
       clone.draw();
   }
   /**
    * This method takes the average of the 4 surrounding pixels.
    * @param x1 - the x position of the pixel
    * @param y1 - the y position of the pixel
    * @param color - the color in rgb (restricted to only 'r', 'g', 'b')
    * @return avg value for the pixel
    */
   public int twoByTwoAvg(int x1,int y1, char color) {
       Pixel top = image.getPixel(x1, y1-1);
       Pixel left = image.getPixel(x1-1, y1);
       Pixel right = image.getPixel(x1+1, y1);
       Pixel bot = image.getPixel(x1, y1+1);
       Pixel[] four = {top, left,right, bot};
       int total=0;
       for (Pixel p: four){
           if (color=='r') {
               total+=p.getRed();
           }
           else if(color=='g') {
               total+=p.getGreen();
           }
           else {
               total+=p.getBlue();
           }

       }
       int avg=(int)total/4;
       return avg;
   }
 /**
  * This method shrinks the image based on a factor given from user input.
  */
   public void shrink() {
       System.out.print("Shrink image factor: ");
       int x = this.getValidNum();
       APImage clone = new APImage(image.getWidth()/x+1, image.getHeight()/x+1);
       int countInner= 0;
       int countOuter = 0;
       for (int i = 0; i < image.getWidth(); i+=x)
       {  countInner = 0;
           for (int j = 0; j < image.getHeight(); j+=x)
           {
               Pixel p = image.getPixel(i, j);
               clone.setPixel(countOuter, countInner, p);
               countInner++;
           }
           countOuter++;
       }
       image.draw();
       clone.draw();
   }
 /**
  * This method enlarges the image based on a factor from the user input.
  */
   public void enlarge() {
       System.out.print("Enlarge image factor: ");
       int factor = this.getValidNum();
       APImage clone = new APImage(image.getWidth() * factor + 1, image.getHeight() * factor + 1);

       int newLength = 2 * (factor - 1) + 1;

       for(int oldY = 0; oldY < image.getHeight(); oldY++){
           for(int oldX = 0; oldX < image.getWidth(); oldX++){
               Pixel p = image.getPixel(oldX, oldY);

               int newXStart = oldX * factor;
               int newYStart = oldY * factor;

               for(int newY = newYStart; newY < newYStart + factor; newY++){
                   for(int newX = newXStart; newX < newXStart + factor; newX++){
                       clone.setPixel(newX, newY, p);
                   }
               }
           }
       }
       image.draw();
       clone.draw();
   }

}



