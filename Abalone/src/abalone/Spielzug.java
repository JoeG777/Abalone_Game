package abalone;

public class Spielzug {
	
	private String von;
	private String nach;
	int richtung;
	FarbEnum farbe;
	
	/**
	 * Konstruktor der Klasse Spielzug
	 * @param von Die Position der Figuren vor dem Zug
	 * @param nach Die Position der Figuren nach dem Zug
	 * @param richtung Die Richtung, in die der Spielzug geht
	 * @param farbe Die Farbe der Figuren
	 */
	public Spielzug(String von, String nach, int richtung, FarbEnum farbe) {
		setVon(von);
		setNach(nach);
		setRichtung(richtung);
		setFarbe(farbe);
	}
	
	/**
	 * Schlanker Konstruktor der Klasse Spielzug
	 * wird aufgerufen bei der Historie-Klasse und Tests
	 * @param von Die Position der Figuren vor dem Zug
	 * @param nach Die Position der Figuren nach dem Zug
	 */
	public Spielzug(String von, String nach) {
		setVon(von);
		setNach(nach);
	}
	
	/**
	 * Gibt die Position der Figuren vor dem Zug zurueck
	 * @return Die Position der Figuren vor dem Zug
	 */
	public String getVon() {
		return von;
	}
	
	/**
	 * Gibt die Position der Figuren nach dem Zug zurueck
	 * @return Die Position der Figuren nach dem Zug
	 */
	public String getNach() {
		return nach;
	}
	
	/**
	 * Gibt die Richtung zurueck, in die der Spielzug geht
	 * @return Die Richtung, in die der Spielzug geht
	 */
	public int getRichtung() {
		return richtung;
	}
	
	/**
	 * Gibt die Farbe der Figuren zurueck
	 * @return Die Farbe der Figuren
	 */
	public FarbEnum getFarbe() {
		return farbe;
	}
	
	/**
	 * Setzt die Position der Figuren vor dem Zug
	 * @param von Die Position der Figuren vor dem Zug
	 */
	private void setVon(String von) {
		this.von = von;
	}
	
	/**
	 * Setzt die Position der Figuren nach dem Zug
	 * @param nach Die Position der Figuren nach dem Zug
	 */
	public void setNach(String nach) {
		this.nach = nach;
	}
	
	/**
	 * Setzt die Richtung, in die der Spielzug geht
	 * @param richtung Die Richtung, in die der Spielzug geht
	 */
	public void setRichtung(int richtung) {
		this.richtung = richtung;
	}
	
	/**
	 * Setzt die Farbe der Figuren
	 * @param farbe Die Farbe der Figuren
	 */
	public void setFarbe(FarbEnum farbe) {
		this.farbe = farbe;
	}
	
	/**
	 * Die equals Methode, die alle enthaltenen Daten eines Spielzug auf
	 * Gleichheit ueberprueft
	 * @param o Vergleichsobjekt
	 * @return Wahrheitswert Ob das uebergebene Objekt und der referenzierte
	 * Spielzug tatsaechlich gleich sind
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o.getClass() != Spielzug.class) {
			return false;
		}
		if (o== this) {
			return true;
		}
		
		Spielzug z = (Spielzug) o;
		
		return (this.getVon() == z.getVon() &&
				this.getNach() == z.getNach() &&
				this.getRichtung() == z.getRichtung() &&
				this.getFarbe() == z.getFarbe());
	}
	
	/**
	 * Erzeugt eine flache Kopie eines Spielzugs
	 *@return Spielzugkopie
	 */
	@Override
	public Spielzug clone() {
		return new Spielzug (getVon(), getNach(), getRichtung(), getFarbe());
	}
	
}
