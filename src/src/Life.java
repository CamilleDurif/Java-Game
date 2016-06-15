package src;

public class Life extends Sprite{
	
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
		
		x-=5;
	}

}
