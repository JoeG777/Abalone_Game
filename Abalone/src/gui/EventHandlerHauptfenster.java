package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventHandlerHauptfenster implements ActionListener{
	private Hauptfenster hauptfenster;
	
	public EventHandlerHauptfenster(Hauptfenster hauptfenster) {
		this.hauptfenster = hauptfenster;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == hauptfenster.getMenuNeuesSpiel()) {
			SpielerAnlegenFenster spielerAnlegen= new SpielerAnlegenFenster();
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
