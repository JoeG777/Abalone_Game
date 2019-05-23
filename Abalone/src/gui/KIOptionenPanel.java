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
	private JLabel infoLabel, kiLabel;
	private JButton kiWeiter, kiDurchziehend;
	
	private EventHandlerKIOptionen eventHandlerKIOptionen;
	public KIOptionenPanel(Controller c) {
		this.setLayout(new GridBagLayout());
		eventHandlerKIOptionen = new EventHandlerKIOptionen(c,this);
		initLabel();
		initKILabelButtons();
		steuereKIPanel(null, false);
		

	}
	
	private void initLabel() {
		infoLabel = new JLabel("KI-Optionen");
		addToGridBag(infoLabel, 0, 0, 2 ,1, GridBagConstraints.REMAINDER);
	}
	
	private void initKILabelButtons() {
		kiLabel = new JLabel();
		kiWeiter = new JButton();
		kiDurchziehend = new JButton();
	}
	

	
	public void steuereKIPanel(String kiName, boolean aktiviert) {
		if(aktiviert) {
			aktiviereKIPanel(kiName);
		}
		else {
			deaktiviereKIPanel();
		}
	}
	
	private void aktiviereKIPanel(String kiName) {
		kiLabel.setText((kiName + "                       "));
		kiLabel.setOpaque(true);
		kiLabel.setBackground(Color.WHITE);
		
		
		kiWeiter.setText("Weiter");
		kiWeiter.addActionListener(eventHandlerKIOptionen);
		kiWeiter.setOpaque(true);
		kiWeiter.setContentAreaFilled(false);
		kiWeiter.setEnabled(true);
		
		
		kiDurchziehend.setText("Ziehe durch");
		kiDurchziehend.setOpaque(true);
		kiDurchziehend.setContentAreaFilled(false);
		kiDurchziehend.setEnabled(true);
		
		if(kiName.length() > 4) {
			kiDurchziehend.setEnabled(false);
			kiWeiter.setEnabled(false);
			kiLabel.setText("(Zieht durch)     ");
		}
		else {
			kiDurchziehend.addActionListener(eventHandlerKIOptionen);
		}
		addToGridBag(kiLabel, 0,1);
		addToGridBag(kiWeiter, 1,1);
		addToGridBag(kiDurchziehend, 2, 1);
		
		aktualisiere();
	}
	
	private void deaktiviereKIPanel() {
		kiLabel.setText("(KI nicht am Zug)     ");
		kiLabel.setOpaque(true);
		kiLabel.setBackground(Color.WHITE);
		addToGridBag(kiLabel, 0,1);
		
		kiWeiter.setText("inaktiv  ");
		kiWeiter.setOpaque(true);
		kiWeiter.setContentAreaFilled(false);
		kiWeiter.setEnabled(false);
		addToGridBag(kiWeiter, 1,1);
		
		kiDurchziehend.setText("inaktiv  ");
		kiDurchziehend.setOpaque(true);
		kiDurchziehend.setContentAreaFilled(false);
		kiDurchziehend.setEnabled(false);
		addToGridBag(kiDurchziehend, 2, 1);
		
		aktualisiere();
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

	public JButton getKiWeiter() {
		return kiWeiter;
	}

	public void setKiWeiter(JButton kiWeiter) {
		this.kiWeiter = kiWeiter;
	}

	public JButton getKiDurchziehend() {
		return kiDurchziehend;
	}

	public void setKiDurchziehend(JButton kiDurchziehend) {
		this.kiDurchziehend = kiDurchziehend;
	}
	
	

	
	
}
