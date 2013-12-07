import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.util.*;

public class Background extends JFrame {
	
	static ArrayList<Rectangle> image1 = new ArrayList<Rectangle>();
	static ArrayList<Rectangle> image2 = new ArrayList<Rectangle>();
	static ArrayList<Rectangle> image3 = new ArrayList<Rectangle>();
	static ArrayList<Rectangle> image4 = new ArrayList<Rectangle>();
	static ArrayList<Rectangle> image5 = new ArrayList<Rectangle>();
	static ArrayList<Rectangle> image6 = new ArrayList<Rectangle>();
	static ArrayList<Rectangle> image7 = new ArrayList<Rectangle>();
	static ArrayList<Rectangle> image8 = new ArrayList<Rectangle>();
	static ArrayList<Rectangle> image9 = new ArrayList<Rectangle>();
	static ArrayList<Rectangle> image10 = new ArrayList<Rectangle>();
	static ArrayList<Rectangle> image11 = new ArrayList<Rectangle>();
	static ArrayList<Rectangle> image12 = new ArrayList<Rectangle>();

	int screenWidth = 1320;
	int screenHeight = 480;
	//to scroll background
	int tilesx;
	int tilesy;
	//how much screen moves by
	int offsetx=0;
	int offsety =0;
	//x locations of onscreen and offscreen images
	int onscreenx;
	int offscreenx;
	int offscreenx2;
	int offscreenx3;
	int offscreenx4;
	int offscreenx5;
	int offscreenx6;
	int offscreenx7;
	int offscreenx8;
	int offscreenx9;
	int offscreenx10;
	int offscreenx11;
	static boolean scrollingDone = false;
        
	//background images
	Image bg[]= new Image[12];
	private Image offscreen;
	private Graphics goff;
	// width and height of image
        
	int height= screenHeight;
	int height10;
        
	int width0=screenWidth;
	int width1=screenWidth;
	int width2=screenWidth;
	int width3=screenWidth;
	int width4=screenWidth;
	int width5=screenWidth;
	int width6=screenWidth;
	int width7=screenWidth;
	int width8=screenWidth;
	int width9=screenWidth;
	int width10=screenWidth;
	int width11=screenWidth;
        
        
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
                                   
		height= bg[0].getHeight(this)*2;
		width0= bg[0].getWidth(this)*3;
		width1= bg[1].getWidth(this)*3;
		width2= bg[2].getWidth(this)*3;
		width3= bg[3].getWidth(this)*3;
		width4= bg[4].getWidth(this)*3;
		width5= bg[5].getWidth(this)*3;
		width6= bg[6].getWidth(this)*3;
		width7= bg[7].getWidth(this)*3;
		width8= bg[8].getWidth(this)*3;
		width9= bg[9].getWidth(this)*3;
		width10= bg[10].getWidth(this)*3;
		height10= bg[10].getHeight(this)*2;
		width11= bg[11].getWidth(this)*3;
                
		//screenWidth = bg[0].getWidth(this)*3;
		screenHeight = bg[0].getHeight(this)*2;
		//System.out.println(screenHeight);
		//System.out.println(screenWidth);
                                
		tilesx = (int)1;//(screenWidth/width);
		tilesy = (int)1;//(screenHeight/height);
		
		image1.add(new Rectangle(0, 365, 1320, 480));
		image1.add(new Rectangle(765, 300, 555, 480));
		image1.add(new Rectangle(957, 238, 363, 480));
		image1.add(new Rectangle(1150, 172, 170, 480));
                                                        
