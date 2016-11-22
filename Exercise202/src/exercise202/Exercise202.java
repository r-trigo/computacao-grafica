package exercise202;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercise202 extends JApplet{

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Exercise202");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JApplet applet = new Exercise202();
		applet.init();
		
		frame.getContentPane().add(applet);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void init() {
		JPanel panel = new Exercise202Panel();
		getContentPane().add(panel);
	}
}

class Exercise202Panel extends JPanel{
		
		public Exercise202Panel(){
			setPreferredSize(new Dimension(400,400));
		}
		
		public void paint(Graphics g){
			super.paint(g);
			Graphics2D g2 = (Graphics2D) g;						
			
			//bordas
			Ellipse2D elipseBordas = new Ellipse2D.Float(50, 50, 250, 250);
			Color corCorpo = new Color(206, 139, 26);
			g2.setColor(corCorpo);
			g2.fill(elipseBordas);
			
			//nariz
			Ellipse2D elipseNariz = new Ellipse2D.Float(162, 125, 30, 55);	
			g2.setColor(Color.BLACK);
			g2.draw(elipseNariz);
			Color corNariz = new Color(86,11,0);
			g2.setColor(corNariz);
			g2.fill(elipseNariz);
						
					
			//olhos
			Shape olhoE = new Ellipse2D.Double(92, 105, 50, 50);
			Shape olhoD = new Ellipse2D.Double(212, 105, 50, 50);			
			g2.draw(olhoE);
			g2.draw(olhoD);
			Area a1 = new Area (olhoE);
			Area a2 = new Area (olhoD);
			a1.add(a2);
			Color corOlhos = new Color(176,212,142);
			g2.setColor(corOlhos);
			g2.fill(a1);
			
			//iris
			Shape irisE = new Ellipse2D.Double(102, 115, 30, 30);
			Shape irisD = new Ellipse2D.Double(222, 115, 30, 30);
			g2.draw(irisE);
			g2.draw(irisD);
			Color corIris = new Color(89,58,29);
			g2.setColor(corIris);
			g2.fill(irisE);
			g2.fill(irisD);
			
			//barbeado
			GeneralPath barbeado = new GeneralPath();
			barbeado.moveTo(162, 200);
			barbeado.lineTo(192, 250);
			barbeado.moveTo(162, 200);
			barbeado.lineTo(162, 250);
			barbeado.moveTo(162, 250);
			barbeado.lineTo(192, 250);
			barbeado.moveTo(192, 250);
			barbeado.lineTo(192, 200);
			barbeado.moveTo(192, 200);
			barbeado.lineTo(162, 250);
			barbeado.closePath();
			Color corBarbeado = new Color(109,73,39);
			g2.setColor(corBarbeado);
			g2.draw(barbeado);
		}
}