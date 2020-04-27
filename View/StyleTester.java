package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import model.MancalaModel;

public class StyleTester {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(300, 300);

		MancalaModel m = new MancalaModel();
		BoardView b = new BoardView(m);
		// b.setBoardStyle(new DarkStyler());
		b.setBoardStyle(new EspressoStyler());
		f.add(b);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}
