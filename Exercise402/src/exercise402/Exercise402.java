package exercise402;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercise402 extends JApplet {
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setTitle("Exercise402");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JApplet applet = new Exercise402();
		applet.init();
		
		frame.getContentPane().add(applet);
		frame.pack();
		frame.setVisible(true);		
	}		
	
	public void init() {
		JPanel panel = new Exercise402Panel();
		getContentPane().add(panel);		
	}

}

class Exercise402Panel extends JPanel implements Runnable, Printable {
	
	PrinterJob pj;
	Controls ctrls = new Controls();
	Snake mySnake = new Snake();
	Shape viper = new Rectangle2D.Float(mySnake.getPosX(), mySnake.getPosY(), mySnake.getLenght(), 10);
	Food myFood = new Food();
	Shape fruit = new Rectangle2D.Float(myFood.getPosX(), myFood.getPosY(), 10, 10);
	Shape blade1 = new Rectangle2D.Double(100,200-5,200,10);
	Shape blade2 = new Rectangle2D.Double(200-5,100+5,10,200);
	AffineTransform rotation = new AffineTransform();
	
	public Exercise402Panel(){
		setPreferredSize(new Dimension(400,400));		
		addKeyListener(ctrls);
		setFocusable(true);
		Thread thread = new Thread(this);
        thread.start();
        pj = PrinterJob.getPrinterJob();
        pj.setPrintable(this);        
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, 400, 400);
		
		g2.setColor(Color.BLUE);
		g2.drawString("Keys: UP, DOWN, LEFT, RIGHT and P", 100, 100);
		
		GradientPaint skin = new GradientPaint(
				0,0,new Color(0,255,0),50,50,new Color(34,139,34),true);	
		g2.setPaint(skin);
		g2.fill(viper);				
		
		g2.setColor(Color.RED);
		g2.fill(fruit);
		
		g2.setColor(Color.LIGHT_GRAY);
		Shape blade10 = rotation.createTransformedShape(blade1);
		Shape blade20 = rotation.createTransformedShape(blade2);
		g2.fill(blade10);
		g2.fill(blade20);
	}
	
	public void userAction() {
		if(ctrls.up) {
			mySnake.setGoing('u');
			ctrls.up = false;
		} else if (ctrls.down) {
			mySnake.setGoing('d');
			ctrls.down = false;
		} else if(ctrls.left) {
			mySnake.setGoing('l');
			ctrls.left = false;
		} else if(ctrls.right) {
			mySnake.setGoing('r');
			ctrls.right = false;
		} else if(ctrls.print) {
	        if (pj.printDialog()) {
	            try {
	                pj.print();
	            } catch (PrinterException ex) {
	                ex.printStackTrace();
	            }
	        }
	        ctrls.print = false;
		}
	}
	
	public void checkSnakeLoc() {
		if (mySnake.getPosX() < 0) {
			mySnake.setPosX(0);
		} else if (mySnake.getPosX() > 400 - mySnake.getLenght()) {
			mySnake.setPosX(400 - mySnake.getLenght());
		} else if (mySnake.getPosY() < 0) {
			mySnake.setPosY(0);
		} else if (mySnake.getPosY() > 400 - 10) {
			mySnake.setPosY(400 - 10);
		}
	}
	
	public void checkFoodLoc() {
		if (myFood.getPosX() < 0) {
			myFood.setPosX(0);
		} else if (myFood.getPosX() > 400 - 10) {
			myFood.setPosX(400 - 10);
		} else if (myFood.getPosY() < 0) {
			myFood.setPosY(0);
		} else if (myFood.getPosY() > 400 - 10) {
			myFood.setPosY(400 - 10);
		}
	}
	
	@Override
	public void run() {
		while(true) {
			checkSnakeLoc();
			userAction();			
			viper = new Rectangle2D.Float(mySnake.getPosX(), mySnake.getPosY(), mySnake.getLenght(), 10);
			if (viper.intersects(fruit.getBounds())) {
				if(mySnake.getLenght() < 350) {
					mySnake.grow();
				}
				myFood = new Food();
				checkFoodLoc();
				fruit = new Rectangle2D.Float(myFood.getPosX(), myFood.getPosY(), 10, 10);
			}
			mySnake.crawl();
			rotation.rotate(Math.toRadians(5),200,200);
			if(viper.intersects(blade1.getBounds()) || viper.intersects(blade2.getBounds())) {
				mySnake.reset();
			}
			repaint();			
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
					
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		switch (pageIndex) {
        case 0:
            draw(graphics);
            break;
        case 1:
            graphics.translate(-(int) pageFormat.getImageableWidth(), 0);
            draw(graphics);
            break;
        default:
            return NO_SUCH_PAGE;
	    }
	    return PAGE_EXISTS;
	}		
}					