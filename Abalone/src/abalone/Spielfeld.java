/**
 * @author Gruppe A4
 * @version 1.4
 * 
 * Die Klasse Spielfeld erschafft ein Spielfeld-Objekt.
 * 
 * 
 */

package abalone;

public class Spielfeld {
	private Spielbrett brett;
	private String id;
	private FarbEnum farbe;
	private Spielfigur figur;
	private Spielfeld[] nachbarn;
	
	
	/**
	 * Erzeugt ein neues Spielfeld-Objekt.
	 * @param brett Ein Spielbrett-Objekt (muss zur Erzeugung existieren).
	 * @param id Die ID des Feldes in Abalone-Notation.
	 * @param farbe Die Farbe des Feldes.
	 * @param figur Die Figur, die sich auf dem Feld befindet.
	 * @exception RuntimeException Wird geworfen, wenn kein Spielbrett 
	 * existiert.
	 * 
	 */
	public Spielfeld(Spielbrett brett, String id, FarbEnum farbe, Spielfigur figur) {
		if(brett == null) {
			throw new RuntimeException("Kann nicht ohne Brett existieren!");
		}
		setBrett(brett);
		setId(id);
		setFarbe(farbe);
		setFigur(figur);

	}
	
	/**
	 * Erzeugt ein Spielfeld-Objekt und setzt die Attribute Farbe und Figur auf
	 * null.
	 * @param brett Ein Spielbrett-Objekt (muss zur Erzeugung existieren).
	 * @param id Die ID des Feldes in Abalone-Notation.
	 * 
	 */
	public Spielfeld(Spielbrett brett, String id) {
		if(brett == null) {
			throw new RuntimeException("Kann nicht ohne Brett existieren!");
		}
		setBrett(brett);
		setId(id);
		setFarbe(null);
		setFigur(null);
	}
	
	/**
	 * Gibt das Brett-Attribut zurück.
	 * @return Ein Spielbrett-Objekt.
	 * 
	 */
	public Spielbrett getBrett() {
		return this.brett;
	}
	
	/**
	 * Setzt das Brett-Attribut.
	 * @param brett Das bespielte Brett
	 * 
	 */
	private void setBrett(Spielbrett brett) {
		this.brett = brett;
	}
	
	/**
	 * Gibt die Figur des Spielfeldes zurück.
	 * @return Ein Spielfigur-Objekt. 
	 * 
	 */
	public Spielfigur getFigur() {
		return this.figur;
	}
	
	/**
	 * Setzt eine Figur auf das Feld.
	 * @param figur Ein Spielfigur-Objekt. 
	 * 
	 */
	public void setFigur(Spielfigur figur) {
		this.figur = figur;
	}
	
	/**
	 * Gibt die Farbe des Spielfeldes zurück.
	 * @return Die Farbe des Spielfeldes als Enum.
	 */
	public FarbEnum getFarbe() {
		return this.farbe;
	}
	
	/**
	 * Setzt die Farbe des Spielfeldes.
	 * @param farbe Eine Farbe des FarbEnum.
	 */
	private void setFarbe(FarbEnum farbe) {
		this.farbe = farbe;
	}
	
	/**
	 * Gibt die ID des Spielfeldes zurück.
	 * @return Die ID des Spielfeldes.
	 * 
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Setzt die ID des Spielfeldes.
	 * @param id Eine ID als String.
	 * 
	 */
	private void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Gibt das Nachbarn Attribut zur�ck.
	 * @return Spielfeld-Array der L�nge 6.
	 * 
	 */
	public Spielfeld[] getNachbarn() {
		return this.nachbarn;
	}
	
	/**
	 * Setzt das Nachbarn Attribut. 
	 * @param nachbarn Spielfeld-Array der Laenge 6.
	 * 
	 */
	private void setNachbarn(Spielfeld[] nachbarn) {
		if(nachbarn.length != 6) {
			throw new RuntimeException("Das Nachbarn-Array muss eine Länge von 6 haben.");
		}
		
		this.nachbarn = nachbarn;
	}
	
	/**
	 * Gibt den Nachbar in die mitgegebene Richtung zur�ck, falls
	 * einer existiert.
	 * @param richtung Die Richtung in welcher der Nachbar liegt.
	 * @return Spielfeld-Objekt des Nachbarn oder null, wenn kein Nachbar
	 * in die Richtung existiert.
	 */
	public Spielfeld getNachbar(int richtung) {
		if(richtung >= 0 && richtung < 6)
			return this.nachbarn[richtung];
		return null;
	}
	
