/**
 * <h1>Spiel</h1>
 * Die Klasse Spiel implementiert das zentrale Objekt für das Spiel Abalone. In
 * ihr laufen alle anderen Klassen zusammen, somit bildet sie die Schnittstelle
 * zu weiteren Objekten wie einer UI 
 * @author Gruppe A4
 * @version 1.2
 * @since 1.0
 */
package abalone;

public class Spiel {

	private Spieler spielerAmZug;
	private Spieler[] spielerImSpiel;
	private Spielbrett spielBrett;
	private Historie historie;

	/**
	 * Konstruktor, instanziiert alle Anfangs benoetigten Objekte
	 */
	public Spiel() {
		spielBrett = new Spielbrett();
		historie = new Historie();
		this.spielerImSpiel = new Spieler[2];
	}
	
	/**
	 * Gibt alle Spieler Objekte im Spiel als Array zurueck
	 * @return ein Array des Typs Spieler
	 */
	public Spieler[] getSpielerImSpiel() {
		return spielerImSpiel;
	}

	/**
	 * Diese Methode wird Benutzt, um Spieler einem Spiel hinzuzufuegen. Sie fuegt
	 * maximal 2 Spieler hinzu. Sollte ein dritter Spieler hinzugefügt werden, wirft
	 * die Methode eine IndexOutOfBounds Exception.
	 * 
	 * @param name  Der für den Spieler gewaehlte Name
	 * @param farbe Die für den Spieler gewaehlte Farbe
	 * @since 1.0
	 */
	public void addSpieler(String name, String farbe) {
		if (spielerImSpiel[0] != null && spielerImSpiel[1] != null) {
			throw new IndexOutOfBoundsException("Das Spieler Array ist bereits voll!");
		}
		if (farbe.equals("weiss") && spielerImSpiel[0] == null) {
			FarbEnum spielerFarbe = FarbEnum.WEISS;
			spielerImSpiel[0] = new Spieler(name, spielerFarbe);
			this.spielerAmZug = spielerImSpiel[0];
		} else if (farbe.equals("schwarz") && spielerImSpiel[0] != null) {
			FarbEnum spielerFarbe = FarbEnum.SCHWARZ;
			spielerImSpiel[1] = new Spieler(name, spielerFarbe);
		} else {
			throw new IllegalArgumentException("Unbekannte farbe :" + farbe);
		}

	}

	/**
	 * Diese Methode gibt den Namen des aktuellen Spielers zurueck.
	 * 
	 * @return Name des aktuellen Spielers als String.
	 */
	public String getSpielerAmZug() {
		return spielerAmZug.getName();
	}
	
	/**
	 * Git die Farbe des Spielers zurueck, der aktuell am Zug ist
	 * @return ein Objekt der Klasse FarbEnum
	 */
	private FarbEnum getFarbeAmZug() {
		return this.spielerAmZug.getFarbe();
	}


	/**
	 * Fragt ab, ob ein Spieler gewonnen hat
	 * 
	 * @param Spielername Name eines Spielers
	 * @return Boolean Ob der übergebene Spieler gewonnen hat
	 */
	public boolean hatGewonnen(String name) {
		Spieler spieler = null;
		Spieler[] spielerArr = getSpielerImSpiel();
		
		for(int i = 0; i<spielerArr.length; i++) {
			if (spielerArr[i].getName().equals(name)) {
				spieler = spielerArr[i];
			}
		}
		for(int i = 0; i<spielerArr.length; i++) {
			if(spieler != null && !spielerArr[i].equals(spieler)) {
				if(14 - this.zaehleKugelnMitFarbe(spieler.getFarbe())> 6 &&
				   14 - this.zaehleKugelnMitFarbe(spielerArr[i].getFarbe())> 6)
					return true;
			}
		}
		return false;
	}

