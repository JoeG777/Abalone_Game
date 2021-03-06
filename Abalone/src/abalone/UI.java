package abalone;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import abalone.spielbrett.SpielfeldException;

/**
 * <h1>UI</h1> Die UI bietet dem Nutzer eine Oberflaeche um Abalone zu spielen
 * Sie nimmt dessen Eingaben an und uebergibt diese dem Spiel
 * 
 * @author Gruppe A4
 * 
 */
public class UI implements java.io.Serializable {

	private static final long serialVersionUID = 108L;
	static Scanner sc = new Scanner(System.in);
	private static bedienerInterface spiel;

	/**
	 * Die Main Methode legt ein neues Spiel und und fuehrt alle notwendigen
	 * Methoden aus um eine Partie Abalone zu spielen
	 * 
	 * @throws IOException Wenn beim Laden oder Speichern ein Problem auftritt
	 * @throws ClassNotFoundException Wenn beim Laden oder Speichern ein Problem auftritt
	 * @throws FileNotFoundException Wenn beim Laden oder Speichern ein Problem auftritt
	 * @throws SpielfeldException Wenn beim Laden oder Speichern ein Problem auftritt
	 * @param args Das Default Parameter der Main Methode
	 */
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException, SpielfeldException {
		spiel = new Spiel();
		welcomeScreen();
		hauptMenue();
		sc.close();
	}

