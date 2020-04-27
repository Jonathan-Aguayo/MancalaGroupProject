package view;

import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.FontMetrics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StyleButton extends JButton {

    private boolean mouseOver = false;
    private boolean mousePressed = false;

    public StyleButton(String text) {
        super(text);
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);

        MouseAdapter mouseListener = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (mouseOver) {
                    mousePressed = true;
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mousePressed = false;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseOver = false;
                mousePressed = false;
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mouseOver = true;
                repaint();
            }
        };

        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setFont(getFont());
        FontMetrics metrics = g2.getFontMetrics(getFont());
        int stringWidth = metrics.stringWidth(getText());
        int stringHeight = metrics.getHeight();

        g2.setColor(new Color(242, 242, 242));
        if (mousePressed) {
            g2.setColor(new Color(253, 218, 206));
        }
        g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 20, 20);
        g2.setColor(new Color(51, 51, 51));
        if (mouseOver) {
            g2.setColor(new Color(255, 136, 77));
        }
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 20, 20);
        g2.drawString(getText(), (getWidth() - stringWidth) / 2, (2 * getHeight() + stringHeight) / 4);
    }
}