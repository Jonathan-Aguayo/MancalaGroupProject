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
    private static final long serialVersionUID = 1L;

    private boolean mouseOver = false;
    private boolean mousePressed = false;
    private boolean enable = true;

    public StyleButton(String text) {
        super(text);
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);
        MouseListeners mouseListener = new MouseListeners();
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }

    @Override
    public void setEnabled(boolean b) {
        super.setEnabled(b);
        enable = b;
    }

    private class MouseListeners extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            mousePressed = true;
            repaint();
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
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setFont(getFont());
        FontMetrics metrics = g2.getFontMetrics(getFont());
        int stringWidth = metrics.stringWidth(getText());
        int stringHeight = metrics.getHeight();

        if (enable) {
            g2.setColor(new Color(255, 230, 204));
            if (mousePressed) {
                g2.setColor(new Color(253, 218, 206));
            }
            g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 20, 20);
            g2.setColor(new Color(51, 51, 51));
            if (mouseOver) {
                g2.setColor(new Color(255, 136, 77));
            }
        } else {
            g2.setColor(new Color(153, 153, 153));
        }

        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 20, 20);
        g2.drawString(getText(), (getWidth() - stringWidth) / 2, (2 * getHeight() + stringHeight) / 4);
    }
}