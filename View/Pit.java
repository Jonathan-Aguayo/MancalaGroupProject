package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.MancalaModel;

public class Pit extends JPanel {
	private static final long serialVersionUID = 1L;

	private static final int MAX_CAPACITY = 6;

	private MancalaModel model;
	private int pitIndex;
	private Shape pitShape;
	private JLabel pitLabel;
	private JLabel remainStoneLabel;
	private JPanel container;
	private boolean enable;
	private Color styleBackground;
	private MouseListeners mouseListener;

	public Pit(MancalaModel m) {
		model = m;

		this.setLayout(new BorderLayout());

		pitLabel = new JLabel();
		pitLabel.setHorizontalAlignment(JLabel.CENTER);

		remainStoneLabel = new JLabel();

		container = new JPanel(new GridLayout(0, 3));
		container.setOpaque(false);

		this.add(pitLabel, BorderLayout.NORTH);
		this.add(remainStoneLabel, BorderLayout.EAST);
		this.add(container, BorderLayout.CENTER);

		mouseListener = new MouseListeners();
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);
	}

	public void setShape(Shape stylerShape) {
		pitShape = stylerShape;
		this.setBounds(pitShape.getBounds());
		this.setPreferredSize(
				new Dimension((int) pitShape.getBounds().getWidth(), (int) pitShape.getBounds().getHeight()));
	}

	public void setLabelColor(Color c) {
		pitLabel.setForeground(c);
		remainStoneLabel.setForeground(c);
	}

	public void setLabelText(String labelText) {
		pitLabel.setText(labelText);
	}

	public void setStyleColor(Color c) {
		styleBackground = c;
	}

	@Override
	public void setEnabled(boolean b) {
		super.setEnabled(b);
		enable = b;
	}

	public void setPitIndex(int pi) {
		pitIndex = pi;
	}

	public Shape getShape() {
		return pitShape;
	}

	public void updateStoneAmount(int stoneAmount) {
		if (stoneAmount > MAX_CAPACITY) {
			remainStoneLabel.setText("+" + (stoneAmount - MAX_CAPACITY));
			stoneAmount = MAX_CAPACITY;
		} else {
			remainStoneLabel.setText("");
		}
		int currentAmount = container.getComponentCount();
		while (currentAmount < stoneAmount) {
			container.add(new Stone());
			currentAmount++;
		}
		while (currentAmount > stoneAmount) {
			container.remove(container.getComponentCount() - 1);
			currentAmount--;
		}
		repaint();
	}

	private class MouseListeners extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			if (enable) {
				model.toMove(pitIndex);
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if (enable) {
				setBackground(new Color(191, 191, 191));
			}
			repaint();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			setBackground(styleBackground);
			repaint();
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension((int) pitShape.getBounds().getHeight(), (int) pitShape.getBounds().getWidth());
	}
}
