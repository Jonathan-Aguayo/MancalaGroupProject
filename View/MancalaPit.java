package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MancalaPit extends JPanel {
	private Shape mancalaShape;
	private JLabel label;

	public MancalaPit() {
		label = new JLabel();
		this.add(label);
	}

	public MancalaPit(Shape shape, String labelText) {
		mancalaShape = shape;
		this.setBounds(mancalaShape.getBounds());
		this.setPreferredSize(new Dimension((int) shape.getBounds().getWidth(), (int) shape.getBounds().getHeight()));
		label = new JLabel(labelText);
		this.add(label);
	}

	public Shape getShape() {
		return mancalaShape;
	}

	public void setLabelColor(Color c) {
		label.setForeground(c);
	}

	public void setLabelText(String labelText) {
		label.setText(labelText);
	}

	public void setShape(Shape s) {
		mancalaShape = s;
		this.setBounds(mancalaShape.getBounds());
		this.setPreferredSize(
				new Dimension((int) mancalaShape.getBounds().getWidth(), (int) mancalaShape.getBounds().getHeight()));
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension((int) mancalaShape.getBounds().getHeight(), (int) mancalaShape.getBounds().getWidth());

	}

}
