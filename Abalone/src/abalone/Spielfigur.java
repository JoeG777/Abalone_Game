package abalone;

public class Spielfigur {

	private FarbEnum farbe;
	
	public Spielfigur(Spielfeld brett, FarbEnum farbe) {
		if (brett == null) {
			throw new RuntimeException("Es existiert kein Brett");
		}
		setFarbe(farbe);
	}
	
	public void setFarbe(FarbEnum farbe) { //Test
		this.farbe = farbe;
	}
	
	public FarbEnum getFarbe() {
		return farbe;
	}
}
