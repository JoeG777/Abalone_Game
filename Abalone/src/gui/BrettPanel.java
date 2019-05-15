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
		
		addToGridBag(new FeldPanel("B1",2),5,1,0,0);
		addToGridBag(new FeldPanel("B2",2),6,1,0,0);
		addToGridBag(new FeldPanel("B3",2),7,1,0,0);
		addToGridBag(new FeldPanel("B4",2),8,1,0,0);
		addToGridBag(new FeldPanel("B5",2),9,1,0,0);
		
		addToGridBag(new FeldPanel("B1",2),5,2,0,0);
		addToGridBag(new FeldPanel("B2",2),6,2,0,0);
		addToGridBag(new FeldPanel("B3",2),7,2,0,0);
		addToGridBag(new FeldPanel("B4",2),8,2,0,0);
		addToGridBag(new FeldPanel("B5",2),9,2,0,0);
		addToGridBag(new FeldPanel("B1",2),10,2,0,0);
		
		
		addToGridBag(new FeldPanel("B1",0),4,3,0,0);
		addToGridBag(new FeldPanel("B2",0),5,3,0,0);
		addToGridBag(new FeldPanel("B3",2),6,3,0,0);
		addToGridBag(new FeldPanel("B4",2),7,3,0,0);
		addToGridBag(new FeldPanel("B5",2),8,3,0,0);
		addToGridBag(new FeldPanel("B1",0),9,3,0,0);
		addToGridBag(new FeldPanel("B1",0),10,3,0,0);
		
		addToGridBag(new FeldPanel("B1",0),4,4,0,0);
		addToGridBag(new FeldPanel("B2",0),5,4,0,0);
		addToGridBag(new FeldPanel("B3",0),6,4,0,0);
		addToGridBag(new FeldPanel("B4",0),7,4,0,0);
		addToGridBag(new FeldPanel("B5",0),8,4,0,0);
		addToGridBag(new FeldPanel("B1",0),9,4,0,0);
		addToGridBag(new FeldPanel("B1",0),10,4,0,0);
		addToGridBag(new FeldPanel("B1",0),11,4,0,0);
		
		addToGridBag(new FeldPanel("A1",0),3,5,0,0);
		addToGridBag(new FeldPanel("A2",0),4,5,0,0);
		addToGridBag(new FeldPanel("A3",0),5,5,0,0);
		addToGridBag(new FeldPanel("A4",0),6,5,0,0);
		addToGridBag(new FeldPanel("A5",0),7,5,0,0);
		addToGridBag(new FeldPanel("A3",0),8,5,0,0);
		addToGridBag(new FeldPanel("A4",0),9,5,0,0);
		addToGridBag(new FeldPanel("A5",0),10,5,0,0);
		addToGridBag(new FeldPanel("A5",0),11,5,0,0);
		
		addToGridBag(new FeldPanel("B1",0),4,6,0,0);
		addToGridBag(new FeldPanel("B2",0),5,6,0,0);
		addToGridBag(new FeldPanel("B3",0),6,6,0,0);
		addToGridBag(new FeldPanel("B4",0),7,6,0,0);
		addToGridBag(new FeldPanel("B5",0),8,6,0,0);
		addToGridBag(new FeldPanel("B1",0),9,6,0,0);
		addToGridBag(new FeldPanel("B1",0),10,6,0,0);
		addToGridBag(new FeldPanel("B1",0),11,6,0,0);
		

		addToGridBag(new FeldPanel("B2",0),4,7,0,0);
		addToGridBag(new FeldPanel("B3",0),5,7,0,0);
		addToGridBag(new FeldPanel("B4",1),6,7,0,0);
		addToGridBag(new FeldPanel("B5",1),7,7,0,0);
		addToGridBag(new FeldPanel("B1",1),8,7,0,0);
		addToGridBag(new FeldPanel("B5",0),9,7,0,0);
		addToGridBag(new FeldPanel("B1",0),10,7,0,0);
		
		addToGridBag(new FeldPanel("A1",1),5,8,0,0);
		addToGridBag(new FeldPanel("A2",1),6,8,0,0);
		addToGridBag(new FeldPanel("A3",1),7,8,0,0);
		addToGridBag(new FeldPanel("A4",1),8,8,0,0);
		addToGridBag(new FeldPanel("A5",1),9,8,0,0);
		addToGridBag(new FeldPanel("A5",1),10,8,0,0);
		
		addToGridBag(new FeldPanel("B1",1),5,9,0,0);
		addToGridBag(new FeldPanel("B2",1),6,9,0,0);
		addToGridBag(new FeldPanel("B3",1),7,9,0,0);
		addToGridBag(new FeldPanel("B4",1),8,9,0,0);
		addToGridBag(new FeldPanel("B5",1),9,9,0,0);
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
