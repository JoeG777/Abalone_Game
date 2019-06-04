package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * <h1>Hauptfenster</h1>
 * Die Klasse Hauptfenster beinhaltet die Spielansicht, die Historie, den
 * Spielstatus und Menuepunkte zum Starten eines neuen Spiels, zum Speichern, 
 * zum Laden, zum Anzeigen der Log-Datei und zum Spiel beenden.
 * Ferner beinhaltet sie eine Schaltfläche zum Steuern einer mitspielenden KI.
 * 
 */
public class Hauptfenster {
	private JFrame mainframe;
	private JPanel mainpanel;
	private JMenuBar menubar;
	private BrettPanel spielfeldPanel;
	private HistoriePanel historiePanel;
	private StatusPanel statusPanel; 
	private KIOptionenPanel kiOptionenPanel;
	private static Controller controller;
	private EventHandlerHauptfenster eventHandlerMenu; 
	private JMenuItem menuNeuesSpiel, menuSpeichern, menuLaden, menuLog, menuBeenden;
	private Font coalition;

	/**
	 * Erzeugt ein neues Hauptfenster und initialisiert die Komponenten. 
	 * Die Groesse des Hauptfenster (960x640) ist fest.
	 * 
	 * @param c Controller, der die Kommunikation zwischen Spiel und GUI
	 * koordniert
	 */
	public Hauptfenster(Controller c) {
		if(controller == null)
			controller = c;
		
		try {
			coalition = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("AbaloneSchrift.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(coalition);
		} catch (FontFormatException | IOException e) {
			new FehlerPanel("Fehler beim Laden der Schriftart!");
		}
		coalition = new Font("Coalition", Font.PLAIN, 17);
		
		eventHandlerMenu = new EventHandlerHauptfenster(this,controller);
		new GridLayout(0,1);
		
		// Default-Werte
		mainframe = new JFrame();
		mainframe.setUndecorated(true);
		mainframe.setSize(960,640);
		mainframe.setTitle("Abalone");
		mainframe.setBackground(Color.DARK_GRAY);
		mainframe.setLayout(new BorderLayout());
		
		mainpanel = new JPanel();
		mainpanel.setBackground(Color.DARK_GRAY);

		mainpanel.setLayout(new GridBagLayout());
			
		initMenuBar();
		initSpielbrettPanel();
		initKIOptionen();
		initStatusPanel();
		initHistorie();

		mainframe.add(mainpanel, BorderLayout.PAGE_START);
		
		// Default 
		mainframe.setResizable(false);
		mainframe.setLocationRelativeTo(null);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setVisible(true);
	}

	/**
	 * Initialisiert das Spielbrettpanel und fuegt es zum Hauptpanel hinzu.
	 * 
	 */
	private void initSpielbrettPanel() {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		spielfeldPanel = new BrettPanel(controller,controller.getFeldDaten());
		spielfeldPanel.setBackground(Color.DARK_GRAY);
		c.gridheight = 3;
		c.gridwidth = 5;
		spielfeldPanel.setBorder(BorderFactory.createEtchedBorder());
		addToGridBag(c,spielfeldPanel,0, 1, 1, 0);

	}
	/**
	 * Initialisiert das KI-Optionen-Panel und fuegt es zum Hauptpanel hinzu.
	 * 
	 */
	private void initKIOptionen() {
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		c.fill = 1; 
		c.gridwidth=1;
		
		kiOptionenPanel = new KIOptionenPanel(controller);
		kiOptionenPanel.setBackground(Color.DARK_GRAY);
		kiOptionenPanel.setForeground(Color.WHITE);
		kiOptionenPanel.setBorder(BorderFactory.createEtchedBorder());
		kiOptionenPanel.steuereKIPanel(null, false);

		addToGridBag(c,kiOptionenPanel,5,2,0,1);
	}
	
	/**
	 * Initialisiert das Status-Panel und fuegt es zum Hauptpanel hinzu.
	 * 
	 */
	private void initStatusPanel() {
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		c.fill = 1; 
		statusPanel = new StatusPanel();
		statusPanel.setBackground(Color.DARK_GRAY);
		statusPanel.setForeground(Color.WHITE);
		statusPanel.setBorder(BorderFactory.createEtchedBorder());
		c.gridwidth = 1;
		addToGridBag(c,statusPanel, 5,1,0,1);
	}
	
	/**
	 * Initialisiert das Historie-Panel und fuegt es zum Hauptpanel hinzu.
	 * 
	 */
	private void initHistorie() {
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		c.fill = 1; 
		c.gridwidth = 1; 
		historiePanel = new HistoriePanel();
		historiePanel.setBackground(Color.DARK_GRAY);
		historiePanel.setForeground(Color.WHITE);
		historiePanel.setBorder(BorderFactory.createEtchedBorder());
		addToGridBag(c,historiePanel, 5,3,0,1);
	}
	

	/**
	 * Initialisiert die Menue-Leiste und fuegt sie zum Frame hinzu.
	 * 
	 */
	private void initMenuBar() {
		
		menubar = new JMenuBar();
		menubar.setForeground(Color.WHITE);

		menuNeuesSpiel = new JMenuItem("Neues Spiel");
		menuNeuesSpiel.setForeground(Color.WHITE);
		menuNeuesSpiel.setBackground(Color.DARK_GRAY);
		menuNeuesSpiel.setFont(coalition);
		menuNeuesSpiel.addActionListener(eventHandlerMenu);
		menuNeuesSpiel.setBorder(BorderFactory.createEtchedBorder());
		menubar.add(menuNeuesSpiel);

		menuSpeichern = new JMenuItem("Speichern");
		menuSpeichern.setForeground(Color.WHITE);
		menuSpeichern.setBackground(Color.DARK_GRAY);
		menuSpeichern.setFont(coalition);
		menuSpeichern.addActionListener(eventHandlerMenu);
		menuSpeichern.setBorder(BorderFactory.createEtchedBorder());
		menubar.add(menuSpeichern);
		
		menuLaden = new JMenuItem("Laden");
		menuLaden.setForeground(Color.WHITE);
		menuLaden.setBackground(Color.DARK_GRAY);
		menuLaden.setFont(coalition);
		menuLaden.addActionListener(eventHandlerMenu);
		menuLaden.setBorder(BorderFactory.createEtchedBorder());
		menubar.add(menuLaden);
		
		menuLog = new JMenuItem("Log");
		menuLog.setForeground(Color.WHITE);
		menuLog.setBackground(Color.DARK_GRAY);
		menuLog.setFont(coalition);
		menuLog.addActionListener(eventHandlerMenu);
		menuLog.setBorder(BorderFactory.createEtchedBorder());
		menubar.add(menuLog);
		
		menuBeenden = new JMenuItem("Spiel beenden");
		menuBeenden.setForeground(Color.WHITE);
		menuBeenden.setBackground(Color.DARK_GRAY);
		menuBeenden.setFont(coalition);
		menuBeenden.addActionListener(eventHandlerMenu);
		menuBeenden.setBorder(BorderFactory.createEtchedBorder());
		menubar.add(menuBeenden);
		
		mainframe.setJMenuBar(menubar);
	}
	
	/**
	 * Hilfsmethode für das Hinzufuegen von Komponenten zum HauptPanel.
	 * 
	 * @param c ein GridBagConstraints-Objekt
	 * @param component die Komponente, die hinzugefuegt werden soll.
	 * @param x Der zu setzende Wert für das gridx-Attribut des GridbagConstraints-Objektes
	 * @param y Der zu setzende Wert für das gridy-Attribut des GridbagConstraints-Objektes
	 * @param xWeight Der zu setzende Wert für das weightx-Attribut des GridbagConstraints-Objektes
	 * @param yWeight Der zu setzende Wert für das weighty-Attribut des GridbagConstraints-Objektes
	 */
	private void addToGridBag(GridBagConstraints c,Component component, int x, int y, double xWeight, double yWeight) {
		c.gridx = x; 
		c.gridy = y; 
		c.weightx = xWeight;
		c.weighty = yWeight;
		
		mainpanel.add(component, c);
	}
	
	/**
	 * Beendet das Hauptfenster.
	 * 
	 */
	public void shutdown() {
		mainframe.dispose();
	}
	
	/**
	 * Gibt Gewinner des Spiels in einem GewonnenPanel aus.
	 * 
	 * @param gewinner Name, des Gewinners
	 */
	public void spielGewonnen(String gewinner) {
		mainframe.setEnabled(false);
		new GewonnenPanel(gewinner, controller, this);
	}
	
	/**
	 * Schafft ein SindSieSicherPanel das fragt, ob der Benutzer
	 * das Spiel beenden möchte.
	 * 
	 */
	public void beendeSpiel() {
		new SindSieSicherPanel("Spiel beenden?", controller, mainframe);
	}
	
	/**
	 * Schafft ein SindSieSicher-Panel das fragt, ob der Benutzer ein neues
	 * Spiel starten möchte..
	 * 
	 */
	public void neuesSpiel() {
		new SindSieSicherPanel("Spiel Neustarten?", controller, mainframe);
	}

	/**
	 * Aktualisiert das gesamte Spielbrett.
	 * 
	 */
	public void aktualisiere() {
		this.aktualisiereSpielbrett(controller.getFeldDaten());
	}
	
	/**
	 * Aktualisiert bestimmte Felder des Spielbretts.
	 * 
	 * @param ids Die zu aktualisierenden Felder
	 */
	public void aktualisiereSpielbrett(String[][] ids) {
		((BrettPanel) spielfeldPanel).aktualisiere(ids);
	}

	/**
	 * Gibt das Mainframe zurueck.
	 * 
	 * @return Mainframe-Attribut des Hauptfensters
	 */
	public JFrame getMainframe() {
		return mainframe;
	}

	/**
	 * Gibt das Spielfeld-Panel zurueck.
	 * 
	 * @return SpielfeldPanel-Attribut des Hauptfensters
	 */
	public BrettPanel getSpielfeldPanel() {
		return spielfeldPanel;
	}

	/**
	 * Gibt das Historie-Panel zurueck.
	 * 
	 * @return HistoriePanel-Attribut des Hauptfensters
	 */
	public HistoriePanel getHistoriePanel() {
		return historiePanel;
	}

	/**
	 * Gibt das Statuspanel zurueck.
	 * 
	 * @return StatusPanel-Attribut des Hauptfensters
	 */
	public StatusPanel getStatusPanel() {
		return statusPanel;
	}

	/**
	 * Gibt das MenuItem für ein neues Spiel zurueck.
	 * 
	 * @return neuesSpiel-Menu Item des Menues des Hauptfensters
	 */
	public JMenuItem getMenuNeuesSpiel() {
		return menuNeuesSpiel;
	}

	/**
	 * Gibt das MenuItem zum Speichern zurueck.
	 * 
	 * @return menuSpeichern-Menu Item des Menues des Hauptfensters
	 */
	public JMenuItem getMenuSpeichern() {
		return menuSpeichern;
	}

	/**
	 * Gibt das MenuItem zum Laden eines Spiels zurueck.
	 * 
	 * @return menuLaden-Menu Item des Menues des Hauptfensters.
	 */
	public JMenuItem getMenuLaden() {
		return menuLaden;
	}

	/**
	 * Gibt das JMenuItem fuer das Log zurueck.
	 * 
	 * @return JMenuItem fuer das Log
	 */
	public JMenuItem getMenuLog() {
		return menuLog;
	}

	/**
	 * Gibt das JMenuItem für das Beenden zurueck.
	 * 
	 * @return JMenuItem für das Beenden
	 */
	public JMenuItem getMenuBeenden() {
		return menuBeenden;
	}

	/**
	 * Aktiviert das Resetten eines Feldes.
	 * 
	 */
	public void resetFelderAuswaehlbar() {
		spielfeldPanel.resetAuswaehlbar();
	}

	/**
	 * Gibt das KI-Optionen-Panel des Hauptfenster zurueck.
	 * 
	 * @return Das KI-Optionen-Panel des Hauptfensters.
	 */
	public KIOptionenPanel getKiOptionenPanel() {
		return kiOptionenPanel;
	}

	/**
	 * Gibt ausgewaehlte Felder als ArrayList aus FeldPanels zurueck.
	 * 
	 * @return Ausgewaehlte Felder als ArrayList aus FeldPanels zurueck
	 */
	public ArrayList<FeldPanel> bekommeGewaehlteFelder() {
		return spielfeldPanel.bekommeGewaehlteFelder();
	}

}
