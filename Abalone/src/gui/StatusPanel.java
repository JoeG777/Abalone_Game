
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
	
	public void aktualisiereStatus(String spieler1name, String spieler2name, String[] status) {
		String string = spieler1name+"(Weiss)\nMuss noch " + status[1] + " Kugeln schlagen"
				+ "\n" + spieler2name + "(Schwarz)\nMuss noch " + status[0] + " Kugeln schlagen";
		statusText.setText(string);
	}
	
	
	
	
}
