package abalone;

public class Spielfigur {

	private FarbEnum farbe;
	
	public Spielfigur(Spielfeld feld, FarbEnum farbe) {
		if (feld == null) {
			throw new RuntimeException("Spielfeld-Objekt muss existieren.");
		}
		setFarbe(farbe);
	}
	
	public Spielfigur(Spielfeld feld, String farbe) {
		if(feld == null) {
			throw new RuntimeException("Spielfeld-Objekt muss existieren.");
		}
		
		if(farbe == null|| 
				(!(farbe.equals("WEISS") || farbe.equals("SCHWARZ")))) {
			throw new RuntimeException("Farbe muss Schwarz oder Weiss sein");
		}
		
		if(farbe.equals("WEISS")) {
			this.farbe = FarbEnum.WEISS;
		}
		
		if(farbe.equals("SCHWARZ")) {
			this.farbe = FarbEnum.SCHWARZ;
		}
	}
	
	public void setFarbe(FarbEnum farbe) {
		this.farbe = farbe;
	}
	
	public FarbEnum getFarbe() {
		return farbe;
	}
}
