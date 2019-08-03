// import java.awt.Color;
import java.awt.Graphics;
// import java.awt.Graphics2D;
// import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
 
import javax.imageio.ImageIO;
import javax.swing.JFrame;
 
public class VoronoiFilter extends JFrame implements Filter {
	static double p = 3;
	static BufferedImage newImg;
    static int px[], py[], color[], height, width, cells;
    static final long serialVersionUID = 42L;
    
 
	public VoronoiFilter() {
		super("Voronoi Diagram");
	}

	static double distance(int x1, int x2, int y1, int y2) {
		double d;
	    d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)); // Euclidian
	//  d = Math.abs(x1 - x2) + Math.abs(y1 - y2); // Manhattan
	//  d = Math.pow(Math.pow(Math.abs(x1 - x2), p) + Math.pow(Math.abs(y1 - y2), p), (1 / p)); // Minkovski
	  	return d;
	}
 

	@Override
	public BufferedImage processImage(BufferedImage image) {
		BufferedImage orgImg = image;
		height = orgImg.getHeight();
		width = orgImg.getWidth();
		cells = (height + width) * 2; 
		setBounds(0, 0, width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		int n = 0;
		Random rand = new Random();
		newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		px = new int[cells];
		py = new int[cells];
		color = new int[cells];
		for (int i = 0; i < cells; i++) {
			px[i] = rand.nextInt(width);
			py[i] = rand.nextInt(height);
			color[i] = orgImg.getRGB(px[i], py[i]);
		}
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				n = 0;
				for (int i = 0; i < cells; i++) {
					if (distance(px[i], x, py[i], y) < distance(px[n], x, py[n], y)) {
						n = i;
					}
				}
				newImg.setRGB(x, y, color[n]);
			}
		}
 
		// Graphics2D g = newImg.createGraphics();
		// g.setColor(Color.BLACK);
		// for (int i = 0; i < cells; i++) {
		// 	g.fill(new Ellipse2D .Double(px[i] - 2.5, py[i] - 2.5, 5, 5));
		// }
		return newImg;
	}
}