/**
 * @author Gruppe A4
 * @version 1.2
 * 
 * Die Klasse Spielbrett erschafft ein Abalone Spielbrett 
 * auf Basis einer Hash Map.
 * Sie bietet Methoden, um auf verschiedene Spielfelder zuzugreifen oder
 * Figuren zu bewegen.
 */

package abalone;

import java.util.HashMap;

public class Spielbrett {

	private static final String[] KOORDINATENQUER = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
	private static final String[] KOORDINATENDIAGONAL = {"1", "2", "3", "4", "5", "6", "7", "8", "9"} ;
	private final int MAXDIAGONALE = 9;
	private final int MITTE = 5;

	private HashMap<String, Spielfeld> brett;


	/**
	 * Konstruktor für Abalone Spielbrett
	 * 
	 * @since 1.0
	 */
	public Spielbrett() {
		setBrett(new HashMap<String, Spielfeld>());
		schaffeMapping(); 
	}


	/**
	 * Gibt das Brettattribut des Spielbretts zurück.
	 * @return Brettattribut in Form von einer
	 * HashMap<String,Spielfeld>  des Spielbretts.
	 * 
	 * @since 1.0
	 */
	public HashMap<String, Spielfeld> getBrett() {
		return this.brett;
	}



	/**
	 * Setzt das Brettattribut des Spielbretts.
	 * @param brett Eine HashMap<String, Spielfeld>, die das Brett modelliert
	 * 
	 * @since 1.0
	 */
	public void setBrett(HashMap<String, Spielfeld> brett) {
		this.brett = brett;
	}



	/**
	 * Verknüpft alle Feldbezeichnungen
	 * eines Abalone-Bretts mit Spielfeld-Objekten.
	 * 
	 * @since 1.0
	 */
	private void schaffeMapping() {
		for(int i = 0; i < KOORDINATENQUER.length; i++) {
			// Untere Felder schaffen und verknüpfen
			if(i < MITTE) {
				//verknüpft von links nach rechts
				for(int j = 0; j < MITTE + i; j++) {
					String key = KOORDINATENQUER[i] + KOORDINATENDIAGONAL[j];
					weiseKeyFeldZu(key);
				}
			}
			// Obere Felder schaffen und verknüpfen 
			else {
				//verknüpft von rechts nach links
				for(int j = KOORDINATENQUER.length-1; j > i-MITTE; j--) {
					String key = KOORDINATENQUER[i] + KOORDINATENDIAGONAL[j];
					weiseKeyFeldZu(key);
				}

			}
		}
	}



	/** 
	 * Ordnet die HashMap in Form eines Abalone Spielbretts mit Koordinaten an
	 * und gibt dieses als String zurück.
	 *  
	 * @return Einen String in Form eines Abalone-Spielbretts.
	 * 
	 * @since 1.1
	 */
	public String toString() {
		StringBuilder gesamtesFeld = new StringBuilder();

		// Start am Ende des Arrays, da I oben steht
		for(int i = KOORDINATENQUER.length - 1; i >= 0; i--) {
			String einzelneQuerlinie = baueEinzelneQuerlinie(i);
			gesamtesFeld.append(einzelneQuerlinie + "\n");
		}

		// Untere Koordinaten anfügen
		gesamtesFeld.append("       " + "1 2 3 4 5");
		return gesamtesFeld.toString();
	}



	/**
	 * Hilfsmethode von toString.
	 * Baut eine komplette Querzeile des Spielbretts mit passender Einrückung,
	 * Feldern und Koordinaten und gibt diese als String zurück.
	 * 
	 * @param posKoordinateQuer Array-Position der gewünschten Querzeilenkoordinate.
	 * 
	 * @return komplette Querzeile als String.
	 * 
	 * @since 1.1
	 */
	private String baueEinzelneQuerlinie(int posKoordinateQuer) {
		StringBuilder einzelneQuerlinie = new StringBuilder();

		einzelneQuerlinie.append(KOORDINATENQUER[posKoordinateQuer] + " ");

		String felder = fuegeFelderZusammen(posKoordinateQuer);

		// Teilung durch 2, weil rechts von Feldern auch Abstand ist.
		int einrückungLänge = MAXDIAGONALE - felder.length()/2; 
		String einrückung = "    ".substring(0, einrückungLänge);

		einzelneQuerlinie.append(einrückung);
		einzelneQuerlinie.append(felder);

		//Koordinaten an rechter, unterer Seite anfügen
		if(posKoordinateQuer < 4) {
			int arrPos = MAXDIAGONALE - 4 + posKoordinateQuer;
			einzelneQuerlinie.append(KOORDINATENDIAGONAL[arrPos]);
		}
		return einzelneQuerlinie.toString(); 
	}



