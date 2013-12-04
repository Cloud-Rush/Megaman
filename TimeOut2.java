
import java.awt.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.image.BufferedImage;
import java.util.Random;






public class TimeOut2 extends JFrame implements Runnable, KeyListener{

	
	//blank image for double buffering
	private Graphics goff;
	private Image offscreen;
	
	int screenWidth = 1200;
	int screenHeight = 600;
	
	//limits number of bullets shot at one time
	int bullets;
	
	
	// increments for x and y direction of clock
	private int  xDirection, yDirection;
	
	
	// variables for the clock
	Clock C = new Clock();
	Rectangle [] CAnimate = new Rectangle[12];
	int cHeight= 100;
	int cWidth = 100;
	int cx = 0;
	int cy = screenHeight- cHeight-25;
	int j=0;
	// jump height
	int jumpH = cy-150;
	
	
	// keeps jump from repeating
	int count = 0;
	// bullets for clock
	private ArrayList<Bullet> Bullets = new ArrayList<Bullet>();
	//bullets for cloud
	private ArrayList<Bullet> Bullets2 = new ArrayList<Bullet>();
	
	Rectangle r2;
	Rectangle r1; 
	
	//bullet direction
	int bdir=1;
	
	//variables for scrolling background
	int tilesx;
	int tilesy;
	
	// how much screen moves by
	int offsetx=0;
	int offsety =0;
	
	// width and height of image
	int width=screenWidth;
	int height= screenHeight;
	
	// tree width and height
	int tH= 300;
	int tW = 200;
	
	//limits the amount of movement to the right
	int limit=200;
	// generates random sizes for trees
	Random generator = new Random();
	
	int w1=100;
	int h1=100;
	int w2=50;
	int h2= 50;
	
	// background image
	Image bg;
	
	//number of trees on screen at a time
	int numtrees=3;
	EnemyTree [] badtrees = new EnemyTree[numtrees];
	
	// x locations of onscreen and offscreen images
	int onscreenx;
	int offscreenx;
	
	//total number of trees
	int trees = 9;
	// cloud object
	Cloud cloud = new Cloud();
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// constructor, initializes trees clock and cloud
	
	public TimeOut2()
	{
	
		
		
		super("Time Out");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(screenWidth,screenHeight);
		setVisible(true);

		bg = new ImageIcon("background1.gif").getImage();
		
		width= bg.getWidth(this);
		height= bg.getHeight(this);
		
		tilesx = (int)(width/screenWidth);
		tilesy = (int)(height/screenHeight);
		
		for(int i = 0; i< CAnimate.length; i++)
		{
			
		CAnimate[i] = new Rectangle(cx,cy,cWidth,cHeight);	
			
			
		}
		
		for(int i=0; i< numtrees;i++){
		
		int width = generator.nextInt(screenWidth-700)+(generator.nextInt(200)+300);	
		int delay = generator.nextInt(2500)+1000;
		int h = generator.nextInt(300)+75;
		int w = generator.nextInt(200)+75;
		
		badtrees[i] = new EnemyTree(width,(screenHeight-(h+14)),w,h,delay);
		
		}
		
		addKeyListener(this);
		
		
	
		C.start();
		cloud.start();
		Thread t = new Thread(this);
		t.start();
		
		for(int i=0; i< numtrees;i++){
		
			badtrees[i].start();
		
		}
		
		
		
		

		
	}
	
	
	

	//////////////////////////////////////////////////////////////////////////////////////////////
	// double buffered image
	
