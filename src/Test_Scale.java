import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

class Test_Scale {
	private Scale s;
	@Test
	public void processImage() {
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
			// scale the image
			BufferedImage imgScale = s.FitImagetoFrame(img, 500, 500);
			// get height and width of image
		
			int newW = imgScale.getWidth();
			int newH = imgScale.getHeight();
			// test whether the image is scaled
			if (width > height) {
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
