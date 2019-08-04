import java.awt.image.BufferedImage;

public abstract class AbstractExtentFilter implements ExtendFilter {

    @Override
    public BufferedImage processImageWithValue(BufferedImage image, Object value) {
       throw new RuntimeException("empty implementation");
    }

    @Override
    public BufferedImage processImage(BufferedImage image) {
        throw new RuntimeException("empty implementation");
    }
}