	/**
	 * Hilfsmethode von toString.
	 * Fügt alle Symbole der Felder einer Zeile mit Zwischenabständen zu einem
	 * String zusammen. Zurückgegebner String ist immer gerade.
	 * 
	 * @param posKoordinateQuer Array-Position der gewünschten Querzeilenkoordinate.
	 * 
	 * @return String aus Feldsymbolen, Zwischenabständen und einem Leerzeichen
	 * 
	 * @since 1.1
	 */
	private String fuegeFelderZusammen(int posKoordinateQuer) {

		StringBuilder felder = new StringBuilder();

		// Fügt Felder von RECHTS nach links zusammen
		for(int j = KOORDINATENDIAGONAL.length - 1; j >= 0; j--) {
			String key = KOORDINATENQUER[posKoordinateQuer] + 
					KOORDINATENDIAGONAL[j];

			if(brett.containsKey(key)) {
				felder.append(" " + brett.get(key).getFeldSymbol());
			}
		}
		felder.reverse(); // jetzt sind Felder von links nach rechts angeordnet

		return felder.toString(); 
	}



	/**
	 * Weist dem übergebenem Key ein Spielfeld zu.
	 * @param key Feldbezeichnung in Form eines Strings.
	 * 
	 * @since 1.0
	 */
	private void weiseKeyFeldZu(String key) {
		Spielfeld feld = new Spielfeld(this, key);
		brett.put(key, feld);
	}
	
	/**
	 * Stellt die Abalone Standard-Startposition auf.
	 * 
	 * @since 1.2;
	 */
	public void stelleStartpositionAuf() {
		stelleGrundlinienAuf();
		stelleVordereSteineAuf();
	}

	
	
	/**
	 * Stellt auf jedem Spielfeld in Querzeile A, B, H, I
	 * eine Figur auf. 
	 * 
	 * @since 1.2;
	 */
	private void stelleGrundlinienAuf() {

		for(int i = 0; i < 2; i++) {
			String buchstabeWeiss = KOORDINATENQUER[MAXDIAGONALE - 1 -i];
			String buchstabeSchwarz = KOORDINATENQUER[i];

			for(String zahl : KOORDINATENDIAGONAL) {
				String idWeiss = buchstabeWeiss + zahl;
				if(brett.containsKey(idWeiss)) {
					Spielfeld feld = brett.get(idWeiss);
					feld.setFigur(new Spielfigur(feld, FarbEnum.WEISS));
				}
				String idSchwarz = buchstabeSchwarz + zahl;

				if(brett.containsKey(idSchwarz)) {
					Spielfeld feld = brett.get(idSchwarz);
					feld.setFigur(new Spielfigur(feld, FarbEnum.SCHWARZ));
				}
			}
		}
	}
	
	
	
	/**
	 * Stellt die vorderen drei Steine (der Startposition)
	 * beider Seiten auf.
	 * 
	 * @since 1.2;
	 */
	private void stelleVordereSteineAuf() {
		for(int i = 3; i < 6; i++) {
			String idWeiss = "G" + (i+2);
			String idSchwarz = "C" + i;

			Spielfeld weiss = brett.get(idWeiss);
			weiss.setFigur(new Spielfigur(weiss, FarbEnum.WEISS));
			Spielfeld schwarz = brett.get(idSchwarz);
			schwarz.setFigur(new Spielfigur(schwarz, FarbEnum.SCHWARZ));
		}
	}

}