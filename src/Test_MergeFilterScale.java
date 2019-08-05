import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
/**
 * this class is used for testing image applying scale filter and then merge filter
 * @author liulu
 *
 */
class Test_MergeFilterScale {

	private MergeFilter mf;
	@Test
	public void processImage() {
		mf = new MergeFilter();
		try {
			//create image
			BufferedImage img = ImageIO.read(new File("example.jpg"));
			//scale image to fit to frame size
			Scale s = new Scale();
			BufferedImage processImage = s.FitImagetoFrame(img, 500, 500);
			int width = img.getWidth();
			int height = img.getHeight();
			//create tool image
			BufferedImage toolImg = ImageIO.read(new File("Example_Merge.jpg"));
			
			int p = processImage.getRGB(0, 0);
			int r = (p>>16)&0xff;
			int g = (p>>8)&0xff;
			int b = p&0xff;
			
			
			int p1 = toolImg.getRGB(0, 0);
			int r1 = (p1>>16)&0xff;
			int g1 = (p1>>8)&0xff;
			int b1 = p1&0xff;
			int avg1 = (b1+r1+g1)/3;
			//merge two image
			processImage = mf.MergeImage(processImage, toolImg);
			int p2 = processImage.getRGB(0, 0);
			int r2 = (p2>>16)&0xff;
			int g2 = (p2>>8)&0xff;
			int b2 = p2&0xff;
			boolean value = true;
			//check whether image is be scale to fit the screen and merged with tool image 
			if (width > height) {
				if (processImage.getWidth() != 500) {
					value = false;
				}
			} 
			else {
				if (processImage.getHeight()  != 500) {
					value = false;
				}
			}
			if(avg1 < 50) {
				if(r2 != (r + 200)/2) {
					value = false;
				}
			}
			else if(avg1 > 50 && avg1 < 100){	
				if(r2 != g2 || r2 != b2 || g2 != b2) {
					value = false;
				}
				
			}
			else if(avg1 > 100 && avg1 < 150) {
				if(g2 != (g+200)/2) {
					value = false;
				}
			}
			else {
				if(p2 != p) {
					value = false;
				}
			}
			assertEquals(true, value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
