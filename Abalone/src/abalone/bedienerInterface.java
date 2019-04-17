package abalone;

import java.io.FileNotFoundException;
import java.io.IOException;

import abalone.spielbrett.SpielbrettException;

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
	void ziehe(String[] zugArr) throws SpielbrettException, UngueltigerZugException;
	void spielAusDateiLaden();
	void spielStatusSpeichern();

	//alle public Methoden aus Spiel, die in UI benutzt werden müssen
	String getSpielerImSpielInterface(); 
	void addSpieler(String name, String farbe, int anzahlSpieler) throws AbaloneException;
	String getStatus();
	String getHistorie();
	String getErlaubteZuegeInterface(String[] ausgangsfelder) throws UngueltigerZugException;////muss noch ein anderer Returnwert sein
	boolean hatGewonnen(String name);
	void lesen(String dateiName) throws FileNotFoundException, IOException, ClassNotFoundException;
	void speichern(String dateiName) throws FileNotFoundException, IOException;
}
