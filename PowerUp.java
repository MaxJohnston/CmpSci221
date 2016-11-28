//Max Johnston

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class PowerUp extends Entity {
    
    private int startY;
    
    public PowerUp(int x, int y) {
        super(x, y);
        startY = y;
    }
    
    public void update() {
        y += 1;
        checkCollisions();
        checkOffScreen();
    }
    
    public void draw(Graphics2D g2d){
        g2d.drawImage(getPUImg(), x, y, null);
        //g2d.draw(getBounds());
    }
    public Image getPUImg(){
        ImageIcon pi = new ImageIcon("PowerUp.png");
        return pi.getImage();
    }
    
    public void checkCollisions(){
        for (int i = 0; i < GameFrame.getMissileList().size(); i++){
            Missile m = GameFrame.getMissileList().get(i);
            
            if (getBounds().intersects(m.getBounds())){
                GameFrame.removePowerUp(this);
                GameFrame.removeMissile(m);
            }
        }
    }
    
    public void checkOffScreen(){
        if (y>=680){
            y = startY;
        }
    }
    public Rectangle getBounds(){
        return new Rectangle(x,y,getPUImg().getWidth(null),getPUImg().getHeight(null));
    }
}