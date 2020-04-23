package View;

import java.awt.Color;
import java.awt.Container;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JComponent;

public class EspressoStyler extends BoardStyler {

	public EspressoStyler() {
		this.setOutlineWidth(8);
		this.setBoardColor(new Color(73, 56, 41));
		this.setPieceColor(new Color(185, 156, 107));
		this.setLabelColor(Color.green);
	}

	public void setPlayer1Pits() {
		double pitsTopLeftXValue = .19;
		for (int i = 0; i < this.player1Pits.length; i++) {
			this.player1Pits[i] = new Rectangle2D.Double(
					this.getParentContainer().getSize().getWidth() * pitsTopLeftXValue,
					this.getParentContainer().getSize().getHeight() * .05,
					this.getParentContainer().getSize().getWidth() * .1,
					this.getParentContainer().getSize().getHeight() * .14);
			pitsTopLeftXValue += .132;
		}
	}

	public void setPlayer2Pits() {
		double pitsTopLeftXValue = .05;
		for (int i = 0; i < this.player2Pits.length; i++) {
			this.player2Pits[i] = new Rectangle2D.Double(
					this.getParentContainer().getSize().getWidth() * pitsTopLeftXValue,
					this.getParentContainer().getSize().getHeight() * .8,
					this.getParentContainer().getSize().getWidth() * .1,
					this.getParentContainer().getSize().getHeight() * .14);
			pitsTopLeftXValue += .132;
		}
	}

	@Override
	public Shape getPlayer1Mancala() {
		this.setPlayer1MancalaShape(new RoundRectangle2D.Double(this.getParentContainer().getSize().getWidth() * .05,
				this.getParentContainer().getSize().getHeight() * .05,
				this.getParentContainer().getSize().getWidth() * .1,
				this.getParentContainer().getSize().getHeight() * .65, 3, 3));
		return super.getPlayer1Mancala();
	}

	@Override
	public Shape getPlayer2Mancala() {
		this.setPlayer2MancalaShape(new RoundRectangle2D.Double(this.getParentContainer().getSize().getWidth() * .85,
				this.getParentContainer().getSize().getHeight() * .35,
				this.getParentContainer().getSize().getWidth() * .1,
				this.getParentContainer().getSize().getHeight() * .60, 3, 3));
		return super.getPlayer2Mancala();
	}

}
