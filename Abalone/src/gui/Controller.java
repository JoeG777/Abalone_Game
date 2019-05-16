package gui;

import java.util.concurrent.TimeUnit;

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
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] zug = {"G5","G4"};
		spiel.ziehe(zug);
		System.out.println(spiel.getStatus());
		this.aktualisiereSpielStatus();
		System.out.println(spiel.getStatus());
		brett.aktualisieren(filtereFeldDaten(spielStatus));
		gameFrame.aktualisiere();
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

