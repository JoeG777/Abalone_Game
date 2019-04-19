
package abalone;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import abalone.spielbrett.Spielbrett;
import abalone.spielbrett.SpielbrettException;
import abalone.spielbrett.SpielfeldException;

/**
 * <h1>Spiel</h1>
 * Die Klasse Spiel implementiert das zentrale Objekt fuer das Spiel Abalone. In
 * ihr laufen alle anderen Klassen zusammen, somit bildet sie die Schnittstelle
 * zu weiteren Objekten wie einer UI 
 * @author Gruppe A4
 * @version 1.2
 * @since 1.0
 */
public class Spiel implements bedienerInterface, java.io.Serializable {

	private static final long serialVersionUID = 109L;
	private Spieler spielerAmZug;
	private Spieler[] spielerImSpiel;
	private Spielbrett spielBrett;
	private Historie historie;
	private boolean herausgedraengt = false;
	private Spielzug letzterZug;
	private BufferedWriter logger;

	/**
	 * Konstruktor, instanziiert alle anfangs benoetigten Objekte.
	 * @throws SpielfeldException 
	 */
	public Spiel() throws SpielfeldException {
		spielBrett = new Spielbrett();
		historie = new Historie();
		this.spielerImSpiel = new Spieler[2];
		try {
			File file = new File("log.txt");
			logger = new BufferedWriter(new FileWriter(file));
		}catch(FileNotFoundException e) {
		}catch(IOException e) {
			
		}
	}

	/**
	 * Gibt alle Spieler Objekte im Spiel als Array zurueck.
	 * 
	 * @return spielerImSpiel Ein Array des Typs Spieler.
	 */
	public Spieler[] getSpielerImSpiel() {
		return spielerImSpiel;
	}

	/**
	 * Diese Methode wird benutzt um Spieler einem Spiel hinzuzufuegen. Sie fuegt
	 * maximal 2 Spieler hinzu. Sollte ein dritter Spieler hinzugefuegt werden,
	 * wirft die Methode eine IndexOutOfBounds Exception.
	 * 
	 * @param name  Der fuer den Spieler gewaehlte Name.
	 * @param farbe Die fuer den Spieler gewaehlte Farbe.
	 * @throws AbaloneException 
	 * @exception IllegalArgumentException  Wird geworfen, wenn der String farbe
	 *                                      nicht "schwarz" oder "weiss" entspricht.
	 * @exception IndexOutOfBoundsException wird geworfen wenn ein Spieler
	 *                                      hinzugefuegt wird, obwohl bereits zwei
	 *                                      Spieler im Spiel sind.
	 * @since 1.0
	 */
	@Override
	public void addSpieler(String name, String farbe, int anzahlSpieler) throws AbaloneException {
		if (anzahlSpieler == 2) {
			if (spielerImSpiel[0] != null && spielerImSpiel[1] != null) {
				throw new AbaloneException(11,"Das Spieler Array ist bereits voll!");
			}
			if (farbe.equals("weiss") && spielerImSpiel[0] == null) {
				FarbEnum spielerFarbe = FarbEnum.WEISS;
				spielerImSpiel[0] = new Spieler(name, spielerFarbe);
				this.spielerAmZug = spielerImSpiel[0];
			} else if (farbe.equals("schwarz") && spielerImSpiel[0] != null) {
				FarbEnum spielerFarbe = FarbEnum.SCHWARZ;
				spielerImSpiel[1] = new Spieler(name, spielerFarbe);
			} else {
				throw new AbaloneException(10,"Unbekannte farbe :" + farbe);
			}
		} else if (anzahlSpieler == 1) {
			FarbEnum spielerFarbe = FarbEnum.WEISS;
			spielerImSpiel[0] = new Spieler(name, spielerFarbe);
			this.spielerAmZug = spielerImSpiel[0];
			FarbEnum KIFarbe = FarbEnum.SCHWARZ;
			spielerImSpiel[1] = new KISchwer(this, KIFarbe);
		} else if (anzahlSpieler == 0) {
			FarbEnum KIFarbe = FarbEnum.WEISS;
			spielerImSpiel[0] = new KIEinfach(KIFarbe);
			this.spielerAmZug = spielerImSpiel[0];
			KIFarbe = FarbEnum.SCHWARZ;
			spielerImSpiel[1] = new KISchwer(this, KIFarbe);
		}

	}

	/**
	 * Diese Methode gibt den Namen des aktuellen Spielers zurueck.
	 * 
	 * @return spielerName Name des Spielers, welcher am Zug ist, als String.
	 */
	@Override
	public String getSpielerAmZug() {
		if (this.spielerAmZug instanceof KI) {
			return "C";
		} else {
			return spielerAmZug.getName();
		}
	}
	
	private Spieler getSpielerAmZugObj() {
		return this.spielerAmZug;
	}
	
	/**
	 * Gibt die Farbe des Spielers zurueck, der aktuell am Zug ist.
	 * 
	 * @return spielerFarbe Ein Objekt der Klasse FarbEnum.
	 */
	private FarbEnum getFarbeAmZug() {
		return this.spielerAmZug.getFarbe();
	}

	/**
	 * Fragt ab, ob ein Spieler gewonnen hat.
	 * zi
	 * @param name Name eines Spielers.
	 * @return boolean Ob der uebergebene Spieler gewonnen hat.
	 */
	public boolean hatGewonnen(String name) {
		Spieler spieler = null;
		Spieler[] spielerArr = getSpielerImSpiel();
		for (int i = 0; i < spielerArr.length; i++) {
			if (spielerArr[i].getName().equals(name)) {
				spieler = spielerArr[i];
			}
		}
		for (int i = 0; i < spielerArr.length; i++) {
			if (spieler != null && !spielerArr[i].equals(spieler)) {
				if (14 - this.zaehleKugelnMitFarbe(spieler.getFarbe()) >= 6)
					return true;
			}
		}
		return false;
	}

