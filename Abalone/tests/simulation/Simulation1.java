package simulation;

import java.util.Scanner;

import abalone.Spiel;
import abalone.spielbrett.SpielfeldException;

public class Simulation1 {

	public static void main(String[] args) {
		Spiel s = null;
		try {
			s = new Spiel();
		} catch (SpielfeldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner sc = new Scanner(System.in);
		String[][] zugSimulation = {
			{"I9G7", "H8"},{"C3C5", "D3"},
			{"I7G7", "F7"}, {"A1B2", "C3"},
			{"I8G6", "F5"}, {"A4B4", "C4"},
			{"H6F6", "E6"}, {"B3D5", "E6"},
			{"H5F5", "E5"}, {"A3B4", "C5"},
			{"G6F6", "E6"}, {"B5B6", "C7"},
			{"H7", "G6"}, {"A5", "B5"},
			{"G6E6", "D6"}, {"A2", "A3"},//{"B1B2", "C1"},
			{"F6D6", "C6"}};
				
//		try {
//			s.addSpieler("spieler1", "weiss",2);
//		} catch (/*AbaloneException e*/) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			s.addSpieler("spieler2", "schwarz",2);
//		} catch (/*AbaloneException e*/) {
//			// TODO Auto-generated catch block
////			e.printStackTrace();
//		}
//		System.out.println(s.getStatus());
//		
//		for (String zug[] : zugSimulation) {
//			try {
//				s.ziehe(zug);
//			} catch (/*AbaloneException e*/) {
//				// TODO Auto-generated catch block
////				e.printStackTrace();
//			}
//			System.out.println(s.getStatus());
//			
//			System.out.println("Drücke ENTER zum fortfahren...");
//			sc.nextLine();
//		}
		System.out.println("Ende der Simultaion");
		sc.close();
	}

}
