import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
/**
 * testing image applying voronoi filter and then decrease brightness
 * @author liulu
 *
 */
class Test_VoronoiFilterAndDeductBrightness {

	private VoronoiFilter vf;
	private BrightnessFilter bf;
	@Test
	void test() {
		vf = new VoronoiFilter();
		bf = new BrightnessFilter();
		try {
			//create image
			BufferedImage img = ImageIO.read(new File("example.jpg"));
			//apply voronoi filter
			BufferedImage processImage = vf.processImage(img);
			//decrease image brightness by 5
			processImage = bf.processImageWithValue(processImage, -5);
			
			boolean value = false;	
			int width = img.getWidth();
			int height = img.getHeight();
			///check whether the image get the right rgb value after applying voronoi and decrease brightness
			int p = img.getRGB(10, 10);

		    int[] px = vf.px;
		    int[] py = vf.py;
		    int[] color = vf.color;
		    int cells = vf.cells;
		    int n = 0;
		    
		    for(int i = 0; i < cells; i ++) {
		    	if(distance(10, px[i], 10, py[i]) < distance(10, px[n], 10, py[n])) {
		    			n = i;
		    	}
		    }
		    
		    
			int p2 = processImage.getRGB(0, 0);
			int r2 = (p2>>16)&0xff;
			int g2 = (p2>>8)&0xff;
			int b2 = p2&0xff;
             
			int p3 = color[n];
			int r3 = (p3>>16)&0xff;
			int g3 = (p3>>8)&0xff;
			int b3 = p3&0xff;
			
			System.out.println(r2);
			System.out.println(r3);
			if( r2 == r3 - 5 && g2 == g3-5 && b2 == b3 -5) {
				value = true;
			}
			
			assertEquals(true, value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	double distance(int x1, int x2, int y1, int y2) {
		double d;
	    d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)); // 
	  	return d;
	}

}
