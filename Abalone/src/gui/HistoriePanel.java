package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HistoriePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextArea historieText;
	
	public HistoriePanel() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		JLabel label = new JLabel("Historie");
		this.add(label,c);
		
		historieText = new JTextArea(25, 25);
		JScrollPane scrollPane = new JScrollPane(historieText); 
		historieText.setEditable(false);
		historieText.setLineWrap(true);
		
		c.gridx = 0;
		c.gridy = 1;
		this.add(historieText,c);
	}

	public JTextArea getHistorieText() {
		return historieText;
	}

	public void setHistorieText(JTextArea historieText) {
		this.historieText = historieText;
	}
	
	public void aktualisiereHistorie(String historie) {
		historieText.setText(historie);
	}
	
	
	
}
