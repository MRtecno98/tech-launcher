package techlauncher.updater.objs;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    private BufferedImage image;
    int width;
    int height;

    public ImagePanel(String path , int width, int height) {
       try {                
          image = ImageIO.read(new File(path));
       } catch (IOException ex) {
            ex.printStackTrace();
       }
       
       this.width = width;
       this.height = height;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, width, height, this);            
    }

}