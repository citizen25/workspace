package chat;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Chat1 extends JFrame implements ActionListener{

    //components: textarea, panel, textbox, 2 buttons(send, open)
    JTextArea area_chat;
    JPanel p_south;
    JTextField area_input;
    JButton bt_send;
    JButton bt_open;

    Chat2 chat2;
    Chat3 chat3;

    public Chat1(){
        super("Main");

        area_chat = new JTextArea();
        p_south = new JPanel();
        area_input = new JTextField(15);
        bt_send = new JButton("send");
        bt_open = new JButton("open");
        
        this.setLayout(new BorderLayout());
        this.add(area_chat, BorderLayout.CENTER);
        this.add(p_south, BorderLayout.SOUTH);

        p_south.add(area_input);
        p_south.add(bt_send);
        p_south.add(bt_open);
        
        bt_send.addActionListener(this);
        bt_open.addActionListener(this);

        this.setSize(500, 700);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); 

    }

    public void actionPerformed(ActionEvent e){
        Object obj = e.getSource();
        if(obj == bt_send){
            String src = area_input.getText();
            area_chat.append(src+"\n");
            area_input.setText("");
            chat2.area_chat.append(src+"\n");
            chat3.area_chat.append(src+"\n");
        }else if(obj == bt_open){
            chat2 = new Chat2(this);
            chat3 = new Chat3(this, chat2);
            chat2.getChat3(chat3);
        }
    }
}
