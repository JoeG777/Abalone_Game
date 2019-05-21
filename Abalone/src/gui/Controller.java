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
	private Spieler spieler1;
	private Spieler spieler2;
	public Controller() throws SpielException {
		try {
			spiel = new Spiel();
		} catch (SpielfeldException e) {
		}
	}
	
	private void aktualisiereSpielStatus() {
		spielStatus = spiel.getStatus().split("\\n");
	}
	
	private String[] filtereFeldDaten(String[] daten) {
		String[] felder = new String[61];
		for(int i = 6; i <= 66; i++ ) {
			felder[i-6] = daten[i];
		}
		return felder;
	}
	
	public Spielfeld getSpielfeldMitId(String id) {
		return brett.getFeld(id);
	}
	
	public void resetAuswaehlbar() {
		brett.resetAuswaehlbar();
	}
	
	public String setEraubteZuege(String[] ausgangsfelder) {
		if(!ausgangsfelder[0].equals("")) {
		try {
			erlaubteZuege = spiel.getErlaubteZuegeInterface(ausgangsfelder);
		} catch (SpielException e) {
		}
		return erlaubteZuege;
		}
		return null;
	}
		
	public void aktualisiereBrett() {
		gameFrame.aktualisiere();
	}
	
	public void spielNeuStarten() {
		try {
			spiel = new Spiel();
		} catch (SpielfeldException e) {
			e.printStackTrace();
		}
	}
	
	public void spielerAnlegen(String name1, String name2, int anzahlSpieler) throws SpielException {
		spiel.addSpieler(name1, "weiss", anzahlSpieler);
		spiel.addSpieler(name2, "schwarz", anzahlSpieler);
		spieler1 = new Spieler(name1, FarbEnum.WEISS);
		spieler1.setSpielerAmZug(spieler1);
		spieler2 = new Spieler(name2, FarbEnum.SCHWARZ);
		spieler1.setSpieler();
		spieler2.setSpieler();
		this.aktualisiereSpielStatus();
		brett = new Spielbrett(this.filtereFeldDaten(spielStatus));
	}
	
	public void hauptFensterStarten() throws SpielException {
		gameFrame = new Hauptfenster(this);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Spielzug.subscribe(this);

	}
	
	public void ziehe() throws SpielException {
		System.out.println("ICH ZIEHE" + Spielzug.getZug());
		spiel.ziehe(Spielzug.getZug());
		spieler1.naechsterSpieler();
		this.aktualisiereSpielStatus();
		brett.aktualisieren(this.filtereFeldDaten(spielStatus));
		gameFrame.aktualisiere();
		for(String eintrag : spielStatus){
			System.out.println(eintrag);
		}
		
	}
	
	//public String getErlaubteZuege(String[] )
}

