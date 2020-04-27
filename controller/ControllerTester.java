package controller;

import java.awt.CardLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;

// import model.*;
// import view.*;

public class ControllerTester {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test Control");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CardLayout card = new CardLayout();
        frame.setLayout(card);

        MainPanel menuPanel = new MainPanel(frame.getContentPane(), card);
        SetUpPanel setUpPanel = new SetUpPanel(frame.getContentPane(), card);
        GamePanel gamePanel = new GamePanel(frame.getContentPane(), card);
        frame.add(PanelName.MENU.getName(), menuPanel);
        frame.add(PanelName.SETUP.getName(), setUpPanel);
        frame.add(PanelName.GAME.getName(), gamePanel);
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                menuPanel.stateChanged(new ChangeEvent(frame));
            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}