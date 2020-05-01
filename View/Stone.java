package view;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class Stone extends JComponent {
    private int x;
    private int y;
    private int width;
    private int height;
    private Shape shape;

    protected Stone(int x, int y, double diameter) {
        this.x = x;
        this.y = y;
        shape = new Ellipse2D.Double(x, y, diameter, diameter);
        this.height = (int) shape.getBounds2D().getHeight();
        this.width = (int) shape.getBounds2D().getWidth();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D.Double shape = new Ellipse2D.Double(x, y, width, height);
        g2.setColor(Color.BLACK);
        g2.fill(shape);
        g2.setColor(Color.BLACK);
        g2.draw(shape);
    }
}
