/**
 * <h1>Spiel</h1>
 * Die Klasse Spiel implementiert das zentrale Objekt für das Spiel Abalone. In
 * ihr laufen alle anderen Klassen zusammen, somit bildet sie die Schnittstelle
 * zu weiteren Objekten wie einer UI 
 * @author Gruppe A4
 * @Version 1.0
 * @since 14.03.2019 
 */
package abalone;

public class Spiel {
	
	
	private Spieler spielerAmZug;
	private Spieler[] spielerImSpiel;
	private Spielbrett spielBrett;
	
	
	/**
	 * Diese Methode wird Benutzt, um Spieler einem Spiel hinzuzufuegen. Sie 
	 * fuegt maximal 2 Spieler hinzu. Sollte ein dritter Spieler hinzugefügt
	 * werden, wirft die Methode eine IndexOutOfBounds Exception.
	 * @param name Der für den Spieler gewaehlte Name
	 * @param farbe Die für den Spieler gewaehlte Farbe
	 */
	public void addSpieler(String name, String farbe) {
		if(spielerImSpiel[0] != null && spielerImSpiel[1] != null) {
			throw new IndexOutOfBoundsException("Das Spieler Array ist bereits voll!");
		}
		if(spielerImSpiel[0] == null) {
			FarbEnum spielerFarbe = new FarbEnum(farbe);
			spielerImSpiel[0] =  new Spieler(name, spielerFarbe));
		}else {
			FarbEnum spielerFarbe = new FarbEnum(farbe);
			spielerImSpiel[1] =  new Spieler(name, spielerFarbe));
		}
		
	}
	/** 
	 * Diese Methode gibt den Namen des aktuellen Spielers zurueck.
	 * @return Name des aktuellen Spielers als String.
	 */
	public String getSpielerAmZug() {
		return spielerAmZug.name;
	}
	
	public void starte() {
		
	}
	
	public boolean hatGewonnen(String name) {
		
	}
	/**
	 * Die ziehe Methode erzeugt aus zwei Strings ein Zug Objekt und übergibt 
	 * dieses dem Spielbrett.
	 * @param zug Ein String Array mit den Werten [0] = von wo gezogen wird,
	 * [1] = wohin gezogen wird.
	 */
	public void ziehe(String[] zug) {
		Spielzug spielZug = new Spielzug(zug[0], zug[1]);
		
		spielBrett.ziehe(spielZug);
		historie.spielZugHinzufuegen; 
		
		
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
	 */
	private char[][] spielzugParser(String[] zug) {
		char[][] geparsterZug = new int[2][];
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
		
		//Zielkoordinate(n) anlege(n)
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
	 */
	private boolean koordinatenValidieren (char[][] geparsterZug) {
		//Pruefen ob die angegebenen Chars auch auf dem Spielbrett existieren
		for(char[] koordinate: geparsterZug) {
			for(int i = 0; i < koordinate.length; i++) {
				if(i%2 == 0) {
					if(!(('I' - koordinate[i] <= 8) 
					     && ('A' - koordinate[i] >= 0))) {
							return false;
					}
				}else {
					if(!(('9' - koordinate[i] <= 8) 
						     && ('1' - koordinate[i] >= 0))) {
								return false;
						}
				}
			}
		}
		//Pruefen ob der Zug an sich valide ist
		switch(geparsterZug[0][0]-geparsterZug[1][0]) {
			int zahlenKoordinaten = geparsterZug[0][1] - geparsterZug[1][1]; //Differenz der Zahlenkoordinaten
			case 0: return ((zahlenKoordinaten == 1) || (zahlenKoordinaten == -1));
			
			case -1: return ((zahlenKoordinaten == 0) || (zahlenKoordinaten == 1)));
			
			case 1: return ((zahlenKoordinaten == 0) || (zahlenKoordinaten == -1)));
			
			default:
				return false;
		}
	}
	/**
	 * Prueft, ob ein Spielzug in abhaengigkeit der Spielbrett belegung
	 * valide ist. 
	 * @param Ein Zweidimensionales String Array.
	 * @return True oder False in abhaengigkeit von der Validitaet des Spielzuges.
	 */
	private boolean spielzugValidieren(String[] zug) {
		
	}

}