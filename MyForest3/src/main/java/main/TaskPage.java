package main;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
/**
 * 
 * @author Gruppe 1
 * Container to display Tasks in a table
 */

public class TaskPage extends Container{
	
	private JFrame parentFrame;
	private JPanel  panelMiddle;
	private MenuBar menuBar;
	private JTable table;
	private JScrollPane scrollpane;
	private JButton refreshButton;
	private List<Problem> problemsInProgress;
	List<Area> areas; 
	List<Status> statuses; 
	DBConnection dbConnection;
	
	public TaskPage(JFrame parentFrame) {
		
		if(problemsInProgress == null) {
			getData();
		}
		
		if (!problemsInProgress.isEmpty()) {
			table = new JTable();
			ProblemTableModel tableModel = new ProblemTableModel(problemsInProgress, areas, statuses);
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
			
			refreshButton = new JButton("refresh");
			refreshButton.addActionListener(new NavButtonListener(parentFrame, Pages.TASK_PAGE));
			
			scrollpane = new JScrollPane(table);
			panelMiddle = new JPanel();
			panelMiddle.add(refreshButton);
			panelMiddle.add(scrollpane);
			
	        
		}else {
        panelMiddle = new JPanel();
		JLabel warnung = new JLabel("Keine Aufgaben in Bearbeitung");
		panelMiddle.add(warnung);
		}
		
		this.parentFrame = parentFrame;
		setLayout(new BorderLayout());
		
        menuBar = new MenuBar(this.parentFrame);
        
        add(menuBar, BorderLayout.NORTH);
        add(panelMiddle, BorderLayout.CENTER);
        setVisible(true);
	}
	
	private void getData(){
			dbConnection = new DBConnection();
			problemsInProgress= dbConnection.getProblemsInProgress();
			areas = dbConnection.getAllAreas();
			statuses = dbConnection.getAllStatuses();
	 }
	
	}
