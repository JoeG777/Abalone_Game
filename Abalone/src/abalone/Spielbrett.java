/**
 * @author Gruppe A4
 * @version 1.4  
 * Die Klasse Spielbrett erschafft ein Abalone Spielbrett 
 * auf Basis einer Hash Map.
 * Sie bietet Methoden, um auf verschiedene Spielfelder zuzugreifen oder
 * Figuren zu bewegen.
 */

package abalone;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Objects;

public class Spielbrett {

	private static final String[] KOORDINATENQUER = {"A", "B", "C", "D", "E", 
			"F", "G", "H", "I"};
	private static final String[] KOORDINATENDIAGONAL = {"1", "2", "3", "4", 
			"5", "6", "7", "8", "9"} ;
	private final int MAXDIAGONALE = 9;
	private final int MITTE = 5;

	private HashMap<String, Spielfeld> brett;


	/**
	 * Konstruktor f�r Abalone Spielbrett
	 */
	public Spielbrett() {
		setBrett(new HashMap<String, Spielfeld>());
		schaffeMapping(); 

		for(Spielfeld feld : brett.values()) {
			feld.setzeNachbarn();
		}
		this.stelleStartpositionAuf();
	}

	/**
	 * Verkn�pft alle Feldbezeichnungen
	 * eines Abalone-Bretts mit Spielfeld-Objekten.
	 * 
	 */
	private void schaffeMapping() {
		for(int i = 0; i < KOORDINATENQUER.length; i++) {
			// Untere Felder schaffen und verkn�pfen
			if(i < MITTE) {
				//verkn�pft von links nach rechts
				for(int j = 0; j < MITTE + i; j++) {
					String key = KOORDINATENQUER[i] + KOORDINATENDIAGONAL[j];
					weiseKeyFeldZu(key);
				}
			}
			// Obere Felder schaffen und verkn�pfen 
			else {
				//verkn�pft von rechts nach links
				for(int j = KOORDINATENQUER.length-1; j > i-MITTE; j--) {
					String key = KOORDINATENQUER[i] + KOORDINATENDIAGONAL[j];
					weiseKeyFeldZu(key);
				}

			}
		}
	}

	/**
	 * Weist dem �bergebenem Key ein Spielfeld zu.
	 * @param key Feldbezeichnung in Form eines Strings.
	 * 
	 */
	private void weiseKeyFeldZu(String key) {
		Spielfeld feld = new Spielfeld(this, key);
		brett.put(key, feld);
	}


	/**
	 * Stellt die Abalone Standard-Startposition auf.
	 * 
	 */
	private void stelleStartpositionAuf() {
		stelleGrundlinienAuf();
		stelleVordereSteineAuf();
	}



	/**
	 * Stellt auf jedem Spielfeld in Querzeile A, B, H, I
	 * eine Figur auf. 
	 * 
	 */
	private void stelleGrundlinienAuf() {

		for(int i = 0; i < 2; i++) {
			String buchstabeWeiss = KOORDINATENQUER[MAXDIAGONALE - 1 -i];
			String buchstabeSchwarz = KOORDINATENQUER[i];

			for(String zahl : KOORDINATENDIAGONAL) {
				String idWeiss = buchstabeWeiss + zahl;
				if(brett.containsKey(idWeiss)) {
					Spielfeld feld = brett.get(idWeiss);
					feld.setFigur(new Spielfigur(feld, "WEISS"));
				}
				String idSchwarz = buchstabeSchwarz + zahl;

				if(brett.containsKey(idSchwarz)) {
					Spielfeld feld = brett.get(idSchwarz);
					feld.setFigur(new Spielfigur(feld, "SCHWARZ"));
				}
			}
		}
	}



