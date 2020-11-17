package io;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class BufferedInput extends JFrame{
    JTextArea area;

    public BufferedInput(){
        area = new JTextArea();    

        area.setPreferredSize(new Dimension(600, 600));
        area.setBackground(Color.BLACK);
        area.setForeground(Color.WHITE);
        area.setFont(new Font("Monospaced", Font.BOLD|Font.ITALIC, 40));

        this.add(area);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        //this.setSize(650, 650);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void readBuffer(){
        InputStream is = System.in;
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader buffr = new BufferedReader(isr);

        String str = null;
        while(true){
            try{
                str = buffr.readLine();
                area.append(str+"\n");
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        
    }

    public static void main(String args[]){
        new BufferedInput().readBuffer();
    }
}
