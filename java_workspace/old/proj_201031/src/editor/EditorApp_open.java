package editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JFileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

class EditorApp extends JFrame implements ActionListener{

    JMenuBar bar;
    JMenu m_file;
    JMenuItem i_open;
    JMenuItem i_save;
    JScrollPane scroll;
    JTextArea area;

    FileInputStream fis;
    EditorApp editorApp;

    public EditorApp(){
        bar = new JMenuBar();
        m_file = new JMenu("file");
        i_open = new JMenuItem("open");
        i_save = new JMenuItem("save");
        area = new JTextArea();
        scroll = new JScrollPane(area);
        
        bar.add(m_file);
        m_file.add(i_open);
        m_file.add(i_save);
        this.add(scroll);
        this.setJMenuBar(bar);

        i_open.addActionListener(this);
        
        this.setVisible(true);
        this.setSize(600, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e){
        this.area.setText("");
        JFileChooser chooser = new JFileChooser("C:/workspace/java_workspace/proj_201031/res");
		chooser.showOpenDialog(this);
        File file = chooser.getSelectedFile();

        try{
            fis = new FileInputStream(file);
            int data = 0;
            String content = "";
            while(true){
                data = fis.read();
                if(data==-1){break;}
                System.out.print((char)data);
                content = content + (char)data;
            }
            this.area.append(content);
        }catch(FileNotFoundException err){
            System.out.println("File not found");
        }catch(IOException err){
            System.out.println("cannot read File");
        }finally{
            if(fis != null){
                try{
                    fis.close();
                }catch(IOException err){
                    System.out.println("null point exception");
                }
            }    
        }
    }

    public static void main(String[] args){
        new EditorApp();
    }
}

