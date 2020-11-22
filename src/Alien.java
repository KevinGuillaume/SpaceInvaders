/*
    This class represents a basic alien in the game
*/

import java.awt.*;
import java.awt.geom.*;

public class Alien extends GameObject {

    private Image image = ImageLoader.loadCompatibleImage("sprites/alienA1.png");
    private int updateCounter = 0;
    private boolean max = false;
    private double value = 0.0;
    private int shootcd = 240;

	public Alien(int x, int y, int w, int h) {
        super(x, y, w, h);
        
	}

	public void update() {
    
        //keep track of how many times this Alien has been updated
        updateCounter++;
        
        //every 120th update, move the bounds of the alien half it's width to the right
        if(shootcd > 0) {
         	shootcd--;
    	 }
        if(updateCounter % 20 == 0) { 
        	
        	value = getBounds().x;
        	if(max==false)
        		getBounds().x += getBounds().width/2;
        	if(value > 755) {
        		max = true;
        		getBounds().y += getBounds().height/2;
        	}
        	if(max==true) {
        		getBounds().x -= getBounds().width/2;
        	}
        	if(value < 20) {
        		max=false;
        		getBounds().y += getBounds().height/2;
        	}
        }
       
	}
	public Laser shoot() {
		if(shootcd<=0) {
			shootcd = 240;
			return new Laser((int)getBounds().x--,(int)getBounds().y++,5,15,-1);
		}
		else
			return null;
		
	}
       	
        
        
    
    //draw the image represented by the alien
	public void render(Graphics2D g) {

		g.drawImage(image,
                    (int)getBounds().x,
                    (int)getBounds().y,
                    (int)getBounds().width,
                    (int)getBounds().height,
                    null);

	}

}