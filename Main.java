// Game Demo

import javax.swing.JFrame;

public class Main {
    
    public static void main(String[] args){
        JFrame frame = new JFrame("Game Demo");
        frame.setSize(500,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new GameFrame());
        frame.setVisible(true);
    }
}
