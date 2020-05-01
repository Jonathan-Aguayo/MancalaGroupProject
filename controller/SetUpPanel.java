package controller;

import java.awt.Container;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.MancalaModel;
import view.BoardView;
import view.StyleButton;

public class SetUpPanel extends JPanel implements ChangeListener {

    private MancalaModel model;
    private BoardView board;
    private JLabel selectStoneLabel;
    private JComboBox<Integer> stoneBox;
    private JLabel selectStyleLabel;
    private StyleButton prev;
    private StyleButton next;
    private StyleButton back;
    private StyleButton select;

    public SetUpPanel(Container p, CardLayout card, MancalaModel m) {
        model = m;

        setOpaque(false);
        setLayout(new BorderLayout());

        selectStoneLabel = new JLabel("Number of stones:");
        stoneBox = new JComboBox<>();
        for (int i = model.MIN_INIT_STONES; i <= model.MAX_INIT_STONES; i++) {
            stoneBox.addItem(i);
        }
        JPanel northPanel = new JPanel();
        northPanel.setOpaque(false);
        northPanel.add(selectStoneLabel);
        northPanel.add(stoneBox);

        selectStyleLabel = new JLabel("Style: " + model.getCurrentStyle().getStyleName());
        board = new BoardView(model);
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(selectStyleLabel, BorderLayout.NORTH);
        centerPanel.add(board, BorderLayout.CENTER);

        prev = new StyleButton("<");
        prev.addActionListener(e -> {
            model.previousStyle();
        });
        JPanel westPanel = new JPanel(new GridBagLayout());
        westPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(0, 5, 0, 5);
        westPanel.add(prev, gbc);

        next = new StyleButton(">");
        next.addActionListener(e -> {
            model.nextStyle();
        });
        JPanel eastPanel = new JPanel(new GridBagLayout());
        eastPanel.setOpaque(false);
        gbc.insets = new Insets(0, 5, 0, 5);
        eastPanel.add(next, gbc);

        back = new StyleButton("Back");
        back.addActionListener(event -> {
            card.show(p, PanelName.MENU.getName());
        });
        select = new StyleButton("Select");
        select.addActionListener(event -> {
            model.setStoneAmount((Integer) stoneBox.getSelectedItem());
            model.newGame();
            card.show(p, PanelName.GAME.getName());
        });
        JPanel southPanel = new JPanel();
        southPanel.setOpaque(false);
        southPanel.add(back);
        southPanel.add(select);
        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(westPanel, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);
        add(southPanel, BorderLayout.SOUTH);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int fontSize = Math.min(getWidth(), getHeight());
        selectStoneLabel.setFont(new Font("Serif", Font.PLAIN, (int) (fontSize * 0.05)));
        stoneBox.setFont(new Font("Serif", Font.PLAIN, (int) (fontSize * 0.04)));
        selectStyleLabel.setFont(new Font("Serif", Font.PLAIN, (int) (fontSize * 0.08)));
        prev.setFont(new Font("Serif", Font.BOLD, (int) (fontSize * 0.1)));
        next.setFont(new Font("Serif", Font.BOLD, (int) (fontSize * 0.1)));
        back.setFont(new Font("Serif", Font.BOLD, (int) (fontSize * 0.05)));
        select.setFont(new Font("Serif", Font.BOLD, (int) (fontSize * 0.05)));
        selectStyleLabel.setText("Style: " + model.getCurrentStyle().getStyleName());
        board.setBoardStyle(model.getCurrentStyle());
    }
}