package simulation;

import java.util.Scanner;

import abalone.Spiel;
import abalone.SpielException;
import abalone.spielbrett.SpielbrettException;
import abalone.spielbrett.SpielfeldException;

public class Simulation4 {

	public static void main(String[] args) throws SpielbrettException, SpielException {
		Spiel s = new Spiel();
		Scanner sc = new Scanner(System.in);
		String[][] zugSimulation = {
				{"I7G7","F7"},{"A1C3","D4"},
				{"I8G6","F5"},{"A2C4","D5"},
				{"I9G7","F6"},{"B5B6","C7"},
				{"I6H5","G4"},{"B2C3","D3"},
				{"H4G4","F4"},{"A3C5","D6"},
				{"I5","H4"},{"B3","C3"},
				 };
			
		
				
		s.addSpieler("spieler1", "weiss",2);
		s.addSpieler("spieler2", "schwarz",2);
		System.out.println(s.getStatus());
		
		for (String zug[] : zugSimulation) {
			s.ziehe(zug);
			System.out.println(s.getStatus());
			
			System.out.println("Dr�cke ENTER zum fortfahren...");
			sc.nextLine();
		}
		System.out.println("Ende der Simultaion");
		sc.close();
	}

}

