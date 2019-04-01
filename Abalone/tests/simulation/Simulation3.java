package simulation;

import java.util.Scanner;

import abalone.Spiel;

public class Simulation3 {

	public static void main(String[] args) {
		Spiel s = new Spiel();
		Scanner sc = new Scanner(System.in);
		String[][] zugSimulation = {
				{"G5G7","F5"},{"A5C5","D5"},
				{"H6H8","G5"},{"A1C3","D4"},
				{"I9H9","G8"},{"B1B2","C1"},
				{"I7I8","H7"},{"A3C5","D6"},
				{"I6H5","G4"},{"A4B5","C6"},
				{"H4G4","F4"},{"B5D5","E5"},
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

