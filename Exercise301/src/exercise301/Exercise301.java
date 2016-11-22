package exercise301;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercise301 extends JApplet{

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Exercise301");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JApplet applet = new Exercise301();
		applet.init();
		
		frame.getContentPane().add(applet);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void init() {
		JPanel panel = new Exercise301Panel();
		getContentPane().add(panel);
	}
}

class Exercise301Panel extends JPanel{
		
	public Exercise301Panel(){
		setPreferredSize(new Dimension(400,400));
	}	
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		
		//LegendaY
		g2.drawString("Download speed - Mbps", 10, 25);
		g2.drawString("1250",10,50);
		g2.drawString("1000",10,100);
		g2.drawString("750",10,150);
		g2.drawString("500",10,200);
		g2.drawString("250",10,250);
		g2.drawString("0",10,300);
		
		//LegendaX
		g2.drawString("Tokyo",50,325);
		g2.drawString("Zurich",100,325);
		g2.drawString("London",150,325);
		g2.drawString("Riga",210,325);
		g2.drawString("Dublin",250,325);
		g2.drawString("Praga",300,325);
		g2.drawString("Town",355,310);
		
		//fundo
		BufferedImage image = null;
		URL url = getClass().getClassLoader().getResource("images/lightning.jpg");
		try {
			image = ImageIO.read(url);
		} catch (IOException ex) {
			ex.printStackTrace();
		}		
		
		Rectangle2D caixaFundo = new Rectangle2D.Float(49, 49, 301, 251);
		g2.draw(caixaFundo);
		TexturePaint fundo = new TexturePaint(image,caixaFundo);
		g2.setPaint(fundo);
		g2.fill(caixaFundo);
		
		//linhas tracejadas
		float dash1[] = {10.0f};
		BasicStroke tracejado =  new BasicStroke(
				1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f); 
		g2.setStroke(tracejado);
		g2.setColor(Color.WHITE);
	
		for(int i=1;i<5;i++){
			int intervalo = i*50;
			g2.drawLine(50,50+intervalo,350,50+intervalo);		
		}
		
		//barras
		GradientPaint barras = new GradientPaint(
				250,250,new Color(117,25,209),150,25,new Color(97,163,130),true);
		int valores[] = {1000,1000,500,300,250,200};
		g2.setPaint(barras);
		for(int i=0;i<6;i++){
			g2.fillRect(50, 300-(valores[i]/5), 49, (valores[i]/5));
			g2.translate(50, 0);
		}		
	}	
}