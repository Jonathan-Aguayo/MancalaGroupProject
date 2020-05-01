import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class BoardView extends JPanel implements ChangeListener {
	private MancalaModel model;
	private BoardStyler currentStyle;
	private MancalaPit player1Mancala;
	private MancalaPit player2Mancala;
	private Pit[] pits;

	public BoardView(MancalaModel model) {
		// this.setPreferredSize(new Dimension(250, 250));
		this.model = model;
		this.setLayout(null);
		this.player1Mancala = new MancalaPit();
		this.player2Mancala = new MancalaPit();
		pits = new Pit[12];
		for (int i = 0; i < 12; i++) {
			pits[i] = new Pit();
			this.add(pits[i]);
		}
	}

	public void setBoardStyle(BoardStyler styler) {
		// Gets Mancala to correct shape and position
		currentStyle = styler;
		styler.setParentContainer(this);
		this.player1Mancala.setShape(styler.getPlayer1Mancala());
		this.player2Mancala.setShape(styler.getPlayer2Mancala());

		// Adds Mancala pits to Board
		this.add(player1Mancala);
		this.add(player2Mancala);

		// Sets Board color to whatever styler calls for
		this.setBackground(styler.getBoardColor());

		// Sets mancala color to whatever styler calls for
		player1Mancala.setBackground(styler.getPieceColor());
		player2Mancala.setBackground(styler.getPieceColor());

		player1Mancala.setLabelText("B");
		player2Mancala.setLabelText("A");

		player1Mancala.setLabelColor(styler.getLabelColor());
		player2Mancala.setLabelColor(styler.getLabelColor());
	}

	public void updatePitPositions() {
		currentStyle.setPlayer1Pits();
		currentStyle.setPlayer2Pits();
		for (int i = 0; i < 6; i++) {
			pits[i].setLabelText("B" + (i + 1));
			pits[i].setLabelColor(currentStyle.getLabelColor());
			pits[i].setShape(currentStyle.getPlayer1Pits()[i]);
			pits[i].setBackground(currentStyle.getPieceColor());
		}

		for (int i = 6; i < 12; i++) {
			pits[i].setLabelText("A" + (i - 5));
			pits[i].setLabelColor(currentStyle.getLabelColor());
			pits[i].setShape(currentStyle.getPlayer2Pits()[i - 6]);
			pits[i].setBackground(currentStyle.getPieceColor());

		}
	}

	public void drawPitPositions(Graphics2D g2) {
		int[] stones = model.getP1Pits();
		Shape pitShape = pits[1].getShape();
		for (int i = 0; i < 6; i++) {
			g2.draw(pits[i].getShape());
			paintStoneCount(g2, stones[i], pitShape);
		}

		stones = model.getP2Pits();
		for (int i = 6; i < 12; i++) {
			g2.draw(pits[i].getShape());
			paintStoneCount(g2, stones[i - 6], pitShape);
		}

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// Gets the most recent information about Boards shape
		this.player1Mancala.setShape(currentStyle.getPlayer1Mancala());
		this.player2Mancala.setShape(currentStyle.getPlayer2Mancala());
		// Sets the outline thickness to whatever the styler calls for
		g2.setStroke(new BasicStroke(currentStyle.getOutLineWidth()));

		// Draws the outlines of each Mancala panel
		g2.draw(player1Mancala.getShape());
		g2.draw(player2Mancala.getShape());
		this.drawPitPositions(g2);
	}
	public void paintStoneCount(Graphics2D g2, int numOfStones, Shape pitShape)
	{
		int size = 5;
/*		ArrayList<Shape> stones = new ArrayList<>();
		if (numOfStones == 1)
		{
			Ellipse2D.Double stone1 = new Ellipse2D.Double(pitShape.getBounds().getCenterX(),
					pitShape.getBounds().getCenterY(), size,size);
			stones.add(stone1);
		}
		if (numOfStones == 2)
		{
			Ellipse2D.Double stone1 = new Ellipse2D.Double(pitShape.getBounds().getCenterX() +25,
					pitShape.getBounds().getCenterY(), size,size);
			Ellipse2D.Double stone2 = new Ellipse2D.Double(pitShape.getBounds().getCenterX() -25,
					pitShape.getBounds().getCenterY(), size,size);
			stones.add(stone1);
			stones.add(stone2);
		}
		if (numOfStones == 3)
		{
			Ellipse2D.Double stone1 = new Ellipse2D.Double(pitShape.getBounds().getCenterX() +25,
					pitShape.getBounds().getCenterY(), size,size);
			Ellipse2D.Double stone2 = new Ellipse2D.Double(pitShape.getBounds().getCenterX() -25,
					pitShape.getBounds().getCenterY(), size,size);
			Ellipse2D.Double stone3 = new Ellipse2D.Double(pitShape.getBounds().getCenterX(),
					pitShape.getBounds().getCenterY() +25, size,size);
			stones.add(stone1);
			stones.add(stone2);
			stones.add(stone3);
		}
		if (numOfStones == 4)
		{
			Ellipse2D.Double stone1 = new Ellipse2D.Double(pitShape.getBounds().getCenterX() -25,
					pitShape.getBounds().getCenterY() +25, size,size);
			Ellipse2D.Double stone2 = new Ellipse2D.Double(pitShape.getBounds().getCenterX() +25,
					pitShape.getBounds().getCenterY() +25, size,size);
			Ellipse2D.Double stone3 = new Ellipse2D.Double(pitShape.getBounds().getCenterX() -25,
					pitShape.getBounds().getCenterY() -25, size,size);
			Ellipse2D.Double stone4 = new Ellipse2D.Double(pitShape.getBounds().getCenterX() +25,
					pitShape.getBounds().getCenterY() -25, size,size);
			stones.add(stone1);
			stones.add(stone2);
			stones.add(stone3);
			stones.add(stone4);
			if (numOfStones > 4)
			{
				Ellipse2D.Double stone5 = new Ellipse2D.Double(pitShape.getBounds().getCenterX(),
						pitShape.getBounds().getCenterY(), size,size);
				stones.add(stone5);
			}
		}*/
		g2.setColor(Color.BLACK);
		if (numOfStones == 1)
		{
			g2.fillOval((int) pitShape.getBounds().getCenterX(),
					(int) pitShape.getBounds().getCenterY(), size,size);

		}
		if (numOfStones == 2)
		{
			g2.fillOval((int) pitShape.getBounds().getCenterX() +25,
					(int) pitShape.getBounds().getCenterY(), size,size);
			g2.fillOval((int) pitShape.getBounds().getCenterX() -25,
					(int) pitShape.getBounds().getCenterY(), size,size);
		}
		if (numOfStones == 3)
		{
			g2.fillOval((int) pitShape.getBounds().getCenterX() +25,
					(int) pitShape.getBounds().getCenterY(), size,size);
			g2.fillOval((int) pitShape.getBounds().getCenterX() -25,
					(int) pitShape.getBounds().getCenterY(), size,size);
			g2.fillOval((int) pitShape.getBounds().getCenterX(),
					(int) pitShape.getBounds().getCenterY() +25, size,size);
		}
		if (numOfStones == 4)
		{
			g2.fillOval((int) pitShape.getBounds().getCenterX() -25,
					(int)pitShape.getBounds().getCenterY() +25, size,size);
			g2.fillOval((int) pitShape.getBounds().getCenterX() +25,
					(int) pitShape.getBounds().getCenterY() +25, size,size);
			g2.fillOval((int) pitShape.getBounds().getCenterX() -25,
					(int) pitShape.getBounds().getCenterY() -25, size,size);
			g2.fillOval((int) pitShape.getBounds().getCenterX() +25,
					(int) pitShape.getBounds().getCenterY() -25, size,size);

			if (numOfStones > 4)
			{
				g2.fillOval((int) pitShape.getBounds().getCenterX(),
						(int) pitShape.getBounds().getCenterY(), size,size);
			}
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

	}
}
