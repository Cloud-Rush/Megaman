import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;

public class HealthBar extends JFrame {
        
        int height = 0;
        
        private Image [] healthbar;
        static int index = 0;
        
        public HealthBar( ) {
                try {
                        healthbar = new Image[10];
                        healthbar[0] = ImageIO.read(new File("Health1.png"));
                        healthbar[1] = ImageIO.read(new File("Health2.png"));
                        healthbar[2] = ImageIO.read(new File("Health3.png"));
                        healthbar[3] = ImageIO.read(new File("Health4.png"));
                        healthbar[4] = ImageIO.read(new File("Health5.png"));
                        healthbar[5] = ImageIO.read(new File("Health6.png"));
                        healthbar[6] = ImageIO.read(new File("Health7.png"));
                        healthbar[7] = ImageIO.read(new File("Health8.png"));
                        healthbar[8] = ImageIO.read(new File("Health9.png"));
                        healthbar[9] = ImageIO.read(new File("Health10.png"));
                } catch (Exception e) {
                        healthbar = null;
                }
        }
        
        //height = healthbar[0].getHeight(this)/2;
        
        public void draw(Graphics g) {
        	g.drawImage(healthbar[index], 10, 10, 50, 200, null);
        }

        public void update(double dt) {
        
        }
}