	/**
	 * Stellt die vorderen drei Steine (der Startposition)
	 * beider Seiten auf.
	 * 
	 */
	private void stelleVordereSteineAuf() {
		for(int i = 3; i < 6; i++) {
			String idWeiss = "G" + (i+2);
			String idSchwarz = "C" + i;

			Spielfeld weiss = brett.get(idWeiss);
			weiss.setFigur(new Spielfigur(weiss, "WEISS"));
			Spielfeld schwarz = brett.get(idSchwarz);
			schwarz.setFigur(new Spielfigur(schwarz, "SCHWARZ"));
		}
	}


	/**
	 * Gibt das Brettattribut des Spielbretts zur�ck.
	 * @return Brettattribut in Form von einer
	 * HashMap<String,Spielfeld>  des Spielbretts.
	 * 
	 */
	public HashMap<String, Spielfeld> getBrett() {
		return this.brett;
	}



	/**
	 * Setzt das Brettattribut des Spielbretts.
	 * @param brett Eine HashMap<String, Spielfeld>, die das Brett modelliert
	 * 
	 */
	private void setBrett(HashMap<String, Spielfeld> brett) {
		this.brett = brett;
	}
	
	/**
	 * Gibt das zum �bergebenen Key passende Feld zurueck.
	 * @param key ein String, der zu einem Feld gehoert.
	 * @return zum Key passendes Spielfeld-Objekt.
	 */
	public Spielfeld getFeld(String key) {
		return brett.get(key);
	}




	/** 
	 * Ordnet die HashMap in Form eines Abalone Spielbretts mit Koordinaten an
	 * und gibt dieses als String zur�ck.
	 *  
	 * @return Einen String in Form eines Abalone-Spielbretts.
	 * 
	 */
	@Override
	public String toString() {
		StringBuilder gesamtesFeld = new StringBuilder();

		// Start am Ende des Arrays, da I oben steht
		for(int i = KOORDINATENQUER.length - 1; i >= 0; i--) {
			String einzelneQuerlinie = baueEinzelneQuerlinie(i);
			gesamtesFeld.append(einzelneQuerlinie + "\n");
		}

		// Untere Koordinaten anf�gen
		gesamtesFeld.append("       " + "1 2 3 4 5");
		return gesamtesFeld.toString();
	}



	/**
	 * Hilfsmethode von toString.
	 * Baut eine komplette Querzeile des Spielbretts mit passender Einr�ckung,
	 * Feldern und Koordinaten und gibt diese als String zur�ck.
	 * 
	 * @param posKoordinateQuer Array-Position der gew�nschten Querzeilenkoordinate.
	 * 
	 * @return komplette Querzeile als String.
	 * 
	 */
	private String baueEinzelneQuerlinie(int posKoordinateQuer) {
		StringBuilder einzelneQuerlinie = new StringBuilder();

		einzelneQuerlinie.append(KOORDINATENQUER[posKoordinateQuer] + " ");

		String felder = fuegeFelderZusammen(posKoordinateQuer);

		// Teilung durch 2, weil rechts von Feldern auch Abstand ist.
		int einrueckungLaenge = MAXDIAGONALE - felder.length()/2; 
		String einrueckung = "    ".substring(0, einrueckungLaenge);

		einzelneQuerlinie.append(einrueckung);
		einzelneQuerlinie.append(felder);

		//Koordinaten an rechter, unterer Seite anf�gen
		if(posKoordinateQuer < 4) {
			int arrPos = MAXDIAGONALE - 4 + posKoordinateQuer;
			einzelneQuerlinie.append(KOORDINATENDIAGONAL[arrPos]);
		}
		return einzelneQuerlinie.toString(); 
	}