	/**
	 * Prueft, ob ein Feld den mitgegebenen Nachbar hat.
	 * @param feld ID des Nachbarn
	 * @return true, wenn Feld Nachbar hat, false wenn nicht.
	 */
	public boolean hatNachbar(String feld) {
		for(int i = 0; i < this.nachbarn.length; i++) {
			if(nachbarn[i] != null && feld.equals(nachbarn[i].getId()))
				return true;
		}
		return false;
	}
	
	/**
	 * Prueft, ob ein Feld den mitgegebenen Nachbar hat.
	 * @param Spielfeld-Objekt des Nachbarn.
	 * @return true, wenn Feld Nachbar hat, false wenn nicht.
	 */
	public boolean hatNachbar(Spielfeld feld) {
		for(int i = 0; i < this.nachbarn.length; i++) {
			if(nachbarn[i] != null && feld.equals(nachbarn[i]))
				return true;
		}
		return false;
	}
	
	/**
	 * Gibt den Index des Nachbarn im Array zur�ck.
	 * @param feld Spielfeld-Objekt des Nachbarn.
	 * @return Index des Nachbarn im Nachbarn-Array.
	 */
	public int getNachbarId(Spielfeld feld) {
		for(int i = 0; i < nachbarn.length; i++) {
			if(nachbarn[i] != null && feld.getId().equals(nachbarn[i].getId()))
				return i;
		}
		return -1;
	}
	
	/**
	 * Ermittelt, ob ein Spielfeld von einer Figur besetzt ist
	 * @return Wahrheitswert Ob besetzt oder nicht
	 */
	public boolean istBesetzt() {
		if(this.figur == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * Prueft, ob ein Feld durch einen gegnerischen Stein besetzt ist.
	 * @param farbe Die eigene Farbe.
	 * @return true, wenn ein Gegner auf dem Feld ist, false wenn es eine 
	 * eigene Figur ist oder keine Figur auf dem Feld ist. 
	 */
	public boolean istDurchGegnerBesetzt(FarbEnum farbe) {
		if(this.figur != null && !(this.figur.gleicheFarbe(farbe))) {
			return true;
		}
		return false;
	}
	
	/**
	 * Befuellt das Nachbarn-Attribut mit existenten Nachbarn (Spielfeld-Objekten).
	 * Position 0 enstpricht links, Position 1 entspricht oben-links,
	 * Position 2 entspricht unten-links, Position 3 entspricht rechts,
	 * Position 4 entspricht oben-rechts, Position 5 enstpricht unten-rechts.
	 * Existiert kein solches Spielfeld, steht im Array null.
	 */
	public void setzeNachbarn() {
		if(brett == null) {
			throw new RuntimeException("Es muss ein Brett geben");
		}
		
		String[] potentielleNachbarn = findePotentielleNachbarn(this.id);
		Spielfeld[] echteNachbarn = new Spielfeld[6];
		
		for(int i = 0; i < potentielleNachbarn.length; i++) {
			if(this.brett.getBrett().containsKey(potentielleNachbarn[i])) {
				echteNachbarn[i] = brett.getBrett().get(potentielleNachbarn[i]);
			}
			else {
				echteNachbarn[i] = null;
			}
		}
		
		setNachbarn(echteNachbarn);
	}
	
	/**
	 * Findet alle IDs die theoretisch um das Feld
	 * mit der uebergebenen ID liegen müssten, ohne zu
	 * ueberpruefen, ob die IDs tatsächlich existieren.
	 * 
	 * @param id ID des Feldes dessen Nachbarn gesucht sind
	 * @return String-Array der Groesse 6 mit IDs der Nachbarfelder
	 */
	private String[] findePotentielleNachbarn(String id) {
		String[] nachbarn = new String[6];
		char buchstabe = id.charAt(0);
		int zahl = Character.getNumericValue(id.charAt(1));
		
		nachbarn[0] = buchstabe + "" + (zahl-1);
		nachbarn[1] = ((char)(buchstabe+1)) + "" + zahl;
		nachbarn[2] = ((char)(buchstabe-1)) + "" + (zahl-1);
		nachbarn[3] = buchstabe + "" + (zahl+1);
		nachbarn[4] = ((char)(buchstabe+1)) + "" + (zahl+1);
		nachbarn[5] = ((char)(buchstabe-1)) + "" + zahl;
		

		return nachbarn;
	}
	
	/**
	 * Gibt das jeweilige, aktuelle Symbol des Spielfeldes zurück.
	 * X entspricht Weiss, O entspricht Schwarz, - entspricht einem 
	 * Feld, auf dem sich keine Figur befindet.
	 * 
	 * @return das jeweilige, aktuelle Symbol des Feldes als String.
	 * 
	 */
	public String getFeldSymbol() {
		if(this.figur != null) {
			if(this.figur.getFarbe() == FarbEnum.WEISS) {
				return "O";
			}
			else {
				return "X";
			}
		}

		return "-";
	}
}
