package abalone;

import java.util.ArrayList;

import abalone.spielbrett.Spielbrett;
import abalone.spielbrett.SpielbrettException;
import abalone.spielbrett.SpielfeldException;

public class KI extends Spieler {

	private static final long serialVersionUID = 110L;
	private static int anzahlKIs = 0;
	private static final String[] namen = {"KI-1", "KI-2"};
	public KI(FarbEnum farbe) {
		super(namen[anzahlKIs], farbe);
		
		anzahlKIs++;
	}

	public int getAnzahlKIs() {
		return anzahlKIs;
	}

}