	/**
	 * Die ziehe Methode erzeugt aus zwei Strings ein Zug Objekt und übergibt dieses
	 * dem Spielbrett.
	 * 
	 * @param zug Ein String Array mit den Werten [0] = von wo gezogen wird, [1] =
	 *            wohin gezogen wird.
	 * @since 1.0
	 */
	public void ziehe(String[] zug) {
		if (spielzugValidieren(zug)) {
			Spielzug spielzug = new Spielzug(zug[0], zug[1], 0, spielerAmZug.getFarbe());
			Spielzug[] spielzuege = new Spielzug[1];
			spielzuege[0] = spielzug;
			if(!spielBrett.ziehe(spielzuege))
				throw new IllegalArgumentException("Unzulaessiger Zug");
			historie.spielzugHinzufuegen(spielzuege[0]);
			if (spielerAmZug.getFarbe() == spielerImSpiel[0].getFarbe()) {
				spielerAmZug = spielerImSpiel[1];
			} else {
				spielerAmZug = spielerImSpiel[0];
			}
		} else {
			throw new IllegalArgumentException("Unzulaessiger Zug");
		}

	}
	/**
	 * Gibt erlaubte Zuege zurueck
	 * @return ein String Array mit den erlaubten Zuegen
	 */
	public String[] getErlaubteZuege() {
		return null;
	}
	
	/**
	 * Zaehlt die Kugeln auf dem Feld mit einer gegebenen Farbe.
	 * @param zu zeaehlende Farbe
	 * @return Anzahl der Kugeln mit dieser Farbe
	 */
	private int zaehleKugelnMitFarbe(FarbEnum farbe) {
		return spielBrett.getFelderMitFarbe(farbe).size();
	}
	
	/**
	 * Gibt die Historie zurueck
	 * @return Historie als String
	 */
	public String getHistorie() {
		return this.historie.getZuege();

	}
	/**
	 * Gibt den Status des Spiels zurueck. Dieser umfasst:
	 * Das Spielbrett
	 * Welcher Spieler am Zug ist
	 * Welche Spieler noch wie viele Steine im Spiel haben
	 * @return der Status als String
	 */
	public String getStatus() { // ??
		String amZug ="Am zug ist: " + spielerAmZug.getName() + "\n";
		String verbleibendeSteine = "Spieler " + this.spielerImSpiel[0].getName() + 
									" hat noch " + this.zaehleKugelnMitFarbe(spielerImSpiel[0].getFarbe())+ " Kugeln. \n"+
									"Spieler " + this.spielerImSpiel[1].getName() + 
									" hat noch " + this.zaehleKugelnMitFarbe(spielerImSpiel[1].getFarbe()) + " Kugeln. \n";
		
		String feld = this.spielBrett.toString() + "\n";
		return feld + verbleibendeSteine + amZug;

	}

	/**
	 * Diese Methode Parst einen Spielzug zu einem Char Array zur weiteren
	 * Verarbeitung
	 * 
	 * @param zug Ein String Array mit den Werten [0] = von wo gezogen wird, [1] =
	 *            wohin gezogen wird.
	 * @return ein zweidimensionales char Array, welches den Zug in chars aufteilt
	 * @since 1.1
	 */
	private char[][] spielzugParser(String[] zug) {
		char[][] geparsterZug = new char[2][];
		if (zug.length < 2) {
			throw new IllegalArgumentException("Ungueltige laenge: " + zug.length);
		}
		if (zug[0].length() % 2 != 0 || zug[0].length() > 4) {
			throw new IllegalArgumentException("Ungueltige zuglaenge: " + zug[0].length());
		}

		// Ausgangskoordinate(n) anlege(n)
		char[] ausgangsPunkt = new char[zug[0].length()];
		// Erste Koordinate
		char buchstabe1 = zug[0].charAt(0);
		char zahl1 = zug[0].charAt(1);
		ausgangsPunkt[0] = buchstabe1;
		ausgangsPunkt[1] = zahl1;

		if (zug[0].length() == 4) {
			// Zweite Koordinate
			char buchstabe2 = zug[0].charAt(2);
			char zahl2 = zug[0].charAt(3);
			ausgangsPunkt[2] = buchstabe2;
			ausgangsPunkt[3] = zahl2;
		}
		geparsterZug[0] = ausgangsPunkt;
		// Zielkoordinate anlegen
		char[] zielPunkt = new char[2];
		char buchstabe = zug[1].charAt(0);
		char zahl = (zug[1].charAt(1));
		zielPunkt[0] = buchstabe;
		zielPunkt[1] = zahl;
		geparsterZug[1] = zielPunkt;
		return geparsterZug;

	}

