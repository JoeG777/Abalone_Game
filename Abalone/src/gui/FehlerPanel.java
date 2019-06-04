package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javax.swing.JOptionPane;
/**
 * <h1>FehlerPanel</h1>
 * Panel zum Anzeigen der verschiedenen Fehler die zum Beispiel bei falschen Eingaben im
 * SpielerAnlegenFenster geworfen werden.
 * 
 */
public class FehlerPanel {
	private JOptionPane fenster;
	private String fehlertext;
	private Font coalition;
	
	/**
	 * Konstruktor des FehlerPanels.
	 * Zeigt das FehlerPanel als JOptionPane mit Warning.
	 * 
	 * @param ft der bestimmte Fehlerttext der geworfen wurde.
	 */
	public FehlerPanel(String ft) {
		try {
			coalition = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("AbaloneSchrift.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(coalition);
		} catch (FontFormatException | IOException e) {
			new FehlerPanel("Fehler beim Laden der Schriftart!");
		}
		coalition = new Font("Coalition", Font.PLAIN, 15);
		
		this.fehlertext = ft;
		fenster = new JOptionPane();
		fenster.setBackground(Color.DARK_GRAY);
		fenster.setForeground(Color.WHITE);
		
		JOptionPane.showMessageDialog(null, fehlertext, "Warning", JOptionPane.ERROR_MESSAGE);
		
	}
	
}
