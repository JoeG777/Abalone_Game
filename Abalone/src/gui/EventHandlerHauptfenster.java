package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Locale;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;



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
			String ending = selected.toString().substring(selected.toString().lastIndexOf('.'));
			System.out.println(selected.toString());
			if(ending.equals(".csv")) {
				controller.speichernCSV(selected.toString());
			}
			else if(ending.equals(".ser")) {
				controller.speichernSer(selected.toString());
			}
		}
		if(e.getSource() == hauptfenster.getMenuLaden()) {
			File selected = generateFileChooser(false);
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
		final JFileChooser jfc = new JFileChooser("./sav");
		jfc.setAcceptAllFileFilterUsed(false);
		FileFilter filterCSV = new FileNameExtensionFilter(".csv", "csv");
		FileFilter filterSER = new FileNameExtensionFilter(".ser", "ser");
		jfc.addChoosableFileFilter(filterCSV);
		jfc.addChoosableFileFilter(filterSER);
		
		if(save) {
			jfc.showSaveDialog(hauptfenster.getMainframe());
		}
		else {
			jfc.showOpenDialog(hauptfenster.getMainframe());
		}
		File selected = jfc.getSelectedFile();
		return selected;
	}

}
