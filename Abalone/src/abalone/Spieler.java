/**
 * @author Gruppe A4
 * 
 * Die Klasse Spieler enth�lt den Plan f�r die 
 * Erschaffung eines Spielers.
 */
package abalone;

public class Spieler {

	private String name;
	private FarbEnum farbe;
	private static FarbEnum farbeZweiterSpieler;
	/*wird ein Spieler mit einer farbe erzeugt
	 MUSS der andere Spieler die andere Farbe benutzen

	 */

	/**
	 * Erschafft ein Spieler Objekt
	 * 
	 * @param name Der gew�hlte Name des Spielers
	 * @param farbe Die Farbe des Spielers. Beim zweiten Spieler festgelegt
	 * 
	 * @return Gibt die erzeugte Spieler-Instanz zurueck
	 */
	public Spieler(String name, FarbEnum farbe) {
		setName(name);
		setFarbe(farbe);
	}

	/**
	 * Setzt das Attribut Name 
	 * @param name selbstgewaehlter Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gibt den gewaehlten Namen zurueck
	 * 
	 * @return name selbstgewaehlter Name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * Gibt die Farbe eines Spieler zurueck
	 * 
	 * @return farbe 
	 */
	public FarbEnum getFarbe() {
		return this.farbe;
	}

	/**
	 * Setzt die Farbe eines Spielers und stellt sicher,
	 *  dass beide Farben vergeben sind
	 * 
	 * @param farbe Aus der Enumeration FarbEnum
	 * @exception RuntimeException Fehlermeldung bei ung�ltiger Farbe
	 */
	private void setFarbe(FarbEnum farbe) {
		//Fallunterscheidung: Ausschliessen, dass zwei Spieler dieselbe Farbe haben
		if (farbeZweiterSpieler == null) {
			switch (farbe) {
			case WEISS:
				this.farbe = farbe;
				farbeZweiterSpieler = FarbEnum.SCHWARZ;
				break;
			case SCHWARZ:
				this.farbe = farbe;
				farbeZweiterSpieler = FarbEnum.WEISS;
				break;
			default:
				throw new RuntimeException("Ung�ltige Farbe");
			}
		}else {
			this.farbe = farbeZweiterSpieler;
		}
		
	}
}
