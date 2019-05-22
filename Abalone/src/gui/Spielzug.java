package gui;

import java.util.ArrayList;

import abalone.SpielException;

public class Spielzug {
	private static Controller controller;
	private static String[] zug = {"",""};
	private static String moeglZuege;
	public static void subscribe(Controller c) {
		controller = c;
	}
	
	public static void toggleString(FarbEnum farbe,String id) {
		if(Spieler.getSpielerAmZugFarbe() == controller.getSpielfeldMitId(id).getFigurFarbe()) {
			if(zug[0].contains(id)) {
				if(zug[0].length() == 4) {
					if(zug[0].substring(0,3).equals(id)) {
						zug[0] = "";
					}else {
						controller.getSpielfeldMitId(zug[0].substring(2,4)).toggleAusgewaehlt();
						zug[0] = zug[0].substring(2,4);
					}
				}else {
					zug[0] = "";
				}
			}else {
				zug[0] += id;
			}
		}else {
			if(zug[1].equals(id)) {
				zug[1] = "";
			}else {
				if(!zug[1].equals("")) {
					controller.getSpielfeldMitId(zug[1]).toggleAusgewaehlt();
				}
				zug[1] = id;
			}
		}
		controller.getSpielfeldMitId(id).toggleAusgewaehlt();
		if(!zug[0].equals("") && !zug[1].equals("")){
			
			try {
				controller.ziehe();
				
			} catch (SpielException e) {
				if(zug[0].length() ==4 ) {
					swapFields();
					try {
						controller.ziehe();
					} catch (SpielException e1) {
					}
				}
			}
			controller.getSpielfeldMitId(zug[1]).toggleAusgewaehlt();
			if(zug[0].length() >= 2) {
				controller.getSpielfeldMitId(zug[0].substring(0,2)).toggleAusgewaehlt();
			}
			if(zug[0].length() >= 4) {
				controller.getSpielfeldMitId(zug[0].substring(2,4)).toggleAusgewaehlt();
			}
			zug[0] = "";
			zug[1] = "";
			controller.getGameFrame().getSpielfeldPanel().resetAusgewaehlt();
		}
		
	}
	
	public static void filterMoeglicheZuege() {
		String rohDaten = controller.setEraubteZuege(parseFuerErlaubteZuege());
	}
	
	private static String[] parseFuerErlaubteZuege() {
		String[] geparst = new String[2];
		geparst[1] = null;
		geparst[0] = zug[0];
		return geparst;
	}
	
	public static String[] getMoeglicheZuege() {
		ArrayList<String> ids = new ArrayList<String>();
		controller.resetAuswaehlbar();
		String rohDaten = controller.setEraubteZuege(parseFuerErlaubteZuege());
		if(rohDaten != null ) {
			String[] einzelnZuege = rohDaten.split(",");
			for(String zug : einzelnZuege) {
				String felder[] = zug.split("-");
				if(felder != null && felder.length >= 2 && felder[1] != null) {
					controller.getSpielfeldMitId(felder[1]).setAuswaehlbar();
					ids.add(felder[1]);
				}
			}
		controller.aktualisiereBrett();
		return ids.toArray(new String[0]);
		}
		controller.aktualisiereBrett();
		return null;
	}
	
	public static String[] getZug() {
		return zug;
	}
	
	public static void setZug(String[] zug) {
		
	}
	private static void swapFields() {
		String f1 = zug[0].substring(0,2);
		String f2 = zug[0].substring(2,4);
		zug[0] = "";
		zug[0] = f2+f1;
	}
}
