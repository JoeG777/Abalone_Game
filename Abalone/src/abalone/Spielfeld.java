package abalone;

public class Spielfeld {
	private Spielbrett brett;
	private String id;
	private FarbEnum farbe;
	private Spielfigur figur;

	public Spielfeld(Spielbrett brett, String id, FarbEnum farbe, Spielfigur figur) {
		if(brett == null) {
			throw new RuntimeException("Kann nicht ohne Brett existieren!");
		}
		this.id = id;
		this.farbe = farbe; 
		this.figur = figur; 

	}

	public Spielfeld(Spielbrett brett, String id) {
		if(brett == null) {
			throw new RuntimeException("Kann nicht ohne Brett existieren!");
		}
		this.brett = brett;
		this.id = id;
		this.farbe = null;
		this.figur = null;
	}
	
	
	public Spielbrett getBrett() {
		return this.brett;
	}
	
	public void setBrett(Spielbrett brett) {
		this.brett = brett;
	}
	
	public Spielfigur getFigur() {
		return this.figur;
	}
	
	public void setFigur(Spielfigur figur) {
		this.figur = figur;
	}
	
	public FarbEnum getFarbe() {
		return this.farbe;
	}
	
	public void setFarbe(FarbEnum farbe) {
		this.farbe = farbe;
	}
	public String getID() {
		return this.id;
	}
	
	public void setID(String id) {
		this.id = id;
	}
	
}
