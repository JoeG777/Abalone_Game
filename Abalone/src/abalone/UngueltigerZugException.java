package abalone;

public class UngueltigerZugException extends SpielException{

	private static final long serialVersionUID = 21L;

	public UngueltigerZugException(int id, String meldung) {
		super(id, meldung);
	}

}
