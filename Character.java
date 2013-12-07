import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;

class Character {
  private static final double SPEED = 450.0;
  private static final double ACCELERATION = 250.0;
  private static final double WIDTH = 70.0;
  private static final double HEIGHT = 70.0;
  private double x, y, dx, dy;

  /* now we have an array of images */
  private Image [] megaman;
  private Image [] megaman1;
  private Image [] megaman2;
  private Rectangle MMRectangle = new Rectangle();
  private Rectangle TempRectangle = new Rectangle();
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
  int groundlevel = 480;


  public Character( ) {
    /* load all the images */
    try {
      megaman1 = new Image[11];
      megaman2 = new Image[11];
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
      megaman1[10] = ImageIO.read(new File("MMFallRT.png"));
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
      megaman2[10] = ImageIO.read(new File("MMFallLT.png"));
    } catch(Exception e) {
      megaman = null;
    }

    x = 10;
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

          if (shooting == true) {
        	  if (verticalMoving == true)
                  current = current + 5;
        	  else
        		  current = current + 6;
                  System.out.println("Shooting frame happened.");
                  System.out.print("Current = ");
                  System.out.println(current);
                  System.out.print("Shoot counter is ");
                  System.out.println(shootCounter);
          }
          
          //&& MMRectangle.getY() + HEIGHT >= TempRectangle.getY()
          
          index = Background.image1.size();
          
          for(int i = 0; i < index; i++) {
        	  TempRectangle = Background.image1.get(i);
        	  if (MMRectangle.intersects(TempRectangle)) {
        		if (MMRectangle.getY() < TempRectangle.getY()) {
              		y = TempRectangle.getY() - MMRectangle.getHeight();
              		groundlevel = (int)y;
          		}
        		else if (MMRectangle.getX() < TempRectangle.getX()) {
        			x = TempRectangle.getX() - MMRectangle.getWidth();
        		}
        		else if (MMRectangle.getX() < TempRectangle.getX() + TempRectangle.width) {
        			x = TempRectangle.getX() + TempRectangle.width;
        		}
        	  }
        	  else {
      			groundlevel = 480;
        	  }
          }
          
    /* draw megaman on the screen */
          g.drawImage(megaman[current], (int)x, (int)y, 70, 70, null);
          MMRectangle.setBounds((int)x, (int)y, 60, 60);
          g.drawRect((int)x, (int)y, 60, 60);
          
          if (shootCounter > 0)
        	  if (shootCounter == 2) {
        		  shooting = false;
        		  shootCounter = 0;
        	  }
        	  else {
        		  shooting = false;
        	  }
  	}

  /* stop megaman */
  public void verticalStop( ) {
         dy = 0;
         verticalMoving = false;
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
  public void shoot( ) {shooting = true; shootCounter++;}
  
  /* update him */
  public void update(double dt) {
        
    x += (dx * dt);
    y += (dy * dt);

    if(y < 50) y = 50;
    if (Background.scrollingDone == false) {
    	if(x < 0) x = 0;
    	if(x > 1200) x = 1200;
    }
    else {
    	if(x < 465) x = 465;
    	if(x > 1070) x = 1070;
    }

    /* update animation */
               down( );
    if (y >= groundlevel) {
            verticalStop( );
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
  }
}
