import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * this class implements filter class can merge two image together, according to the tool image pixel average value, change main image pixel 
 * 
 *
 */
public class MergeFilter implements Filter {
	/**
	 * input main image
	 */
	public BufferedImage MergeImage(BufferedImage image, BufferedImage toolImg) {
		int h = image.getHeight();
		int w = image.getWidth();
		Scale sca = new Scale();
		int i = 200;
		int j = 200;
		int m = 200;
		//scale the the tool image to match the size of the main image, keep same proportion
		toolImg = sca.resizeToOneSide(toolImg, w, h);
		BufferedImage outputImg = image;
		//loop every pixel in the two images
		for(int y = 0; y < h; y++){
			for(int x = 0; x < w; x++){
				int p = toolImg.getRGB(x, y);
				int a = (p>>24)&0xff;
				int r = (p>>16)&0xff;
				int g = (p>>8)&0xff;
				int b = p & 0xff;
				// get the average of the tool Image
				int avg = (a+r+g)/3;
				int p2 = image.getRGB(x, y);
				int a2 = (p2>>24)&0xff;
				int r2 = (p2>>16)&0xff;
				int g2 = (p2>>8)&0xff;
				int b2 = p2 & 0xff;
				//get the average of the main image
				int avg2 = (a2+r2+g2)/3;
				int p3 = (a| (r2+i)/2 <<16|avg2<<8|avg2);
			    int p4 = (a| avg2<<16|avg2<<8|avg2);
			    int p5 = (a| r <<16|(g2 +m)/2<<8|b2);
			    //if this pixel avg < 50, make this pixel red value change
				if(avg < 50) {					
					outputImg.setRGB(x, y, p3);
					i = i+1;
					if(i == 255) {
						i = 200;
					}
				}
			    //if this pixel avg > 50 & avg < 100, make this part grey
				else if(avg > 50 && avg < 100){	
					outputImg.setRGB(x, y, p4);
					j = j-1;
					if(j == 0) {
						j = 200;
					}
				}
				//if this pixel avg < 150, make this pixel green value change
				else if(avg > 100 && avg < 150){
					outputImg.setRGB(x, y, p5);
					m = m+1;
					if(m == 255) {
						m = 200;
					}
				}
			}
		}
		return outputImg;
	}

	@Override
	public BufferedImage processImage(BufferedImage image) {
		// TODO Auto-generated method stub
		return null;
	};
}