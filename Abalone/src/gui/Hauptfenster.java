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
	
	
	public Hauptfenster(Controller c) {
		if(controller == null) {
			controller = c;
		}
		GridLayout experimentLayout = new GridLayout(0,1);
		// Default-Werte
		mainframe = new JFrame();
		mainframe.setSize(960,640);
		mainframe.setTitle("Abalone");
		mainframe.setLayout(new BorderLayout());

		mainpanel = new JPanel();
		mainpanel.setLayout(new GridBagLayout());
			
//		initMenuBar();
//		initSpielbrettPanel();
//		initStatusPanel();
//		initHistorie();
		initHauptmenue();
		
		


		
		
		

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
	

	private void initHauptmenue() {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		Component hM =new Hauptmenue();
		c.gridheight = 5;
		c.gridwidth = 5;
		addToGridBag(c, hM, 1, 1, 0, 0);
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
		c.anchor = GridBagConstraints.EAST;
		statusPanel = new StatusPanel();
		statusPanel.setBorder(BorderFactory.createEtchedBorder());
		c.gridwidth = 1;
		addToGridBag(c,statusPanel, 5,1,0,0);
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

		JMenuItem menuNeuesSpiel = new JMenuItem("Neues Spiel");
		menuNeuesSpiel.addActionListener(null);
		menuNeuesSpiel.setBorder(BorderFactory.createEtchedBorder());
		menubar.add(menuNeuesSpiel);

		
		JMenuItem menuSpeichern = new JMenuItem("Speichern");
		menuSpeichern.addActionListener(null);
		menuSpeichern.setBorder(BorderFactory.createEtchedBorder());
		menubar.add(menuSpeichern);
		
		
		JMenuItem menuLaden = new JMenuItem("Laden");
		menuLaden.addActionListener(null);
		menuLaden.setBorder(BorderFactory.createEtchedBorder());
		menubar.add(menuLaden);
		
		JMenuItem menuLog = new JMenuItem("Log");
		menuLog.addActionListener(null);
		menuLog.setBorder(BorderFactory.createEtchedBorder());
		menubar.add(menuLog);
		
		JMenuItem menuBeenden = new JMenuItem("Spiel beenden");
		menuBeenden.addActionListener(null);
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
	

	public static void main(String[] args) {
		Hauptfenster hf = new Hauptfenster(controller);
	}
}
