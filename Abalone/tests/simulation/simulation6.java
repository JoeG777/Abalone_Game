package simulation;

import java.util.Scanner;

import abalone.Spiel;
import abalone.SpielException;
import abalone.spielbrett.SpielfeldException;

public class simulation6 {
	public static void main(String[] args) throws SpielfeldException, SpielException {
		Spiel s = new Spiel();
		Scanner sc = new Scanner(System.in);
		String[][] zugSimulation = {
			{"I5H4", "H4"},{"A5C5", "D5"},
			{"H9", "G9"}, {"B5D5", "E5"},
			{"G9", "H9"}, {"C5E5", "F5"},
			{"H9", "G9"}, {"D5F5", "G5"},
			{"G9", "H9"}, {"E5G5", "H5"},
			{"H9", "G9"}, {"F5H5", "G5"},
			{"H4G3", "I5"}, {"H4G3", "I5"},
			{"G6E6", "D6"}, {"A2", "A3"},
			{"F6D6", "C6"}};
				
		s.addSpieler("spieler1", "weiss",2);
		s.addSpieler("spieler2", "schwarz",2);
		System.out.println(s.getStatus());
		
		for (String zug[] : zugSimulation) {
			try {
				s.ziehe(zug);
			}catch(IllegalArgumentException e) {
				System.out.println("!!!!!!!!!!!!! " + zug[0] + " " + zug[1]);
			}
			System.out.println(s.getStatus());
			
			System.out.println("Drücke ENTER zum fortfahren...");
			sc.nextLine();
		}
		System.out.println("Ende der Simultaion");
		sc.close();
	}
}
