package controller;

import java.awt.Container;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.MancalaModel;
import view.StyleButton;

public class ResultPanel extends JPanel implements ChangeListener {
    private static final long serialVersionUID = 1L;

    private MancalaModel model;
    private JLabel mancalaA;
    private JLabel mancalaB;
    private JLabel result;
    private StyleButton again;
    private StyleButton quit;

    public ResultPanel(Container p, CardLayout card, MancalaModel m) {
        model = m;

        setLayout(new GridBagLayout());
        setOpaque(false);

        mancalaA = new JLabel();
        mancalaB = new JLabel();
        result = new JLabel();
        again = new StyleButton("Play again");
        again.addActionListener(e -> {
            model.newGame();
            card.show(p, PanelName.GAME.getName());
        });
        quit = new StyleButton("Quit");
        quit.addActionListener(e -> {
            card.show(p, PanelName.MENU.getName());
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        add(mancalaA, gbc);
        add(mancalaB, gbc);
        add(result, gbc);
        gbc.insets = new Insets(15, 0, 0, 0);
        add(again, gbc);
        add(quit, gbc);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int fontSize = Math.min(getWidth(), getHeight());
        mancalaA.setFont(new Font("Serif", Font.PLAIN, (int) (fontSize * 0.05)));
        mancalaB.setFont(new Font("Serif", Font.PLAIN, (int) (fontSize * 0.05)));
        result.setFont(new Font("Serif", Font.BOLD, (int) (fontSize * 0.1)));
        again.setFont(new Font("Serif", Font.BOLD, (int) (fontSize * 0.05)));
        quit.setFont(new Font("Serif", Font.BOLD, (int) (fontSize * 0.05)));

        mancalaA.setText("Mancala 1: " + model.getP1Pits()[model.NUMBER_OF_PITS]);
        mancalaB.setText("Mancala 2: " + model.getP2Pits()[model.NUMBER_OF_PITS]);
        result.setText(model.getResult());
    }

}