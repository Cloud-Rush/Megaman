
import java.awt.Image;
import java.awt.Rectangle;




public class Bullet {

	private int x,y, speedx;
	private boolean visible;
	Image img;
	int w = 10, h =5;
	int dire=1;
	
	int speedX;
	
	//initializes bullet
	public Bullet(int startX, int startY)
	{
	
		x = startX;
		y = startY;
		speedX=7;
		visible = true;
		//ImageIcon newBullet = new ImageIcon("bullet.gif");
		//img = newBullet.getImage();
		
		
		
	}
	
	// returns rectangle bounds of bullet
	public Rectangle getBounds()
	{
		
		return new Rectangle(x,y,w,h);
		
	}
	
	//updates speed and direction
	public void update(int dir, int speed)
	{
	dire=dir;
	speedX= speed;	
	
	if(dire== 1)	
	x+= speedX;
	if(x >1200){
		 visible = false;
	}
	if(dire== 2)	
	x-= speedX;
	if(x <0){
		 visible = false;
	}
	if(dire== 3)	
		y-= speedX;
		if(y <0){
			 visible = false;
		}
		
		if(dire== 4)	
			y+= speedX;
			if(y > 600){
				 visible = false;
			}
			
	
		
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
	
	// returns speed
	public int getSpeedX()
	{
		
		return speedx;	
		
	}
	// returns if visible or not
	public boolean isVisible()
	{
		
		return visible;
		
	}
	
	// sets x value
	public void setX(int x)
	{
		this.x = x;
		
	}
	//sets y value
	public void setY(int y)
	{
		this.y = y;
	
	}
	
	//sets speed
	public void setSpeedX(int speedX)
	{
		this.speedX = speedX;
		
	}
	// sets visibility
	public void setVisible(boolean visible)
	{
		
		this.visible = visible;
	}
	

}
