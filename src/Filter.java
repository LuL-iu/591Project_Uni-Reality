import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
/**
 * This is an interface for all the filters. Input is a image file and output is the image with filter effect
 * 
 *
 */
public interface Filter {

	public abstract BufferedImage processImage(BufferedImage image);
		
		
}
