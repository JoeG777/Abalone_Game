package gui;

public class Spieler {
	private String name;
	private FarbEnum farbe;
	private static Spieler spielerAmZug;
	private static Spieler spieler1;
	private static Spieler spieler2;
	public Spieler(String name, FarbEnum farbe) {
		this.name = name;
		this.farbe = farbe;
	}
	
	public void setSpielerAmZug(Spieler spieler) {
		spielerAmZug = spieler;
	}
	public Spieler getSpielerAmZug() {
		return spielerAmZug;
	}
	
	public FarbEnum getFarbe() {
		return this.farbe;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void naechsterSpieler() {
		if(this.equals(spielerAmZug)) {
			spielerAmZug = this;
		}else if(!this.equals(spieler2)){
			spielerAmZug = spieler2;
		}else {
			spielerAmZug = spieler1;
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if(o instanceof Spieler) {
			Spieler spieler = (Spieler) o;
			return this.getName() == spieler.getName();
		}
		return false;
	}
}
