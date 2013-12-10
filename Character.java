import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;

class Character {
  private static final double SPEED = 250.0;
  private static final double ACCELERATION = 450.0;
  private static final double WIDTH = 70.0;
  private static final double HEIGHT = 70.0;
  private double x, y, dx, dy, dy2;

  /* now we have an array of images */
  private Image [] megaman;
  private Image [] megaman1;
  private Image [] megaman2;
  private Shots [] MMshot;
  private int shotIndex = 0;
  private static Rectangle MMRectangle = new Rectangle();
  private Rectangle TempRectangle = new Rectangle();
  private Rectangle OnRectangle = new Rectangle();
  private Rectangle LeftRectangle = new Rectangle();
  private Rectangle above = new Rectangle();
  private int current;
  private static boolean right = true;
  private boolean horizontalMoving = false;
  private boolean verticalMoving = false;
  private boolean shootFrame = false;
  boolean under = false;
  boolean wasHit = false;
  private int shootCounter = 0;
  private int backgroundIndex = 0;
  private int enemyIndex = 0;

  /* time between flips in the animation */
  private static final double FLIP_TIME = 0.125;
  private static final double WAIT_TIME = .5;

  /* time since last flip */
  private double timer = 0.0;
  private double shotTimer = 0.0;
  int groundlevel = 600;

  public Character( ) {
    /* load all the images */
    try {
      megaman1 = new Image[15];
      megaman2 = new Image[15];
      MMshot = new Shots[3];
      megaman1[0] = ImageIO.read(new File("MMStandRT.png"));
      megaman1[1] = ImageIO.read(new File("MMRun1RT.png"));
      megaman1[2] = ImageIO.read(new File("MMRun2RT.png"));
      megaman1[3] = ImageIO.read(new File("MMRun3RT.png"));
      megaman1[4] = ImageIO.read(new File("MMRun2RT.png"));
      megaman1[5] = ImageIO.read(new File("MMJumpR.png"));
      megaman1[6] = ImageIO.read(new File("MMShootRT.png"));
      megaman1[7] = ImageIO.read(new File("MMShootRun1RT.png"));
      megaman1[8] = ImageIO.read(new File("MMShootRun2RT.png"));
      megaman1[9] = ImageIO.read(new File("MMShootRun3RT.png"));
      megaman1[10] = ImageIO.read(new File("MMShootRun2RT.png"));
      megaman1[11] = ImageIO.read(new File("MMFallRT.png"));
      megaman1[12] = ImageIO.read(new File("MMDamaged1R.png"));
      megaman1[13] = ImageIO.read(new File("MMDamaged2.png"));
      megaman1[14] = ImageIO.read(new File("MMDead.png"));
      megaman2[0] = ImageIO.read(new File("MMStandLT.png"));
      megaman2[1] = ImageIO.read(new File("MMRun1LT.png"));
      megaman2[2] = ImageIO.read(new File("MMRun2LT.png"));
      megaman2[3] = ImageIO.read(new File("MMRun3LT.png"));
      megaman2[4] = ImageIO.read(new File("MMRun2LT.png"));
      megaman2[5] = ImageIO.read(new File("MMJumpL.png"));
      megaman2[6] = ImageIO.read(new File("MMShootLT.png"));
      megaman2[7] = ImageIO.read(new File("MMShootRun1LT.png"));
      megaman2[8] = ImageIO.read(new File("MMShootRun2LT.png"));
      megaman2[9] = ImageIO.read(new File("MMShootRun3LT.png"));
      megaman2[10] = ImageIO.read(new File("MMShootRun2LT.png"));
      megaman2[11] = ImageIO.read(new File("MMFallLT.png"));
      megaman2[12] = ImageIO.read(new File("MMDamaged1L.png"));
      megaman2[13] = ImageIO.read(new File("MMDamaged2.png"));
      megaman2[14] = ImageIO.read(new File("MMDead.png"));
      MMshot[0] = new Shots(0);
      MMshot[1] = new Shots(1);
      MMshot[2] = new Shots(2);
    } catch(Exception e) {
      megaman = null;
    }

    x = 100;
    y = 0;
    dx = dy = 0;
    current = 0;
  }

