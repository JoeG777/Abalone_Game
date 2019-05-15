
package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class StatusPanel extends JPanel{
	private static final long serialVersionUID = 3L;
	
	public StatusPanel() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		JLabel label = new JLabel("Status");
		this.add(label,c);
		
		JTextArea textArea = new JTextArea(8, 25);
		JScrollPane scrollPane = new JScrollPane(textArea); 
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		
		c.gridx = 0;
		c.gridy = 1;
		this.add(textArea,c);
	}
}
