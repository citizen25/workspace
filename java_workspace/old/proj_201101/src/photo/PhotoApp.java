package photo;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PhotoApp{
    JFrame window;
    JPanel panel_south;
    MainContent panel_center;
    JScrollPane scroll;

    String directory = "C:/workspace/java_workspace/proj_201101/res/";
    String[] srcArray = {"0.jpg","1.jpg","2.jpg","3.jpg","4.jpg","5.jpg","6.jpg","7.jpg","8.jpg","9.jpg"};

    Thumbnail[] thumbnail = new Thumbnail[srcArray.length];
    
    public PhotoApp(){
        window = new JFrame("Photo Album");
        panel_south = new JPanel();
        panel_center = new MainContent(this, 0);
        scroll = new JScrollPane(panel_south);

        window.setLayout(new BorderLayout());
        window.add(scroll, BorderLayout.SOUTH);
        window.add(panel_center, BorderLayout.CENTER);

        for(int i=0; i<srcArray.length; i++){
            thumbnail[i] = new Thumbnail(this, panel_center, i);
            panel_south.add(thumbnail[i]);
        }

        window.setVisible(true);
        window.setSize(600, 1000);
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);

    }

    public static void main(String args[]){
        new PhotoApp();
    }
}

