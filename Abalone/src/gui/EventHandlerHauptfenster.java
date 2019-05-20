package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventHandlerHauptfenster implements ActionListener{
	private Hauptfenster hauptfenster;
	private Controller controller;
	
	public EventHandlerHauptfenster(Hauptfenster hauptfenster, Controller c) {
		this.hauptfenster = hauptfenster;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == hauptfenster.getMenuNeuesSpiel()) {
//			new SindSieSicherPanel("Wollen Sie wirklich Neu starten?");
//			SpielerAnlegenFenster spielerAnlegen= new SpielerAnlegenFenster(controller);
			hauptfenster.neuesSpiel();
		}
		if(e.getSource() == hauptfenster.getMenuSpeichern()) {
			
		}
		if(e.getSource() == hauptfenster.getMenuLaden()) {
			
		}
		if(e.getSource() == hauptfenster.getMenuLog()) {
			LogFenster logFenster = new LogFenster();
		}
		if(e.getSource() == hauptfenster.getMenuBeenden()) {
			hauptfenster.beendeSpiel();
		}
		
	}

}
