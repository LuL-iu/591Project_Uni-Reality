import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
/**
 * this class is used for testing image applying grey filter and then add image brightness
 * @author liulu
 *
 */
class Test_GreyFilterAndAddBrightness {
	private Filter gf;
	private Filter bf;
	@Test
	void test() {
		gf = new GreyFilter();
		bf = new BrightnessFilter();
		try {
			//create image
			boolean value = false;
			BufferedImage img = ImageIO.read(new File("example.jpg"));
			int width = img.getWidth();
			int height = img.getHeight();
			double expected = 5.0;

			//turn image to grey
			BufferedImage processImage = gf.processImage(img);
			int x = width/2;
			int y = height/2;
			int p = processImage.getRGB(x,y);
			int r = (p>>16)&0xff;
			int g = (p>>8)&0xff;
			int b = p&0xff;
			//add image brightness by applying brightness filter
			processImage = ((BrightnessFilter) bf).processImageWithValue(processImage, 5);
			int p2 = processImage.getRGB(x,y);
			int r2 = (p2>>16)&0xff;
			int g2 = (p2>>8)&0xff;
			int b2 = p2&0xff;
			//check whether r2 = g2 = b2 and r2 has been add 5 
			if(r2 ==  g2 && r2 == b2 && r2 == r+5 ) {
				value = true;
			}
			assertEquals(true, value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
