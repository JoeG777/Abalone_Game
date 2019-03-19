package abalone;

public class Spielfigur {

	private FarbEnum farbe;
	
	public Spielfigur(Spielfeld feld, FarbEnum farbe) {
		if (feld == null) {
			throw new RuntimeException("Es existiert kein Brett");
		}
		setFarbe(farbe);
	}
	
	public void setFarbe(FarbEnum farbe) {
		this.farbe = farbe;
	}
	
	public FarbEnum getFarbe() {
		return farbe;
	}
}
