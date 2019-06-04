package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import abalone.SpielException;

/**
 * <h1>EventHandlerKIOptionen</h1>
 * Verwaltet die ActionEvents des KI-Optionen-Panels.
 * 
 * @author Gruppe A4
 */
public class EventHandlerKIOptionen implements ActionListener{
	private KIOptionenPanel kiPanel;
	private Controller c; 
	
	/**
	 * Schafft einen EventHandler für ein KiPanel eines Hauptfensters.
	 * 
	 * @param c der Controller des Hauptfensters des Ki-Panels
	 * @param kiPanel das KI-Panel, dessen Events koordiniert werden sollen
	 */
	public EventHandlerKIOptionen(Controller c, KIOptionenPanel kiPanel) {
		this.kiPanel = kiPanel;
		this.c = c; 
	}
	/**
	 * Behandelt die Events eines KI-Panels.
	 * Diese sind:
	 * KI, die am Zug ist, ziehen lassen.
	 * KI, die am Zug ist, durchziehen lassen.
	 * @param e das zu behandelnde Event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == kiPanel.getKiWeiter()) {
			String[] kiZug = {"", ""};

			try {
				c.zieheKI(kiZug);
			} catch(SpielException e1) {
				new FehlerPanel("Ziehen der KI fehlgeschlagen!");
				return;
			}
			
			c.aktualisiereAlles();
		}
		
		if(e.getSource() == kiPanel.getKiDurchziehend()) {
			String[] kiZug = {"DURCHZIEHEN", ""};
			
			try {
				c.zieheKI(kiZug);
			} catch(SpielException e1) {
				new FehlerPanel("Ziehen der KI fehlgeschlagen!");
				return;
			}
			c.aktualisiereAlles();
			
			if(c.nurDurchziehendeKIs()) {
				new FehlerPanel("Achtung! Nur durchziehende KIs im Spiel.\n Da die "
						+ "Strategie der KI \n geheim bleiben soll, wird nun nur gezeigt,\n welche KI gewonnen hat! \nEinen Moment bitte...");
				startKIvsKI(kiZug);
			}	
	}
	}
	
	/**
	 * Wird aufgerufen, wenn das Spiel von KIs bis zum Ende gespielt werden soll.
	 * Die GUI wird dabei blockiert.
	 * 
	 * @param kiZug ein Zug im Format eines KI-Zuges (leer, oder mit DURCHZIEHEN)
	 */
	private void startKIvsKI(String[] kiZug) {
		while(!c.getBedienerInterface().hatGewonnen(c.getSpielerAmZug())) {
			try {
				c.getBedienerInterface().ziehe(kiZug);
			} catch (SpielException e) {
				new FehlerPanel("Fehler beim Ziehen der KI!");
			}
			c.aktualisiereAlles();
			if(c.gewonnen()) {
				return;
			}
		}
	}

}
