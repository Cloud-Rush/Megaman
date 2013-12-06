import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JComponent;


class GameWorld extends JComponent implements KeyListener {
  private Character megaman;
  Background background;
  private long elapsed;
  private int counter = 0;
   
  
  private Image offscreen;
  private Graphics goff;
	
	

  public GameWorld( ) {
    elapsed = new Date( ).getTime( );
    megaman = new Character( );
    background = new Background();
    
  }

  public void keyTyped(KeyEvent e) {
	//irrelevant
  }

  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_D ) {
      megaman.right( );
      background.right();
      counter = 0;
    } else if (e.getKeyCode() == KeyEvent.VK_A ) {
    	megaman.left( );
    	background.left();
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
  
  
  
  
  public void paint(Graphics g)
	{
		offscreen = createImage(getWidth(),getHeight());
		goff = offscreen.getGraphics();
		
		paintComponent(goff);
		g.drawImage(offscreen, 0, 0, this);
	
		
		
	}

  

  public void paintComponent(Graphics g) {
	  
	 background.draw(g); 
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

