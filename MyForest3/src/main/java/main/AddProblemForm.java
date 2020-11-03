package main;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 
 * @author Gruppe 1
 * Form to add a new Problem to the database
 *
 */
public class AddProblemForm extends JFrame {
	private JScrollPane scrollpane, scrollpane2, scrollpane3;
	private JComboBox<String> baummenu;
	private JComboBox<Integer> gebietmenu;
	private JTextArea standortTextArea, bildTextArea, problemTextArea;
	private JLabel labelProblem, labelBaumart, labelStandort, labelBild, labelGebiet;
        private JFrame frame = this;
        
	public AddProblemForm() {
		Container c = getContentPane();
        c.setLayout(new GridLayout(10, 1));
        String[] namen = new String []
                {StartClass.translation.getString("Eiche"), StartClass.translation.getString("Buche"), StartClass.translation.getString("Tanne"), StartClass.translation.getString("Ahorn"), StartClass.translation.getString("Kastanie")};
        Integer[] gebiet = new Integer []
                {1,2,3,4,5,6,7,8,9};
        labelProblem = new JLabel(StartClass.translation.getString("Problembeschreibung"));
        labelBaumart = new JLabel(StartClass.translation.getString("Baumart"));
        labelGebiet = new JLabel(StartClass.translation.getString("Gebiet"));
        labelStandort = new JLabel(StartClass.translation.getString("Standortkoordinaten"));
        labelBild = new JLabel(StartClass.translation.getString("Bild"));
        problemTextArea = new JTextArea();
        problemTextArea.setLineWrap(true);
        scrollpane = new JScrollPane(problemTextArea);
        baummenu = new JComboBox<String>(namen);
        gebietmenu = new JComboBox<Integer>(gebiet);
        standortTextArea = new JTextArea();
        scrollpane2 = new JScrollPane(standortTextArea);
        bildTextArea = new JTextArea();
        scrollpane3 = new JScrollPane(bildTextArea);
        JButton knopf = new JButton(StartClass.translation.getString("Hinzufuegen"));
        knopf.addActionListener(new ButtonListener());
        c.add(labelProblem);
        c.add(scrollpane);
        c.add(labelBaumart);
        c.add(baummenu);
        c.add(labelGebiet);
        c.add(gebietmenu);
        c.add(labelStandort);
        c.add(scrollpane2);
        c.add(labelBild);
        c.add(scrollpane3);
        c.add(knopf);
        
	}

	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			try {
				String description = problemTextArea.getText();
				String baum = (String) baummenu.getSelectedItem();
				
				int area = (int) gebietmenu.getSelectedItem();
 
				DBConnection dbConnection = new DBConnection();
				dbConnection.insertProblem(new Problem(0, description, area, 4, baum));
				
				frame.dispose();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
