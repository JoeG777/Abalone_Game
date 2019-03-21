package abalone;

public class Spielzug {
	
	private String von;
	private String nach;
	int richtung;
	
	/**
	 * Konstruktor der Klasse Spielzug
	 * @param von Die Position der Spieler vor dem Zug
	 * @param nach Die Position der Spieler nach dem Zug
	 */
	public Spielzug(String von, String nach, int richtung) {
		setVon(von);
		setNach(nach);
		setRichtung(richtung);
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
	
}