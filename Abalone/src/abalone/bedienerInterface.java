package abalone;

/**
 * Schnittstelle zwischen der Konsolen-UI und dem Spiel mit der Spiellogik.
 * 
 * @author Gruppe A4
 * 
 */
public interface bedienerInterface {
	/**
	 * Gibt den Namen des Spielers, der dran ist, zurueck
	 * 
	 * @return String, der den Namen des Spielers enthaelt
	 */
	String getSpielerAmZug();
	
	/**
	 * Gibt fuer eine KI alle auf dem Spielbrett moeglichen Bewegungen zurueck.
	 * 
	 * @return String, der alle moeglichen Zuege enthaelt
	 */
	String getAlleZuege();
	
	/**
	 * Delegiert einen Zug an die ziehe Methode von der Spielbrett-Klasse.
	 * Falls der aktuelle Spieler eine KI ist, wird der passende Zug
	 * ermittelt und uebergeben.
	 * 
	 * @param zugArr Ein String Array, der enthaelt von wo aus, wohin gezogen wird.
	 * @throws SpielException
	 */
	void ziehe(String[] zugArr) throws SpielException;

	/**
	 * Gibt einen String mit dem Namen zurueck.
	 * 
	 * @return String, der den Namen enthaelt
	 */
	String getSpielerImSpielInterface();
	
	/**
	 * Fuegt Spieler hinzu.
	 * 
	 * @param name Name des hinzuzufuegenden Spielers
	 * @param farbe Farbe des hinzuzufuegenden Spielers
	 * @param anzahlSpieler Anzahl der Spieler
	 * @throws SpielException
	 */
	void addSpieler(String name, String farbe, int anzahlSpieler) throws SpielException;
	
	/**
	 * Gibt den Status des Spiels zurueck. Dieser umfasst das Spielbrett, welcher
	 * Spieler am Zug ist, wie viele Steine die jeweiligen Spieler noch im Spiel
	 * haben und wie viele Steine Sie jeweils verloren haben.
	 * 
	 * @return String, der den Status enthaelt
	 */
	String getStatus();
	
	/**
	 * Gibt die Historie aus.
	 * 
	 * @return String, der die Historie enthaelt
	 */
	String getHistorie();
	
	/**
	 * Gibt die erlaubten Zuege zurueck.
	 * 
	 * @param ausgangsfelder Die Ausgangsfelder, von denen gezogen wird.
	 * @return String, der alle erlaubten Zuege enthaelt
	 * @throws SpielException
	 */
	String getErlaubteZuegeInterface(String[] ausgangsfelder) throws SpielException;
	
	/**
	 * Prueft, ob ein Spieler gewonnen hat.
	 * 
	 * @param name Name eines Spielers
	 * @return wahr oder falsch
	 */
	boolean hatGewonnen(String name);
	
	/**
	 * Speichert den Spielstand in eine serialisierte Datei.
	 * 
	 * @param dateiName Name, der zu speichernden Datei
	 * @throws SpielException
	 */
	void speichernSerialisiert(String dateiName) throws SpielException;
	
	/**
	 * Laedt den Spielstand aus einer serialisierte Datei.
	 * 
	 * @param dateiName Name, der zu ladenden Datei
	 * @throws SpielException
	 */
	void lesenSerialisiert(String dateiName) throws SpielException;
	
	/**
	 * Speichert den Spielstand in eine CSV-Datei.
	 * 
	 * @param dateiName Name, der zu speichernden Datei
	 * @throws SpielException
	 */
	void speichernCSV(String dateiName) throws SpielException;
	
	/**
	 * Laedt den Spielstand aus einer CSV-Datei.
	 * 
	 * @param dateiName Name, der zu ladenden Datei
	 * @throws SpielException
	 */
	void lesenCSV(String dateiName) throws SpielException;
}
