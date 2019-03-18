package abalone;

public class Spielfigur {

	private FarbEnum farbe;
	
	public Spielfigur(FarbEnum farbe) {
		setFarbe(farbe);
	}
	
	public void setFarbe(FarbEnum farbe) {
		this.farbe = farbe;
	}
	
	public FarbEnum getFarbe() {
		return farbe;
	}
}
