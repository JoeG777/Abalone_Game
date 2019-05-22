
package gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class StatusPanel extends JPanel{
	private static final long serialVersionUID = 3L;
	private JTextArea statusText;
	
	
	

	public StatusPanel() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		JLabel label = new JLabel("Status");
		this.add(label,c);
		
		statusText = new JTextArea("Hier steht bald der Spiel-Status.",4, 27);

		statusText.setEditable(false);
		statusText.setLineWrap(true);
		
		c.gridx = 0;
		c.gridy = 1;
		this.add(statusText,c);
	}




	public JTextArea getStatusText() {
		return statusText;
	}

	public void setStatusText(JTextArea statusText) {
		this.statusText = statusText;
	}
	
	public void aktualisiereStatus(String[] spieler1, String spieler2[]) {
		if(spieler1[0] != null && spieler1[0].startsWith("KI-")) {
			spieler1[0] = spieler1[0].substring(0, 4);
		}
		if(spieler2[0] != null && spieler2[0].startsWith("KI-")) {
			spieler2[0] = spieler2[0].substring(0, 4);
		}
		String string = spieler1[0] + " (Weiss)\n" + spieler1[4] + " / 14 Kugeln "
				+ "(Muss " + (Integer.parseInt(spieler2[4]) - 8) + " Kugeln schlagen f�r Sieg)\n"
				+ spieler2[0] + " (Schwarz)\n" + spieler2[4] + " / 14 Kugeln "
				+ "(Muss " + (Integer.parseInt(spieler1[4]) - 8) + " Kugeln schlagen f�r Sieg)";
		statusText.setText(string);
	}
	
	
	
	
}
