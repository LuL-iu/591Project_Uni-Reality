import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
/**
 * this class to test greyfilter class
 * 
 *
 */
class Test_GreyFilter {
	private Filter gf;
	@Test
	void test() {
		gf = new GreyFilter();
		try {
			//create image
			BufferedImage img = ImageIO.read(new File("example.jpg"));
			int width = img.getWidth();
			int height = img.getHeight();
			double expected = 5.0;
			int x = width/2;
			int y = height/2;
			//turn image to grey
			BufferedImage imgGrey = gf.processImage(img);
			//get grey image pixel rgb 
			int p = imgGrey.getRGB(x,y);
			int r = (p>>16)&0xff;
			int g = (p>>8)&0xff;
			int b = p&0xff;
			boolean value = false;
			//test whether the pixel is grey(r = g = b)
			if(r == g && r == b && g == b) {
				value = true;
			}
			assertEquals(true, value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
