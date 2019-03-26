package abalone;

import java.io.IOException;
import java.util.Scanner;
import jline.console.ConsoleReader;

public class UI {

	public static void main (String[] args) {
		Spiel spiel = new Spiel();
		welcomeScreen();
		spielerAnlegen(spiel);
		spielen(spiel);
	}
	/**
	 * Methode um das Spiel zu starten
	 */
	public static void spielerAnlegen(Spiel spiel) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Gibt den Namen f�r den Spieler mit der Farbe Weiss ein:");

		String name = sc.nextLine();
		try {
			spiel.addSpieler(name, "weiss");
		}catch(IllegalArgumentException e) {
			System.out.println("Unzul�ssige eingabe, bitte benutze WEISS f�r Wei� und SCHWARZ f�r Schwarz)");
		}
		System.out.println("Spieler angelegt. Nun gib den Namen f�r den Spieler mit der Farbe Schwarz ein:");
		name = sc.nextLine();
		try {
			spiel.addSpieler(name, "schwarz");
		}catch(IllegalArgumentException e) {
			System.out.println("Unzul�ssige eingabe, bitte benutze WEISS f�r Wei� und SCHWARZ f�r Schwarz)");
		}
	}
//Eine Aenderung als Beweis.
	public static void welcomeScreen() {
		Scanner sc = new Scanner(System.in);
		System.out.println("#    ___  _           _                  \r\n" + 
				"#   / _ \\| |         | |                 \r\n" + 
				"#  / /_\\ \\ |__   __ _| | ___  _ __   ___ \r\n" + 
				"#  |  _  | '_ \\ / _` | |/ _ \\| '_ \\ / _ \\\r\n" + 
				"#  | | | | |_) | (_| | | (_) | | | |  __/\r\n" + 
				"#  \\_| |_/_.__/ \\__,_|_|\\___/|_| |_|\\___|\r\n" + 
				"#                                        \r\n" + 
				"#                                        \n" + 
				"Dr�cke Enter zum Starten");
		String start = sc.nextLine();
		try {
			clearConsole();
		}catch(IOException e) {
			
		}
	}
	
	public final static void clearConsole() throws IOException {
		String penguinClearConsole = "clear";
		String windowsClearConsole = "cls";
		 
		String os = System.getProperty("os.name");

		if(os.startsWith("Windows")){
			Runtime.getRuntime().exec(penguinClearConsole);
		}
		else if(os.startsWith("Linux")){
			Runtime.getRuntime().exec(windowsClearConsole);
		}
	}
	public static void spielen(Spiel spiel) {
		Scanner sc = new Scanner(System.in);
		boolean imSpiel = true;
		while(imSpiel) {
			System.out.println(spiel.getStatus());
			String zug = sc.nextLine();
			if(!ziehen(zug, spiel))
				System.out.println("Irgendwas hat da nicht gestimmt");
			imSpiel = !spiel.hatGewonnen(spiel.getSpielerAmZug());
		}

	}
	
	public static boolean ziehen(String zug, Spiel spiel) {

		String[] zugArr = new String[2];
		System.out.println(zug.length());
		try {
			if(zug.length()< 5 ) {
				return false;
			}
			if(zug.length() >= 5) {
				zugArr[0] = zug.substring(0,2);
				zugArr[1] = zug.substring(3,5);
			}
			if(zug.length() >= 7) {
				zugArr[0] = zug.substring(0,4);
				zugArr[1] = zug.substring(5,7);
			}
			try {
				spiel.ziehe(zugArr);
			}catch(IllegalArgumentException e ) {
				return false;
			}
		}catch (StringIndexOutOfBoundsException e) {

			return false;
		}
		return true;
	}
	
//	public static boolean ziehen1(String zug, Spiel spiel) {
//		String[] zugArr = new String[2];
//		System.out.println(zug.length());
//		try {
//		if(zug.length()< 5 ) {
//			return false;
//		}
//		if(zug.length() >= 5) {
//			zugArr[0] = zug.substring(0,2);
//			zugArr[1] = zug.substring(3,5);
//		}
//		if(zug.length() >= 7) {
//			zugArr[0] = zug.substring(0,4);
//			zugArr[1] = zug.substring(5,7);
//		}
//		try {
//			spiel.ziehe(zugArr);
//		}catch(IllegalArgumentException e ) {
//			return false;
//		}
//		}catch (StringIndexOutOfBoundsException e) {
//			return false;
//		}
//		return true;
//	}
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


