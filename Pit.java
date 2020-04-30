import java.awt.Color;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Pit extends JPanel {
	private Shape pitShape;
	private JLabel label;
 	private ArrayList<Shape> stones;


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

	public ArrayList<Shape> getStone(int numOfStones) {
		this.setStones(numOfStones);
		return this.stones;
	}
	public void setStones(int numOfStones){
		stones = new ArrayList<>();
		if (numOfStones == 1)
		{
			Ellipse2D.Double stone1 = new Ellipse2D.Double(pitShape.getBounds().getCenterX(),
					pitShape.getBounds().getCenterY(), 50,50);
			stones.add(stone1);
		}
		if (numOfStones == 2)
		{
			Ellipse2D.Double stone1 = new Ellipse2D.Double(pitShape.getBounds().getCenterX() +25,
					pitShape.getBounds().getCenterY(), 50,50);
			Ellipse2D.Double stone2 = new Ellipse2D.Double(pitShape.getBounds().getCenterX() -25,
					pitShape.getBounds().getCenterY(), 50,50);
			stones.add(stone1);
			stones.add(stone2);
		}
		if (numOfStones == 3)
		{
			Ellipse2D.Double stone1 = new Ellipse2D.Double(pitShape.getBounds().getCenterX() +25,
					pitShape.getBounds().getCenterY(), 50,50);
			Ellipse2D.Double stone2 = new Ellipse2D.Double(pitShape.getBounds().getCenterX() -25,
					pitShape.getBounds().getCenterY(), 50,50);
			Ellipse2D.Double stone3 = new Ellipse2D.Double(pitShape.getBounds().getCenterX(),
					pitShape.getBounds().getCenterY() +25, 50,50);
			stones.add(stone1);
			stones.add(stone2);
			stones.add(stone3);
		}
		if (numOfStones == 4)
		{
			Ellipse2D.Double stone1 = new Ellipse2D.Double(pitShape.getBounds().getCenterX() -25,
					pitShape.getBounds().getCenterY() +25, 50,50);
			Ellipse2D.Double stone2 = new Ellipse2D.Double(pitShape.getBounds().getCenterX() +25,
					pitShape.getBounds().getCenterY() +25, 50,50);
			Ellipse2D.Double stone3 = new Ellipse2D.Double(pitShape.getBounds().getCenterX() -25,
					pitShape.getBounds().getCenterY() -25, 50,50);
			Ellipse2D.Double stone4 = new Ellipse2D.Double(pitShape.getBounds().getCenterX() +25,
					pitShape.getBounds().getCenterY() -25, 50,50);
			stones.add(stone1);
			stones.add(stone2);
			stones.add(stone3);
			stones.add(stone4);
				if (numOfStones > 4)
				{
					Ellipse2D.Double stone5 = new Ellipse2D.Double(pitShape.getBounds().getCenterX(),
							pitShape.getBounds().getCenterY(), 50,50);
					stones.add(stone5);
				}
		}

	}

}
