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
	 * 
	 * @since 1.0
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
	 * @since 1.0
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
	 * @since 1.0
	 */
	public Spielbrett getBrett() {
		return this.brett;
	}
	
	/**
	 * Setzt das Brett-Attribut.
	 * @param brett Das bespielte Brett
	 * 
	 * @since 1.0
	 */
	public void setBrett(Spielbrett brett) {
		this.brett = brett;
	}
	
	/**
	 * Gibt die Figur des Spielfeldes zurück.
	 * @return Ein Spielfigur-Objekt. 
	 * 
	 * @since 1.0
	 */
	public Spielfigur getFigur() {
		return this.figur;
	}
	
	/**
	 * Setzt eine Figur auf das Feld.
	 * @param figur Ein Spielfigur-Objekt. 
	 * 
	 * @since 1.0
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
	public void setFarbe(FarbEnum farbe) {
		this.farbe = farbe;
	}
	
	/**
	 * Gibt die ID des Spielfeldes zurück.
	 * @return Die ID des Spielfeldes.
	 * 
	 * @since 1.0
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Setzt die ID des Spielfeldes.
	 * @param id Eine ID als String.
	 * 
	 * @since 1.0
	 */
	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Gibt das Nachbarn Attribut zurück.
	 * @return Spielfeld-Array der Länge 6.
	 * 
	 * @since 1.3
	 */
	public Spielfeld[] getNachbarn() {
		return this.nachbarn;
	}
	
	public Spielfeld getNachbar(int richtung) {
		if(richtung >= 0 && richtung < 6)
			return this.nachbarn[richtung];
		return null;
	}
	
	/**
	 * Setzt das Nachbarn Attribut. 
	 * @param nachbarn Spielfeld-Array der Länge 6.
	 * 
	 * @since 1.3
	 */
	public void setNachbarn(Spielfeld[] nachbarn) {
		if(nachbarn.length != 6) {
			throw new RuntimeException("Das Nachbarn-Array muss eine Länge von 6 haben.");
		}
		
		this.nachbarn = nachbarn;
	}
	
	/**
	 * Befüllt das Nachbarn-Attribut mit existenten Nachbarn (Spielfeld-Objekten).
	 * Position 0 enstpricht links, Position 1 entspricht oben-links,
	 * Position 2 entspricht unten-links, Position 3 entspricht rechts,
	 * Position 4 entspricht oben-rechts, Position 5 enstpricht unten-rechts.
	 * Existiert kein solches Spielfeld, steht im Array null.
	 * @since 1.3
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
		
		this.nachbarn = echteNachbarn;
		
		
		
		
	}
	/**
	 * Findet alle IDs die theoretisch um das Feld
	 * mit der übergebenen ID liegen müssten, ohne zu
	 * überprüfen, ob die IDs tatsächlich existieren.
	 * 
	 * @param id ID des Feldes dessen Nachbarn gesucht sind
	 * @return String-Array der Größe 6 mit IDs der Nachbarfelder
	 * 
	 * @since 1.3
	 */
	public String[] findePotentielleNachbarn(String id) {
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
	 * @param Spielfeld ein beliebiges Spielfeld
	 * @return das jeweilige, aktuelle Symbol des Feldes als String.
	 * 
	 * @since 1.2
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
	
	public boolean hatNachbar(String feld) {
		for(int i = 0; i < this.nachbarn.length; i++) {
			if(nachbarn[i] != null && feld.equals(nachbarn[i].getId()))
				return true;
		}
		return false;
	}
	
	public boolean hatNachbar(Spielfeld feld) {
		for(int i = 0; i < this.nachbarn.length; i++) {
			if(nachbarn[i] != null && feld.equals(nachbarn[i]))
				return true;
		}
		return false;
	}
	
	public int getNachbarId(Spielfeld feld) {
		for(int i = 0; i < nachbarn.length; i++) {
			if(nachbarn[i] != null && feld.getId().equals(nachbarn[i].getId()))
				return i;
		}
		return -1;
	}
	
	public boolean gleichBelegt(Spielfeld feld) {
		if(feld == null || this.getFigur() == null || feld.getFigur() == null)
			return false;
		return this.getFigur().getFarbe() == feld.getFigur().getFarbe();
	}
}
