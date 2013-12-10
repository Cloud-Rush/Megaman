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
  HealthBar healthbar;
  Enemies enemies;
  private long elapsed;
  //initialize and try opening the stage music.
  //private static Sound bgMusic = new Sound("stagemusic.aiff");
  //initialize and open blaster sounds.
  //private static Sound blaster = new Sound("megaman_blaster.aiff");
  //initialize and open ground contact.
  //private static Sound groundContact = new Sound("megaman_groundcontact.aiff");
  //initialize and open death sound.
  //private static Sound death = new Sound("megaman_death.aiff");
  //initialize and open enemy death.
  //private static Sound enemyDeath = new Sound("megaman_enemydeath.aiff");
  //initialize and open hit noise.
  //private static Sound hit = new Sound("megaman_getHit.aiff");
  
  private Image offscreen;
  private Graphics goff;

  public GameWorld( ) {
    elapsed = new Date( ).getTime( );
    healthbar = new HealthBar( );
    megaman = new Character( );
    background = new Background();
  }

  public void keyTyped(KeyEvent e) {
        //irrelevant
  }

  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_D ) {
    	megaman.right( );
    } else if (e.getKeyCode() == KeyEvent.VK_A ) {
    	megaman.left( );
    } else if (e.getKeyCode() == KeyEvent.VK_W ) {        
    			megaman.up( );
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
    	megaman.shoot( );
    	//play blaster SFX
    	//blaster.play();
    }
  }

  public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_D) {
                        megaman.horizontalStop( );
                } else if (e.getKeyCode() == KeyEvent.VK_A ) {
                        megaman.horizontalStop( );
                } else if (e.getKeyCode() == KeyEvent.VK_W ) {
             megaman.verticalStop( );
    }
  }
  
  
  /*
public void paint(Graphics g)
        {
                offscreen = createImage(getWidth(),getHeight());
                goff = offscreen.getGraphics();
                
                paintComponent(goff);
                g.drawImage(offscreen, 0, 0, this);
        
                
                
        }

*/

  public void paintComponent(Graphics g) {
        
         background.draw(g);
         megaman.draw(g);
         healthbar.draw(g);
       //plays the music, i'm not sure where this statement should go but i figure the music should play right after 
         //the game screen gets initialized. 
         //bgMusic.play();

    /* now update */
    long time_now = new Date( ).getTime( );
    double seconds = (time_now - elapsed) / 1000.0f;
    elapsed = time_now;
    megaman.update(seconds);
    background.right(seconds);

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
