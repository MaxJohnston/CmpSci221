import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ImageIcon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JLabel;

public class StratGame2 extends JPanel implements ActionListener {
    public static JLabel scoreStatus;
    public static JLabel speedStatus;
    public static int score=0;
    public static int hullHealth = 5;
    Timer mainTimer;
    static MyUnit unit;
    
    int enemyCount = 5;
    int powerCt = 1;
    static int level = 1;
    
    static ArrayList<EnemyUnit> enemies = new ArrayList<EnemyUnit>();
    static ArrayList<Projectile> projectile = new ArrayList<Projectile>();
    static ArrayList<PowerUp> powerUp = new ArrayList<PowerUp>();
    static ArrayList<SuperPower> superpowers = new ArrayList<SuperPower>();
    Random rand = new Random();
    
    public StratGame2(){
        setFocusable(true);
        scoreStatus = new JLabel("N/A");
        speedStatus = new JLabel("N/A");
        add(speedStatus,BorderLayout.PAGE_END);
        add(scoreStatus,BorderLayout.NORTH);
        
        scoreStatus.setText("Level: " + Integer.toString(level) + 
                " Score: "+Integer.toString(score));
        
        unit = new MyUnit(230, 530);
        addKeyListener(new Adaptinator(unit));
        
        mainTimer = new Timer(10, this);
        mainTimer.start();
        
        startGame();
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        
        ImageIcon ic = 
        new ImageIcon("DemoMissile.png");
        g2d.drawImage(ic.getImage(), 0, 0,null);
        
        unit.draw(g2d);
        
        for (int i = 0; i < enemies.size(); i++){
            EnemyUnit tempEnemy = enemies.get(i);
            tempEnemy.draw(g2d);
        }
        
        for (int i = 0; i < powerUp.size(); i++){
            PowerUp powerup = powerUp.get(i);
            powerup.draw(g2d);
        }
        
        for (int i = 0; i < projectile.size();i++){
            Projectile p = projectile.get(i);
            p.draw(g2d);
        }
        for (int i = 0; i < superpowers.size(); i++){
            SuperPower tempPU = superpowers.get(i);
            tempPU.draw(g2d);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        unit.update();
        
        for (int i = 0; i < enemies.size(); i++){
            EnemyUnit tempEnemy = enemies.get(i);
            tempEnemy.update();
        }
        
        for (int i = 0; i < powerUp.size(); i++){
            PowerUp powerup = powerUp.get(i);
            powerup.update();
        }
        
        for (int i = 0; i < projectile.size();i++){
            Projectile p = projectile.get(i);
            p.update();
        }
        for (int i = 0; i < superpowers.size(); i++){
            SuperPower tempPU = superpowers.get(i);
            tempPU.update();
        }
        
        checkEnd();
        repaint();

    }
    
    public void addEnemy(EnemyUnit e) {
        enemies.add(e);
    }
    
    public void addPowerUp(PowerUp p){
        powerUp.add(p);
    }
    
    public static void removeEnemy(EnemyUnit e){
        enemies.remove(e);
        
    }
    
    public static void removePowerUp(PowerUp p){
        powerUp.remove(p);
    }
    
    public static ArrayList<EnemyUnit> getEnemyList(){
        return enemies;
    }
    
    public static void addProjectile(Projectile p) {
        projectile.add(p);
        score = score-level;
        StratGame2.scoreStatus.setText("Level: " + Integer.toString(StratGame2.level) + 
                " Score: "+Integer.toString(StratGame2.score) 
                    + " Hull Health: " + StratGame2.hullHealth);
    }
    
    public static void removeProjectile(Projectile p){
        projectile.remove(p);
    }
    
    public static ArrayList<Projectile> getProjectileList(){
        return projectile;
    }
    public static ArrayList<PowerUp> getPowerUpList(){
        return powerUp;
    }
    
    public void addSuperPower(SuperPower u) {
        superpowers.add(u);
    }
    
    public static void removeSuperPower(SuperPower u){
        superpowers.remove(u);
    }
    
    public static ArrayList<SuperPower> getSuperPowerList(){
        return superpowers;
    }
    public void startGame(){
        enemyCount = level*5;
        powerCt = level;
        for (int i = 0; i < enemyCount; i++){
            addEnemy(new EnemyUnit(rand.nextInt(400)+50,-rand.nextInt(600)));
        }
        addSuperPower(new SuperPower(rand.nextInt(500),-rand.nextInt(600)));
        for (int i = 0; i < powerCt; i++){
            addPowerUp(new PowerUp(rand.nextInt(400)+50,-rand.nextInt(600)));
        }
        StratGame2.scoreStatus.setText("Level: " + Integer.toString(StratGame2.level) + 
                " Score: "+Integer.toString(StratGame2.score) 
                    + " Hull Health: " + StratGame2.hullHealth);;
    }
    public void checkEnd(){
        if(enemies.size() == 0){
            level++;
            score = level*(score+50);
            hullHealth++;
            enemies.clear();
            projectile.clear();
            JOptionPane.showMessageDialog(this, "You've won this round!");
            startGame();
        }
    }
}
