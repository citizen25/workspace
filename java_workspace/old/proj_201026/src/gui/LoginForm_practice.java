package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Label;

class LoginForm{
    public static void main(String[] args){
        Frame frame = new Frame("로그인");
        GridLayout gLayout = new GridLayout(2, 2);
        Label id = new Label("ID");
        Label pass = new Label("Pass");
        TextField id_text = new TextField();
        TextField pass_text = new TextField();
        Panel p1 = new Panel();
        Panel p2 = new Panel();
        Button bt1 = new Button("버튼1");
        Button bt2 = new Button("버튼2");
        
        frame.setLayout(new BorderLayout());
        p1.setLayout(new GridLayout());

        p1.add(id);
        p1.add(id_text);
        p1.add(pass);
        p1.add(pass_text);

        p2.add(bt1);
        p2.add(bt2);

        frame.add(p1);
        frame.add(p2, BorderLayout.SOUTH);

        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}
