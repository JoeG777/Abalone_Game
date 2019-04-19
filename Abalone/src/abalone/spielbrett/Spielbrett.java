
package abalone.spielbrett;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Objects;

import abalone.FarbEnum;
import abalone.Spielzug;

/**
 * <h1>Spielbrett</h1>
 * Die Klasse Spielbrett erschafft ein Abalone Spielbrett 
 * auf Basis einer Hash Map.
 * Sie bietet Methoden, um auf verschiedene Spielfelder zuzugreifen oder
 * Figuren zu bewegen.
 * @author Gruppe A4
 * @version 1.4  
 */

public class Spielbrett implements java.io.Serializable, Cloneable {

	private static final long serialVersionUID = 107L;
	public static final String[] KOORDINATENQUER = {"A", "B", "C", "D", "E", 
			"F", "G", "H", "I"};
	public static final String[] KOORDINATENDIAGONAL = {"1", "2", "3", "4", 
			"5", "6", "7", "8", "9"} ;
	private final int MAXDIAGONALE = 9;
	private final int MITTE = 5;

	private HashMap<String, Spielfeld> brett;


	/**
	 * Konstruktor fuer Abalone Spielbrett
	 * @throws SpielfeldException 
	 */
	public Spielbrett() throws SpielfeldException {
		setBrett(new HashMap<String, Spielfeld>());
		schaffeMapping(); 

		for(Spielfeld feld : brett.values()) {
			feld.setzeNachbarn(this);
		}
		this.stelleStartpositionAuf();
	}
	
	private Spielbrett(HashMap<String, Spielfeld> map) {
		this.setBrett(map);
	}
	/**
	 * Verknuepft alle Feldbezeichnungen
	 * eines Abalone-Bretts mit Spielfeld-Objekten.
	 * @throws SpielfeldException 
	 * 
	 */
	private void schaffeMapping() throws SpielfeldException {
		for(int i = 0; i < KOORDINATENQUER.length; i++) {
			// Untere Felder schaffen und verknuepfen
			if(i < MITTE) {
				//verknuepft von links nach rechts
				for(int j = 0; j < MITTE + i; j++) {
					String key = KOORDINATENQUER[i] + KOORDINATENDIAGONAL[j];
					weiseKeyFeldZu(key);
				}
			}
			// Obere Felder schaffen und verknuepfen 
			else {
				//verknuepft von rechts nach links
				for(int j = KOORDINATENQUER.length-1; j > i-MITTE; j--) {
					String key = KOORDINATENQUER[i] + KOORDINATENDIAGONAL[j];
					weiseKeyFeldZu(key);
				}

			}
		}
	}

	/**
	 * Weist dem uebergebenem Key ein Spielfeld zu.
	 * @param key Feldbezeichnung in Form eines Strings.
	 * @throws SpielfeldException 
	 * 
	 */
	private void weiseKeyFeldZu(String key) throws SpielfeldException {
		Spielfeld feld = new Spielfeld(key);
		brett.put(key, feld);
	}


	/**
	 * Stellt die Abalone Standard-Startposition auf.
	 * @throws SpielfeldException 
	 * 
	 */
	private void stelleStartpositionAuf() throws SpielfeldException {
		stelleGrundlinienAuf();
		stelleVordereSteineAuf();
	}



	/**
	 * Stellt auf jedem Spielfeld in Querzeile A, B, H, I
	 * eine Figur auf. 
	 * @throws SpielfeldException 
	 * 
	 */
	private void stelleGrundlinienAuf() throws SpielfeldException {

		for(int i = 0; i < 2; i++) {
			String buchstabeWeiss = KOORDINATENQUER[MAXDIAGONALE - 1 -i];
			String buchstabeSchwarz = KOORDINATENQUER[i];

			for(String zahl : KOORDINATENDIAGONAL) {
				String idWeiss = buchstabeWeiss + zahl;
				if(brett.containsKey(idWeiss)) {
					Spielfeld feld = brett.get(idWeiss);
					feld.setAndInitFigur("WEISS");
				}
				String idSchwarz = buchstabeSchwarz + zahl;

				if(brett.containsKey(idSchwarz)) {
					Spielfeld feld = brett.get(idSchwarz);
					feld.setAndInitFigur("SCHWARZ");
				}
			}
		}
	}



	/**
	 * Stellt die vorderen drei Steine (der Startposition)
	 * beider Seiten auf.
	 * @throws SpielfeldException 
	 * 
	 */
	private void stelleVordereSteineAuf() throws SpielfeldException {
		for(int i = 3; i < 6; i++) {
			String idWeiss = "G" + (i+2);
			String idSchwarz = "C" + i;

			Spielfeld weiss = brett.get(idWeiss);
			weiss.setAndInitFigur("WEISS");
			Spielfeld schwarz = brett.get(idSchwarz);
			schwarz.setAndInitFigur("SCHWARZ");
		}
	}


