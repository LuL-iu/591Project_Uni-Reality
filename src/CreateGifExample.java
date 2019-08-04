

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class CreateGifExample {

    public void writeGif(ArrayList<BufferedImage> images) throws FileNotFoundException, IOException{
        BufferedImage first = images.get(0);
        ImageOutputStream output = new FileImageOutputStream(new File("example.gif"));
        GifSequenceWriter writer = new GifSequenceWriter(output, first.getType(), 250, true);
        writer.writeToSequence(first);
        
        for (int i = 0; i < images.size(); i++) {
        	BufferedImage image = images.get(i);
            writer.writeToSequence(image);
        }

        writer.close();
        output.close();
    }
}
