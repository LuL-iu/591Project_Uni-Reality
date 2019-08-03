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
    public BufferedImage resize(BufferedImage img, int scaledWidth, int scaledHeight){
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
    public  BufferedImage resizeByPercent(BufferedImage img, double percent) {
        int scaledWidth = (int) (img.getWidth() * percent);
        int scaledHeight = (int) (img.getHeight() * percent);
        BufferedImage outputImage = resize(img, scaledWidth, scaledHeight);
        return outputImage; 
    }
 
    public BufferedImage FitImagetoFrame(BufferedImage img, int frameW, int frameH) {
    	int w = img.getWidth();
    	int h = img.getHeight();
    	double wRatio = (double)frameW/w;
    	double hRatio = (double)frameH/h;
    	double Ratio = wRatio < hRatio ? wRatio : hRatio;
    	BufferedImage outputImage = resizeByPercent(img, Ratio);
    	return outputImage;
    }
    
    public BufferedImage resizeToOneSide(BufferedImage img, int frameW, int frameH) {
    	int w = img.getWidth();
    	int h = img.getHeight();
    	double wRatio = (double)frameW/w;
    	double hRatio = (double)frameH/h;
    	double Ratio = wRatio < hRatio ? hRatio : wRatio;
    	BufferedImage outputImage = resizeByPercent(img, Ratio);
    	return outputImage;
    }
}