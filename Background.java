import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.util.*;

public class Background extends JFrame {
	
	static ArrayList<Rectangle> image = new ArrayList<Rectangle>();
	static ArrayList<Enemies> Enemy = new ArrayList<Enemies>();

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
	static boolean intersects = false;
	
	  private static final double FLIP_TIME = 0.125;
	  private double timer = 0.0;
        
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
		
		offscreenx= width0;
		offscreenx2= width0+width1;
		offscreenx3= width0+width1+width2;
		offscreenx4= width0+width1+width2+width3;
		offscreenx5=width0+width1+width2+width3+ width4;
		offscreenx6= width0+width1+width2+width3+ width4+width5;
		offscreenx7= width0+width1+width2+width3+ width4+width5+width6;
		offscreenx8= width0+width1+width2+width3+ width4+width5+width6+width7;
		offscreenx9= width0+width1+width2+width3+ width4+width5+width6+width7+width8;
		offscreenx10= width0+width1+width2+width3+ width4+width5+width6+width7+width8+width9;
		offscreenx11= width0+width1+width2+width3+ width4+width5+width6+width7+width8+width9+width10;
		
		//Collision Detection Rectangles for BG Image 1
		image.add(new Rectangle(0, 365, 2495, 115));
		image.add(new Rectangle(765, 300, 769, 180));
		image.add(new Rectangle(957, 238, 480, 242));
		image.add(new Rectangle(1150, 172, 170, 308));
		Enemy.add(new Enemies(2, 1200, 100));
		Enemy.add(new Enemies(2, 1200, 300));
		Enemy.add(new Enemies(4, 1245, 115));
		
		//Collision Detection Rectangles for BG Image 2
		image.add(new Rectangle(offscreenx + 982, 300, 190, 308));
		image.add(new Rectangle(offscreenx + 1080, 242, 107, 320));
		Enemy.add(new Enemies(3, offscreenx + 500, 340));
		
		//Collision Detection Rectangles for BG Image 3
		image.add(new Rectangle(offscreenx2 + 125, 376, 285, 115));
		image.add(new Rectangle(offscreenx2 + 220, 310, 185, 308));
		image.add(new Rectangle(offscreenx2 + 317, 242, 98, 325));
		image.add(new Rectangle(offscreenx2 + 155, 376, 285, 115));
		image.add(new Rectangle(offscreenx2 + 270, 310, 185, 308));
		image.add(new Rectangle(offscreenx2 + 367, 242, 98, 325));
		image.add(new Rectangle(offscreenx2 + 225, 376, 285, 115));
		image.add(new Rectangle(offscreenx2 + 320, 310, 185, 308));
		image.add(new Rectangle(offscreenx2 + 417, 242, 98, 325));
		image.add(new Rectangle(offscreenx2 + 1278, 376, 280, 115));
		image.add(new Rectangle(offscreenx2 + 1470, 310, 180, 308));
		image.add(new Rectangle(offscreenx2 + 1568, 242, 100, 325));
		
		//Collision Detection Rectangles for BG Image 4
		image.add(new Rectangle(offscreenx3, 0, 0, 0));
		image.add(new Rectangle(offscreenx3, 0, 0, 0));
		image.add(new Rectangle(offscreenx3, 0, 0, 0));
		image.add(new Rectangle(offscreenx3, 0, 0, 0));
		image.add(new Rectangle(offscreenx3, 0, 0, 0));
		image.add(new Rectangle(offscreenx3, 0, 0, 0));
		image.add(new Rectangle(offscreenx3, 0, 0, 0));
		image.add(new Rectangle(offscreenx3, 0, 0, 0));
		image.add(new Rectangle(offscreenx3, 0, 0, 0));
		image.add(new Rectangle(offscreenx3, 0, 0, 0));
		image.add(new Rectangle(offscreenx3, 0, 0, 0));
		image.add(new Rectangle(offscreenx3, 0, 0, 0));

		//Collision Detection Rectangles for BG Image 5
		image.add(new Rectangle(offscreenx4 + 62, 376, 1655, 104));
		image.add(new Rectangle(offscreenx4 + 1532, 310, 175, 170));
		image.add(new Rectangle(offscreenx4 + 1628, 242, 98, 238));
		Enemy.add(new Enemies(1, offscreenx4 + 500, 340));
		
