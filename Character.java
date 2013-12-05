import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;

class Character {
  private static final double SPEED = 150.0;
  private static final double ACCELERATION = 250.0;
  private double x, y, dx, dy;

  /* now we have an array of images */
  private Image [] megaman;
  private Image [] megaman1;
  private Image [] megaman2;
  private int current;
  private boolean right = true;
  private boolean horizontalMoving = false;
  private boolean verticalMoving = false;

  /* time between flips in the animation */
  private static final double FLIP_TIME = 0.125;

  /* time since last flip */
  private double timer = 0.0;


  public Character( ) {
    /* load all the images */
    try {
      megaman1 = new Image[6];
      megaman2 = new Image[6];
      megaman1[0]  = ImageIO.read(new File("MMStandRT.png"));
      megaman1[1]  = ImageIO.read(new File("MMRun1RT.png"));
      megaman1[2]  = ImageIO.read(new File("MMRun2RT.png"));
      megaman1[3]  = ImageIO.read(new File("MMRun3RT.png"));
      megaman1[4]  = ImageIO.read(new File("MMRun2RT.png"));
      megaman1[5]  = ImageIO.read(new File("MMFallRT.png"));
      megaman2[0]  = ImageIO.read(new File("MMStandLT.png"));
      megaman2[1]  = ImageIO.read(new File("MMRun1LT.png"));
      megaman2[2]  = ImageIO.read(new File("MMRun2LT.png"));
      megaman2[3]  = ImageIO.read(new File("MMRun3LT.png"));
      megaman2[4]  = ImageIO.read(new File("MMRun2LT.png"));
      megaman2[5]  = ImageIO.read(new File("MMFallLT.png"));
    } catch(Exception e) {
      megaman = null;
    }

    x = y = 100;
    dx = dy = 0;
    current = 0;
  }

  public void draw(Graphics g) {
    /* add to the index if going left */
//    int add = 0;
//    if(!right) add = 5;
	  
	  if (right) {
		  megaman = megaman1;
	  }
	  else
		  megaman = megaman2;
    
	  /*
	   * if (shoot == true) {
	   * 	current = current + 6;
	   */
    /* draw megaman on the screen */
    g.drawImage(megaman[current], (int)x, (int)y, 70, 70, null);
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
  public void up( ) {if (y == 100) {verticalMoving = true; dy = -2000;} }
  public void right( ) {horizontalMoving = true; right = true; dx = SPEED;}
  public void down( ) {verticalMoving = true; dy = ACCELERATION;}
  
  /* update him */
  public void update(double dt) {
	
	x += (dx * dt);
    y += (dy * dt);

    if(y < 50) y = 50;
    if(x < 0) x = 0;
    if(x > 200) x = 200;

    /* update animation */
       	down( );
    if (y >= 100) {
    	verticalStop( );
    	y = 100;
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


class GameWorld extends JComponent implements KeyListener {
  private Character megaman;
  private long elapsed;
  private int counter = 0;

  public GameWorld( ) {
    elapsed = new Date( ).getTime( );
    megaman = new Character( );
  }

  public void keyTyped(KeyEvent e) {
	//irrelevant
  }

  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_D ) {
      megaman.right( );
      counter = 0;
    } else if (e.getKeyCode() == KeyEvent.VK_A ) {
      megaman.left( );
      counter = 0;
    } else if (e.getKeyCode() == KeyEvent.VK_W ) {	  
    	if (counter == 1) {
    		/*does nothing*/
    	}
    	else {
    		megaman.up( );
        	counter++;
    	}
   	}
  }

  public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D) {
			if (e.getKeyCode() != KeyEvent.VK_W) {
			megaman.horizontalStop( );
			}
		} else if (e.getKeyCode() == KeyEvent.VK_A ) {
			if (e.getKeyCode() != KeyEvent.VK_W) { 
			megaman.horizontalStop( );
			}
		} else if (e.getKeyCode() == KeyEvent.VK_W ) {
    	  megaman.verticalStop( );
    	  counter = 0; 
    }
  }

  public void paintComponent(Graphics g) {
    megaman.draw(g);

    /* now update */
    long time_now = new Date( ).getTime( );
    double seconds = (time_now - elapsed) / 1000.0f;
    elapsed = time_now;
    megaman.update(seconds);

    /* force an update */
    revalidate( );
    repaint( );
    /* sleep for 1/20th of a second */
    try {
      Thread.sleep(50);
    } catch(InterruptedException e) {
      Thread.currentThread( ).interrupt( );
    }
  }
}

public class TestCode {
  public static void main(String args[]) {
    // create and set up the window.
    JFrame frame = new JFrame("Graphics Example!");

    // make the program close when the window closes
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // add the GameWorld component
    GameWorld g = new GameWorld( );
    frame.add(g);
    frame.addKeyListener(g);

    // display the window.
    frame.setSize(500, 500);
    frame.setVisible(true);
  }    
}
