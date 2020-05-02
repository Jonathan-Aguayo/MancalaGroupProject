package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JComponent;

public class Stone extends JComponent {
    private static final long serialVersionUID = 1L;

    public Stone() {
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int size = (int) (getWidth() * 0.4);
        int x = (getWidth() - size) / 2;
        int y = (getHeight() - size) / 2;
        g2.setColor(Color.BLACK);
        g2.fillOval(x, y, size, size);
    }
}
