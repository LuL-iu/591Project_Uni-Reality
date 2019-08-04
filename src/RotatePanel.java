import javax.swing.*;
import java.awt.*;

public class RotatePanel extends JPanel {

    private boolean rotating = false;
    private int degree;
    private Graphics2D g2d;
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        float x = this.getWidth() / 2.0f;
        float y = this.getHeight() / 2.0f;
        g2d.rotate(degree / 180.0 * Math.PI, x, y);
    }

    @Override
    public void paintChildren(Graphics g) {
        super.paintChildren(g2d);
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }
}
