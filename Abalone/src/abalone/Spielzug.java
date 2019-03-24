package abalone;

public class Spielzug {
	
	private String von;
	private String nach;
	int richtung;
	FarbEnum farbe;
	
	/**
	 * Konstruktor der Klasse Spielzug
	 * @param von Die Position der Spieler vor dem Zug
	 * @param nach Die Position der Spieler nach dem Zug
	 */
	public Spielzug(String von, String nach, int richtung, FarbEnum farbe) {
		this.setVon(von);
		this.setNach(nach);
		this.setRichtung(richtung);
		this.setFarbe(farbe);
	}
	
	public Spielzug(String von, String nach) {
		this.setVon(von);
		this.setNach(nach);
	}
	
	public String getVon() {
		return von;
	}
	
	public String getNach() {
		return nach;
	}
	
	public int getRichtung() {
		return richtung;
	}
	
	private void setVon(String von) {
		this.von = von;
	}
	
	private void setNach(String nach) {
		this.nach = nach;
	}
	
	private void setRichtung(int richtung) {
		this.richtung = richtung;
	}
	
	private void setFarbe(FarbEnum farbe) {
		this.farbe = farbe;
	}
	
	public FarbEnum getFarbe() {
		return this.farbe;
	}
	
}
