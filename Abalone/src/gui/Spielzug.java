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
						System.out.println("1");
						zug[0] = "";
					}else {
						System.out.println("2");
						zug[0] = zug[0].substring(2,4);
					}
				}else {
					System.out.println("3");
					zug[0] = "";
				}
			}else {
				System.out.println("4");
				zug[0] += id;
			}
		}else if(!zug[0].equals("")){
			System.out.println("5");
			zug[1] = id;
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
			zug[0] = "";
			zug[1] = "";
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
		return null;
	}
	
	public static String[] getZug() {
		return zug;
	}
	
	private static void swapFields() {
		String f1 = zug[0].substring(0,2);
		String f2 = zug[0].substring(2,4);
		zug[0] = "";
		zug[0] = f2+f1;
		
		
	}
}
