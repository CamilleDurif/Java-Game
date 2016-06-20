package src;

public class Alien extends Sprite{
	
	private static int speed;
	
	public Alien(int x, int y){
		super(x,y);	
		initAlien();
	}
	
	private void initAlien(){
		
		loadImage("/alien4.png");
		getImageDimensions();
		
	}
	/*
	 * increment of the x coordinate of aliens by -3 for going from right to left 
	 * when an alien touches the left bound of the screen, it is not visible
	 */
	public void move(){
		
		if(x<0)
			vis = false;
		
		x -= speed ;
	}

	public static int getSpeed() {
		return speed;
	}

	public static void setSpeed(int speed) {
		Alien.speed = speed;
	}

}
