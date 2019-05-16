package gui;

public class Spielfeld {
	private FarbEnum figurFarbe;
	private String id;
	private FeldPanel subscriber;
	public Spielfeld(String id, String farbe) {
		this.id = id;
		if(farbe.contains("weiss")) {
			figurFarbe = FarbEnum.WEISS;
		}else if(farbe.contains("schwarz")){
			figurFarbe = FarbEnum.SCHWARZ;
		}else {
			figurFarbe = null;
		}
	}
	
	public FarbEnum getFigurFarbe() {
		return this.figurFarbe;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void subscribe(FeldPanel fp) {
		subscriber = fp;
	}
	
	public void setfigurFarbe(FarbEnum farbe) {
		figurFarbe = farbe;
		if(subscriber != null) {
			subscriber.aktualisiere();
		}
	}
}