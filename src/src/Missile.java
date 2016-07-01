package src;

public class Missile extends Sprite {

    private final int BOARD_WIDTH = 480;
    private final int MISSILE_SPEED = 3;

    public Missile(int x, int y) {
        super(x,y);
        initMissile();
    }
    
    private void initMissile() {
        
        loadImage("/" + Frame.getTheme() + "_missile2.png");  
        getImageDimensions();
    }

/*
 * increment of the coordinate x of the missile by 3 (missile_speed)
 * when the missile touches the right bound of the screen, it is no more visible 
 */
    public void move() {
        
        x += MISSILE_SPEED;
        
        if (x > BOARD_WIDTH) {
            vis = false;
        }
    }
}