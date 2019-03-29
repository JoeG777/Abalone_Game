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
	 * Diese Methode fragt den Namen und die Farbe der Spieler ab, um diese Parameter
	 * an die addSpieler Methode zu Uebergeben.
	 * 
	 * @param spiel Das Spielobjekt
	 */
	public static void spielerAnlegen(Spiel spiel) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Gibt den Namen für den Spieler mit der Farbe Weiss ein:");

		String name = sc.nextLine();
		try {
			spiel.addSpieler(name, "weiss");
		}catch(IllegalArgumentException e) {
			System.out.println("Unzulüssige eingabe, bitte benutze WEISS für Weiss und SCHWARZ für Schwarz)");
		}
		System.out.println("Spieler angelegt. Nun gib den Namen für den Spieler mit der Farbe Schwarz ein:");
		name = sc.nextLine();
		try {
			spiel.addSpieler(name, "schwarz");
		}catch(IllegalArgumentException e) {
			System.out.println("Unzulässige eingabe, bitte benutze WEISS für Weiss und SCHWARZ für Schwarz)");
		}
	}
	/**
	 * Diese Methode zeigt den Willkommensscreen fuer Abalone.
	 */
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
				"Drücke Enter zum Starten");
		String start = sc.nextLine();
		try {
			clearConsole();
		}catch(IOException e) {
			
		}
	}
	/**
	 * Diese Methode loescht den Inhalt der Console.
	 * 
	 * @throws IOException
	 */
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
	/**
	 * Diese Methode startet das Spiel und uebergibt der ziehen Methode immer wieder
	 * den vom Benutzer eingegebenen Zug bis einer der Spieler das Spiel gewonnen hat. 
	 * 
	 * @param spiel Das Spiel Objekt
	 */
	public static void spielen(Spiel spiel) {
		Scanner sc = new Scanner(System.in);
		boolean imSpiel = true;
		while(imSpiel) {
			System.out.println(spiel.getStatus());
			System.out.println(">");
			String zug = sc.nextLine();
			if(!ziehen(zug, spiel)) {
				System.out.println("Irgendwas hat da nicht gestimmt");
				System.out.println();
			}
			imSpiel = !spiel.hatGewonnen(spiel.getSpielerAmZug());
		}

	}
	/**
	 * Diese Methode prueft ob die Zug-Eingabe des Benutzers korrekt war und gibt
	 * den Zug dann an die ziehe Methode weiter.
	 * 
	 * @param zug Der vom Benutzer eingegebene Zug.
	 * @param spiel Das Spielobjekt
	 * @return boolean Ob der Zug korrekt war
	 */
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

}


