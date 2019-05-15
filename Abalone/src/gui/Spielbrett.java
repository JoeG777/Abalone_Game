package gui;

import java.util.HashMap;

public class Spielbrett {
	private HashMap<String,Spielfeld> feld;
	
	public Spielbrett(String[] felder) {
		feld = new HashMap<String,Spielfeld>();
		this.aktualisieren(felder);
	}
	
	public Spielfeld getFeld(String id) {
		System.out.println(id);
		return feld.get(id);
	}
	
	public void aktualisieren(String[] felder) {
		for(String feldDaten : felder) {
			String[] data = feldDaten.split(",");
			String id = data[0];
			System.out.println(id);
			System.out.println(id.length());
			String farbe = data[1].split(":")[1];
			Spielfeld spielFeld = new Spielfeld(id, farbe);
			feld.put(id, spielFeld);
		}
		System.out.println("Done");
	}
	
	
}