	/**
	 * Prueft ob die gegeben Koordinaten einem validen Zug entsprechen, unabhaengig
	 * von der aktuellen Feldbelegegung.
	 * 
	 * @param geparsterZug ein zweidimensionales Char Array.
	 * @return true oder false in Abhaengigkeit der Validitaet eines Zuges.
	 * @since 1.1
	 */
	private boolean koordinatenValidieren(char[][] geparsterZug) {
		// Pruefen ob die angegebenen Chars auch auf dem Spielbrett existieren
		int buchstabenKoordinaten = 0;
		int zahlenKoordinaten = 0;
		for (char[] koordinate : geparsterZug) {
			for (int i = 0; i < koordinate.length; i++) {
				if (i % 2 == 0) {
					buchstabenKoordinaten = 'I' - koordinate[i];
					if ((buchstabenKoordinaten < 8 && buchstabenKoordinaten < 0)) {
						return false;
					}
				} else {
					zahlenKoordinaten = '9' - koordinate[i];
					// Die cases Spiegeln die differenz Zwischen 'I' und dem aktuellen Buchstaben
					// wider
					// Buchstabe = I: case = 0, Buchstabe = A: case = 8
					switch (buchstabenKoordinaten) {
					case 0:
						if (zahlenKoordinaten > 5 || zahlenKoordinaten < 0) {
							return false;
						}
						break;
					case 1:
						if (zahlenKoordinaten > 6 || zahlenKoordinaten < 0) {
							return false;
						}
						break;
					case 2:
						if (zahlenKoordinaten > 7 || zahlenKoordinaten < 0) {
							return false;
						}
						break;
					case 3:
						if (zahlenKoordinaten > 8 || zahlenKoordinaten < 0) {
							return false;
						}
						break;
					case 4:
						if (zahlenKoordinaten > 9 || zahlenKoordinaten < 0) {
							return false;
						}
						break;
					case 5:
						if (zahlenKoordinaten > 9 || zahlenKoordinaten < 1) {
							return false;
						}
						break;
					case 6:
						if (zahlenKoordinaten > 9 || zahlenKoordinaten < 2) {
							return false;
						}
						break;
					case 7:
						if (zahlenKoordinaten > 9 || zahlenKoordinaten < 3) {
							return false;
						}
						break;
					case 8:
						if (zahlenKoordinaten > 9 || zahlenKoordinaten < 4) {
							return false;
						}
						break;
					}
				}
			}
		}
		return true;
	}
		

	/**
	 * Prueft, ob ein Spielzug in abhaengigkeit der Spielbrett belegung valide ist.
	 * 
	 * @param Ein Zweidimensionales String Array.
	 * @return True oder False in abhaengigkeit von der Validitaet des Spielzuges.
	 * @since 1.1
	 */
	private boolean spielzugValidieren(String[] zug) {
		return this.koordinatenValidieren(this.spielzugParser(zug));
	}
    /**Prueft, ob zu gegebenen Koordinaten Spielfelder existieren
     * 
     * @param zug als String-Array
     * @return True oder False, abhaengig von der Existenz der Koordinaten
     */
	private boolean existierenKoordinaten(String[] zug) {
		String von = zug[0];
		String nach = zug[1];
		if (von.length() != 2 || von.length() != 4)
			return false;
		if (nach.length() != 2)
			return false;
		Spielfeld feld1 = spielBrett.getFeld(von.substring(0, 1));
		Spielfeld ziel = spielBrett.getFeld(nach);
		Spielfeld feld2;
		if (von.length() == 4) {
			feld2 = spielBrett.getFeld(von.substring(2, 3));
			if (ziel == null || feld1 == null || feld2 == null)
				return false;
		} else if (ziel == null || feld1 == null)
			return false;

		return true;
	}

	
	/**
	 * Prueft, ob die Farbe der bewegten Figuren mit der Farbe des Spielers 
	 * uebereinstimmt.
	 * 
	 * @param spielfelder als Spielfeld-Array.
	 * @param spielerAmZug als Spieler Objekt.
	 * @return true oder false in Abhaengigkeit der Farbenuebereinstimmung.
	 */
	private boolean sindEigeneFiguren(Spielfeld [] spielfelder, 
			Spieler spielerAmZug) {

		for (int i = 0; i < spielfelder.length; i++) {

			if (spielfelder[i] == null ||
				spielfelder[i].getFigur().getFarbe() != spielerAmZug.getFarbe()) 
			{
				return false;
			}
		}
		return true;
	}
	
	

	}
