package day1029.collection;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ButtonCollection_practice extends JFrame implements ActionListener{

	JPanel p_north;
	JPanel p_center;
	JButton bt_create;
	JButton bt_bg;
	
	ArrayList<JButton> btArray = new ArrayList<JButton>();
	
	int count=0;
	
	public ButtonCollection_practice() {
		p_north = new JPanel();
		p_center = new JPanel();
		
		bt_create = new JButton("Create Button");
		bt_bg = new JButton("Background Color");
				
		this.add(p_north, BorderLayout.NORTH);
		this.add(p_center, BorderLayout.CENTER);
		
		p_north.setLayout(new FlowLayout());
		p_center.setLayout(new FlowLayout());
		
		p_north.add(bt_create);
		p_north.add(bt_bg);
		
		bt_create.addActionListener(this);
		bt_bg.addActionListener(this);
		
		setVisible(true);
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == bt_create) {
			count++;
			JButton bt = new JButton("Button"+count);
			btArray.add(bt);
			for(int i=0; i<btArray.size(); i++) {
				p_center.add(btArray.get(i));
			}
			p_center.updateUI();
		}else if(obj == bt_bg) {
			p_center.setBackground(Color.BLACK);
		}
	}
	
	public static void main(String[] args) {
		new ButtonCollection_practice();
	}

}
