package main;
import java.awt.*;
import java.net.URL;
import javax.swing.*;

/**
 * 
 * @author Gruppe 1
 * Container to display Main Menu with weather and map
 */
public class MainMenu extends Container  {

	private MenuBar menuBar;
	private JPanel panelMiddle;
	private JLabel labelWeather,labelMap;
	private JFrame parentFrame;


	public MainMenu (JFrame parentframe){
		this.parentFrame = parentframe;
		setLayout(new BorderLayout());
        menuBar		 	= new MenuBar(parentFrame);

        URL weatherUrl 	= MainMenu.class.getResource("/weatherImage.png");
        URL mapUrl 		= MainMenu.class.getResource("/map.png");
        Icon weather 	= new ImageIcon(weatherUrl);
        Icon map 		= new ImageIcon(mapUrl);
        
        labelWeather 	= new JLabel(weather);
        labelMap 		= new JLabel(map);
        
        panelMiddle= new JPanel(new GridLayout(2,1));
        panelMiddle.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0; 
        gbc.gridheight = 1;
        panelMiddle.add(labelWeather, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1; 
        gbc.gridheight = 2;
        gbc.insets = new Insets(10, 0, 0, 0);
        panelMiddle.add(labelMap, gbc);
        
        add(menuBar, BorderLayout.NORTH);
        add(panelMiddle, BorderLayout.CENTER);
        setVisible(true);
    }
}
