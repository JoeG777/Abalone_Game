package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BrettPanel extends JPanel{
	int counter = 0;
	public BrettPanel() {
		setSize(350, 350);
		setLayout(new GridBagLayout());
		
		addToGridBag(new LinienPanel(1),1,1,0,0);
		addToGridBag(new LinienPanel(2),1,2,0,0);
		addToGridBag(new LinienPanel(3),1,3,0,0);
		addToGridBag(new LinienPanel(4),1,4,0,0);
		addToGridBag(new LinienPanel(5),1,5,0,0);
		addToGridBag(new LinienPanel(6),1,6,0,0);
		addToGridBag(new LinienPanel(7),1,7,0,0);
		addToGridBag(new LinienPanel(8),1,8,0,0);
		addToGridBag(new LinienPanel(9),1,9,0,0);
		
		this.setBackground(Color.WHITE);
	}
	
	public void addToGridBag(Component component, int x, int y, double xWeight, double yWeight) {
		GridBagConstraints c = new GridBagConstraints(); 
		c.gridx = x; 
		c.gridy = y; 
		c.weightx = xWeight;
		c.weighty = yWeight;
		/*if(counter%2 == 1) {
			c.ipadx = 25;
		}else {
			c.ipadx =0;
		}*/
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
