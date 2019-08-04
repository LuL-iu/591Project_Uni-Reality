
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class ContrastFilter extends AbstractExtentFilter  {

   private float scaleFactor=1.2f;


     /**
    * This is the main method which takes the selected photo, change the contrast ;eve; of the selected picture and return a picture with amended hue.
    * @param imgsrc  this is selected photo.
    * @param value this is contrast value to be adjusted.
    * @return BufferedImage Object.
    */

    @Override
    public BufferedImage processImageWithValue(BufferedImage imgsrc, Object value) {
        int offset = (int) value;
        RescaleOp rescale = new RescaleOp(scaleFactor, offset, null);
        return rescale.filter(imgsrc,null);//(sourse,destination)
    }
}
