package src;

import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class Craft extends Sprite {
	
	private int dy;
	private ArrayList<Missile> missiles; //list of visible missiles
	
	private boolean pressed = false;

	private int missilestate;
	
	public Craft(int x, int y){
		super(x,y);
		initCraft();
	}
	
	private void initCraft(){
		
		missiles = new ArrayList<>();
		loadImage("/" + Frame.getTheme() + "_red.png");
		getImageDimensions();
		
		missilestate = 0;
		
	}
	/*
	 * increment of the y coordinate of the craft by the number given by keyPressed()
	 * the craft is limited by the upper and lower bounds of the screen (1 and 300)
	 */
	public void move(){
		
		if (pressed)
			y += dy;
		
		if(y<20) y=20;
		if(y>264) y = 264;
		pressed = false;
		
	}
	
	@SuppressWarnings("rawtypes")
	public ArrayList getMissiles(){
		
		return missiles;
		
	}
	
	
	public void keyPressed(KeyEvent e){
		
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_UP)
			dy = 0;
		
		if (key == KeyEvent.VK_DOWN)
			dy = 0;
		
	}
	
	public void keyReleased(KeyEvent e){
		
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_UP){
			dy = -61;
			pressed = true;}
		
		if (key == KeyEvent.VK_DOWN){
			dy = 61;
			pressed = true;}
		
		if (key == KeyEvent.VK_SPACE)
			fire();
		
	}
	/*
	 * add missiles to the ArrayList of missiles when the spacebar is released
	 * there can only be 10 missiles at the same time on the screen
	 * and they are launched only when the spacebar is released in order to not spamming missiles
	 */
	public void fire(){
		
		switch(missilestate)
		{
			case 0:
				break;
				
			case 1:
				if(missiles.size() < 5)
					missiles.add(new Missile(x + width, y));
				break;
				
			case 2:
				missiles.add(new Missile(x + width, y));
				break;
			
			case 3:
				if(y<264)
					missiles.add(new Missile(x + width, y + 64));
				missiles.add(new Missile(x + width, y));
				break;
				
			case 4:
				if(y<264)
					missiles.add(new Missile(x + width, y + 64));
				missiles.add(new Missile(x + width, y));
				missiles.add(new Missile(x + width, y - 64));
				break;
				
			case 5:
				missiles.add(new Missile(x + width, y - 128));
				missiles.add(new Missile(x + width, y - 64));
				missiles.add(new Missile(x + width, y));
				missiles.add(new Missile(x + width, y + 64));
				missiles.add(new Missile(x + width, y + 128));
				
		}
			
		
			//Sound.play("poke_shoot.wav");
		
		
	}
	
	public void upShoot(){
		this.missilestate++;
	}
	
	public void downShoot(){
		this.missilestate--;
		if(this.missilestate < 0)
			missilestate = 0;
	}
	
	public int getShoot(){
		return this.missilestate;
	}

}   
