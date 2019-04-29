package abalone.spielbrett;

/**
 * Oberklasse aller Exceptions, welche von dem Spielbrett geworfen werden.
 *
 */
public class SpielbrettException extends Exception {
	private static final long serialVersionUID = 23L;
	private int id;
	
	/**
	 * Erstellt eine neue Instanz der Klasse SpielException
	 * @param id ID des Fehlers
	 * @param meldung Die gesetzte Message der Exception
	 */
	public SpielbrettException(int id, String meldung) {
		super(meldung);
		setId(id);
	}
	
	/**
	 * Setter fuer den int-Wert ID
	 * @param id int Wert des Fehlers
	 */
	private void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Getter fuer die ID
	 * @return id gibt die ID als int zurueck
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * toString Methode der Klasse 
	 * @return Die in der Instanz gesetzten Variablen als String
	 */
	@Override
	public String toString() {
		return "Fehler ID " + getId() + "aufgetreten" + getMessage();
	}
		
	
	
}