	/**
	 * Diese Methode erstellt und beschreibt eine Datei und wird zum Speichern
	 * eines Spielstandes als serialisierte Datei verwendet
	 */
	public void speichernSerialisiert(String dateiName) throws AbaloneException {
		PersistenzImplSerialisiert serial = new PersistenzImplSerialisiert();
		
		try {
			serial.oeffnen(dateiName);
		} catch(IOException e) {
			log(e);
			throw new AbaloneException(13,"Datei konnte nicht geoeffnet werden!");
		}
		
		Object[] spielState = {this.spielerImSpiel,this.spielerAmZug,this.spielBrett,this.historie,this.herausgedraengt,this.letzterZug};
		
		try {
			serial.schreiben(spielState);
		} catch(IOException e) {
			log(e);
			throw new AbaloneException(13, "Datei konnte nicht geoeffnet werden!");
		}
	}

	/**
	 * Diese Methode oeffnet und liest eine Datei und wird zum Laden
	 * eines - als serialisierte Datei - gespeicherten Spielstandes verwendet
	 */
	public void lesenSerialisiert(String dateiName) throws AbaloneException{
		PersistenzImplSerialisiert serial = new PersistenzImplSerialisiert();
		
		try {
			serial.oeffnen(dateiName);
		} catch(IOException e) {
			log(e);
			throw new AbaloneException(13,"Datei konnte nicht geoeffnet werden!");
		}
		
		Object[] spielState = null;
		
		try {
			spielState = (Object[]) serial.lesen();
		}
		catch(IOException e) {
			log(e);
			throw new AbaloneException(13,"Datei nicht gefunden!");
		}
		catch(ClassNotFoundException e){
			log(e);
			throw new AbaloneException(15,"Die Datei scheint kaputt zu sein!");
		};
		
		this.spielerImSpiel = (Spieler[])spielState[0];
		this.spielerAmZug = (Spieler)spielState[1];
		this.spielBrett = (Spielbrett) spielState[2];
		this.historie = (Historie) spielState[3];
		this.herausgedraengt = (boolean) spielState[4];
		this.letzterZug = (Spielzug) spielState[5];;
	}
	
	/**
	 * Die ziehe Methode erzeugt aus zwei Strings ein Zug Objekt und uebergibt
	 * dieses dem Spielbrett.
	 * 
	 * @param zug Ein String Array mit den Werten [0] = von wo aus gezogen wird, [1]
	 *            = wohin gezogen wird.
	 * @throws AbaloneException 
	 * @exception IllegalArgumentException Wirft eine IllegalArgumentException wenn
	 *                                     zugValidieren false zurueck gibt oder ein
	 *                                     Array Eintrag NULL ist.
	 * @since 1.0
	 */
	@Override
	public void ziehe(String[] zug) throws AbaloneException {
		if(spielerAmZug instanceof KISchwer)  {
			zug = ((KISchwer)getSpielerAmZugObj()).getBesterZug(this);
		}
		if(spielerAmZug instanceof KIEinfach) {
			zug = ((KIEinfach)getSpielerAmZugObj()).randomZiehen(this);
		}
		try{
			ziehen(zug);
		}catch(UngueltigerZugException e) {
			log(e);
			throw new AbaloneException(0,"UngueltigerZug!");
		}catch(SpielbrettException e) {
			log(e);
			throw new AbaloneException(1,"Irgendwas ist schief gelaufen!");
		}
	}
	
	public void ziehen(String[] zug) throws SpielbrettException, UngueltigerZugException {
		if (koordinatenValidieren(spielzugParser(zug))) {
			Spielzug spielzug = new Spielzug(zug[0], zug[1]);
			if (spielzug.getNach() == null) {
				throw new UngueltigerZugException(7,"Unzulaessiger Zug");
			}
			spielzug.setRichtung(this.bekommeRichtung(spielzug));
			spielzug.setFarbe(getFarbeAmZug());;
			if (zugValidieren(spielzug)) {
				Spielzug[] spielzuege = spielzugSplitter(spielzug);
				if (spielzuege[0].getNach() == null) {
					letzterZug = spielzuege[0];
					herausgedraengt = true;
					spielzug.setNach(spielzug.getNach() + "*");
				}
				spielBrett.ziehe(spielzuege);
				historie.spielzugHinzufuegen(spielzug);
				if (getFarbeAmZug() == spielerImSpiel[0].getFarbe()) {
					spielerAmZug = spielerImSpiel[1];
				} else {
					spielerAmZug = spielerImSpiel[0];
				}
			} else {
				throw new UngueltigerZugException(7,"Unzulaessiger Zug");
			}
		}
	}

	/**
	 * Gibt die erlaubten Zuege zurueck.
	 * 
	 * @param ausgangsFelder Die Ausgangsfelder, von denen gezogen wird. gesammelt
	 *                       werden sollen.
	 * @return ErlaubteZuege Ein String Array mit den erlaubten Zuegen.
	 * @throws UngueltigerZugException 
	 */
	
	public String[] getErlaubteZuege(String[] ausgangsFelder) throws UngueltigerZugException {
		if (!koordinatenValidieren(spielzugParser(ausgangsFelder))) {
			throw new UngueltigerZugException(7, "Ungueltige Eingabe");
		}
		ArrayList<String> erlaubteZuege = new ArrayList<String>();
		if (koordinatenValidieren(felderParser(ausgangsFelder[0]))) {
			for (String felder : ausgangsFelder) {
				if (felder != null) {
					// Fall 1: Nur ein Feld
					if (felder.length() == 2) {
						String ausgang = spielBrett.getFeld(felder);
						String[] nachbarn = spielBrett.getNachbarnByIdVonFeld(felder);
						for (String nachbar : nachbarn) {
							if (nachbar != null) {
								Spielzug zug = new Spielzug(spielBrett.getFeld(ausgang), spielBrett.getFeld(nachbar));
								zug.setRichtung(bekommeRichtung(zug));
								zug.setFarbe(getFarbeAmZug());
								if (zugValidieren(zug)) {
									erlaubteZuege.add(zug.getVon() + "-" + zug.getNach());
								}
							}
						}
					}
					// Fall 2: Zwei Felder
					if (felder.length() == 4) {
						String ausgang1 = spielBrett.getFeld(felder.substring(0, 2));
						String ausgang2 = spielBrett.getFeld(felder.substring(2, 4));
						String[] nachbarn1 = spielBrett.getNachbarnByIdVonFeld(ausgang1);
						String[] nachbarn2 = spielBrett.getNachbarnByIdVonFeld(ausgang2);
						for (String nachbar : nachbarn1) {
							if (nachbar != null) {
								Spielzug zug = new Spielzug(ausgang1 + ausgang2 , nachbar);
								zug.setRichtung(bekommeRichtung(zug));
								zug.setFarbe(getFarbeAmZug());;
								if (zugValidieren(zug)) {
									nachbar = spielBrett.getNachbarByIdInRichtung(ausgang1,zug.getRichtung());
									if (nachbar != null)
										zug.setNach(spielBrett.getFeld(nachbar));
									else
										zug.setNach(null);
									erlaubteZuege.add(zug.getVon() + "-" + zug.getNach());
								}
							}
						}
						for (String nachbar : nachbarn2) {
							if (nachbar != null) {
								Spielzug zug = new Spielzug(ausgang2+ ausgang1, nachbar);
								zug.setRichtung(bekommeRichtung(zug));
								zug.setFarbe(getFarbeAmZug());
								if (zugValidieren(zug)) {
									nachbar = spielBrett.getNachbarByIdInRichtung(ausgang2, zug.getRichtung());
									zug.setNach(nachbar);
									erlaubteZuege.add(zug.getVon() + "-" + zug.getNach());
								}
							}
						}
					}
				}
			}
		}
		return erlaubteZuege.toArray(new String[0]);
	}

