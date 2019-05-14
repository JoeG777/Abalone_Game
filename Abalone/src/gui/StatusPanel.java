
package gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class StatusPanel extends JPanel{
	private static final long serialVersionUID = 3L;
	
	public StatusPanel() {
		GridLayout experimentLayout = new GridLayout(0,1);
		this.setLayout(experimentLayout);
		JLabel label = new JLabel("Status");
		this.add(label);
		JTextArea textArea = new JTextArea(5, 20);
		JScrollPane scrollPane = new JScrollPane(textArea); 
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		this.add(scrollPane);
	}
}
