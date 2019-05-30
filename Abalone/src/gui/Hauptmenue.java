package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import abalone.SpielException;

/**
 * Startmenue, dass den Einstieg in die graphische Oberflaeche bietet.
 * @author Team A4
 *
 */
public class Hauptmenue extends JFrame implements ActionListener{

	private static final long serialVersionUID = -3874636578097053073L;
	private static Controller controller;
	private JPanel jp;
	private JLabel jl;
	private JButton neuesSpiel;
	private JButton spielLaden;
	private JButton beenden;
/**
 * Konstruktor, der das Fenster auf dem Bildschirm aufleuchten lässt
 * @param controller Wird speater den folgenden Fenstern uebergeben
 */
	private ImageIcon ueberschrift;
	private ImageIcon neuesSpielIcon;
	private ImageIcon spielLadenIcon;
	private ImageIcon beendenIcon;

	public Hauptmenue (Controller controller){
		Hauptmenue.controller =controller;
		jp = new JPanel(new GridBagLayout());
		jp.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc = new GridBagConstraints();
		this.setSize(800,500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		ueberschrift =  new ImageIcon(getClass().getResource("/assets/burn-in3.gif"));
		neuesSpielIcon = new ImageIcon(getClass().getResource("/assets/Neues Spiel.png"));
		spielLadenIcon =  new ImageIcon(getClass().getResource("/assets/Spiel Laden.png"));
		beendenIcon =  new ImageIcon(getClass().getResource("/assets/Beenden.png"));

		gbc.weightx = 1.0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(14,0,0,0);
		jl = new JLabel(ueberschrift);
		jp.add(jl, gbc);

		neuesSpiel = new JButton();
		neuesSpiel.setIcon(neuesSpielIcon);
		neuesSpiel.setPreferredSize(new Dimension(227,72));
		gbc.ipady = 0;
		gbc.ipadx = 0;
		gbc.gridy = 1;

		neuesSpiel.addActionListener(this);
		jp.add(neuesSpiel, gbc);

		spielLaden = new JButton ();
		spielLaden.setIcon(spielLadenIcon);
		spielLaden.setPreferredSize(new Dimension(227,72));
		gbc.gridy = 2;
		spielLaden.addActionListener(this);
		jp.add(spielLaden,gbc);

		beenden = new JButton ();
		beenden.setIcon(beendenIcon);
		beenden.setPreferredSize(new Dimension(227,72));
		gbc.gridy = 3;
		beenden.addActionListener(this);
		jp.add(beenden, gbc);

		this.add(jp);
		this.setVisible(true);
	}

	/** 
	 * Implementierung die actionPerformed, deren Aufgabe besteht die Button-Events auszufuehren
	 * @param ActionEvent Button Klick
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == neuesSpiel) {
			SpielerAnlegenFenster saf = new SpielerAnlegenFenster(controller);
			this.dispose();
		}else if(e.getSource() == spielLaden){
			File selected = generateFileChooser(false);
			
			if(selected == null) {
				return; 
			}
			
			String ending = selected.toString().substring(selected.toString().lastIndexOf('.'));
			

			
			if(ending.equals(".csv")) {
				controller.ladenCSV(selected.toString());
			}
			else if(ending.equals(".ser")) {
				controller.ladenSer(selected.toString());
			}

			try {
				Hauptmenue.controller.hauptFensterStarten();
			} catch (SpielException e1) {
				new FehlerPanel("Fehler beim Starten des Schriftart!");
			}
			
			this.dispose();
			
		}else if(e.getSource() == beenden) {
			System.exit(0);
		}

	}
	public File generateFileChooser(boolean save) {
		File selected = null;
		boolean loop = true; 
		
		while(loop) {
			loop = false;
			final JFileChooser jfc = new JFileChooser("./sav");
			jfc.setAcceptAllFileFilterUsed(false);
			FileFilter filterCSV = new FileNameExtensionFilter(".csv", "csv");
			FileFilter filterSER = new FileNameExtensionFilter(".ser", "ser");
			jfc.addChoosableFileFilter(filterCSV);
			jfc.addChoosableFileFilter(filterSER);

			int option = 0; 
			if(save) {
				option = jfc.showSaveDialog(this);
			}
			else {
				option = jfc.showOpenDialog(this);
			}

			selected = jfc.getSelectedFile();
			FileFilter filter = jfc.getFileFilter();

			if(option == JFileChooser.CANCEL_OPTION) {
				return null;
			}

			if(save) {
				if(!(selected.toString().endsWith(".csv") || 
						selected.toString().endsWith(".ser"))) {
					selected = new File(selected.toString()+filter.getDescription());
				}
			}
			else {
				if(!selected.exists()) {
					loop = true; 
					new FehlerPanel("Bitte gueltige Datei waehlen oder abbrechen!");
				}
			}
		}
		return selected;
	}
	
	public JButton getSpielLaden() {
		return this.spielLaden;
	}

}
