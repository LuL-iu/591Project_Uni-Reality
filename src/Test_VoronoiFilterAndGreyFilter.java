import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
/**
 * this class is used for testing image applying voronoi filter and then grey filter
 * @author liulu
 *
 */
class Test_VoronoiFilterAndGreyFilter {
	
private VoronoiFilter vf;
private GreyFilter gf;
@Test
void test() {
	vf = new VoronoiFilter();
	gf = new GreyFilter();
	try {
		//create image
		BufferedImage img = ImageIO.read(new File("example.jpg"));
		BufferedImage processImage = vf.processImage(img);
		processImage = gf.processImage(processImage);
		
		boolean value = false;	
		int width = img.getWidth();
		int height = img.getHeight();
	
		int p = img.getRGB(10, 10);

	    int[] px = vf.px;
	    int[] py = vf.py;
	    int[] color = vf.color;
	    int cells = vf.cells;
	    int n = 0;
	  
	    //check whether image is being applied by voronoi filter and turned to grey
	    for(int i = 0; i < cells; i ++) {
	    	if(distance(10, px[i], 10, py[i]) < distance(10, px[n], 10, py[n])) {
	    			n = i;
	    	}
	    }
	  
	    int p1 = color[n];
	    int r1 = (p1>>16)&0xff;
		int g1 = (p1>>8)&0xff;
		int b1 = p1&0xff;
		int avg1 = (r1+g1+b1)/3;
	    
		int p2 = processImage.getRGB(10, 10);
		int r2 = (p2>>16)&0xff;
		int g2 = (p2>>8)&0xff;
		int b2 = p2&0xff;
		
		
		if( r2 == avg1 && g2 == avg1 && b2 == avg1) {
			value = true;
		}
		assertEquals(true, value);
	}
	catch(IOException e) {
		e.printStackTrace();		
	}
}
	
double distance(int x1, int x2, int y1, int y2) {
	double d;
    d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)); // 
  	return d;
}	

}
