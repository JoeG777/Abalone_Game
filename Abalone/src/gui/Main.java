package gui;

import abalone.Spiel;
import abalone.bedienerInterface;
import abalone.spielbrett.SpielfeldException;

public class Main {
	
	public static void main(String[] args) throws SpielfeldException {
		bedienerInterface spiel = new Spiel();
		Hauptfenster window = new Hauptfenster();
	}
}
