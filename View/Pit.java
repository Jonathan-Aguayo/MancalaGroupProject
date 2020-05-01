package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Shape;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Pit extends JPanel {
	private static final long serialVersionUID = 1L;

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
