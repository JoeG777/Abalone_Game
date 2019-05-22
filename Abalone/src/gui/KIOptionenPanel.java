package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class KIOptionenPanel extends JPanel {
	private JLabel infoLabel, ki1Label, ki2Label;
	private JButton ki1Weiter, ki2Weiter, ki1Durchziehend, ki2Durchziehend;
	
	public KIOptionenPanel() {
		this.setLayout(new GridBagLayout());
		
		infoLabel = new JLabel("KI-Optionen");
		addToGridBag(infoLabel, 0, 0, 2 ,1, GridBagConstraints.REMAINDER);
//		infoLabel.setBackground(Color.white);
//		infoLabel.setOpaque(true);
		
		ki1Label = new JLabel("KI-1        ");
		ki1Label.setBackground(Color.white);
		ki1Label.setOpaque(true);
		addToGridBag(ki1Label, 0,1);
		
		ki1Weiter = new JButton("Weiter");
		ki1Weiter.setOpaque(true);
		ki1Weiter.setContentAreaFilled(false);
		addToGridBag(ki1Weiter, 1, 1);
		
		ki1Durchziehend = new JButton("Ziehe durch");
		ki1Durchziehend.setOpaque(true);
		ki1Durchziehend.setContentAreaFilled(false);
		addToGridBag(ki1Durchziehend, 2, 1);
		
		ki2Label = new JLabel("KI-2                       ");
		ki2Label.setOpaque(true);
		ki2Label.setBackground(Color.WHITE);
		addToGridBag(ki2Label, 0,2);
		

		ki2Weiter = new JButton("Weiter");
		ki2Weiter.setOpaque(true);
		ki2Weiter.setContentAreaFilled(false);
		addToGridBag(ki2Weiter, 1,2);
		
	
		ki2Durchziehend = new JButton("Ziehe durch");
		ki2Durchziehend.setOpaque(true);
		ki2Durchziehend.setContentAreaFilled(false);
		addToGridBag(ki2Durchziehend, 2, 2);
		
		
	}
	
	private void addToGridBag(Component comp, int x, int y) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx= x;
		c.gridy = y; 
		c.fill = 1; 
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		this.add(comp,c);
	}
	
	private void addToGridBag(Component comp, int x, int y, int width, int height, int fill) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx= x;
		c.gridy = y; 
		c.fill = fill; 
		c.gridwidth = width;
		c.gridheight = height;
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		this.add(comp,c);
	}
}
