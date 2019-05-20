package gui;

import java.util.ArrayList;
import java.util.HashMap;

public class Spielbrett {
	private HashMap<String,Spielfeld> felder;
	private ArrayList<String> keys;
	
	public Spielbrett(String[] felder) {
		this.felder = new HashMap<String,Spielfeld>();
		keys = new ArrayList<String>();
		this.aktualisieren(felder);
	}
	
	public Spielfeld getFeld(String id) {
		return felder.get(id);
	}
	
	public void aktualisieren(String[] felder) {
		String[] dataRaw = null;
		keys.clear();
		for(String feldDaten : felder) {
			dataRaw = feldDaten.split(",");
			for(int i = 0; i < dataRaw.length; i++) {
				keys.add(dataRaw[1]);
				String id = dataRaw[1];
				String besetzung = dataRaw[2].split(":")[1];
				Spielfeld f = new Spielfeld(id, besetzung);
				this.felder.put(id, f);
			}
		}
	}
	
	public void resetAuswaehlbar() {
		for(String id : keys) {
			felder.get(id).unsetAuswaehlbar();
		}
	}
	
}	

