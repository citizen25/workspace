package day1030.album;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class XCanvas extends Canvas{
	private Toolkit kit = Toolkit.getDefaultToolkit();
	private Image img;
	private String src;
	
	public XCanvas(String src) {
		img = kit.getImage(src);
		img = img.getScaledInstance(660, 450, Image.SCALE_SMOOTH);
		setPreferredSize(new Dimension(660, 400));
		setBackground(Color.GRAY);
	}
	
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		img = kit.getImage(src);
		img = img.getScaledInstance(660, 450, Image.SCALE_SMOOTH);
		this.src = src;
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}
}
