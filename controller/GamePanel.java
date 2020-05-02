package controller;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.MancalaModel;
import view.BoardView;
import view.StyleButton;

public class GamePanel extends JPanel implements ChangeListener {
    private static final long serialVersionUID = 1L;

    private MancalaModel model;
    private BoardView board;
    private JLabel turnLabel;
    private JLabel msgLabel;
    private StyleButton redo;
    private StyleButton endTurn;
    private StyleButton quit;
    private boolean isP1turn;
    private int redoCount;
    private int turnCount;
    private int p1MancalaCount;
    private int p2MancalaCount;
    private Timer time;
    private int transVal;

    public GamePanel(Container p, CardLayout card, MancalaModel m) {
        model = m;

        setLayout(new BorderLayout());
        setOpaque(false);

        turnLabel = new JLabel();
        turnLabel.setHorizontalAlignment(JLabel.CENTER);

        msgLabel = new JLabel();
        msgLabel.setHorizontalAlignment(JLabel.CENTER);
        msgLabel.setForeground(new Color(255, 136, 77, transVal));
        board = new BoardView(model);
        model.addChangeListener(board);
        redo = new StyleButton("Redo: ");
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
        centerPanel.add(msgLabel, BorderLayout.NORTH);
        centerPanel.add(board, BorderLayout.CENTER);
        centerPanel.add(bottomcenterPanel, BorderLayout.SOUTH);

        quit = new StyleButton("Quit");
        quit.addActionListener(event -> {
            card.show(p, PanelName.QUIT.getName());
        });
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setOpaque(false);
        bottomPanel.add(quit);

        add(turnLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        time = new Timer(10, e -> {
            msgLabel.setForeground(new Color(255, 136, 77, transVal));
            transVal--;
            if (transVal <= 0) {
                time.stop();
            }
        });
    }

    public void resizeFont() {
        int fontSize = Math.min(getWidth(), getHeight());
        turnLabel.setFont(new Font("Serif", Font.PLAIN, (int) (fontSize * 0.1)));
        msgLabel.setFont(new Font("Serif", Font.BOLD, (int) (fontSize * 0.08)));
        redo.setFont(new Font("Serif", Font.BOLD, (int) (fontSize * 0.05)));
        endTurn.setFont(new Font("Serif", Font.BOLD, (int) (fontSize * 0.05)));
        quit.setFont(new Font("Serif", Font.BOLD, (int) (fontSize * 0.05)));
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (model.getTurnCount() < 0) {
            p1MancalaCount = p2MancalaCount = redoCount = turnCount = 0;
            isP1turn = false;
        } else {
            turnLabel.setText("Player " + (model.isP1turn() ? "A" : "B") + "'s turn");
            board.setBoardStyle(model.getCurrentStyle());
            redo.setText("Redo: " + model.getRedoCount());
            redo.setEnabled(model.getRedoCount() > 0);
            endTurn.setEnabled(!(model.getTurnCount() > 0));

            int newP1Mancala = model.getP1Pits()[model.NUMBER_OF_PITS];
            int newP2Mancala = model.getP2Pits()[model.NUMBER_OF_PITS];
            if ((newP1Mancala - p1MancalaCount) > 1 || (newP2Mancala - p2MancalaCount) > 1) {
                msgLabel.setText("STEAL !!!");
                transVal = 255;
                time.start();
            } else if (isP1turn == model.isP1turn() && redoCount == model.getRedoCount()
                    && turnCount == model.getTurnCount()) {
                msgLabel.setText("+1 TURN !!!");
                transVal = 255;
                time.start();
            } else {
                transVal = 0;
                msgLabel.setText("Uhhh!");
            }
            p1MancalaCount = newP1Mancala;
            p2MancalaCount = newP2Mancala;
            isP1turn = model.isP1turn();
            redoCount = model.getRedoCount();
            turnCount = model.getTurnCount();
            repaint();
        }
    }
}