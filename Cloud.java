
import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;



public class Cloud extends Thread {

	
	
	
	
	
	Enemy en;
	int cWidth=300;
	int cHeight=150;
	boolean left;
	int hits=0;
	int x;
	int y;
	
	private ArrayList<Bullet> Bullets = new ArrayList<Bullet>();
	
	// initializes cloud
	public Cloud(){
		
		 x = 300;
		 y = 100;
		
		en = new Enemy(x, y, "Cloudemy.gif");
		
	
		
		
	}
	// retuns x
	public int getX()
	{
		
		return en.x;
		
	}
	
	
	// returns y
	public int getY()
	{		
		return en.y;

	}
	
	// returns whether cloud is alive or not
	public boolean Alive(){
		
		
		return en.Alive();
		
		
	}
	
	// draws the cloud
	public void drawCloud(Graphics g)
	{
		
		
		if(en.Alive() == true)
		{
			g.drawImage(en.getImage(), en.getX(), en.getY(), cWidth,cHeight, null);
		}
		
		
		
	}
	
	
	
	
// detects if cloud is hit by bullets
	public void isHit(ArrayList B)
	{
		
		
		Rectangle r1 = en.getBounds();
		en.setH(cHeight);
		en.setW(cWidth);
	
		for (int w = 0; w<B.size();w++)
		{
			
			Bullet m = (Bullet) B.get(w);
			Rectangle m1 = m.getBounds();
			
			if(r1.intersects(m1) && en.Alive())
			{
				hits++;
				B.remove(m);
				
				if(hits >= 30)
				{
				en.isAlive = false;
				m.setVisible(false);
				}
				
				
			}
		
		
		}
	
		
		
	}	

	
	
	

/////////////////////////////////////////////////////////////////////////////////////
// returns array list of bullets
public ArrayList getBullets(){

return Bullets;

}	
	
	
///////////////////////////////////////////////////////	
// adds bullets to arraylist
public void shoot(int centerX,int centerY)
{

Bullet b = new Bullet(centerX, centerY);
Bullets.add(b);

}
	




	
//////////////////////////////////////////////////////////////////////////////////
// moves cloud left and right
public void run(){


//Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
while(true)
{


if(en.x <= 5){
	
	left = false;
}	

if( left && en.x > 5)
{
	
	en.x-=20;
	
}
else
{
	en.x+=20;
	
}


if( en.x > 1000)
{
	
	left = true;
	
}



try
{
Thread.sleep(100);

}
catch(Exception e){}




}	


}	


	
	
	
	
	
	
	
	
}
