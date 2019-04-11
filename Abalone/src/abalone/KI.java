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

	public String[] randomZiehen(Spiel spiel, FarbEnum farbe) {

		ArrayList<Spielzug> alleMoeglichenZuege = spiel.getAlleMoeglichenZuege(this);
		int random = (int) (Math.random() * alleMoeglichenZuege.size());
		String[] randomZug = {alleMoeglichenZuege.get(random).getVon(),
				alleMoeglichenZuege.get(random).getNach()};
		
		return randomZug;
	}
}
