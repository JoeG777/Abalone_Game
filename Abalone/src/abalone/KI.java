package abalone;

import java.util.ArrayList;

import abalone.spielbrett.Spielbrett;

public class KI extends Spieler {

	private static final long serialVersionUID = 110L;
	private static int anzahlKIs = 0;

	public KI(FarbEnum farbe) {
		super("KEVIN", farbe);
		anzahlKIs++;
	}

	public int getAnzahlKIs() {
		return anzahlKIs;
	}

	public String[] randomZiehen(Spiel spiel, FarbEnum farbe) {

		ArrayList<Spielzug> alleMoeglichenZuege = spiel.getAlleMoeglichenZuege(this);
		int random = (int) (Math.random() * alleMoeglichenZuege.size());
		String[] randomZug = {alleMoeglichenZuege.get(random).getVon(),
				alleMoeglichenZuege.get(random).getNach()};
		
		return randomZug;
	}
	
	public class Node{
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
