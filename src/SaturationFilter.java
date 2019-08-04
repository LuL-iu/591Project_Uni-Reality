import java.awt.*;
import java.awt.image.BufferedImage;

public class SaturationFilter extends AbstractExtentFilter {

    /**
       * This is the main method which takes the selected photo, change the hue of the selected picture and return a picture with amended hue.
       * @param raw thid is selected photo.
       * @param value this is hue value to be adjusted.
       * @return BufferedImage Object.
       */

    @Override
    public BufferedImage processImageWithValue(BufferedImage raw, Object value) {
        int WIDTH = raw.getWidth();
        int HEIGHT = raw.getHeight();
        int iHUE;
        BufferedImage processed = new BufferedImage(WIDTH,HEIGHT,raw.getType());
//        do
//        {
//            System.out.print("Hue (0-360):");
            iHUE = (int) value;
//            if(iHUE > 360 || iHUE < 0)
//            {
//                System.out.println("Please enter a hue value between 0-360.");
//            }
//        }

//        while((iHUE > 360 || iHUE < 0));
        float hue = iHUE/360.0f;

        for(int Y=0; Y<HEIGHT;Y++)
        {
            for(int X=0;X<WIDTH;X++)
            {
                int RGB = raw.getRGB(X,Y);
                int R = (RGB >> 16) & 0xff;
                int G = (RGB >> 8) & 0xff;
                int B = (RGB) & 0xff;
                float HSV[]=new float[3];
                Color.RGBtoHSB(R,G,B,HSV);
                processed.setRGB(X,Y, Color.getHSBColor(hue,HSV[1],HSV[2]).getRGB());
            }
        }
        return processed;
    }
}
