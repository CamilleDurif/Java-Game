package src;

public class Bonus extends Sprite{
	
	private static int speed;
	
	public Bonus(int x, int y, int i){
		
		super(x,y);
		initBonus(i);
		
	}
	
	public void initBonus(int bonustype){
		
		String imagename = null;
		
		switch(bonustype)
		{
		case 1:
			imagename = "/" + Frame.getTheme() + "_bonus1.png";
			break;
		case 2:
			imagename = "/" + Frame.getTheme() + "_bonus2.png";
			break;
		default:
			System.out.print("Erreur bonus non trouvé");
		}
		
		loadImage(imagename);
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
		Bonus.speed = speed;
	}
}
