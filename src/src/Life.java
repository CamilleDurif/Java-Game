package src;

public class Life extends Sprite{
	
	private static int speed;
	
	public Life(int x, int y){
		super(x,y);
		initLife();
	}
	
	public void initLife(){
		loadImage("/life.png");
		getImageDimensions();
	
	}
	
	public void move(){
		
		if(x<0)
			vis = false;
		
		x-=speed;
	}

	public static int getSpeed() {
		return speed;
	}

	public static void setSpeed(int speed) {
		Life.speed = speed;
	}

}
