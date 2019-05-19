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
	private String erlaubteZuege;
	public Controller() throws SpielException {
		try {
			spiel = new Spiel();
		} catch (SpielfeldException e) {
		}
		spiel.addSpieler("Hans", "weiss", 2);
		spiel.addSpieler("JOCHEN", "schwarz", 2);
		Spieler spieler1 = new Spieler("Hans", FarbEnum.WEISS);
		spieler1.setSpielerAmZug(spieler1);
		Spieler spieler2 = new Spieler("JOCHEN", FarbEnum.SCHWARZ);
		spieler1.setSpieler();
		spieler2.setSpieler();
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
		spieler1.naechsterSpieler();
		this.aktualisiereSpielStatus();
		brett.aktualisieren(filtereFeldDaten(spielStatus));
		Spielzug.subscribe(this);
		//System.out.println("______ERLAUBTEZUEGE_____");
		Spielzug.toggleString(FarbEnum.SCHWARZ, "C5");
		Spielzug.getMoeglicheZuege();
		gameFrame.aktualisiere();
		for(int i = 0; i < spielStatus.length; i++) {
			System.out.println(spielStatus[i]);
		}
		
		
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
	
	public String setEraubteZuege(String[] ausgangsfelder) {
		try {
			erlaubteZuege = spiel.getErlaubteZuegeInterface(ausgangsfelder);
		} catch (SpielException e) {
		}
		System.out.println(erlaubteZuege);
		return erlaubteZuege;
	}
	
	//public String getErlaubteZuege(String[] )
}

