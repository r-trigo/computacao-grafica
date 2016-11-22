package exercise303;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercise303 extends JApplet{

	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setTitle("Exercise303");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JApplet applet = new Exercise303();
		applet.init();
		
		frame.getContentPane().add(applet);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void init() {
		JPanel panel = new Exercise303Panel();
		getContentPane().add(panel);
	}

}
class Exercise303Panel extends JPanel{
	
	public Exercise303Panel(){
		setPreferredSize(new Dimension(400,400));
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform tr = new AffineTransform();
		
		
		//Vaso
		Rectangle2D vase = new Rectangle2D.Float(25, 25, 350, 350);
		Color castanho = new Color(206, 139, 26);
		g2.setColor(castanho);			
		g2.fill(vase);
		
		
		//Centro		
		Ellipse2D center = new Ellipse2D.Float(175, 175, 50, 50);
		g2.setColor(Color.BLACK);
		g2.draw(center);
		g2.setColor(Color.ORANGE);
		g2.fill(center);
		
		
		
		Shape petal1 = new Ellipse2D.Double(center.getCenterX(), center.getMaxY(), 25, 150);	
		

		
		//Pétalas RED		
		g2.setColor(Color.RED);
		for(int i=0;i<7;i++){
			AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (1.0f-i/10f)); 
			g2.setComposite(ac);
			Shape petal2 = tr.createTransformedShape(petal1);
			tr.rotate(Math.PI/14, center.getCenterX(), center.getCenterY());
			g2.fill(petal2);
		}
		
		
		
		//Pétalas BLUE
		g2.setColor(Color.BLUE);
		for(int i=0;i<7;i++){
			AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (1.0f-i/10f)); 
			g2.setComposite(ac);
			Shape petal2 = tr.createTransformedShape(petal1);
			tr.rotate(Math.PI/14, center.getCenterX(), center.getCenterY());
			g2.fill(petal2);
		}
		
		
		
		//Pétalas GREEN
		g2.setColor(Color.GREEN);
		for(int i=0;i<7;i++){
			AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (1.0f-i/10f)); 
			g2.setComposite(ac);
			Shape petal2 = tr.createTransformedShape(petal1);
			tr.rotate(Math.PI/14, center.getCenterX(), center.getCenterY());
			g2.fill(petal2);
		}
		
		
		
		//Pétalas YELLOW
		g2.setColor(Color.YELLOW);
		for(int i=0;i<7;i++){
			AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (1.0f-i/10f)); 
			g2.setComposite(ac);
			Shape petal2 = tr.createTransformedShape(petal1);
			tr.rotate(Math.PI/14, center.getCenterX(), center.getCenterY());
			g2.fill(petal2);
		}
						
		
		
		Shape minipetal1 = new Ellipse2D.Double(center.getCenterX(), center.getMaxY(), 10, 45);	
		
		
		
		//Pétalas miniBLUE
		g2.setColor(Color.BLUE);
		for(int i=0;i<3;i++){
			AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (0.7f+i/10f)); 
			g2.setComposite(ac);
			Shape minipetal2 = tr.createTransformedShape(minipetal1);
			tr.rotate(Math.PI/6, center.getCenterX(), center.getCenterY());
			g2.fill(minipetal2);
		}
		
		
		
		//Pétalas miniGREEN
		g2.setColor(Color.GREEN);
		for(int i=0;i<3;i++){
			AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (0.7f+i/10f)); 
			g2.setComposite(ac);
			Shape minipetal2 = tr.createTransformedShape(minipetal1);
			tr.rotate(Math.PI/6, center.getCenterX(), center.getCenterY());
			g2.fill(minipetal2);
		}
		
		
		
		//Pétalas miniYELLOW
		g2.setColor(Color.YELLOW);
		for(int i=0;i<3;i++){
			AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (0.7f+i/10f)); 
			g2.setComposite(ac);
			Shape minipetal2 = tr.createTransformedShape(minipetal1);
			tr.rotate(Math.PI/6, center.getCenterX(), center.getCenterY());
			g2.fill(minipetal2);
		}
		
		
		
		//Pétalas miniRED		
		g2.setColor(Color.RED);
		for(int i=0;i<3;i++){
			AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (0.7f+i/10f)); 
			g2.setComposite(ac);
			Shape minipetal2 = tr.createTransformedShape(minipetal1);
			tr.rotate(Math.PI/6, center.getCenterX(), center.getCenterY());
			g2.fill(minipetal2);
		}
		
		
		
		//Desenhar pétalas grandes
		g2.setColor(Color.BLACK);
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (1.0f)); 
		g2.setComposite(ac);
		
		g2.draw(petal1);

		for(int i=0;i<28;i++){
			Shape petal2 = tr.createTransformedShape(petal1);
			tr.rotate(Math.PI/14, center.getCenterX(), center.getCenterY());
			g2.draw(petal2);
		}
		
		
		
		//Desenhar pétalas pequenas	
		
		g2.setColor(Color.BLACK);
		g2.draw(minipetal1);
		

		for(int i=0;i<12;i++){
			Shape minipetal2 = tr.createTransformedShape(minipetal1);
			tr.rotate(Math.PI/6, center.getCenterX(), center.getCenterY());
			g2.draw(minipetal2);
		}								
		
		
	}
}