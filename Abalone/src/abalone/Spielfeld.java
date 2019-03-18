package abalone;

public class Spielfeld {
	private Spielbrett brett;
	private String id;
	private FarbEnum farbe;
	private Spielfigur figur;
	
	
	
	/**
	 * Erzeugt ein neues Spielfeld-Objekt.
	 * @param brett Ein Spielbrett-Objekt (muss zur Erzeugung existieren).
	 * @param id Die ID des Feldes in Abalone-Notation.
	 * @param farbe Die Farbe des Feldes.
	 * @param figur Die Figur, die sich auf dem Feld befindet.
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
	 */
	public Spielbrett getBrett() {
		return this.brett;
	}
	
	/**
	 * Setzt das Brett-Attribut.
	 * @param brett 
	 */
	public void setBrett(Spielbrett brett) {
		this.brett = brett;
	}
	
	/**
	 * Gibt die Figur des Spielfeldes zurück.
	 * @return Ein Spielfigur-Objekt. 
	 */
	public Spielfigur getFigur() {
		return this.figur;
	}
	
	/**
	 * Setzt eine Figur auf das Feld.
	 * @param figur Ein Spielfigur-Objekt. 
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
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Setzt die ID des Spielfeldes.
	 * @param id Eine ID als String.
	 */
	
	public void setId(String id) {
		this.id = id;
	}
	
}
