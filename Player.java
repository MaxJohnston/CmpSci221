//Jack Roboski and Max Johnston


import javax.swing.ImageIcon;

import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;

import javax.swing.JOptionPane;
import java.util.*;
public class Player extends Entity {
    
    int velX=0;
    int speed = 2;
    boolean TripleBlast=false;
    boolean RapidFire = false;
    boolean Speedster = false;
    
    public Player(int x, int y) {
        super(x,y);

    }
    
    public void update() {

        x += velX;
        
        checkCollisions();
    }
    
    public void draw(Graphics2D g2d){
        g2d.drawImage(getPlayerImg(), x, y,null);
        //g2d.draw(getBounds());
    }
    
    public Image getPlayerImg(){
        ImageIcon ic = new ImageIcon("Demo.png");
        return ic.getImage();
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if (Speedster = true)
        	speed = 4;
        else
        	speed = 2;

        if (key == KeyEvent.VK_A){
            velX = -speed;
        } else if (key == KeyEvent.VK_D){
            velX = speed;
        }
        else if ((key == KeyEvent.VK_SPACE) && (TripleBlast == false)&& (RapidFire == false)){
            GameFrame.addMissile(new Missile(x,y));
        }
        else if ((key == KeyEvent.VK_SPACE) && (TripleBlast ==true)&& (RapidFire == false)){
            GameFrame.addMissile(new Missile(x,y));
            GameFrame.addMissile(new Missile(x+15,y));
            GameFrame.addMissile(new Missile(x-15,y));
        }
        else if ((key == KeyEvent.VK_SPACE) && (RapidFire ==true)&& (TripleBlast == false)){
            GameFrame.addMissile(new Missile(x,y));
            GameFrame.addMissile(new Missile(x,y+10));
            GameFrame.addMissile(new Missile(x,y+20));
        }
    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
  
        if (key == KeyEvent.VK_A){
            velX = 0;
        } else if (key == KeyEvent.VK_D){
            velX = 0;
        }
    }
    
    public void checkCollisions(){
        ArrayList<Enemy> enemies = GameFrame.getEnemyList();
        ArrayList<PowerUp> powerups = GameFrame.getPowerUpList();
        
        for (int i = 0; i < enemies.size(); i++){
            Enemy tempEnemy = enemies.get(i);
            if(getBounds().intersects(tempEnemy.getBounds())){
            	JOptionPane.showMessageDialog(null, "You've lost..");
            	System.exit(0);

            }
        }
        for (int i = 0; i < powerups.size(); i++){
        	PowerUp tempPU = powerups.get(i);
            if(getBounds().intersects(tempPU.getBounds())){
            	int pType = (int) (Math.random()*3);
            	if (pType == 1){
            		TripleBlast = true;
            		RapidFire = false;
            		Speedster = false;
            	}
            	else if (pType == 2){
            		RapidFire = true;;
            		TripleBlast = false;
            		Speedster = false;
            	}
            	else{
            		Speedster = true;
            		RapidFire = false;
            		TripleBlast = false;
            	}
            	GameFrame.removePowerUp(tempPU);
            }
        }
        
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x,y,getPlayerImg().getWidth(null),getPlayerImg().getHeight(null));
    }
}
