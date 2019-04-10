package abalone;

/**
 * Schnittstelle zwischen der Konsolen-UI und dem Spiel mit der Spiellogik.
 * 
 * @author Gruppe A4
 * 
 */
public interface bedienerInterface {
	//vorgegebenen Methoden aus PDF inkl 2b
	String getSpielerAmZug();//nicht szu implementieren
	String getAlleZuege();//Historie ausgeben in Rumpf
	void ziehe(String[] zugArr);// ziehe Methode in den Rumpf
	void spielAusDateiLaden();
	void spielStatusSpeichern();//Spieler, Status, Historie

	//alle public Methoden aus Spiel, die in UI benutzt werden müssen
	String getSpielerImSpielInterface(); //muss noch ein anderer Returnwert sein
	void addSpieler(String name, String farbe);
	String getStatus();
	String getErlaubteZuegeInterface(String[] ausgangsfelder);////muss noch ein anderer Returnwert sein
	boolean hatGewonnen(String name);
}
