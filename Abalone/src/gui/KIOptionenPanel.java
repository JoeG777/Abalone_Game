package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class KIOptionenPanel extends JPanel {
	private JLabel infoLabel, kiLabel;
	private JButton kiWeiter, kiDurchziehend;
	private Font coalition, coalition2;
	
	private EventHandlerKIOptionen eventHandlerKIOptionen;
	public KIOptionenPanel(Controller c) {
		try {
			coalition = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("AbaloneSchrift.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(coalition);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		coalition = new Font("Coalition", Font.PLAIN, 10);
		coalition2 = new Font("Coalition", Font.PLAIN, 14);
		
		this.setLayout(new GridBagLayout());
		eventHandlerKIOptionen = new EventHandlerKIOptionen(c,this);
		initLabel();
		initKILabelButtons();
		steuereKIPanel(null, false);
		

	}
	
	private void initLabel() {
		if(infoLabel == null) {
			infoLabel = new JLabel("KI-Optionen");
			infoLabel.setBackground(Color.DARK_GRAY);
			infoLabel.setForeground(Color.WHITE);
			infoLabel.setFont(coalition2);
			addToGridBag(infoLabel, 0, 0, 2 ,1, GridBagConstraints.REMAINDER);
		}
		
	}
	
	private void initKILabelButtons() {
		if(kiLabel == null) {
			kiLabel = new JLabel();
			kiLabel.setBackground(Color.DARK_GRAY);
			kiLabel.setForeground(Color.WHITE);
			kiLabel.setFont(coalition);
			addToGridBag(kiLabel, 0,1);
		}
		
		if(kiWeiter == null) {
			kiWeiter = new JButton();
			kiWeiter.setBackground(Color.DARK_GRAY);
			kiWeiter.setForeground(Color.WHITE);
			kiWeiter.setFont(coalition);
			kiWeiter.addActionListener(eventHandlerKIOptionen);
			addToGridBag(kiWeiter, 1,1);
		}
		
		if(kiDurchziehend == null)  {
			kiDurchziehend = new JButton();
			kiDurchziehend.setBackground(Color.DARK_GRAY);
			kiDurchziehend.setForeground(Color.WHITE);
			kiDurchziehend.setFont(coalition);
			kiDurchziehend.addActionListener(eventHandlerKIOptionen);
			addToGridBag(kiDurchziehend, 2, 1);
		}

	
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
		kiLabel.setText((kiName + "             "));
		kiLabel.setOpaque(true);
		kiLabel.setBackground(Color.DARK_GRAY);
		kiLabel.setForeground(Color.WHITE);
		
		
		kiWeiter.setText("Weiter");
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

		
		aktualisiere();
	}
	
	private void deaktiviereKIPanel() {
		kiLabel.setText("(KI nicht am Zug)  ");
		kiLabel.setOpaque(true);
		kiLabel.setBackground(Color.DARK_GRAY);
		kiLabel.setForeground(Color.WHITE);
		
		
		kiWeiter.setText("inaktiv  ");
		kiWeiter.setOpaque(true);
		kiWeiter.setContentAreaFilled(false);
		kiWeiter.setEnabled(false);
		
		
		kiDurchziehend.setText("inaktiv  ");
		kiDurchziehend.setOpaque(true);
		kiDurchziehend.setContentAreaFilled(false);
		kiDurchziehend.setEnabled(false);
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
