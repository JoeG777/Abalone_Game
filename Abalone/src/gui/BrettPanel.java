package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BrettPanel extends JPanel{
	private static Controller controller;
	private ArrayList<LinienPanel> panels;
	public BrettPanel(Controller c) {
		if(controller == null) {
			controller = c;
		}
		setSize(350, 350);
		setLayout(new GridBagLayout());
		panels = new ArrayList<LinienPanel>();
		LinienPanel p1 = new LinienPanel(controller,1);
		addToGridBag(p1,1,1,0,0);
		p1 = new LinienPanel(controller,2);
		addToGridBag(p1,1,2,0,0);
		p1 = new LinienPanel(controller,3);
		addToGridBag(p1,1,3,0,0);
		p1 = new LinienPanel(controller,4);
		addToGridBag(p1,1,4,0,0);
		p1 = new LinienPanel(controller,5);
		addToGridBag(p1,1,5,0,0);
		p1 = new LinienPanel(controller,6);
		addToGridBag(p1,1,6,0,0);
		p1 = new LinienPanel(controller,7);
		addToGridBag(p1,1,7,0,0);
		p1 = new LinienPanel(controller,8);
		addToGridBag(p1,1,8,0,0);
		p1 = new LinienPanel(controller,9);
		addToGridBag(p1,1,9,0,0);
		
		this.setBackground(Color.WHITE);
	}
	
	public void addToGridBag(Component component, int x, int y, double xWeight, double yWeight) {
		GridBagConstraints c = new GridBagConstraints(); 
		c.gridx = x; 
		c.gridy = y; 
		c.weightx = xWeight;
		c.weighty = yWeight;
		panels.add((LinienPanel) component);
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
	
	public void aktualisiere() {
		for(LinienPanel p : panels) {
			p.aktualisiere();
		}
		this.validate();
	}
}
