import java.awt.image.BufferedImage;
/**
 * This is an interface for all the filters. Input is a image file and output is the image with filter effect
 * @author liulu
 *
 */
public interface Filter {

	public abstract BufferedImage processImage(BufferedImage image);
}
