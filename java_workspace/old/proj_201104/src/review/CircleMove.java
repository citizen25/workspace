package review;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class CircleMove extends JFrame{

    JPanel can;
    JButton bt;
    int x, y;
    Thread thread;
    
    public CircleMove(){
        can = new JPanel(){
            public void paint(Graphics g){
                g.setColor(Color.YELLOW);
                g.fillRect(0, 0, 740, 640);
                g.setColor(Color.RED);
                g.fillOval(x, y, 50, 50);
            }
        };

        bt = new JButton("움직이기");
        thread = new Thread(){
            public void run(){
                while(true){
                    move();
                    can.repaint();
                    try{
                        Thread.sleep(10);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };

        can.setPreferredSize(new Dimension(700, 600));

        setLayout(new FlowLayout());
        add(bt);
        add(can);

        bt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                thread.start();
            }

        });

        setSize(740, 640);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void move(){
        x += 2;
        y += 2;
    }
    

    public static void main(String args[]){
        new CircleMove();
    }

}
