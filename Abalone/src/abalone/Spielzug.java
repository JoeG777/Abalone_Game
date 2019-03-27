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
		this.setVon(von);
		this.setNach(nach);
		this.setRichtung(richtung);
		this.setFarbe(farbe);
	}
	
	/**
	 * Schlanker Konstruktor der Klasse Spielzug
	 * wird aufgerufen bei der Historie-Klasse und Tests
	 * @param von Die Position der Figuren vor dem Zug
	 * @param nach Die Position der Figuren nach dem Zug
	 */
	public Spielzug(String von, String nach) {
		this.setVon(von);
		this.setNach(nach);
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
	 * @param von Die Position der Figuren nach dem Zug
	 */
	private void setNach(String nach) {
		this.nach = nach;
	}
	
	/**
	 * Setzt die Richtung, in die der Spielzug geht
	 * @param von Die Richtung, in die der Spielzug geht
	 */
	public void setRichtung(int richtung) {
		this.richtung = richtung;
	}
	
	/**
	 * Setzt die Farbe der Figuren
	 * @param von Die Farbe der Figuren
	 */
	public void setFarbe(FarbEnum farbe) {
		this.farbe = farbe;
	}
	
}
