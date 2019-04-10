package abalone;

import java.util.ArrayList;

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

	public String randomZiehen(Spiel spiel, FarbEnum farbe) {

		ArrayList<Spielzug> alleMoeglichenZuege = spiel.getAlleMoeglichenZuege(this);
		int x = alleMoeglichenZuege.size();
		int random = (int) (Math.random() * x);
		int counter = 0;
		String randomZug = "";

			for (Spielzug zug : alleMoeglichenZuege) {
				if (counter == random) {
					randomZug = zug.getVon() + zug.getNach();
				}
				counter++;
			}
		
		return randomZug;
	}
}
