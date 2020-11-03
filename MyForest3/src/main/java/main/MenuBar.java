package main;
import javax.swing.*;

import java.awt.*;
import java.net.URL;


/**
 * 
 * @author Gruppe 1
 * Menubar used for navigation between pages 
 * 
 *
 */
public class MenuBar extends JPanel {
	private JButton mainButton, shopButton, chatButton, problemsButton, tasksPage ;
	
	public MenuBar(JFrame parentFrame ) {
		setLayout(new GridLayout(1,5));
		URL url = MenuBar.class.getResource("/Icon.png");
	    Icon icon = new ImageIcon(url);
	    
	    
		mainButton 		= new JButton(icon);
		shopButton 		= new JButton(StartClass.translation.getString("Shop"));
		chatButton 		= new JButton(StartClass.translation.getString("Chat"));
		problemsButton 	= new JButton(StartClass.translation.getString("Probleme"));
		tasksPage	 	= new JButton(StartClass.translation.getString("Aufgaben"));
		
		
	    mainButton.addActionListener(new NavButtonListener( parentFrame, Pages.MAIN_MENU));
	    shopButton.addActionListener(new NavButtonListener( parentFrame, Pages.SHOP_PAGE));
	    chatButton.addActionListener(new NavButtonListener( parentFrame, Pages.CHAT_PAGE));
	    problemsButton.addActionListener(new NavButtonListener( parentFrame, Pages.PROBLEM_PAGE));
	    tasksPage.addActionListener(new NavButtonListener( parentFrame, Pages.TASK_PAGE));
	    
	    tasksPage.setBackground(Color.WHITE);
	    mainButton.setBackground(Color.WHITE);
	    problemsButton.setBackground(Color.WHITE);
	    shopButton.setBackground(Color.WHITE);
	    chatButton.setBackground(Color.WHITE);
	    
	    setBackground(Color.white);
	    
	    add(problemsButton);
	    add(tasksPage);
	    add(mainButton);
	    add(shopButton);
	    add(chatButton);
	}
}
