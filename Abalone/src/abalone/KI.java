package abalone;

import java.util.ArrayList;
import java.util.HashMap;

import abalone.spielbrett.Spielbrett;
import abalone.spielbrett.SpielbrettException;
import abalone.spielbrett.SpielfeldException;

public class KI extends Spieler {

	private static final long serialVersionUID = 110L;
	private static int anzahlKIs = 0;
	private static final String[] namen = {"KI-1", "KI-2"};
	
	private String[] besterZug;
	private HashMap<String, Integer> werteMap;
	private int gegnerFigVorZug;
	
	public KI(FarbEnum farbe, Spielbrett brett) {
		super(namen[anzahlKIs], farbe);
		setGegnerFigVorZug(0);
		initWerteMap(brett);
		anzahlKIs++;
	}
	
	private void initWerteMap(Spielbrett brett) {
		boolean done = false;
		int schritte = 1;
		String aktuellesFeld = "E5";
		int wert = 12;
		werteMap.put("E5", 15);
		
		while(!done)  {
			aktuellesFeld = laufeUndBewerte(brett, aktuellesFeld, 4, 1, wert-schritte);
			aktuellesFeld = laufeUndBewerte(brett, aktuellesFeld, 3, schritte-1, wert-schritte);
			aktuellesFeld = laufeUndBewerte(brett, aktuellesFeld, 5, schritte, wert-schritte);
			aktuellesFeld = laufeUndBewerte(brett, aktuellesFeld, 2, schritte, wert-schritte);
			aktuellesFeld = laufeUndBewerte(brett, aktuellesFeld, 0, schritte, wert-schritte);
			aktuellesFeld = laufeUndBewerte(brett, aktuellesFeld, 1, schritte, wert-schritte);
			aktuellesFeld = laufeUndBewerte(brett, aktuellesFeld, 4, schritte, wert-schritte);

			schritte++;

			if(schritte == 5) {
				done = true;
			}

		}
	}
	
	private String laufeUndBewerte(Spielbrett brett, String id, int richtung, int schritte, int wert) {
		String newID = id;
		for(int i = 0; i < schritte; i++) {
			newID = brett.getNachbarByIdInRichtung(newID, richtung);
			werteMap.put(newID, wert);
		}
		return newID;
	}
	
	public int getAnzahlKIs() {
		return anzahlKIs;
	}
	
	public void setBesterZug(String[] besterZug) {
		this.besterZug = besterZug;
	}
	
	
	public String[] getBesterZug() {
		return this.besterZug;
	}
	
	public int getGegnerFigVorZug() {
		return gegnerFigVorZug;
	}
	
	public void setGegnerFigVorZug(int gegnerFigVorZug) {
		this.gegnerFigVorZug = gegnerFigVorZug;
	}
	
	private int getFeldStaerkeById(String id) {
		return werteMap.get(id);
	}
	
	public int calcStaerkeDesBretts(Spielbrett brettNachZug) {
		int score = bewerteFigurPos(brettNachZug);
		score += bewerteZusammenhalt(brettNachZug);
		if(isGegnerGeschlagen(brettNachZug)) {
			score += 50;
		}
		return score;
	}
	
	private int bewerteFigurPos(Spielbrett spielbrett) {
		int score = 0;
		
		for(String id : spielbrett.getFelderMitFarbe(this.getFarbe())) {
			score += getFeldStaerkeById(id);
		}
		return score;
	}
	
	private int bewerteZusammenhalt(Spielbrett spielbrett) {
		int score = 0;
		
		for(String id : spielbrett.getFelderMitFarbe(this.getFarbe())) {
			for(String nachbarId : spielbrett.getNachbarnByIdVonFeld(id)) {
				if(nachbarId != null) {
					score++;
				}
			}
		}
		
		return score;
	}
	
	private boolean isGegnerGeschlagen(Spielbrett vorZug) {
		int gegnerFigNachZug = 0;
		
		if(this.getFarbe() == FarbEnum.SCHWARZ) {
			gegnerFigNachZug = vorZug.getFelderMitFarbe(FarbEnum.WEISS).size();
		}
		else {
			gegnerFigNachZug = vorZug.getFelderMitFarbe(FarbEnum.SCHWARZ).size();
		}
		if(gegnerFigNachZug > gegnerFigVorZug) {
			return true;
		}
		return false;
		
		
	}

}
