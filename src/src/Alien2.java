package src;

public class Alien2 extends Sprite{
	
	double i = 0;
	int count = 2;
	
	public Alien2(int x, int y){
		super(x,y);	
		initAlien2();
	}
	
	private void initAlien2(){
		
		loadImage("/alien5.png");
		getImageDimensions();
		
	}
	/*
	 * increment of the x coordinate of aliens by -3 for going from right to left 
	 * when an alien touches the left bound of the screen, it is not visible
	 */
	public void move(){
		
		if(x<0){
			vis = false;
			i = 0;
		}
		x -= 3 ;
		
		int dy = (int)(Math.sin(Math.toRadians(i))*20) /4;
		y+=dy;
		i = i + 3;
	}
}
