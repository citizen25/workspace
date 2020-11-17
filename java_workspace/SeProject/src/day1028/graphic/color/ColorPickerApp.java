package day1028.graphic.color;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorPickerApp extends JFrame implements MouseListener{

	JPanel p_palette;
	JPanel p_paper;
	
	JPanel p_red;
	JPanel p_orange;
	JPanel p_yellow;
	JPanel p_green;
	JPanel p_blue;
	JPanel p_navy;
	JPanel p_purple;
	
	public ColorPickerApp() {
		p_palette = new JPanel();
		p_paper = new JPanel();
		
		p_red = new JPanel();
		p_orange = new JPanel();
		p_yellow = new JPanel();
		p_green = new JPanel();
		p_blue = new JPanel();
		p_navy = new JPanel();
		p_purple = new JPanel();
		
		p_red.setPreferredSize(new Dimension(100, 100));
		p_orange.setPreferredSize(new Dimension(100, 100));
		p_yellow.setPreferredSize(new Dimension(100, 100));
		p_green.setPreferredSize(new Dimension(100, 100));
		p_blue.setPreferredSize(new Dimension(100, 100));
		p_navy.setPreferredSize(new Dimension(100, 100));
		p_purple.setPreferredSize(new Dimension(100, 100));
		
		p_red.setBackground(Color.RED);
		p_orange.setBackground(Color.ORANGE);
		p_yellow.setBackground(Color.YELLOW);
		p_green.setBackground(Color.GREEN);
		p_blue.setBackground(Color.BLUE);
		p_navy.setBackground(new Color(0, 0, 128));
		p_purple.setBackground(new Color(128, 0, 128));
		
		add(p_palette, BorderLayout.NORTH);
		add(p_paper, BorderLayout.CENTER);
		
		p_palette.add(p_red);
		p_palette.add(p_orange);
		p_palette.add(p_yellow);
		p_palette.add(p_green);
		p_palette.add(p_blue);
		p_palette.add(p_navy);
		p_palette.add(p_purple);

		p_red.addMouseListener(this);
		p_orange.addMouseListener(this);
		p_yellow.addMouseListener(this);
		p_green.addMouseListener(this);
		p_blue.addMouseListener(this);
		p_navy.addMouseListener(this);
		p_purple.addMouseListener(this);
		
		this.setSize(800, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		if(obj == p_red) {
			p_paper.setBackground(Color.RED);
		}else if(obj == p_orange) {
			p_paper.setBackground(Color.ORANGE);
		}else if(obj == p_yellow) {
			p_paper.setBackground(Color.YELLOW);
		}else if(obj == p_green) {
			p_paper.setBackground(Color.GREEN);
		}else if(obj == p_blue) {
			p_paper.setBackground(Color.BLUE);
		}else if(obj == p_navy) {
			p_paper.setBackground(new Color(0, 0, 128));
		}else if(obj == p_purple) {
			p_paper.setBackground(new Color(128, 0, 128));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	public static void main(String[] args) {
		new ColorPickerApp();
	}

}
