import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class Background extends JFrame {
	int screenWidth = 1000;
	int screenHeight = 500;
	//to scroll background
	int tilesx;
	int tilesy;
	//how much screen moves by
	int i = 0;
	int offsetx=0;
	int offsety =0;
	//x locations of onscreen and offscreen images
	int onscreenx;
	int offscreenx;
	//background images
	Image bg[]= new Image[12];
	private Image offscreen;
	private Graphics goff;
	// width and height of image
	int width=screenWidth;
	int height= screenHeight;
	
	public Background() {
		try {			
			bg[0] = new ImageIcon("BG1.png").getImage();
			bg[1] = new ImageIcon("BG2.png").getImage();
			bg[2] = new ImageIcon("BG3.png").getImage();
			bg[3] = new ImageIcon("BG4.png").getImage();
			bg[4] = new ImageIcon("BG5.png").getImage();
			bg[5] = new ImageIcon("BG6.png").getImage();
			bg[6] = new ImageIcon("BG7.png").getImage();
			bg[7] = new ImageIcon("BG8.png").getImage();
			bg[8] = new ImageIcon("BG9.png").getImage();
			bg[9] = new ImageIcon("BG10.png").getImage();
			bg[10] = new ImageIcon("BG11.png").getImage();
			bg[11] = new ImageIcon("BG12.png").getImage();
		} catch(Exception e) {
			bg = null;
		}
                                
		tilesx = (int)1;//(screenWidth/width);
		tilesy = (int)1;//(screenHeight/height);
                                
		//System.out.println("Tilesx"+tilesx);
		//System.out.println("Tilesy"+tilesy);        
	}
                    
	public void paint(Graphics g) {
		offscreen = createImage(getWidth(),getHeight());
		goff = offscreen.getGraphics();
                        
		draw(goff);
		g.drawImage(offscreen, 0, 0, this);                
	}            

	public void draw(Graphics g) {
		//scrolls background
		for(int y =0; y<tilesy; y++)
		{
			for(int x=0; x<tilesx;x++)
			{
				onscreenx= x*width-offsetx;
				offscreenx= width-offsetx;
				
				if (offsetx >= 995 && i <= 9) {
					i = i + 2;
				}

				g.drawImage(bg[1], onscreenx, y*height-offsety,screenWidth,screenHeight, null);
				g.drawImage(bg[2], offscreenx, y*height-offsety,screenWidth,screenHeight, null);
			}
		}        
	}                
                        
//	public void left(){
//		if(offsetx>=0)
//			offsetx = (offsetx-5)%width;        	
//	}
                        
                        
	public void right(){
		offsetx = (offsetx+5)%width;                                  
	}
                        
	public void update(double dt) {
		
	}
}
