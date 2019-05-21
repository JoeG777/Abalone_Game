
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
		
		statusText = new JTextArea(4, 25);

		statusText.setEditable(false);
		statusText.setLineWrap(true);
		
		statusText.append("Spieler 1:\n");
		statusText.append("Muss noch XX Kugeln schlagen.\n");
		statusText.append("Spieler 2:\n");
		statusText.append("Muss noch XX Kugeln schlagen.\n");
		
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
	
	
	
	
}