		//Collision Detection Rectangles for BG Image 6
		image.add(new Rectangle(offscreenx5 + 120, 376, 285, 115));
		image.add(new Rectangle(offscreenx5 + 215, 310, 185, 308));
		image.add(new Rectangle(offscreenx5 + 312, 242, 98, 325));
		image.add(new Rectangle(offscreenx5 + 150, 376, 285, 115));
		image.add(new Rectangle(offscreenx5 + 265, 310, 185, 308));
		image.add(new Rectangle(offscreenx5 + 362, 242, 98, 325));
		image.add(new Rectangle(offscreenx5 + 220, 376, 285, 115));
		image.add(new Rectangle(offscreenx5 + 315, 310, 185, 308));
		image.add(new Rectangle(offscreenx5 + 412, 242, 98, 325));
		image.add(new Rectangle(offscreenx5 + 1273, 376, 780, 115));
		Enemy.add(new Enemies(3, offscreenx5 + 500, 340));
		
		//Collision Detection Rectangles for BG Image 7
		image.add(new Rectangle(offscreenx3, 0, 0, 0));
		image.add(new Rectangle(offscreenx3, 0, 0, 0));
		image.add(new Rectangle(offscreenx3, 0, 0, 0));
		image.add(new Rectangle(offscreenx3, 0, 0, 0));
		image.add(new Rectangle(offscreenx3, 0, 0, 0));
		image.add(new Rectangle(offscreenx3, 0, 0, 0));
		image.add(new Rectangle(offscreenx3, 0, 0, 0));
		Enemy.add(new Enemies(2, offscreenx3 + 500, 340));
		
		image.add(new Rectangle(0, 365, 1320, 480));
		image.add(new Rectangle(765, 300, 555, 480));
		image.add(new Rectangle(957, 238, 363, 480));
		image.add(new Rectangle(1150, 172, 170, 480));
		image.add(new Rectangle(0, 365, 1320, 480));
		image.add(new Rectangle(765, 300, 555, 480));
		image.add(new Rectangle(957, 238, 363, 480));
		image.add(new Rectangle(1150, 172, 170, 480));
		image.add(new Rectangle(957, 238, 363, 480));
		image.add(new Rectangle(1150, 172, 170, 480));
                                                        
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
				
				g.setColor(Color.red);
				//Collision Detection Rectangles for BG Image 1
				image.get(0).setBounds(0 - offsetx, 365, 2495, 115);
				image.get(1).setBounds(765 - offsetx, 300, 769, 180);
				image.get(2).setBounds(957 - offsetx, 238, 480, 242);
				image.get(3).setBounds(1150 - offsetx, 172, 190, 308);
				Enemy.get(0).draw(g);
				Enemy.get(1).draw(g);
				Enemy.get(2).draw(g);

				//Collision Detection Rectangles for BG Image 2
				image.get(4).setBounds(offscreenx + 982, 300, 190, 308);
				image.get(5).setBounds(offscreenx + 1080, 242, 107, 320);
				Enemy.get(3).draw(g);
				
				//Collision Detection Rectangles for BG Image 3
				image.get(6).setBounds(offscreenx2 + 125, 376, 285, 115);
				image.get(7).setBounds(offscreenx2 + 210, 315, 185, 308);
				image.get(8).setBounds(offscreenx2 + 317, 242, 98, 325);
				image.get(9).setBounds(offscreenx2 + 510, 376, 280, 115);
				image.get(10).setBounds(offscreenx2 + 605, 310, 180, 308);
				image.get(11).setBounds(offscreenx2 + 702, 242, 97, 325);
				image.get(12).setBounds(offscreenx2 + 893, 376, 280, 115);
				image.get(13).setBounds(offscreenx2 + 990, 310, 180, 308);
				image.get(14).setBounds(offscreenx2 + 1088, 242, 97, 325);
				image.get(15).setBounds(offscreenx2 + 1278, 376, 280, 115);
				image.get(16).setBounds(offscreenx2 + 1470, 310, 180, 308);
				image.get(17).setBounds(offscreenx2 + 1568, 242, 100, 325);
				
				//Collision Detection Rectangles for BG Image 4
				image.get(18).setBounds(offscreenx3, 374, 1100, 104);
				image.get(19).setBounds(offscreenx3 + 170, 240, 285, 33);
				image.get(20).setBounds(offscreenx3 + 365, 207, 93, 66);
				image.get(21).setBounds(offscreenx3 + 599, 141, 97, 33);
				image.get(22).setBounds(offscreenx3 + 745, 306, 49, 33);
				image.get(23).setBounds(offscreenx3 + 794, 207, 49, 33);
				image.get(24).setBounds(offscreenx3 + 840, 108, 292, 33);
				image.get(25).setBounds(offscreenx3 + 986, 174, 146, 33);
				image.get(26).setBounds(offscreenx3 + 1035, 207, 97, 33);
				image.get(27).setBounds(offscreenx3 + 1035, 339, 97, 33);
				image.get(28).setBounds(offscreenx3 + 1082, 240, 49, 33);
				image.get(29).setBounds(offscreenx3 + 1130, 108, 96, 350);
				
