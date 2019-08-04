import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MergeFilter implements Filter {
	
	
	public BufferedImage processImage(BufferedImage image) {
		File f = new File("Flowers" + ".jpg");
		try {
			BufferedImage toolImg = ImageIO.read(f);	
			int h = image.getHeight();
			int w = image.getWidth();
			Scale sca = new Scale();
			int i = 200;
			int j = 200;
			int m = 200;
			toolImg = sca.resizeToOneSide(toolImg, w, h);
			BufferedImage outputImg = image;
			for(int y = 0; y < h; y++){
				for(int x = 0; x < w; x++){
				int p = toolImg.getRGB(x, y);
				int a = (p>>24)&0xff;
				int r = (p>>16)&0xff;
				int g = (p>>8)&0xff;
				int b = p & 0xff;
				int p2 = image.getRGB(x, y);
				int a2 = (p2>>24)&0xff;
				int r2 = (p2>>16)&0xff;
				int g2 = (p2>>8)&0xff;
				int b2 = p2 & 0xff;
				int avg = (a2+r2+g2)/3;
				p2 = (a| (r2+i)/2 <<16|avg<<8|avg);
			    int p3 = (a| avg<<16|avg<<8|avg);
			    int p4 = (a| r <<16|(g2 +m)/2<<8|b2);
				if(r < 100) {					
					outputImg.setRGB(x, y, p2);
					i = i+1;
					if(i == 255) {
						i = 200;
					}
				}
				else if(g > 100){	
					outputImg.setRGB(x, y, p3);
					j = j-1;
					if(j == 0) {
						j = 200;
					}
				}
				else if(b > 100) {
					outputImg.setRGB(x, y, p4);
					m = m+1;
					if(m == 255) {
						m = 200;
					}
				}
			}
			}
			return outputImg;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image; 
	};
}