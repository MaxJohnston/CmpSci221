//Jack Roboski and Max Johnston

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.awt.Image;

public class GameFrame extends JPanel implements ActionListener {
    
    Timer mainTimer;
    Player player;
    
    int enemyCount = 5;
    int level = 1;
    
    static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    static ArrayList<PowerUp> powerups = new ArrayList<PowerUp>();
    static ArrayList<Missile> missiles = new ArrayList<Missile>();
    Random rand = new Random();
    
    public GameFrame(){
        setFocusable(true);
        
        player = new Player(230, 530);
        addKeyListener(new KeyAdapt(player));
        
        mainTimer = new Timer(10, this);
        mainTimer.start();
        
        startGame();
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        
        ImageIcon ic = new ImageIcon("DemoMissile.png");
        g2d.drawImage(ic.getImage(), 0, 0,null);
        
        player.draw(g2d);
        
        for (int i = 0; i < enemies.size(); i++){
            Enemy tempEnemy = enemies.get(i);
            tempEnemy.draw(g2d);
        }
        
        for (int i = 0; i < missiles.size();i++){
            Missile m = missiles.get(i);
            m.draw(g2d);
        }
        
        for (int i = 0; i < powerups.size(); i++){
            PowerUp tempPU = powerups.get(i);
            tempPU.draw(g2d);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        player.update();
        
        for (int i = 0; i < enemies.size(); i++){
            Enemy tempEnemy = enemies.get(i);
            tempEnemy.update();
        }
        
        for (int i = 0; i < powerups.size(); i++){
            PowerUp tempPU = powerups.get(i);
            tempPU.update();
        }
        
        for (int i = 0; i < missiles.size();i++){
            Missile m = missiles.get(i);
            m.update();
        }
        
        checkEnd();
        repaint();

    }
    
    public void addEnemy(Enemy e) {
        enemies.add(e);
    }
    
    public static void removeEnemy(Enemy e){
        enemies.remove(e);
    }
    
    public static ArrayList<Enemy> getEnemyList(){
        return enemies;
    }
    
    public static void addMissile(Missile m) {
        missiles.add(m);
    }
    
    public static void removeMissile(Missile m){
        missiles.remove(m);
    }
    
    public static ArrayList<Missile> getMissileList(){
        return missiles;
    }
    
    public void addPowerUp(PowerUp u) {
        powerups.add(u);
    }
    
    public static void removePowerUp(PowerUp u){
        powerups.remove(u);
    }
    
    public static ArrayList<PowerUp> getPowerUpList(){
        return powerups;
    }
    public void startGame(){
        enemyCount = level*5;
        for (int i = 0; i < enemyCount; i++){
            addEnemy(new Enemy(rand.nextInt(500),-rand.nextInt(600)));
        }
        addPowerUp(new PowerUp(rand.nextInt(500),-rand.nextInt(600)));
    }
    public void checkEnd(){
        if(enemies.size() == 0 &&(powerups.size()==0)){
            level++;
            enemies.clear();
            missiles.clear();
            powerups.clear();
            JOptionPane.showMessageDialog(this, "You've won this round!");
            startGame();
        }
    }
}
