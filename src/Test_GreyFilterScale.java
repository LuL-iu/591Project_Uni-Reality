import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

class Test_GreyFilterScale {
	private Filter gf;
	private Scale s;
	@Test
	public void processImage() {
		gf = new GreyFilter();
		s = new Scale();
		try {
			boolean value = true;
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
			//test whether the pixel is grey(r = g = b)
			if(r != g || r != b || g != b) {
				value = false;
			}
			// scale the image
			BufferedImage imgScale = s.FitImagetoFrame(imgGrey, 500, 500);
			// get height and width of image
			int oldW = imgGrey.getWidth();
			int oldH = imgGrey.getHeight();
			int newW = imgScale.getWidth();
			int newH = imgScale.getHeight();
			// test whether the image is scaled
			if (oldW > oldH) {
				if (newW != 500) {
					value = false;
				}
			} else {
				if (newH != 500) {
					value = false;
				}
			}
			// check the result
			assertEquals(true, value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
