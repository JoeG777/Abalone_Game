package abalone;

public class AbaloneException extends Exception{
	private int id;
	
	public AbaloneException(int id, String meldung) {
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

