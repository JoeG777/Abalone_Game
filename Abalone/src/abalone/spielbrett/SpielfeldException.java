package abalone.spielbrett;

/**
 * Wird geworfen, wenn in einem Spielfeld ein Fehler auftritt.
 *
 */
public class SpielfeldException extends SpielbrettException{

	private static final long serialVersionUID = 24L;
	
	/**
	 * Erstellt eine neue Instanz der Klasse SpielbrettException
	 * @param id ID des Fehlers
	 * @param meldung Die gesetzte Message der Exception
	 */
	public SpielfeldException(int id, String meldung) {
		super(id, meldung);
	}

}
