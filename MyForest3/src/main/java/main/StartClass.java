package main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * This is the starting point of the MyForest Application
 * @author Gruppe 1
 *
 */
public class StartClass extends JFrame {
	private Container c;
	
	public StartClass() {
		c = this.getContentPane();
		c.setLayout(new BorderLayout());
		MenuBar navMenuBar = new MenuBar(this);
		Container content = new HomeScreen(this);
		c.add(navMenuBar, BorderLayout.NORTH);
		c.add(content, BorderLayout.CENTER);
		
	}
	public static void main(String[] args) {
		JFrame start = new StartClass();
		start.setContentPane(new HomeScreen(start));
		start.setTitle("My Forest");
		start.setSize(1500, 1000);
		start.setVisible(true);
		start.setBackground(Color.white);
		start.addWindowListener(new WindowAdapter() { 
			@Override
			public void windowClosing(WindowEvent e) {
				DBConnection.close();
			    System.exit(0);
			}
		});
	}
}
