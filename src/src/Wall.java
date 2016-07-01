package src;

public class Wall extends Sprite{
	
	public Wall(int x, int y){
		super(x,y);
		initWall();
	}
	
	public void initWall(){
		
		loadImage("/" + Frame.getTheme() + "_wall3.png");
		getImageDimensions();
		loadSoundName("explosion.wav");
		
	}
	
	public void move(){
		
		if(x<0)
			vis = false;
			
		x-=2;
		
	}

}