  public void draw(Graphics g) {
		
         if (right) {
                 megaman = megaman1;
         }
         else
                 megaman = megaman2;

          if (shootFrame == true && (current >= 0 && current < 6)) {
        		current = current + 6;
          }
          else if (shootFrame == true && current == 7) {
        	  current = 8;
          }
          else if (shootFrame == true && current == 8) {
        	  current = 9;
          }
          else if (shootFrame == true && current == 9) {
        	  current = 10;
          }
          else if (shootFrame == true && current == 10) {
        	  current = 7;
          }
          
          if (shootCounter > 0) {
        	  if (shootCounter >= 5) {
        		  shootFrame = false;
        		  shootCounter = 0;
        	  }
        	  else if (shootCounter > 0 && shootCounter < 5) {
        		  shootCounter++;
        	  }
          }
          
          backgroundIndex = Background.image.size();
          enemyIndex = Background.Enemy.size();
          MMRectangle.setBounds(((int)x+18), ((int)y+5), 40, 60);
          //g.drawRect((int)x+18, (int)y+5, 40, 60);
          
          under = false;
          wasHit = Shots.getMMhit();
          
          if (wasHit) {
        	  current = 12;
        	  if (HealthBar.index != 9) {
        		  HealthBar.index += 1;
        	  }
        	  if (HealthBar.index == 9) {
    			  current = 14;
    			  y = groundlevel + 20;
    			  Background.scrollingDone = true;
    			  horizontalMoving = false;
    			  verticalMoving = true;
    		  }
          }
          
          for (int i = 0; i < enemyIndex; i++) {
        	  TempRectangle = Background.Enemy.get(i).getRectangle();
        	  if (MMRectangle.intersects(TempRectangle)) {
        		  if (MMRectangle.getX() < TempRectangle.getX()) {
         			 x = x - 50;
        		  }
        		  else if (MMRectangle.getX() + MMRectangle.width > TempRectangle.getX() + TempRectangle.width) {
         			x = x + 50;
        		  }
        		  if (HealthBar.index != 9) {
        			  HealthBar.index += 1;
        		  }
        		  current = 12;
        		  
        		  if (HealthBar.index == 9) {
        			  current = 14;
        			  y = groundlevel + 20;
        			  Background.scrollingDone = true;
        			  horizontalMoving = false;
        			  verticalMoving = true;
        		  }
        	  }
          }
          
          for(int i = 0; i < backgroundIndex; i++) {
        	  TempRectangle = Background.image.get(i);
        	  above.setBounds(MMRectangle.x,MMRectangle.y-3000,40,2975);
        	  //g.drawRect(MMRectangle.x, MMRectangle.y-3000, 40, 2975);
        	  if(above.intersects(TempRectangle)){
        		  under=true;
        		  dy2= ((TempRectangle.getY() + TempRectangle.height)-MMRectangle.y)+2;
        	  }
        	  if (MMRectangle.intersects(TempRectangle)) {
        		if ((MMRectangle.getY()) < TempRectangle.getY()) {
              		y = TempRectangle.getY() - MMRectangle.height;
              		groundlevel = (int)y;
              		OnRectangle = TempRectangle;
              		LeftRectangle = new Rectangle(0, 0, 0, 0);
              		Background.intersects=false;
          		}
        		else if (MMRectangle.getX() < TempRectangle.getX()) {
        			 Background.intersects=true;
        			 x = TempRectangle.getX()-MMRectangle.width-11;
        			 LeftRectangle = TempRectangle;
        		}
        		else if (MMRectangle.getX() + MMRectangle.width > TempRectangle.getX() + TempRectangle.width) {
        			x = TempRectangle.getX() + TempRectangle.width;
        			//OnRectangle = TempRectangle;
        		}
        		else if (MMRectangle.getY()+MMRectangle.height > (TempRectangle.getY() + TempRectangle.height)) {
        			y = TempRectangle.getY() + TempRectangle.height;
        		}
        	  }
        	  else {
        		  if(MMRectangle.getX() + MMRectangle.width < OnRectangle.getX() || MMRectangle.getX()> (OnRectangle.getX()+OnRectangle.width))  
        			  groundlevel = 600;
        			  if(MMRectangle.getX()+MMRectangle.width> LeftRectangle.getX() && MMRectangle.getX()<LeftRectangle.getX()) {
        				  Background.intersects=true;
        			  } else{
        				  Background.intersects=false;
        			  }
        		  //groundlevel = 200;
        	  }
          }
          
    /* draw megaman on the screen */
          g.drawImage(megaman[current], (int)x, (int)y, 70, 70, null);
//        MMRectangle.setBounds((int)x+18, ((int)y+5), 40, 60);
//        g.setColor(Color.green);
//        g.drawRect((int)x+18, (int)y+5, 40, 60);
          MMshot[0].ShotCreator(g, 0);
          MMshot[1].ShotCreator(g, 0);
          MMshot[2].ShotCreator(g, 0);
          Shots.setMMhit(false);
  	}

