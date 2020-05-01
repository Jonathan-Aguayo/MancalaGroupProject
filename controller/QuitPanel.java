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

import view.StyleButton;

public class QuitPanel extends JPanel implements ChangeListener {

    private JLabel label;
    private StyleButton confirm;
    private StyleButton cancel;

    public QuitPanel(Container p, CardLayout card) {
        setLayout(new GridBagLayout());
        setOpaque(false);

        label = new JLabel("Are you sure you want to quit?");
        confirm = new StyleButton("Yes");
        confirm.addActionListener(event -> {
            card.show(p, PanelName.MENU.getName());
        });
        cancel = new StyleButton("No");
        cancel.addActionListener(event -> {
            card.show(p, PanelName.GAME.getName());
        });
        JPanel buttons = new JPanel();
        buttons.setOpaque(false);
        buttons.add(confirm);
        buttons.add(cancel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(15, 0, 0, 0);
        add(label, gbc);
        add(buttons, gbc);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int fontSize = Math.min(getWidth(), getHeight());
        label.setFont(new Font("Serif", Font.PLAIN, (int) (fontSize * 0.1)));
        confirm.setFont(new Font("Serif", Font.BOLD, (int) (fontSize * 0.05)));
        cancel.setFont(new Font("Serif", Font.BOLD, (int) (fontSize * 0.05)));
    }

}