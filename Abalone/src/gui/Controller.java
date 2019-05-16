package gui;

import javax.swing.JFrame;

import abalone.Spiel;
import abalone.SpielException;
import abalone.bedienerInterface;
import abalone.spielbrett.SpielfeldException;

public class Controller {
	private bedienerInterface spiel;
	private Hauptfenster gameFrame;
	private String[] spielStatus;
	private Spielbrett brett;
	public Controller() throws SpielException {
		try {
			spiel = new Spiel();
		} catch (SpielfeldException e) {
		}
		spiel.addSpieler("Hans", "weiss", 2);
		spiel.addSpieler("JOCHEN", "schwarz", 2);
		this.aktualisiereSpielStatus();
		brett = new Spielbrett(this.filtereFeldDaten(spielStatus));
		gameFrame = new Hauptfenster(this);
	}
	
	private void aktualisiereSpielStatus() {
		spielStatus = spiel.getStatus().split(",");
	}
	
	private String[] filtereFeldDaten(String[] daten) {
		int counter = 0;
		String[] felder = new String[61];
		for(int i = 7; i <= 128; i+=2 ) {
			felder[counter] = daten[i] +","+ daten[i+1];
			counter ++;
			
		}
		return felder;
	}
	
	public Spielfeld getSpielfeldMitId(String id) {
		return brett.getFeld(id);
	}
}

