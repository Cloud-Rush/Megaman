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
  private double x, y, dx, dy;

  /* now we have an array of images */
  private Image [] megaman;
  private Image [] megaman1;
  private Image [] megaman2;
  private Rectangle MMRectangle = new Rectangle();
  private Rectangle TempRectangle = new Rectangle();
  private Rectangle OnRectangle = new Rectangle();
  private Rectangle LeftRectangle = new Rectangle();
  private int current;
  private boolean right = true;
  private boolean horizontalMoving = false;
  private boolean verticalMoving = false;
  private boolean shooting = false;
  private int shootCounter = 0;
  private int index = 0;

  /* time between flips in the animation */
  private static final double FLIP_TIME = 0.125;

  /* time since last flip */
  private double timer = 0.0;
  int groundlevel = 600;
  
  private Shots MMshot = new Shots(0);

  public Character( ) {
    /* load all the images */
    try {
      megaman1 = new Image[12];
      megaman2 = new Image[12];
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

          if (shooting == true && (current >= 0 && current < 6)) {
        		current = current + 6;
          }
          else if (shooting == true && current == 7) {
        	  current = 8;
          }
          else if (shooting == true && current == 8) {
        	  current = 9;
          }
          else if (shooting == true && current == 9) {
        	  current = 10;
          }
          else if (shooting == true && current == 10) {
        	  current = 7;
          }
          
          if (shootCounter > 0) {
        	  if (shootCounter >= 5) {
        		  shooting = false;
        		  shootCounter = 0;
        	  }
        	  else if (shootCounter > 0 && shootCounter < 5) {
        		  shootCounter++;
        	  }
        	  else {
        		  shooting = true;
        	  }
          }
          
          index = Background.image.size();
          MMRectangle.setBounds(((int)x+18), ((int)y+5), 40, 60);
          g.drawRect((int)x+18, (int)y+5, 40, 60);
          
          for(int i = 0; i < index; i++) {
        	  TempRectangle = Background.image.get(i);
        	  if (MMRectangle.intersects(TempRectangle)) {
        		if (MMRectangle.getY() < TempRectangle.getY()) {
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
        		else if (MMRectangle.getY() < (TempRectangle.getY() + TempRectangle.height)) {
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
          MMRectangle.setBounds((int)x+18, ((int)y+5), 40, 60);
          g.setColor(Color.green);
          g.drawRect((int)x+18, (int)y+5, 40, 60);
          if (shooting == true) {
        	  MMshot.draw(g);
        	  MMshot.update();
          }
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
  //public void up( ) {if (y == groundlevel) {verticalMoving = true; dy = -3000;} }
  public void up( ) {verticalMoving = true; dy = -3000;}
  public void right( ) {horizontalMoving = true; right = true; dx = SPEED;}
  public void down( ) {verticalMoving = true; dy = ACCELERATION;}
  public void shoot( ) {shooting = true; shootCounter++; MMshot.xStart(x + 70.0); MMshot.yStart(y + 20.0);}
  
  /* update him */
  public void update(double dt) {
	  
	//if (shooting == true) {
		
	//}
        
    if (Background.scrollingDone == true) {
    	x += (dx * dt);
    }
    else {
    	x += (dx * dt) - 7;
    }
    y += (dy * dt);

    if (Background.scrollingDone == true) {
    	if(x > 1070) x = 1070;
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
    
    if(verticalMoving) {
            current = 5;
    }
    else if(horizontalMoving) {
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
            current = 0;
    }
    
    if(x + 70 < 0 || y> 480)
    	HealthBar.index=9;
    
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
}
