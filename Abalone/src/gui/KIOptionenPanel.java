package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class KIOptionenPanel extends JPanel {
	private JLabel infoLabel, ki1Label, ki2Label;
	private JButton ki1Weiter, ki2Weiter, ki1Durchziehend, ki2Durchziehend;
	private EventHandlerKIOptionen eventHandlerKIOptionen;
	public KIOptionenPanel(Controller c) {
		this.setLayout(new GridBagLayout());
		eventHandlerKIOptionen = new EventHandlerKIOptionen(c,this);
		initLabel();
		initKI1();
		initKI2();
	}
	
	private void initLabel() {
		infoLabel = new JLabel("KI-Optionen");
		addToGridBag(infoLabel, 0, 0, 2 ,1, GridBagConstraints.REMAINDER);
	}
	
	private void initKI1() {
		ki1Label = new JLabel("KI-1                       ");
		ki1Label.setBackground(Color.white);
		ki1Label.setOpaque(true);
		addToGridBag(ki1Label, 0,1);
		
		ki1Weiter = new JButton("Weiter");
		ki1Weiter.addActionListener(eventHandlerKIOptionen);
		ki1Weiter.setOpaque(true);
		ki1Weiter.setContentAreaFilled(false);
		addToGridBag(ki1Weiter, 1, 1);
		
		ki1Durchziehend = new JButton("Ziehe durch");
		ki1Durchziehend.addActionListener(eventHandlerKIOptionen);
		ki1Durchziehend.setOpaque(true);
		ki1Durchziehend.setContentAreaFilled(false);
		addToGridBag(ki1Durchziehend, 2, 1);
	}
	
	private void initKI2() {
		ki2Label = new JLabel("KI-2                       ");
		ki2Label.setOpaque(true);
		ki2Label.setBackground(Color.WHITE);
		addToGridBag(ki2Label, 0,2);
		

		ki2Weiter = new JButton("Weiter");
		ki2Weiter.addActionListener(eventHandlerKIOptionen);
		ki2Weiter.setOpaque(true);
		ki2Weiter.setContentAreaFilled(false);
		addToGridBag(ki2Weiter, 1,2);
		
	
		ki2Durchziehend = new JButton("Ziehe durch");
		ki2Durchziehend.addActionListener(eventHandlerKIOptionen);
		ki2Durchziehend.setOpaque(true);
		ki2Durchziehend.setContentAreaFilled(false);
		addToGridBag(ki2Durchziehend, 2, 2);
	}
	
	public void aktiviereKI1() {
		ki1Label.setText("KI-1                       ");
		ki1Weiter.setText("Weiter");
		ki1Weiter.setEnabled(true);
		ki1Durchziehend.setText("Ziehe durch");
		ki1Durchziehend.setEnabled(true);
	}
	
	public void aktiviereKI2() {
		ki2Label.setText("KI-2                       ");
		ki2Weiter.setText("Weiter");
		ki2Weiter.setEnabled(true);
		ki2Durchziehend.setText("Ziehe durch");
		ki2Durchziehend.setEnabled(true);
	}
	public void deaktiviereKI1() {
		ki1Label.setText("deaktiviert      ");
		ki1Weiter.setText("deaktiviert");
		ki1Weiter.setEnabled(false);
		ki1Durchziehend.setText("deaktiviert");
		ki1Durchziehend.setEnabled(false);
	}
	public void deaktiviereKI2() {
		ki2Label.setText("deaktiviert      ");
		ki2Weiter.setText("deaktiviert");
		ki2Weiter.setEnabled(false);
		ki2Durchziehend.setText("deaktiviert");
		ki2Durchziehend.setEnabled(false);
	}
	
	public void aktiviereDurchziehendKI1() {
		ki1Label.setText("KI-1          ");
		ki2Label.setText("KI-2          ");
		ki1Weiter.setText("durchziehend");
		ki1Weiter.setEnabled(false);
		ki1Durchziehend.setText("durchziehend");
		ki1Durchziehend.setEnabled(false);
	}
	
	public void aktiviereDurchziehendKI2() {
		ki2Label.setText("KI-2         ");
		ki1Label.setText("KI-1         ");
		ki2Weiter.setText("durchziehend");
		ki2Weiter.setEnabled(false);
		ki2Durchziehend.setText("durchziehend");
		ki2Durchziehend.setEnabled(false);
	}
	
	public void aktualisiere() {
		this.repaint();
		this.revalidate();
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

	public JLabel getInfoLabel() {
		return infoLabel;
	}

	public void setInfoLabel(JLabel infoLabel) {
		this.infoLabel = infoLabel;
	}

	public JLabel getKi1Label() {
		return ki1Label;
	}

	public void setKi1Label(JLabel ki1Label) {
		this.ki1Label = ki1Label;
	}

	public JLabel getKi2Label() {
		return ki2Label;
	}

	public void setKi2Label(JLabel ki2Label) {
		this.ki2Label = ki2Label;
	}

	public JButton getKi1Weiter() {
		return ki1Weiter;
	}

	public void setKi1Weiter(JButton ki1Weiter) {
		this.ki1Weiter = ki1Weiter;
	}

	public JButton getKi2Weiter() {
		return ki2Weiter;
	}

	public void setKi2Weiter(JButton ki2Weiter) {
		this.ki2Weiter = ki2Weiter;
	}

	public JButton getKi1Durchziehend() {
		return ki1Durchziehend;
	}

	public void setKi1Durchziehend(JButton ki1Durchziehend) {
		this.ki1Durchziehend = ki1Durchziehend;
	}

	public JButton getKi2Durchziehend() {
		return ki2Durchziehend;
	}

	public void setKi2Durchziehend(JButton ki2Durchziehend) {
		this.ki2Durchziehend = ki2Durchziehend;
	}
	
	
}
