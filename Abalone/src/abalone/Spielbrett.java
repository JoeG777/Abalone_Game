/**
 * @author Gruppe A4
 * @version 1.4  
 * Die Klasse Spielbrett erschafft ein Abalone Spielbrett 
 * auf Basis einer Hash Map.
 * Sie bietet Methoden, um auf verschiedene Spielfelder zuzugreifen oder
 * Figuren zu bewegen.
 */

package abalone;

import java.util.HashMap;

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
	
	public Boolean ziehe(Spielzug zug) {
		Spielfeld[] felderVon = this.getFelderZuZiehen(zug);
		int richtung = this.bekommeRichtung(zug);
		if(this.isSchiebung(felderVon, richtung)) {	
			
		}
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
	public void bewegeFigur(String von, String auf) {

		brett.get(auf).setFigur(brett.get(von).getFigur());;
		brett.get(von).setFigur(null);
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

	public boolean isSchiebung(Spielfeld[] felder, int richtung) {
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
}
