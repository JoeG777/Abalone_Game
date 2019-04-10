package abalone;

public class KI extends Spieler {

	private static final long serialVersionUID = 110L;
	private static int anzahlKIs = 0;
	
	public KI(FarbEnum farbe) {
		super("KEVIN", farbe);
		anzahlKIs++;
	}

	public int getAnzahlKIs() {
		return anzahlKIs;
	}
}