	/**
	 * Diese Methode fragt den Namen und die Farbe der Spieler ab, um diese
	 * Parameter an die addSpieler Methode zu Uebergeben.
	 * 
	 * @param anzahlSpieler Die Anzahl der menschlichen Spieler
	 */
	public static void spielerAnlegen(int anzahlSpieler) {
		if (anzahlSpieler == 2) {
			addWeiss(2);
			System.out.println("Spieler angelegt.");

			addSchwarz(2);
			System.out.println("Spieler angelegt.");

			
		}
		if (anzahlSpieler == 1) {
			addWeiss(1);
			System.out.println("Spieler angelegt.");

		}
		if (anzahlSpieler == 0) {
			try {
				spiel.addSpieler(null, null, anzahlSpieler);
			}catch(SpielException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * UI-Methode fuer das Hinzufuegen eines Spielers
	 * @param anzahl Anzahl der Spieler
	 */
	public static void addWeiss(int anzahl){
		System.out.println("\nGeben Sie den Namen fuer den Spieler mit der Farbe Weiss ein:");
		System.out.print("> ");
		String name = sc.nextLine();
		try {
			spiel.addSpieler(name, "weiss",anzahl);
		} catch (SpielException e) {
			if(e.getId() == 14) System.out.println("Ungueltige Laenge!");
			if(e.getId() == 11) System.out.println("Es sind bereits 2 Spieler im Spiel!");
			if(e.getId() == 13) System.out.println("Der Spieler mit diesem Namen existiert bereits!");
			if(e.getId() == 18) System.out.println("Spielername darf keine Sonderzeichen ausser _ enthalten!");
			if(e.getId() == 19) System.out.println("Spielername darf nicht mit \"KI\" beginnen!");
			addWeiss(anzahl);
		}
	}
	
	/**
	 * UI-Methode fuer das Hinzufuegen eines Spielers
	 * @param anzahl Anzahl der Spieler
	 */
	public static void addSchwarz(int anzahl) {
		System.out.println("\nGeben Sie den Namen fuer den Spieler mit der Farbe Schwarz ein:");
		System.out.print("> ");
		String name = sc.nextLine();

		try {
			spiel.addSpieler(name, "schwarz",anzahl);
		} catch (SpielException e) {
			if(e.getId() == 14) System.out.println("Ungueltige Laenge!");
			if(e.getId() == 11) System.out.println("Es sind bereits 2 Spieler im Spiel!");
			if(e.getId() == 13) System.out.println("Der Spieler mit diesem Namen existiert bereits!");
			if(e.getId() == 18) System.out.println("Spielername darf keine Sonderzeichen ausser _ enthalten!");
			if(e.getId() == 19) System.out.println("Spielername darf nicht mit \"KI\" beginnen!");
			addSchwarz(anzahl);
		}
	}

	/**
	 * Diese Methode zeigt den Willkommensscreen fuer Abalone.
	 */
	public static void welcomeScreen() {
		System.out.println("###############################################\r\n"
				+ "#    ___  _           _                       #\r\n"
				+ "#   / _ \\| |         | |                      #\r\n"
				+ "#  / /_\\ \\ |__   __ _| | ___  _ __   ___      #\r\n"
				+ "#  |  _  | '_ \\ / _` | |/ _ \\| '_ \\ / _ \\     #\r\n"
				+ "#  | | | | |_) | (_| | | (_) | | | |  __/     #\r\n"
				+ "#  \\_| |_/_.__/ \\__,_|_|\\___/|_| |_|\\___|     #\r\n"
				+ "#                                             #\r\n"
				+ "###############################################\n"
				+ "########## Druecke Enter zum Starten ##########\n"
				+ "###############################################");
		sc.nextLine();

	}

	/**
	 * Das Hauptmenue der graphischen Oberflaeche
	 * @throws FileNotFoundException Wenn ein Problem beim Speichern oder Laden auftritt
	 * @throws ClassNotFoundException Wenn ein Problem beim Speichern oder Laden auftritt
	 * @throws IOException Wenn ein Problem beim Speichern oder Laden auftritt
	 */
	public static void hauptMenue()
			throws FileNotFoundException, ClassNotFoundException, IOException {
		boolean imSpiel = true;

		while (imSpiel) {

		System.out.println("\nBitte waehlen Sie welches Spiel Sie starten wollen!");
		System.out.println();
		System.out.print("(1) 2 Spieler \n(2) 1 Spieler + 1 KI \n(3) 2 KIs \n(4) Spiel laden\n\n> ");
		String eingabe = sc.nextLine();
			
		if (eingabe.equals("1.") || eingabe.equals("1")) {
			imSpiel = false;
			spielerAnlegen(2);
			spielen();
		} else if (eingabe.equals("2.") || eingabe.equals("2")) {
			imSpiel = false;
			spielerAnlegen(1);
			spielen();
		} else if (eingabe.equals("3.") || eingabe.equals("3")) {
			imSpiel = false;
			spielerAnlegen(0);
			spielen();
		} else if (eingabe.equals("4.") || eingabe.equals("4")) {
			laden();
			imSpiel = false;
			spielen();
		} else {
			System.out.println("Bitte waehlen Sie eine der moeglichen Optionen aus!");
			}
		}
	}

	/**
	 * Diese Methode ist ein Hilfsmenue fuer das Regelwerk und die Bedienung von
	 * Abalone.
	 * 
	 */
	public static void hilfsMenu() {
		boolean inSchleifeBleiben = true;
		System.out.println("###############################################\r\n"
				+ "################# HilfsMenue ##################\r\n"
				+ "###############################################\r\n"
				+ "#### 1. Regeln ########### 2. Spielablauf #####\r\n"
				+ "###############################################\r\n"
				+ "### Geben Sie die Nummer oder den Namen des ###\r\n"
				+ "##### Artikels ein den Sie lesen moechten. ####\r\n"
				+ "###############################################\r\n");
		String eingabe = sc.nextLine();
		while (inSchleifeBleiben) {

			if (eingabe.equalsIgnoreCase("regeln") || eingabe.equals("1") || eingabe.contentEquals("1.")) {
				System.out.println("");
				System.out.println("\r\n"
						+ "Abalone wird auf einem sechseckingen Spielfeld mit 61 Feldern gespielt.\r\n"
						+ "Jeder Spieler hat 14 Kugeln und muss versuchen durch taktisch kluges verschieben\r\n"
						+ "von einer, zwei oder drei eigenen Kugeln die gegnerischen Kugeln vom Spielfeld zu schieben.\r\n"
						+ "Der Spieler, der zuerst sechs gegnerische Kugeln verdraengt hat, hat gewonnen.\r\n" + "\r\n"
						+ "Man kann die Kugeln in alle sechs angrenzenden Luecken verschieben.\r\n"
						+ "Es ist moeglich eine, zwei, oder drei Kugeln zu bewegen, solange die Kugeln\r\n"
						+ "in eine Richtung (auch diagonal) bewegt werden und alle angrenzenden Luecken frei sind\r\n"
						+ "Kugeln koennen nur eine Luecke pro Zug verschoben werden.\r\n" + "\r\n"
						+ "Ist das Feld durch eine gegnerische Kugel besetzt, kann diese nur durch mehrere\r\n"
						+ "eigene Kugeln in einer Linie von dort verdraengt werden, wenn sich dahinter ein\r\n"
						+ "freies Feld befindet oder die gegnerische Kugel vom Spielbrett geschoben wird\r\n" + "\r\n"
						+ "Das herunterschieben einer gegnerischen Kugel wird Sumito genannt. Es gibt drei\r\n"
						+ "verschiedene Sumitos:" + "2-1 Sumito: zwei eigene Kugeln schieben eine gegnerische\r\n"
						+ "3-1 Sumito: drei eigene Kugeln schieben eine gegnerische\r\n"
						+ "3-2 Sumito: drei eigene Kugeln schieben zwei gegnerische\r\n" + "\r\n"
						+ "Hat der Gegner drei oder mehr Kugeln in einer Reihe stehen, so koennen diese"
						+ "nicht verschoben werden.");
			}

			else if (eingabe.equalsIgnoreCase("spielablauf") || eingabe.equals("2") || eingabe.contentEquals("2.")) {
				System.out.println("");
				System.out.println("\r\n"
						+ "Wie beim Schach hat Abalone ein Notationssystem fuer Zuege. Hierbei werden die Querlinien mit Buchstaben von A bis I\r\n"
						+ "(die unterste Grundlinie ist A) bezeichnet und die Diagonalen von 1 bis 9 (die linke schwarze Diagonale ist 1)\r\n"
						+ "\r\n"
						+ "Ein Zug wird jeweils mit dem Anfangs-und Endpunkt des letzten schiebenden Steines bezeichnet.\r\n"
						+ "Ein diagonaler Zug wird mit den Anfangspositionen der beiden aeussersten Steine bezeichnet und \r\n"
						+ "mit der Endposition eines dieser beiden aeussersten Steine.\r\n"
						+ "Beispiele dafuer waeren: >C3C5-D3< oder >G6I8-F5<\r\n");
			} else {
				System.out.println("");
				System.out.println(
						"Bitte halten Sie sich an die Schreibweise und geben Sie entweder die Nummer oder den Namen des Artikels ein den Sie lesen moechten. ");
			}

			System.out.println("");
			System.out.println(
					"Wenn Sie einen anderen Artikel lesen moechten, so geben Sie bitte seine Nummer oder seinen Namen ein.\r\n"
							+ "Wenn Sie zurueck zum Spiel mochten, so geben sie bitte Back oder Zurueck ein.\r\n");
			eingabe = sc.nextLine();
			if (eingabe.equalsIgnoreCase("back") || eingabe.equalsIgnoreCase("zuruck")
					|| eingabe.equalsIgnoreCase("zurueck")) {
				inSchleifeBleiben = false;
			}
		}

	}

	/**
	 * Diese Methode startet das Spiel und uebergibt der ziehen Methode immer wieder
	 * den vom Benutzer eingegebenen Zug bis einer der Spieler das Spiel gewonnen
	 * hat.
	 * 
	 * @throws IOException Wenn beim Speichern oder Laden ein Fehler auftritt
	 * @throws ClassNotFoundException Wenn beim Speichern oder Laden ein Fehler auftritt
	 * @throws FileNotFoundException Wenn beim Speichern oder Laden ein Fehler auftritt
	 */
	public static void spielen()
			throws FileNotFoundException, ClassNotFoundException, IOException {
		String gewinner = "";
		String verlierer = "";
		boolean imSpiel = true;
		boolean ki1Loop = true;
		boolean ki2Loop = true;
		while (imSpiel) {
			System.out.println();
			System.out.println("Geben Sie 'Menu' ein falls Sie ins Hauptmenu wollen"
					+ " und 'exit' falls sie das Spiel abbrechen moechten.");
			System.out.println();
			System.out.println(spiel.getStatus());
			if (spiel.getSpielerAmZug().substring(0,2).equals("KI")) {
				boolean kiLoop = true;
				if(spiel.getSpielerAmZug().charAt(3) == '1') kiLoop = ki1Loop;
				if(spiel.getSpielerAmZug().charAt(3) == '2') kiLoop = ki2Loop;
				String[] zug = {""};
				if(spiel.getSpielerAmZug().length() != 4 &&
						"(durchziehend)".equals(spiel.getSpielerAmZug().substring(4, 18))) {
					kiLoop = false;
				}
				if (kiLoop) {
					System.out.println("ENTER DR�CKEN------menu EINGEBEN FUER DAS MENUE");
					String eingabe = sc.nextLine();
					if ("BIS ZUM ENDE".equals(eingabe)) {
						kiLoop = false;
						zug[0] = "DURCHZIEHEN";
					}
					while("menu".equals(eingabe)) {
						menue();
						System.out.println();
						System.out.println("Geben Sie 'Menu' ein falls Sie ins Hauptmenu wollen"
								+ " und 'exit' falls sie das Spiel abbrechen moechten.");
						System.out.println();
						System.out.println(spiel.getStatus());
						System.out.println("ENTER DR�CKEN");
						eingabe = sc.nextLine();
					}
					                          
				}
				
				if (spiel.getSpielerAmZug().charAt(3) == '1') ki1Loop = kiLoop;
				if (spiel.getSpielerAmZug().charAt(3) == '2') ki2Loop = kiLoop;
				
				try {
					spiel.ziehe(zug);
				} catch(SpielException e) {
					System.out.println(e.getMessage());
				}
			} else {
				System.out.print("> ");
				String eingabe = sc.nextLine();
				if (eingabe.equalsIgnoreCase("menu")) {
					menue();
				} else

					if (eingabe.equalsIgnoreCase("exit")) {
						System.out.println("Wollen Sie wirklich das Spiel verlassen? (Ja/Nein)");
						eingabe = sc.nextLine();
						if (eingabe.equalsIgnoreCase("Ja")) {
							gewinner = null;
							verlierer = null;
							imSpiel = false;
							break;
						}
					} else
						if (!ziehen(eingabe)) {
							System.out.println("Irgendwas hat da nicht gestimmt!");
							System.out.println();
						}
			}
		imSpiel = !spiel.hatGewonnen(spiel.getSpielerAmZug());
		if (!imSpiel) {
			verlierer = spiel.getSpielerAmZug();
			String[] spielerImSpielArr = spiel.getSpielerImSpielInterface().split(",");
			for (String s1 : spielerImSpielArr) {
				if (!s1.equals(verlierer)) {
					gewinner = s1;
				}
			}
		}
		}
	if(gewinner.length() >= 2 && gewinner.substring(0,2).equals("KI")) {
		gewinner = gewinner.substring(0,4);
	}
	if(verlierer.length() >= 2 && verlierer.substring(0,2).equals("KI")) {
		verlierer = verlierer.substring(0,4);
	}
	spielBeenden(gewinner, verlierer);
	}

	/**
	 * Gibt das Hauptmenue aus, wartet auf eine Eingabe: 1 fuer getHistorie() 2 fuer
	 * hilfsMenue 3 fuer weiter spielen
	 * 
	 * @throws IOException Wenn beim Speichern oder Laden ein Fehler auftritt
	 * @throws FileNotFoundException  Wenn beim Speichern oder Laden ein Fehler auftritt
	 * @throws ClassNotFoundException  Wenn beim Speichern oder Laden ein Fehler auftritt
	 */
	public static void menue() throws FileNotFoundException, IOException, ClassNotFoundException {
		boolean inSchleifeBleiben = true;

		while (inSchleifeBleiben) {
			System.out.print(
					"\nBitte waehlen Sie:\n(1) Historie ausgeben\n" + "(2) Hilfsmenue ausgeben\n(3) Spiel fortsetzen\n"
							+ "(4) Spiel speichern\n(5) Spiel laden\n(6) Spiel beenden\n\n> ");
			String auswahl = sc.nextLine();

			if (auswahl.equals("1")) {
				if (spiel.getHistorie().length() == 0)
					System.out.println("Es wurden noch nicht gezogen!");

				System.out.println(spiel.getHistorie());
			}
			else if (auswahl.equals("2"))
				hilfsMenu();
			
			else if (auswahl.equals("3"))
				inSchleifeBleiben = false;
			
			else if (auswahl.equals("4"))
				speichern();
			
			else if (auswahl.equals("5"))
				laden();
			
			else if(auswahl.equals("6"))
				spielBeenden(null, null);
			
			else {
				System.out.println("Die Tasten befinden sich oben links auf ihrer Tastatur. + \n"
						+ "Falls Sie sich zusaetzlich eine Tastatur mit NumPad\n"
						+ "geleistet haben finden Sie die Tasten auch rechts.");
				System.out.println();
			}
		}
	}

	/**
	 * Diese Methode prueft ob die Zug-Eingabe des Benutzers korrekt war und gibt
	 * den Zug dann an die ziehe Methode weiter.
	 * 
	 * @param zug   Der vom Benutzer eingegebene Zug.
	 * @return boolean Ob der Zug korrekt war.
	 */
	public static boolean ziehen(String zug) {

		zug = zug.toUpperCase();

		String[] zugArr = new String[2];
		try {

			if (zug.length() == 4 || zug.length() == 2) {
				zugArr[0] = zug;
				zugArr[1] = null;
				printErlaubteZuege(zugArr);
				return true;
			}

			if (zug.length() < 5 || zug.length() > 7) {
				return false;
			}

			if (zug.length() == 5) {
				if (!zug.substring(2, 3).equals("-")) {
					return false;
				}
				zugArr[0] = zug.substring(0, 2);
				zugArr[1] = zug.substring(3, 5);
			}
			if (zug.length() == 7) {
				if (!zug.substring(4, 5).equals("-")) {
					return false;
				}
				zugArr[0] = zug.substring(0, 4);
				zugArr[1] = zug.substring(5, 7);
			}
			try {
				spiel.ziehe(zugArr);
			} catch (SpielException e) {
				return false;
			}
		} catch (StringIndexOutOfBoundsException e) {

			return false;
		}
		return true;
	}

	/**
	 * Gibt die erlaubten Zuege auf der Konsole aus.
	 * 
	 * @param zug   Ein Spielzug Objekt mit Nach-Attribut auf null.
	 */
	public static void printErlaubteZuege(String[] zug) {
		try {
			String[] erlaubteZuege = spiel.getErlaubteZuegeInterface(zug).split(",");
			if (erlaubteZuege.length == 0) {
				System.out.println("Fuer " + zug[0] + " sind momentan keine Zuege moeglich.");
			} else {
				System.out.println("Folgende Zuege sind fuer " + zug[0] + " momentan moeglich: ");
				for (String zugString : erlaubteZuege) {
					System.out.println(zugString);
				}
			}
			System.out.println("Druecken Sie Enter zum fortfahren.");
			sc.nextLine();
		} catch (SpielException e) {
			System.out.println("Ungueltige Eingabe");
		}
	}

	/**
	 * Diese Methode gibt den Gewinner aus.
	 * 
	 * @param gewinner  Der Gewinner des Spiels.
	 * @param verlierer Der Verlierer des Spiels.
	 */
	public static void spielBeenden(String gewinner, String verlierer) {
		if (gewinner != null && verlierer != null) {
			System.out.println("Hurra " + gewinner + " Hat das Spiel gewonnen!");
			System.out.println("Verlierer der heutigen Runde ist: " + verlierer + "!");
		}
		
		System.out.println();
		System.out.println();
		System.out.println("###############################################\r\n"
				+ "#    ___  _           _                       #\r\n"
				+ "#   / _ \\| |         | |                      #\r\n"
				+ "#  / /_\\ \\ |__   __ _| | ___  _ __   ___      #\r\n"
				+ "#  |  _  | '_ \\ / _` | |/ _ \\| '_ \\ / _ \\     #\r\n"
				+ "#  | | | | |_) | (_| | | (_) | | | |  __/     #\r\n"
				+ "#  \\_| |_/_.__/ \\__,_|_|\\___/|_| |_|\\___|     #\r\n"
				+ "#                                             #\r\n"
				+ "###############################################\n"
				+ "#################### ENDE #####################\n"
				+ "###############################################");
		System.exit(0);
	}
	
	/**
	 * Diese Methode dient zum Abspeichern eines Spielstandes.
	 * @throws IOException  Wenn beim Speichern ein Fehler auftritt
	 * @throws ClassNotFoundException  Wenn beim Speichern ein Fehler auftritt
	 * @throws FileNotFoundException Wenn beim Speichern ein Fehler auftritt
	 */
	public static void speichern() throws FileNotFoundException, ClassNotFoundException, IOException {
		boolean a = true;
		boolean b = true;
		
		while(a) {
			System.out.println("\nBitte geben Sie ein, wie Sie die Datei speichern m�chten.");
			System.out.println("(1) Serialisierte Datei");
			System.out.println("(2) CSV-Datei");
			System.out.print("(3) Zurueck zum Menue\n> ");
			String eingabe = sc.nextLine();
			
			if (eingabe.equals("1")) {
				a = false;
				
				while(b) {
					try {
						System.out.println("\nBitte geben Sie einen gueltigen Dateinamen ein.");
						System.out.print("Zum Abbrechen 'abbruch' eingeben.\n> ");
						String dateiName = sc.nextLine();
						if (dateiName.equalsIgnoreCase("abbruch"))
							break;
						
						spiel.speichernSerialisiert(dateiName);
						System.out.println("\nDie Datei wurde erfolgreich gespeichert.");
						b = false;
					} catch (SpielException e) {
						System.out.println(e.getMessage());
					}
				}
			} else if (eingabe.equals("2")) {
				a = false;
				
				while(b) {
					try {
						System.out.println("\nBitte geben Sie einen gueltigen Dateinamen ein.");
						System.out.print("Zum Abbrechen 'abbruch' eingeben.\n> ");
						String dateiName = sc.nextLine();
						if (dateiName.equalsIgnoreCase("abbruch"))
							break;
						
						spiel.speichernCSV(dateiName);
						System.out.println("\nDie Datei wurde erfolgreich gespeichert.");
						b = false;
					} catch (SpielException e) {
						System.out.println(e.getMessage());
					}
				}
			} else if (eingabe.equals("3")) {
				break;
			} else {
				System.out.println("Ihre Eingabe ist fehlerhaft.");
			}
		}
	}

	/**
	 * Diese Methode dient zum Laden eines bereits gespeicherten Spielstandes.
	 * @throws IOException  Wenn beim Laden ein Fehler auftritt
	 * @throws ClassNotFoundException  Wenn beim Laden ein Fehler auftritt
	 * @throws FileNotFoundException  Wenn beim Laden ein Fehler auftritt
	 */
	public static void laden() throws FileNotFoundException, ClassNotFoundException, IOException {
		boolean a = true;
		boolean b = true;
		
		while(a) {
			System.out.println("\nBitte geben Sie ein, was f�r eine Datei sie laden wollen.");
			System.out.println("(1) Serialisierte Datei");
			System.out.println("(2) CSV-Datei");
			System.out.print("(3) Zurueck zum Menue\n> ");
			String eingabe = sc.nextLine();
			
			if (eingabe.equals("1")) {
				a = false;
				
				while(b) {
					try {
						System.out.println("\nBitte geben Sie einen gueltigen Dateinamen ein.");
						System.out.print("Zum Abbrechen 'abbruch' eingeben.\n> ");
						String dateiName = sc.nextLine();
						
						if (dateiName.equalsIgnoreCase("abbruch")) {
							try {
								if (spiel.getSpielerAmZug() == null);
								else break;
							} catch (NullPointerException e) {
								hauptMenue();
							}	
						}
						
						spiel.lesenSerialisiert(dateiName);
						System.out.println("\nDie Datei wurde erfolgreich geladen.");
						b = false;
					} catch(SpielException e) {
						System.out.println(e.getMessage());
					}
				}
			} else if (eingabe.equals("2")) {
				a = false;
				
				while(b) {
					try {
						System.out.println("\nBitte geben Sie einen gueltigen Dateinamen ein.");
						System.out.print("Zum Abbrechen 'abbruch' eingeben.\n> ");
						String dateiName = sc.nextLine();

						if (dateiName.equalsIgnoreCase("abbruch")) {
							try {
								if (spiel.getSpielerAmZug() == null);
								else break;
							} catch (NullPointerException e) {
								hauptMenue();
							}	
						}
						
						spiel.lesenCSV(dateiName);
						System.out.println("\nDie Datei wurde erfolgreich geladen.");
						b = false;
					} catch(SpielException e) {
						System.out.println(e.getMessage());
					}
				}
			} else if (eingabe.equals("3")) {
					try {
						if (spiel.getSpielerAmZug() == null);
						else break;
					} catch (NullPointerException e) {
						hauptMenue();
					}
			} else {
				System.out.println("Ihre Eingabe ist fehlerhaft.");
			}
		}
	}
	
}
