package abalone;

import java.util.ArrayList;

import abalone.spielbrett.SpielbrettException;

public class KIEinfach extends KI {
	
	public KIEinfach(FarbEnum farbe) {
		super(farbe);
	}
	
	public String[] randomZiehen(Spiel spiel) {
		String[] randomZug = new String[2];

		ArrayList<Spielzug> alleMoeglichenZuege = spiel.getAlleMoeglichenZuege(this.getFarbe());
		int random = (int) (Math.random() * alleMoeglichenZuege.size());
		randomZug[0] = alleMoeglichenZuege.get(random).getVon();
		randomZug[1] = 	alleMoeglichenZuege.get(random).getNach();


		return randomZug;
	}
}
