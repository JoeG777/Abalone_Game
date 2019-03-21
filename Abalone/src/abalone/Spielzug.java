package abalone;

public class Spielzug {
	
	private String von;
	private String nach;
	
	/**
	 * Konstruktor der Klasse Spielzug
	 * @param von Die Position der Spieler vor dem Zug
	 * @param nach Die Position der Spieler nach dem Zug
	 */
	public Spielzug(String von, String nach) {
		setVon(von);
		setNach(nach);
	}
	
	public String getVon() {
		return von;
	}
	
	public String getNach() {
		return nach;
	}
	
	private void setVon(String von) {
		this.von = von;
	}
	
	private void setNach(String nach) {
		this.nach = nach;
	}
	
}
