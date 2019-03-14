/**
 * @author Team A4
 * @version 1.0
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
	 */
	 public Spielbrett() {
		 setBrett(new HashMap<String, Spielfeld>());
		 schaffeMapping();
		 
		 
	 }
	 /**
	  * Gibt das Brettattribut des Spielbretts zurück.
	  * @return Brettattribut in Form von einer
	  * HashMap<String,Spielfeld>  des Spielbretts.
	  */
	 public HashMap<String, Spielfeld> getBrett() {
		 return this.brett;
	 }
	 
	 /**
	  * Setzt das Brettattribut des Spielbretts.
	  * @param Eine HashMap<String, Spielfeld>, die das Brett modelliert
	  */
	 public void setBrett(HashMap<String, Spielfeld> brett) {
		 this.brett = brett;
	 }
	 
	 /**
	  * Verknüpft alle Feldbezeichnungen
	  * eines Abalone-Bretts mit Spielfeld-Objekten.
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
	  * Weist dem übergebenem Key ein Spielfeld zu.
	  * @param Feldbezeichnung in Form eines Strings.
	  */
	 private void weiseKeyFeldZu(String key) {
			Spielfeld feld = new Spielfeld(this, key);
			brett.put(key, feld);
	 }
}