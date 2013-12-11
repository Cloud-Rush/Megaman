import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.Date;
import java.io.*;

import javax.imageio.*;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;


class GameWorld extends JComponent implements KeyListener {
  private Character megaman;
  private Background background;
  private HealthBar healthbar;
  private Enemies enemies;
  private long elapsed;
  private Image megamanDead;
  //initialize and try opening the stage music.
  static Sound bgMusic = new Sound("stagemusic.aiff");
  //initialize and open blaster sounds.
  static Sound blaster = new Sound("megaman_blaster.aiff");
  //initialize and open ground contact.
  static Sound groundContact = new Sound("megaman_groundcontact.aiff");
  //initialize and open death sound.
  static Sound death = new Sound("megaman_death.aiff");
  //initialize and open enemy death.
  static Sound enemyDeath = new Sound("megaman_enemydeath.aiff");
  //initialize and open hit noise.
  static Sound hit = new Sound("megaman_getHit.aiff");
  //initialize and open boss music
  static Sound bossMusic = new Sound("bossfight.aiff");
  
  private Image offscreen;
  private Graphics goff;

  public GameWorld( ) {
    elapsed = new Date( ).getTime( );
    healthbar = new HealthBar( );
    megaman = new Character( );
    background = new Background();
    
    try {
    	megamanDead = ImageIO.read(new File("MMDeadR.png"));
    } catch (Exception e) {
    	megamanDead = null;
    }
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

  public void paintComponent(Graphics g) {
	  if (healthbar.index != 9) {
		  background.draw(g);
		  megaman.draw(g);
		  healthbar.draw(g);

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
	  else {
		  death.play();
		  try {
		  bgMusic.stop( );
		  bossMusic.stop( );
		  } catch (Exception e) {
			  //unused
		  }
		  Font f = new Font("Arial", Font.BOLD, 150);
		  g.setFont(f);
		  g.drawString("Game Over", 200, 200);
		  g.drawImage(megamanDead, 450, 250, 300, 300, null);
		  Font q = new Font("Arial", Font.BOLD, 25);
		  g.setFont(q);
		  g.drawString("Your final score was:", 500, 50);
		  g.drawString(String.valueOf(Background.score), 753, 52);
		  if (Background.score > Integer.parseInt(MegamanGame.highscore))
		  try {
				PrintWriter out = new PrintWriter("highscore.txt");
				String t = String.valueOf(Background.score);
				out.println(t);
				out.close();
				} catch (Exception e) {
					//unused
				}
	  }
  }
}
