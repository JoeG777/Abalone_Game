package abalone;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import abalone.spielbrett.Spielbrett;
import abalone.spielbrett.SpielbrettException;
import abalone.spielbrett.SpielfeldException;

public class KISchwer extends KI {
	private Spielbrett simulationsbrett;
	public HashMap<String, Integer> felderStaerke;
	private static final long serialVersionUID = 111L;

	public KISchwer(Spiel spiel, FarbEnum farbe) {
		super(farbe);
		simulationsbrett = spiel.getSpielbrett().clone();
		felderStaerke = new HashMap<String, Integer>();
		initFelderStaerke();

	}
	
	

	private void initFelderStaerke() {
		boolean done = false;
		int schritte = 1;
		String aktuellesFeld = "E5";
		int wert = 12;
		felderStaerke.put("E5", 15);
		
		while(!done)  {
			aktuellesFeld = laufeUndBewerte(aktuellesFeld, 4, 1, wert-schritte);
			aktuellesFeld = laufeUndBewerte(aktuellesFeld, 3, schritte-1, wert-schritte);
			aktuellesFeld = laufeUndBewerte(aktuellesFeld, 5, schritte, wert-schritte);
			aktuellesFeld = laufeUndBewerte(aktuellesFeld, 2, schritte, wert-schritte);
			aktuellesFeld = laufeUndBewerte(aktuellesFeld, 0, schritte, wert-schritte);
			aktuellesFeld = laufeUndBewerte(aktuellesFeld, 1, schritte, wert-schritte);
			aktuellesFeld = laufeUndBewerte(aktuellesFeld, 4, schritte, wert-schritte);

			schritte++;

			if(schritte == 5) {
				done = true;
			}

		}
		
		
	}
	
	private int getFeldStaerkeById(String id) {
		return felderStaerke.get(id);
	}

	private String laufeUndBewerte(String id, int richtung, int schritte, int wert) {
		String newID = id;
		for(int i = 0; i < schritte; i++) {
			newID = simulationsbrett.getNachbarByIdInRichtung(newID, richtung);
			felderStaerke.put(newID, wert);
		}
		return newID;
	}
	
	private int rateSpielbrett() {
		int score = 0;
		
		for(String id : simulationsbrett.getFelderMitFarbe(this.getFarbe())) {
			score += getFeldStaerkeById(id);
		}
		
	
		return score;
	}
	
	private boolean isGegnerGeschlagen(Spielbrett vorZug) {
		int gegnerFelder = 0;
		int gegnerFelderVorZug =0;
		if(this.getFarbe() == FarbEnum.SCHWARZ) {
			gegnerFelder = simulationsbrett.getFelderMitFarbe(FarbEnum.WEISS).size();
			gegnerFelderVorZug = vorZug.getFelderMitFarbe(FarbEnum.WEISS).size();
		}
		else {
		gegnerFelder = simulationsbrett.getFelderMitFarbe(FarbEnum.SCHWARZ).size();
		gegnerFelderVorZug = vorZug.getFelderMitFarbe(FarbEnum.WEISS).size();
		}

		if(gegnerFelderVorZug > gegnerFelder) {
			return true;
		}
		
		return false;
		
		
	}

	public String[] getBesterZug(Spiel spiel) {
		simulationsbrett = spiel.getSpielbrett().clone();
		String[] besterZug = new String[2];
		int max = 0;
		
		ArrayList<Spielzug> moeglicheZuege = new ArrayList<Spielzug>();
		moeglicheZuege = spiel.getAlleMoeglichenZuege(this.getFarbe());
		
		for(Spielzug zug : moeglicheZuege) {
			Spielbrett testbrett = simulationsbrett.clone();
			System.out.println(zug.getVon() + " --- " + zug.getNach());
			try {
				simulationsbrett.ziehe(spiel.spielzugSplitter(zug));
			} catch (SpielbrettException e) {
				e.printStackTrace();
			}
			
			int score = rateSpielbrett();
			if(isGegnerGeschlagen(testbrett)) {
				score += 50;
			}
			if(score > max) {
				max = score;
				besterZug[0] = zug.getVon();
				besterZug[1] = zug.getNach();
			}
			score = 0;
			simulationsbrett = testbrett;
		}
		return besterZug;
	}





	private class Node{
		boolean isRoot;
		Node[] subNodes;
		int value;
		Spielbrett brett;
		Spielzug zug;

		public Node(boolean isRoot, Spielbrett brett) {
			this.isRoot = isRoot;
			this.brett = brett;
		}
		
		public Node(boolean isRoot, Spielbrett brett, Spielzug zug) {
			this.isRoot = isRoot;
			this.brett = brett;
		}

		public void generateSubNodes(Spielzug[] zuege) {
			subNodes = new Node[zuege.length];
			for(int i = 0; i<zuege.length; i++) {
				subNodes[i] = new Node(false, this.brett.clone(), zuege[i]);
			}
		}

		public void computeValue() {
			
		}

	}



}
