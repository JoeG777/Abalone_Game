package abalone.spielbrett;

public class SpielfeldException extends SpielbrettException{

	private static final long serialVersionUID = 24L;

	public SpielfeldException(int id, String meldung) {
		super(id, meldung);
	}

}
