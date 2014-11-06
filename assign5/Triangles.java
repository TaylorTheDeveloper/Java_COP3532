//Triangles.java
//Taylor Brockhoeft
//11/05/14
//Java, Homework Five.

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.Dimension;

public class Triangles extends JPanel{
    private static int size = 500;//Size of Window
    private static int maxcolor = 256;
    public static int height, width;
    public static Dimension windowSize;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Random rand = new Random();        
        Graphics2D g2d = (Graphics2D) g;
                        
        for(int i=0; i<5; i++){
                                                      
            GeneralPath triangle = new GeneralPath();
            triangle.moveTo(rand.nextInt(size), rand.nextInt(size));

            for(int j=0; j<2; j++){
                triangle.lineTo(rand.nextInt(size), rand.nextInt(size));
            }
            
            triangle.closePath();
            g2d.setColor(new Color(rand.nextInt(maxcolor), rand.nextInt(maxcolor),
                rand.nextInt(maxcolor)));
            g2d.fill(triangle);
            System.out.println(height);System.out.println(width);
            height = (int)windowSize.getHeight(); // Issue, keeps returning 470 and 490(width)
            width  = (int)windowSize.getWidth();
        }
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("Triangles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(size, size);
        //frame.setResizable(false);
        Triangles t = new Triangles();
        t.setBackground(Color.WHITE);
        frame.add(t);
        frame.setVisible(true);

        windowSize = frame.getContentPane().getSize();

    }
}
