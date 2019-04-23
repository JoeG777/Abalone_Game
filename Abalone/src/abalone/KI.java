package abalone;

import java.util.ArrayList;
import java.util.HashMap;

import abalone.spielbrett.Spielbrett;
import abalone.spielbrett.SpielbrettException;
import abalone.spielbrett.SpielfeldException;

public class KI extends Spieler {

	private static final long serialVersionUID = 110L;
	private static int anzahlKIs = 0;
	private static final String[] namen = {"KI-1", "KI-2"};
	
	private String[] besterZug;
	private HashMap<String, Integer> werteMap;
	private int gegnerFigVorZug;
	private boolean durchziehend;
	
	/**
	 * Erzeugt eine neu KI mit passenden Namen und
	 * initialisiert die werteMap der KI.
	 * @param farbe die Farbe der KI
	 * @param brett ein Abalone-Spielbrett.
	 */
	public KI(FarbEnum farbe, Spielbrett brett) {
		super(namen[anzahlKIs], farbe);
		setGegnerFigVorZug(0);
		setDurchziehend(false);
		this.werteMap = new HashMap<String, Integer>();
		initWerteMap(brett);
		anzahlKIs++;
	}
	
	/**
	 * Initialisiert die Werte der WerteMap.
	 * Diese Methode läuft, ausgehend
	 * vom mittleren Feld, alle Felder ab und gibt diesen die passende
	 * Bewertung. Dabei geht man vom mittleren Feld nach rechts-oben,
	 * dann nach rechts-unten, links-unten, links, links-oben, rechts-oben
	 * und so weiter.
	 * @param brett ein Abalone-Spielbrett
	 */
	private void initWerteMap(Spielbrett brett) {
		boolean done = false;
		int schritte = 1;
		String aktuellesFeld = "E5";
		int wert = 12;
		werteMap.put("E5", 15);
		
		while(!done)  {
			aktuellesFeld = laufeUndBewerte(brett, aktuellesFeld, 4, 1, wert-schritte);
			aktuellesFeld = laufeUndBewerte(brett, aktuellesFeld, 3, schritte-1, wert-schritte);
			aktuellesFeld = laufeUndBewerte(brett, aktuellesFeld, 5, schritte, wert-schritte);
			aktuellesFeld = laufeUndBewerte(brett, aktuellesFeld, 2, schritte, wert-schritte);
			aktuellesFeld = laufeUndBewerte(brett, aktuellesFeld, 0, schritte, wert-schritte);
			aktuellesFeld = laufeUndBewerte(brett, aktuellesFeld, 1, schritte, wert-schritte);
			aktuellesFeld = laufeUndBewerte(brett, aktuellesFeld, 4, schritte, wert-schritte);

			schritte++;

			if(schritte == 5) {
				done = true;
			}

		}
	}
	
	/**
	 * Hilfsmethode der WerteMap. Laeuft von der uebergebenen
	 * ID die uebergebene Anzahl an Schritten in die uebergebene Richtung.
	 * Die Methode setzt dabei auf jeden Feld den uebergebenen Wert.
	 * @param brett ein Abalone-Spielbrett.
	 * @param id die ID des Startfeldes.
	 * @param richtung die Richtung, in die gezogen werden soll (vgl. Nachbarn-Array des Spielfeldes)
	 * @param schritte die Anzahl der Schritte
	 * @param wert der Wert, der vergeben werden soll.
	 * @return die ID des letzten Feldes, auf das gegangen wurde.
	 */
	private String laufeUndBewerte(Spielbrett brett, String id, int richtung, int schritte, int wert) {
		String newID = id;
		for(int i = 0; i < schritte; i++) {
			newID = brett.getNachbarByIdInRichtung(newID, richtung);
			werteMap.put(newID, wert);
		}
		return newID;
	}
	
	/**
	 * Gibt die Anzahl der erzeugten KIs zurueck.
	 * @return die Anzahl der aktuell existenten KIs.
	 */
	public int getAnzahlKIs() {
		return anzahlKIs;
	}
	
//	public void setBesterZug(String[] besterZug) {
//		this.besterZug = besterZug;
//	}
//	
//	
//	public String[] getBesterZug() {
//		return this.besterZug;
//	}
	
	/**
	 * Gibt das GegnerFigVorZug-Attribut der KI zurueck.
	 * @return das GegnerFigVorZug-Attribut als int.
	 */
	public int getGegnerFigVorZug() {
		return gegnerFigVorZug;
	}
	
