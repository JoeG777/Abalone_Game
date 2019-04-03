package abalone;

import java.io.IOException;
import java.util.Scanner;

public class UI {
	
	static Scanner sc = new Scanner(System.in);

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

		System.out.println("Gibt den Namen für den Spieler mit der Farbe Weiss ein:");
		System.out.print(">");
		String name = sc.nextLine();
		while(name.length() > 20 || name.length() < 2) {
			System.out.println("Bitte geben Sie einen Namen mit mindestens 2 und weniger als 20 Zeichen an!");
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
		String name2 = sc.nextLine();
		while(name2.equalsIgnoreCase(name)) {
			System.out.println("Bitte geben Sie unterschiedliche Namen für die Spieler ein!");
			name2 = sc.nextLine();
		}
		while(name2.length() > 20 || name2.length() < 2) {
			System.out.println("Bitte geben Sie einen Namen mit mindestens 2 und weniger als 20 Zeichen an!");
			System.out.print(">");
			name2 = sc.nextLine();
		}
		try {
			spiel.addSpieler(name2, "schwarz");
		}catch(IllegalArgumentException e) {
			System.out.println("Unzulässige eingabe, bitte benutze WEISS für Weiss und SCHWARZ für Schwarz)");
		}
	}
	/**
	 * Diese Methode zeigt den Willkommensscreen fuer Abalone.
	 */
	public static void welcomeScreen() {
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

	}
	
	/**
	 * Diese Methode ist ein Hilfsmenue fuer das Regelwerk und die Bedienung von Abalone.
	 * @param spiel Das erstelle Spiel Objekt.
	 */
	public static void hilfsMenu(Spiel spiel) {
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
				hauptMenue(spiel);
			}
		}
		
	}
	/**
	 * Diese Methode startet das Spiel und uebergibt der ziehen Methode immer wieder
	 * den vom Benutzer eingegebenen Zug bis einer der Spieler das Spiel gewonnen hat. 
	 * 
	 * @param spiel Das Spiel Objekt
	 */
	public static void spielen(Spiel spiel) {
		String gewinner = "";
		String verlierer = "";
		boolean imSpiel = true;
		while(imSpiel) {
			System.out.println();
			System.out.println("Geben Sie 'Menu' ein falls Sie ins Hauptmenu wollen"
					+ " und 'exit' falls sie das Spiel abbrechen moechten.");
			System.out.println();
			System.out.println(spiel.getStatus());
				System.out.print(">");
				String eingabe = sc.nextLine();
				if (eingabe.equalsIgnoreCase("menu")) {
					hauptMenue(spiel);
				}
				if (eingabe.equalsIgnoreCase("exit")) {
					System.out.println("Wollen Sie wirklich das Spiel verlassen? (Ja/Nein)");
					eingabe = sc.nextLine();
					if (eingabe.equalsIgnoreCase("Ja")) {
						gewinner = null;
						verlierer = null;
						break;
					}
				}
				if(!ziehen(eingabe, spiel)) {
					System.out.println("Irgendwas hat da nicht gestimmt!");
					System.out.println();
				}
				imSpiel = !spiel.hatGewonnen(spiel.getSpielerAmZug());
				if (!imSpiel) {
					verlierer = spiel.getSpielerAmZug();
					for(Spieler s1 : spiel.getSpielerImSpiel()) {
						if (!s1.getName().equals(verlierer)) {
							gewinner = s1.getName();
						}
					}
				}
		}
		spielBeenden(gewinner, verlierer);
	}
	
	/**
	 * Gibt das Hauptmenue aus, wartet auf eine Eingabe:
	 * 1 fuer getHistorie()
	 * 2 fuer hilfsMenue
	 * 3 fuer weiter spielen
	 * @param spiel Das erstellte Spiel Objekt.
	 */
	public static void hauptMenue(Spiel spiel) {
		boolean inSchleifeBleiben = true;
		
		while(inSchleifeBleiben) {
			System.out.println("Bitte waehlen Sie:\n(1) Historie ausgeben\n" + 
					"(2) Hilfsmenue ausgeben\n(3) weiter spielen.\n");
			String auswahl = sc.nextLine();

		if(auswahl.equals("1")) {
			System.out.println(spiel.getHistorie());
		}
		
		else if (auswahl.equals("2")) {
			hilfsMenu(spiel);
		}

		else if (auswahl.equals("3")) {
			inSchleifeBleiben = false;
			spielen(spiel);
		}
		else {
			System.out.println("Die Tasten befinden sich oben links auf ihrer Tastatur. + \n"
					+ "Falls Sie sich zusätzlich eine Tastatur mit NumPad\n"+
					"geleistet haben finden Sie die Tasten auch rechts.");
			System.out.println();
			}
		}
	}
	/**
	 * Diese Methode prueft ob die Zug-Eingabe des Benutzers korrekt war und gibt
	 * den Zug dann an die ziehe Methode weiter.
	 * 
	 * @param zug Der vom Benutzer eingegebene Zug.
	 * @param spiel Das Spielobjekt.
	 * @return boolean Ob der Zug korrekt war.
	 */
	public static boolean ziehen(String zug, Spiel spiel) {

		String[] zugArr = new String[2];
		try {
			
			if(zug.length() == 4 || zug.length() ==2 ) {
				zugArr[0] = zug;
				zugArr[1] = null;
				printErlaubteZuege(zugArr, spiel);
				return true;
			}
			
			if(zug.length()< 5 || zug.length() > 7) {
				return false;
			}
			
			if(zug.length() == 5) {
				zugArr[0] = zug.substring(0,2);
				zugArr[1] = zug.substring(3,5);
			}
			if(zug.length() == 7) {
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
	/**
	 * Gibt die erlaubten Zuege auf der Konsole aus.
	 * @param zug Ein Spielzug-Objekt mit Nach-Attribut auf null.
	 * @param spiel ein Spiel-Objekt.
	 */
	public static void printErlaubteZuege(String[] zug, Spiel spiel) {
		String[] erlaubteZuege = spiel.getErlaubteZuege(zug);
		System.out.println ("Folgende Zuege sind momentan moeglich:");
		for(String zugString : erlaubteZuege) {
			System.out.println (zugString);
		}
	}
	
	/**
	 * Diese Methode gibt den Gewinner aus.
	 * @param gewinner Der Gewinner des Spiels.
	 * @param verlierer Der Verlierer des Spiels.
	 */
	public static void spielBeenden(String gewinner, String verlierer) {
		if (gewinner != null && verlierer != null) {
		System.out.println("Hurraa " + gewinner + " Hat das Spiel gewonnen!");
		System.out.println("Verlierer der heutigen Runde ist: " + verlierer + "!");
		}
		System.out.println();
		System.out.println();
		System.out.println("###############################################\r\n" + 
				   "#    ___  _           _                       #\r\n" + 
				   "#   / _ \\| |         | |                      #\r\n" + 
				   "#  / /_\\ \\ |__   __ _| | ___  _ __   ___      #\r\n" + 
				   "#  |  _  | '_ \\ / _` | |/ _ \\| '_ \\ / _ \\     #\r\n" + 
				   "#  | | | | |_) | (_| | | (_) | | | |  __/     #\r\n" + 
				   "#  \\_| |_/_.__/ \\__,_|_|\\___/|_| |_|\\___|     #\r\n" + 
				   "#                                             #\r\n" + 
				   "###############################################\n" + 
				   "#################### ENDE #####################\n" +
				   "###############################################" );
	sc.close();
	}
}


