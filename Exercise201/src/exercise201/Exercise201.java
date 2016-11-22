package exercise201;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercise201 extends JApplet{
	/*Este desenho abstrato representa a capa do álbum musical
	"Ship arriving too late to save a drowning witch", dos Zappa*/
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Exercise201");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JApplet applet = new Exercise201();
		applet.init();
		
		frame.getContentPane().add(applet);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void init() {
		JPanel panel = new Exercise201Panel();
		getContentPane().add(panel);
	}
}

class Exercise201Panel extends JPanel{
	
	public Exercise201Panel(){
		setPreferredSize(new Dimension(400,400));
	}
	
	public void paint(Graphics g){
		
		//Z
		g.drawLine(102, 25, 138, 25);
		g.drawLine(138, 25, 102, 75);
		g.drawLine(102, 75, 138, 75);
		
		//A
		g.drawLine(142, 75, 160, 25);
		g.drawLine(160, 25, 178, 75);
		g.drawLine(142, 75, 178, 75);				
		
		//P
		g.drawLine(182, 25, 182, 75);
		g.drawOval(182, 25, 36, 36);
		
		//P
		g.drawLine(222, 25, 222, 75);
		g.drawOval(222, 25, 36, 36);
		
		//A
		g.drawLine(262, 75, 280, 25);
		g.drawLine(280, 25, 298, 75);
		g.drawLine(262, 75, 298, 75);
		
		//exterior rectangle
		g.drawRect(100, 100, 200, 200);
		
		//interior rectangle
		g.drawLine(100, 150, 200, 150);
		g.drawLine(200, 150, 150, 250);
		g.drawLine(100, 250, 300, 250);
		g.drawLine(200, 250, 225, 200);
		g.drawLine(225, 200, 250, 250);
		
		//message
		g.drawString("Ship arriving too late to save a drowning witch", 75, 325);
	}
}