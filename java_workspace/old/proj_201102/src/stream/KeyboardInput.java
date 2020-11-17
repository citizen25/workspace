package stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import javax.swing.JFrame;
import javax.swing.JTextArea;

class KeyBoardInput extends JFrame{

    JTextArea area;

    public KeyBoardInput(){
        area = new JTextArea();

        this.add(area);

        this.setVisible(true);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        new KeyBoardInput();

        InputStream is = System.in;
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader buffr = new BufferedReader(reader);


        String str = null;
        try{
            while(true){
                str = buffr.readLine();
                System.out.println(str);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
}
