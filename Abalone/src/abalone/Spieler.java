package abalone;

/**
 * <h1>Spieler</h1>
 * Die Klasse Spieler enthaelt den Plan fuer die 
 * Erschaffung eines Spielers.
 * @author Gruppe A4
 */
public class Spieler implements java.io.Serializable {

	private static final long serialVersionUID = 106;
	private String name;
	private FarbEnum farbe;
	private int spielerID;
	private int eliminierteKugeln = 0;
	
	private static FarbEnum farbeZweiterSpieler;
	private static int anzahlSpieler = 0;
	
	/**
	 * Erschafft ein Spieler Objekt
	 * 
	 * @param name Der gewaehlte Name des Spielers
	 * @param farbe Die Farbe des Spielers. Beim zweiten Spieler festgelegt
	 * 
	 */
	public Spieler(String name, FarbEnum farbe) {
		anzahlSpieler++;
		setSpielerID();
		setName(name);
		setFarbe(farbe);
	}
	
	/**
	 * Setzt die Spieler ID auf die momentane Anzahl an Spieler
	 */
	private void setSpielerID() {
		this.spielerID = anzahlSpieler;
	}
	
	/**
	 * Gibt die SpielerID aus
	 * @return SpielerID
	 */
	public int getSpielerID() {
		return this.hashCode();
	}
	
	/**
	 * Setzt das Attribut Name 
	 * @param name selbstgewaehlter Name
	 */
	protected void setName(String name) {
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
	 * Setzt die Farbe eines Spielers und stellt sicher,
	 *  dass beide Farben vergeben sind
	 * 
	 * @param farbe Aus der Enumeration FarbEnum
	 * @exception RuntimeException Fehlermeldung bei ungueltiger Farbe
	 */
	private void setFarbe(FarbEnum farbe) {
		//Fallunterscheidung: Ausschliessen, dass zwei Spieler dieselbe Farbe haben
		if (farbeZweiterSpieler == null) {
			
			switch (farbe) {
			case SCHWARZ:
				this.farbe = farbe;
				farbeZweiterSpieler = FarbEnum.WEISS;
				break;
			case WEISS:
				this.farbe = farbe;
				farbeZweiterSpieler = FarbEnum.SCHWARZ;
				break;
			default:
				throw new RuntimeException("Ungueltige Farbe");
			}
		}else {
			//2.Spieler nimmt die andere Farbe an egal was
			this.farbe = farbeZweiterSpieler;
		}
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
	 * Methode wird aufgerufen, wenn der Spieler eine gegnerische Kugel 
	 * rauswirft. Die Anzahl wird einfach um eins erhoeht.
	 * 
	 */
	public void eliminiereKugel() {
		this.eliminierteKugeln++;
	}
	
	/**
	 * Gibt die momentane Anzahl der eignene Kugeln zurueck
	 * @return Anzahl, der sich im Spiel befindlichen Kugeln
	 */
	public int getEliminierteKugeln() {
		return this.eliminierteKugeln;
	}

	
	@Override
	public String toString() {
		return "Spieler " + this.getSpielerID()
		+ " mit dem Namen " + this.getName() + " spielt die Farbe "
		+ this.getFarbe().name() + " und hat schon " + getEliminierteKugeln() 
		+ " Kugeln eliminiert";
		}
	
	@Override
	/**
	 * Gibt einen hashCode fuer den Spieler zurueck.
	 */
	public int hashCode() {
		return this.spielerID;
	}
	
	/**
	 * Vergleicht den Spieler mit dem uebergebenen Objekt.
	 * @param o Das Objekt, welches man vergleichen will
	 * @return Boolean Ob zwei verglichene Objekte gleich
	 * in ihren Attributen sind
	 */
	
	@Override
	public boolean equals(Object o) {
		
		if (o == null) {
			return false;
		}
		if (o == this) {
			return true;
		}
		if (o.getClass() != Spieler.class) {
			return false;
		}
		
		Spieler  s = (Spieler)o;
		
		return (this.getName() == s.getName()&&
				this.getFarbe() == s.getFarbe() &&
				this.getSpielerID() == s.getSpielerID());
	}
	
	/**
	 * Diese Methode gibt die - zur CSV-Speicherung notwendigen - Informationen in Form
	 * eines Strings zurueck
	 * @return String, der die - zur CSV-Speicherung notwendigen - Informationen enthaelt
	 */
	public String schreibeCSV() {
		String csv = "SPIELER:";
		csv += this.name;
		String farbe;
		
		if (this.farbe == FarbEnum.WEISS) {
			farbe = "weiss";
		} else {
			if (this.farbe == FarbEnum.SCHWARZ) {
				farbe = "schwarz";
			} else {
				farbe = "null";
			}
		}
		csv += ","+farbe+","+spielerID+","+eliminierteKugeln;
		
		return csv;
	}

}
