package gui;

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
		
		addToGridBag(new FeldPanel("B1"),5,1,0,0);
		addToGridBag(new FeldPanel("B2"),6,1,0,0);
		addToGridBag(new FeldPanel("B3"),7,1,0,0);
		addToGridBag(new FeldPanel("B4"),8,1,0,0);
		addToGridBag(new FeldPanel("B5"),9,1,0,0);
		
		addToGridBag(new FeldPanel("B1"),5,2,0,0);
		addToGridBag(new FeldPanel("B2"),6,2,0,0);
		addToGridBag(new FeldPanel("B3"),7,2,0,0);
		addToGridBag(new FeldPanel("B4"),8,2,0,0);
		addToGridBag(new FeldPanel("B5"),9,2,0,0);
		addToGridBag(new FeldPanel("B1"),10,2,0,0);
		
		
		addToGridBag(new FeldPanel("B1"),4,3,0,0);
		addToGridBag(new FeldPanel("B2"),5,3,0,0);
		addToGridBag(new FeldPanel("B3"),6,3,0,0);
		addToGridBag(new FeldPanel("B4"),7,3,0,0);
		addToGridBag(new FeldPanel("B5"),8,3,0,0);
		addToGridBag(new FeldPanel("B1"),9,3,0,0);
		addToGridBag(new FeldPanel("B1"),10,3,0,0);
		
		addToGridBag(new FeldPanel("B1"),4,4,0,0);
		addToGridBag(new FeldPanel("B2"),5,4,0,0);
		addToGridBag(new FeldPanel("B3"),6,4,0,0);
		addToGridBag(new FeldPanel("B4"),7,4,0,0);
		addToGridBag(new FeldPanel("B5"),8,4,0,0);
		addToGridBag(new FeldPanel("B1"),9,4,0,0);
		addToGridBag(new FeldPanel("B1"),10,4,0,0);
		addToGridBag(new FeldPanel("B1"),11,4,0,0);
		
		addToGridBag(new FeldPanel("A1"),3,5,0,0);
		addToGridBag(new FeldPanel("A2"),4,5,0,0);
		addToGridBag(new FeldPanel("A3"),5,5,0,0);
		addToGridBag(new FeldPanel("A4"),6,5,0,0);
		addToGridBag(new FeldPanel("A5"),7,5,0,0);
		addToGridBag(new FeldPanel("A3"),8,5,0,0);
		addToGridBag(new FeldPanel("A4"),9,5,0,0);
		addToGridBag(new FeldPanel("A5"),10,5,0,0);
		addToGridBag(new FeldPanel("A5"),11,5,0,0);
		
		addToGridBag(new FeldPanel("B1"),4,6,0,0);
		addToGridBag(new FeldPanel("B2"),5,6,0,0);
		addToGridBag(new FeldPanel("B3"),6,6,0,0);
		addToGridBag(new FeldPanel("B4"),7,6,0,0);
		addToGridBag(new FeldPanel("B5"),8,6,0,0);
		addToGridBag(new FeldPanel("B1"),9,6,0,0);
		addToGridBag(new FeldPanel("B1"),10,6,0,0);
		addToGridBag(new FeldPanel("B1"),11,6,0,0);
		

		addToGridBag(new FeldPanel("B2"),4,7,0,0);
		addToGridBag(new FeldPanel("B3"),5,7,0,0);
		addToGridBag(new FeldPanel("B4"),6,7,0,0);
		addToGridBag(new FeldPanel("B5"),7,7,0,0);
		addToGridBag(new FeldPanel("B1"),8,7,0,0);
		addToGridBag(new FeldPanel("B5"),9,7,0,0);
		addToGridBag(new FeldPanel("B1"),10,7,0,0);
		
		addToGridBag(new FeldPanel("A1"),5,8,0,0);
		addToGridBag(new FeldPanel("A2"),6,8,0,0);
		addToGridBag(new FeldPanel("A3"),7,8,0,0);
		addToGridBag(new FeldPanel("A4"),8,8,0,0);
		addToGridBag(new FeldPanel("A5"),9,8,0,0);
		addToGridBag(new FeldPanel("A5"),10,8,0,0);
		
		addToGridBag(new FeldPanel("B1"),5,9,0,0);
		addToGridBag(new FeldPanel("B2"),6,9,0,0);
		addToGridBag(new FeldPanel("B3"),7,9,0,0);
		addToGridBag(new FeldPanel("B4"),8,9,0,0);
		addToGridBag(new FeldPanel("B5"),9,9,0,0);
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
