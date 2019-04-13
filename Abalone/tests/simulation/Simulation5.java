package simulation;

import java.util.Scanner;

import abalone.Spiel;

public class Simulation5 {

	public static void main(String[] args) {
		Spiel s = new Spiel();
		Scanner sc = new Scanner(System.in);
		String[][] zugSimulation = {
				{"I7G5","F4"},{"A4C4","D4"},
				{"I8G6","F5"},{"A3C3","D3"},
				{"H5H4","G4"},{"A2B2","C2"},
				{"I6I5","H5"},{"B3D3","E3"},
				{"H6F4","E3"},{"D4B4","E4"},
				{"G5E3","D2"},{"A5C5","D5"},
				{"D2F4","C1"},{"B2C2","D2"},
				{"I9G7","F6"},{"C3D3","E3"},
				{"H8F6","E5"},{"C2D2","E2"},
				{"G7E5","D4"},{"C3E3","F3"},
				{"F6D4","C3"},{"D3F3","G3"},
				{"E5C3","B2"},{"D2E2","F2"},
				{"D4B2","A1"},{"B5D5","E5"},
				{"B2C3","D3"},{"C5E5","F5"},
				{"C2D3","B1"},{"D5F5","G5"},
				{"H9","I9"},{"E5G5","H5"},
				{"I9","I8"},{"F5H5","I5"},
				{"I8G6","F5"},{"I5","I6"},
				{"H7F5","E4"},{"F2G3","H4"},
				{"E4","F4"},{"G3H4","I5"} };
			
		
				
		s.addSpieler("spieler1", "weiss",2);
		s.addSpieler("spieler2", "schwarz",2);
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

