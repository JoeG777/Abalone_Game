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
	 * Diese Methode wird Benutzt, um Spieler einem Spiel hinzuzufuegen. Sie 
	 * fuegt maximal 2 Spieler hinzu. Sollte ein dritter Spieler hinzugefügt
	 * werden, wirft die Methode eine IndexOutOfBounds Exception.
	 * @param name Der für den Spieler gewaehlte Name
	 * @param farbe Die für den Spieler gewaehlte Farbe
	 * @since 1.0
	 */
	public void addSpieler(String name, String farbe) {
		if(spielerImSpiel[0] != null && spielerImSpiel[1] != null) {
			throw new IndexOutOfBoundsException("Das Spieler Array ist bereits voll!");
		}
		if(farbe.equals("weiss")  && spielerImSpiel[0] != null) {
			FarbEnum spielerFarbe = FarbEnum.WEISS;
			//	spielerImSpiel[0];
		}else if(farbe.equals("weiss")  && spielerImSpiel[0] != null){
			FarbEnum spielerFarbe = FarbEnum.SCHWARZ;
			//spielerImSpiel[1];
		}else {
			throw new IllegalArgumentException("Unbekannte farbe :" + farbe);
		}

	}
	/** 
	 * Diese Methode gibt den Namen des aktuellen Spielers zurueck.
	 * @return Name des aktuellen Spielers als String.
	 */
	public String getSpielerAmZug() {
		return spielerAmZug.getName();
	}

	public void starte() {

	}

	/**
	 * Fragt ab, ob ein Spieler gewonnen hat
	 * @param Spielername
	 * @return Wahrheitswert Ob der übergebene Spieler gewonnen hat
	 */
	public boolean hatGewonnen(String name) {
		Spieler[] spielerArr = getSpieler();

		for (int i = 0; i< spielerArr.length; i++){
			if (name.equals(spielerArr[i].getName())) {
				if(spielerArr[i].getFarbe() == FarbEnum.WEISS){
					//Regel: Gewonen bei 6 rausgeschmissenen Kugeln
					if(8 >= getFarbigeFelder(FarbEnum.SCHWARZ)) {
						return true;
					}
				}else {
					if(8 >= getFarbigeFelder(FarbEnum.WEISS)) {
						return true;
					}
				}
			}
		}
	}

	/**
	 * Überprüft, wie viele Figuren es noch von dieser Farbe gibt
	 * @param farbe
	 * @return Anzahl der Felder von der übergebenen Farbe
	 */
	public int getFarbigeFelder(FarbEnum farbe) {
		return 0;
	}
	/**
	 * Die ziehe Methode erzeugt aus zwei Strings ein Zug Objekt und übergibt 
	 * dieses dem Spielbrett.
	 * @param zug Ein String Array mit den Werten [0] = von wo gezogen wird,
	 * [1] = wohin gezogen wird.
	 * @since 1.0
	 */
	public void ziehe(String[] zug) {
		if(spielzugValidieren(zug)) {
			Spielzug spielzug = new Spielzug(zug[0], zug[1]);

			spielBrett.ziehe(spielzug);
			historie.spielzugHinzufuegen(spielzug);
			if(spielerAmZug.getFarbe() == spielerImSpiel[0].getFarbe()) {
				spielerAmZug = spielerImSpiel[1];
			}else {
				spielerAmZug = spielerImSpiel[0];
			}
		}else {
			throw new IllegalArgumentException("Unzulaessiger Zug");
		}

	}

	public String[] getErlaubteZuege(){

	}

	public String getHistorie() {

	}

	public String getStatus() { //??

	}

	/**
	 * Diese Methode Parst einen Spielzug zu einem Char Array zur weiteren 
	 * Verarbeitung
	 * @param zug Ein String Array mit den Werten [0] = von wo gezogen wird,
	 * [1] = wohin gezogen wird.
	 * @return ein zweidimensionales char Array, welches den Zug in chars 
	 * aufteilt
	 * @since 1.1
	 */
	public char[][] spielzugParser(String[] zug) {
		char[][] geparsterZug = new char[2][];
		if(zug.length < 2) {
			throw new IllegalArgumentException(
					"Ungueltige laenge: " + zug.length
					);
		}
		if(zug[0].length()%2 != 0 || zug[0].length() > 4) {
			throw new IllegalArgumentException( 
					"Ungueltige zuglaenge: " + zug[0].length()
					);
		}

		//Ausgangskoordinate(n) anlege(n)
		char[] ausgangsPunkt = new char[zug[0].length()];
		//Erste Koordinate
		char buchstabe1 = zug[0].charAt(0);
		char zahl1 = zug[0].charAt(1);
		ausgangsPunkt[0] = buchstabe1;
		ausgangsPunkt[1] = zahl1;

		if(zug[0].length() == 4) {
			//Zweite Koordinate
			char buchstabe2 = zug[0].charAt(2);
			char zahl2 = zug[0].charAt(3);
			ausgangsPunkt[2] = buchstabe2;
			ausgangsPunkt[3] = zahl2;
		}
		geparsterZug[0] = ausgangsPunkt;
		//Zielkoordinate anlegen
		char[] zielPunkt = new char[2];
		char buchstabe = zug[1].charAt(0);
		char zahl = (zug[1].charAt(1));
		zielPunkt[0] = buchstabe;
		zielPunkt[1] = zahl;
		geparsterZug[1] = zielPunkt;
		return geparsterZug;

	}

	/**
	 * Prueft ob die gegeben Koordinaten einem validen Zug entsprechen,
	 * unabhaengig von der aktuellen Feldbelegegung.
	 * @param ein zweidimensionales Char Array.
	 * @return true oder false in Abhaengigkeit der Validitaet eines Zuges.
	 * @since 1.1
	 */
	public boolean koordinatenValidieren (char[][] geparsterZug) {
		//Pruefen ob die angegebenen Chars auch auf dem Spielbrett existieren
		int buchstabenKoordinaten = 0;
		int zahlenKoordinaten = 0;
		for(char[] koordinate: geparsterZug) {
			for(int i = 0; i < koordinate.length; i++) {
				if(i%2 == 0) {
					buchstabenKoordinaten = 'I' - koordinate[i];
					if(!(buchstabenKoordinaten <= 8 && buchstabenKoordinaten >= 0)) {
						return false;
					}
				}else {
					zahlenKoordinaten = '9' - koordinate[i];
					// Die cases Spiegeln die differenz Zwischen 'I' und dem aktuellen Buchstaben wider
					// Buchstabe = I: case = 0, Buchstabe = A: case = 8
					switch (buchstabenKoordinaten) {
					case 0: if(zahlenKoordinaten > 5 || zahlenKoordinaten < 0) {
						return false;
					}
					case 1: if(zahlenKoordinaten > 6 || zahlenKoordinaten < 0) {
						return false;
					}
					case 2: if(zahlenKoordinaten > 7 || zahlenKoordinaten < 0) {
						return false;
					}
					case 3: if(zahlenKoordinaten > 8 || zahlenKoordinaten < 0) {
						return false;
					}
					case 4: if(zahlenKoordinaten > 9 || zahlenKoordinaten < 0) {
						return false;
					}
					case 5: if(zahlenKoordinaten > 9 || zahlenKoordinaten < 1) {
						return false;
					}
					case 6: if(zahlenKoordinaten > 9 || zahlenKoordinaten < 2) {
						return false;
					}
					case 7: if(zahlenKoordinaten > 9 || zahlenKoordinaten < 3) {
						return false;
					}
					case 8: if(zahlenKoordinaten > 9 || zahlenKoordinaten < 4) {
						return false;
					}
					}
				}
			}
		}
		//Pruefen ob der Zug an sich valide ist
		if(geparsterZug[0].length == 2) { // Wenn nur eine Kugel bewegt wird
			zahlenKoordinaten = geparsterZug[0][1] - geparsterZug[1][1];
			buchstabenKoordinaten = geparsterZug[0][0]-geparsterZug[1][0];
			switch(buchstabenKoordinaten) { // Wert der Buchstaben Koordinate
			//Differenz der Zahlenkoordinaten
			case 0: return ((zahlenKoordinaten == 1) || (zahlenKoordinaten == -1));

			case -1: return ((zahlenKoordinaten == 0) || (zahlenKoordinaten == -1));

			case 1: return ((zahlenKoordinaten == 0) || (zahlenKoordinaten == 1));

			default: return false;
			}
		}else { //bei mehreren Kugeln
			// a) pruefen ob die Kugeln so zusammen bewegt werden duerfen
			zahlenKoordinaten = geparsterZug[0][1] - geparsterZug[0][3];
			buchstabenKoordinaten = (geparsterZug[0][0]-geparsterZug[0][2]);
			if(buchstabenKoordinaten == 0) {
				if(zahlenKoordinaten > 2 || zahlenKoordinaten < -2 || zahlenKoordinaten == 0) {
					return false;
				}
			}else if(buchstabenKoordinaten >= -2 && buchstabenKoordinaten <= 2){
				if(zahlenKoordinaten != buchstabenKoordinaten || zahlenKoordinaten != 0) {
					return false;
				}

			}
			//b) pruefen ob die Kugeln auf das Zielfeld bewegt werden duerfen.
			//b) I : Ist das Zielfeld im Bereich der rechten Ausgangskoordinate?
			boolean rechts = false;
			zahlenKoordinaten = geparsterZug[0][3] - geparsterZug[1][1];
			buchstabenKoordinaten = geparsterZug[0][2]-geparsterZug[1][0];
			switch(buchstabenKoordinaten) { // Wert der Buchstaben Koordinate
			case 0: rechts = zahlenKoordinaten == -1;
			break;

			case -1: rechts = zahlenKoordinaten == -1;
			break;

			case 1: rechts = zahlenKoordinaten == 0;
			break;

			default: return false;
			};
			boolean links = false;
			//b) 2: Ist das Zielfeld im Bereich der linken Ausgangkoordinate?
			zahlenKoordinaten = geparsterZug[0][1] - geparsterZug[1][1];
			buchstabenKoordinaten = geparsterZug[0][0]-geparsterZug[1][0];
			switch(buchstabenKoordinaten) { // Wert der Buchstaben Koordinate
			case 0: links = zahlenKoordinaten == -1;
			break;

			case -1: links = zahlenKoordinaten == 0;
			break;

			case 1: links = zahlenKoordinaten == 1;
			break;

			default: return false;
			}
			return rechts || links;
		}
	}

	/**
	 * Prueft, ob ein Spielzug in abhaengigkeit der Spielbrett belegung
	 * valide ist. 
	 * @param Ein Zweidimensionales String Array.
	 * @return True oder False in abhaengigkeit von der Validitaet des Spielzuges.
	 * @since 1.1
	 */
	private boolean spielzugValidieren(String[] zug) {
		char[][] geparsterZug = this.spielzugParser(zug);
		if(!koordinatenValidieren(geparsterZug)) {
			return false;
		}

	}
}