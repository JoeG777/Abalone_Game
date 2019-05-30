
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
import javax.swing.JTextArea;

/**
 * Das Status-Panel erbt vom JPanel und dient zur
 * Visualisierung des Status des Spiels.
 * 
 * @author Gruppe A4
 *
 */
public class StatusPanel extends JPanel {
	private static final long serialVersionUID = 3L;
	private JTextArea statusText;
	private Font coalition, coalition2;
	
	/**
	 * Konstruktor des Status-Panels.
	 * 
	 */
	public StatusPanel() {
		try {
			coalition = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("AbaloneSchrift.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(coalition);
		} catch (FontFormatException | IOException e) {
			new FehlerPanel("Fehler beim Laden der Schriftart!");
		}
		coalition = new Font("Coalition", Font.PLAIN, 10);
		coalition2 = new Font("Coalition", Font.PLAIN, 14);
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		JLabel label = new JLabel("Status");
		label.setFont(coalition2);
		label.setForeground(Color.WHITE);
		this.add(label,c);
		
		statusText = new JTextArea("Hier steht bald der Spiel-Status.",5, 27);
		statusText.setBackground(Color.darkGray);
		statusText.setForeground(Color.WHITE);
		statusText.setFont(coalition);
		statusText.setEditable(false);
		statusText.setLineWrap(true);
		
		c.gridx = 0;
		c.gridy = 1;
		this.add(statusText,c);
	}

	/**
	 * Gibt den Text des StatusPanels als JTextArea zurueck.
	 * 
	 * @return JTextArea, die StatusText enthaelt
	 */
	public JTextArea getStatusText() {
		return statusText;
	}

	/**
	 * Setzt die JTextArea StatusText.
	 * 
	 * @param statusText JTextArea, die StatusText enthaelt
	 */
	public void setStatusText(JTextArea statusText) {
		this.statusText = statusText;
	}
	
	/**
	 * Aktualisiert den StatusPanel.
	 * 
	 * @param amZug String, welcher den Namen des Spielers, der am Zug ist, enthaelt
	 * @param spieler1 String-Array, welcher notwendige Informationen des 1. Spielers enthaelt
	 * @param spieler2 String-Array, welcher notwendige Informationen des 2. Spielers enthaelt
	 */
	public void aktualisiereStatus(String amZug, String[] spieler1, String spieler2[]) {
		if (spieler1[0] != null && spieler1[0].startsWith("KI_")) {
			String durchziehend = ""; 
			if(spieler1[0].endsWith("(durchziehend)")) {
				durchziehend += "(durchziehend)";
			}
			spieler1[0] = spieler1[0].substring(0, 4) + durchziehend;
		}
		if (spieler2[0] != null && spieler2[0].startsWith("KI_")) {
			String durchziehend = "";
			if(spieler2[0].endsWith("(durchziehend)")) {
				durchziehend += "(durchziehend)";
			}
			spieler2[0] = spieler2[0].substring(0, 4) +durchziehend;
		}
		String string = spieler1[0] + " (Rot)\n" + spieler1[4] + " / 14 Kugeln "
				+ "(Muss " + (Integer.parseInt(spieler2[4]) - 8) + " Kugeln schlagen für Sieg)\n"
				+ spieler2[0] + " (Blau)\n" + spieler2[4] + " / 14 Kugeln "
				+ "(Muss " + (Integer.parseInt(spieler1[4]) - 8) + " Kugeln schlagen für Sieg)";
		if(amZug != null) {
			string += "\n Am Zug ist " + amZug + ".";
		}
		statusText.setText(string);
	}
	
}
