package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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

public class Hauptfenster {
	private JFrame mainframe;
	private JPanel mainpanel;
	private JMenuBar menubar;
	private JPanel spielfeldPanel, historiePanel, statusPanel;
	private static Controller controller;
	private EventHandlerHauptfenster eventHandlerMenu; 
	private JMenuItem menuNeuesSpiel, menuSpeichern, menuLaden, menuLog, menuBeenden;
	
	
	public Hauptfenster(Controller c) {
		if(controller == null) {
			controller = c;
		}
		eventHandlerMenu = new EventHandlerHauptfenster(this,controller);
		GridLayout experimentLayout = new GridLayout(0,1);
		// Default-Werte
		mainframe = new JFrame();
		mainframe.setSize(960,640);
		mainframe.setTitle("Abalone");
		mainframe.setLayout(new BorderLayout());

		mainpanel = new JPanel();
		mainpanel.setLayout(new GridBagLayout());
			
		initMenuBar();
		initSpielbrettPanel();
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
	

	private void initSpielbrettPanel() {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		spielfeldPanel = new BrettPanel(controller);
		c.gridheight = 2;
		c.gridwidth = 5;
		spielfeldPanel.setBorder(BorderFactory.createEtchedBorder());
		addToGridBag(c,spielfeldPanel,0, 1, 1, 0);

	}
	private void initStatusPanel() {
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.fill = 1; 
		statusPanel = new StatusPanel();
		statusPanel.setBorder(BorderFactory.createEtchedBorder());
		c.gridwidth = 1;
		addToGridBag(c,statusPanel, 5,1,0,1);
	}
	private void initHistorie() {
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		historiePanel = new HistoriePanel();
		historiePanel.setBorder(BorderFactory.createEtchedBorder());
		addToGridBag(c,historiePanel, 5,2,0,0);
		
	}
	

	
	private void initMenuBar() {
//		GridBagConstraints c = new GridBagConstraints();
		
		menubar = new JMenuBar();

		menuNeuesSpiel = new JMenuItem("Neues Spiel");
		menuNeuesSpiel.addActionListener(eventHandlerMenu);
		menuNeuesSpiel.setBorder(BorderFactory.createEtchedBorder());
		menubar.add(menuNeuesSpiel);

		
		menuSpeichern = new JMenuItem("Speichern");
		menuSpeichern.addActionListener(eventHandlerMenu);
		menuSpeichern.setBorder(BorderFactory.createEtchedBorder());
		menubar.add(menuSpeichern);
		
		
		menuLaden = new JMenuItem("Laden");
		menuLaden.addActionListener(eventHandlerMenu);
		menuLaden.setBorder(BorderFactory.createEtchedBorder());
		menubar.add(menuLaden);
		
		menuLog = new JMenuItem("Log");
		menuLog.addActionListener(eventHandlerMenu);
		menuLog.setBorder(BorderFactory.createEtchedBorder());
		menubar.add(menuLog);
		
		menuBeenden = new JMenuItem("Spiel beenden");
		menuBeenden.addActionListener(eventHandlerMenu);
		menuBeenden.setBorder(BorderFactory.createEtchedBorder());
		menubar.add(menuBeenden);
	
// 
//		c.gridwidth = GridBagConstraints.REMAINDER;
//		c.fill = GridBagConstraints.HORIZONTAL;
		
		mainframe.setJMenuBar(menubar);


//		addToGridBag(c,menubar, 0, 0,1,0);
	}
	public void addToGridBag(GridBagConstraints c,Component component, int x, int y, double xWeight, double yWeight) {
		c.gridx = x; 
		c.gridy = y; 
		c.weightx = xWeight;
		c.weighty = yWeight;
		
		mainpanel.add(component, c);
	}
	

	public void beendeSpiel() {
		System.exit(0);
	}
	
	public void aktualisiere() {
		((BrettPanel) spielfeldPanel).aktualisiere();
	}
	
	public void aktualisiereSpielbrett(String[] ids) {
		((BrettPanel)spielfeldPanel).aktualisiere(ids);
	}
	
	public JFrame getMainframe() {
		return mainframe;
	}
	
	public void setMainframe(JFrame mainframe) {
		this.mainframe = mainframe;
	}
	
	public JPanel getMainpanel() {
		return mainpanel;
	}
	
	public void setMainpanel(JPanel mainpanel) {
		this.mainpanel = mainpanel;
	}
	
	public JMenuBar getMenubar() {
		return menubar;
	}
	
	public void setMenubar(JMenuBar menubar) {
		this.menubar = menubar;
	}
	
	public JPanel getSpielfeldPanel() {
		return spielfeldPanel;
	}
	
	public void setSpielfeldPanel(JPanel spielfeldPanel) {
		this.spielfeldPanel = spielfeldPanel;
	}

	public JPanel getHistoriePanel() {
		return historiePanel;
	}

	public void setHistoriePanel(JPanel historiePanel) {
		this.historiePanel = historiePanel;
	}

	public JPanel getStatusPanel() {
		return statusPanel;
	}

	public void setStatusPanel(JPanel statusPanel) {
		this.statusPanel = statusPanel;
	}

	public static Controller getController() {
		return controller;
	}
	
	public static void setController(Controller controller) {
		Hauptfenster.controller = controller;
	}

	public EventHandlerHauptfenster getEventHandlerMenu() {
		return eventHandlerMenu;
	}

	public void setEventHandlerMenu(EventHandlerHauptfenster eventHandlerMenu) {
		this.eventHandlerMenu = eventHandlerMenu;
	}

	public JMenuItem getMenuNeuesSpiel() {
		return menuNeuesSpiel;
	}

	public void setMenuNeuesSpiel(JMenuItem menuNeuesSpiel) {
		this.menuNeuesSpiel = menuNeuesSpiel;
	}

	public JMenuItem getMenuSpeichern() {
		return menuSpeichern;
	}
	
	public void setMenuSpeichern(JMenuItem menuSpeichern) {
		this.menuSpeichern = menuSpeichern;
	}

	public JMenuItem getMenuLaden() {
		return menuLaden;
	}

	public void setMenuLaden(JMenuItem menuLaden) {
		this.menuLaden = menuLaden;
	}

	public JMenuItem getMenuLog() {
		return menuLog;
	}

	public void setMenuLog(JMenuItem menuLog) {
		this.menuLog = menuLog;
	}

	public JMenuItem getMenuBeenden() {
		return menuBeenden;
	}
	
	public void setMenuBeenden(JMenuItem menuBeenden) {
		this.menuBeenden = menuBeenden;
	}
}
