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
	private GridBagConstraints c; 
	
	
	public Hauptfenster() {
		GridLayout experimentLayout = new GridLayout(0,1);
		// Default-Werte
		mainframe = new JFrame();
		mainframe.setSize(960,640);
		mainframe.setLayout(new BorderLayout());
		
		
		mainpanel = new JPanel();
		mainpanel.setLayout(new GridBagLayout());
		
		
		c  = new GridBagConstraints();
		
		
		

		menubar = new JMenuBar();
		menubar.add(new JMenuItem("Neues Spiel"));
		menubar.add(new JMenuItem("Speichern"));
		menubar.add(new JMenuItem("Laden"));
		menubar.add(new JMenuItem("Log"));
		menubar.add(new JMenuItem("Spiel beenden"));

		c.fill = GridBagConstraints.HORIZONTAL;

		c.weightx = 1;
		addToGridBag(menubar, 0, 0,1,1);
		
		
		
		spielfeldPanel = new JPanel();
		spielfeldPanel.setSize(400,400);
		spielfeldPanel.add(new JLabel("TEST"));
		JPanel spielfeldPane2 = new JPanel();
		spielfeldPane2.setLayout(experimentLayout);
		spielfeldPane2.add(new StatusPanel());
		spielfeldPane2.add(new HistoriePanel());
		spielfeldPane2.add(new FeldPanel("A1"));
		addToGridBag(spielfeldPanel, 0,1,0.5,1);
		addToGridBag(spielfeldPane2, 1,1,0.5,1);
		
		
		mainframe.add(mainpanel, BorderLayout.PAGE_START);
		// Default 
		mainframe.setResizable(false);
		mainframe.setLocationRelativeTo(null);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setVisible(true);
	}
	
	public void addToGridBag(Component component, int x, int y, double xWeight, double yWeight) {
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
