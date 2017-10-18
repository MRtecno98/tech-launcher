package tecno.launcher.objects.panels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import tecno.launcher.objects.misc.JBounds;

/**
 * This class is a JPanel contains Image
 * @author MRtecno98
 * @version 1.0.0, 08/20/2017
 */
@SuppressWarnings("serial")
public class ImagePanel extends JPanel {
	
	/** The Image Object */
    private BufferedImage image;
    /** The width of Image */
    int width;
    /** The height of Image */
    int height;
    int x;
    int y;
    
    /**
     * Construct the frame and resize the image at indicated misures
     * @param path The path of Image File
     * @param width The width of image In-Frame
     * @param height The height of Image In-Frame
     */
    public ImagePanel(String path , JBounds bounds) {
       try {                
          image = ImageIO.read(new File(path));
       } catch (IOException ex) {
            ex.printStackTrace();
       }
       
       this.width = bounds.getWidth();
       this.height = bounds.getHeight();
       this.x = bounds.getX();
       this.y = bounds.getY();
    }
    
    public ImagePanel(URL url , JBounds bounds) {
    	try {                
            image = ImageIO.read(url);
         } catch (IOException ex) {
              ex.printStackTrace();
         }
         
         this.width = bounds.getWidth();
         this.height = bounds.getHeight();
         this.x = bounds.getX();
         this.y = bounds.getY();
    }
    
    /**
     * Paint Graphics g
     * @param g The Graphics to paint
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, x, y, width, height, this);            
    }

}