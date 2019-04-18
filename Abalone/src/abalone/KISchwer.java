package abalone;


import java.util.HashMap;
import java.util.Map;

import abalone.spielbrett.Spielbrett;
import abalone.spielbrett.SpielfeldException;

public class KISchwer extends KI {
	private Spielbrett simulationsbrett;
	public HashMap<String, Integer> felderStaerke;
	private static final long serialVersionUID = 111L;

	public KISchwer(Spiel spiel, FarbEnum farbe) throws SpielfeldException {
		super(farbe);
		simulationsbrett = spiel.getSpielbrett();
		felderStaerke = new HashMap<String, Integer>();
		initFelderStaerke();

	}


	private void initFelderStaerke() {
		boolean done = false;
		int schritte = 1;
		String aktuellesFeld = "E5";

		while(!done)  {
			aktuellesFeld = laufeUndBewerte(aktuellesFeld, 4, 1, schritte);
			aktuellesFeld = laufeUndBewerte(aktuellesFeld, 3, schritte-1, schritte);
			aktuellesFeld = laufeUndBewerte(aktuellesFeld, 5, schritte, schritte);
			aktuellesFeld = laufeUndBewerte(aktuellesFeld, 2, schritte, schritte);
			aktuellesFeld = laufeUndBewerte(aktuellesFeld, 0, schritte, schritte);
			aktuellesFeld = laufeUndBewerte(aktuellesFeld, 1, schritte, schritte);
			aktuellesFeld = laufeUndBewerte(aktuellesFeld, 4, schritte, schritte);

			schritte++;

			if(schritte == 5) {
				break;
			}

		}
	}

	private String laufeUndBewerte(String id, int richtung, int schritte, int wert) {
		String newID = id;
		for(int i = 0; i < schritte; i++) {
			newID = simulationsbrett.getNachbarByIdInRichtung(newID, richtung);
			felderStaerke.put(newID, wert);
		}
		return newID;
	}
	private int calcFeldstaerke(String id) {
		int feldstaerke = 0;
		return 0;
	}

	private int manhattanDistanceZurMitte(String id) {
		return 0;
	}



	private class Node{
		boolean isRoot;
		Node[] subNodes;
		int value;
		Spielbrett brett;
		Spielzug zug;

		public Node(boolean isRoot, Spielbrett brett, Spielzug zug) {
			this.isRoot = isRoot;
			this.brett = brett;
		}

		public void generateSubNodes(Spielzug[] zuege) {
			subNodes = new Node[zuege.length];
			for(int i = 0; i<zuege.length; i++) {
				subNodes[i] = new Node(false, this.brett, zuege[i]);
			}
		}

		public void computeValue() {

		}

	}



}
