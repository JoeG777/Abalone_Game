package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Hauptfenster {
	private JFrame mainframe;
	private JPanel mainpanel;
	private JMenuBar menubar;
	private JPanel spielfeldPanel, historiePanel, statusPanel;

	
	
	public Hauptfenster() {
		GridLayout experimentLayout = new GridLayout(0,1);
		// Default-Werte
		mainframe = new JFrame();
		mainframe.setSize(960,640);
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
	
	private void initStatusPanel() {
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		statusPanel = new StatusPanel();
		addToGridBag(c,statusPanel, 7,1,0,0);
	}
	private void initSpielbrettPanel() {
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		spielfeldPanel = new BrettPanel();
		c.gridheight = 2;
		c.gridwidth = 7;
		c.fill = GridBagConstraints.BOTH;
		addToGridBag(c,spielfeldPanel, 0, 1, 0, 0);
	}
	private void initHistorie() {
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		historiePanel = new HistoriePanel();
		c.gridwidth = 1;
		addToGridBag(c,historiePanel, 7,2,0,0);
		
	}
	private void initMenuBar() {
		GridBagConstraints c = new GridBagConstraints();
		menubar = new JMenuBar();
		menubar.add(new JMenuItem("Neues Spiel"));
		menubar.add(new JMenuItem("Speichern"));
		menubar.add(new JMenuItem("Laden"));
		menubar.add(new JMenuItem("Log"));
		menubar.add(new JMenuItem("Spiel beenden"));
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;


		addToGridBag(c,menubar, 0, 0,1,0);
	}
	public void addToGridBag(GridBagConstraints c,Component component, int x, int y, double xWeight, double yWeight) {
		c.gridx = x; 
		c.gridy = y; 
		c.weightx = xWeight;
		c.weighty = yWeight;
		
		mainpanel.add(component, c);
	}
	

	public static void main(String[] args) {
		Hauptfenster hf = new Hauptfenster();
	}
}
