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

import testprojekt.FarbEnum;
import testprojekt.Spielbrett;
import testprojekt.Spieler;
import testprojekt.Spielzug;

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
	 * Diese Methode Parst einen Spielzug zu einem Integer Array zur weiteren 
	 * Verarbeitung
	 * @param zug Ein String Array mit den Werten [0] = von wo gezogen wird,
	 * [1] = wohin gezogen wird.
	 * @return ein zweidimensionales int Array, welches den Zug in zahlen 
	 * aufteilt
	 */
	private int[][] SpielzugParser(String[] zug) {
		int[][] geparsterZug = new int[2][];
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
		int[] ausgangsPunkt = new int[zug[0].length()];
		//Erste Koordinate
		char buchstabe1 = zug[0].charAt(0);
		int zahl1 = Integer.parseInt(String.valueOf(zug[0].charAt(1)));
		ausgangsPunkt[0] = this.buchstabenMapper(buchstabe1);
		ausgangsPunkt[1] = zahl1;
			
		if(zug[0].length() == 4) {
			//Zweite Koordinate
			char buchstabe2 = zug[0].charAt(2);
			int zahl2 = Integer.parseInt(String.valueOf(zug[0].charAt(3)));
			ausgangsPunkt[2] = this.buchstabenMapper(buchstabe2);
			ausgangsPunkt[3] = zahl2;
		}
		geparsterZug[0] = ausgangsPunkt;
		
		//Zielkoordinate(n) anlege(n)
		int[] zielPunkt = new int[2];
		char buchstabe = zug[1].charAt(0);
		int zahl = Integer.parseInt(String.valueOf(zug[1].charAt(1)));
		zielPunkt[0] = buchstabenMapper(buchstabe);
		zielPunkt[1] = zahl;
		
		geparsterZug[1] = zielPunkt;
		
		return geparsterZug;
		
	}
	/**
	 * Diese Methode Mapt Buchstaben auf Zahlen
	 * @param ein zu Mappender Buchstabe zwischen A und I
	 */
	private int buchstabenMapper(char buchstabe) {
		int zahl;
		switch (buchstabe) {
			case 'A' : 
				zahl = 1;
				break;
			case 'B' : 
				zahl = 2;
				break;
			case 'C' : 
				zahl = 3;
				break;
			case 'D' : 
				zahl = 4;
				break;
			case 'E' : 
				zahl = 5;
				break;
			case 'F' :
				zahl = 6;
				break;
			case 'G' : 
				zahl = 7;
				break;
			case 'H' : 
				zahl = 8;
				break;
			case 'I' : 
				zahl = 9;
				break;
			default:
				throw new IllegalArgumentException(
						"Ungueltiger Buchstabe " + buchstabe);
			
		}
		return zahl;
	}
	private boolean spielZugValidieren (String[] zug) {
		
	}

}