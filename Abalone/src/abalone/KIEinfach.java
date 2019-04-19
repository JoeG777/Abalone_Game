package abalone;

import java.util.ArrayList;

import abalone.spielbrett.SpielbrettException;

public class KIEinfach extends KI {
	
	public KIEinfach(FarbEnum farbe) {
		super(farbe);
	}
	
	public String[] randomZiehen(Spiel spiel) {
		String[] randomZug = new String[2];
		try {
			ArrayList<Spielzug> alleMoeglichenZuege = spiel.getAlleMoeglichenZuege(this);
			int random = (int) (Math.random() * alleMoeglichenZuege.size());
			randomZug[0] = alleMoeglichenZuege.get(random).getVon();
			randomZug[1] = 	alleMoeglichenZuege.get(random).getNach();
		}catch(AbaloneException e) {}
		
		return randomZug;
	}
}
