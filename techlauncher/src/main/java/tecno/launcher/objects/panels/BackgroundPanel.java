package tecno.launcher.objects.panels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {

    private BufferedImage backgroundImage;

    public BackgroundPanel(URL url) throws IOException {
        backgroundImage = ImageIO.read(url);
    }
    
    public BackgroundPanel(File f) throws IOException {
    	backgroundImage = ImageIO.read(f);
    }

    @Override
    public void paintComponent(Graphics g) {
        int width = this.getWidth();
        int height = this.getHeight();
        for(int y = 0; y < height; y += backgroundImage.getHeight(null)) {
            for(int x = 0; x < width; x += backgroundImage.getWidth(null)) {
                g.drawImage(backgroundImage,x,y,null);
            }
        }
    }
}