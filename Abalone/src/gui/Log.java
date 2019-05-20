package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Diese Klasse dient zur Nutzung der Log-Datei.
 * 
 * @author Gruppe A4
 *
 */
public class Log {
	
	/**
	 * Diese Methode liest den Inhalt der Log-Datei aus und gibt ihn in Form
	 * eines Strings zurueck
	 * 
	 * @return Log-Inhalt als String
	 */
	public String logLesen() {
		BufferedReader br = null;
		String datei = "";
		String zeile;
		
		try {
			br = new BufferedReader(
				new InputStreamReader(new FileInputStream("log.txt"), "utf-8"));
			while ((zeile = br.readLine()) != null) {
				datei = datei + "\n" + zeile;
			}
			br.close();
		} catch (IOException e) {
			logErstellen();
			return "";
		}
			
		return datei;
	}
	
	/**
	 * Erstellt eine Log-Datei und loescht die alte.
	 */
	private void logErstellen() {
		File file = new File("log.txt");
		
		if (file.exists())
			file.delete();
		
		try {
			file.createNewFile();
		} catch (IOException e) {
		}
	}
	
}
