//Game Demo


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;





public class Missile extends Entity {
    
    
    
    public Missile(int x, int y) {
        super(x,y);
    }
    
    public void update(){
        y -= 3;
    }
    
    public void draw(Graphics2D g2d){
        g2d.drawImage(getMissileImg(), x,y, null);
    }
    
    public Image getMissileImg(){
        ImageIcon ic = new ImageIcon("C:\\Users\\Gomensnana\\Desktop\\DemoMissile.png");
        return ic.getImage();
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x,y,getMissileImg().getWidth(null),getMissileImg().getHeight(null));
    }
}
