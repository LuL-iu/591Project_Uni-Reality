import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
/**
 * this class is used for testing image applying merge filter and add brightness
 * @author liulu
 *
 */
class Test_MergeFilterAddBrightness {
	
	private MergeFilter mf;
	private BrightnessFilter bf;
	@Test
	void test() {
		mf = new MergeFilter();
		bf = new BrightnessFilter();
		try {
			//create image
			BufferedImage img = ImageIO.read(new File("example.jpg"));
			int width = img.getWidth();
			int height = img.getHeight();
			//create too image to be merged
			BufferedImage toolImg = ImageIO.read(new File("Example_Merge.jpg"));
			Scale s = new Scale();
			toolImg = s.FitImagetoFrame(toolImg, width, height);

			int p = img.getRGB(0, 0);
			int r = (p>>16)&0xff;
			int g = (p>>8)&0xff;
			int b = p&0xff;
			int avg = (r+b+g)/3;
			
			int p1 = toolImg.getRGB(0, 0);
			int r1 = (p1>>16)&0xff;
			int g1 = (p1>>8)&0xff;
			int b1 = p1&0xff;
			int avg1 = (b1+r1+g1)/3;
			//merge two image
			BufferedImage processImage = mf.MergeImage(img, toolImg);
			//increase brightness by 5
			processImage = bf.processImageWithValue(processImage, 5);
			int p2 = processImage.getRGB(0, 0);
			int r2 = (p2>>16)&0xff;
			int g2 = (p2>>8)&0xff;
			int b2 = p2&0xff;
			boolean value = false;
			//check whether two images are merged and brightness is increased by 5
			if(avg1 < 50) {
				if(r2 == (r + 200)/2 +5) {
					value = true;
				}
			}
			else if(avg1 > 50 && avg1 < 100){	
				if(r2 == g2 && r2 == b2 && g2 == b2 && r2 == avg + 5) {
					value = true;
				}
				
			}
			else if(avg1 > 100 && avg1 < 150) {
				if(g2 == (g+200)/2 +5) {
					value = true;
				}
			}
			else {
				if(r2 == r + 5 && g2 == g +5 && b2 == b+5) {
					value = true;
				}
			}
			assertEquals(true, value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