	public void paint(Graphics g)
	{
		offscreen = createImage(getWidth(),getHeight());
		goff = offscreen.getGraphics();
		
		paintComponent(goff);
		g.drawImage(offscreen, 0, 0, this);
	
		
		
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	/// draws background, clock, trees and cloud
	public void paintComponent(Graphics g)
	{
		//clears screen
		g.setColor(Color.white);
		g.clearRect(0, 0, screenWidth, screenHeight);
	
		
		// if player wins
		if(!(cloud.Alive()))
		{
			
			
			g.drawImage(bg,0,0,null);
			g.setColor(Color.green);	
			Font f = new Font("Arial", Font.BOLD, 150);
			g.setFont(f);
			g.drawString("You Win!!!", 200, 300);	
			C.lives=1000;
			
			
			
		}
		
		// if still playing
		if(C.getLives() >0 && (cloud.Alive()))
		{
			
		//scrolls background
		for(int y =0; y<tilesy; y++)
		{
			for(int x=0; x<tilesx;x++)
			{
				
				 onscreenx= x*width-offsetx;
				 offscreenx= width-offsetx;
		
			
				g.drawImage(bg, onscreenx, y*height-offsety, null);
				;
				g.drawImage(bg, offscreenx, y*height-offsety, null);
				
			}
		
		}	
		

		
		
		
			// initializes new trees
		if(onscreenx < -1194 && !badtrees[0].Alive() && !badtrees[1].Alive() && !badtrees[2].Alive() && (trees>0))
		{
			trees= trees-numtrees;
			
			
				
			for(int i=0; i< numtrees;i++){
				
				int width = generator.nextInt(screenWidth-700)+(generator.nextInt(200)+300);	
				int delay = generator.nextInt(2500)+1000;
				int h = generator.nextInt(300)+75;
				int w = generator.nextInt(200)+75;
				
				badtrees[i] = new EnemyTree(width,(screenHeight-(h+14)),w,h,delay);
				
				}
			for(int i=0; i< numtrees;i++){
				
				if(trees>0)
				badtrees[i].start();
			
			}
			
			
		}
		
		// draws animated clock
		if(C.getImage() != null){
			g.setColor(Color.yellow);
			g.drawRect(CAnimate[C.getCurrent()].x, CAnimate[C.getCurrent()].y, cWidth, cHeight);
			g.drawImage(C.getImage(), CAnimate[C.getCurrent()].x , CAnimate[C.getCurrent()].y, cWidth, cHeight, this);
			
		}
		
		//draws trees
		if(trees>0)
		for(int i=0; i< numtrees;i++){
			
	
		if(badtrees[i] != null)	
		badtrees[i].drawTree(g);
		
		}
		
		
		// draws bullets for clock
		Bullets = C.getBullets();
		for(int i =0; i<Bullets.size(); i++)
		{
			Bullet b = (Bullet) Bullets.get(i);
			g.setColor(Color.yellow);
			g.fillOval(b.getX(), b.getY(), 10, 5);
			
			
			
		}
		// draws bullets for trees
		if(trees>0){
		for(int i=0; i< numtrees;i++){
			
			if(badtrees[i]!=null)
			badtrees[i].drawBullets(g);
		
		}
		}
		
		
		// when cloud appears
		if(trees <= 0)
		{
		
		cloud.drawCloud(g);	
		offsetx = (offsetx+5)%width;
		limit=1000;
		
		
		
		drawCBullets(g);
		
		int rate = (int)(Math.random()*25);
		
		if(cloud.Alive() && rate ==0)
		{	
		cloud.shoot(cloud.getX()+150, cloud.getY()+100);
		
		}
		
			
			
		}
		
		
		
		
		
			
		}
		// if player loses
		if(C.lives <= 0)
		{
			
		g.drawImage(bg,0,0,null);
		g.setColor(Color.red);	
		Font f = new Font("Arial", Font.BOLD, 150);
		g.setFont(f);
		g.drawString("Game Over!!!", 200, 300);	
			
			
			
		}
		
	
		
	}
	
	
	
	
	
	// draws bullets for cloud
	public void drawCBullets(Graphics g)
	{
		
		
		
		Bullets2 = cloud.getBullets();
		for(int i =0; i<Bullets2.size(); i++)
		{
			Bullet a = (Bullet) Bullets2.get(i);
			g.setColor(Color.blue);
			g.fillOval(a.getX(), a.getY(), 15, 15);
			
			
			
		}
		
	}

	///////////////////////////////////////////////////////////////

	// shoots bullets for cloud
	public void shooting2()
	{
		ArrayList Bullets2 = cloud.getBullets();
		for(int i=0; i< Bullets2.size();i++)
		{
			Bullet a = (Bullet)Bullets2.get(i);
			if(a.isVisible()==true)
			{
				a.update(4, 5);
			} else{
				
				Bullets2.remove(i);
				
			}
			
			
			
			
		}
		
		
	}
	
	
	// checks to see if cloud hits the clock
	public boolean checkcollision2(Rectangle r3)
	{
		
		boolean shot =false;
		ArrayList B2 = cloud.getBullets();	
		
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
		
	
	
	///////////////////////////////////////////////////////////////////////////////
	////////// checks if tree bullets collide with clock
	
	
	public void checkCollisions()
	{
		

		Rectangle r3 = new Rectangle(CAnimate[C.current].x,CAnimate[C.current].y,cWidth,cHeight);
		
		ArrayList B = C.getBullets();
		
		// sees if clock hits trees
		for(int i=0; i< numtrees;i++){
		badtrees[i].isHit(B);
		
		
		//sees if trees hit clock
		if(badtrees[i].checkcollision(r3))
		{
			if(C.current<11)
					C.current++;
			else
				C.lives=0;
			
		}
		
		
		
		}
		// sees if cloud is hit
		if(trees<=0)
		{
		cloud.isHit(B);
		// sees if cloud hits clock
		if(checkcollision2(r3))
		{
			if(C.current<11)
					C.current++;
			else
				C.lives=0;
			
		}
		
		}
		
		
	
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// limits movement of clock
	public void move(){

		int previousX = CAnimate[C.getCurrent()].x;
		int previousY = CAnimate[C.getCurrent()].y;

	
		
		
		CAnimate[C.getCurrent()].x += xDirection;
		CAnimate[C.getCurrent()].y += yDirection;
			
	
		r2 = new Rectangle(CAnimate[C.getCurrent()].x,CAnimate[C.getCurrent()].y,cWidth,cHeight);
			
		
		if(CAnimate[C.getCurrent()].y > (screenHeight-cHeight)){
			CAnimate[C.getCurrent()].y = (screenHeight-cHeight);

		}

		if(CAnimate[C.getCurrent()].y <= (24)){
	 	CAnimate[C.getCurrent()].y = 24;
		
		}
		
		if(CAnimate[C.getCurrent()].x >= (limit+1)){
		CAnimate[C.getCurrent()].x= limit+1;
		
		}
		
		if(CAnimate[C.getCurrent()].x <= (0)){
		CAnimate[C.getCurrent()].x= 0;
		
		}


		
		updateAllFrames(CAnimate, CAnimate[C.getCurrent()].x,CAnimate[C.getCurrent()].y);
	
	
	}		

	
	

	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	// updates location of all frames for animated object
public void updateAllFrames (Rectangle [] values, int newValueX, int newValueY)
{


for(int i = 0; i < values.length; i++)
{

values[i].x = newValueX;
values[i].y = newValueY;		

}


}		
	

// shoots bullets for clock
public void shooting()
{
	ArrayList Bullets = C.getBullets();
	for(int i=0; i< Bullets.size();i++)
	{
		Bullet b = (Bullet)Bullets.get(i);
		if(b.isVisible()==true)
		{
			b.update(bdir, 5);
		} else{
			
			Bullets.remove(i);
			
		}
		
		
		
		
	}
	
	
}



	
//////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////









/// sets x direction
public void setXDirection(int xDir)
{


xDirection = xDir;
}


//sets y direction	
public void setYDirection(int yDir){


yDirection = yDir;

}

//////////////////////////////////////////////////////////////////////////////////
// checks for collisions and shoots bullets
public void run(){
	
	
	//Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
	while(true)
	{
		
		 move();
		checkCollisions();
		shooting();
		shooting2();
		
		for(int i=0; i< numtrees;i++){
		badtrees[i].shooting();
		
		}
		
		
		repaint();
		
		
		try
		{
		Thread.sleep(10);
		
		}
		catch(Exception e){}
	
	
		}
	
	}
	
	



//////////////////////////////////////////////////////////////
//detects when key is pressed increments or decrements x or y direction
public void keyPressed(KeyEvent e){

int key = e.getKeyCode();



if (key== KeyEvent.VK_UP){

	bdir=3;
	bullets=0;

}
if (key== KeyEvent.VK_DOWN){


setYDirection(10);

}
// moves clock, scrolls background
if (key== KeyEvent.VK_LEFT){

setXDirection(-10);
bdir=2;
if(offsetx>=0)
offsetx = (offsetx-5)%width;


}
//moves clock scrolls background
if (key== KeyEvent.VK_RIGHT){
	
	
bdir=1;	
setXDirection(10);


if(CAnimate[C.current].x > limit)
{

offsetx = (offsetx+5)%width;

}
}

if (key == KeyEvent.VK_ESCAPE){

System.exit(0);

}
// shoots clock bullets 
if(key== KeyEvent.VK_SPACE){


if(bullets < 3)	
C.shoot(CAnimate[C.getCurrent()].x, CAnimate[C.getCurrent()].y+ 70 );	
bullets++;

}


// jump
if(key== KeyEvent.VK_X && count==0){
	
	
	while(CAnimate[C.getCurrent()].y> jumpH)
	{
	CAnimate[C.getCurrent()].y--;
	count++;
	
	}

}



}



	
 
/////////////////////////////////////////////////////////////

public void keyTyped(KeyEvent e){	
	
}	

///////////////////////////////////////////////////////////////////////////////////
// stops movement when key is released
public void keyReleased(KeyEvent e)
{

	int key = e.getKeyCode();

	if (key== KeyEvent.VK_UP){

	setYDirection(0);

	}
	if (key== KeyEvent.VK_DOWN){

	setYDirection(0);

	}
	if (key== KeyEvent.VK_LEFT){

	setXDirection(0);

	}
	if (key== KeyEvent.VK_RIGHT){

	setXDirection(0);

	}
	//lowers clock after jump
	if (key== KeyEvent.VK_X){

		while(CAnimate[C.getCurrent()].y < cy)
		{
		CAnimate[C.getCurrent()].y++;
		
		}
		count =0;
	
	}
	
	if (key== KeyEvent.VK_SPACE){
	
	bullets=0;		
	
	}
	
	
}



//////////////////////////////////////////////////////////////////////////////////////////////////////////
//main method

public static void main(String[] args)
{
	
	
	new TimeOut2();
	
	
	
}


	
}
