//Jack Roboski and Max Johnston


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


public class PowerUp extends Being {
    
    
    PowerUp(int x, int y){
        super(x,y);
    }
    
    public void update() {
        y += 1;
        checkOffScreen();
        checkCollisions();
    }
    
    public void checkOffScreen(){
        if (y>=600){
            StratGame2.removePowerUp(this);
        }
    }
    
    public void checkCollisions(){
            MyUnit u = StratGame2.unit;
            
            if (getBounds().intersects(u.getBounds())){
                StratGame2.removePowerUp(this);
                StratGame2.hullHealth +=1;
        StratGame2.scoreStatus.setText("Level: " + Integer.toString(StratGame2.level) + 
                " Score: "+Integer.toString(StratGame2.score) 
                    + " Hull Health: " + StratGame2.hullHealth);
            }
    }
    
    public void draw(Graphics2D g2d){
        g2d.drawImage(getPowerUpImg(), x, y, null);
    }
    
    public Image getPowerUpImg(){
        ImageIcon ic = new ImageIcon
        ("Demo.png");
        return ic.getImage();
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x,y,getPowerUpImg().getWidth(null),getPowerUpImg().getHeight(null));
    }
}