	/**
	 * Zaehlt die Kugeln auf dem Feld mit einer gegebenen Farbe.
	 * 
	 * @param farbe zu zeaehlende Farbe als FarnEnum.
	 * @return AnzahlKugeln Anzahl der Kugeln mit dieser Farbe
	 */
	private int zaehleKugelnMitFarbe(FarbEnum farbe) {
		return spielBrett.getFelderMitFarbe(farbe).size();
	}

	/**
	 * Gibt die Historie zurueck.
	 * 
	 * @return HistorienString Historie als String.
	 */
	@Override
	public String getHistorie() {
		return this.historie.toString();

	}

	/**
	 * Gibt den Status des Spiels zurueck. Dieser umfasst: Das Spielbrett; Welcher
	 * Spieler am Zug ist; Wieviele Steine die jeweiligen Spieler noch im Spiel haben und
	 * wieviele Steiner Sie jeweils verloren haben
	 * 
	 * @return der Status als String
	 */
	@Override
	public String getStatus() {
		String[] verloreneSteineArray = { "", " *", " * *", " * * *", " * * * *", " * * * * *", " * * * * * *" };
		String amZug = "        Am zug ist: " + spielerAmZug.getName();
		String verbleibendeSteineO = "             Spieler " + this.spielerImSpiel[0].getName() + "(O) hat noch "
				+ this.zaehleKugelnMitFarbe(spielerImSpiel[0].getFarbe()) + " Kugeln.";
		String verloreneSteineO = "            Verlorene Kugeln:"
				+ verloreneSteineArray[14 - this.zaehleKugelnMitFarbe(spielerImSpiel[0].getFarbe())];
		String verbleibendeSteineX = "          Spieler " + this.spielerImSpiel[1].getName() + "(X) hat noch "
				+ this.zaehleKugelnMitFarbe(spielerImSpiel[1].getFarbe()) + " Kugeln.";
		String verloreneSteineX = "         Verlorene Kugeln:"
				+ verloreneSteineArray[14 - this.zaehleKugelnMitFarbe(spielerImSpiel[1].getFarbe())];

		String brettZeilen[] = this.spielBrett.toString().split("\n");
		brettZeilen[1] += verbleibendeSteineO;
		brettZeilen[2] += verloreneSteineO;
		brettZeilen[3] += "    ";
		brettZeilen[4] += verbleibendeSteineX;
		brettZeilen[5] += verloreneSteineX;
		brettZeilen[6] += "    ";
		brettZeilen[7] += amZug;

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < brettZeilen.length; i++) {
			sb.append(brettZeilen[i] +"\n");
		}
		String brett = sb.toString();

