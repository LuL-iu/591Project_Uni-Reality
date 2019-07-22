import java.awt.image.BufferedImage;

public class GreyFilter implements Filter {
	public BufferedImage processImage(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
			    int p = img.getRGB(x,y);

			    int a = (p>>24)&0xff;
			    int r = (p>>16)&0xff;
			    int g = (p>>8)&0xff;
			    int b = p&0xff;
				 //calculate average
				int avg = (r+g+b)/3;
				
				//replace RGB value with avg
				    p = (a<<24) | (avg<<16) | (avg<<8) | avg;
				
				    img.setRGB(x, y, p);
			}
		}
		return img;
	}
}
