import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
 
/**
 * This program demonstrates how to resize an image.
 *
 * @author www.codejava.net
 *
 */
public class Scale {
	/**
	 *  Resizes an image to a absolute width and height (the image may not be
     * proportional)
	 * @param img
	 * @param scaledWidth
	 * @param scaledHeight
	 * @return
	 */
    public static BufferedImage resize(BufferedImage img, int scaledWidth, int scaledHeight){
        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, img.getType());
 
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(img, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
        return outputImage;
    }
 
    /**
     * Resizes an image by a percentage of original size (proportional).
     * @param img
     * @param percent
     * @return
     */
    public static BufferedImage resize(BufferedImage img, double percent) {
        int scaledWidth = (int) (img.getWidth() * percent);
        int scaledHeight = (int) (img.getHeight() * percent);
        BufferedImage outputImage = resize(img, scaledWidth, scaledHeight);
        return outputImage; 
    }
 
}