package abalone;

/**
 * Wird geworfen, wenn ein ungueltiger Zug eingegeben wurde
 *
 */
public class UngueltigerZugException extends SpielException{

	private static final long serialVersionUID = 21L;
	
	/**
	 * Erstellt eine neue Instanz der Klasse UngueltigerZugException
	 * @param id ID des Fehlers
	 * @param meldung Die gesetze Message der Exception
	 */
	public UngueltigerZugException(int id, String meldung) {
		super(id, meldung);
	}

}
