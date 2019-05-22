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
	
	private void aktualisiereStatus() {
		String[] spieler1Array = spielStatus[1].split(":");
		String[] spieler2Array = spielStatus[2].split(":");
		String[] spieler1 = spieler1Array[1].split(",");
		String[] spieler2 = spieler2Array[1].split(",");
		
		gameFrame.getStatusPanel().aktualisiereStatus(spieler1, spieler2);
	}
	
	private void aktualisiereHistorie() {
		String historie = spiel.getHistorie();
		gameFrame.getHistoriePanel().aktualisiereHistorie(historie);
	}
	
	private String[][] filtereFeldDaten(String[] daten) {
		String[][] felder = new String[61][];
		for(int i = 6; i <= 66; i++ ) {
			felder[i-6] = daten[i].split(",");
		}
		return felder;
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
		
	}
	
	public void hauptFensterStarten() throws SpielException {
		gameFrame = new Hauptfenster(this);
		aktualisiereAlles();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Spielzug.subscribe(this);

	}
	
	public void speichernCSV(String dateipfad) {
		try {
			spiel.speichernCSV(dateipfad);
		} catch (SpielException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void speichernSer(String dateipfad) {
		try {
			spiel.speichernSerialisiert(dateipfad);
		} catch (SpielException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ladenCSV(String dateipfad) {
		try {
			spiel.lesenCSV(dateipfad);
		} catch (SpielException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		aktualisiereAlles();
	}
	
	public void ladenSer(String dateipfad) {
		try {
			spiel.lesenSerialisiert(dateipfad);
		} catch (SpielException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		aktualisiereAlles();
	}

	public void ziehe() throws SpielException {
		spiel.ziehe(Spielzug.getZug());
		spieler1.naechsterSpieler();
		this.aktualisiereSpielStatus();
		this.aktualisiereStatus();
		this.aktualisiereHistorie();
		gameFrame.aktualisiere();

	}
	
	public String[][] getFeldDaten() {
		this.aktualisiereSpielStatus();
		return this.filtereFeldDaten(spielStatus);
	}
	
	public FeldPanel getSpielfeldMitId(String id) {
		return gameFrame.getSpielfeldPanel().getFeld(id);
	}
	
	public void resetAuswaehlbar() {
		gameFrame.resetFelderAuswaehlbar();
	}
	
	public void aktualisiereAlles() {
		this.aktualisiereSpielStatus();
		this.aktualisiereBrett();
		this.aktualisiereStatus();
		this.aktualisiereHistorie();
		this.kiMitspielerPruefen();
		
	}
	

	private void kiMitspielerPruefen() {
		String[] spieler = spiel.getSpielerImSpielInterface().split(",");
		System.out.println(spieler[0]);
		if(spieler[0]!= null && spieler[0].startsWith("KI")) {
			gameFrame.getKiOptionenPanel().aktiviereKI1();
		}
		else {
			gameFrame.getKiOptionenPanel().deaktiviereKI1();
		}
		if(spieler[1] != null && spieler[1].startsWith("KI")) {
			gameFrame.getKiOptionenPanel().aktiviereKI2();
		}
		else {
			gameFrame.getKiOptionenPanel().deaktiviereKI2();
		}
		
		if(spieler[0] != null && spieler[0].startsWith("KI") && spieler[0].length() > 4) {
			gameFrame.getKiOptionenPanel().aktiviereDurchziehendKI1();
		}
		
		if(spieler[1] != null && spieler[1].startsWith("KI") &&spieler[1].length() > 4) {
			gameFrame.getKiOptionenPanel().aktiviereDurchziehendKI2();
		}
	}
	public Hauptfenster getGameFrame() {
		return this.gameFrame;

	}
}

