import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;




public class EnemyTree extends Thread{

	
	
	Tree T = new Tree();
	Rectangle [] TAnimate = new Rectangle[2];
	
	Enemy en;
	
	private ArrayList<Bullet> Bullets2 = new ArrayList<Bullet>();
	
	int tHeight;
	int tWidth;
	int tx ;
	int ty;
	int freq;
	
	int low ;
	int high;	
	private int hits=0;
	
	
	
	// intializes tree
	public EnemyTree(int x, int y, int w, int h, int f){
		
		
		tx=x;
		ty=y;
		tWidth = w;
		tHeight = h;
		freq =f;
		hits=0;
		
		low = (tHeight-(tHeight/8));
		high = 	(tHeight-((6*tHeight)/8));
		
		for(int i = 0; i< TAnimate.length; i++)
		{
			
		TAnimate[i] = new Rectangle(tx,ty,tWidth,tHeight);	
			
			
		}
		
		
		
		en = new Enemy(x, y, "Trenemy0.gif");
		en.setH(h);
		en.setW(w);
		
		T.start();
		
		
		
	}
	
	
	// returns if tree is alive or not
	
	public boolean Alive(){
		
		
		return en.Alive();
		
		
	}
	
	
	// updates bullet direction and speed
	public void shooting()
	{
		ArrayList Bullets2 = T.getBullets();
		for(int i=0; i< Bullets2.size();i++)
		{
			Bullet a = (Bullet)Bullets2.get(i);
			if(a.isVisible()==true)
			{
				a.update(2, 2);
			} else{
				
				Bullets2.remove(i);
				
			}
			
			
			
			
		}
		
		
	}
	
	// shoots bullets for tree
	public void shoot(int bH)
	{
		
		
		if(en.Alive() == true)
		{	
		T.shoot(TAnimate[T.getCurrent()].x, (TAnimate[T.getCurrent()].y+bH));
		
		}
		
	}
	
	
	
	
	
	// draws a tree
	
	public void drawTree(Graphics g){
		
		
		if(en.Alive() == true)
		{
			g.drawImage(T.getImage(), en.getX(), en.getY(), tWidth,tHeight, null);
		}
		
		
	}
	
	// draws bullets for trees
	public void drawBullets(Graphics g)
	{
		
		
		
		Bullets2 = T.getBullets();
		for(int i =0; i<Bullets2.size(); i++)
		{
			Bullet a = (Bullet) Bullets2.get(i);
			g.setColor(Color.red);
			g.fillOval(a.getX(), a.getY(), 10, 5);
			
			
			
		}
		
		
		
	}
	
	// detects if tree is hit
	
	public void isHit(ArrayList B)
	{
		
		
		Rectangle r1 = en.getBounds();
		en.setH(tHeight);
		en.setW(tWidth);
	
		for (int w = 0; w<B.size();w++)
		{
			
			Bullet m = (Bullet) B.get(w);
			Rectangle m1 = m.getBounds();
			
			if(r1.intersects(m1) && en.Alive())
			{
				hits++;
				B.remove(m);
				
				if(hits >= 10)
				{
				en.isAlive = false;
				m.setVisible(false);
				}
				
				
			}
		
		
		}
	
		
		
	}	
	
	
////////////////////////////////////////////////////////
/////////////// do enemy bullets collide with clock?
	
	public boolean checkcollision(Rectangle r3)
	{
		
		boolean shot =false;
		ArrayList B2 = T.getBullets();	
		
		for (int z = 0; z<B2.size();z++)
		{
			
			Bullet k = (Bullet) B2.get(z);
			Rectangle m2 = k.getBounds();
			
			if(r3.intersects(m2))
				
			{
				
				k.setVisible(false);
				shot = true;
				
			}
			
		
		
		}
	
		return shot;
	
	}
		
		
		

	
//////////////////////////////////////////////////////////////////////////////////
	// shoots bullets either high or low, controls frequency
public void run(){


//Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
while(true)
{

	try
	{
	Thread.sleep(freq);

	}
	catch(Exception e){}


	
	
	
int c = (int)(Math.random()*2);
if(c == 0)
shoot(low);
else
if(c == 1)
shoot(high);	



}	
		
	
}	
	
	
	
	
	
}
