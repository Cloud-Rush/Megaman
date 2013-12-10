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
	private static final int ENEMY_BULLET_WIDTH = 60;
	private static final int ENEMY_BULLET_HEIGHT = 60;
	private int xLocation = -100;
	private int yLocation = -100;
	private int dx = 0;
	private int shotIndicator = 0;
	
	int index = 0;
	boolean hitsomething = false;
	private static boolean MMhit = false;
	Rectangle rectangle = new Rectangle();
	Rectangle TempRectangle = new Rectangle();
	Rectangle MMRectangle = new Rectangle();
	
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
			if(!hitsomething && shotIndicator == 0) {
				if(xLocation>1320)
					yLocation=-1000;
				rectangle.setLocation(xLocation+3,yLocation+3);
				rectangle.setBounds(xLocation+3, yLocation+3, PLAYER_BULLET_WIDTH-15, PLAYER_BULLET_HEIGHT-15);
				//g.drawRect(xLocation+3, yLocation+3, PLAYER_BULLET_WIDTH-15, PLAYER_BULLET_HEIGHT-15);
				hitSomething();
				g.drawImage(Bullets[index], xLocation, yLocation, PLAYER_BULLET_WIDTH, PLAYER_BULLET_HEIGHT, null);
				shotIndicator = index;
			}
			else
				rectangle.setBounds(0, 0, 0, 0);
			if(!hitsomething && (shotIndicator == 1 || shotIndicator == 2)) {
				if(xLocation>1320)
					yLocation=-1000;
				rectangle.setLocation(xLocation+3,yLocation+3);
				rectangle.setBounds(xLocation+3, yLocation+3, ENEMY_BULLET_WIDTH-15, ENEMY_BULLET_HEIGHT-15);
				//g.drawRect(xLocation+3, yLocation+3, PLAYER_BULLET_WIDTH-15, PLAYER_BULLET_HEIGHT-15);
				hitSomething();
				g.drawImage(Bullets[index], xLocation, yLocation, ENEMY_BULLET_WIDTH, ENEMY_BULLET_HEIGHT, null);
				shotIndicator = index;
			}
			else
				rectangle.setBounds(0, 0, 0, 0);
	}
	
	public void update( ) {
		if(!hitsomething)
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
			 MMRectangle = Character.getMMRectangle(); 
			 if(shotIndicator == 0 && rectangle.intersects(TempRectangle)) {
				 Background.Enemy.get(i).isHit();
				 hitsomething = true;
		 	}
			 else if ((shotIndicator == 1 || shotIndicator == 2) && rectangle.intersects(MMRectangle)) {
				 hitsomething = true;
				 MMhit = true;
			 }
		 }
	 }
	 
	 public static boolean getMMhit() {
		 return MMhit;
	 }
	 
	 public static void setMMhit(boolean truefalse) {
		 MMhit = truefalse;
	 }
}
