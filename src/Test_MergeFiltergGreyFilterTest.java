import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

class Test_MergeFiltergGreyFilterTest {


	private MergeFilter mf;
	private GreyFilter gf;
	@Test
	public void processImage() {
		mf = new MergeFilter();
		gf = new GreyFilter();
		try {
			//create image
			
			BufferedImage img = ImageIO.read(new File("example.jpg"));
			int width = img.getWidth();
			int height = img.getHeight();
			
			BufferedImage toolImg = ImageIO.read(new File("Example_Merge.jpg"));

			int p = img.getRGB(0, 0);
			int r = (p>>16)&0xff;
			int g = (p>>8)&0xff;
			int b = p&0xff;
			
			
			int p1 = toolImg.getRGB(0, 0);
			int r1 = (p1>>16)&0xff;
			int g1 = (p1>>8)&0xff;
			int b1 = p1&0xff;
			int avg1 = (b1+r1+g1)/3;
			//merge two image
			BufferedImage processImage = gf.processImage(img);
			processImage = mf.MergeImage(processImage, toolImg);
			int p2 = processImage.getRGB(0, 0);
			int r2 = (p2>>16)&0xff;
			int g2 = (p2>>8)&0xff;
			int b2 = p2&0xff;
			boolean value = false;
			if(avg1 < 50) {
				if(r2 == (r + 200)/2 && g2 == b2) {
					value = true;
				}
			}
			else if(avg1 > 50 && avg1 < 100){	
				if(r2 == g2 && r2 == b2 && g2 == b2) {
					value = true;
				}
				
			}
			else if(avg1 > 100 && avg1 < 150) {
				if(g2 == (g+200)/2 && r2 == b2) {
					value = true;
				}
			}
			else {
				if(r2 == g2 && r2 == b2) {
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
