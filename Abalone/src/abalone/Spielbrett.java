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
	 * Konstruktor f�r Abalone Spielbrett
	 * 
	 * @since 1.0
	 */
	 public Spielbrett() {
		 setBrett(new HashMap<String, Spielfeld>());
		 schaffeMapping(); 
	 }
	 
	 
	 /**
	  * Gibt das Brettattribut des Spielbretts zur�ck.
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
	  * @param Eine HashMap<String, Spielfeld>, die das Brett modelliert
	  * 
	  * @since 1.0
	  */
	 public void setBrett(HashMap<String, Spielfeld> brett) {
		 this.brett = brett;
	 }
	 
	 
	 
	 /**
	  * Verkn�pft alle Feldbezeichnungen
	  * eines Abalone-Bretts mit Spielfeld-Objekten.
	  * 
	  * @since 1.0
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
	  * Ordnet die HashMap in Form eines Abalone Spielbretts mit Koordinaten an
	  * und gibt dieses als String zur�ck.
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

		 // Untere Koordinaten anf�gen
		 gesamtesFeld.append("       " + "1 2 3 4 5");
		 return gesamtesFeld.toString();
	 }
	 
	 
	 
	 /**
	  * Hilfsmethode von toString.
	  * Baut eine komplette Querzeile des Spielbretts mit passender Einr�ckung,
	  * Feldern und Koordinaten und gibt diese als String zur�ck.
	  * 
	  * @param Array-Position der gew�nschten Querzeilenkoordinate.
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
		 int einr�ckungL�nge = MAXDIAGONALE - felder.length()/2; 
		 String einr�ckung = "    ".substring(0, einr�ckungL�nge);
		 
		 einzelneQuerlinie.append(einr�ckung);
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
	  * @param Array-Position der gew�nschten Querzeilenkoordinate.
	  * 
	  * @return String aus Feldsymbolen, Zwischenabst�nden und einem Leerzeichen
	  * 
	  * @since 1.1
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
	  * Weist dem �bergebenem Key ein Spielfeld zu.
	  * @param Feldbezeichnung in Form eines Strings.
	  * 
	  * @since 1.0
	  */
	 private void weiseKeyFeldZu(String key) {
			Spielfeld feld = new Spielfeld(this, key);
			brett.put(key, feld);
	 }
	 
	 
	 
}