package abalone;

public class Spielfigur {

	private FarbEnum farbe;

	/**
	 * Erzeugt ein neues Spielfigur Objekt mit Farbe als FarbEnum.
	 * @param feld Ein Spielfeld Objekt (Muss zur Erzeugung existieren).
	 * @param farbe Die Farbe der Spielfigur
	 */
	public Spielfigur(Spielfeld feld, FarbEnum farbe) {
		if (feld == null) {
			throw new RuntimeException("Spielfeld-Objekt muss existieren.");
		}
		setFarbe(farbe);
	}
	
	/**
	 * Erzeugt ein neues Spielfigur Objekt mit Farbe als String.
	 * @param feld Ein Spielfeld Objekt (muss zur Erzeugung existieren).
	 * @param farbe Die Farbe der Spielfigur.
	 */

	public Spielfigur(Spielfeld feld, String farbe) {
		if(feld == null) {
			throw new RuntimeException("Spielfeld-Objekt muss existieren.");
		}

		if(farbe == null|| 
				(!(farbe.equals("WEISS") || farbe.equals("SCHWARZ")))) {
			throw new RuntimeException("Farbe muss Schwarz oder Weiss sein");
		}

		if(farbe.equals("WEISS")) {
			setFarbe(FarbEnum.WEISS);
		}

		if(farbe.equals("SCHWARZ")) {
			setFarbe(FarbEnum.SCHWARZ);
		}
	}

	/**
	 * Setzt die Farbe der Spielfigur.
	 * @param farbe Die Farbe der Spielfigur.
	 */
	public void setFarbe(FarbEnum farbe) {
		this.farbe = farbe;
	}

	/**
	 * Gibt Die Farbe der Spielfigur zurueck.
	 * @return farbe
	 */
	public FarbEnum getFarbe() {
		return farbe;
	}
}
