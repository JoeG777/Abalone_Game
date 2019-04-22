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
	void ziehe(String[] zugArr) throws AbaloneException;

	//alle public Methoden aus Spiel, die in UI benutzt werden müssen
	String getSpielerImSpielInterface(); 
	void addSpieler(String name, String farbe, int anzahlSpieler) throws AbaloneException;
	String getStatus();
	String getHistorie();
	String getErlaubteZuegeInterface(String[] ausgangsfelder) throws AbaloneException;////muss noch ein anderer Returnwert sein
	boolean hatGewonnen(String name);
	void speichernSerialisiert(String dateiName) throws AbaloneException;
	void lesenSerialisiert(String dateiName) throws AbaloneException;
	void speichernCSV(String dateiName) throws AbaloneException;
	void lesenCSV(String dateiName) throws AbaloneException;
}
