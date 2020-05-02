package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MancalaPit extends JPanel {
	private static final long serialVersionUID = 1L;

	private static final int MAX_CAPACITY = 18;

	private Shape mancalaShape;
	private JLabel mancalaLabel;
	private JLabel remainStoneLabel;
	private JPanel container;
	private JLabel totalLabel;

	public MancalaPit() {
		this.setLayout(new BorderLayout());

		mancalaLabel = new JLabel();
		mancalaLabel.setHorizontalAlignment(JLabel.CENTER);

		container = new JPanel(new GridLayout(0, 3));
		container.setOpaque(false);
		remainStoneLabel = new JLabel();
		remainStoneLabel.setHorizontalAlignment(JLabel.RIGHT);

		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setOpaque(false);
		centerPanel.add(container, BorderLayout.CENTER);
		centerPanel.add(remainStoneLabel, BorderLayout.SOUTH);

		totalLabel = new JLabel();
		totalLabel.setHorizontalAlignment(JLabel.CENTER);

		this.add(mancalaLabel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(totalLabel, BorderLayout.SOUTH);
	}

	public Shape getShape() {
		return mancalaShape;
	}

	public void setLabelColor(Color c) {
		mancalaLabel.setForeground(c);
		remainStoneLabel.setForeground(c);
		totalLabel.setForeground(c);
	}

	public void setLabelFont(int w, int h) {
		int fontSize = Math.min(w, h);
		Font newFont = new Font("Serif", Font.PLAIN, (int) (fontSize * 0.04));
		mancalaLabel.setFont(newFont);
		remainStoneLabel.setFont(newFont);
		totalLabel.setFont(newFont);
	}

	public void setLabelText(String labelText) {
		mancalaLabel.setText(labelText);
	}

	public void setShape(Shape s) {
		mancalaShape = s;
		this.setBounds(mancalaShape.getBounds());
		this.setPreferredSize(
				new Dimension((int) mancalaShape.getBounds().getWidth(), (int) mancalaShape.getBounds().getHeight()));
	}

	public void updateStoneAmount(int stoneAmount) {
		totalLabel.setText("Total: " + stoneAmount);
		if (stoneAmount > MAX_CAPACITY) {
			remainStoneLabel.setText("+" + (stoneAmount - MAX_CAPACITY));
			stoneAmount = MAX_CAPACITY;
		} else {
			remainStoneLabel.setText("");
		}
		int stoneIndex = 0;
		while (stoneIndex < stoneAmount) {
			container.add(new Stone(), stoneIndex);
			stoneIndex++;
		}
		while (stoneIndex < MAX_CAPACITY) {
			container.add(new JLabel(), stoneIndex);
			stoneIndex++;
		}
		int currentAmount = container.getComponentCount();
		while (currentAmount > MAX_CAPACITY) {
			container.remove(container.getComponentCount() - 1);
			currentAmount--;
		}
		repaint();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension((int) mancalaShape.getBounds().getHeight(), (int) mancalaShape.getBounds().getWidth());
	}

}
