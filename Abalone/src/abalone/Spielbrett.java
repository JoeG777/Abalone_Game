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
import java.util.Collections;

import java.util.HashMap;
import java.util.Objects;

public class Spielbrett {

	private static final String[] KOORDINATENQUER = {"A", "B", "C", "D", "E", "F", 
			"G", "H", "I"};
	private static final String[] KOORDINATENDIAGONAL = {"1", "2", "3", "4", "5", 
			"6", "7", "8", "9"} ;
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

		for(Spielfeld feld : brett.values()) {
			feld.setzeNachbarn();
		}
		this.stelleStartpositionAuf();
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
	 * @param HashMap Eine HashMap<String, Spielfeld>, die das Brett modelliert
	 * 
	 * @since 1.0
	 */
	public void setBrett(HashMap<String, Spielfeld> brett) {
		this.brett = brett;
	}

	public Spielfeld getFeld(String key) {
		return brett.get(key);
	}

	//	public Boolean ziehe(Spielzug zug) {
	//		Spielfeld[] felderVon = this.getFelderZuZiehen(zug);
	//		int richtung = this.bekommeRichtung(zug);
	//		if(this.isSchiebung(felderVon, richtung)) {	
	//			
	//		}
	//	}


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


	/**
	 * Diese Methode Parst einen Spielzug zu einem Char Array zur weiteren 
	 * Verarbeitung
	 * @param Zug Ein String von wo gezogen wird und ein String wohin gezogen wird.
	 * @return charArray ein zweidimensionales char Array, welches den Zug in chars 
	 * aufteilt
	 * @since 1.3
	 */
	public char[][] spielzugParser(String zugVon, String zugNach) {
		char[][] geparsterZug = new char[2][];
		if(zugNach.length() < 2) {
			throw new IllegalArgumentException(
					"Ungueltige laenge: " + zugNach.length()
					);
		}
		if(zugVon.length() != 4 || zugVon.length() != 2) {
			throw new IllegalArgumentException( 
					"Ungueltige zuglaenge: " + zugVon.length()
					);
		}

		//Ausgangskoordinaten anlegen)
		char[] ausgangsPunkt = new char[zugVon.length()];
		//Erste Koordinate
		char buchstabe1 = zugVon.charAt(0);
		char zahl1 = zugVon.charAt(1);
		ausgangsPunkt[0] = buchstabe1;
		ausgangsPunkt[1] = zahl1;

		if(zugVon.length() == 4) {
			//Zweite Koordinate
			char buchstabe2 = zugVon.charAt(2);
			char zahl2 = zugVon.charAt(3);
			ausgangsPunkt[2] = buchstabe2;
			ausgangsPunkt[3] = zahl2;
		}
		geparsterZug[0] = ausgangsPunkt;
		//Zielkoordinate anlegen
		char[] zielPunkt = new char[2];
		char buchstabe = zugNach.charAt(0);
		char zahl = (zugNach.charAt(1));
		zielPunkt[0] = buchstabe;
		zielPunkt[1] = zahl;
		geparsterZug[1] = zielPunkt;
		return geparsterZug;

	}

	/**
	 * Checkt, in welche Richtung ein Zug geht.
	 * 0 = Links
	 * 1 = Oben Links
	 * 2 = Unten Links
	 * 3 = rechts
	 * 4 = Oben Rechts
	 * 5 = Unten Rechts
	 * @param Zug Objekt
	 * @return Index des Objektes, in dessen Richtung gezogen wird
	 * @since 1.3
	 */

