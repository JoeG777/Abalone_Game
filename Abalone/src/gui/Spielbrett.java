package gui;

import java.util.HashMap;

public class Spielbrett {
	private HashMap<String,Spielfeld> feld;
	
	public Spielbrett(String[] felder) {
		feld = new HashMap<String,Spielfeld>();
		this.aktualisieren(felder);
	}
	
	public Spielfeld getFeld(String id) {
		return feld.get(id);
	}
	
	public void aktualisieren(String[] felder) {
		String[] dataRaw = null;
		for(String feldDaten : felder) {
			dataRaw = feldDaten.split("\n");
			for(String s : dataRaw) {
				System.out.println(s);
				/*String[] data = s.split(",");
				String id = data[0];
				String farbe = data[1].split(":")[1];
				Spielfeld spielFeld = new Spielfeld(id, farbe);
				feld.put(id, spielFeld);*/
			}
		}
	}
}	

