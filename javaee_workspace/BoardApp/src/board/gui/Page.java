package board.gui;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Page extends JPanel{
	BoardMain boardMain;
	public Page(BoardMain boardMain) {
		this.boardMain = boardMain;
		this.setPreferredSize(new Dimension(880, 680));
	}
}