	public int bekommeRichtung(Spielzug zug) {
		String zugVon = zug.getVon();
		String zugNach = zug.getNach();
		if(zugVon.length() == 4) {
			Spielfeld feld1 = brett.get(zugVon.substring(0,2));
			Spielfeld feld2 = brett.get(zugVon.substring(2,4));
			Spielfeld ziel = brett.get(zugNach);
			boolean flagFeld1 = feld1.hatNachbar(ziel.getId());
			boolean flagFeld2 = feld2.hatNachbar(ziel.getId());
			if(flagFeld1 && !flagFeld2) {
				return feld1.getNachbarId(ziel);
			}
			if(!flagFeld1 && flagFeld2) {
				return feld2.getNachbarId(ziel);
			}
		}	
		if(zugVon.length() == 2) {
			Spielfeld feld1 = brett.get(zugVon.substring(0,2));
			Spielfeld ziel = brett.get(zugNach);
			if(feld1.hatNachbar(ziel.getId()))
				return feld1.getNachbarId(ziel);
		}
		return -1;
	}


	/**
	 * Bewegt eine Figur von einem Feld auf ein anderes, 
	 * ohne dabei zu überprüfen, ob dies "logisch" möglich ist. 
	 * @param von das Feld auf dem sich die Figur befindet.
	 * @param auf das Feld auf das die Figur bewegt werden soll. 
	 * @since 1.4
	 */
	private void bewegeFigur(String von, String auf) {

		brett.get(auf).setFigur(brett.get(von).getFigur());;
		brett.get(von).setFigur(null);
	}
	
	private void bewegeFiguren(Spielfeld[] ausgangsfelder, Spielfeld[] zielfelder) {
		for(int i = 0; i < ausgangsfelder.length; i++) {
			if(zielfelder[i] != null) {
				String ausgangsfeld = ausgangsfelder[i].getId();
				String zielfeld = zielfelder[i].getId();
				bewegeFigur(ausgangsfeld, zielfeld);
			}
		}
	}
	
	public Spielfeld[] getFelderZuZiehen(Spielzug zug) {
		Spielfeld[] spielFelder = null;
		String zugVon = zug.getVon();
		String zugNach = zug.getNach();
		Spielfeld feld1 = this.getFeld(zugVon.substring(0, 2));
		int richtung = this.bekommeRichtung(zug);
		if (zugVon.length() == 2) {
			spielFelder = new Spielfeld[1];
			spielFelder[0] = feld1;
			return spielFelder;
		} else {
			if (feld1.hatNachbar(zugVon.substring(2, 4))) {
				spielFelder = new Spielfeld[2];
				spielFelder[0] = feld1;
				spielFelder[1] = this.getFeld(zugVon.substring(2, 4));
				return spielFelder;
			}
			Spielfeld feld3 = this.getFeld(zugVon.substring(2, 4));
			if(richtung >= 3 && richtung <= 5 ) {
				Spielfeld[] nachbarn = feld1.getNachbarn();
				if (nachbarn[richtung].hatNachbar(feld3.getId())) {
					Spielfeld moeglicherNachbar = this.getFeld(nachbarn[richtung].getId());
					if(moeglicherNachbar != null && 
							feld1.hatNachbar(moeglicherNachbar.getId()) && 
							feld3.hatNachbar(moeglicherNachbar.getId())) {
						spielFelder = new Spielfeld[3];
						spielFelder[0] = feld1;
						spielFelder[1] = this.getFeld(moeglicherNachbar.getId());
						spielFelder[2] = feld3;
						return spielFelder;

					}
				}
			}
			Spielfeld[] nachbarn = feld3.getNachbarn();
			Spielfeld moeglicherNachbar = this.getFeld(nachbarn[richtung].getId());
			if(richtung < 3 && richtung >= 0 ) {
				if (nachbarn[richtung].hatNachbar(feld1.getId())) {
					if(moeglicherNachbar != null && 
							feld1.hatNachbar(moeglicherNachbar.getId()) && 
							feld3.hatNachbar(moeglicherNachbar.getId())) {
						spielFelder = new Spielfeld[3];
						spielFelder[0] = feld1;
						spielFelder[1] = this.getFeld(moeglicherNachbar.getId());
						spielFelder[2] = feld3;
						return spielFelder;

					}
				}
			}
		}
		return spielFelder;
	}
	/**
	 * Prüft, ob in einem Zug andere Kugeln verschoben werden können
	 * @param Die Felder, von denen gezogen wird 
	 * @param Die Richtung, in die gezogen wird
	 * @return True oder False, abhängig davon, ob eine andere Kugel verschoben 
	 * werden kann
	 */

