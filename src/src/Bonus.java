package src;

public class Bonus extends Sprite{
	
	private static int speed;
	
	private int type;
	
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
		case 3:
			imagename = "/" + Frame.getTheme() + "_bonus3.png";
			break;
		default:
			System.out.print("Erreur bonus non trouvé");
		}
		
		loadImage(imagename);
		getImageDimensions();
		
		type = bonustype;
		
	}
	
	public void move(){
		
		if(x+width<0)
			vis = false;
		
		x-=speed;
		
	}
	
	public static int getSpeed() {
		return speed;
	}

	public static void setSpeed(int speed) {
		Bonus.speed = speed;
	}
	
	public int getBonusType(){
		return this.type;
	}
}
