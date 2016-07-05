package src;

public class BossMissile extends Sprite{
	
	private final int MISSILE_SPEED = 3;

	public BossMissile(int x, int y) {
	    super(x,y);
	    initMissile();
	}
	    
	private void initMissile() {
	        
	    loadImage("/" + Frame.getTheme() + "_bossmissile.png");  
	    getImageDimensions();
	}

	/*
	 * increment of the coordinate x of the missile by 3 (missile_speed)
	 * when the missile touches the right bound of the screen, it is no more visible 
	 */
	 public void move() {
	        
	    x -= MISSILE_SPEED;
	        
	    if (x < 0) {
	            vis = false;
	    }
	}

}
