
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class ContrastFilter extends AbstractExtentFilter  {

   private float scaleFactor=1.2f;

    @Override
    public BufferedImage processImageWithValue(BufferedImage imgsrc, Object value) {
        int offset = (int) value;
        RescaleOp rescale = new RescaleOp(scaleFactor, offset, null);
        return rescale.filter(imgsrc,null);//(sourse,destination)
    }
}
