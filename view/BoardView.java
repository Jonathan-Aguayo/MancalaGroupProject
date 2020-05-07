package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.MancalaModel;

public class BoardView extends JPanel implements ChangeListener {
	private static final long serialVersionUID = 1L;

	private MancalaModel model;
	private BoardStyler currentStyle;
	private MancalaPit player1Mancala;
	private MancalaPit player2Mancala;
	private Pit[] pits;

	public BoardView(MancalaModel m) {
		this.model = m;
		this.setLayout(null);
		this.player1Mancala = new MancalaPit();
		this.player2Mancala = new MancalaPit();
		pits = new Pit[12];
		for (int i = 0; i < 12; i++) {
			pits[i] = new Pit(model);
			this.add(pits[i]);
		}
		if (!model.getStyleList().isEmpty()) {
			setBoardStyle(model.getCurrentStyle());
		} else {
			setBoardStyle(new EspressoStyler());
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

	private void updateBoard() {
		boolean p1Turn = model.isP1turn();
		boolean gameStarted = model.getTurnCount() >= 0;
		int[] p2Pits = model.getP2Pits();
		player1Mancala.updateStoneAmount(p2Pits[model.NUMBER_OF_PITS]);
		for (int i = 0; i < 6; i++) {
			pits[i].setEnabled(!p1Turn && gameStarted);
			pits[i].setPitIndex(5 - i);
			pits[i].updateStoneAmount(p2Pits[5 - i]);
		}

		int[] p1Pits = model.getP1Pits();
		player2Mancala.updateStoneAmount(p1Pits[model.NUMBER_OF_PITS]);
		for (int i = 6; i < 12; i++) {
			pits[i].setEnabled(p1Turn && gameStarted);
			pits[i].setPitIndex(i - 6);
			pits[i].updateStoneAmount(p1Pits[i - 6]);
		}
	}

	private void updatePitPositions() {
		currentStyle.setPlayer1Pits();
		currentStyle.setPlayer2Pits();
		for (int i = 0; i < 6; i++) {
			pits[i].setLabelText("B" + (6 - i));
			pits[i].setLabelColor(currentStyle.getLabelColor());
			pits[i].setShape(currentStyle.getPlayer1Pits()[i]);
			pits[i].setBackground(currentStyle.getPieceColor());
			pits[i].setStyleColor(currentStyle.getPieceColor());
			pits[i].setLabelFont(currentStyle.getParentContainer().getWidth(),
					currentStyle.getParentContainer().getHeight());
		}
		for (int i = 6; i < 12; i++) {
			pits[i].setLabelText("A" + (i - 5));
			pits[i].setLabelColor(currentStyle.getLabelColor());
			pits[i].setShape(currentStyle.getPlayer2Pits()[i - 6]);
			pits[i].setBackground(currentStyle.getPieceColor());
			pits[i].setStyleColor(currentStyle.getPieceColor());
			pits[i].setLabelFont(currentStyle.getParentContainer().getWidth(),
					currentStyle.getParentContainer().getHeight());
		}
	}

	private void drawPitPositions(Graphics2D g2) {
		this.updatePitPositions();
		for (int i = 0; i < 6; i++) {
			g2.draw(pits[i].getShape());
		}

		for (int i = 6; i < 12; i++) {
			g2.draw(pits[i].getShape());
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// Gets the most recent information about Boards shape and size
		this.player1Mancala.setShape(currentStyle.getPlayer1Mancala());
		this.player1Mancala.setLabelFont(currentStyle.getParentContainer().getWidth(),
				currentStyle.getParentContainer().getHeight());
		this.player2Mancala.setShape(currentStyle.getPlayer2Mancala());
		this.player2Mancala.setLabelFont(currentStyle.getParentContainer().getWidth(),
				currentStyle.getParentContainer().getHeight());
		// Sets the outline thickness to whatever the styler calls for
		g2.setStroke(new BasicStroke(currentStyle.getOutLineWidth()));

		// Draws the outlines of each Mancala panel
		g2.draw(player1Mancala.getShape());
		g2.draw(player2Mancala.getShape());
		this.drawPitPositions(g2);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		this.updateBoard();
	}
}
