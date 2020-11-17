package chat;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Chat3 extends JFrame implements ActionListener{

    //components: textarea, panel, textbox, 2 buttons(send, open)
    JTextArea area_chat;
    JPanel p_south;
    JTextField area_input;
    JButton bt_send;
    
    Chat1 chat1;
    Chat2 chat2;

    public Chat3(Chat1 chat1, Chat2 chat2){
        super("Sub");

        this.chat1 = chat1;
        this.chat2 = chat2;

        area_chat = new JTextArea();
        p_south = new JPanel();
        area_input = new JTextField(15);
        bt_send = new JButton("send");
       
        this.setLayout(new BorderLayout());
        this.add(area_chat, BorderLayout.CENTER);
        this.add(p_south, BorderLayout.SOUTH);

        p_south.add(area_input);
        p_south.add(bt_send);
        
        bt_send.addActionListener(this);

        this.setLocation(1400, 0);
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
            chat1.area_chat.append(src+"\n");
            chat2.area_chat.append(src+"\n");
        }
    }
}
