package abalone;


import java.util.HashMap;
import java.util.Map;

import abalone.spielbrett.Spielbrett;
import abalone.spielbrett.SpielfeldException;

public class KISchwer extends KI {
	private Spielbrett simulationsbrett;
	private HashMap<String, Integer> felderStaerke;
	private static final long serialVersionUID = 111L;

	public KISchwer(Spiel spiel, FarbEnum farbe) throws SpielfeldException {
		super(farbe);
		simulationsbrett = spiel.getSpielbrett();
		felderStaerke = new HashMap<String, Integer>();

	}


	private int calcFeldstaerke(String id) {
		int feldstaerke = 0;
		return 0;
	}

	private int manhattanDistanceZurMitte(String id) {
		return 0;
	}

	private boolean aufDiagonale(String id) {
		char buchstabe = id.charAt(0);
		int zahl = Character.getNumericValue(id.charAt(1));

		if(buchstabe == 'E' || zahl == '5' || buchstabe - 64 == zahl) {
			return true;
		}

		return false;
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