	/**
	 * Gibt das Brettattribut des Spielbretts zurueck.
	 * @return Brettattribut in Form von einer
	 * HashMap(String,Spielfeld)  des Spielbretts.
	 * 
	 */
	HashMap<String, Spielfeld> getBrett() {
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
	 * symbolische Methode, die zu einem uebergebenen Id die id zurueck gibt
	 * @param key ein String, der zu einem Feld gehoert.
	 * @return zum Key passendes Spielfeld-Objekt.
	 */
	public String getFeld(String key) {
		return brett.get(key).getId();
	}

	/**
	 * Zur internen Verwaltung von Spielfeldern
	 * @param id
	 * @return
	 */
	private Spielfeld getFeldById(String id) {
		return brett.get(id);
	}



	/** 
	 * Ordnet die HashMap in Form eines Abalone Spielbretts mit Koordinaten an.
	 * @return Einen String in Form eines Abalone-Spielbretts.
	 * 
	 */

	@Override
	public String toString() {
		StringBuilder gesamtesFeld = new StringBuilder();
		gesamtesFeld.append("                    \n");
		for(int i = KOORDINATENQUER.length - 1; i >= 0; i--) {
			String einzelneQuerlinie = baueEinzelneQuerlinie(i);
			gesamtesFeld.append(einzelneQuerlinie + "\n");
		}

		// Untere Koordinaten anfuegen
		gesamtesFeld.append("                  6\n");
		gesamtesFeld.append("         " + "1 2 3 4 5 ");
		gesamtesFeld.append("\n");
		return gesamtesFeld.toString();
		} 
		
	@Override
	public Spielbrett clone() {
		HashMap<String, Spielfeld> klon = new HashMap<String, Spielfeld>();
		for(HashMap.Entry<String, Spielfeld> entry : this.getBrett().entrySet()) {
			klon.put(entry.getKey(), entry.getValue().clone());
		}
		
		return new Spielbrett(klon);
	}


	/**
	 * notwendige Hilfsmethode von getStatus von der Spielklasse.
	 * Baut eine komplette Querzeile des Spielbretts mit passender Einrueckung,
	 * Feldern und Koordinaten und gibt diese als String zurueck.
	 * 
	 * @param posKoordinateQuer Array-Position der gewuenschten Querzeilenkoordinate.
	 * 
	 * @return komplette Querzeile als String.
	 * 
	 */
	private String baueEinzelneQuerlinie(int posKoordinateQuer) {
		StringBuilder einzelneQuerlinie = new StringBuilder();

		einzelneQuerlinie.append(KOORDINATENQUER[posKoordinateQuer] + "  ");

		String felder = fuegeFelderZusammen(posKoordinateQuer);

		// Teilung durch 2, weil rechts von Feldern auch Abstand ist.
		int einrueckungLaenge = MAXDIAGONALE - felder.length()/2; 
		String einrueckung = "    ".substring(0, einrueckungLaenge);

		einzelneQuerlinie.append(einrueckung);
		einzelneQuerlinie.append(felder);

		//Koordinaten an rechter, unterer Seite anfuegen
		if(posKoordinateQuer < 3) {
			int arrPos = MAXDIAGONALE - 3 + posKoordinateQuer ;
			einzelneQuerlinie.append("  " + KOORDINATENDIAGONAL[arrPos]);
		}
		return einzelneQuerlinie.toString(); 
	}



	/**
	 * Hilfsmethode von toString.
	 * Fuegt alle Symbole der Felder einer Zeile mit Zwischenabstaenden zu einem
	 * String zusammen. Zurueckgegebner String ist immer gerade.
	 * 
	 * @param posKoordinateQuer Array-Position der gewuenschten Querzeilenkoordinate.
	 * 
	 * @return String aus Feldsymbolen, Zwischenabstaenden und einem Leerzeichen
	 * 
	 */
	private String fuegeFelderZusammen(int posKoordinateQuer) {

		StringBuilder felder = new StringBuilder();

		// Fuegt Felder von RECHTS nach links zusammen
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
	 * @throws SpielbrettException 
	 */

	public void ziehe(Spielzug[] zuege) throws SpielbrettException {
		for(Spielzug zug : zuege) {
			if(zug != null ) {
				if(zug.getNach() != null) {
					bewegeFigur(zug.getVon(), zug.getNach());
				}
				else {
					steinAbraeumen(this.getFeldById(getFeld(zug.getVon())));
				}
			}
		}
	}

	/**
	 * Bewegt eine Figur von einem Feld auf ein anderes, 
	 * ohne dabei zu ueberpruefen, ob dies "logisch" moeglich ist. 
	 * @param von das Feld auf dem sich die Figur befindet.
	 * @param auf das Feld auf das die Figur bewegt werden soll. 
	 * @throws SpielbrettException 
	 */
	private void bewegeFigur(String von, String auf) throws SpielbrettException {
		if(brett.get(auf) != null && brett.get(von) != null) {
			brett.get(auf).setFigur(brett.get(von).getFigur());;
			brett.get(von).setFigur(null);
		}else
			throw new SpielbrettException(1, "Ungueltiger Zug " + von + "-" + auf);
	}
	
	/**
	 * Loescht einen Stein vom Spielfeld.
	 * @param feld Spielfeld, auf dem der zu loeschende Stein ist.
	 */
	private void steinAbraeumen(Spielfeld feld) {
		feld.setFigur(null);
	}


	/**
	 * Gibt alle Ausgangsfelder eines Zuges zurueck-
	 * @param zug Ein Spielzug-Objekt.
	 * @return Spielfeld-Array mit allen Ausgangsfelder des Zuges.
	 */
	public String[] getAusgangsfelder(Spielzug zug) {
		ArrayList<String> ausgangsfelder = new ArrayList<String>();
		if(zug.getVon().length() == 2) {
			ausgangsfelder.add(brett.get(zug.getVon()).getId());
		}
		else if(zug.getVon().length() == 4) {
			Spielfeld feldLinks = brett.get(zug.getVon().substring(0, 2));
			Spielfeld feldRechts = brett.get(zug.getVon().substring(2, 4));

			if(feldLinks != null && feldLinks.hatNachbar(feldRechts)) {
				ausgangsfelder.add(feldLinks.getId());
				ausgangsfelder.add(feldRechts.getId());
			}

			else {
				for(int i = 0; i < 6; i++) {
					if(feldLinks != null && feldLinks.getNachbar(i) != null) {
						Spielfeld dazwischen = brett.get(feldLinks.getNachbar(i));
						Spielfeld ziel = brett.get(dazwischen.getNachbar(i));


						if(ziel == feldRechts) {
							ausgangsfelder.add(feldLinks.getId());
							ausgangsfelder.add(dazwischen.getId());
							ausgangsfelder.add(feldRechts.getId());
						}
					}
				}
			}
		}
		else {
			return null;
		}

		return ausgangsfelder.toArray(new String[0]); //vorher new Spielfeld[0]
	}
	

	/**
	 * Gibt alle Felder zurueck, die mit einer Figur belegt sind, 
	 * deren Farbe gleich der uebergebenen Farbe ist.
	 * @param farbe Die Farbe der gesuchten Figuren.
	 * @return ArrayList aus Spielfeldern, auf denen Figuren der uebergebenen 
	 * Farbe sind
	 */
	public ArrayList<String> getFelderMitFarbe(FarbEnum farbe) {
		ArrayList<String> felder = new ArrayList<String>();
		for(Spielfeld feld : brett.values()) {
			if(feld.getFigur() != null && feld.getFarbeDerFigur() == farbe) {
				felder.add(feld.getId());
			}
		}
		felder.removeIf(Objects::isNull);
		return felder;
	}
	
	public String writeCSV() {
		String brettCSV = "BRETT: \n";
		for(String key: brett.keySet()) {
		    Spielfeld feld = brett.get(key);
		    brettCSV += feld.writeCSVString() + "\n";
		}
		return brettCSV;
	}
	
	/**
	 * Methode zum Herausfinden der Ids der Nachbarfelder
	 * @param id
	 * @return NachbarFelderIds Nachbar Ids
	 */
	public String[] getNachbarnByIdVonFeld(String id) {
		String[] nachbarIds = this.getFeldById(id).getNachbarn();

		return nachbarIds;
	}
	
	public String getNachbarByIdInRichtung(String id, int richtung) {
		return this.getFeldById(id).getNachbar(richtung);
		//Spielbrett holt sich Feld -> Nachbar -> id -> Rueckgabe
	}
	
	/**
	 * Ermittelt, ob ein Spielfeld von einer Figur besetzt ist
	 * @return Wahrheitswert Ob besetzt oder nicht
	 */
	public boolean istBesetzt(String id) {
		if(this.getFeldById(id).getFigur() == null) {
			return false;
		}
		return true;
	}

	/**
	 * Zugriff auf die Farbe einer Figur.
	 * @param id
	 * @return FarbeDerFigurAufDemFeld
	 */
	public FarbEnum getFarbeDerFigurById(String id) {
		return this.getFeldById(id).getFarbeDerFigur();
	}
	
	/**
	 * Abfrage, ob ein Spielfeld von einer Figur der mitgegebenen Farbe ist
	 * @param id des Spielfelds
	 * @param farbe die eigene Farbe
	 * @return Wahrheitswert
	 */
	public boolean istDurchGegnerBesetztById(String id, FarbEnum farbe) {
		if(id == null) {
			return false;
		}
		return this.getFeldById(id).istDurchGegnerBesetzt(farbe);
	}
	
	/**
	 * Abfrage, ob Felder Nachbar von einander sind
	 * @param id eigenes Feld
	 * @param nachbarId Feld zu ueberpruefen
	 * @return Wahrheitswert
	 */
	public boolean hatNachbarById(String id, String nachbarId) {
		return this.getFeldById(id).hatNachbar(this.getFeldById(nachbarId));
	}
	
	/**
	 * Zugriff auf den Index eines Nachbarns im Nachbararray
	 * @param id eigenes Feld
	 * @param nachbarId zu ueberpruefendes Feld
	 * @return Index im Nachbarn-Array
	 */
	public int getNachbarIndexById(String id, String nachbarId) {
		return this.getFeldById(id).getNachbarId(this.getFeldById(nachbarId));
	}
}

