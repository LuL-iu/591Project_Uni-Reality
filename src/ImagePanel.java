import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Add Image to Panel
 */

public class ImagePanel extends JPanel{

    private BufferedImage image;

    public ImagePanel() {
       try {                
    	  System.out.println();
          image = ImageIO.read(new File("pikachu-lets-go.jpg"));
       } catch (IOException ex) {
            // handle exception...
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }

}