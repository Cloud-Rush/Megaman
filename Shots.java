import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;

class Shots {
	private Image [] Bullets;
	private static final double PLAYER_SHOT_SPEED = 50.0;
	private static final double ENEMY_SHOT_SPEED = 50.0;
	private static final int PLAYER_BULLET_WIDTH = 30;
	private static final int PLAYER_BULLET_HEIGHT = 30;
	private static final int ENEMY1_BULLET_WIDTH = 30;
	private static final int ENEMY1_BULLET_HEIGHT = 30;
	private static final int ENEMY4_BULLET_WIDTH = 30;
	private static final int ENEMY4_BULLET_HEIGHT = 30;
	private int xLocation = 0;
	private int yLocation = 0;
	private int dx = 0;
	
	int index = 0;
	boolean hitsomething= false;
	Rectangle rectangle = new Rectangle();
	Rectangle TempRectangle = new Rectangle();
	
	public Shots(int shotIndicator) {
		try {
			Bullets = new Image[3];
			Bullets[0] = ImageIO.read(new File("MMBullet.png"));
			Bullets[1] = ImageIO.read(new File("Enemy1Bullet.png"));
			Bullets[2] = ImageIO.read(new File("Enemy4Bullet.png"));
		} catch (Exception e) {
			Bullets = null;
		}
	}
	
	public void ShotCreator(Graphics g, int index) {
		draw(g, index);
		update( );
	}
	
	public void draw(Graphics g, int index) {
		if (index == 0) {
			if(!hitsomething) {
				if(xLocation>1320)
					yLocation=-1000;
				rectangle.setLocation(xLocation+3,yLocation+3);
				rectangle.setBounds(xLocation+3, yLocation+3, PLAYER_BULLET_WIDTH-15, PLAYER_BULLET_HEIGHT-15);
				g.drawRect(xLocation+3, yLocation+3, PLAYER_BULLET_WIDTH-15, PLAYER_BULLET_HEIGHT-15);
				hitSomething();
			}
			g.drawImage(Bullets[0], xLocation, yLocation, PLAYER_BULLET_WIDTH, PLAYER_BULLET_HEIGHT, null);
		}
	}
	
	public void update( ) {
		xLocation = xLocation + dx;
	}
	
	public void xStartRight(double location) {
		xLocation = (int)location;
		hitsomething=false;
		dx = (int)PLAYER_SHOT_SPEED;
	}
	
	public void xStartLeft(double location) {
		xLocation = (int)location;
		hitsomething=false;
		dx = -(int)PLAYER_SHOT_SPEED;
	}
	
	public void yStart(double location) {
		yLocation = (int)location;
	}
	
	 public void hitSomething() {
		 index = Background.Enemy.size();
		 for(int i = 0; i < index; i++) {
			 TempRectangle = Background.Enemy.get(i).getRectangle();
			 if(rectangle.intersects(TempRectangle)) {
				 Background.Enemy.get(i).isHit();
				 hitsomething=true;
		 	}
		 }
	 }
}
