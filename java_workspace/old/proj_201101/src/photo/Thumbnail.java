package photo;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Thumbnail extends JPanel implements MouseListener{
    Toolkit kit = Toolkit.getDefaultToolkit();
    Image img;

    int n=0;

    PhotoApp photoApp;
    MainContent mainContent;
    
    public Thumbnail(PhotoApp photoApp, MainContent mainContent, int n){
        this.photoApp = photoApp;
        this.mainContent = mainContent;
        this.setPreferredSize(new Dimension(60, 100));
        this.n = n;
        img = kit.getImage(photoApp.directory + photoApp.srcArray[n]);
        img = img.getScaledInstance(60, 100, Image.SCALE_SMOOTH);

        this.addMouseListener(this);
    }

    public void paint(Graphics g){
        g.drawImage(img, 0, 0, this);
    }

    public void setMainContent(){
        mainContent.img = kit.getImage(photoApp.directory + photoApp.srcArray[n]);
        mainContent.repaint();
    }

    public void mousePressed(MouseEvent e){
        setMainContent();
    }
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
}
