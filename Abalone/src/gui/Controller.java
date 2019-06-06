package gui;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingWorker;

import abalone.Spiel;
import abalone.SpielException;
import abalone.bedienerInterface;
import abalone.spielbrett.SpielfeldException;

/**
 * <h1>Controller</h1>
 * Die Controller-Klasse steuert das Spiel und enthaelt
 * die Main zum Ausfuehren des Spiels als GUI.
 * 
 * @author Gruppe A4
 */
public class Controller {
	private bedienerInterface spiel;
	private Hauptfenster gameFrame;
	private String[] spielStatus;
	private String erlaubteZuege;
	
	/**
	 * Die Main startet das Spiel, indem sie einen Controller erstellt.
	 * 
	 * @param args Der Default Parameter der Main Methode
	 */
	public static void main(String[] args) {
		try {
			new Controller();
		} catch (SpielException e) {
			new FehlerPanel("Fehler beim Erstellen des Controllers!");
		}
	}
	
	/**
	 * Der Konstruktor des Controllers. Erstellt ein Spiel.
	 * 
	 * @throws SpielException wird bei einem Fehler beim Laden geworfen
	 */
	public Controller() throws SpielException {
		try {
			spiel = new Spiel();
		} catch (SpielfeldException e) {
			new FehlerPanel("Fehler beim Laden der Spiels!");
		}
		
		new Hauptmenue(this);
	}
	
	/**
	 * Aktualisiert den Spielstatus.
	 * 
	 */
	private void aktualisiereSpielStatus() {
		spielStatus = spiel.getStatus().split("\\n");
	}
	
	/**
	 * Aktualisiert das StatusPanel.
	 * 
	 */
	private void aktualisiereStatus() {
		String[] spieler1Array = spielStatus[1].split(":");
		String[] spieler2Array = spielStatus[2].split(":");
		String[] spieler1 = spieler1Array[1].split(",");
		String[] spieler2 = spieler2Array[1].split(",");
		String amZug = spiel.getSpielerAmZug();
		gameFrame.getStatusPanel().aktualisiereStatus(amZug, spieler1, spieler2);
	}
	
	/**
	 * Aktualisiert die Historie.
	 * 
	 */
	private void aktualisiereHistorie() {
		String historie = spiel.getHistorie();
		gameFrame.getHistoriePanel().aktualisiereHistorie(historie);
	}
	
	/**
	 * Filtert aus dem Spielstatus, die fuer das Spielbrett notwendigen
	 * Informationen und speichert sie in ein 2-dimensionales String-Array.
	 * 
	 * @param daten String-Array, der den Spielstatus enthaelt
	 * @return 2-dimensionales String-Array, welches die fuer das Spielbrett
	 * notwendigen Daten enthaelt
	 */
	private String[][] filtereFeldDaten(String[] daten) {
		String[][] felder = new String[61][];
		for(int i = 6; i <= 66; i++ ) {
			felder[i-6] = daten[i].split(",");
		}
		return felder;
	}
	
	/**
	 * Gibt die erlaubten Zuege zurueck anhand uebergebener Ausgangsfelder.
	 * 
	 * @param ausgangsfelder String-Array, mit Ausgangsfeldern
	 * @return String, der die erlaubten Zuege enthaelt.
	 */
	public String setEraubteZuege(String[] ausgangsfelder) {
		if(!ausgangsfelder[0].equals("")) {
		try {
			erlaubteZuege = spiel.getErlaubteZuegeInterface(ausgangsfelder);
		} catch (SpielException e) {
			new FehlerPanel("Fehler beim Laden der erlaubten Zuege!");
		}
		return erlaubteZuege;
		}
		return null;
	}
	
	public String getErlaubteZuege(String[] ausgangsfelder) {
		if(!ausgangsfelder[0].equals("")) {
			try {
				return spiel.getErlaubteZuegeInterface(ausgangsfelder);
			} catch (SpielException e) {
				new FehlerPanel("Fehler beim Laden der erlaubten Zuege!");
			}
			}
			return null;
	}
	
	/**
	 * Aktualisiert das Spielbrett.
	 * 
	 */
	public void aktualisiereBrett() {
		gameFrame.aktualisiere();
	}
	
	/**
	 * Startet das Spiel neu, indem es ein neues Spiel anlegt.
	 * 
	 */
	public void spielNeuStarten() {
		try {
			spiel = new Spiel();
			
		} catch (SpielfeldException e) {
			new FehlerPanel("Fehler beim Neustarten des Spiels!");
		}
	}
	
	/**
	 * Legt die Spieler an.
	 * 
	 * @param name1 Name des ersten anzulegenden Spielers
	 * @param name2 name des zweiten anzulegenden Spielers
	 * @param anzahlSpieler Anzahl der Spieler
	 * @throws SpielException wird geworfen, wenn ein Spieler nict angelegt werden kann.
	 */
	public void spielerAnlegen(String name1, String name2, int anzahlSpieler) throws SpielException {
		spiel.addSpieler(name1, "weiss", anzahlSpieler);
		spiel.addSpieler(name2, "schwarz", anzahlSpieler);
		this.aktualisiereSpielStatus();
	}
	
