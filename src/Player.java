/*
    Class that represents a player in the game
*/

import java.awt.*;
import java.awt.geom.*;

public class Player extends GameObject {

    //the image used for the player
    private Image image = ImageLoader.loadCompatibleImage("sprites/player.png");
    
    //keeps track of whether the player is moving to the left
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private int shootcountdown = 0;
    private int direction = 1;
    private int lives = 3;

	public Player(int x, int y, int w, int h) {
        super(x, y, w, h);
	}

	public void update() {
		if(shootcountdown > 0)
			shootcountdown--;
        //if we're moving to the left, decrease the player's boundary x by 1
		if(movingLeft)
            getBounds().x--;
        if(movingRight)
        	getBounds().x++;
        if(getBounds().x == 756)
        	getBounds().x--;
        if(getBounds().x == 0)
        	getBounds().x++;		
    }
	public void loseLife() {
		lives--;
	}
	public int getLives() {
		return lives;
	}
	public Laser shoot() {
		if(shootcountdown <= 0){
			shootcountdown = 80;
			return new Laser((int)getBounds().x + 18,(int)getBounds().y,6,15,1);
		}
		else {
			return null;
		}
	}
    
    //tell the player if they should be moving left or right
    public void setMovingLeft(boolean ml) {
        movingLeft = ml;
    }
    public void setMovingRight(boolean mm) {
    	movingRight = mm;
    }
    
    //draw the player with the player's image
	public void render(Graphics2D g) {

		g.drawImage(image,
                    (int)getBounds().x,
                    (int)getBounds().y,
                    (int)getBounds().width,
                    (int)getBounds().height,
                    null);

	}

}