		//System.out.println("Tilesx"+tilesx);
		//System.out.println("Tilesy"+tilesy);            
	}
                
        
	/*
         public void paint(Graphics g)
                {
                        offscreen = createImage(getWidth(),getHeight());
                        goff = offscreen.getGraphics();
                        
                        draw(goff);
                        g.drawImage(offscreen, 0, 0, this);       
                }    
         */
        
	int i=0;        
	public void draw(Graphics g) {                
		//scrolls background
		for(int y =0; y<tilesy; y++) {
			for(int x=0; x<tilesx;x++) {                            
				onscreenx= x*width0-offsetx;
				offscreenx= width0-offsetx;
                        
				// System.out.println("onscreen "+onscreenx);
				//System.out.println("offscreen"+ offscreenx);
                                        
				//if(offsetx>994 && i<=9)
				//                i=i++;
				offscreenx2= width0+width1-offsetx;
				offscreenx3= width0+width1+width2-offsetx;
				offscreenx4= width0+width1+width2+width3-offsetx;;
				offscreenx5=width0+width1+width2+width3+ width4-offsetx;;
				offscreenx6= width0+width1+width2+width3+ width4+width5-offsetx;;
				offscreenx7= width0+width1+width2+width3+ width4+width5+width6-offsetx;;
				offscreenx8= width0+width1+width2+width3+ width4+width5+width6+width7-offsetx;;
				offscreenx9= width0+width1+width2+width3+ width4+width5+width6+width7+width8-offsetx;;
				offscreenx10= width0+width1+width2+width3+ width4+width5+width6+width7+width8+width9-offsetx;;
				offscreenx11= width0+width1+width2+width3+ width4+width5+width6+width7+width8+width9+width10-offsetx;;
                                        
				g.drawImage(bg[0], onscreenx, y*height-offsety,width0,height, null);
				image1.get(0).setBounds(0 - offsetx, 365, 1320, 480);
				image1.get(1).setBounds(765 - offsetx, 300, 555, 480);
				image1.get(2).setBounds(957 - offsetx, 238, 363, 480);
				image1.get(3).setBounds(1150 - offsetx, 172, 170, 480);
				g.drawRect(0 - offsetx, 365, 1320, 480);
				g.drawRect(765 - offsetx, 300, 1320, 480);
				g.drawRect(957 - offsetx, 238, 1320, 480);
				g.drawRect(1150 - offsetx, 172, 1320, 480);
				g.drawImage(bg[1], offscreenx, y*height-offsety,width1,screenHeight, null);
				g.drawImage(bg[2], offscreenx2, y*height-offsety,width2,screenHeight, null);
				g.drawImage(bg[3], offscreenx3, y*height-offsety,width3,screenHeight, null);
				g.drawImage(bg[4], offscreenx4, y*height-offsety,width4,screenHeight, null);
				g.drawImage(bg[5], offscreenx5, y*height-offsety,width5,screenHeight, null);
				g.drawImage(bg[6], offscreenx6, y*height-offsety,width6,screenHeight, null);
				g.drawImage(bg[7], offscreenx7, y*height-offsety,width7,screenHeight, null);
				g.drawImage(bg[8], offscreenx8, y*height-offsety,width8,screenHeight, null);
				g.drawImage(bg[9], offscreenx9, y*height-offsety,width9,screenHeight, null);
				g.drawImage(bg[10], offscreenx10, y*height-offsety,width10,screenHeight, null);
				g.drawImage(bg[11], offscreenx11, y*height-offsety,width11,screenHeight, null);
			}
		}
	}                
    
	public void left() {
		//if(offsetx>=0)
		//offsetx = (offsetx-5)%width;                              
	}                    
                        
	public void right() {
                                
		if(offsetx< width0+width1+width2+width3+ width4+width5+width6+width7+width8+width9+width10-(screenWidth-width10)) {
			offsetx = (offsetx+10);//%width;
		}
		else {
			scrollingDone = true;
		}
		//System.out.println("offsetx"+offsetx);                       
	}
                        
	public void jumpright() {
                                
		if(offsetx< width0+width1+width2+width3+ width4+width5+width6+width7+width8+width9+width10-(screenWidth-width10)) {
			offsetx = (offsetx+10);//%width;
		}
		else {
			scrollingDone = true;
		}
		//System.out.println("offsetx"+offsetx);                           
	}
                        
                        
	public void update(double dt) {
                                
	}
}