	/**
	 * Startet das Hauptfenster.
	 * 
	 * @throws SpielException wird geworfen, wenn Hauptfenster nicht gestartet werden kann
	 */
	public void hauptFensterStarten() throws SpielException {
		gameFrame = new Hauptfenster(this);
		aktualisiereAlles();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			new FehlerPanel("Fehler beim Starten des Hauptfensters!");
		}
		Spielzug.subscribe(this);

	}
	
	/**
	 * Speichert das Spiel in eine CSV-Datei.
	 * 
	 * @param dateipfad Dateipfad, in die der Spielstand gespeichert werden soll.
	 */
	public void speichernCSV(String dateipfad) {
		try {
			spiel.speichernCSV(dateipfad);
		} catch (SpielException e) {
			new FehlerPanel("Fehler beim Speichern des Spiels!");
		}
	}
	
	/**
	 * Speichert das Spiel in eine serialisierte Datei.
	 * 
	 * @param dateipfad Dateipfad, in die der Spielstand gespeichert werden soll.
	 */
	public void speichernSer(String dateipfad) {
		try {
			spiel.speichernSerialisiert(dateipfad);
		} catch (SpielException e) {
			new FehlerPanel("Fehler beim Speichern des Spiels!");
		}
	}
	
	/**
	 * Ladet das Spiel aus einer CSV-Datei.
	 * 
	 * @param dateipfad Dateipfad, aus der der Spielstand geladen wird.
	 */
	public void ladenCSV(String dateipfad) {
		try {
			spiel.lesenCSV(dateipfad);
		} catch (SpielException e) {
			new FehlerPanel("Fehler beim Laden des Spiels!");
		}
	}
	
	/**
	 * Ladet das Spiel aus einer serialisierten Datei.
	 * 
	 * @param dateipfad Dateipfad, aus der der Spielstand geladen wird.
	 */
	public void ladenSer(String dateipfad) {
		try {
			spiel.lesenSerialisiert(dateipfad);
		} catch (SpielException e) {
			new FehlerPanel("Fehler beim Laden des Spiels!");
		}
	}
	
	/**
	 * Guckt, ob Spieler gewonnen hat.
	 * 
	 * @return boolean (wahr = gewonnen, falsch = nicht gewonnen)
	 */
	public boolean gewonnen() {
		for(String spieler : spiel.getSpielerImSpielInterface().split(",")) {
			if(!spieler.equals(spiel.getSpielerAmZug())) {
				if(spiel.hatGewonnen(spieler)) {
					gameFrame.spielGewonnen(spieler);
					return true;
				}
			}
		}
		
		return false;
	}

	/**
	 * Zieht eine oder mehrere Figuren und aktualisiert die Ansicht entsprechend.
	 * 
	 * @throws SpielException wird geworfen, wenn beim Ziehen was scief geht
	 */
	public void ziehe() throws SpielException {
		
		spiel.ziehe(Spielzug.getZug());
		getGeschlagenenStein();
		this.aktualisiereAlles();
//		gameFrame.aktualisiere();
		if(gewonnen()) {
			return;
		}
		if(spiel.getSpielerAmZug().startsWith("KI") 
				&& spiel.getSpielerAmZug().endsWith("(durchziehend)")) {
			String[] kiZug = {"",""};
			zieheKI(kiZug);
		}
	}
	
	/**
	 * Zieht Figuren der KI.
	 * 
	 * @param zug String-Array, der zu spielenden Zug enthaelt
	 * @throws SpielException wird geworfen, wenn beim Ziehen was scief geht
	 */
	public void zieheKI(String[] zug) throws SpielException {
		boolean durchziehendAmZug = spiel.getSpielerAmZug().startsWith("KI") 
				&& spiel.getSpielerAmZug().endsWith("(durchziehend)");
		spiel.ziehe(zug);
		getGeschlagenenStein();
		this.aktualisiereAlles();
//		gameFrame.aktualisiere();
		if(gewonnen()) {
			return;
		}
		if(!durchziehendAmZug && spiel.getSpielerAmZug().startsWith("KI") 
				&& spiel.getSpielerAmZug().endsWith("(durchziehend)")) {
			String[] kiZug = {"",""};
			zieheKI(kiZug);
			
		}
		
	}
	
	public void zieheKI2(String[] zug) throws SpielException {
		spiel.ziehe(zug);
		getGeschlagenenStein();
		this.aktualisiereAlles();
//		gameFrame.aktualisiere();
	}
	
	/**
	 * Gibt die Daten des Spielbrettes als 2-dimensionales String-Array zurueck.
	 * 
	 * @return 2-dimensionales String-Array, welches die Daten des Spielbrettes enthaelt
	 */
	public String[][] getFeldDaten() {
		this.aktualisiereSpielStatus();
		return this.filtereFeldDaten(spielStatus);
	}
	
	/**
	 * Gibt FeldPanel anhand uebergebener ID zurueck.
	 * 
	 * @param id ID des Spielfeldes
	 * @return Spielfeld als FeldPanel
	 */
	public FeldPanel getSpielfeldMitId(String id) {
		return gameFrame.getSpielfeldPanel().getFeld(id);
	}
	
	/**
	 * Setzt die ausgewaehlten Felder zurueck.
	 * 
	 */
	public void resetAuswaehlbar() {
		gameFrame.resetFelderAuswaehlbar();
	}
	
	/**
	 * Aktualisiert das gesamte Spiel.
	 * 
	 */
	public void aktualisiereAlles() {
		this.aktualisiereSpielStatus();
		this.aktualisiereBrett();
		this.aktualisiereStatus();
		this.aktualisiereHistorie();
		this.aktualisiereKI();
	}
	
	/**
	 * Aktualisiert die KI.
	 * 
	 */
	private void aktualisiereKI() {
		String spielerAmZug = spiel.getSpielerAmZug();
		
		if(spielerAmZug != null && spielerAmZug.startsWith("KI")) {
			gameFrame.getKiOptionenPanel().steuereKIPanel(spielerAmZug, true);
		}
		else {
			gameFrame.getKiOptionenPanel().steuereKIPanel(null, false);
		}
	}
	
	/**
	 * Gibt das Hauptfenster zurueck.
	 * 
	 * @return Hauptfenster das Hauptfenster, das der Controller koordniert.
	 */
	public Hauptfenster getGameFrame() {
		return this.gameFrame;
	}
	
	/**
	 * Gibt das bedienerInterface zurueck.
	 * 
	 * @return bedienerInterface das Spiel-Attribut des Controllers.
	 */
	public bedienerInterface getBedienerInterface() {
		return this.spiel;
	}
	
	/**
	 * Gibt den Namen des Spielers, der am Zug ist, als String zurueck.
	 * 
	 * @return Name des Spielers, der am Zug ist
	 */
	public String getSpielerAmZug() {
		return spiel.getSpielerAmZug();
	}
	
	/**
	 * Gibt das FarbEnum des Spielers am Zug zurueck.
	 * 
	 * @return Farbe des Spielers, der am Zug ist
	 */
	public FarbEnum getSpielerAmZugFarbe() {
		String[] spieler1 = this.spielStatus[1].split(",");
		String spieler1Name = spieler1[0].split(":")[1];
		String[] spieler2 = this.spielStatus[2].split(",");
		String spielerAmZug = this.spielStatus[3].split(":")[1];
		if(spieler1Name.equals(spielerAmZug)) {
			if(spieler1[1].equals("weiss"))
					return FarbEnum.WEISS;
			else
				return FarbEnum.SCHWARZ;
		} else {
			if(spieler2[1].equals("weiss"))
				return FarbEnum.WEISS;
			else
			return FarbEnum.SCHWARZ;
		}
	}
	
	/**
	 * Gibt das bedienerInterface zurueck.
	 * 
	 * @return bedienerInterface
	 */
	public bedienerInterface getBenutzerInterface() {
		return this.spiel;
	}
	
	/**
	 * Prueft, ob beide KIs durchziehend sind.
	 * 
	 * @return boolean (true=beide durchziehend)
	 */
	public boolean nurDurchziehendeKIs() {
		String[] spieler = spiel.getSpielerImSpielInterface().split(",");
		
		boolean spieler1 = spieler[0].startsWith("KI") && spieler[0].endsWith("(durchziehend)");
		boolean spieler2 = spieler[1].startsWith("KI") && spieler[1].endsWith("(durchziehend)");
		
		return spieler1 && spieler2;
	}

	/**
	 * Gibt die ausgewaehlten Felder als ArrayList von FeldPanels zurueck.
	 * 
	 * @return ausgewaehlte Felder als ArrayList aus FeldPanels
	 */
	public ArrayList<FeldPanel> bekommeGewahlteFelder() {
		return gameFrame.bekommeGewaehlteFelder();
	}
	
	public void getGeschlagenenStein() {
		String feld;
		String status = spiel.getStatus();
		if (status.contains("true")) {
			String[] split = status.split(";");
			for(String s : split) {
				if (s.contains("true")) {
					feld = s.substring(12,14);
					this.getSpielfeldMitId(feld).setGeschlagen(true);
				}
			}
		}
	}
	
	public void klasseErstellen() {
		KIDurchziehend ki = new KIDurchziehend();
		ki.execute();
	}
	
	class KIDurchziehend  extends SwingWorker<Void, Void> {

		@Override
		protected Void doInBackground() throws Exception {
			boolean durchziehendAmZug = spiel.getSpielerAmZug().startsWith("KI") 
					&& spiel.getSpielerAmZug().endsWith("(durchziehend)");
			while(durchziehendAmZug) {
				String[] kiZug = {"",""};
				zieheKI2(kiZug);
				Thread.sleep(350);
				if(gewonnen()) {
					break;
				}
			}
			return null;

		}
	}
}
