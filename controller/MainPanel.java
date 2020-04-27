package controller;

import java.awt.Graphics;
import java.awt.Container;
import java.awt.Color;
import java.awt.Font;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.StyleButton;

public class MainPanel extends JPanel implements ChangeListener {

    private JLabel title;
    private StyleButton play;
    private StyleButton exit;
    private JLabel author;

    public MainPanel(Container p, CardLayout card) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        title = new JLabel("Let's Mancala");
        play = new StyleButton("Play");
        play.addActionListener(event -> {
            card.show(p, PanelName.SETUP.getName());
        });
        exit = new StyleButton("Exit");
        exit.addActionListener(event -> {
            System.exit(0);
        });
        author = new JLabel("Team Orange");
        author.setForeground(new Color(255, 136, 77));
        add(title);
        add(play);
        add(exit);
        add(author);
    }

    private void setComponentSize() {
        int fontSize = Math.min(getWidth(), getHeight());
        title.setFont(new Font("Serif", Font.ITALIC, (int) (fontSize * 0.16)));
        play.setFont(new Font("Serif", Font.BOLD, (int) (fontSize * 0.05)));
        exit.setFont(new Font("Serif", Font.BOLD, (int) (fontSize * 0.05)));
        author.setFont(new Font("Serif", Font.ITALIC, (int) (fontSize * 0.08)));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = (getWidth() - title.getWidth()) / 2;
        int y = (getHeight() - (title.getHeight() + play.getHeight() + exit.getHeight())) / 2 - title.getHeight();
        title.setLocation(x, y);

        int padding = (int) (getHeight() * 0.05);
        x = (getWidth() - play.getWidth()) / 2;
        y += title.getHeight() + padding;
        play.setLocation(x, y);

        x = (getWidth() - exit.getWidth()) / 2;
        y += play.getHeight() + padding;
        exit.setLocation(x, y);

        author.setLocation(0, getHeight() - author.getHeight());
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        setComponentSize();
    }
}