/*
    Screen implementation that models a game
*/

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public class GameScreen extends Screen
{
    //variables that represent the different GameObjects in the game
    private ArrayList<Alien> aliens;
    private Player player;
    private ArrayList<Laser> lasers;
    private int score;
    private int highScore;
    
    //this class inherits the following final variables (so you can't change them!)
    //
    //  GameState state;    //variable that lets you switch to another screen
	//  int width, height;  //the width and height of this screen

	public GameScreen(GameState s, int w, int h) {
		super(s, w, h);
		highScore = java.util.prefs.Preferences.userRoot().getInt("highScore", 0);
        initGame();
	}
    
    //initialize our variables before the next game begins
    public void initGame() {
    	score = 0;
        aliens = new ArrayList<Alien>();
        lasers = new ArrayList<Laser>();
        player = new Player(width/2 - 23, height - 24, 45, 24);
        aliens.add(new Alien(410, 100, 37, 25));
        aliens.add(new Alien(330, 100, 37, 25));
        aliens.add(new Alien(250, 100, 37, 25));
        aliens.add(new Alien(170, 100, 37, 25));
        aliens.add(new Alien(170, 160, 37, 25));
        aliens.add(new Alien(250, 160, 37, 25));
        aliens.add(new Alien(330, 160, 37, 25));
        aliens.add(new Alien(410, 160, 37, 25));
        aliens.add(new Alien(410, 220, 37, 25));
        aliens.add(new Alien(170, 220, 37, 25));
        aliens.add(new Alien(250, 220, 37, 25));
        aliens.add(new Alien(330, 220, 37, 25));
        aliens.add(new Alien(410, 220, 37, 25));
    }
	
    //render all the game objects in the game
	public void render(Graphics2D g) {
		
		for(Alien a: aliens)
            a.render(g);
		for(Laser b: lasers)
			b.render(g);
        player.render(g);
        g.setFont(new Font("Geneva", Font.BOLD, 28));
		g.setColor(Color.WHITE);
        g.drawString("Score:" + score, 660 , 65);
        g.setFont(new Font("Geneva", Font.BOLD, 28));
		g.setColor(Color.BLUE);
        g.drawString("High Score:" + highScore, 590 , 35);
        g.setFont(new Font("Geneva", Font.BOLD, 28));
		g.setColor(Color.GREEN);
        g.drawString("Lives:" + player.getLives(), 660 , 95);
       
        
	}
	
    //update all the game objects in the game
	public void update() {
		if(score > highScore) {
			highScore = score;
		}
		for(int i = 0; i < aliens.size(); i++) {
            Alien a = aliens.get(i);
            a.update();
            Laser l = a.shoot();
            if(l !=null)
            	lasers.add(l);
        }
        for(int i = 0; i < lasers.size(); i++) {
            Laser a = lasers.get(i);
            a.update();
        }
        for(int i = lasers.size()-1; i >= 0; i--){
        	Laser laser = lasers.get(i);
        
        	 for(int j = 0; j < aliens.size(); j++) {
        		 Alien a = aliens.get(j);
        		 if(laser.getX() < 0 || laser.getX() > 1000) {
        			 lasers.remove(laser);
        		 }
        		 if(a.intersects(laser) && laser.getDirection() == 1)  {
        			 aliens.remove(j);
        			 lasers.remove(i);
        			 score++;
        			 break;
        		 }
        		 if(player.intersects(laser) && laser.getDirection() == -1) {
        			 player.loseLife();
        			 lasers.remove(laser);
        			 break;
            		 }
        		 if(player.getLives() == 0) {
        			 
        			 gameOver();
        			 return;
        		 }
        		 
        	 }
        }
        
 
        
        player.update();
	}
	
    //handles key press events
	public void keyPressed(KeyEvent e)
	{
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_Q)
			state.switchToWelcomeScreen();
        else if(code == KeyEvent.VK_LEFT)
            player.setMovingLeft(true);
        if(code == KeyEvent.VK_RIGHT)
        	player.setMovingRight(true);
        if(code == KeyEvent.VK_SPACE) {
        	Laser a = player.shoot();
        	if(a != null)
        		lasers.add(a);
        	
        }
	}
	
    //handles key released events
	public void keyReleased(KeyEvent e)
	{
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_LEFT)
            player.setMovingLeft(false);
        if(code == KeyEvent.VK_RIGHT)
        	player.setMovingRight(false);
	}
    
    //should be called when the game is over
    
	public void gameOver() {
        //sets up the next game
		java.util.prefs.Preferences.userRoot().putInt("highScore", highScore);
		try { 
			java.util.prefs.Preferences.userRoot().sync(); } 
		catch(Exception e) { 
			e.printStackTrace(); };
        initGame();
        
        //switch to the game over screen
        state.switchToGameOverScreen();
    }
	
    //implement these methods if your player can use the mouse
	public void mousePressed(Point2D p)
	{
	}
	public void mouseReleased(Point2D p)
	{
	}
	public void mouseMoved(Point2D p)
	{
	}
	public void mouseDragged(Point2D p)
	{
	}
}