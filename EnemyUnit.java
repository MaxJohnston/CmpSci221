// Game Demo

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class EnemyUnit extends Being {
    
    private int startY;
    
    public EnemyUnit(int x, int y) {
        super(x, y);
        startY = y;
    }
    
    public void update() {
        y += 1;
        checkCollisions();
        checkOffScreen();
    }
    
    public void draw(Graphics2D g2d){
        g2d.drawImage(getEnemyImg(), x, y, null);
        //g2d.draw(getBounds());
    }
    public Image getEnemyImg(){
        ImageIcon ic = new ImageIcon
        ("DemoEnemy.png");
        return ic.getImage();
    }
    
    public void checkCollisions(){
        for (int i = 0; i < StratGame2.getProjectileList().size(); i++){
            Projectile p = StratGame2.getProjectileList().get(i);
            
            if (getBounds().intersects(p.getBounds())){
                StratGame2.removeEnemy(this);
                StratGame2.removeProjectile(p);
                StratGame2.score = StratGame2.score+10;
        StratGame2.scoreStatus.setText("Level: " + Integer.toString(StratGame2.level) + 
                " Score: "+Integer.toString(StratGame2.score) 
                    + " Hull Health: " + StratGame2.hullHealth);
            }
        }
    }
    
    public void checkOffScreen(){
        if (y>=600){
            StratGame2.removeEnemy(this);
            StratGame2.score = StratGame2.score-(StratGame2.level*10);
            StratGame2.hullHealth -=1;
            StratGame2.scoreStatus.setText("Level: " + Integer.toString(StratGame2.level) + 
                " Score: "+Integer.toString(StratGame2.score) 
                    + " Hull Health: " + StratGame2.hullHealth);
            if (StratGame2.hullHealth <=0){
                JOptionPane.showMessageDialog
        (null, "You've lost.. Final stats: Level: " 
                + Integer.toString(StratGame2.level) + 
                " Score: "+Integer.toString(StratGame2.score) 
                    + " Hull Health: " + StratGame2.hullHealth);
                System.exit(0);
            }
        }
    }
    public Rectangle getBounds(){
        return new Rectangle(x,y,getEnemyImg().getWidth(null),getEnemyImg().getHeight(null));
    }
}
