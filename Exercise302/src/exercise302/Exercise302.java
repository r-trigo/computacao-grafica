package exercise302;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercise302 extends JApplet{
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setTitle("Exercise302");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JApplet applet = new Exercise302();
		applet.init();
		
		frame.getContentPane().add(applet);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void init() {
		JPanel panel = new Exercise302Panel();
		getContentPane().add(panel);
	}

}
class Exercise302Panel extends JPanel{
	
	public Exercise302Panel(){
		setPreferredSize(new Dimension(400,400));
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
					
		
		//Intensidade
		g2.setColor(Color.RED);
		Shape intensity1 = new Rectangle2D.Float(315,25,80,20);
		g2.draw(intensity1);
		g2.setColor(Color.ORANGE);
		Shape intensity2 = new Rectangle2D.Float(315,75,80,20);
		g2.draw(intensity2);
		Shape intensity3 = new Rectangle2D.Float(315,125,80,20);
		g2.draw(intensity3);
		g2.setColor(Color.YELLOW);
		Shape intensity4 = new Rectangle2D.Float(315,175,80,20);
		g2.draw(intensity4);		
		Shape intensity5 = new Rectangle2D.Float(315,225,80,20);
		g2.draw(intensity5);
		g2.setColor(Color.GREEN);
		Shape intensity6 = new Rectangle2D.Float(315,275,80,20);
		g2.draw(intensity6);
		Shape intensity7 = new Rectangle2D.Float(315,325,80,20);
		g2.fill(intensity7);
		Shape intensity8 = new Rectangle2D.Float(315,375,80,20);
		g2.fill(intensity8);
				
		
	    //Inclinação
	    g2.rotate(Math.PI/16);			    	    	    
		
	    
		//Buracos
	    g2.setColor(Color.BLUE);
	    int x=50,y=50;
	    for(int i=0;i<3;i++){
	    	for(int j=0;j<3;j++){
	    	    Ellipse2D hole = new Ellipse2D.Float(x,y,50,50);
	    	    g2.draw(hole);
	    	    y += 100;
	    	}
    	    x +=100;
    	    y = 50;
	    }
	    
	    
	    //Marmota
	    g2.setColor(Color.ORANGE);
	    Ellipse2D molebody = new Ellipse2D.Float(62, 35, 25, 65);		   
	    
	    
	    //	Movimento
	    AffineTransform moleTR = new AffineTransform();	 
	    for(int i=0;i<3;i++){
	    	for(int j=0;j<3;j++){
	    	    Shape mole = moleTR.createTransformedShape(molebody);
	    		g2.fill(mole);
	    		moleTR.translate(0, 100);
	    	}
	    	moleTR.translate(100, -300);
	    }
	    
	    
		//Pau/Martelo
		g2.setColor(Color.BLACK);
		Shape hammer = new Rectangle2D.Float(0, 70, 15, 85);
		AffineTransform hammerTR = new AffineTransform();
		hammerTR.rotate(-Math.PI/6);
		Shape hammerNow = hammerTR.createTransformedShape(hammer);
		g2.fill(hammerNow);
	}
}
