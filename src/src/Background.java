package src;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Background {

	private int pos_x;
	private Image image;
	
	public Background(){
		
		try{
			image = ImageIO.read(new File("fond.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
		
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
			
		 pos_x += 1 ;
		 
	 }
}
