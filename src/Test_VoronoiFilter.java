import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
/**
 * this is used for testing image applying voronoi filter
 * @author liulu
 *
 */
class Test_VoronoiFilter {

	private VoronoiFilter vf;
	@Test
	void test() {
		vf = new VoronoiFilter();
		try {
			//create image
			BufferedImage img = ImageIO.read(new File("example.jpg"));
			//apply voronoi filter
			BufferedImage processImage = vf.processImage(img);
			boolean value = false;
			
			int width = img.getWidth();
			int height = img.getHeight();
		    //check whether the pixel on image was changed to correct rgb value after applying voronoi filter 
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
			
			if(p2 == color[n]) {
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
