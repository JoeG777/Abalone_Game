package abalone;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * <h1>PersistenzImplCSV</h1>
 * Die Klasse PersistenzImplCSV implementiert das
 * PersistenzInterface vollstaendig und ermoeglicht das Speichern
 * und Laden des Spiel-Status als CSV-Datei
 * @author Gruppe A4
 */
public class PersistenzImplCSV implements PersistenzInterface, java.io.Serializable {

	private static final long serialVersionUID = 101L;
	private String dateiName;
	private BufferedReader br = null;

	/**
	 * Diese Methode bekommt einen Spielstand in Form einer Datei
	 * uebergeben und oeffnet diese
	 * @param dateiName String, welcher den Dateinamen des Spielstandes enthaelt
	 */
	@Override
	public void oeffnen(String dateiName) {
		this.dateiName = dateiName;
	}

	/**
	 * Diese Methode beendet den Speicher- und Ladevorgang vollstaendig
	 * @throws IOException
	 */
	@Override
	public void schliessen() throws IOException {
		if (br != null)
			br.close();
	}

	/**
	 * Diese Methode liest die Datei aus und gibt das Resultat als Objekt zurueck
	 * @return String, der alle Informationen enthaelt
	 * @throws FileNotFoundException, IOException 
	 * @throws UnsupportedEncodingException
	 */
	@Override
	public String lesen() throws UnsupportedEncodingException, FileNotFoundException, IOException {
		String datei = "";
		String zeile;
		
		br = new BufferedReader(
			new InputStreamReader(new FileInputStream("sav/" + dateiName + ".csv"), "utf-8"));
	
		while ((zeile = br.readLine()) != null) {
			datei = datei + ";" + zeile;
		}
		
		return datei;
	}

	/**
	 * Diese Methode beschreibt eine CSV-Datei
	 * @param zuSchreibenderInhalt Information, die beschrieben werden soll
	 */
	@Override
	public void schreiben(Object zuSchreibenderInhalt) {
		if(!(zuSchreibenderInhalt instanceof String))
			throw new RuntimeException("Fehlerhafte Informationen!");
		
		String s = (String) zuSchreibenderInhalt;
	}

}