	private boolean kannSchieben(Spielfeld[] felder, int richtung) {
		Spielfeld feld = felder[0];
		if(richtung >=0 && richtung < 3) {
			Spielfeld nachbar1 = feld.getNachbar(richtung);
			if(nachbar1 == null) {
				return false;
			}
			if(felder.length == 1) {
				if(nachbar1.getFigur() == null)
					return true;
			}
			if(felder.length == 2) {
				if(nachbar1.getFigur() == null)
					return true;
				Spielfeld nachbar2 = nachbar1.getNachbar(richtung);
				if(nachbar2 == null &&
						nachbar1.getFigur().getFarbe() == feld.getFigur().getFarbe())
					return true;
				if(nachbar2.getFigur() == null &&
						nachbar1.getFigur().getFarbe() == feld.getFigur().getFarbe())
					return true;
				return false;
			}  
			if(felder.length == 3) {
				Spielfeld nachbar2 = nachbar1.getNachbar(richtung);
				Spielfeld nachbar3 = nachbar2.getNachbar(richtung);
				if(nachbar1.getFigur() == null)
					return true;
				if(nachbar2 == null && 
						nachbar1.getFigur().getFarbe() == feld.getFigur().getFarbe())
					return true;
				if(nachbar2.getFigur() == null && 
						nachbar1.getFigur().getFarbe() == feld.getFigur().getFarbe())
					return true;
				if(nachbar1.getFigur().getFarbe() == feld.getFigur().getFarbe() && 
						nachbar1.getFigur().getFarbe() == feld.getFigur().getFarbe() && 
						nachbar3==null)
					return true;
				if(nachbar1.getFigur().getFarbe() == feld.getFigur().getFarbe() && 
						nachbar1.getFigur().getFarbe() == feld.getFigur().getFarbe() && 
						nachbar3.getFigur()==null)
					return true;
				return false;
			}
		}
		if(richtung >=3 && richtung <= 5) {
			feld = felder[felder.length-1];
			Spielfeld nachbar1 = felder[1];
			if(nachbar1 == null) {
				return false;
			}
			if(felder.length == 1) {
				if(nachbar1.getFigur() == null)
					return true;
			}
			if(felder.length == 2) {
				if(nachbar1.getFigur() == null)
					return true;
				Spielfeld nachbar2 = nachbar1.getNachbar(richtung);
				if(nachbar2 == null && 
						nachbar1.getFigur().getFarbe() == feld.getFigur().getFarbe())
					return true;
				if(nachbar2.getFigur() == null && 
						nachbar1.getFigur().getFarbe() == feld.getFigur().getFarbe())
					return true;
				return false;
			}  
			if(felder.length == 3) {
				Spielfeld nachbar2 = felder[1];
				Spielfeld nachbar3 = felder[2];
				if(nachbar1.getFigur() == null)
					return true;
				if(nachbar2 == null && 
						nachbar1.getFigur().getFarbe() == feld.getFigur().getFarbe())
					return true;
				if(nachbar2.getFigur() == null && 
						nachbar1.getFigur().getFarbe() == feld.getFigur().getFarbe())
					return true;
				if(nachbar1.getFigur().getFarbe() == feld.getFigur().getFarbe() && 
						nachbar1.getFigur().getFarbe() == feld.getFigur().getFarbe() && 
						nachbar3==null)
					return true;
				if(nachbar1.getFigur().getFarbe() == feld.getFigur().getFarbe() && 
						nachbar1.getFigur().getFarbe() == feld.getFigur().getFarbe() && 
						nachbar3.getFigur()==null)
					return true;
				return false;
			}
		}
		return false;
	}

