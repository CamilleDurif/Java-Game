package src;

import java.awt.Image;

import javax.swing.ImageIcon;


public class Background {

	private int pos_x;
	private Image image;
	private int speed;
	
	public Background(){
		
		ImageIcon ii = new ImageIcon(getClass().getResource("/fond4.png"));
        image = ii.getImage();
        
        speed = 1;
		
		pos_x = this.getWidth() - 500;
		
	}
	
	public int getWidth(){
		
		return image.getWidth(null);
		
	}
	
	 public Image getImage() {
	        return image;
	    }
	 
	 public int getPosX(){
		 
		 return pos_x; 
		 
	 }
	 
	 public void move(){
		 
		 if(pos_x>image.getWidth(null))
			 pos_x = 0;
			
		pos_x += speed ;
		 
	 }
	 
	 public int getSpeed(){
		 return speed;
	 }
	 
	 public void setSpeed(int speed){
		this.speed = speed;
	 }
}
