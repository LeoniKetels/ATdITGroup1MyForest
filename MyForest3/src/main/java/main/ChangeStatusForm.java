package main;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ChangeStatusForm extends JFrame{
	private JFrame frame = this;
	JLabel labelCurrentStatus;
	
	public ChangeStatusForm(Problem problem) {
		this.setTitle(problem.getDescription() + ": Status aendern");
		String statusDescription = "";
		Container c = getContentPane();
		c.setLayout(new GridLayout(3,1));
		DBConnection dbConnection;
			dbConnection = new DBConnection();
			int status = problem.getStatus_id();
	        statusDescription = dbConnection.getStatusById(status).getDescription();
			

	    labelCurrentStatus = new JLabel("jetziger Status: " + statusDescription);
		
		String[] statuses = {StartClass.translation.getString("Offen"),
							 StartClass.translation.getString("inBearbeitung"),
							 StartClass.translation.getString("Bearbeitet"), 
							 StartClass.translation.getString("inWarteschlange")};
		JComboBox<String> statusMenu = new JComboBox<String>(statuses);
		
		JButton save = new JButton("Aenderungen speichern");
		save.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if((statusMenu.getSelectedIndex() +1) != problem.getStatus_id()) {
					int index = statusMenu.getSelectedIndex();
					int statusId = index +1; 
						DBConnection dbConnection = new DBConnection();
						dbConnection.changeStatus(problem.getId(),statusId );
				}
				frame.dispose();
			}
		});
		
		c.add(labelCurrentStatus);
		c.add(statusMenu);
		c.add(save);
		
		
		
	
	}
}
