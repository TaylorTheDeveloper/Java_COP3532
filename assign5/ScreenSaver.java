//ScreenSaver.java
//Taylor Brockhoeft
//11/05/14
//Java, Homework Five.

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import javax.swing.Timer;

public class ScreenSaver extends JPanel implements ActionListener{
    private static int maxcolor = 256;
    private static int width = 800;
    private static int height = 600;
    private static Timer t;

    
    public ScreenSaver(){
        t = new Timer(1000, this);
        t.start();
    }
    
    public void actionPerformed(ActionEvent event){
        repaint();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Random rand = new Random();
        int x1, x2, y1, y2;

        for(int i=0; i<100; i++){
            x1 = rand.nextInt(width);
            y1 = rand.nextInt(height);
            x2 = rand.nextInt(width);
            y2 = rand.nextInt(height);
            g.setColor(new Color(rand.nextInt(maxcolor),rand.nextInt(maxcolor), rand.nextInt(maxcolor)));
            g.drawLine(x1, y1, x2, y2);
        }
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("ScreenSaver");
        ScreenSaver s = new ScreenSaver();
        s.setBackground(Color.WHITE);
        frame.add(s);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

}




