package gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BrettPanel extends JPanel{
		
	public BrettPanel() {
		setSize(350, 350);
		setLayout(new GridBagLayout());
		
		addToGridBag(new JLabel(""),0,0,0,0, 100);
		addToGridBag(new FeldPanel("A1"),1,0,0,0);
		addToGridBag(new FeldPanel("A2"),2,0,0,0);
		addToGridBag(new FeldPanel("A3"),3,0,0,0);
		addToGridBag(new FeldPanel("A4"),4,0,0,0);
		addToGridBag(new FeldPanel("A5"),5,0,0,0);
		
		addToGridBag(new FeldPanel("B1"),0,1,0,0);
		addToGridBag(new FeldPanel("B2"),1,1,0,0);
		addToGridBag(new FeldPanel("B3"),2,1,0,0);
		addToGridBag(new FeldPanel("B4"),3,1,0,0);
		addToGridBag(new FeldPanel("B5"),4,1,0,0);
		addToGridBag(new FeldPanel("B1"),5,1,0,0);
	}
	
	public void addToGridBag(Component component, int x, int y, double xWeight, double yWeight) {
		GridBagConstraints c = new GridBagConstraints(); 
		c.gridx = x; 
		c.gridy = y; 
		c.weightx = xWeight;
		c.weighty = yWeight;
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
