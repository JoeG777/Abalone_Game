package abalone;

import java.io.IOException;

/**
 * <h1>PersistenzImplCSV</h1>
 * Die Klasse PersistenzImplCSV implementiert das
 * PersistenzInterface vollstaendig und ermoeglicht das Speichern
 * und Laden des Spiel-Status als CSV-Datei
 * @author Gruppe A4
 */
public class PersistenzImplCSV implements PersistenzInterface, java.io.Serializable {

	private static final long serialVersionUID = 101L;

	/**
	 * Diese Methode bekommt einen Spielstand in Form einer Datei
	 * uebergeben und oeffnet diese
	 * @param dateiName String, welcher den Dateinamen des Spielstandes enthaelt
	 */
	@Override
	public void oeffnen(String dateiName) {
		
		
	}

	/**
	 * Diese Methode beendet den Speicher- und Ladevorgang vollstaendig
	 */
	@Override
	public void schliessen() {
		
		
	}

	/**
	 * Diese Methode liest die Datei aus und gibt das Resultat als Objekt zurueck
	 * @return gelesenes Objekt
	 * @throws IOException
	 */
	@Override
	public Object lesen() throws IOException {
		
		return null;
	}

	/**
	 * Diese Methode beschreibt eine CSV-Datei
	 * @param Information, die beschrieben werden soll
	 */
	@Override
	public void schreiben(Object zuSchreibendesObjekt) {
		
		
	}

}
