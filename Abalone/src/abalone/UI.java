package abalone;

import java.util.Scanner;

public class UI {
	
	public static void main (String[] args) {
		spielStarten();
	}
	/**
	 * Methode um das Spiel zu starten
	 */
	public static void spielStarten() {
		Scanner sc = new Scanner(System.in);
		Spiel s1 = new Spiel();
		System.out.println("Fuege Spieler 1 hinzu! (Name,Farbe)");
		String[] woerter = sc.nextLine().split(",");
		
		s1.addSpieler(woerter[0], woerter[1]);
	}
	
	/**
	 * Methode um das Spiel zu beenden
	 */
	public static void spielBeenden() {
		
	}
	
	/**
	 * Methode um einen alten Spielstand zu laden
	 */
	public static void spielLaden() {
		
	}
	
	/**
	 * Methode um (einen) Spieler hinzuzufuegen
	 */
	public static void spielerAnmelden() {
		
	}
}

	
