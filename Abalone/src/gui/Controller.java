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
	private String spielerName1;
	private FarbEnum spielerFarbe1;
	private FarbEnum spielerFarbe2;
	private String spielerName2;
	
	public static void main(String[] args) {
		try {
			new Controller();
		} catch (SpielException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Controller() throws SpielException {
//		try {
//			spiel = new Spiel();
//		} catch (SpielfeldException e) {
//		}
		
		new Hauptmenue(this);
	}
	
	private void aktualisiereSpielStatus() {
		spielStatus = spiel.getStatus().split("\\n");
	}
	
	private void aktualisiereStatus() {
		String[] spieler1Array = spielStatus[1].split(":");
		String[] spieler2Array = spielStatus[2].split(":");
		String[] spieler1 = spieler1Array[1].split(",");
		String[] spieler2 = spieler2Array[1].split(",");
		String amZug = spiel.getSpielerAmZug();
		gameFrame.getStatusPanel().aktualisiereStatus(amZug, spieler1, spieler2);
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
		

	}
	
	public void ladenSer(String dateipfad) {
		try {
			spiel.lesenSerialisiert(dateipfad);
		} catch (SpielException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	public void ziehe() throws SpielException{
		spiel.ziehe(Spielzug.getZug());
		this.aktualisiereAlles();
		gameFrame.aktualisiere();
		if(spiel.getSpielerAmZug().startsWith("KI") 
				&& spiel.getSpielerAmZug().endsWith("(durchziehend)")) {
			String[] kiZug = {"",""};
			zieheKI(kiZug);
		}
	}
	
	public void zieheKI(String[] zug) throws SpielException{
		boolean durchziehendAmZug = spiel.getSpielerAmZug().startsWith("KI") 
				&& spiel.getSpielerAmZug().endsWith("(durchziehend)");
		
		spiel.ziehe(zug);
		this.aktualisiereAlles();
		gameFrame.aktualisiere();
		
		if(!durchziehendAmZug && spiel.getSpielerAmZug().startsWith("KI") 
				&& spiel.getSpielerAmZug().endsWith("(durchziehend)")) {
			String[] kiZug = {"",""};
			zieheKI(kiZug);
		}

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
		this.aktualisiereKI();
		
	}
	

	private void aktualisiereKI() {
		String spielerAmZug = spiel.getSpielerAmZug();
		
		if(spielerAmZug != null && spielerAmZug.startsWith("KI")) {
			gameFrame.getKiOptionenPanel().steuereKIPanel(spielerAmZug, true);
		}
		else {
			gameFrame.getKiOptionenPanel().steuereKIPanel(null, false);
		}
	}
	
	public Hauptfenster getGameFrame() {
		return this.gameFrame;

	}
	
	public bedienerInterface getBedienerInterface() {
		return this.spiel;
	}
	
	public String getSpielerAmZug() {
		return spiel.getSpielerAmZug();
	}
	
	public FarbEnum getSpielerAmZugFarbe() {
		String[] spieler1 = this.spielStatus[1].split(",");
		String spieler1Name = spieler1[0].split(":")[1];
		String[] spieler2 = this.spielStatus[2].split(",");
		String spieler2Name = spieler1[0].split(":")[1];
		String spielerAmZug = this.spielStatus[3].split(":")[1];
		if(spieler1Name.equals(spielerAmZug)) {
			if(spieler1[1].equals("weiss")){
					return FarbEnum.WEISS;
			}else {
				return FarbEnum.SCHWARZ;
			}
		}else {
			if(spieler2[1].equals("weiss")){
				return FarbEnum.WEISS;
		}else {
			return FarbEnum.SCHWARZ;
		}
		}
	}
	
	public bedienerInterface getBenutzerInterface() {
		return this.spiel;
	}
	
	public boolean nurDurchziehendeKIs() {
		String[] spieler = spiel.getSpielerImSpielInterface().split(",");
		
		boolean spieler1 = spieler[0].startsWith("KI") && spieler[0].endsWith("(durchziehend)");
		boolean spieler2 = spieler[1].startsWith("KI") && spieler[1].endsWith("(durchziehend)");
		
		return spieler1 && spieler2;
	}
}

