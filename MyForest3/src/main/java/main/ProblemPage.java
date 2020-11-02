package main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * 
 * @author Gruppe 1 
 * Container to display Problems in a table 
 *
 */

//will be extended so one can click a button to set the status to in progress

public class ProblemPage extends Container{
	
	private JPanel panelMiddle;
	private MenuBar menuBar;
	private JTable table;
	private JScrollPane scrollpane;
	private JButton btnAdd, refreshButton;
	List<Problem> problems; 
	List<Area> areas; 
	List<Status> statuses; 
	DBConnection dbConnection;
	
	
	public ProblemPage(JFrame parentFrame) {
		
		if(problems == null) {
			getData();
		}
		
		btnAdd = new JButton("+");
		btnAdd.addActionListener(new ButtonListener());
		
		refreshButton = new JButton("refresh");
		refreshButton.addActionListener(new NavButtonListener(parentFrame, Pages.PROBLEM_PAGE));
		if (!problems.isEmpty()) {
		table = new JTable();
		ProblemTableModel tableModel = new ProblemTableModel(problems, areas, statuses);
		table.setModel(tableModel);
		table.setSize(200,200);
		
		table.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
			    if (e.getClickCount() == 2) {
			      JTable target = (JTable)e.getSource();
			      int row = target.getSelectedRow();
			      ProblemTableModel tableModel = (ProblemTableModel) table.getModel();
			      Problem selectedProblem = tableModel.getProblem(row);
			      
			      ChangeStatusForm fenster = new ChangeStatusForm(selectedProblem);
			        fenster.setSize(500, 300);
			        fenster.setVisible(true);
			    }
			  }
			});
		
		scrollpane = new JScrollPane(table);
		panelMiddle = new JPanel();
		}else {
	        panelMiddle = new JPanel();
			JLabel warnung = new JLabel("Keine Probleme vorhanden");
			panelMiddle.add(warnung);
			}
		panelMiddle.add(btnAdd);
		panelMiddle.add(refreshButton);
		panelMiddle.add(scrollpane);
		
		
		setLayout(new BorderLayout());
		
        menuBar = new MenuBar(parentFrame);
        
        add(menuBar, BorderLayout.NORTH);
        add(panelMiddle, BorderLayout.CENTER);
        setVisible(true);

	}
	private void getData(){
			dbConnection = new DBConnection();
			problems = dbConnection.getAllProblems();
			areas = dbConnection.getAllAreas();
			statuses = dbConnection.getAllStatuses();
	 }
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			AddProblemForm fenster = new AddProblemForm();
	        fenster.setTitle("Problem hinzufuegen");
	        fenster.setSize(500, 300);
	        fenster.setVisible(true);
		}
		
	}

}
