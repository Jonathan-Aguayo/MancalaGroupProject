package controller;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;

import model.MancalaModel;
import view.DarkStyler;
import view.EspressoStyler;

public class Mancala extends JFrame {
    private static final long serialVersionUID = 1L;

    public Mancala() {
        this.getContentPane().setBackground(new Color(255, 230, 204));
        this.setMinimumSize(new Dimension(850, 600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CardLayout card = new CardLayout();
        this.setLayout(card);

        MancalaModel model = new MancalaModel();
        model.addStyle(new EspressoStyler());
        model.addStyle(new DarkStyler());

        MainPanel menuPanel = new MainPanel(this.getContentPane(), card);
        SetUpPanel setUpPanel = new SetUpPanel(this.getContentPane(), card, model);
        GamePanel gamePanel = new GamePanel(this.getContentPane(), card, model);
        QuitPanel quitPanel = new QuitPanel(this.getContentPane(), card, model);
        ResultPanel resultPanel = new ResultPanel(this.getContentPane(), card, model);
        model.addChangeListener(setUpPanel);
        model.addChangeListener(gamePanel);
        model.addChangeListener(resultPanel);

        this.add(PanelName.MENU.getName(), menuPanel);
        this.add(PanelName.SETUP.getName(), setUpPanel);
        this.add(PanelName.GAME.getName(), gamePanel);
        this.add(PanelName.QUIT.getName(), quitPanel);
        this.add(PanelName.RESULT.getName(), resultPanel);
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                menuPanel.stateChanged(new ChangeEvent(this));
                setUpPanel.stateChanged(new ChangeEvent(this));
                gamePanel.stateChanged(new ChangeEvent(this));
                quitPanel.stateChanged(new ChangeEvent(this));
                resultPanel.stateChanged(new ChangeEvent(this));
            }
        });
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}