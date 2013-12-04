

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;



public class Tree extends Thread {

	private Image[] tree = new Image[2];
	int current = 0;
	private ArrayList<Bullet> Bullets = new ArrayList<Bullet>();
	
	
	// calls load pics
	public Tree(){
		
		loadPics();
	
	}
	// loads pictures into an array
	public void loadPics(){
		
	String name;
		
		for(int i = 0; i< tree.length; i++){
			
			
			//Create a string that is the name of your image files
			name = new String ("Trenemy"+ i + ".gif");
			//System.out.print("Image = " + name);
			//Reads in your file
			ImageIcon image = new ImageIcon(name);
			//converts that ImageIcon into an Image
			// put your image name
			tree[i]= image.getImage();
			
			
		}
		
		
		
	}
	
	
	
	
	
	
///////////////////////////////////////////////////////	
// adds bullets to arraylist
public void shoot(int centerX,int centerY)
{
	
	Bullet b = new Bullet(centerX, centerY);
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
		
		
		
		return tree[current];
		
	}
		
		
	////////////////////////////////////////////////////////////////////////////////////////////////	
		// returns height
		public int getHeight(){
			
			
		return tree[current].getHeight(null);	
			
		}
		
		
		
	/////////////////////////////////////////////////////////////////////////////////////////////	
		// returns width
		public int getWidth(){
			
			
			return tree[current].getWidth(null);	
				
			}
		
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
		// returns current image
		public int getCurrent(){
			
			
			return current;
			
			
		}
	
////////////////////////////////////////////////////////////////////////////////////////////////////
		// returns particular image
	public Image getImageNumber(int number)
	{
		
		return tree[number];
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	// cycles through images
	public void run() {
		
		while (true) {
			
			if((current < (tree.length-1)))
			{
				current+=1;
			}
			else
			{
				 current=0;
			
			}
			try {
				
				Thread.sleep(500);
				
			} catch (InterruptedException e) {
				// do nothing
				
				
			}
			
		}
		
	}
	
	
	
	
}


	

