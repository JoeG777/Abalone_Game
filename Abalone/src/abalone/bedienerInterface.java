package abalone;

/**
 * Schnittstelle zwischen der Konsolen-UI und dem Spiel mit der Spiellogik.
 * 
 * @author Gruppe A4
 * 
 */
public interface bedienerInterface {
	//vorgegebenen Methoden aus PDF inkl 2b
	String getSpielerAmZug();
	String getAlleZuege();//Historie ausgeben in Rumpf
	void ziehe(String[] zugArr) throws SpielException;

	//alle public Methoden aus Spiel, die in UI benutzt werden müssen
	String getSpielerImSpielInterface(); 
	void addSpieler(String name, String farbe, int anzahlSpieler) throws SpielException;
	String getStatus();
	String getHistorie();
	String getErlaubteZuegeInterface(String[] ausgangsfelder) throws SpielException;////muss noch ein anderer Returnwert sein
	boolean hatGewonnen(String name);
	void speichernSerialisiert(String dateiName) throws SpielException;
	Object lesenSerialisiert(String dateiName) throws SpielException;
	void speichernCSV(String dateiName) throws SpielException;
	void lesenCSV(String dateiName) throws SpielException;
}