	/**
	 * Hilfsmethode von toString.
	 * F�gt alle Symbole der Felder einer Zeile mit Zwischenabst�nden zu einem
	 * String zusammen. Zur�ckgegebner String ist immer gerade.
	 * 
	 * @param posKoordinateQuer Array-Position der gew�nschten Querzeilenkoordinate.
	 * 
	 * @return String aus Feldsymbolen, Zwischenabst�nden und einem Leerzeichen
	 * 
	 */
	private String fuegeFelderZusammen(int posKoordinateQuer) {

		StringBuilder felder = new StringBuilder();

		// F�gt Felder von RECHTS nach links zusammen
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
	 * Nimmt Spielzuege an und bewegt Steine vom von-Attribut des Spielzug-
	 * Objektes auf dessen nach-Attribut. Ist das nach-Attribut gleich 'null'
	 * wird der Stein vom Spielfeld entfernt (Figur Attribut von Spielfeld
	 * wird auf null gesetzt).
	 * @param zuege ein Spielzug-Array das einzelne Zuege aus einem Stein 
	 * enthaehlt.
	 */

	public void ziehe(Spielzug[] zuege) {
		for(Spielzug zug : zuege) {
			if(zug != null ) {
				if(zug.getNach() != null) {
					bewegeFigur(zug.getVon(), zug.getNach());
				}
				else {
					steinAbraeumen(getFeld(zug.getVon()));
				}
			}
		}
	}

	/**
	 * Bewegt eine Figur von einem Feld auf ein anderes, 
	 * ohne dabei zu �berpr�fen, ob dies "logisch" m�glich ist. 
	 * @param von das Feld auf dem sich die Figur befindet.
	 * @param auf das Feld auf das die Figur bewegt werden soll. 
	 */
	private void bewegeFigur(String von, String auf) {
		if(brett.get(auf) != null && brett.get(von) != null) {
			brett.get(auf).setFigur(brett.get(von).getFigur());;
			brett.get(von).setFigur(null);
		}else
			throw new IllegalArgumentException("Ungueltiger Zug");
	}

	private void steinAbraeumen(Spielfeld feld) {
		feld.setFigur(null);
	}


	/**
	 * Gibt alle Ausgangsfelder eines Zuges zur�ck-
	 * @param zug Ein Spielzug-Objekt.
	 * @return Spielfeld-Array mit allen Ausgangsfelder des Zuges.
	 */
	public Spielfeld[] getAusgangsfelder(Spielzug zug) {
		ArrayList<Spielfeld> ausgangsfelder = new ArrayList<Spielfeld>();
		if(zug.getVon().length() == 2) {
			ausgangsfelder.add(brett.get(zug.getVon()));
		}
		else if(zug.getVon().length() == 4) {
			Spielfeld feldLinks = brett.get(zug.getVon().substring(0, 2));
			Spielfeld feldRechts = brett.get(zug.getVon().substring(2, 4));

			if(feldLinks != null && feldLinks.hatNachbar(feldRechts)) {
				ausgangsfelder.add(feldLinks);
				ausgangsfelder.add(feldRechts);
			}

			else {
				for(int i = 0; i < 6; i++) {
					if(feldLinks != null && feldLinks.getNachbar(i) != null) {
						Spielfeld dazwischen = feldLinks.getNachbar(i);
						Spielfeld ziel = dazwischen.getNachbar(i);


						if(ziel == feldRechts) {
							ausgangsfelder.add(feldLinks);
							ausgangsfelder.add(dazwischen);
							ausgangsfelder.add(feldRechts);
						}
					}
				}
			}
		}
		else {
			return null;
		}

		return ausgangsfelder.toArray(new Spielfeld[0]);
	}

	/**
	 * Gibt alle Felder zurueck, die mit einer Figur belegt sind, 
	 * deren Farbe gleich der uebergebenen Farbe ist.
	 * @param farbe Die Farbe der gesuchten Figuren.
	 * @return ArrayList aus Spielfeldern, auf denen Figuren der uebergebenen 
	 * Farbe sind
	 */
	public ArrayList<Spielfeld> getFelderMitFarbe(FarbEnum farbe) {
		ArrayList<Spielfeld> felder = new ArrayList<Spielfeld>();
		for(Spielfeld feld : brett.values()) {
			if(feld.getFigur() != null && feld.getFigur().getFarbe() == farbe) {
				felder.add(feld);
			}
		}
		felder.removeIf(Objects::isNull);
		return felder;
	}
}

