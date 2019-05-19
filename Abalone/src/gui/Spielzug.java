package gui;

import java.util.ArrayList;

public class Spielzug {
	private static Controller subscribedC;
	private static String[] zug = {"",""};
	private static String moeglZuege;
	public static void subscribe(Controller c) {
		subscribedC = c;
	}
	
	public static void toggleString(FarbEnum farbe,String id) {
		if(Spieler.getSpielerAmZugFarbe() == farbe) {
			if(zug[0].contains(id)) {
				if(zug[0].length() == 4) {
					if(zug[0].substring(0,3).equals(id)) {
						zug[0] = "";
					}else {
						zug[0] = zug[0].substring(2,4);
					}
				}else {
					zug[0] = "";
				}
			}else {
				zug[0] += id;
			}
		}else {
			zug[1] = id;
		}
	}
	
	public static void filterMoeglicheZuege() {
		String rohDaten = subscribedC.setEraubteZuege(parseFuerErlaubteZuege());
	}
	
	private static String[] parseFuerErlaubteZuege() {
		String[] geparst = new String[2];
		geparst[1] = null;
		geparst[0] = zug[0];
		return geparst;
	}
	
	public static String[] getMoeglicheZuege() {
		ArrayList<String> ids = new ArrayList<String>();
		String rohDaten = subscribedC.setEraubteZuege(parseFuerErlaubteZuege());
		String[] einzelnZuege = rohDaten.split(",");
		for(String zug : einzelnZuege) {
			String felder[] = zug.split("-");
			subscribedC.getSpielfeldMitId(felder[1]).setAuswaehlbar();
			ids.add(felder[1]);
		}
		return ids.toArray(new String[0]);
	}
}
