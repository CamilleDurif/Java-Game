package src;

import java.util.ArrayList;
import java.util.Random;

public class Boss extends Sprite{
	
	private int life = 20;
	private ArrayList<BossMissile> missiles;
	
	private boolean shooted = false;
	
	private final int B_HEIGHT = 340;
	
	public Boss(int x, int y){
		
		super(x,y);
		initBoss();	
		
	}
	
	public void initBoss(){
		
		loadImage("/" + Frame.getTheme() + "_boss.png");
		this.getImageDimensions();
		missiles = new ArrayList<>();
		
	}
	
	public void move(){

		Random rand = new Random();
		Random rand2 = new Random();
		
		int posY = rand.nextInt(B_HEIGHT);
		
		if(rand2.nextInt(1000) >= 990){
			x-=5;
			shooted = false;
		}
		
		if(rand2.nextInt(1000) >= 980){
			if(posY < 76 && y == 81) y = 20;
			else if(posY < 132 && (y == 20 || y == 142)) y = 81;
			else if(posY < 188 && (y == 81 || y == 203)) y = 142;
			else if(posY < 244 && (y == 142 || y == 264)) y = 203;
			else if(posY < 300 && y == 203) y = 264;
			shooted = false;
		
		}
	}
	
	public void shoot(){
		
		if(!shooted)
			missiles.add(new BossMissile(x,y+20));
		shooted = true;
		
	}
	
	public ArrayList<BossMissile> getMissiles(){
		
		return missiles;
		
	}
	
	public int getLife(){		
		return life;
	}
	
	public void setLife(int life){
		this.life = life;
	}

}
