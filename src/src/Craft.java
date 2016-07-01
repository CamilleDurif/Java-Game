package src;

import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class Craft extends Sprite {
	
	private int dy;
	private ArrayList<Missile> missiles; //list of visible missiles
	
	private boolean pressed = false;
	
	public Craft(int x, int y){
		super(x,y);
		initCraft();
	}
	
	private void initCraft(){
		
		missiles = new ArrayList<>();
		loadImage("/" + Frame.getTheme() + "_red.png");
		getImageDimensions();
		
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
		
		if (missiles.size() < 10){
			missiles.add(new Missile(x + width, y + height/4));
			//Sound.play("poke_shoot.wav");
		}
		
	}

}   