		if (herausgedraengt) {
			brett = this.addSternchen(brett, letzterZug);
			herausgedraengt = false;///
		}
		return brett;

	}

	/**
	 * Diese Methode Parst einen Spielzug zu einem Char Array zur weiteren
	 * Verarbeitung
	 * 
	 * @param zug Ein String Array mit den Werten [0] = von wo gezogen wird, [1] =
	 *            wohin gezogen wird.
	 * @return ein zweidimensionales char Array, welches den Zug in chars aufteilt
	 * @throws UngueltigerZugException 
	 * @exception IllegalArgumentException Wird geworfen, wenn Zuglaenge ungueltig
	 *                                     ist.
	 */
	private char[][] spielzugParser(String[] zug) throws UngueltigerZugException {
		char[][] geparsterZug = new char[2][];
		if (zug.length < 2) {
			throw new UngueltigerZugException(8,"Ungueltige laenge: " + zug.length);
		}
		if (zug[0] == null) {
			throw new UngueltigerZugException(9, "Zug darf nicht null sein!");
		}
		if (zug[0].length() % 2 != 0 || zug[0].length() > 4) {
			throw new UngueltigerZugException(9,"Ungueltige zuglaenge: " + zug[0].length());
		}

		// Ausgangskoordinate(n) anlege(n)
		char[] ausgangsPunkt = new char[zug[0].length()];
		// Erste Koordinate
		char buchstabe1 = zug[0].charAt(0);
		char zahl1 = zug[0].charAt(1);
		ausgangsPunkt[0] = buchstabe1;
		ausgangsPunkt[1] = zahl1;

		if (zug[0].length() == 4) {
			// Zweite Koordinate
			char buchstabe2 = zug[0].charAt(2);
			char zahl2 = zug[0].charAt(3);
			ausgangsPunkt[2] = buchstabe2;
			ausgangsPunkt[3] = zahl2;
		}
		geparsterZug[0] = ausgangsPunkt;
		// Zielkoordinate anlegen
		if (zug.length == 2 && zug[1] != null) {
			char[] zielPunkt = new char[2];
			char buchstabe = zug[1].charAt(0);
			char zahl = (zug[1].charAt(1));
			zielPunkt[0] = buchstabe;
			zielPunkt[1] = zahl;
			geparsterZug[1] = zielPunkt;
		}
		return geparsterZug;

	}

	/**
	 * Parst einen String zu einem zweidimensonalen Char Array
	 * 
	 * @param zug mit dem Datentyp String
	 * @return zweidimensionales Char Array, welches den Zug als Chars enthaelt
	 * @throws UngueltigerZugException 
	 * @exception IllegalArgumentException Wird geworfen, wenn Zuglaenge ungueltig
	 *                                     ist.
	 */
	private char[][] felderParser(String zug) throws UngueltigerZugException {
		char[][] geparsterZug = new char[1][];
		if (zug.length() % 2 != 0 || zug.length() > 4) {
			throw new UngueltigerZugException(8, "Ungueltige zuglaenge: " + zug.length());
		}

		// Ausgangskoordinate(n) anlege(n)
		char[] ausgangsPunkt = new char[zug.length()];
		// Erste Koordinate
		char buchstabe1 = zug.charAt(0);
		char zahl1 = zug.charAt(1);
		ausgangsPunkt[0] = buchstabe1;
		ausgangsPunkt[1] = zahl1;

		if (zug.length() == 4) {
			// Zweite Koordinate
			char buchstabe2 = zug.charAt(2);
			char zahl2 = zug.charAt(3);
			ausgangsPunkt[2] = buchstabe2;
			ausgangsPunkt[3] = zahl2;
		}
		geparsterZug[0] = ausgangsPunkt;
		return geparsterZug;

	}

	/**
	 * Prueft ob die gegeben Koordinaten einem validen Zug entsprechen, unabhaengig
	 * von der aktuellen Feldbelegegung.
	 * 
	 * @param geparsterZug Ein zweidimensionales Char Array.
	 * @return boolean true oder false in Abhaengigkeit der Validitaet eines Zuges.
	 * @since 1.1
	 */
	private boolean koordinatenValidieren(char[][] geparsterZug) {
		// Pruefen ob die angegebenen Chars auch auf dem Spielbrett existieren
		int buchstabenKoordinaten = 0;
		int zahlenKoordinaten = 0;
		for (char[] koordinate : geparsterZug) {
			if (koordinate != null) {
				for (int i = 0; i < koordinate.length; i++) {
					if (i % 2 == 0) {
						buchstabenKoordinaten = 'I' - koordinate[i];
						if ((buchstabenKoordinaten < 8 && buchstabenKoordinaten < 0)) {
							return false;
						}
					} else {
						zahlenKoordinaten = '9' - koordinate[i];
						// Die cases Spiegeln die differenz Zwischen 'I' und dem aktuellen Buchstaben
						// wider
						// Buchstabe = I: case = 0, Buchstabe = A: case = 8
						switch (buchstabenKoordinaten) {
						case 0:
							if (zahlenKoordinaten >= 5 || zahlenKoordinaten < 0) {
								return false;
							}
							break;
						case 1:
							if (zahlenKoordinaten >= 6 || zahlenKoordinaten < 0) {
								return false;
							}
							break;
						case 2:
							if (zahlenKoordinaten >= 7 || zahlenKoordinaten < 0) {
								return false;
							}
							break;
						case 3:
							if (zahlenKoordinaten >= 8 || zahlenKoordinaten < 0) {
								return false;
							}
							break;
						case 4:
							if (zahlenKoordinaten >= 9 || zahlenKoordinaten < 0) {
								return false;
							}
							break;
						case 5:
							if (zahlenKoordinaten >= 9 || zahlenKoordinaten < 1) {
								return false;
							}
							break;
						case 6:
							if (zahlenKoordinaten >= 9 || zahlenKoordinaten < 2) {
								return false;
							}
							break;
						case 7:
							if (zahlenKoordinaten >= 9 || zahlenKoordinaten < 3) {
								return false;
							}
							break;
						case 8:
							if (zahlenKoordinaten >= 9 || zahlenKoordinaten < 4) {
								return false;
							}
							break;
						default:
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	/**
	 * Prueft ob mehrere Zuege abhaengig von der Spielfeldbelegung ausfuehrbar sind.
	 * 
	 * @param zuege Array des Typs Spielzug.
	 * @return boolean True oder False, in Abhaengigkeit der Validitaet der Zuege.
	 */
	public boolean zugValidieren(Spielzug zug) {
		boolean erfolgreich = false;

		String[] ausgangsfelder = spielBrett.getAusgangsfelder(zug);
		if (ausgangsfelder.length == 0)
			return false;
		int richtung = bekommeRichtung(zug);
		for (int i = 0; i < ausgangsfelder.length; i++) {
			if (!(spielBrett.istBesetzt(ausgangsfelder[i]))|| zug.getFarbe() != spielBrett.getFarbeDerFigurById(ausgangsfelder[i]))
				return false;
		}
		String[] zielfelder = getZielfelder(ausgangsfelder, richtung);
		for (String feld : zielfelder) {
			if (feld == null) {
				return false;
			}
		}
		if (ausgangsfelder.length == 1) { // Ein Stein darf nicht schieben, also nur ueberpruefen, ob Zielfeld
			// belegt ist
			if (spielBrett.getNachbarByIdInRichtung(ausgangsfelder[0],richtung) != null
					&& !(spielBrett.istBesetzt(spielBrett.getNachbarByIdInRichtung(ausgangsfelder[0],richtung)))) {
				return true;
			}
		}

		else if (isSchiebung(ausgangsfelder, richtung)) { // Schiebende Zuege sind anders zu behandeln als diagonale

			if (ausgangsfelder.length == 2) {
				String vordersterStein = getVorderstenStein(ausgangsfelder, richtung);

				if (spielBrett.istBesetzt(spielBrett.getNachbarByIdInRichtung(vordersterStein,richtung))) {
					if (isZuEinsSumito(vordersterStein, richtung)) {
						return true;
					}
				} else {
					return true;
				}
			} else if (ausgangsfelder.length == 3) {
				String vordersterStein = getVorderstenStein(ausgangsfelder, richtung);
				if (spielBrett.istBesetzt(spielBrett.getNachbarByIdInRichtung(vordersterStein, richtung))) {
					if (isZuEinsSumito(vordersterStein, richtung)) {
						return true;
					} else if (isZuZweiSumito(vordersterStein, richtung)) {
						return true;
					}
				} else {
					return true;
				}

			}
		} else if (!isSchiebung(ausgangsfelder, richtung)) {
			for (String feld : zielfelder) {
				if (feld != null && spielBrett.istBesetzt(feld)) {
					return false;
				}
			}
			return true;
		}



		return erfolgreich;
	}

	/**
	 * Checkt, in welche Richtung ein Zug geht. 0 = Links 1 = Oben Links 2 = Unten
	 * Links 3 = Rechts 4 = Oben Rechts 5 = Unten Rechts.
	 * 
	 * @param Zug Objekt des Typs Zug.
	 * @return SpielzugRichtung Index des Objektes, in dessen Richtung gezogen wird.
	 * @since 1.3
	 */
	private int bekommeRichtung(Spielzug zug) {
		String zugVon = zug.getVon();
		String zugNach = zug.getNach();
		String feldVon = zugVon.substring(0, 2);


		return spielBrett.getNachbarIndexById(feldVon, spielBrett.getFeld(zugNach));
	}

	/**
	 * Gibt alle Spielfelder eines Zuges als Spielfeld-Array zurueck.
	 * 
	 * @param ausgangsfelder Die Felder, von denen gezogen wird.
	 * @param richtung       Die Richtung des Zuges.
	 * @return Spielfeld-Array Ein Array mit allen Zielfeldern des Zuges.
	 */
	private String[] getZielfelder(String[] ausgangsfelder, int richtung) {
		String[] zielfelder = new String[ausgangsfelder.length];

		for (int i = 0; i < ausgangsfelder.length; i++) {
			zielfelder[i] = spielBrett.getNachbarByIdInRichtung(ausgangsfelder[i],richtung);
		}

		return zielfelder;

	}

	/**
	 * Prueft, ob es sich bei einem regulaeren Zug um einen Zug handelt, bei dem
	 * eigene Steine in einer Linie geschoben werden.
	 * 
	 * @param felder   Die Ausgangsfelder eines Spielzuges.
	 * @param richtung Die Richtung der Bewegung (Position im Array).
	 * @return boolean true, wenn es sich um einen Zug handelt, bei dem eigene
	 *         Steine geschoben werden. false, wenn es sich nicht um einen solchen
	 *         Zug handelt.
	 */

	private boolean isSchiebung(String[] felder, int richtung) {
		if (felder.length == 3) {
			if (felder[1].equals(spielBrett.getNachbarByIdInRichtung(felder[0],richtung)) || felder[1].equals(spielBrett.getNachbarByIdInRichtung(felder[2],richtung))) {
				return true;
			}
		}
		if (felder.length == 2) {
			if (felder[0].equals(spielBrett.getNachbarByIdInRichtung(felder[1],richtung)) || felder[1].equals(spielBrett.getNachbarByIdInRichtung(felder[0],richtung))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Prueft, ob ein Zwei-zu-eins Sumito moeglich ist.
	 * 
	 * @param vordersterStein der vorderste Stein eines Spielzuges in dem zwei bis
	 *                        drei Steine bewegt werden.
	 * @param richtung        die Richtung des Spielzuges.
	 * @return boolean true, wenn moeglich, false, wenn nicht moeglich.
	 */
	private boolean isZuEinsSumito(String vordersterStein, int richtung) {
		String nachbarInRichtung = spielBrett.getNachbarByIdInRichtung(vordersterStein, richtung);
		if (nachbarInRichtung != null && spielBrett.istBesetzt(nachbarInRichtung)
				&& !spielBrett.getFarbeDerFigurById(nachbarInRichtung).equals(spielBrett.getFarbeDerFigurById(vordersterStein))) {
			String nachbarHinterNachbar = spielBrett.getNachbarByIdInRichtung(nachbarInRichtung, richtung);
			if (nachbarHinterNachbar == null) {
				return true;
			}
			if (!(spielBrett.istBesetzt(nachbarHinterNachbar))) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Gibt fuer einen Zug aus 2 oder 3 Steinen, bei dem eigene Steine geschoben
	 * werden, die Position des vordersten Steines in Richtung des Zuges zurueck.
	 * 
	 * @param felder   Felder auf denen sich die zu ziehenden Steine befinden.
	 * @param richtung Die Richtung des Zuges.
	 * @return Spielfeld-Objekt Spielfeld auf dem sich der vorderste Stein befindet.
	 */
	private String getVorderstenStein(String[] felder, int richtung) {
		String posVordersterStein = null;

		if (felder.length == 3) {
			if (!spielBrett.getNachbarByIdInRichtung(felder[0], richtung).equals(felder[1])) {
				posVordersterStein = felder[0];
			} else {
				posVordersterStein = felder[2];
			}
		} else if (felder.length == 2) {
			if (!spielBrett.getNachbarByIdInRichtung(felder[0], richtung).equals(felder[1])) {
				posVordersterStein = felder[0];
			} else {
				posVordersterStein = felder[1];
			}
		}

		return posVordersterStein;

	}

	/**
	 * Prueft, ob ein gegnerischer Stein durch Ausfuehrung des Zuges vom Spielbrett
	 * geworfen wird. Falls dies zutrifft, wird die Figur vom Spielfeld entfernt.
	 * 
	 * @param gegnerischerStein Das Feld, auf dem der Stein ist.
	 * @param richtung          Richtung des Spielzuges.
	 * @return boolean true, wenn der Stein runtergeworfen wird, false, wenn nicht.
	 */
	private boolean steinAbgeraeumt(String gegnerStein, int richtung) {
		if (spielBrett.getNachbarByIdInRichtung(gegnerStein, richtung) == null) {
			return true;
		}

		return false;
	}

	/**
	 * Prueft, ob ein Zwei-zu-drei Sumito moeglich ist.
	 * 
	 * @param vordersterStein der vorderste Stein eines Spielzuges in dem drei
	 *                        Steine bewegt werden.
	 * @param richtung        die Richtung des Spielzuges.
	 * @return boolean true, wenn moeglich, false, wenn nicht moeglich.
	 */
	private boolean isZuZweiSumito(String vordersterStein, int richtung) {
		String erstesFeldInRichtung = spielBrett.getNachbarByIdInRichtung(vordersterStein, richtung);
		String zweitesFeldInRichtung = null;
		String drittesFeldInRichtung = null;
		if(erstesFeldInRichtung!=null) {
			zweitesFeldInRichtung = spielBrett.getNachbarByIdInRichtung(erstesFeldInRichtung, richtung);
		}
		if(zweitesFeldInRichtung!=null) {
			drittesFeldInRichtung = spielBrett.getNachbarByIdInRichtung(zweitesFeldInRichtung, richtung);
		}

		if (erstesFeldInRichtung != null && spielBrett.istBesetzt(erstesFeldInRichtung)
				&& !spielBrett.getFarbeDerFigurById(erstesFeldInRichtung).equals(spielBrett.getFarbeDerFigurById(vordersterStein))
				&& !spielBrett.getFarbeDerFigurById(zweitesFeldInRichtung).equals(spielBrett.getFarbeDerFigurById(vordersterStein))) {

			if (drittesFeldInRichtung == null) {
				return true;
			}
			if (!(spielBrett.istBesetzt(drittesFeldInRichtung))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Ermittelt aus einem Spielzug wie welche Steine einzeln gezogen werden
	 * muessen.
	 * 
	 * @param zug Der geplante Spielzug.
	 * @return SpielzugArray Ein Array des Typs Spielzug mit allen einzeln
	 *         auszufuehrenden Zuegen.
	 */
	public Spielzug[] spielzugSplitter(Spielzug zug) {
		String[] felder = spielBrett.getAusgangsfelder(zug);
		if (isSchiebung(felder, zug.getRichtung()))
			felder = ordneInRichtung(felder, zug.getRichtung());
		if (felder.length <= 1) {
			Spielzug[] zuege = { zug };
			return zuege;
		}
		ArrayList<Spielzug> zuege = new ArrayList<Spielzug>();
		int richtung = zug.getRichtung();
		String zielfeld = spielBrett.getNachbarByIdInRichtung(felder[0], richtung);
		Spielzug zielFeldZug = null;
		Spielzug zielNachbarzug = null;

		if (spielBrett.istDurchGegnerBesetztById(zielfeld, getFarbeAmZug())) {
			String zielNachbar = spielBrett.getNachbarByIdInRichtung(zielfeld, richtung);
			if (zielNachbar == null) {
				zielFeldZug = new Spielzug(zielfeld, null, zug.getRichtung(), zug.getFarbe());

			} else {
				zielFeldZug = new Spielzug(zielfeld, zielNachbar);
			}
			if (zielNachbar != null && spielBrett.istDurchGegnerBesetztById(zielNachbar, getFarbeAmZug())) {
				String zielNachbar2 = spielBrett.getNachbarByIdInRichtung(zielNachbar,richtung);
				if (zielNachbar2 == null) {
					zielNachbarzug = new Spielzug(zielNachbar, null, zug.getRichtung(), zug.getFarbe());

				} else {
					zielNachbarzug = new Spielzug(zielNachbar, zielNachbar2, zug.getRichtung(),
							zug.getFarbe());
				}
			}
		}
		if (zielNachbarzug != null) {
			zuege.add(zielNachbarzug);
		}
		if (zielFeldZug != null) {
			zuege.add(zielFeldZug);
		}
		for (int i = 0; i < felder.length; i++) {

			if (felder[i] != null && spielBrett.getNachbarByIdInRichtung(felder[i], zug.getRichtung()) != null) {
				String zielFeld = spielBrett.getNachbarByIdInRichtung(felder[i], zug.getRichtung());
				Spielzug teilZug = new Spielzug(felder[i], zielFeld, zug.getRichtung(), zug.getFarbe());
				zuege.add(teilZug);
			}
		}

		return zuege.toArray(new Spielzug[0]); 
	}

	/**
	 * Gibt das hinterste Feld eines Spielzuges zurueck.
	 * 
	 * @param felder   Ein Array des Typs Felder.
	 * @param richtung Die Richtung, in die gezogen wird.
	 * @return spielfeld Das hinterste Spielfeld als Spielfeld Objekt.
	 */
	private String getHinterstenStein(String[] felder, int richtung) {
		if (felder.length == 3) {
			if(spielBrett.getNachbarByIdInRichtung(felder[0], richtung) != null) {
				if (spielBrett.getNachbarByIdInRichtung(felder[0], richtung).equals(felder[1])) {
					return felder[0];
				} 
			}
			if(spielBrett.getNachbarByIdInRichtung(felder[2], richtung) != null) {
				if (spielBrett.getNachbarByIdInRichtung(felder[2], richtung).equals(felder[1])) {
					return felder[2];
				}
			}
		}

		if (felder.length == 2) {
			if(spielBrett.getNachbarByIdInRichtung(felder[0], richtung) != null) {
				if (spielBrett.getNachbarByIdInRichtung(felder[0], richtung).equals(felder[1])) {
					return felder[0];
				} 
			}
			if(spielBrett.getNachbarByIdInRichtung(felder[1], richtung) != null) {
				if (spielBrett.getNachbarByIdInRichtung(felder[1], richtung).equals(felder[0])) {
					return felder[1];
				}
			}
		}
		return null;
	}

	/**
	 * Ordnet ein Spielfeld Array nach einer gegebenen Richtung.
	 * 
	 * @param felder   Ein Array mit zu ordnenden Spielfeldern.
	 * @param richtung Die Richtung, nach der geordnet werden soll.
	 * @return sortiertesArray Das sortierte Array aus Spielfeldern.
	 */
	private String[] ordneInRichtung(String[] felder, int richtung) {
		ArrayList<String> geordneteFelder = new ArrayList<String>();

		String hintersterStein = getHinterstenStein(felder, richtung);
		geordneteFelder.add(hintersterStein);

		for (int i = 1; i < felder.length; i++) { // Baut vom hintersten Stein bis zum vordersten Stein auf
			geordneteFelder.add(spielBrett.getNachbarByIdInRichtung(geordneteFelder.get(i - 1), richtung));
		}

		Collections.reverse(geordneteFelder); // Dreht um, sodass erster Stein vorne steht
		return geordneteFelder.toArray(new String[0]);
	}

	/**
	 * Formatiert einen Spielzug in die intern benutzte Notation.
	 * 
	 * @param zug Der geplante Spielzug.
	 * @return formatiertenZug Formatierter Spielzug.
	 */
	private Spielzug formatieren(Spielzug zug) {
		if (zug.getVon().length() == 2) {
			return zug;
		}

		String vonFeld1 = zug.getVon().substring(0, 2);
		String vonFeld2 = zug.getVon().substring(2, 4);
		String nachFeld = zug.getNach();
		if (!(spielBrett.hatNachbarById(vonFeld2,spielBrett.getFeld(nachFeld)))
				|| (spielBrett.hatNachbarById(vonFeld1,spielBrett.getFeld(vonFeld2))
						&& (spielBrett.hatNachbarById(vonFeld1, nachFeld)
								&& spielBrett.hatNachbarById(vonFeld2, nachFeld)))
				|| (spielBrett.hatNachbarById(vonFeld1, nachFeld)
						&& spielBrett.hatNachbarById(vonFeld2, nachFeld))) {
			int richtung = spielBrett.getNachbarIndexById(vonFeld1, spielBrett.getFeld(nachFeld));
			//(spielBrett.getFeld(vonFeld1).hatNachbar(nachFeld)&& spielBrett.getFeld(vonFeld2).hatNachbar(nachFeld)))
			if (vonFeld1.charAt(1) > vonFeld2.charAt(1) || vonFeld1.charAt(0) > vonFeld2.charAt(0)) {
				String halter = vonFeld1;
				vonFeld1 = vonFeld2;
				vonFeld2 = halter;
			}
			if (spielBrett.getNachbarByIdInRichtung(vonFeld2,richtung) != null) {
				nachFeld = spielBrett.getNachbarByIdInRichtung(vonFeld2, richtung);
			} else {
				nachFeld = null;
			}
		}

		return new Spielzug(vonFeld1 + "" + vonFeld2, nachFeld);

	}

	/**
	 * Fuegt fuer geschlagene Kugeln einen Stern am Spielfeldrand an.
	 * 
	 * @param brettAlsString Das aktuelle Brett als String.
	 * @param zug            Der aktuell ausgefuehrte Zug.
	 * @return Das aktuelle Brett als String mit angefuegtem Sternchen.
	 */
	private String addSternchen(String brettAlsString, Spielzug zug) {
		String[] brettAlsArray = brettAlsString.split("\n");
		char[] spielZugVon = zug.getVon().toCharArray();
		int richtung = zug.getRichtung();
		spielZugVon[0] = zug.getVon().charAt(0);
		spielZugVon[1] = zug.getVon().charAt(1);
		int zeilenIndex = 0;
		boolean gefunden = false;
		char[] alsArray = null;
		if (!(spielZugVon[0] == 'A' && richtung == 2 || spielZugVon[0] == 'A' && richtung == 5)) {
			for (int i = 0; i < brettAlsArray.length; i++) {
				if (richtung == 1 || richtung == 4) {
					if (brettAlsArray[i].charAt(0) == spielZugVon[0] && !gefunden) {
						zeilenIndex = i - 1;
						gefunden = true;
					}
				}
				if (richtung == 0 || richtung == 3) {
					if (brettAlsArray[i].charAt(0) == spielZugVon[0] && !gefunden) {
						zeilenIndex = i;
						gefunden = true;
					}
				}
				if (richtung == 2 || richtung == 5) {
					if (brettAlsArray[i].charAt(0) == spielZugVon[0] && !gefunden) {
						zeilenIndex = i + 1;
						gefunden = true;
					}
				}
			}
			gefunden = false;
			alsArray = brettAlsArray[zeilenIndex].toCharArray();
			for (int i = 0; i < brettAlsArray[zeilenIndex].length(); i++) {
				char aktuellerChar = alsArray[i];
				if (richtung >= 0 && richtung < 3) {
					aktuellerChar = alsArray[i];
					if ((aktuellerChar == 'O' || aktuellerChar == 'X' || aktuellerChar == '-') && !gefunden) {
						if (richtung != 0)
							alsArray[i - 2] = '*';
						else
							alsArray[i - 2] = '*';
						gefunden = true;
					}
				}
				if (richtung >= 3 && richtung < 6) {
					aktuellerChar = alsArray[i];
					if ((aktuellerChar == 'O' || aktuellerChar == 'X' || aktuellerChar == '-') && !gefunden) {
						if (alsArray[i + 2] == ' ') {
							alsArray[i + 2] = '*';
							gefunden = true;
						}
					}
				}
			}
		}
		if (spielZugVon[0] == 'I') {
			alsArray = brettAlsArray[0].toCharArray();
			zeilenIndex = 0;
			switch (spielZugVon[1]) {
			case '5':
				if (richtung == 1) {
					alsArray[6] = '*';
				}
				if (richtung == 4) {
					alsArray[8] = '*';
				}
				break;
			case '6':
				if (richtung == 1) {
					alsArray[8] = '*';
				}
				if (richtung == 4) {
					alsArray[10] = '*';
				}
				break;
			case '7':
				if (richtung == 1) {
					alsArray[10] = '*';
				}
				if (richtung == 4) {
					alsArray[12] = '*';
				}
				break;
			case '8':
				if (richtung == 1) {
					alsArray[12] = '*';
				}
				if (richtung == 4) {
					alsArray[14] = '*';
				}
				break;
			case '9':
				if (richtung == 1) {
					alsArray[14] = '*';
				}
				if (richtung == 4) {
					alsArray[16] = '*';
				}
				break;

			}

		}
		if (spielZugVon[0] == 'A') {
			alsArray = brettAlsArray[10].toCharArray();
			zeilenIndex = 10;
			switch (spielZugVon[1]) {
			case '1':
				if (richtung == 2) {
					alsArray[6] = '*';
				}
				if (richtung == 5) {
					alsArray[7] = '*';
				}
				break;
			case '2':
				if (richtung == 2) {
					alsArray[8] = '*';
				}
				if (richtung == 5) {
					alsArray[10] = '*';
				}
				break;
			case '3':
				if (richtung == 2) {
					alsArray[10] = '*';
				}
				if (richtung == 5) {
					alsArray[12] = '*';
				}
				break;
			case '4':
				if (richtung == 2) {
					alsArray[12] = '*';
				}
				if (richtung == 5) {
					alsArray[14] = '*';
				}
				break;
			case '5':
				if (richtung == 2) {
					alsArray[14] = '*';
				}
				if (richtung == 5) {
					alsArray[16] = '*';
				}
				break;

			}

		}
		gefunden = false;
		String zeile = "";
		for (int i = 0; i < alsArray.length; i++) {
			zeile += alsArray[i];
		}
		brettAlsArray[zeilenIndex] = zeile;
		String neuesBrett = "";
		for (int i = 0; i < brettAlsArray.length; i++) {
			neuesBrett += brettAlsArray[i] + "\n";
		}
		return neuesBrett;

	}

	/**
	 * Gibt fuer eine KI alle auf dem Spielbrett moeglichen Bewegungen zurueck
	 * @return alleMoeglichenZuege als String
	 */
	@Override
	public String getAlleZuege() {
		return "";
	}

	/**
	 * Gibt einen String mit den Namen im Stil "Name1,Name2" zur�ck. 
	 * Angepasst fuer das Interface im Stil, dass nur ein String ueberliefert 
	 * wird.
	 * 
	 * @return AlleNamenDerSpieler Spielernamen getrennt durch Kommata
	 */
	@Override
	public String getSpielerImSpielInterface() {
		String spielerString = "";
		for(Spieler s: getSpielerImSpiel()) {
			spielerString += s.getName() + ",";
		}

		return spielerString;
	}

	/**
	 * Anpassung der getErlaubte-Methode fuer das Interface
	 * 
	 * @return erlaubteZuege Als String implementiert
	 * @throws AbaloneException 
	 * 
	 */
	@Override
	public String getErlaubteZuegeInterface(String[] ausgangsfelder) throws AbaloneException {
		String zuegeString = "";
		String[] felder = null;
		try {
			felder=getErlaubteZuege(ausgangsfelder);
		}catch(UngueltigerZugException e) {
			log(e);
			throw new AbaloneException(1,"Ungueltiger Zug!");
		}
		for(String s : felder) {
			zuegeString += s + ",";
		}

		return zuegeString;
	}

	@Override
	public void spielAusDateiLaden() {
		// TODO Auto-generated method stub

	}

	@Override
	public void spielStatusSpeichern() {
		// TODO Auto-generated method stub

	}

	public ArrayList<String> getMoeglicheAusgangsfelder(FarbEnum farbe) {
		ArrayList<String> moeglicheAusgangsfelder = new ArrayList<String>();
		ArrayList<String> felderInFarbe = spielBrett.getFelderMitFarbe(farbe);

		for(String momentanesFeld : felderInFarbe) {
			String betrachtetesEinzelfeld = momentanesFeld;
			moeglicheAusgangsfelder.add(betrachtetesEinzelfeld);			

			for(int i = 3; i <= 5; i++) {
				if(spielBrett.getNachbarByIdInRichtung(momentanesFeld, i) != null) {
					String nachbar = spielBrett.getNachbarByIdInRichtung(momentanesFeld, i);
					if(spielBrett.istBesetzt(nachbar) && spielBrett.getFarbeDerFigurById(nachbar) == farbe) {
						String betrachteteZweiFelder = momentanesFeld+ ""+ nachbar;
						moeglicheAusgangsfelder.add(betrachteteZweiFelder);

						if(spielBrett.getNachbarByIdInRichtung(nachbar, i) != null) {
							String nachbarDesNachbars = spielBrett.getNachbarByIdInRichtung(nachbar, i);
							if(spielBrett.istBesetzt(nachbarDesNachbars) && spielBrett.getFarbeDerFigurById(nachbarDesNachbars) == farbe) {
								String betrachteteDreiFelder = momentanesFeld + ""+ nachbarDesNachbars;
								moeglicheAusgangsfelder.add(betrachteteDreiFelder);
							}
						}
					}
				}
			}
		}
		
		return moeglicheAusgangsfelder;
	}

	public ArrayList<Spielzug> getAlleMoeglichenZuege(Spieler spieler) throws AbaloneException {

		ArrayList<Spielzug> alleMoeglichenZuege = new ArrayList <Spielzug>();
		ArrayList<String> moeglicheAusgangsFelder = getMoeglicheAusgangsfelder(spieler.getFarbe());

		for (String ausgangsFelder : moeglicheAusgangsFelder) {
			String[] ausgangsFelderFormat = {ausgangsFelder, null};
			String[] erlaubteZuege = null;
			try {
				erlaubteZuege = getErlaubteZuege(ausgangsFelderFormat);
			}
			catch(UngueltigerZugException e) {
				log(e);
				throw new AbaloneException(1,"Ungueltiger Zug!");
			}
			for (String erlaubterZug : erlaubteZuege) {
				String[] erlaubterZugSplit = erlaubterZug.split("-");
				Spielzug zug = new Spielzug(erlaubterZugSplit[0], erlaubterZugSplit[1]);
				zug.setRichtung(bekommeRichtung(zug));
				zug.setFarbe(spieler.getFarbe());
				if (!alleMoeglichenZuege.contains(zug)) {
					alleMoeglichenZuege.add(zug);
				}

			}
		}
		return alleMoeglichenZuege;
	}

	public String writeCSV() {
		String csv = "SPIEL: \n";
		for(Spieler spieler: spielerImSpiel) {
			csv +=  spieler.writeCSV()+"\n";
		}
		csv += historie.writeCSV() + "\n" + spielBrett.writeCSV();
		return csv;
	}
	
	public void log(Exception e ) {
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		Date date = new Date();
		try {
			logger.newLine();
			logger.write("Fehler am " + dateFormat.format(date) + ": ");
			logger.newLine();
			logger.write(e.toString());
		}catch(FileNotFoundException fehler) {
			
		}catch(IOException fehler) {
			
		}
	}
	
	
	
	Spielbrett getSpielbrett() {
		return this.spielBrett;
	}
	


}
