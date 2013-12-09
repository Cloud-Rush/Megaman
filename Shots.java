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
	private static final int BULLET_WIDTH = 30;
	private static final int BULLET_HEIGHT = 30;
	private int xLocation = 0;
	private int yLocation = 0;
	
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
	
	public void draw(Graphics g) {
		g.drawImage(Bullets[0], xLocation, yLocation, BULLET_WIDTH, BULLET_HEIGHT, null);
	}
	
	public void update( ) {
		xLocation = xLocation + (int)PLAYER_SHOT_SPEED;
	}
	
	public void xStart(double location) {
		xLocation = (int)location;
	}
	
	public void yStart(double location) {
		yLocation = (int)location;
	}
}
