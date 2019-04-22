package abalone;

public class SpielException extends Exception{
	private static final long serialVersionUID = 21L;
	private int id;
	
	public SpielException(int id, String meldung) {
		super(meldung);
		setId(id);
	}
	
	private void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "Fehler ID " + getId() + " aufgetreten: " + getMessage();
	}
}

