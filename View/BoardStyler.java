package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Shape;

/**
 * The BoardStyler interface allows implementing classes to define look and feel
 * of BoardView panel. Adding a BoardStyler to a BoardView panel can be thought
 * of as adding a theme to the BoardView.
 * 
 * @author Jonathan Aguayo
 *
 */
public abstract class BoardStyler {
	private Shape player1Mancala;
	private Shape player2Mancala;
	protected Shape[] player1Pits;
	protected Shape[] player2Pits;
	private Container parentContainer;
	private int outlineWidth;
	private Color boardColor;
	private Color pitColor;
	private Color labelColor;

	/**
	 * Sets the shape of player 1 Mancala
	 * 
	 * @param s The shape Player 1 Mancala should be
	 */
	void setPlayer1MancalaShape(Shape s) {
		player1Mancala = s;
		player1Pits = new Shape[6];
		player2Pits = new Shape[6];
	}

	/**
	 * Sets the shape of player 2 Mancala
	 * 
	 * @param s The shape Player 2 Mancala should be
	 */
	void setPlayer2MancalaShape(Shape s) {
		player2Mancala = s;
	}

	abstract void setPlayer2Pits();

	abstract void setPlayer1Pits();

	/**
	 * Sets the parentContailer for this styler. The ParentContainer will be used to
	 * calculate relative positioning of mancalas and pits on board
	 * 
	 * @param parent The parent of this styler. Will be the mancala board that this
	 *               styler will style.
	 */
	void setParentContainer(Container parent) {
		parentContainer = parent;
	}

	/**
	 * Sets the width of the drawing that will outline the shape of the mancalas and
	 * pits
	 * 
	 * @param width The width of the outline
	 */
	void setOutlineWidth(int width) {
		outlineWidth = width;
	}

	/**
	 * Sets the mancala board to a specific color
	 * 
	 * @param c The color that the mancala game board should be set to
	 */
	void setBoardColor(Color c) {
		boardColor = c;
	}

	/**
	 * Sets the pit and mancala colors to a specific color
	 * 
	 * @param c The color that all pits and mancalas should be set to
	 */
	void setPieceColor(Color c) {
		pitColor = c;
	}

	/**
	 * Sets the color for the text labels of the pits and mancalas
	 * 
	 * @param c The color the labels of the pits and mancalas should be set to.
	 */
	void setLabelColor(Color c) {
		labelColor = c;
	}

	/**
	 * Gets this stylers parent container which will be the game board to be styled
	 * 
	 * @return The game board this styler will style
	 */
	Container getParentContainer() {
		return parentContainer;
	}

	/**
	 * Gets the shape player 1 mancala will be. This method will be used in the game
	 * board class to set the shape and position of player 1 mancala.
	 * 
	 * @return The shape player 1 mancala will b.
	 */
	Shape getPlayer1Mancala() {
		return player1Mancala;
	}

	/**
	 * Gets the shape player 2 mancala will be. This method will be used in the game
	 * board class to set the shape and position of player 1 mancala.
	 * 
	 * @return The shape player 2 mancala will b.
	 */
	Shape getPlayer2Mancala() {
		return player2Mancala;
	}

	Shape[] getPlayer1Pits() {
		return player1Pits;
	}

	Shape[] getPlayer2Pits() {
		return player2Pits;
	}

	/**
	 * Gets the color that this styler says the game board should be.
	 * 
	 * @return The color this styler says the game board background should be set
	 *         to.
	 */
	Color getBoardColor() {
		return boardColor;
	}

	/**
	 * Gets the color that this styler says the game pieces should be. Pieces
	 * include pits and mancalas only.
	 * 
	 * @return The color this styler says the game pieces background should be.
	 */
	Color getPieceColor() {
		return pitColor;
	}

	/**
	 * gets the color that this styler says the game pieces text labels should be.
	 * return The color this styler says the game pieces text labels should be set
	 * to
	 */
	Color getLabelColor() {
		return labelColor;
	}

	/**
	 * Gets the width of the pit and mancala outlining shapes.
	 * 
	 * @return The width this styler says the game pieces outlining shapes should
	 *         be.
	 */
	int getOutLineWidth() {
		return outlineWidth;
	}

}
