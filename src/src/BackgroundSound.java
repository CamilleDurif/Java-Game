package src;

public class BackgroundSound {
	
	private String menuSound;
	
	private Sound sound;
	
	public BackgroundSound(){
		
		menuSound = "on.wav";
		
	}
	
	public void play(String state){
		
		if(sound != null)
			sound.stop();
		
		if(state == "game"){
			sound = new Sound(Frame.getTheme() + "_road.wav");
			sound.loop();
		}
		else if(state == "gameover"){
			sound = new Sound(Frame.getTheme() + "_gameover.wav");
		}
		else if(state == "boss"){
			sound = new Sound(Frame.getTheme() + "_boss.wav");
		}
		else{
			sound = new Sound(menuSound);
		}
	}
	
		
}
