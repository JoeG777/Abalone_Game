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
		this.von = von;
		this.nach = nach;
	}
	
	public String getSpielzugVon() {
		return von;
	}
	
	public String getSpielzugNach() {
		return nach;
	}
	
}
