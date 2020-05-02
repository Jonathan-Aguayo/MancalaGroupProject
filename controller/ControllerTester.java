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

public class ControllerTester {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test Control");
        frame.getContentPane().setBackground(new Color(255, 230, 204));
        frame.setMinimumSize(new Dimension(850, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CardLayout card = new CardLayout();
        frame.setLayout(card);

        MancalaModel model = new MancalaModel();
        model.addStyle(new EspressoStyler());
        model.addStyle(new DarkStyler());

        MainPanel menuPanel = new MainPanel(frame.getContentPane(), card);
        SetUpPanel setUpPanel = new SetUpPanel(frame.getContentPane(), card, model);
        GamePanel gamePanel = new GamePanel(frame.getContentPane(), card, model);
        QuitPanel quitPanel = new QuitPanel(frame.getContentPane(), card, model);
        ResultPanel resultPanel = new ResultPanel(frame.getContentPane(), card, model);
        model.addChangeListener(setUpPanel);
        model.addChangeListener(gamePanel);
        model.addChangeListener(resultPanel);

        frame.add(PanelName.MENU.getName(), menuPanel);
        frame.add(PanelName.SETUP.getName(), setUpPanel);
        frame.add(PanelName.GAME.getName(), gamePanel);
        frame.add(PanelName.QUIT.getName(), quitPanel);
        frame.add(PanelName.RESULT.getName(), resultPanel);
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                menuPanel.stateChanged(new ChangeEvent(frame));
                setUpPanel.stateChanged(new ChangeEvent(frame));
                gamePanel.stateChanged(new ChangeEvent(frame));
                quitPanel.stateChanged(new ChangeEvent(frame));
                resultPanel.stateChanged(new ChangeEvent(frame));
            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}