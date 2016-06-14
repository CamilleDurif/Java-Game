package src;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Sprite {
	
	//coordinates of the sprite
    protected int x;
    protected int y;
    //size of the sprite
    protected int width;
    protected int height;
    //state of life (visible or not)
    protected boolean vis;
    protected Image image;

    public Sprite(int x, int y) {

        this.x = x;
        this.y = y;
        vis = true;
    }

    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }
    
    protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }    

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return vis;
    }

    public void setVisible(Boolean visible) {
        vis = visible;
    }
    
    //returns the bounding rectangle of the sprite image
    public Rectangle getBounds(){
    	
    	return new Rectangle(x, y, width, height);
    	
    }
}