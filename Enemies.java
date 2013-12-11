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
        
        private Shots Enemy1Shot;
        private Shots Enemy3Shot;
        private Shots Enemy4Shot;
        
        private int enemy1Current;
        private int enemy3Current;
        private int enemy4Current;
        
        private int enemyType;
        private int enemyX;
        private int enemyY;
        private int xWidth = 70;
        private int yHeight = 70;
        
        private static final double FLIP_TIME_1 = 3;
        private static final double FLIP_TIME_3 = 0.25;
        private static final double FLIP_TIME_4 = 1;
        private double timer1 = 0.0;
        private double timer3 = 0.0;
        private double timer4 = 0.0;
        Rectangle rectangle = new Rectangle(enemyX,enemyY,xWidth,yHeight);
        boolean alive = true;
        private int hits = 0;
        
        public Enemies(int type, int x, int y) {
                
                enemyType = type;
                enemyX = x;
                enemyY = y;
                
                try {
                        Enemy1 = new Image[2];
                        Enemy3 = new Image[6];
                        Enemy4 = new Image[5];
                        Enemy1[0] = ImageIO.read(new File("Enemy1Down.png"));
                        Enemy1[1] = ImageIO.read(new File("Enemy1Up.png"));
                        Enemy2 = ImageIO.read(new File("Enemy2.png"));
                        Enemy3[0] = ImageIO.read(new File("Enemy3Down.png"));
                        Enemy3[1] = ImageIO.read(new File("Enemy3Up1.png"));
                        Enemy3[2] = ImageIO.read(new File("Enemy3Up2.png"));
                        Enemy3[3] = ImageIO.read(new File("Enemy3Up3.png"));
                        Enemy3[4] = ImageIO.read(new File("Enemy3Up2.png"));
                        Enemy3[5] = ImageIO.read(new File("Enemy3Up1.png"));
                        Enemy4[0] = ImageIO.read(new File("Enemy4Block.png"));
                        Enemy4[1] = ImageIO.read(new File("Enemy4JumpBlock.png"));
                        Enemy4[2] = ImageIO.read(new File("Enemy4Block.png"));
                        Enemy4[3] = ImageIO.read(new File("Enemy4Open1.png"));
                        Enemy4[4] = ImageIO.read(new File("Enemy4Open2.png"));
                        Enemy1Shot = new Shots(1);
                        Enemy3Shot = new Shots(1);
                        Enemy4Shot = new Shots(2);
                } catch (Exception e) {
                        Enemy1 = null;
                        Enemy3 = null;
                        Enemy4 = null;
                }
                
        }
        
        public void draw(Graphics g) {
                if(alive){
                	if (enemyType == 1) {
                        if(enemy1Current == 1){
                        g.drawImage(Enemy1[enemy1Current], enemyX, enemyY, xWidth, yHeight, null);
                        g.setColor(Color.red);
                        rectangle.setLocation(enemyX,enemyY-10);
                        rectangle.resize(xWidth-20, yHeight-20);
//                      g.drawRect(enemyX+5, enemyY, xWidth-30, yHeight-30);
                        Enemy1Shot.xStartLeft(enemyX - 5);
                        Enemy1Shot.yStart(enemyY);
                        }
                        else{
                        		g.drawImage(Enemy1[enemy1Current], enemyX, enemyY, xWidth, yHeight, null);
                                rectangle.setLocation(-100,-100);
                                rectangle.resize(0, 0);        
                                
                        }
                        
                }
                if (enemyType == 2) {
                        g.drawImage(Enemy2, enemyX, enemyY, xWidth, yHeight, null);
                        g.setColor(Color.red);
                        rectangle.setLocation(enemyX+5,enemyY+7);
                        rectangle.resize(xWidth-34, yHeight-32);
//                      g.drawRect(enemyX+5, enemyY+7, xWidth-34, yHeight-32);
                        
                }
                if (enemyType == 3) {
                        if (enemy3Current == 0) {
                                g.drawImage(Enemy3[enemy3Current], enemyX, enemyY, xWidth, yHeight, null);
                                g.setColor(Color.yellow);
                                rectangle.setLocation(-100,-100);
                                rectangle.resize(0, 0);
//                              g.drawRect(enemyX+5, enemyY+2, xWidth-10, yHeight-10);
                        
                        }
                        else if (enemy3Current == 3) {
                        	g.drawImage(Enemy3[enemy3Current], enemyX, enemyY - 16, xWidth, yHeight, null);
                            g.setColor(Color.green);
                            rectangle.setLocation(enemyX,enemyY-10);
                            rectangle.resize(xWidth-25, yHeight-20);
//                          g.drawRect(enemyX, enemyY-10, xWidth-25, yHeight-20);
                            Enemy3Shot.xStartLeft(enemyX - 20);
                            Enemy3Shot.yStart(enemyY - 20);
                        }
                        else {
                                g.drawImage(Enemy3[enemy3Current], enemyX, enemyY - 16, xWidth, yHeight, null);
                                g.setColor(Color.green);
                                rectangle.setLocation(enemyX,enemyY-10);
                                rectangle.resize(xWidth-25, yHeight-20);
//                              g.drawRect(enemyX, enemyY-10, xWidth-25, yHeight-20);
                        }
                }
                if (enemyType == 4) {
                        if (enemy4Current == 1) {
                        	g.drawImage(Enemy4[enemy4Current], enemyX, enemyY - 50, xWidth, yHeight, null);
                        	g.setColor(Color.red);
                        	rectangle.setLocation(enemyX+5,enemyY-50);
                        	rectangle.resize(xWidth-10, yHeight-10);
//                        	g.drawRect(enemyX+5, enemyY-50, xWidth-8, yHeight);
                        }
                        else if (enemy4Current == 4) {
                        	g.drawImage(Enemy4[enemy4Current], enemyX, enemyY, xWidth, yHeight, null);
                            g.setColor(Color.red);
                            rectangle.setLocation(enemyX+5,enemyY+2);
                            rectangle.resize(xWidth-10, yHeight-10);
//                          g.drawRect(enemyX+5, enemyY+2, xWidth-10, yHeight-10);
                            Enemy4Shot.xStartLeft(enemyX - 20);
                            Enemy4Shot.yStart(enemyY + 20);
                        }
                        else {
                                //if(enemy4Current == 3 || enemy4Current == 4){
                        	g.drawImage(Enemy4[enemy4Current], enemyX, enemyY, xWidth, yHeight, null);
                        	g.setColor(Color.red);
                        	rectangle.setLocation(enemyX+5,enemyY+2);
                        	rectangle.resize(xWidth-10, yHeight-10);
//                        	g.drawRect(enemyX+5, enemyY+2, xWidth-10, yHeight-10);
                                //}
//                        		else{
//                                        g.drawImage(Enemy4[enemy4Current], enemyX, enemyY, xWidth, yHeight, null);
//                                        rectangle.setLocation(-100,-100);
//                                        rectangle.resize(0, 0);                        
//                                }
                        }
                	}
                } 
                else { 
                	rectangle.setLocation(-100,-100);
                }
                Enemy1Shot.ShotCreator(g, 1);
                Enemy3Shot.ShotCreator(g, 1);
                Enemy4Shot.ShotCreator(g, 2);
        }
                
        public void isHit(){
        	// if(rectangle.intersects()){
        	if (enemyType == 4 && enemy4Current != 0 && enemy4Current != 2 || enemyType == 3)
        	hits++;
        	
        	if (enemyType == 3 && hits >= 3) {
        		alive = false;
        		GameWorld.enemyDeath.play();
        		Background.score += 300;
        	}
        	else if (enemyType == 4 && hits >= 5) {
        		alive = false;
        		GameWorld.enemyDeath.play();
        		Background.score += 600;
        	}
        	else if (enemyType == 2) {
        		alive = false;
        		GameWorld.enemyDeath.play();
        		Background.score += 150;
        	}
        	else if (enemyType == 1) {
                 alive = false;
                 GameWorld.enemyDeath.play();
                 Background.score += 100000;
        	}
        	
            // }
        }
        
        
        public Rectangle getRectangle(){
                return rectangle;
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
                                if (enemy3Current == 5)
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
        
        public boolean isAlive() {
        	return alive;
        }
        
        public int getEnemyType() {
        	return enemyType;
        }
}
