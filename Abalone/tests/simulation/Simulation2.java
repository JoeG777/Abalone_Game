package simulation;

import java.util.Scanner;

import abalone.Spiel;

public class Simulation2 {

	public static void main(String[] args) {
		Spiel s = new Spiel();
		Scanner sc = new Scanner(System.in);
		String[][] zugSimulation = {
				{"I7G5","F4"},{"A4C4","D4"},
				{"I8G6","F5"},{"A3C3","D3"},
				{"H4H5","G4"},{"A2B2","C2"},
				{"I5I6","H5"},{"B3D3","E3"},
				{"H6F4","E3"},{"D4B4","E4"},
				{"G5E3","D2"},{"A5C5","D5"},
				{"F4D2","C1"},{"B2C2","D2"},
				 };
			
		
				
		s.addSpieler("spieler1", "weiss");
		s.addSpieler("spieler2", "schwarz");
		System.out.println(s.getStatus());
		
		for (String zug[] : zugSimulation) {
			s.ziehe(zug);
			System.out.println(s.getStatus());
			
			System.out.println("Drücke ENTER zum fortfahren...");
			sc.nextLine();
		}
		System.out.println("Ende der Simultaion");
		sc.close();
	}

}