  /* stop megaman */
  public void verticalStop( ) {
         dy = 0;
         //verticalMoving = false;
  }
  
  public void horizontalStop( ) {
         dx = 0;
         horizontalMoving = false;
  }

  /* left/up/right/down */
  public void left( ) {horizontalMoving = true; right = false; dx = -SPEED;}
  public void right( ) {horizontalMoving = true; right = true; dx = SPEED;}
  public void down( ) {verticalMoving = true; dy = ACCELERATION;}
  public void up( ) {
	  if (y == groundlevel && current != 14) {
		  if(!under) {
			  verticalMoving = true; 
			  dy = -3000;
		  } else {
			  verticalMoving = true;
			  y = y+dy2;
		  }
	  }
  }
  public void shoot( ) {
	  shotIndex++;
	  if (shotIndex < 3) {
		  shootFrame = true; shootCounter++;
		  if (right) {
			  MMshot[shotIndex].xStartRight(x + 70.0); 
			  MMshot[shotIndex].yStart(y + 20.0);
		  } else {
		  MMshot[shotIndex].xStartLeft(x); 
		  MMshot[shotIndex].yStart(y + 20.0);
	  	} 
	  }
	  else {
		  shotIndex = 3;
	  }
  }
  
  /* update him */
  public void update(double dt) {
	  
	  shotTimer = shotTimer + dt;
	  if (shotTimer > WAIT_TIME) {
		  shotTimer = 0;
		  if (shotIndex == 3)
			  shotIndex = -1;
	  }
        
    if (Background.scrollingDone == true) {
    	x += (dx * dt);
    }
    else {
    	x += ((dx) * dt)-3.7;
    }
    y += (dy * dt);

    if (Background.scrollingDone == true) {
    	if(x > 1070) x = 1070;
    	//if(x<470) x= 470;
    }
    else {
    	if(x > 1300) x = 1300;
    }

    /* update animation */
    if (y < groundlevel) {
        down( );	
    }
    else if (y >= groundlevel) {
            verticalMoving = false;
            y = groundlevel;
    }
    
    if(verticalMoving && current != 14) {
            current = 5;
    }
    else if(horizontalMoving) {
    	if (current == 12)
    		timer = timer + (dt / 5);
    	else
    		timer += dt;
      if(timer > FLIP_TIME) {
        timer = 0;
        if (verticalMoving == false) {
        current = current + 1;
        }
        if (current >= 5 && verticalMoving == false) {
                current = 1;
        }
      }
    }
    else {
            if (current != 14)
            	current = 0;
    }
    if(x + 70 < 0 || y> 480) {
    	HealthBar.index=9;
    	Background.scrollingDone = true;
    	horizontalMoving = false;
    }
  }
  
  public boolean getVerticalMoving() {
	  return verticalMoving;
  }
  
  public boolean getHorizontalMoving() {
	  return horizontalMoving;
  }
  
  public double getX() {
	  return x;
  }
  
  public static boolean getRight() {
	  return right;
  }
  
  public static Rectangle getMMRectangle( ) {
	  return MMRectangle;
  }
}