	/**
	 * Setzt das GegnerFigVorZug-Attribut der KI.
	 * @param gegnerFigVorZug die Anzahl der gegnerischen Figuren
	 * bevor moegliche Zuege untersucht werden sollen.
	 */
	public void setGegnerFigVorZug(int gegnerFigVorZug) {
		this.gegnerFigVorZug = gegnerFigVorZug;
	}
	
	/**
	 * Gibt für eine uebergebene ID die Bewertung zurueck.
	 * @param id die ID eines Spielfeldes als String.
	 * @return die Bewertung des uebergebenen Spielfeldes.
	 */
	private int getFeldStaerkeById(String id) {
		return werteMap.get(id);
	}
	
	/**
	 * Gibt das durchziehend-Attribut der KI zurück.
	 * @param durchziehend true, wenn die KI durchziehend soll, false, wenn nicht.
	 * @return das durchziehend-Attribut der KI.
	 */
	private boolean getDurchziehend(boolean durchziehend) {
		return this.durchziehend;
	}
	
	/**
	 * Setzt das durchziehend-Attribut der KI.
	 * @param durchziehend true, wenn die KI durchziehen soll, false, wenn nicht.
	 */
	public void setDurchziehend(boolean durchziehend) {
		this.durchziehend = durchziehend;
	}
	
	/**
	 * Berechnet die Staerke des uebergebenen Spielbretts.
	 * @param spielbrett ein zu bewertendes Spielbrett
	 * @return Die Staerke der eigenen Position.
	 */
	public int calcStaerkeDesBretts(Spielbrett spielbrett) {
		int score = bewerteFigurPos(spielbrett);
		score += bewerteZusammenhalt(spielbrett);
		if(isGegnerGeschlagen(spielbrett)) {
			score += 50;
		}
		return score;
	}
	
	/**
	 * Bewertet die aktuelle Position der eigenen Figuren. Dafuer 
	 * wird die werteMap der KI verwendet. 
	 * @param spielbrett Ein zu bewertendes Spielbrett.
	 * @return die Bewertung der Positonen der eigenen Figuren.
	 */
	private int bewerteFigurPos(Spielbrett spielbrett) {
		int score = 0;
		
		for(String id : spielbrett.getFelderMitFarbe(this.getFarbe())) {
			score += getFeldStaerkeById(id);
		}
		return score;
	}
	
	/**
	 * Ueberprueft, durch wie viele eigenen Kugeln jede eigene Kugeln
	 * umgeben ist. Fuer jede Kugel wird +1 gerechnet.
	 * @param spielbrett Ein zu bewertendes Spielbrett.
	 * @return die Bewertung des Zusammenhalts der eigenen Figuren.
	 */
	private int bewerteZusammenhalt(Spielbrett spielbrett) {
		int score = 0;
		
		for(String id : spielbrett.getFelderMitFarbe(this.getFarbe())) {
			for(String nachbarId : spielbrett.getNachbarnByIdVonFeld(id)) {
				if((nachbarId != null && spielbrett.istBesetzt(nachbarId)) &&
						spielbrett.getFarbeDerFigurById(nachbarId)
						== this.getFarbe()) {
					score++;
				}
			}
		}
		
		return score;
	}
	
	/**
	 * Ueberprueft, ob der Gegner der KI eine Figur verloren hat, nachdem 
	 * der Zug ausgefuehrt wurde. Dafuer muss die Instanzvariable gegnerFigVorZug
	 * korrekt gesetzt sein.
	 * @param nachZug Das Spielbrett nachdem der Zug ausgeführt wurde.
	 * @return true, wenn Gegner eine Figur verloren hat, false, wenn nicht.
	 */
	private boolean isGegnerGeschlagen(Spielbrett nachZug) {
		int gegnerFigNachZug = 0;
		
		if(this.getFarbe() == FarbEnum.SCHWARZ) {
			gegnerFigNachZug = nachZug.getFelderMitFarbe(FarbEnum.WEISS).size();
		}
		else {
			gegnerFigNachZug = nachZug.getFelderMitFarbe(FarbEnum.SCHWARZ).size();
		}
		if(gegnerFigNachZug > gegnerFigVorZug) {
			return true;
		}
		return false;	
	}

}
