//Game Demo


import javax.swing.ImageIcon;

import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;

import javax.swing.JOptionPane;

public class MyUnit extends Being {
    int velY=0;
    int velX=0;
    int speed = 2;
    boolean TripleBlast=false;
    boolean RapidFire = false;
    boolean Speedster = false;
    
    public MyUnit(int x, int y) {
        super(x,y);

    }
    
    public void update() {

        x += velX;
        y += velY;
        checkCollisions();
        StratGame2.speedStatus.setText
        ("->X: " + velX + "    ->\nY: " + velY + "    ");
    }
    
    public void draw(Graphics2D g2d){
        g2d.drawImage(getUnitImg(), x, y,null);
        //g2d.draw(getBounds());
    }
    
    public Image getUnitImg(){
        ImageIcon ic = 
                new ImageIcon("Demo.png");
        return ic.getImage();
    }
    
    public void keyPressed(KeyEvent e){
        int kP = e.getKeyCode();
        if (kP == KeyEvent.VK_W){
            velY = -speed;
        } else if (kP == KeyEvent.VK_S){
            velY = speed;
        } else if (kP == KeyEvent.VK_A){
            velX = -speed;
        } else if (kP == KeyEvent.VK_D){
            velX = speed;
        }
        	else if ((kP == KeyEvent.VK_SPACE) && (TripleBlast == false)&& (RapidFire == false)){
                StratGame2.addProjectile(new Projectile(x,y));
            }
            else if ((kP == KeyEvent.VK_SPACE) && (TripleBlast ==true)&& (RapidFire == false)){
                StratGame2.addProjectile(new Projectile(x,y));
                StratGame2.addProjectile(new Projectile(x+15,y));
                StratGame2.addProjectile(new Projectile(x-15,y));
            }
            else if ((kP == KeyEvent.VK_SPACE) && (RapidFire ==true)&& (TripleBlast == false)){
                StratGame2.addProjectile(new Projectile(x,y));
                StratGame2.addProjectile(new Projectile(x,y+10));
                StratGame2.addProjectile(new Projectile(x,y+20));
            }
    }
    public void keyReleased(KeyEvent e){
        int kP = e.getKeyCode();
        
  
        if (kP == KeyEvent.VK_A){
            velX = 0;
        } else if (kP == KeyEvent.VK_D){
            velX = 0;
        } 
        else if (kP == KeyEvent.VK_W){
            velY = 0;
        } else if (kP == KeyEvent.VK_S){
            velY = 0;
        }
    
    }
    
    public void checkCollisions(){
        ArrayList<EnemyUnit> enemies = StratGame2.getEnemyList();
        ArrayList<SuperPower> superpowers = StratGame2.getSuperPowerList();
        for (int i = 0; i < enemies.size(); i++){
            EnemyUnit tempEnemy = enemies.get(i);
            if(getBounds().intersects(tempEnemy.getBounds())){
                JOptionPane.showMessageDialog
        (null, "You've lost.. Final stats: Level: " 
                + Integer.toString(StratGame2.level) + 
                " Score: "+Integer.toString(StratGame2.score) 
                    + " Hull Health: " + StratGame2.hullHealth);
                System.exit(0);
            }
        }
        for (int i = 0; i < superpowers.size(); i++){
        	SuperPower tempPU = superpowers.get(i);
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
            	else if (pType == 0){
            		Speedster = true;
            		RapidFire = false;
            		TripleBlast = false;
            	}if (Speedster == true)
            	{
            		speed =4;
            	}
            	if (Speedster == false)
            	{
            		speed = 2;
            	}
            	
            	StratGame2.removeSuperPower(tempPU);
            }
        }
    }
    
    public Rectangle getBounds(){
        return new Rectangle
        (x,y,getUnitImg().getWidth(null),getUnitImg().getHeight(null));
    }


}
