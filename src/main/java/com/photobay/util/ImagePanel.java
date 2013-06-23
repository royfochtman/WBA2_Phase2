package main.java.com.photobay.util;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

	private static final long serialVersionUID = 1L;
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
            if(image.getWidth() > this.getWidth() || image.getHeight() > this.getHeight())
            {
            	double xScale = this.getWidth() / (double)image.getWidth();
            	double yScale = this.getHeight() / (double)image.getHeight();
            	double scale = 0;
            	if(xScale <= yScale)
            		scale = xScale;
            	else
            		scale = yScale;
                BufferedImage after = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
                AffineTransform at = new AffineTransform();
                at.scale(scale, scale);
                AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                		after = scaleOp.filter(image, after);
                image = after;
            }
            this.update(this.getGraphics());
         } catch (IOException ex) {
              // handle exception...
         }
    }
    
    public void clearImage()
    {
    	try
    	{
    		this.image = null;
    		this.update(this.getGraphics());
    	}
    	catch(Exception ex){}
    	
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try
        {
	        int x = 0;
	        int y = 0;
	        if(image.getWidth()<this.getWidth())
	        	x = (this.getWidth() - image.getWidth()) / 2;
	        if(image.getHeight()<this.getHeight())
	        	y = (this.getHeight() - image.getHeight()) / 2;
	        g.drawImage(image, x, y, null);
        }
        catch(Exception ex) {}
        // see javadoc for more info on the parameters       
    }

}