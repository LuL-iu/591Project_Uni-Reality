import java.awt.image.BufferedImage;

public interface ExtendFilter extends Filter {

    BufferedImage processImageWithValue(BufferedImage image, Object value);
}