	/**
	 * Prueft, ob es sich bei einem regulären Zug um einen Zug handelt, 
	 * bei dem eigene Steine in einer Linie geschoben werden. 
	 * @param felder Die Ausgangsfelder eines Spielzuges
	 * @param richtung Die Richtung der Bewegung (Position im Array).
	 * @return true, wenn es sich um Zug handelt, bei dem eigene Steine
	 * geschoben werden. false, wenn es sich nicht, um einen solchen Zug 
	 * handelt.
	 */

	private boolean isSchiebung(Spielfeld[] felder, int richtung) {
		if(felder.length == 3) {
			if(felder[1] == felder[0].getNachbar(richtung) ||
					felder[1] == felder[2].getNachbar(richtung)) {
				return true;
			}
		}
		if(felder.length == 2) {
			if(felder[0] == felder[1].getNachbar(richtung) ||
					felder[1] == felder[0].getNachbar(richtung)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gibt für einen Zug aus 2 oder 3 Steinen, bei dem eigene Steine geschoben 
	 * werden, die Position des vordersten Steines in Richtung des Zuges zurück.
	 * @param felder Felder auf denen sich die zu ziehenden Steine befinden.
	 * @param richtung Die Richtung des Zuges.
	 * @return Spielfeld-Objekt auf dem sich der vorderste Stein befindet
	 */
	public Spielfeld getVorderstenStein(Spielfeld[] felder, int richtung) {
		Spielfeld posVordersterStein = null;

		if(felder.length == 3) {
			if(felder[0].getNachbar(richtung) != felder[1]) {
				posVordersterStein = felder[0];
			}
			else {
				posVordersterStein = felder[2];
			}
		}
		else if(felder.length == 2) {
			if(felder[0].getNachbar(richtung) != felder[1]) {
				posVordersterStein = felder[0];
			}
			else {
				posVordersterStein = felder[1];
			}
		}

		return posVordersterStein;

	}
	
	/**
	 * Gibt alle Spielfelder eines Zuges als Spielfeld-Array zurück.
	 * @param ausgangsfelder Die Felder, von denen gezogen wird.
	 * @param richtung Die Richtung des Zuges.
	 * @return Spielfeld-Array mit allen Zielfeldern des Zuges.
	 */
	private Spielfeld[] getZielfelder(Spielfeld[] ausgangsfelder, 
			int richtung) {
		Spielfeld[] zielfelder = new Spielfeld[ausgangsfelder.length];
		
		for(int i = 0; i < ausgangsfelder.length; i++) {
			zielfelder[i] = ausgangsfelder[i].getNachbar(richtung);
		}
		
		return zielfelder;
		
	}
	
	/**
	 * Gibt alle Ausgangsfelder eines Zuges zurück-
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
			
			if(feldLinks.hatNachbar(feldRechts)) {
				ausgangsfelder.add(feldLinks);
				ausgangsfelder.add(feldRechts);
			}
			
			else {
				for(int i = 0; i < 6; i++) {
					if(feldLinks.getNachbar(i) != null) {
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
	 * Prüft, ob ein Zwei-zu-eins Sumito möglich ist.
	 * @param vordersterStein der vorderste Stein eines Spielzuges in dem 
	 * zwei bis drei Steine bewegt werden.
	 * @param richtung die Richtung des Spielzuges.
	 * @return true, wenn möglich, false, wenn nicht möglich.
	 */
	private boolean isZuEinsSumito(Spielfeld vordersterStein, int richtung) {
		Spielfeld nachbarInRichtung = vordersterStein.getNachbar(richtung);
		

		if(nachbarInRichtung != null && nachbarInRichtung.istBesetzt() && nachbarInRichtung.getFigur().getFarbe() != vordersterStein.getFigur().getFarbe()) {
			Spielfeld nachbarHinterNachbar = nachbarInRichtung.getNachbar(richtung);
			if(nachbarHinterNachbar == null) {
				System.out.println("hierhin");
				return true;
			}
			if(nachbarHinterNachbar.getFigur() == null) {
				System.out.println("dahin");
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Prüft, ob ein Zwei-zu-drei Sumito möglich ist.
	 * @param vordersterStein der vorderste Stein eines Spielzuges in dem 
	 * drei Steine bewegt werden.
	 * @param richtung die Richtung des Spielzuges.
	 * @return true, wenn möglich, false, wenn nicht möglich.
	 */
	private boolean isZuZweiSumito(Spielfeld vordersterStein, int richtung) {
		Spielfeld erstesFeldInRichtung = vordersterStein.getNachbar(richtung);
		Spielfeld zweitesFeldInRichtung = erstesFeldInRichtung.getNachbar(richtung);
		Spielfeld drittesFeldInRichtung = zweitesFeldInRichtung.getNachbar(richtung);
		
		if(erstesFeldInRichtung != null && erstesFeldInRichtung.istBesetzt() && erstesFeldInRichtung.getFigur().getFarbe() != vordersterStein.getFigur().getFarbe() &&
			zweitesFeldInRichtung.getFigur().getFarbe() != vordersterStein.getFigur().getFarbe()) {
				
			if(drittesFeldInRichtung == null) {
				System.out.println("Abraeumer");
				return true;
			}
			if(drittesFeldInRichtung.getFigur() == null) {
				return true;
			}
			}
		return false;
	}
	
	/** 
	 * Prueft, ob ein gegnerischer Stein durch Ausfuehrung des Zuges
	 * vom Spielbrett geworfen wird. Falls dies zutrifft, wird die Figur vom
	 * Spielfeld entfernt.
	 * @param gegnerStein das Feld, auf dem der Stein ist.
	 * @param richtung die Richtung des Spielzuges
	 * @return true, wenn Stein runtergeworfen wird, false, wenn nicht.
	 */
	private boolean steinAbgeraeumt(Spielfeld gegnerStein, int richtung) {
		if(gegnerStein.getNachbar(richtung) == null) {
			gegnerStein.setFigur(null);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Ordnet einen Array aus Spielfeldern so, dass der vorderste Stein in 
	 * die Richtung des Spielzuges an erster Stelle und der letzte Stein 
	 * an letzter Stelle steht.
	 * @param felder ein Spielfeld-Array.
	 * @param richtung die Richtung des Spielzuges.
	 * @return ein geordnetes Spielfeld-Array.
	 */
	public Spielfeld[] ordneInRichtung(Spielfeld[] felder, int richtung) {
		ArrayList<Spielfeld> geordneteFelder = new ArrayList<Spielfeld>();
		
		Spielfeld hintersterStein = getHinterstenStein(felder, richtung);
		System.out.println(hintersterStein.getId());
		geordneteFelder.add(hintersterStein);
		
		for(int i = 1; i < felder.length; i++) { // Baut vom hintersten Stein bis zum vordersten Stein auf
			geordneteFelder.add(geordneteFelder.get(i-1).getNachbar(richtung)); 	
		}
		
		
		Collections.reverse(geordneteFelder); // Dreht um, sodass erster Stein vorne steht
		return geordneteFelder.toArray(new Spielfeld[0]);
	}
	
	/**
	 * Gibt für einen Zug aus 2 oder 3 Steinen, bei dem eigene Steine geschoben 
	 * werden, die Position des hintersten Steines in Richtung des Zuges zurück.
	 * @param felder ein Spielfeld-Array
	 * @param richtung die Richtung eines Spielzuges
	 * @return der hinterste Stein des Spielzuges.
	 */
	private Spielfeld getHinterstenStein(Spielfeld[] felder, int richtung) {
			if(felder.length == 3) {
				if(felder[0].getNachbar(richtung) == felder[1]) {
					return felder[0];
				}
				else if(felder[2].getNachbar(richtung) == felder[1]) {
					return felder[2];
				}
			}
			
			if(felder.length == 2) {
				if(felder[0].getNachbar(richtung) == felder[1]) {
					return felder[0];
				}
				else if(felder[1].getNachbar(richtung) == felder[0]) {
					return felder[1];
				}
			}
			return null;
	}
	
	private void fuehreZugAus(Spielfeld[] ausgangsfelder, int richtung) {
		
		ausgangsfelder = ordneInRichtung(ausgangsfelder, richtung);
		Spielfeld[] zielfelder = getZielfelder(ausgangsfelder, richtung);
		bewegeFiguren(ausgangsfelder, zielfelder);
	}
			
	
	/**
	 * Nimmt syntaktisch-korrekte Spielzuege an und fuehrt diese aus. 
	 * @param zuege ein Array aus Spielzuegen.
	 * @return true, wenn Zug erfolgreich, false, wenn nicht.
	 */
	public boolean ziehe(Spielzug[] zuege) {
		boolean erfolgreich = false;

		for(Spielzug zug : zuege) {
			Spielfeld[] ausgangsfelder = getAusgangsfelder(zug);
			int richtung = bekommeRichtung(zug);
			for(int i = 0; i < ausgangsfelder.length; i++) {
				if(ausgangsfelder[i].getFigur() == null || zug.getFarbe() != ausgangsfelder[i].getFigur().getFarbe())
					return false;
			}
			Spielfeld[] zielfelder = getZielfelder(ausgangsfelder,richtung);
			if(ausgangsfelder.length == 1) { // Ein Stein darf nicht schieben, also nur ueberpruefen, ob Zielfeld belegt ist
				if(ausgangsfelder[0].getNachbar(richtung).getFigur() == null) {
					bewegeFiguren(ausgangsfelder, zielfelder);
					erfolgreich = true;
				}
			}

			else if(isSchiebung(ausgangsfelder, richtung)) { //Schiebende Zuege sind anders zu behandeln als diagonale

				if(ausgangsfelder.length == 2) {
					Spielfeld vordersterStein = getVorderstenStein(ausgangsfelder, richtung);

					if(vordersterStein.getNachbar(richtung).getFigur() != null) { 
						if(isZuEinsSumito(vordersterStein, richtung)) {
							Spielfeld gegnerStein = vordersterStein.getNachbar(richtung);
							if(steinAbgeraeumt(gegnerStein, richtung)) { 
								fuehreZugAus(ausgangsfelder, richtung);
								erfolgreich = true; 
							}
							else {
								bewegeFigur(gegnerStein.getId(), gegnerStein.getNachbar(richtung).getId());
								fuehreZugAus(ausgangsfelder, richtung);
								erfolgreich = true; 
							}
						}
					}
					else {
						fuehreZugAus(ausgangsfelder, richtung);
						erfolgreich = true; 
					}
				}
				else if(ausgangsfelder.length == 3) {
					Spielfeld vordersterStein = getVorderstenStein(ausgangsfelder, richtung);
					if(vordersterStein.getNachbar(richtung).getFigur() != null) {
						if(isZuEinsSumito(vordersterStein, richtung)) {
							Spielfeld gegnerStein = vordersterStein.getNachbar(richtung);

							if(steinAbgeraeumt(gegnerStein, richtung)) {
								fuehreZugAus(ausgangsfelder, richtung);
								erfolgreich = true; 
							}

							else {
								bewegeFigur(gegnerStein.getId(), gegnerStein.getNachbar(richtung).getId());
								fuehreZugAus(ausgangsfelder, richtung);
								erfolgreich = true; 
							}
						}
						else if(isZuZweiSumito(vordersterStein, richtung)) {
							Spielfeld vordererGegnerStein = vordersterStein.getNachbar(richtung);
							Spielfeld hintererGegnerStein = vordererGegnerStein.getNachbar(richtung);

							if(steinAbgeraeumt(hintererGegnerStein, richtung)) {
								bewegeFigur(vordererGegnerStein.getId(), vordererGegnerStein.getNachbar(richtung).getId());
								fuehreZugAus(ausgangsfelder, richtung);
								erfolgreich = true; 
							}

							else {
								bewegeFigur(hintererGegnerStein.getId(), hintererGegnerStein.getNachbar(richtung).getId());
								bewegeFigur(vordererGegnerStein.getId(), hintererGegnerStein.getId());
								fuehreZugAus(ausgangsfelder, richtung);
								erfolgreich = true; 
							}
						}
					}
					else {
						fuehreZugAus(ausgangsfelder, richtung);
						erfolgreich = true; 
					}

				}
			}
			else if(!isSchiebung(ausgangsfelder,richtung)) {
				for(Spielfeld feld : zielfelder) {
					if(feld != null && feld.getFigur() != null) {
						return false;
					}
				}
				bewegeFiguren(ausgangsfelder, zielfelder);
				erfolgreich = true;
			}



		}

		return erfolgreich;
	}
	
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
	
	private ArrayList<Spielfeld> checkNachbarn(ArrayList<Spielfeld> felder){
		ArrayList<Spielfeld> gefilterteFelder = new ArrayList<Spielfeld>();
		for(Spielfeld feld : felder) {
			boolean flag = false;
			Spielfeld[] nachbarn = feld.getNachbarn();
			for(Spielfeld nachbar : nachbarn) {
				if(nachbar != null && 
				   (nachbar.getFigur() == null ||
				    nachbar.getFigur().getFarbe() != feld.getFigur().getFarbe()
				  )) 
				{
					flag = true;
				}
			}
			if(flag) {
			   gefilterteFelder.add(feld);
			}
			
		}
		return gefilterteFelder;
	}
	
	private ArrayList<Spielfeld> getNachbarnFuerZuege(Spielfeld feld) {
		ArrayList<Spielfeld> felderFuerZug = new ArrayList<Spielfeld>();
		for(int i = 0; i < 6; i++) {
			Spielfeld nachbar = feld.getNachbar(i);
			if(nachbar!= null && feld.gleichBelegt(nachbar)) {
				felderFuerZug.add(feld);
			    felderFuerZug.add(nachbar);
			}
			if(nachbar!= null) {
			Spielfeld nachbar2 = nachbar.getNachbar(i);
			if(nachbar!= null && feld.gleichBelegt(nachbar)) 
			    felderFuerZug.add(nachbar2);
			}
			}
		return felderFuerZug;
	}
	
	private ArrayList<Spielzug> gesamtMoeglichkeiten(ArrayList<Spielfeld> felder){
		ArrayList<Spielzug> gesamt = new ArrayList<Spielzug>();
		ArrayList<Spielzug> zuegeAllein = this.moeglichkeitenAlleineZiehen(felder);
		gesamt.addAll(zuegeAllein);
		for(Spielfeld feld : felder) {
			ArrayList<Spielzug> zuegeZusammen = this.moeglicheZuege(this.moeglichkeitenZusammenZiehen(this.getNachbarnFuerZuege(feld)));
			gesamt.addAll(zuegeZusammen);
		}
		return gesamt;
	}
	
	private ArrayList<Spielzug> moeglichkeitenAlleineZiehen(ArrayList<Spielfeld> felder){
		ArrayList<Spielzug> zuege = new ArrayList<Spielzug>();
		for(Spielfeld feld : felder) {
			Spielfeld[]nachbarn = feld.getNachbarn();
			for(int i = 0; i < nachbarn.length; i++) {
				if(nachbarn[i] != null && nachbarn[i].getFigur() == null) {
					Spielzug zug = new Spielzug(feld.getId(), nachbarn[i].getId(), i, feld.getFarbe());
				}
			}
		}
		return zuege;
	}
	
	
	private ArrayList<Spielfeld[]> moeglichkeitenZusammenZiehen(ArrayList<Spielfeld> felder) {
		
		ArrayList<Spielfeld[]> zuegeZusammen = new ArrayList<Spielfeld[]>();
		for(Spielfeld feld: felder) {
			Spielfeld feld1 = feld;
			Spielfeld feld2 = null;
			Spielfeld feld3 = null;
			if(felder.size() > 1) {
				feld2 = felder.get(1);
			}
			if(felder.size() > 2) {
				feld3 = felder.get(2);
			}
			if(feld2 != null && feld1.istBesetzt() && feld1.gleichBelegt(feld2)) {
				Spielfeld[] spielFelder = {feld1, feld2};
				zuegeZusammen.add(spielFelder);
				if(feld3 != null && feld1.gleichBelegt(feld3)) {
					spielFelder[0] = feld1;
					spielFelder[1] = feld3;
					zuegeZusammen.add(spielFelder);
				}
			}
		}
		return zuegeZusammen;
		
		
	}
	
	private ArrayList<Spielzug> moeglicheZuege(ArrayList<Spielfeld[]> felderListe){
		ArrayList<Spielzug> zuege = new ArrayList<Spielzug>();
		for(Spielfeld[] feldArr: felderListe) {
			Spielfeld feld1 = feldArr[0];
			Spielfeld feld2 = feldArr[1];
			int richtung;
			int anzahlFelder;
			if(!feld1.hatNachbar(feld2)) {
				richtung = feld1.sucheInNachbar(feld2);
				anzahlFelder = 3;
			}else { 
				richtung = feld1.getNachbarId(feld2);
				anzahlFelder = 2;
			}
			if(richtung != -1) {
				Spielfeld zielFeld = feld2.getNachbar(richtung);
				boolean kannZiehenFlag = false;
				if(zielFeld != null && zielFeld.getFigur() == null) {
					kannZiehenFlag = true;
				}
				if(zielFeld != null) {
				Spielfeld zielNachbar1 = zielFeld.getNachbar(richtung);
				if(zielFeld != null && !zielFeld.gleichBelegt(feld2) && anzahlFelder == 3) {
					kannZiehenFlag = this.isZuEinsSumito(feld2, richtung) ||
									 this.isZuZweiSumito(feld2, richtung);
				}
				if(zielFeld != null && !zielFeld.gleichBelegt(feld2) && anzahlFelder == 2) {
					kannZiehenFlag = this.isZuEinsSumito(feld2, richtung);
				}
				if(kannZiehenFlag) {
					Spielzug zug = new Spielzug(feld1.getId()+feld2.getId(), zielFeld.getId(), richtung, feld1.getFarbe());
					zuege.add(zug);
				}
				}
			}
				
			}
			return zuege;
		}
	
	public Spielzug[] baueArrayMitMoeglZuegen(FarbEnum farbe) {
		ArrayList<Spielfeld> felderMitFarbe = this.getFelderMitFarbe(farbe);
		ArrayList<Spielzug> moeglicheZuege = this.gesamtMoeglichkeiten(felderMitFarbe);
		Spielzug[] zuege = new Spielzug[moeglicheZuege.size()];
		for(int i = 0; i < zuege.length; i++) {
			zuege[i] = moeglicheZuege.get(i);
		}
		return zuege;
	}
	
	private int umgekehrteRichtung(int richtung) {
		switch(richtung) {
			case 0: return 3;
			case 1: return 5;
			case 2: return 4;
			case 3: return 0;
			case 4: return 2;
			case 5: return 1;
			default: return 6;
		}
	}
		
		
}

