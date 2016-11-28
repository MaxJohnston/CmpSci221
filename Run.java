// Jack Roboski

import javax.swing.JFrame;

public class Run {
    
    public static void main(String[] args){
        JFrame frame = new JFrame("Game Demo");
        frame.setSize(500,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new StratGame2());
        frame.setVisible(true);
    }
}



//Max Johnston

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class SuperPower extends Being {
    
    private int startY;
    
    public SuperPower(int x, int y) {
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
        for (int i = 0; i < StratGame2.getProjectileList().size(); i++){
            Projectile m = StratGame2.getProjectileList().get(i);
            
            if (getBounds().intersects(m.getBounds())){
                StratGame2.removeSuperPower(this);
                StratGame2.removeProjectile(m);
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
