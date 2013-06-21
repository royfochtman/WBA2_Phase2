package main.java.com.photobay.util;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    private BufferedImage image;

    public ImagePanel(File file) {
       try {                
          image = ImageIO.read(file);
       } catch (IOException ex) {
            // handle exception...
       }
    }
    
    public ImagePanel(){}
    
    public void setImage(File file)
    {
    	try {                
            image = ImageIO.read(file);
            this.update(this.getGraphics());
         } catch (IOException ex) {
              // handle exception...
         }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawImage(image, 0, 0, null);
        // see javadoc for more info on the parameters       
        g.drawImage(image, 0, 0, image.getWidth() /2, image.getHeight()/2, null);
    }

}