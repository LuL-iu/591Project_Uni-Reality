import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
 
public class gifPanel extends JPanel {
 
  Image image;
 
  public gifPanel() {
    image = Toolkit.getDefaultToolkit().createImage("giphy.gif");
    System.out.print("add");
  }
 
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (image != null) {
      g.drawImage(image, 0, 0, this);

    }
  }
  
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
 
      @Override
      public void run() {
        JFrame frame = new JFrame();
        frame.add(new gifPanel());
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
      }
    });
  }
}