				//Collision Detection Rectangles for BG Image 5
				image.get(30).setBounds(offscreenx4 + 62, 376, 1655, 104);
				image.get(31).setBounds(offscreenx4 + 1532, 310, 175, 170);
				image.get(32).setBounds(offscreenx4 + 1628, 242, 98, 238);
				Enemy.get(4).draw(g);
				//Collision Detection Rectangles for BG Image 6
				image.get(33).setBounds(offscreenx5 + 120, 376, 285, 115);
				image.get(34).setBounds(offscreenx5 + 205, 315, 185, 308);
				image.get(35).setBounds(offscreenx5 + 312, 242, 98, 325);
				image.get(36).setBounds(offscreenx5 + 505, 376, 280, 115);
				image.get(37).setBounds(offscreenx5 + 600, 310, 180, 308);
				image.get(38).setBounds(offscreenx5 + 697, 242, 97, 325);
				image.get(39).setBounds(offscreenx5 + 888, 376, 280, 115);
				image.get(40).setBounds(offscreenx5 + 985, 310, 180, 308);
				image.get(41).setBounds(offscreenx5 + 1083, 242, 97, 325);
				image.get(42).setBounds(offscreenx5 + 1273, 376, 780, 115);
				Enemy.get(5).draw(g);
			
				//Collision Detection Rectangles for BG Image 7
				image.get(43).setBounds(offscreenx6 + 620, 115, 97, 300);
				image.get(44).setBounds(offscreenx6 + 236, 312, 49, 33);
				image.get(45).setBounds(offscreenx6 + 284, 215, 49, 33);
				image.get(46).setBounds(offscreenx6 + 332, 115, 386, 33);
				image.get(47).setBounds(offscreenx6 + 92, 148, 94, 33);
				image.get(48).setBounds(offscreenx6 - 122, 377, width6, 200);
				Enemy.get(6).draw(g);
				
				//Collision Detection Rectangles for BG Image 8
				image.get(49).setBounds(offscreenx7+572, 210, 49, 33);
				image.get(50).setBounds(offscreenx7+332, 308, 49, 33);
				image.get(51).setBounds(offscreenx7+379, 210, 49, 33);
				image.get(52).setBounds(offscreenx7+40, 142, 49, 33);
				image.get(53).setBounds(offscreenx7+138, 372, 100, 200);
				image.get(54).setBounds(offscreenx7+765, 372, width7, 200);
		
				//Collision Detection Rectangles for BG Image 9
				image.get(55).setBounds(offscreenx8, 372, width8, 200);
			
				//Collision Detection Rectangles for BG Image 10
				image.get(56).setBounds(offscreenx9-80, 308, 500, 200);
				
				//Collision Detection Rectangles for BG Image 11
				image.get(57).setBounds(offscreenx10, 308, 918, 200);
				
				//Collision Detection Rectangles for BG Image 12
				image.get(58).setBounds(offscreenx11, 402, 1300, 50);
				
				//Collision Detection Rectangles for BG Image 1
				g.drawRect(0 - offsetx, 365, 2495, 115);
				g.drawRect(765 - offsetx, 300, 769, 180);
				g.drawRect(957 - offsetx, 238, 480, 242);
				g.drawRect(1150 - offsetx, 172, 190, 308);
				
				//Collision Detection Rectangles for BG Image 2
				g.drawRect(offscreenx + 982, 300, 190, 308);
				g.drawRect(offscreenx + 1080, 242, 107, 320);
				
				//Collision Detection Rectangles for BG Image 3
				g.drawRect(offscreenx2 + 125, 376, 280, 115);
				g.drawRect(offscreenx2 + 220, 310, 180, 308);
				g.drawRect(offscreenx2 + 317, 242, 98, 325);
				g.drawRect(offscreenx2 + 510, 376, 280, 115);
				g.drawRect(offscreenx2 + 605, 310, 180, 308);
				g.drawRect(offscreenx2 + 702, 242, 97, 325);
				g.drawRect(offscreenx2 + 893, 376, 280, 115);
				g.drawRect(offscreenx2 + 990, 310, 180, 308);
				g.drawRect(offscreenx2 + 1088, 242, 97, 325);
				g.drawRect(offscreenx2 + 1278, 376, 280, 115);
				g.drawRect(offscreenx2 + 1470, 310, 180, 308);
				g.drawRect(offscreenx2 + 1568, 242, 100, 325);
				
