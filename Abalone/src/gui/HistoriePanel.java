package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 * Das Historie-Panel erbt vom JPanel und dient zur
 * Visualisierung der Historie
 * 
 * @author Gruppe A4
 *
 */
public class HistoriePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextArea historieText;
	private Font coalition , coalition2;
	
	/**
	 * Konstruktor des HistoriePanel.
	 * 
	 */
	public HistoriePanel() {
		try {
			coalition = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("AbaloneSchrift.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(coalition);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
		coalition = new Font("Coalition", Font.PLAIN, 12);
		coalition2 = new Font("Coalition", Font.PLAIN, 12);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		JLabel label = new JLabel("Historie");
		label.setForeground(Color.WHITE);
		label.setFont(coalition);
		this.add(label,c);
		
		historieText = new JTextArea(25, 25);
		historieText.setFont(coalition2);
		historieText.setBackground(Color.WHITE);
		historieText.setForeground(Color.BLACK);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(historieText);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		historieText.setEditable(false);
		historieText.setLineWrap(true);
		
		c.gridx = 0;
		c.gridy = 1;
		this.setForeground(Color.WHITE);
		this.add(scrollPane,c);
	}

	/**
	 * Gibt den Inhalt der Historie als JTextArea zurueck.
	 * 
	 * @return JTextArea, die den Inhalt der Historie enthaelt
	 */
	public JTextArea getHistorieText() {
		return historieText;
	}

	/**
	 * Setzt die JTextArea.
	 * 
	 * @param historieText JTextArea, die den Inhalt der Historie enthaelt
	 */
	public void setHistorieText(JTextArea historieText) {
		this.historieText = historieText;
	}
	
	/**
	 * Aktualisiert den Inhalt des HistoriePanels.
	 * 
	 * @param historie String, der aktuellen Inhalt der Historie enthaelt
	 */
	public void aktualisiereHistorie(String historie) {
		historieText.setText("");
		historieText.setText(historie);
	}
	
}
