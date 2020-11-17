package chat;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Chat2 extends JFrame implements ActionListener{

    //components: textarea, panel, textbox, 2 buttons(send, open)
    JTextArea area_chat;
    JPanel p_south;
    JTextField area_input;
    JButton bt_send;
    
    Chat1 chat1;
    Chat3 chat3;

    public Chat2(Chat1 chat1){
        super("Sub");

        this.chat1 = chat1;

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

        this.setLocation(700, 0);
        this.setSize(500, 700);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); 

    }

    public void getChat3(Chat3 chat3){
        this.chat3 = chat3;
    }

    public void actionPerformed(ActionEvent e){
        Object obj = e.getSource();
        if(obj == bt_send){
            String src = area_input.getText();
            area_chat.append(src+"\n");
            area_input.setText("");
            chat1.area_chat.append(src+"\n");
            chat3.area_chat.append(src+"\n");
        }
    }
}
