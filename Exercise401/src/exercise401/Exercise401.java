package exercise401;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.RescaleOp;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Exercise401 extends JFrame implements ActionListener {

	private ImagePanel imageSrc, imageDst;
	
	public static void main(String[] args){
		JFrame frame = new Exercise401();
		frame.setTitle("Exercise401");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public Exercise401() {
		JMenuBar toolbar = new JMenuBar();
		setJMenuBar(toolbar);
		
		JMenu menu = new JMenu("Ficheiro");		
		
		JMenuItem item = new JMenuItem("Guardar imagem...");
		item.addActionListener(this);
		menu.add(item);
		
		item = new JMenuItem("Sair");
		item.addActionListener(this);
		menu.add(item);
		
		toolbar.add(menu);
		
		menu = new JMenu("Operações");
		
		item = new JMenuItem("Inverter horizontal");
		item.addActionListener(this);
		menu.add(item);
		
		item = new JMenuItem("Inverter vertical");
		item.addActionListener(this);
		menu.add(item);
		
		item = new JMenuItem("Desfocar");
		item.addActionListener(this);
		menu.add(item);
		
		toolbar.add(menu);
		
		Container cp = this.getContentPane();
		cp.setLayout(new FlowLayout());
		
		imageSrc = new ImagePanel();
		imageDst = new ImagePanel();
		cp.add(imageSrc);
		cp.add(imageDst);			
        
		//Escolha pré-definida        
		URL url = getClass().getClassLoader().getResource("images/alphabet.jpeg");
    	BufferedImage bi = null;
    	
    	try {    		
    		bi = ImageIO.read(url);
    	} catch (IOException ex) {
    		ex.printStackTrace();
    	}
    	
        imageSrc.setImage(bi);
	}
 
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc = new JFileChooser(); 
        String escolha = e.getActionCommand();              

        if ("Guardar imagem...".equals(escolha)) {
            int retval = jfc.showSaveDialog(this);
            if (retval == JFileChooser.APPROVE_OPTION) {
                try {
                    ImageIO.write(imageDst.getImg(), "png", jfc.getSelectedFile());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else if ("Sair".equals(escolha)) {
            System.exit(0);
        } else {
            process(escolha);
        }		
	}
	
	public void process(String opName) {
		
		BufferedImageOp op = null;

		if (opName.equals("Inverter horizontal")) {
            BufferedImage bi = InverterHorizontal(imageSrc.getImg());
            imageDst.setImage(bi);
            pack();
		
		} else if (opName.equals("Inverter vertical")) {
            BufferedImage bi = InverterVertical(imageSrc.getImg());
            imageDst.setImage(bi);
            pack();

        } else if (opName.equals("Desfocar")) {        	
        	float[] data={
                    0.111f, 0.111f, 0.111f,
                    0.111f, 0.111f, 0.111f,
                    0.111f, 0.111f, 0.111f};
            Kernel ker = new Kernel(3,3,data);
            op = new ConvolveOp(ker);
        	BufferedImage bi = op.filter(imageSrc.getImg(), null);
            imageDst.setImage(bi);
            pack();	                      

        }        			
	}

	public BufferedImage InverterHorizontal(BufferedImage bi) {
	        BufferedImage temp = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
	        Graphics2D g2 = temp.createGraphics();
	        g2.drawImage(bi, 0, 0, bi.getWidth(), bi.getHeight(), bi.getWidth(), 0, 0, bi.getHeight(), null);
	        g2.dispose();
	        return temp;
	    }
	  
  	public BufferedImage InverterVertical(BufferedImage bi) {
	        BufferedImage temp = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
	        Graphics2D g2 = temp.createGraphics();
	        g2.drawImage(bi, bi.getWidth(), bi.getHeight(), 0, 0, bi.getWidth(), 0, 0, bi.getHeight(), null);
	        g2.dispose();
	        return temp;
	    }
			
}

class ImagePanel extends JPanel{
	
	BufferedImage img = null;
	
	public ImagePanel(){		
		img = null;
		setPreferredSize(new Dimension(256,256));
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		
		if (img != null) {
			g2.drawImage(img, 0, 0, this);
		} else {
			g2.drawRect(0, 0, getWidth() - 1, getHeight() -1);
		}						
	}
	
    public void setImage(BufferedImage bi) {
        img = bi;
        setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
        invalidate();
        repaint();
    }

    public BufferedImage getImg() {
    	return img;
    }
}