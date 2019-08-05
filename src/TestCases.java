import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

class TestCases {
	private GreyFilter gf;
	private BrightnessFilter bf;
	private Scale s;
	private MergeFilter mf;
	private VoronoiFilter vf;
	
	/**
	 * this method to test grey filter class
	 * 
	 *
	 */
	@Test
	void test1() {
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
	
	/**
	 * this method is used for testing image applying grey filter and then add image brightness
	 *
	 *
	 */
	@Test
	void test2() {
		gf = new GreyFilter();
		bf = new BrightnessFilter();
		try {
			//create image
			boolean value = false;
			BufferedImage img = ImageIO.read(new File("example.jpg"));
			int width = img.getWidth();
			int height = img.getHeight();
			double expected = 5.0;

			//turn image to grey
			BufferedImage processImage = gf.processImage(img);
			int x = width/2;
			int y = height/2;
			int p = processImage.getRGB(x,y);
			int r = (p>>16)&0xff;
			int g = (p>>8)&0xff;
			int b = p&0xff;
			//add image brightness by applying brightness filter
			processImage = ((BrightnessFilter) bf).processImageWithValue(processImage, 5);
			int p2 = processImage.getRGB(x,y);
			int r2 = (p2>>16)&0xff;
			int g2 = (p2>>8)&0xff;
			int b2 = p2&0xff;
			//check whether r2 = g2 = b2 and r2 has been add 5 
			if(r2 ==  g2 && r2 == b2 && r2 == r+5 ) {
				value = true;
			}
			assertEquals(true, value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * this method is used for testing image applying grey filter and then  deduct image brightness
	 *
	 *
	 */
	@Test
	void test3() {
		gf = new GreyFilter();
		bf = new BrightnessFilter();
		try {
			//create image
			boolean value = false;
			BufferedImage img = ImageIO.read(new File("example.jpg"));
			int width = img.getWidth();
			int height = img.getHeight();

	
			//turn image to grey
			BufferedImage processImage = gf.processImage(img);
			int x = width/2;
			int y = height/2;
			int p = processImage.getRGB(x,y);
			int r = (p>>16)&0xff;
			int g = (p>>8)&0xff;
			int b = p&0xff;
			//deduct brightness by 5 through brightness filter
			processImage = ((BrightnessFilter) bf).processImageWithValue(processImage, -5);
			int p2 = processImage.getRGB(x,y);
			int r2 = (p2>>16)&0xff;
			int g2 = (p2>>8)&0xff;
			int b2 = p2&0xff;
			//check whether r2 = g2 = b2 and r2 is decreased by 5 
			if(r2 ==  g2 && r2 == b2 && r2 == r-5 ) {
				value = true;
			}
			assertEquals(true, value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * this method is used for testing image applying grey filter and then scale filter
	 *
	 *
	 */	
	@Test
	void test4() {
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

	/**
	 * this method is used for testing image applying merge filter
	 *
	 *
	 */
	@Test
	void test5() {
		mf = new MergeFilter();
		try {
			//create image
			BufferedImage img = ImageIO.read(new File("example.jpg"));
			int width = img.getWidth();
			int height = img.getHeight();
			//create tool image for merging with
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
			BufferedImage processImage = mf.MergeImage(img, toolImg);
			int p2 = processImage.getRGB(0, 0);
			int r2 = (p2>>16)&0xff;
			int g2 = (p2>>8)&0xff;
			int b2 = p2&0xff;
			boolean value = false;
			//check whether two image is being merged
			if(avg1 < 50) {
				if(r2 == (r + 200)/2) {
					value = true;
				}
			}
			else if(avg1 > 50 && avg1 < 100){	
				if(r2 == g2 && r2 == b2 && g2 == b2) {
					value = true;
				}
				
			}
			else if(avg1 > 100 && avg1 < 150) {
				if(g2 == (g+200)/2) {
					value = true;
				}
			}
			else {
				if(p2 == p) {
					value = true;
				}
			}
			assertEquals(true, value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * this method is used for testing image applying merge filter and add brightness
	 * 
	 *
	 */
	@Test
	void test6() {
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
			processImage = ((BrightnessFilter) bf).processImageWithValue(processImage, 5);
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
	
	/**
	 * this method is used for testing image applying merge filter and decrease brightness
	 * 
	 *
	 */
	@Test
	void test7() {
		mf = new MergeFilter();
		bf = new BrightnessFilter();
		try {
			//create image
			BufferedImage img = ImageIO.read(new File("example.jpg"));
			int width = img.getWidth();
			int height = img.getHeight();
			
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
			//decreasae image brightness by 5
			processImage = bf.processImageWithValue(processImage, -5);
			int p2 = processImage.getRGB(0, 0);
			int r2 = (p2>>16)&0xff;
			int g2 = (p2>>8)&0xff;
			int b2 = p2&0xff;
			boolean value = false;
			//check whether two images are merged and merged image brightness decreased by 5
			if(avg1 < 50) {
				if(r2 == (r + 200)/2 +5) {
					value = true;
				}
			}
			else if(avg1 > 50 && avg1 < 100){	
				if(r2 == g2 && r2 == b2 && g2 == b2 && r2 == avg - 5) {
					value = true;
				}
				
			}
			else if(avg1 > 100 && avg1 < 150) {
				if(g2 == (g+200)/2-5) {
					value = true;
				}
			}
			else {
				if(r2 == r - 5 && g2 == g - 5 && b2 == b-5) {
					value = true;
				}
			}
			assertEquals(true, value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * this method is used for testing image applying grey filter and then merge filter
	 * 
	 *
	 */
	@Test
	void test8() {
		mf = new MergeFilter();
		gf = new GreyFilter();
		try {
			//create image
			BufferedImage img = ImageIO.read(new File("example.jpg"));
			int width = img.getWidth();
			int height = img.getHeight();
			//create tool image to be merged
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
			//make orginal image grey
			BufferedImage processImage = gf.processImage(img);
			//merge grey image with tool image
			processImage = mf.MergeImage(processImage, toolImg);
			int p2 = processImage.getRGB(0, 0);
			int r2 = (p2>>16)&0xff;
			int g2 = (p2>>8)&0xff;
			int b2 = p2&0xff;
			boolean value = false;
			//check whether original image become grey and merged with tool image
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
	
	/**
	 * this method is used for testing image applying scale filter and then merge filter
	 * 
	 *
	 */
	@Test
	void test9() {
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
	
	
	/**
	 * this method is used for testing image applying scale filter
	 * 
	 *
	 */
	@Test
	public void test10() {
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
	
	/**
	 * this is used for testing image applying voronoi filter
	 * 
	 *
	 */
	@Test
	void test11() {
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
			int p2 = processImage.getRGB(10, 10);			
			if(p2 == color[n]) {
				value = true;
			}
			
			assertEquals(true, value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * testing image applying voronoi filter and then increase brightness
	 *
	 *
	 */
	@Test
	void test12() {
		vf = new VoronoiFilter();
		bf = new BrightnessFilter();
		try {
			//create image
			BufferedImage img = ImageIO.read(new File("example.jpg"));
			//apply voronoi filter
			BufferedImage processImage = vf.processImage(img);
			//increase image brightness by 5
			processImage = bf.processImageWithValue(processImage, 5);
			boolean value = false;	
			int width = img.getWidth();
			int height = img.getHeight();
		    
			//check whether the image get the right rgb value after applying voronoi and add brightness
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
			int p2 = processImage.getRGB(10, 10);
			int r2 = (p2>>16)&0xff;
			int g2 = (p2>>8)&0xff;
			int b2 = p2&0xff;
             
			int p3 = color[n];
			int r3 = (p3>>16)&0xff;
			int g3 = (p3>>8)&0xff;
			int b3 = p3&0xff;
			
			if( r2 == r3 + 5 && g2 == g3+5 && b2 == b3 +5) {
				value = true;
			}			
			assertEquals(true, value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * testing image applying voronoi filter and then decrease brightness
	 * 
	 *
	 */
	@Test
	void test13() {
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
			int p2 = processImage.getRGB(10, 10);
			int r2 = (p2>>16)&0xff;
			int g2 = (p2>>8)&0xff;
			int b2 = p2&0xff;
             
			int p3 = color[n];
			int r3 = (p3>>16)&0xff;
			int g3 = (p3>>8)&0xff;
			int b3 = p3&0xff;
			if( r2 < r3) {
				value = true;
			}

			assertEquals(true, value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * this is used for testing image applying voronoi filter and then grey filter
	 * 
	 *
	 */
	@Test
	void test14() {
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
	
	/**
	 * this is used for testing image applying scale filter and then voronoi filter
	 * 
	 *
	 */
	@Test
	void test15() {
		vf = new VoronoiFilter();
		try {
			//create image
			BufferedImage img = ImageIO.read(new File("example.jpg"));
			//scale image to fit frame
			Scale s = new Scale();
			//apply voronoi filter
			BufferedImage processImage = s.FitImagetoFrame(img, 500, 500);
			processImage = vf.processImage(processImage);
			boolean value = false;
			int width = img.getWidth();
			int height = img.getHeight();
			
		    int[] px = vf.px;
		    int[] py = vf.py;
		    int[] color = vf.color;
		    int cells = vf.cells;
		    int n = 0;
		    //check whether image is scaled to right size and applied with voronoi filter
		    for(int i = 0; i < cells; i ++) {
		    	if(distance(10, px[i], 10, py[i]) < distance(10, px[n], 10, py[n])) {
		    			n = i;
		    	}
		    }
			int p2 = processImage.getRGB(10, 10);
			if (width > height) {
				if (processImage.getWidth() == 500 && p2 == color[n]) {
					value = true;
				}
			} 
			else {
				if (processImage.getHeight()  == 500 && p2 == color[n]) {
					value = true;
				}
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
