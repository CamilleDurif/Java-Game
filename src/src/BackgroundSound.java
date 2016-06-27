package src;

public class BackgroundSound {
	
	private String menuSound;
	private String gameSound;
	private String gameoverSound;
	
	private Sound sound;
	
	public BackgroundSound(){
		
		menuSound = "on.wav";
		gameSound = "road.wav";
		gameoverSound = "gameover.wav";
		
	}
	
	public void play(String state){
		
		if(sound != null)
			sound.stop();
		
		if(state == "game"){
			sound = new Sound(gameSound);
			sound.loop();
		}
		else if(state == "gameover"){
			sound = new Sound(gameoverSound);
		}
		else{
			sound = new Sound(menuSound);
		}
	}
}
