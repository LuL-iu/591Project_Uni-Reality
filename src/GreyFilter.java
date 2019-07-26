import java.awt.image.BufferedImage;
//This class implements filter class, has function processImage, can return grey image
public class GreyFilter implements Filter {
	public BufferedImage processImage(BufferedImage img) {
		BufferedImage imgGrey = img;
		int width = imgGrey.getWidth();
		int height = imgGrey.getHeight();
		//running through all the pixel in image, and make all the pixel to grey color(r=g=b=(r+g+b)/3)
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
			    int p = imgGrey.getRGB(x,y);
			    int a = (p>>24)&0xff;
			    int r = (p>>16)&0xff;
			    int g = (p>>8)&0xff;
			    int b = p&0xff;
				 //calculate average
				int avg = (r+g+b)/3;
				
				//replace RGB value with avg
			    p = (a<<24) | (avg<<16) | (avg<<8) | avg;
			    
			    imgGrey.setRGB(x, y, p);
			}
		}
		return imgGrey;
	}
}
