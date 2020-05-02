package controller;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;

import model.MancalaModel;
import view.DarkStyler;
import view.EspressoStyler;

public class Mancala extends JFrame {
    private static final long serialVersionUID = 1L;

    public Mancala() {
        setTitle("Mancala");
        getContentPane().setBackground(new Color(255, 230, 204));
        setMinimumSize(new Dimension(850, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CardLayout card = new CardLayout();
        setLayout(card);

        MancalaModel model = new MancalaModel();
        model.addStyle(new EspressoStyler());
        model.addStyle(new DarkStyler());

        MainPanel menuPanel = new MainPanel(getContentPane(), card);
        SetUpPanel setUpPanel = new SetUpPanel(getContentPane(), card, model);
        GamePanel gamePanel = new GamePanel(getContentPane(), card, model);
        QuitPanel quitPanel = new QuitPanel(getContentPane(), card, model);
        ResultPanel resultPanel = new ResultPanel(getContentPane(), card, model);
        model.addChangeListener(setUpPanel);
        model.addChangeListener(gamePanel);
        model.addChangeListener(resultPanel);

        add(PanelName.MENU.getName(), menuPanel);
        add(PanelName.SETUP.getName(), setUpPanel);
        add(PanelName.GAME.getName(), gamePanel);
        add(PanelName.QUIT.getName(), quitPanel);
        add(PanelName.RESULT.getName(), resultPanel);
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                menuPanel.resizeFont();
                setUpPanel.resizeFont();
                gamePanel.resizeFont();
                quitPanel.resizeFont();
                resultPanel.resizeFont();
            }
        });
        setLocationRelativeTo(null);
        setVisible(true);
    }
}