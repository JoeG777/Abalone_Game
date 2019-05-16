package gui;

import abalone.Spiel;
import abalone.SpielException;
import abalone.bedienerInterface;
import abalone.spielbrett.SpielfeldException;

public class Main {
	
	public static void main(String[] args) throws SpielfeldException {
		try {
			Controller c = new Controller();
		} catch (SpielException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
