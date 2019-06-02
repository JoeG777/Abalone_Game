package gui;

import java.util.ArrayList;

import abalone.SpielException;

public class Spielzug {
	private static Controller controller;
	private static String[] zug = {"",""};
	private static String moeglZuege;
	private static ArrayList<FeldPanel> gewaehlteFelder;
	public static void subscribe(Controller c) {
		controller = c;
	}
	
	public static void toggleAusgewaehlt(String id) {
		controller.getSpielfeldMitId(id).toggleAusgewaehlt();
	}
	
	public static boolean checkGewaehlteFelder() {
		gewaehlteFelder = controller.bekommeGewahlteFelder();
		if(gewaehlteFelder != null) {
		if(gewaehlteFelder.size() > 3 || gewaehlteFelder.size() == 0) return false;	
		FeldPanel panel1 = gewaehlteFelder.get(0);
		FeldPanel panel2 = null;
		FeldPanel panel3 = null;
		String zuege = "";
		if(gewaehlteFelder.size() > 1) {
			panel2 = gewaehlteFelder.get(1);
			if(gewaehlteFelder.size() > 2) {
				panel3 = gewaehlteFelder.get(2);
			}
		}
		String[] felder = new String[2];
		felder[0] = panel1.getId();
		if(panel2 != null) {
			felder[0] += panel2.getId();
			zuege = controller.getErlaubteZuege(felder);
			if(zuege == null || zuege == "") {
				return false;
			}
			if(panel3 != null) {
				felder[0] = panel1.getId() + panel3.getId();
				zuege = controller.getErlaubteZuege(felder);
				if(zuege == null || zuege == "") {
					return false;
					
				}
				felder[0] = panel2.getId() + panel3.getId();
				String zuege2 = controller.getErlaubteZuege(felder);
				if(zuege2 == null || zuege2 == "") {
					return false;
				}
 			}
		}
		}
		return true;
		
	}
	
	public static void versucheZug() {
		ArrayList<FeldPanel> ausgang = new ArrayList<>();
		ArrayList<FeldPanel> ziel = new ArrayList<>();
		if(gewaehlteFelder != null) {
		for(FeldPanel p : gewaehlteFelder) {
			if(p.getFigurFarbe() == controller.getSpielerAmZugFarbe()) ausgang.add(p);
			if(p.getFigurFarbe() != controller.getSpielerAmZugFarbe()) ziel.add(p);
		}
		if(ziel.size() == 1 && ausgang.size() > 0 && ausgang.size() <= 3) {
			if(ausgang.size() == 1) {
				zug[0] = ausgang.get(0).getId();
				zug[1] = ziel.get(0).getId();
			}
			if(ausgang.size() == 2) {
				zug[0] = ausgang.get(0).getId() + ausgang.get(1).getId();
				zug[1] = ziel.get(0).getId();
			}
			if(ausgang.size() == 3) {
				zug[0] = ausgang.get(0).getId() + ausgang.get(2).getId();
				zug[1] = ziel.get(0).getId();
			}
			try {
				controller.ziehe();
				
			} catch (SpielException e) {
				if(zug[0].length() ==4 ) {
					swapFields();
					try {
						controller.ziehe();
					} catch (SpielException e1) {
						new FehlerPanel("Fehler beim Ziehen!");
					}
				}
			}
			zug[0] = "";
			zug[1] = "";
			controller.getGameFrame().getSpielfeldPanel().resetAusgewaehlt();
		}
	}
	}
	
	public static void setzeMoeglicheAuswahl() {
		controller.resetAuswaehlbar();
		if(checkGewaehlteFelder()) {
			getMoeglicheZuege();
			
		}
		controller.aktualisiereBrett();
	}
	
	public static void toggleString(FarbEnum farbe,String id) {
		controller.getSpielfeldMitId(id).toggleAusgewaehlt();
		versucheZug();
		/*
		if(controller.getSpielerAmZugFarbe() == controller.getSpielfeldMitId(id).getFigurFarbe()) {
			if(zug[0].contains(id)) {
				if(zug[0].length() == 4) {
					if(zug[0].substring(0,2).equals(id)) {
						zug[0] = zug[0].substring(2,4);
					}else {
						
						zug[0] = zug[0].substring(0,2);
						
					}
				}else {
					zug[0] = "";
				}
			}else {
				if(zug[0].length() == 4) {
					zug[0] = zug[0].substring(0,2);
					zug[0] += id;
				}else {
					zug[0] += id;
				}
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
		if(!zug[0].equals("") && !zug[1].equals("")){
			
			try {
				controller.ziehe();
				
			} catch (SpielException e) {
				if(zug[0].length() ==4 ) {
					swapFields();
					try {
						controller.ziehe();
					} catch (SpielException e1) {
						new FehlerPanel("Fehler beim Ziehen!");
					}
				}
			}
			zug[0] = "";
			zug[1] = "";
			controller.getGameFrame().getSpielfeldPanel().resetAusgewaehlt();
		}
		*/
	}
	
	public static void filterMoeglicheZuege() {
		String rohDaten = controller.setEraubteZuege(parseFuerErlaubteZuege());
	}
	
	private static String[] parseFuerErlaubteZuege() {
		String[] geparst = new String[2];
		geparst[1] = null;
		geparst[0] = "";
		if(gewaehlteFelder.size() == 1) geparst[0] = gewaehlteFelder.get(0).getId();
		if(gewaehlteFelder.size() == 2) geparst[0] = gewaehlteFelder.get(0).getId() + gewaehlteFelder.get(1).getId();
		if(gewaehlteFelder.size() == 3) geparst[0] = gewaehlteFelder.get(0).getId() + gewaehlteFelder.get(2).getId();
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
	
	public static void setZug(String[] zuge) {
		zug = zuge;
	}
	private static void swapFields() {
		String f1 = zug[0].substring(0,2);
		String f2 = zug[0].substring(2,4);
		zug[0] = "";
		zug[0] = f2+f1;
	}
	
	public static Controller getController() {
		return controller;
	}
	
	
}
