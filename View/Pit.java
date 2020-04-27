package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Pit extends JPanel {
	private Shape pitShape;
	private JLabel label;

	public Pit(Shape s, String labelText) {
		pitShape = s;
		label = new JLabel(labelText);
		this.setBounds(pitShape.getBounds());
		this.setPreferredSize(
				new Dimension((int) pitShape.getBounds().getWidth(), (int) pitShape.getBounds().getHeight()));
		this.add(label);
	}

	public Pit() {
		label = new JLabel();
		this.add(label);
	}

	public void setShape(Shape stylerShape) {
		pitShape = stylerShape;
		this.setBounds(pitShape.getBounds());
		this.setPreferredSize(
				new Dimension((int) pitShape.getBounds().getWidth(), (int) pitShape.getBounds().getHeight()));
	}

	public void setLabelColor(Color c) {
		label.setForeground(c);
	}

	public void setLabelText(String labelText) {
		label.setText(labelText);
	}

	public Shape getShape() {
		return this.pitShape;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension((int) pitShape.getBounds().getHeight(), (int) pitShape.getBounds().getWidth());
	}
}
