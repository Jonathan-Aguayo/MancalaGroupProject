package View;

import java.awt.Color;
import java.awt.Container;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D.Double;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JComponent;

public class EspressoStyler extends BoardStyler
{	
	public EspressoStyler() 
	{	
		this.setOutlineWidth(8);
		this.setBoardColor(new Color(95,72,50));
		this.setPieceColor(new Color(103,92,74)); 
		this.setLabelColor(Color.green);
	}

	@Override
	public Shape getPitShape()
	{
		this.setPitShape(new Ellipse2D.Double(this.getParentContainer().getSize().getWidth()*.05, this.getParentContainer().getSize().getHeight()*.05, this.getParentContainer().getSize().getWidth()*.1, this.getParentContainer().getSize().getHeight()*.65));
		return null;
	}

	@Override
	public Shape getPlayer1Mancala() 
	{
		this.setPlayer1MancalaShape(new RoundRectangle2D.Double(this.getParentContainer().getSize().getWidth()*.05, this.getParentContainer().getSize().getHeight()*.05, this.getParentContainer().getSize().getWidth()*.1, this.getParentContainer().getSize().getHeight()*.65,3,3));
		return super.getPlayer1Mancala();
	}

	@Override
	public Shape getPlayer2Mancala() 
	{
		this.setPlayer2MancalaShape(new RoundRectangle2D.Double(this.getParentContainer().getSize().getWidth()*.85, this.getParentContainer().getSize().getHeight()*.35, this.getParentContainer().getSize().getWidth()*.1, this.getParentContainer().getSize().getHeight()*.60,3,3));
		return super.getPlayer2Mancala();
	}
}

