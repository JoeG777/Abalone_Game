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

/**
 * <h1>KIOptionenPanel</h1>
 * Das KI-Optionen Panel erlaubt es dem Nutzer die KI zu steuern.
 *
 */
public class KIOptionenPanel extends JPanel {
	private static final long serialVersionUID = 2225L;
	private JLabel infoLabel, kiLabel;
	private JButton kiWeiter, kiDurchziehend;
	private Font coalition, coalition2;
	private EventHandlerKIOptionen eventHandlerKIOptionen;
	
	/**
	 * Konstruktor des KI-Optionen-Panels.
	 * 
	 * @param Controller, der zum Hauptfenster gehört
	 */
	public KIOptionenPanel(Controller c) {
		try {
			coalition = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("AbaloneSchrift.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(coalition);
		} catch (FontFormatException | IOException e) {
			new FehlerPanel("Fehler beim Laden der Schriftart!");
		}
		coalition = new Font("Coalition", Font.PLAIN, 10);
		coalition2 = new Font("Coalition", Font.PLAIN, 14);
		
		this.setLayout(new GridBagLayout());
		eventHandlerKIOptionen = new EventHandlerKIOptionen(c,this);
		initLabel();
		initKILabelButtons();
		steuereKIPanel(null, false);
	}
	
	/**
	 * Initialisiert das "KI-Optionen"-Label (Ueberschrift).
	 * 
	 */
	private void initLabel() {
		if(infoLabel == null) {
			infoLabel = new JLabel("KI-Optionen");
			infoLabel.setBackground(Color.DARK_GRAY);
			infoLabel.setForeground(Color.WHITE);
			infoLabel.setFont(coalition2);
			addToGridBag(infoLabel, 0, 0, 2 ,1, GridBagConstraints.REMAINDER);
		}
	}
	
	/**
	 * Initialisiert die KI-Buttons.
	 * 
	 */
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
	
	/**
	 * Aktiviert/deaktiviert die Buttons.
	 * 
	 * @param kiName der Name der KI
	 * @param aktiviert wahr/falsch, je nachdem, ob die Buttons aktiviert
	 * oder deaktiviert sein sollen.
	 */
	public void steuereKIPanel(String kiName, boolean aktiviert) {
		if(aktiviert) {
			aktiviereKIPanel(kiName);
		}
		else {
			deaktiviereKIPanel();
		}
	}
	
	/**
	 * Aktiviert das KI-Panel.
	 * 
	 * @param kiName der Name der KI, die am Zug ist
	 */
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
	
	/**
	 * Deaktiviert das KI-Panel.
	 * 
	 */
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
	
	/**
	 * Hilfsmethode. Ruft repaint und revalidate auf die eigene Instanz auf.
	 * 
	 */
	public void aktualisiere() {
		this.repaint();
		this.revalidate();
	}
	
	/**
	 * Hilfsmethode zum Hinzufuegen zum Layout.
	 * 
	 * @param comp Komponente die Hinzugefügt werden soll
	 * @param x x-Wert der Komponente im Layout
	 * @param y y-Wert der Komponente im Layout
	 */
	private void addToGridBag(Component comp, int x, int y) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx= x;
		c.gridy = y; 
		c.fill = 1; 
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		this.add(comp,c);
	}
	
	/**
	 * Hilfsmethode zum Hinzufuegen zum Layout.
	 * 
	 * @param comp Komponente die Hinzugefügt werden soll
	 * @param x x-Wert der Komponente im Layout
	 * @param y y-Wert der Komponente im Layout
	 * @param width der Weite der Komponente im Layout
 	 * @param height die Höhe der Komponente im Layout
	 * @param fill der Fill-Wert der Komponente im Layout
	 */
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
	
	/**
	 * Gibt den KI-Weiter Button des KIOptionenPanels zurueck.
	 * 
	 * @return KI-Weiter Button
	 */
	public JButton getKiWeiter() {
		return kiWeiter;
	}
	
	/**
	 * Setzt den KI-Weiter Button des KIOptionenPanels.
	 * 
	 * @param kiWeiter zu setzender JButton
	 */
	public void setKiWeiter(JButton kiWeiter) {
		this.kiWeiter = kiWeiter;
	}
	
	/**
	 * Gibt den durchziehend-Button des KI-OptionenPanels zurueck.
	 * 
	 * @return der durchziehend-Button des Panels
	 */
	public JButton getKiDurchziehend() {
		return kiDurchziehend;
	}
	/**
	 * Setzt den durchziehend-Button des KI-OptionenPanels.
	 * 
	 * @param kiDurchziehend durchziehend-Button, der gesetzt werden soll.
	 */
	public void setKiDurchziehend(JButton kiDurchziehend) {
		this.kiDurchziehend = kiDurchziehend;
	}

}
