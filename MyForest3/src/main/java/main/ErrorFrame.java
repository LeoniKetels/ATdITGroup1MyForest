package main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorFrame extends JFrame {
	
	public ErrorFrame(String errorText, String solutionRecommendation, String messageFromException) {
		JOptionPane.showMessageDialog(this, errorText +"\n" + solutionRecommendation +"\n" + messageFromException, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
