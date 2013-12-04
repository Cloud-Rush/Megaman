






import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;



public class Clock extends Thread {

	private Image[] clock = new Image[12];
	int current = 11;
	private ArrayList<Bullet> Bullets = new ArrayList<Bullet>();
	int lives = 2;
	
	
	// calls loadpics
	public Clock(){
		
		loadPics();
	
	}
	// loads pictures for clock
	public void loadPics(){
		
	String name;
		
		for(int i = 0; i< clock.length; i++){
			
			
			//Create a string that is the name of your image files
			name = new String ("Clock"+ i + ".gif");
			//System.out.print("Image = " + name);
			//Reads in your file
			ImageIcon image = new ImageIcon(name);
			//converts that ImageIcon into an Image
			// put your image name
			clock[i]= image.getImage();
			
			
		}
		
		
		
	}
	
	
	
	// returns number of lives
public int getLives()
{
	
	return lives;
	
}

// sets number of lives
public void setLives(int l)
{
	
	lives = l;
	
}
	
	
///////////////////////////////////////////////////////	
// adds bullets to array list
public void shoot(int centerX,int centerY)
{
	
	Bullet b = new Bullet(centerX + 50, centerY-25);
	Bullets.add(b);
	
}

/////////////////////////////////////////////////////////////////////////////////////
// returns array list of bullets
public ArrayList getBullets(){
	
	return Bullets;
	
}
	
////////////////////////////////////////////////////////////////////////////////////
// returns current image
	public Image getImage(){
		
		
		
		return clock[current];
	
		
	}
		
		
	////////////////////////////////////////////////////////////////////////////////////////////////	
		// returns height
		public int getHeight(){
			
			
		return clock[current].getHeight(null);	
			
		}
		
		
		
	/////////////////////////////////////////////////////////////////////////////////////////////	
		// returns width
		public int getWidth(){
			
			
			return clock[current].getWidth(null);	
				
			}
		
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
		//returns number of current image
		public int getCurrent(){
			
			
			return current;
			
			
		}
	
////////////////////////////////////////////////////////////////////////////////////////////////////
		// returns particular image
	public Image getImageNumber(int number)
	{
		
		return clock[number];
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	// cycles through images, reduces lives after full cycle
	public void run() {
		
		while (true) {
			
			if((current < (clock.length-1)))
			{
				current+=1;
			}
			else
			{
				 current=0;
				 lives--;
			
			}
			try {
				
				Thread.sleep(15000);
				
			} catch (InterruptedException e) {
				// do nothing
				
				
			}
			
		}
		
	}
	
	
	
	
}

