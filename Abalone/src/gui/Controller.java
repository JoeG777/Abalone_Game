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
		brett.aktualisieren(filtereFeldDaten(spielStatus));
		System.out.println("-----------Aktualisere Mainframe.....");
		gameFrame.aktualisiere();
		
		
	}
	
	private void aktualisiereSpielStatus() {
		spielStatus = spiel.getStatus().split("\\n");
	}
	
	private String[] filtereFeldDaten(String[] daten) {
		String[] felder = new String[61];
		for(int i = 6; i <= 66; i++ ) {
			felder[i-6] = daten[i];
			System.out.println(felder[i-6]);
		}
		return felder;
	}
	
	public Spielfeld getSpielfeldMitId(String id) {
		return brett.getFeld(id);
	}
}

