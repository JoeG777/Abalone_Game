package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BrettPanel extends JPanel{
	private static Controller controller;
	int counter = 0;
	public BrettPanel(Controller c) {
		if(controller == null) {
			controller = c;
		}
		setSize(350, 350);
		setLayout(new GridBagLayout());
		
		addToGridBag(new LinienPanel(controller,1),1,1,0,0);
		addToGridBag(new LinienPanel(controller,2),1,2,0,0);
		addToGridBag(new LinienPanel(controller,3),1,3,0,0);
		addToGridBag(new LinienPanel(controller,4),1,4,0,0);
		addToGridBag(new LinienPanel(controller,5),1,5,0,0);
		addToGridBag(new LinienPanel(controller,6),1,6,0,0);
		addToGridBag(new LinienPanel(controller,7),1,7,0,0);
		addToGridBag(new LinienPanel(controller,8),1,8,0,0);
		addToGridBag(new LinienPanel(controller,9),1,9,0,0);
		
		this.setBackground(Color.WHITE);
	}
	
	public void addToGridBag(Component component, int x, int y, double xWeight, double yWeight) {
		GridBagConstraints c = new GridBagConstraints(); 
		c.gridx = x; 
		c.gridy = y; 
		c.weightx = xWeight;
		c.weighty = yWeight;
		counter++;
		this.add(component,c);
	}
	
	public void addToGridBag(Component component, int x, int y, double xWeight, double yWeight, int width) {
		GridBagConstraints c = new GridBagConstraints(); 
		c.gridx = x; 
		c.gridy = y; 
		c.weightx = xWeight;
		c.weighty = yWeight;
		c.gridwidth = width;
	}
}
