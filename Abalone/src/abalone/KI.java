package abalone;

import java.util.ArrayList;

import abalone.spielbrett.Spielbrett;
import abalone.spielbrett.SpielfeldException;

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
		String[] randomZug = new String[2];
		try {
			ArrayList<Spielzug> alleMoeglichenZuege = spiel.getAlleMoeglichenZuege(this);
			int random = (int) (Math.random() * alleMoeglichenZuege.size());
			randomZug[0] = alleMoeglichenZuege.get(random).getVon();
			randomZug[0] = 	alleMoeglichenZuege.get(random).getNach();
		}catch(AbaloneException e) {}
		
		return randomZug;
	}

}
