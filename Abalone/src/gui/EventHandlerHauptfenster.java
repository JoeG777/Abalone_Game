package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import abalone.DateiIOException;



public class EventHandlerHauptfenster implements ActionListener{
	private Hauptfenster hauptfenster;
	private Controller controller;
	
	public EventHandlerHauptfenster(Hauptfenster hauptfenster, Controller c) {
		this.hauptfenster = hauptfenster;
		controller = c; 
	}
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
		}
		if(e.getSource() == hauptfenster.getMenuLog()) {
			LogFenster logFenster = new LogFenster();
		}
		if(e.getSource() == hauptfenster.getMenuBeenden()) {
			hauptfenster.beendeSpiel();
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
