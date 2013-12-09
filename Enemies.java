import javax.swing.*;
import javax.imageio.*;

import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;

class Enemies {
	
	private Image [] Enemy1;
	private Image Enemy2;
	private Image [] Enemy3;
	private Image [] Enemy4;
	
	private int enemy1Current;
	private int enemy3Current;
	private int enemy4Current;
	
	private int enemyType;
	private int enemyX;
	private int enemyY;
	private int xWidth = 70;
	private int yHeight = 70;
	
	private static final double FLIP_TIME_1 = 3;
	private static final double FLIP_TIME_3 = 0.5;
	private static final double FLIP_TIME_4 = 1;
	private double timer1 = 0.0;
	private double timer3 = 0.0;
	private double timer4 = 0.0;
	
	public Enemies(int type, int x, int y) {
		
		enemyType = type;
		enemyX = x;
		enemyY = y;
		
		try {
			Enemy1 = new Image[2];
			Enemy3 = new Image[4];
			Enemy4 = new Image[5];
			Enemy1[0] = ImageIO.read(new File("Enemy1Down.png"));
			Enemy1[1] = ImageIO.read(new File("Enemy1Up.png"));
			Enemy2 = ImageIO.read(new File("Enemy2.png"));
			Enemy3[0] = ImageIO.read(new File("Enemy3Down.png"));
			Enemy3[1] = ImageIO.read(new File("Enemy3Up1.png"));
			Enemy3[2] = ImageIO.read(new File("Enemy3Up2.png"));
			Enemy3[3] = ImageIO.read(new File("Enemy3Up3.png"));
			Enemy4[0] = ImageIO.read(new File("Enemy4Block.png"));
			Enemy4[1] = ImageIO.read(new File("Enemy4JumpBlock.png"));
			Enemy4[2] = ImageIO.read(new File("Enemy4Block.png"));
			Enemy4[3] = ImageIO.read(new File("Enemy4Open1.png"));
			Enemy4[4] = ImageIO.read(new File("Enemy4Open2.png"));
		} catch (Exception e) {
			Enemy1 = null;
			Enemy3 = null;
			Enemy4 = null;
		}
		
	}
	
	public void draw(Graphics g) {
		if (enemyType == 1) {
			g.drawImage(Enemy1[enemy1Current], enemyX, enemyY, xWidth, yHeight, null);
		}
		if (enemyType == 2) {
			g.drawImage(Enemy2, enemyX, enemyY, xWidth, yHeight, null);
		}
		if (enemyType == 3) {
			if (enemy3Current == 0) {
				g.drawImage(Enemy3[enemy3Current], enemyX, enemyY, xWidth, yHeight, null);
			}
			else
				g.drawImage(Enemy3[enemy3Current], enemyX, enemyY - 16, xWidth, yHeight, null);
		}
		if (enemyType == 4) {
			if (enemy4Current == 1) {
				g.drawImage(Enemy4[enemy4Current], enemyX, enemyY - 50, xWidth, yHeight, null);
			}
			else {
				g.drawImage(Enemy4[enemy4Current], enemyX, enemyY, xWidth, yHeight, null);
			}
		}
	}
		
	public void update(double seconds) {
		  timer1 += seconds;
		  timer3 += seconds;
		  timer4 += seconds;
			if(timer1 > FLIP_TIME_1) {
				if (enemy1Current == 1) {
					enemy1Current = 0;
					timer1 = 0;
				}
				else {
					enemy1Current++;
					timer1 = 2.5;
				}
			}
			if(timer3 > FLIP_TIME_3) {
				timer3 = 0;
				if (enemy3Current == 3)
					enemy3Current = 0;
				else
					enemy3Current++;
			}
			if(timer4 > FLIP_TIME_4) {
				timer4 = 0;
				if (enemy4Current == 4) {
					enemy4Current = 0;
					timer4 = 0;
				}
				else if (enemy4Current == 2 || enemy4Current == 3) {
					enemy4Current++;
					timer4 = .75;
				}
				else {
					enemy4Current++;
				}
			}
	}
	
	public void scroll(int scrollValue) {
		enemyX -= scrollValue;
	}
}
