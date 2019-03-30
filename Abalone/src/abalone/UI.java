package abalone;

import java.io.IOException;
import java.util.Scanner;


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
		System.out.print(">");
		String name = sc.nextLine();
		while(name.length() > 20) {
			System.out.println("Bitte geben Sie einen Namen mit weniger als 20 Zeichen an!");
			System.out.print(">");
			name = sc.nextLine();
		}
		try {
			spiel.addSpieler(name, "weiss");
		}catch(IllegalArgumentException e) {
			System.out.println("Unzulüssige eingabe, bitte benutze WEISS für Weiss und SCHWARZ für Schwarz)");
		}
		System.out.println("Spieler angelegt. Nun gib den Namen für den Spieler mit der Farbe Schwarz ein:");
		System.out.print(">");
		name = sc.nextLine();
		while(name.length() > 20) {
			System.out.println("Bitte geben Sie einen Namen mit weniger als 20 Zeichen an!");
			System.out.print(">");
			name = sc.nextLine();
		}
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
		System.out.println("###############################################\r\n" + 
						   "#    ___  _           _                       #\r\n" + 
						   "#   / _ \\| |         | |                      #\r\n" + 
						   "#  / /_\\ \\ |__   __ _| | ___  _ __   ___      #\r\n" + 
						   "#  |  _  | '_ \\ / _` | |/ _ \\| '_ \\ / _ \\     #\r\n" + 
						   "#  | | | | |_) | (_| | | (_) | | | |  __/     #\r\n" + 
						   "#  \\_| |_/_.__/ \\__,_|_|\\___/|_| |_|\\___|     #\r\n" + 
						   "#                                             #\r\n" + 
						   "###############################################\n" + 
						   "########### Drücke Enter zum Starten ##########\n" +
						   "###############################################" );
		String start = sc.nextLine();
		try {
			clearConsole();
		}catch(IOException e) {
			
		}
	}
	
	/**
	 * Diese Methode ist ein Hilfsmenue fuer das Regelwerk und die Bedienung von Abalone.
	 */
	public static void hilfsMenu() {
		Scanner sc = new Scanner(System.in);
		boolean inSchleifeBleiben = true;
		System.out.println("###############################################\r\n" +
						   "################# HilfsMenue ##################\r\n" +
						   "###############################################\r\n" +
						   "#### 1. Regeln ########### 2. Spielablauf #####\r\n" +
						   "###############################################\r\n" +
						   "### Geben Sie die Nummer oder den Namen des ###\r\n" +
						   "##### Artikels ein den Sie lesen möchten. #####\r\n" +
						   "###############################################\r\n" );
		String eingabe = sc.nextLine();
		while(inSchleifeBleiben) {
			
			if(eingabe.equalsIgnoreCase("regeln") || eingabe.equals("1") || eingabe.contentEquals("1.")) {
				System.out.println("");
				System.out.println("\r\n" +
							   "Abalone wird auf einem sechseckingen Spielfeld mit 61 Feldern gespielt.\r\n" +
							   "Jeder Spieler hat 14 Kugeln und muss versuchen durch taktisch kluges verschieben\r\n"+
							   "von einer, zwei oder drei eigenen Kugeln die gegnerischen Kugeln vom Spielfeld zu schieben.\r\n"+
							   "Der Spieler, der zuerst sechs gegnerische Kugeln verdraengt hat, hat gewonnen.\r\n"+
							   "\r\n"+
							   "Man kann die Kugeln in alle sechs angrenzenden Luecken verschieben.\r\n"+
							   "Es ist moeglich eine, zwei, oder drei Kugeln zu bewegen, solange die Kugeln\r\n"+
							   "in eine Richtung (auch diagonal) bewegt werden und alle angrenzenden Luecken frei sind\r\n"+
							   "Kugeln koennen nur eine Luecke pro Zug verschoben werden.\r\n"+
							   "\r\n"+
							   "Ist das Feld durch eine gegnerische Kugel besetzt, kann diese nur durch mehrere\r\n"+
							   "eigene Kugeln in einer Linie von dort verdraengt werden, wenn sich dahinter ein\r\n"+
							   "freies Feld befindet oder die gegnerische Kugel vom Spielbrett geschoben wird\r\n"+
							   "\r\n"+
							   "Das herunterschieben einer gegnerischen Kugel wird Sumito genannt. Es gibt drei\r\n"+
							   "verschiedene Sumitos:"+
							   "2-1 Sumito: zwei eigene Kugeln schieben eine gegnerische\r\n"+
							   "3-1 Sumito: drei eigene Kugeln schieben eine gegnerische\r\n"+
							   "3-2 Sumito: drei eigene Kugeln schieben zwei gegnerische\r\n"+
							   "\r\n"+
							   "Hat der Gegner drei oder mehr Kugeln in einer Reihe stehen, so koennen diese"+
							   "nicht verschoben werden.");
		}
			
			else if(eingabe.equalsIgnoreCase("spielablauf") || eingabe.equals("2") || eingabe.contentEquals("2.")) {
				System.out.println("");
				System.out.println("\r\n" +
							   "Wie beim Schach hat Abalone ein Notationssystem für Zuege. Hierbei werden die Querlinien mit Buchstaben von A bis I\r\n"+
							   "(die unterste Grundlinie ist A) bezeichnet und die Diagonalen von 1 bis 9 (die linke schwarze Diagonale ist 1)\r\n"+
							   "\r\n"+
							   "Ein Zug wird jeweils mit dem Anfangs-und Endpunkt des letzten schiebenden Steines bezeichnet.\r\n"+
							   "Ein diagonaler Zug wird mit den Anfangspositionen der beiden aeußersten Steine bezeichnet und \r\n"+
							   "mit der Endposition eines dieser beiden aeußersten Steine.\r\n"+
							   "Beispiele dafür waeren: >C3C5-D3< oder >G6I8-F5<\r\n");
		}
			else {
				System.out.println("");
				System.out.println("Bitte halten Sie sich an die schreibweise und geben Sie entweder die Nummer oder den Namen des Artikels ein den Sie lesen moechten. ");
			}
			
			System.out.println("");
			System.out.println("Wenn Sie einen anderen Artikel lesen moechten, so geben Sie bitte seine Nummer oder seinen Namen ein.\r\n"+
							   "Wenn Sie zurueck zum Spiel mochten, so geben sie bitte Back oder Zurueck ein.\r\n");
			eingabe=sc.nextLine();
			if(eingabe.equalsIgnoreCase("back") || eingabe.equalsIgnoreCase("zuruck") || eingabe.equalsIgnoreCase("zurueck")) {
				inSchleifeBleiben = false;
			}
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
		String gewinner = "";
		String verlierer = "";
		boolean imSpiel = true;
		while(imSpiel) {
			System.out.println();
			System.out.println(spiel.getStatus());
			System.out.print(">");
			String zug = sc.nextLine();
			if(zug.equalsIgnoreCase("hilfe")) {
				hilfsMenu();
			}
			if(!ziehen(zug, spiel)) {
				System.out.println("Irgendwas hat da nicht gestimmt!");
				System.out.println();
			}
			imSpiel = !spiel.hatGewonnen(spiel.getSpielerAmZug());
			if (!imSpiel) {
				gewinner = spiel.getSpielerAmZug();
				for(Spieler s1 : spiel.getSpielerImSpiel()) {
					if (!s1.getName().equals(gewinner)) {
						verlierer = s1.getName();
					}
				}
			}
		}
		spielBeenden(gewinner, verlierer);
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
	 * Diese Methode gibt den Gewinner aus.
	 * @param gewinner Der Spieler der das Spiel gewonnen hat.
	 */
	public static void spielBeenden(String gewinner, String verlierer) {
		System.out.println("Hurraa " + gewinner + " Hat das Spiel gewonnen!");
		System.out.println("Verlierer der heutigen Runde ist: " + verlierer + "!");
	}
}


