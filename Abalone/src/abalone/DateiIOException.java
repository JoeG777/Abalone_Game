package abalone;

/**
 * Diese Exception wird geworfen, wenn es ein Problem beim Speichern gibt
 * 
 *
 */
public class DateiIOException extends SpielException{

	private static final long serialVersionUID = 21L;

	/**
	 * Erstellt eine neue Instanz der Klasse DateiIOException
	 * @param id ID des Fehlers
	 * @param meldung Die gesetze Message der Exception
	 */
	public DateiIOException(int id, String meldung) {
		super(id, meldung);
	}
}
