import java.awt.Graphics2D;
import java.awt.Image;

public class Laser extends GameObject {
	 private Image image = ImageLoader.loadCompatibleImage("sprites/playerLaser.png");
	 private int direction = 1;
	 private int updateCounter = 0;
	 private int xCord = 0;
	 private int yCord = 0;
	public Laser(int x, int y, int w, int h, int d) {
		super(x,y,w,h);
		direction = d;
		xCord = x;
		yCord = y;
	}
	public void update() {
		// TODO Auto-generated method stub
		updateCounter++;
		if(updateCounter % 3 ==0) {
			if(direction == -1)
				getBounds().y += getBounds().height/2;
			else {
				getBounds().y -= getBounds().height/2;
			}
		}
	}
	public int getDirection() {
		return direction;
	}
	public int getX() {
		return xCord;
	}
	

	
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		g.drawImage(image,
                (int)getBounds().x,
                (int)getBounds().y,
                (int)getBounds().width,
                (int)getBounds().height,
                null);
	}

}
