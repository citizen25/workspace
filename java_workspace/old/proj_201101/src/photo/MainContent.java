package photo;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Dimension;

import javax.swing.JPanel;

public class MainContent extends JPanel{
    Toolkit kit = Toolkit.getDefaultToolkit();
    Image img;

    PhotoApp photoApp;

    public MainContent(PhotoApp photoApp, int n){
        this.photoApp = photoApp;
        this.setPreferredSize(new Dimension(600, 900));
        img = kit.getImage(photoApp.directory + photoApp.srcArray[n]);
        img = img.getScaledInstance(600, 900, Image.SCALE_SMOOTH);
    }

    public void paint(Graphics g){
        g.drawImage(img, 0, 0, this);
    }
}
