package abalone;

public class UngueltigeEingabeException extends SpielException{

	private static final long serialVersionUID = 21L;
	
	/**
	 * Erstellt eine neue Instanz der Klasse UngueltigerZugException
	 * @param id ID des Fehlers
	 * @param meldung Die gesetzte Message der Exception
	 */
	public UngueltigeEingabeException(int id, String meldung) {
		super(id, meldung);
		this.log();
	}
}