				//Collision Detection Rectangles for BG Image 4
				g.drawRect(offscreenx3, 374, 1100, 104);
				g.drawRect(offscreenx3 + 170, 240, 285, 33);
				g.drawRect(offscreenx3 + 365, 207, 93, 66);
				g.drawRect(offscreenx3 + 599, 141, 97, 33);
				g.drawRect(offscreenx3 + 745, 306, 49, 33);
				g.drawRect(offscreenx3 + 794, 207, 49, 33);
				g.drawRect(offscreenx3 + 1035, 207, 97, 33);
				g.drawRect(offscreenx3 + 1082, 240, 49, 33);
				g.drawRect(offscreenx3 + 1035, 339, 97, 33);
				g.drawRect(offscreenx3 + 986, 174, 146, 33);
				g.drawRect(offscreenx3 + 840, 108, 292, 33);
				g.drawRect(offscreenx3 + 1130, 108, 96, 350);
				
				//Collision Detection Rectangles for BG Image 5
				g.drawRect(offscreenx4 + 62, 376, 1655, 104);
				g.drawRect(offscreenx4 + 1532, 310, 175, 170);
				g.drawRect(offscreenx4 + 1628, 242, 98, 238);
				
				//Collision Detection Rectangles for BG Image 6
				g.drawRect(offscreenx5 + 120, 376, 280, 115);
				g.drawRect(offscreenx5 + 215, 310, 180, 308);
				g.drawRect(offscreenx5 + 312, 242, 98, 325);
				g.drawRect(offscreenx5 + 505, 376, 280, 115);
				g.drawRect(offscreenx5 + 600, 310, 180, 308);
				g.drawRect(offscreenx5 + 697, 242, 97, 325);
				g.drawRect(offscreenx5 + 888, 376, 280, 115);
				g.drawRect(offscreenx5 + 985, 310, 180, 308);
				g.drawRect(offscreenx5 + 1083, 242, 97, 325);
				g.drawRect(offscreenx5 + 1273, 376, 780, 115);
				
				//Collision Detection Rectangles for BG Image 7
				g.drawRect(offscreenx6 + 620, 115, 97, 300);
				g.drawRect(offscreenx6 + 236, 312, 49, 33);
				g.drawRect(offscreenx6 + 284, 215, 49, 33);
				g.drawRect(offscreenx6 + 332, 115, 386, 33);
				g.drawRect(offscreenx6 + 92, 148, 94, 33);
				g.drawRect(offscreenx6 - 122, 377, width6, 200);
				
				//Collision Detection Rectangles for BG Image 8
				g.drawRect(offscreenx7+572, 210, 49, 33);
				g.drawRect(offscreenx7+332, 308, 49, 33);
				g.drawRect(offscreenx7+379, 210, 49, 33);
				g.drawRect(offscreenx7+40, 142, 49, 33);
				g.drawRect(offscreenx7+138, 372, 100, 200);
				g.drawRect(offscreenx7+765, 372, width7, 200);

				//Collision Detection Rectangles for BG Image 9
				g.drawRect(offscreenx8, 372, width8, 200);
				
				//Collision Detection Rectangles for BG Image 10
				g.drawRect(offscreenx9-80, 308, 500, 200);
				
				//Collision Detection Rectangles for BG Image 11
				g.drawRect(offscreenx10, 308, 918, 200);
				
				//Collision Detection Rectangles for BG Image 12
				g.drawRect(offscreenx11, 402, 1300, 50);
			}
		}
	}                
    
	public void left() {
		//if(offsetx>=0)
		//offsetx = (offsetx-5)%width;  
		if(offsetx>=0)
			offsetx = (offsetx-10);                              
	}                    
                        
	public void right(double seconds) {
		
		timer += seconds;
		if(timer > FLIP_TIME) {
			timer = 0;
		}
		else {
			if(offsetx< width0+width1+width2+width3+ width4+width5+width6+width7+width8+width9+width10-(screenWidth-width10)) {
					offsetx = (offsetx+10);//%width;
					Enemy.get(0).scroll(15);
					Enemy.get(1).scroll(15);
					Enemy.get(2).scroll(10);
					Enemy.get(3).scroll(10);
					Enemy.get(4).scroll(10);
					Enemy.get(5).scroll(10);
					Enemy.get(6).scroll(10);
					
			else {
				scrollingDone = true;
			}
		}
		
		Enemy.get(0).update(seconds);
		Enemy.get(1).update(seconds);
		Enemy.get(2).update(seconds);
		Enemy.get(3).update(seconds);
		Enemy.get(4).update(seconds);
		Enemy.get(5).update(seconds);
		Enemy.get(6).update(seconds);
		
		//System.out.println("offsetx"+offsetx);                       
	}
                        
	public void jumpright() {
                                
//		if(offsetx< width0+width1+width2+width3+ width4+width5+width6+width7+width8+width9+width10-(screenWidth-width10)) {
//			offsetx = (offsetx+10);//%width;
//		}
//		else {
//			scrollingDone = true;
//		}
		//System.out.println("offsetx"+offsetx);                           
	}
                        
                        
	public void update(double dt) {
		
	}
}
