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
import java.awt.MenuItem;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import abalone.SpielException;
/**
 * Die Klasse Hauptfenster beinhaltet die Spielansicht, die Historie, den
 * Spielstatus und Menuepunkte zum Starten eines neuen Spiels, zum Speichern, 
 * zum Laden, zum Anzeigen der Log-Datei und zum Spiel beenden.
 * Ferner beinhaltet sie eine Schaltfläche zum Steuern einer mitspielenden
 * KI.
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
	 * @param c Controller, der die Kommunikation zwischen Spiel und GUI
	 * koordniert.
	 */
	public Hauptfenster(Controller c) {
		if(controller == null) {
			controller = c;
		}
		
		try {
			coalition = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("AbaloneSchrift.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(coalition);
		} catch (FontFormatException | IOException e) {
			new FehlerPanel("Fehler beim Laden der Schriftart!");
		}
		coalition = new Font("Coalition", Font.PLAIN, 17);
		
		eventHandlerMenu = new EventHandlerHauptfenster(this,controller);
		GridLayout experimentLayout = new GridLayout(0,1);
		// Default-Werte
		mainframe = new JFrame();
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
		
		/*spielfeldPanel = new JPanel();
		spielfeldPanel.setSize(400,400);
		spielfeldPanel.setLayout(new BorderLayout());
		spielfeldPanel.add(new BrettPanel(), BorderLayout.PAGE_START);
		JPanel spielfeldPane2 = new JPanel();
		spielfeldPane2.setSize(400,400);
		spielfeldPane2.add(new JLabel("TEST2"));
		addToGridBag(c,spielfeldPanel, 0,1,0.5,1);
		addToGridBag(c,spielfeldPane2, 1,1,0.5,1);*/
		
		

		mainframe.add(mainpanel, BorderLayout.PAGE_START);
		// Default 
		mainframe.setResizable(false);
		mainframe.setLocationRelativeTo(null);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setVisible(true);
	}

	/**
	 * Initialisiert das Spielbrettpanel und fügt es zum Hauptpanel hinzu.
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
	 * Initialisiert das KI-Optionen-Panel und fügt es zum Hauptpanel hinzu.
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
	 * Initialisiert das Status-Panel und fügt es zum Hauptpanel hinzu.
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
	 * Initialisiert das Historie-Panel und fügt es zum Hauptpanel hinzu.
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
	 * Initialisiert die Menue-Leiste und fügt sie zum Frame hinzu.
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
	 * Hilfsmethode für das Hinzufügen von Komponenten zum Hauptpanel.
	 * @param c ein GridBagConstraints-Objekt.
	 * @param component die Komponente, die hinzugefuegt werden soll. 
	 * @param x Der zu setzende Wert für das 
	 * gridx-Attribut des GridbagConstraints-Objektes.
	 * @param y Der zu setzende Wert für das 
	 * gridy-Attribut des GridbagConstraints-Objektes.
	 * @param xWeight Der zu setzende Wert für das 
	 * weightx-Attribut des GridbagConstraints-Objektes.
	 * @param yWeight Der zu setzende Wert für das 
	 * weighty-Attribut des GridbagConstraints-Objektes.
	 */
	private void addToGridBag(GridBagConstraints c,Component component, int x, int y, double xWeight, double yWeight) {
		c.gridx = x; 
		c.gridy = y; 
		c.weightx = xWeight;
		c.weighty = yWeight;
		
		mainpanel.add(component, c);
	}
	
	public void shutdown() {
		mainframe.dispose();
	}
	
	public void spielGewonnen(String gewinner) {
		mainframe.setEnabled(false);
		new GewonnenPanel(gewinner, controller, this);
	}
	/**
	 * Schafft ein SindSieSicher-Panel das fragt, ob der Benutzer
	 * das Spiel beenden möchte.
	 */
	public void beendeSpiel() {
		new SindSieSicherPanel("Spiel beenden?", controller, mainframe);
	}
	/**
	 * Schafft ein SindSieSicher-Panel das fragt, ob der Benutzer ein neues
	 * Spiel starten möchte..
	 */
	public void neuesSpiel() {
		new SindSieSicherPanel("Spiel Neustarten?", controller, mainframe);
	}

	/**
	 * Aktualisiert das gesamte Spielbrett.
	 */
	public void aktualisiere() {
		this.aktualisiereSpielbrett(controller.getFeldDaten());
	}
	
	/**
	 * Aktualisiert bestimmte Felder des Spielbretts.
	 * @param ids die zu aktualisierenden Felder.
	 */
	public void aktualisiereSpielbrett(String[][] ids) {
		((BrettPanel) spielfeldPanel).aktualisiere(ids);
	}

	/**
	 * Gibt das Mainframe zurück.
	 * @return Mainframe-Attribut des Hauptfensters.
	 */
	public JFrame getMainframe() {
		return mainframe;
	}


	/**
	 * Gibt das Spielfeld-Panel zurück.
	 * @return SpielfeldPanel-Attribut des Hauptfensters.
	 */
	public BrettPanel getSpielfeldPanel() {
		return spielfeldPanel;
	}

	/**
	 * Gibt das Historie-Panel zurück.
	 * @return HistoriePanel-Attribut des Hauptfensters.
	 */
	public HistoriePanel getHistoriePanel() {
		return historiePanel;
	}

	/**
	 * Gibt das Statuspanel zurück.
	 * @return StatusPanel-Attribut des Hauptfensters.
	 */
	public StatusPanel getStatusPanel() {
		return statusPanel;
	}

	/**
	 * Gibt das MenuItem für ein neues Spiel zurück.
	 * @return neuesSpiel-Menu Item des Menues des Hauptfensters.
	 */
	public JMenuItem getMenuNeuesSpiel() {
		return menuNeuesSpiel;
	}

	/**
	 * Gibt das MenuItem zum Speichern zurück.
	 * @return menuSpeichern-Menu Item des Menues des Hauptfensters.
	 */
	public JMenuItem getMenuSpeichern() {
		return menuSpeichern;
	}

	/**
	 * Gibt das MenuItem zum Laden eines Spiels zurück.
	 * @return menuLaden-Menu Item des Menues des Hauptfensters.
	 */
	public JMenuItem getMenuLaden() {
		return menuLaden;
	}




	public JMenuItem getMenuLog() {
		return menuLog;
	}





	public JMenuItem getMenuBeenden() {
		return menuBeenden;
	}


	
	public void resetFelderAuswaehlbar() {
		spielfeldPanel.resetAuswaehlbar();
	}


	public KIOptionenPanel getKiOptionenPanel() {
		return kiOptionenPanel;
	}


	
	
	
}
