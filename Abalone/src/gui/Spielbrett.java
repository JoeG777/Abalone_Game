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
			dataRaw = feldDaten.split(",");
			for(int i = 0; i < dataRaw.length; i++) {
				String id = dataRaw[1];
				String besetzung = dataRaw[2].split(":")[1];
				Spielfeld f = new Spielfeld(id, besetzung);
				feld.put(id, f);
			}
		}
	}
	
}	

