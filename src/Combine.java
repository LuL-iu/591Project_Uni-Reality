import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Combine {
	public ArrayList<BufferedImage> combineGif(ArrayList<BufferedImage> imgList, BufferedImage image, int a, int b){
		BufferedImage img = image;
		ArrayList<BufferedImage> output = new ArrayList<BufferedImage>();
		
        for(int i = 0; i < imgList.size(); i++) {
        	BufferedImage toolImage = imgList.get(i);
        	BufferedImage processImage = img;
        	Scale s = new Scale();
        	s.FitImagetoFrame(toolImage, 150, 150);
        	int w = toolImage.getHeight();
    		int h = toolImage.getHeight();
        	for(int x = a; x < a+w; x ++) {
    			for (int y = b; y < b+h; y ++) {
    			 	int p = toolImage.getRGB(x-a,y-b);
    			 	processImage.setRGB(x, y, p);
    			}
    		}
        	output.add(processImage);
            File f = new File(i + ".jpg");
            try {
				ImageIO.write(processImage, "jpg" , f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
	   return output;
	}
}
