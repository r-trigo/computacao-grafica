package exercise304;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercise304 extends JApplet{

	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setTitle("Exercise304");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JApplet applet = new Exercise304();
		applet.init();
		
		frame.getContentPane().add(applet);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void init() {
		JPanel panel = new Exercise304Panel();
		getContentPane().add(panel);
	}

}
class Exercise304Panel extends JPanel{
	
	public Exercise304Panel(){
		setPreferredSize(new Dimension(400,400));
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		
		
		Font myfont = new Font("Times New Roman", Font.CENTER_BASELINE, 120);
		AffineTransform tx = new AffineTransform();
		Rectangle2D caixa = new Rectangle2D.Float(165, 65, 120, 190);
		tx.rotate(Math.PI/-16);
		GradientPaint corFundo = new GradientPaint(
				250,250,new Color(0,128,128),150,25,new Color(255,128,0),true);
		g2.setPaint(corFundo);
		g2.fill(caixa);
		
		
		float dash1[] = {2f};
		BasicStroke tracejado =  new BasicStroke(
				4.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10f, dash1, 12f); 
		g2.setStroke(tracejado);
		g2.setColor(Color.BLACK);
		g2.draw(caixa);				
		
		
		g2.setColor(Color.BLUE);
		//T
		tx.scale(2, 2);
		g2.setFont(myfont.deriveFont(tx));
		g2.drawString("T", 80, 250);

		
		g2.setColor(Color.YELLOW);
		//R
		tx.scale(0.5, 0.5);
		g2.setFont(myfont.deriveFont(tx));			
		g2.drawString("R", 150, 250);				
	}
}