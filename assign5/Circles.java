//Circles.java
//Taylor Brockhoeft
//11/05/14
//Java, Homework Five.

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Circles extends JPanel{
    //circle params
    private double radius; 
    private double x;
    private double y;
    
    public Circles(double r, double xpos, double ypos){
        radius = r;
        x = xpos;
        y = ypos;
    }

    public double diameter(double r){
        return Math.round(2*r*100.0)/100.0;
    }
    public double circumference(double r){
        return Math.round(2*Math.PI*r*100.0)/100.0;
    }
    public double area(double r){
        return Math.round(Math.PI*r*r*100.0)/100.0;
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        double diameter = diameter(radius);;
        double circum = circumference(radius);
        double area = area(radius);
        
        g.drawString("Diameter: " + diameter, 5, 15);
        g.drawString("X: " + x, 5, 30);
        g.drawString("Y: " + y, 75, 30);
        g.drawString("Circumference: " + circum, 5, 45);
        g.drawString("Area: " + area, 5, 60);
        
        g2d.setColor(new Color(153 , 0, 153));
        g2d.fill(new Ellipse2D.Double(x, y, diameter, diameter));
    }


    public static void main(String[] args){
        double cx=0;
        double cy=0;
        double rad; 
        double maxX = 800;
        double maxY = 600;

        do{
        String r = JOptionPane.showInputDialog("Enter radius (r > 580)");
        rad = Double.parseDouble(r);
        } while(cx < 0 || cx > 480);

        maxX=maxX - 20; 
        maxY=maxY - 20;
        
        do{
        String x = JOptionPane.showInputDialog("Enter coordinate x (0 - "+ maxX +")");//Max is 800,thus 780
        cx = Double.parseDouble(x);
        } while(cx < 0 || cx > maxX);
        
        do{
        String y = JOptionPane.showInputDialog("Enter coordinate y (50 -" + maxY + ")");//Max is 600, thus 580
        cy = Double.parseDouble(y);
        } while(cy < 0 || cy > maxY);
        
        JFrame frame = new JFrame("Circles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        Circles c = new Circles(rad, cx, cy);
        c.setBackground(Color.WHITE);
        frame.add(c);
        frame.setVisible(true);
    }
}




