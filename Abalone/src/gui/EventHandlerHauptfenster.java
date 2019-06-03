package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import abalone.DateiIOException;
/**
 * Der EventHandler für das Hauptfenster des Spiels. Verwaltet die Action-
 * Listener des Hauptfensters.
 *
 */


public class EventHandlerHauptfenster implements ActionListener{
	private Hauptfenster hauptfenster;
	private Controller controller;
	
	/**
	 * Erschafft einen Event-Handler für ein Hauptfenster.
	 * @param hauptfenster das Hauptfenster, dessen Events koordiniert werden 
	 * sollen.
	 * @param c Der Controller, der zum Hauptfenster gehört.
	 */
	public EventHandlerHauptfenster(Hauptfenster hauptfenster, Controller c) {
		this.hauptfenster = hauptfenster;
		controller = c; 
		
	}
//	public void warten() {
//		EventHandlerHauptfenster ha = this;
//		SwingWorker<Void, Void> w = new SwingWorker<Void, Void>() {
//
//			@Override
//			protected Void doInBackground() throws Exception {
//				Thread.sleep(100);
//				JButton button = new JButton();
//				button.addActionListener(ha);
//				button.setActionCommand("autoClick");
//				button.doClick();
//				return null;
//			}
//			
//		};
//		w.execute();
//	}
	
	/**
	 * Behandelt die Events eines Hauptfensters.
	 * Diese sind:
	 * Starten eines neuen Spiels, Speichern eines Spiels, Laden eines Spiels,
	 * Anzeigen der Log-Datei, Beenden des Spiels.
	 * @param e das zu behandelnde Event.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == hauptfenster.getMenuNeuesSpiel()) {
//			new SindSieSicherPanel("Wollen Sie wirklich Neu starten?");
//			SpielerAnlegenFenster spielerAnlegen= new SpielerAnlegenFenster(controller);
			hauptfenster.neuesSpiel();
		}
		if(e.getSource() == hauptfenster.getMenuSpeichern()) {
			File selected = generateFileChooser(true);
			if(selected == null) {
				return;
			}
			String ending = selected.toString().substring(selected.toString().lastIndexOf('.'));
			if(ending.equals(".csv")) {
				controller.speichernCSV(selected.toString());
			}
			else if(ending.equals(".ser")) {
				controller.speichernSer(selected.toString());
			}
		}
		if(e.getSource() == hauptfenster.getMenuLaden()) {
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
			
			controller.aktualisiereAlles();
		}
		if(e.getSource() == hauptfenster.getMenuLog()) {
			LogFenster logFenster = new LogFenster();
		}
		if(e.getSource() == hauptfenster.getMenuBeenden()) {
			hauptfenster.beendeSpiel();
		}
	}
	
	/**
	 * Öffnet einen FileChooser und erlaubt es dem Nutzer eine
	 * Datei auszuwählen. Es wird geprueft, ob die Datei existiert
	 * und anschließend ein passendes File-Objekt zurückgegeben. 
	 * @param save wahr/falsch je nachdem ob speichern/laden
	 * @return File-Objekt, das ausgewählt wurde
	 */
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
				option = jfc.showSaveDialog(hauptfenster.getMainframe());
			}
			else {
				option = jfc.showOpenDialog(hauptfenster.getMainframe());
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
	


}
