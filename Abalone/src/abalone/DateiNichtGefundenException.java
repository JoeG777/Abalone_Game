package abalone;

public class DateiNichtGefundenException extends SpielException{

	private static final long serialVersionUID = 21L;

	public DateiNichtGefundenException(int id, String meldung) {
		super(id, meldung);
	}
}
