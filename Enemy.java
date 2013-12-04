

import java.awt.*;
import javax.swing.*;



public class Enemy {

	Image img;
	int x, y, w, h;
	boolean isAlive = true;
	
	//intializes enemy
	public Enemy(int startX, int startY, String location)
	{
		x = startX;
		y = startY;
		
		ImageIcon l = new ImageIcon(location);
		img= l.getImage();
		
		
		
	}
	// returns x
	public int getX()
	{
		return x;
	
	}
	//returns y
	public int getY()
	{
		return y;
	
	}
	//set width
	public void setW(int width)
	{
		w = width;
	
	}
	//set height
	public void setH(int height)
	{
		h = height;
	
	}
	
	// enemy is alive or not
	public boolean Alive()
	{
		return isAlive;
	
	}
	
	// returns image
	public Image getImage()
	{
		
		return img;
		
	}
	
	// moves enemy
	public void move(int dx)
	{
		
		x = x-dx;
		
	}
	// returns rectangle bounds of enemy
	public Rectangle getBounds()
	{
	
		return new Rectangle(x,y,w,h);
		
	}
	
	
	
	
	
}
