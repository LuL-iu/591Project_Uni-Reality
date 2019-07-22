import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

class GreyFilterTest {
	private Filter gf;
	@Test
	public void processImage() {
		gf = new GreyFilter();
		try {
			BufferedImage img = ImageIO.read(new File("go.jpg"));
			int width = img.getWidth();
			int height = img.getHeight();
			double expected = 5.0;
			int x = width/2;
			int y = height/2;
			BufferedImage imgGrey = gf.processImage(img);
			int p = imgGrey.getRGB(x,y);
			int r = (p>>16)&0xff;
			int g = (p>>8)&0xff;
			int b = p&0xff;
			boolean value = false;
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
