/**
 * 
 * @author Gruppe A4
 * 
 * Die Klasse Spielfigur erschafft ein Spielfigur Objekt.
 * Sie bietet Methoden um auf ihre Attribute zuzugreifen.
 * 
 */
package abalone;

public class Spielfigur {

	private FarbEnum farbe;

	/**
	 * Erzeugt ein neues Spielfigur Objekt mit Farbe als FarbEnum.
	 * @param feld Ein Spielfeld Objekt (Muss zur Erzeugung existieren).
	 * @param farbe Die Farbe der Spielfigur
	 * @exception RuntimeException Wird geworfen, wenn kein Spielfeld-Objekt
	 * existiert.
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
	
	/**
	 * Prueft, ob die gegebene Farbe der Farbe der Figur entspricht
	 * @param farbe Farbe, die zu pruefen ist 
	 * @return True oder False
	 */
	public boolean gleicheFarbe(FarbEnum farbe) {
		if(farbe != null && this.getFarbe() == farbe)
			return true;
		return false;
	}
	/**
	 * Gibt einen String mit der Farbe der jeweiligen Figur zurueck.
	 */
	@Override
	public String toString() {
		return "Eine Figur der Farbe " + this.getFarbe();
	}
}
