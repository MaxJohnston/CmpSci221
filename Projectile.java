//Game Demo


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;





public class Projectile extends Being {
    
    
    
    public Projectile(int x, int y) {
        super(x,y);
    }
    
    public void update(){
        y -= 3;
    }
    
    public void draw(Graphics2D g2d){
        g2d.drawImage(getProjectileImg(), x,y, null);
    }
    
    public Image getProjectileImg(){
        ImageIcon ic = new ImageIcon("DemoMissile.png");
        return ic.getImage();
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x,y,getProjectileImg().getWidth(null),getProjectileImg().getHeight(null));
    }
}
