package controller;

import java.awt.Container;
import java.awt.Color;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.StyleButton;

public class MainPanel extends JPanel implements ChangeListener {
    private static final long serialVersionUID = 1L;

    private JLabel title;
    private StyleButton play;
    private StyleButton exit;
    private JLabel author;

    public MainPanel(Container p, CardLayout card) {
        setOpaque(false);
        setLayout(new BorderLayout());

        title = new JLabel("Let's Mancala");
        play = new StyleButton("Play");
        play.addActionListener(event -> {
            card.show(p, PanelName.SETUP.getName());
        });
        exit = new StyleButton("Exit");
        exit.addActionListener(event -> {
            System.exit(0);
        });
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(15, 0, 0, 0);
        centerPanel.add(title, gbc);
        centerPanel.add(play, gbc);
        centerPanel.add(exit, gbc);

        author = new JLabel("Team Orange");
        author.setForeground(new Color(255, 136, 77));

        add(centerPanel, BorderLayout.CENTER);
        add(author, BorderLayout.SOUTH);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int fontSize = Math.min(getWidth(), getHeight());
        title.setFont(new Font("Serif", Font.ITALIC, (int) (fontSize * 0.16)));
        play.setFont(new Font("Serif", Font.BOLD, (int) (fontSize * 0.05)));
        exit.setFont(new Font("Serif", Font.BOLD, (int) (fontSize * 0.05)));
        author.setFont(new Font("Serif", Font.ITALIC, (int) (fontSize * 0.08)));
    }
}