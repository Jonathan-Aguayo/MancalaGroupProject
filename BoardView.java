package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Model.MancalaModel;

public class BoardView extends JPanel implements ChangeListener
{
	private MancalaModel model;
	private BoardStyler currentStyle;
	private MancalaPit player1Mancala;
	private MancalaPit player2Mancala;
	private Pit[] pits;
	
	public BoardView(MancalaModel model)
	{
		//this.setPreferredSize(new Dimension(250, 250));
		this.model = model;
		this.setLayout(null);
		this.player1Mancala = new MancalaPit();
		this.player2Mancala = new MancalaPit();
	}

	public void setBoardStyle(BoardStyler styler)
	{
		//Gets Mancala to correct shape and position
		currentStyle = styler;
		styler.setParentContainer(this);
		this.player1Mancala.setShape(styler.getPlayer1Mancala());
		this.player2Mancala.setShape(styler.getPlayer2Mancala());
		
		//Adds Mancala pits to Board
		this.add(player1Mancala);
		this.add(player2Mancala);
		
		//Sets Board color to whatever styler calls for
		this.setBackground(styler.getBoardColor());

		//Sets mancala color to whatever styler calls for
		player1Mancala.setBackground(styler.getPieceColor());
		player2Mancala.setBackground(styler.getPieceColor());
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		//Gets the most recent information about Boards shape
		this.player1Mancala.setShape(currentStyle.getPlayer1Mancala());
		this.player2Mancala.setShape(currentStyle.getPlayer2Mancala());
		
		//Sets the outline thickness to whatever the styler calls for
		g2.setStroke(new BasicStroke(currentStyle.getOutLineWidth()));
		
		//Draws the outlines of each Mancala panel
		g2.draw(player1Mancala.getShape());
		g2.draw(player2Mancala.getShape());
	}


	@Override
	public void stateChanged(ChangeEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
}
