package main;
import java.awt.*;
import java.net.URL;
import javax.swing.*;

 /**
  * 
  * @author Gruppe 1 
  * 
  * Container to display Shop
  *
  */

//This is more or less a placeholder to show what we would have liked the application to be able to do 
//but we decided not to implement an actual Shop Page


public class ShopPage extends Container{
    private JPanel panel;
    private MenuBar menuBar;
    private JLabel label;
    
    public ShopPage (JFrame parentFrame) {
    setLayout(new BorderLayout());
    panel = new JPanel();
    menuBar = new MenuBar(parentFrame);
    URL url = ChatPage.class.getResource("/ShopImage.png");           
    ImageIcon bild = new ImageIcon(url);
    label = new JLabel (bild);
    panel.add(label);
    add(menuBar, BorderLayout.NORTH);
    add(panel, BorderLayout.CENTER);

    }
}