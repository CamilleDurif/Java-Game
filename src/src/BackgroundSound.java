package src;

public class BackgroundSound {
	
	private String menuSound;
	//private String gameSound;
	//private String gameoverSound;
	
	private Sound sound;
	
	public BackgroundSound(){
		
		menuSound = "on.wav";
		//gameSound = Frame.getTheme() + "_road.wav";
		//gameoverSound = Frame.getTheme() + "_gameover.wav";
		
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
		else{
			sound = new Sound(menuSound);
		}
	}
}
