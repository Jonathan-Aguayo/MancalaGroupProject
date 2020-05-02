package controller;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.MancalaModel;
import view.BoardView;
import view.StyleButton;

public class GamePanel extends JPanel implements ChangeListener {
    private static final long serialVersionUID = 1L;

    private Container container;
    private CardLayout card;
    private MancalaModel model;
    private BoardView board;
    private JLabel turnLabel;
    private StyleButton redo;
    private StyleButton endTurn;
    private StyleButton quit;

    public GamePanel(Container p, CardLayout c, MancalaModel m) {
        container = p;
        card = c;
        model = m;

        setLayout(new BorderLayout());
        setOpaque(false);

        turnLabel = new JLabel();
        turnLabel.setHorizontalAlignment(JLabel.CENTER);
        board = new BoardView(model);
        model.addChangeListener(board);
        redo = new StyleButton("");
        redo.addActionListener(e -> {
            model.redo();
        });
        endTurn = new StyleButton("Endturn");
        endTurn.addActionListener(e -> {
            model.endTurn();
        });
        JPanel bottomcenterPanel = new JPanel();
        bottomcenterPanel.setOpaque(false);
        bottomcenterPanel.add(redo);
        bottomcenterPanel.add(endTurn);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(turnLabel, BorderLayout.NORTH);
        centerPanel.add(board, BorderLayout.CENTER);
        centerPanel.add(bottomcenterPanel, BorderLayout.SOUTH);

        quit = new StyleButton("Quit");
        quit.addActionListener(event -> {
            card.show(container, PanelName.QUIT.getName());
        });
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setOpaque(false);
        bottomPanel.add(quit);

        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int fontSize = Math.min(getWidth(), getHeight());
        turnLabel.setFont(new Font("Serif", Font.PLAIN, (int) (fontSize * 0.1)));
        redo.setFont(new Font("Serif", Font.BOLD, (int) (fontSize * 0.05)));
        endTurn.setFont(new Font("Serif", Font.BOLD, (int) (fontSize * 0.05)));
        quit.setFont(new Font("Serif", Font.BOLD, (int) (fontSize * 0.05)));

        turnLabel.setText("Player " + (model.isP1turn() ? "A" : "B") + "'s turn");
        board.setBoardStyle(model.getCurrentStyle());
        redo.setText("Redo: " + model.getRedoCount());
        redo.setEnabled(model.getRedoCount() > 0);
        endTurn.setEnabled(!(model.getTurnCount() > 0));
        if (!(model.getTurnCount() < 0) && model.hasEmptyPits()) {
            card.show(container, PanelName.RESULT.getName());
        }
    }

}