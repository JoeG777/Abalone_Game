package abalone.spielbrett;

public class SpielbrettException extends Exception {
	private int id;
	
	public SpielbrettException(int id, String meldung) {
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
		return "Fehler ID " + getId() + "aufgetreten" + getMessage();
	}
		
	
	
}
