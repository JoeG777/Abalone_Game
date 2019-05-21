package abalone;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

/**
 * <h1>PersistenzImplCSV</h1>
 * Die Klasse PersistenzImplCSV implementiert das
 * PersistenzInterface vollstaendig und ermoeglicht das Speichern
 * und Laden des Spiel-Status als CSV-Datei
 * @author Gruppe A4
 */
public class PersistenzImplCSV implements PersistenzInterface, java.io.Serializable {

	private static final long serialVersionUID = 101L;
	private String dateiPfad;
	private BufferedReader br = null;
	private PrintWriter pw = null;

	/**
	 * Diese Methode bekommt einen Dateinamen uebergeben,
	 * ueberprueft dessen Existenz und oeffnet diese gegebenenfalls
	 * 
	 * @param dateiName String, welcher den Dateinamen des Spielstandes enthaelt
	 * @param lesen Info, ob Datei gelesen oder beschrieben werden soll
	 * @throws FileNotFoundException Wenn die Datei mit dem Namen nicht existiert
	 * @throws UnsupportedEncodingException Wenn das Encoding der Datei nicht unterstuetzt wird
	 */
	@Override
	public void oeffnen(String dateiPfad, boolean lesen) throws FileNotFoundException, UnsupportedEncodingException {
		this.dateiPfad = dateiPfad;
		
		if (lesen)
			br = new BufferedReader(
					new InputStreamReader(new FileInputStream(dateiPfad), "utf-8"));
		else {
			File f = new File("sav");
			if (!f.exists())
				f.mkdir();
			
			pw = new PrintWriter(dateiPfad, "utf-8");
		}	
	}

	/**
	 * Diese Methode beendet den Speicher- und Ladevorgang vollstaendig
	 * 
	 * @throws IOException Wenn der Reader oder der Writer nicht geschlossen werden kann
	 */
	@Override
	public void schliessen() throws IOException {
		if (br != null)
			br.close();
		if (pw != null)
			pw.close();
	}

	/**
	 * Diese Methode liest die Datei aus und gibt das Resultat als Objekt zurueck
	 * 
	 * @return String, der alle Informationen enthaelt
	 * @throws IOException Wenn eine Datei nicht gelesen werden kann
	 * @throws UnsupportedEncodingException Wenn das Encoding einer Datei nicht unterstuetzt wird
	 */
	@Override
	public String lesen() throws IOException {
		String datei = "";
		String zeile;
	
		while ((zeile = br.readLine()) != null) {
			datei = datei + "\n" + zeile;
		}
		
		return datei;
	}

	/**
	 * Diese Methode beschreibt eine CSV-Datei
	 * 
	 * @param zuSchreibenderInhalt Information, die beschrieben werden soll
	 * @throws IOException Wenn ein Problem beim Schreiben der Datei auftritt
	 */
	@Override
	public void schreiben(Object zuSchreibenderInhalt) throws IOException {
		if(!(zuSchreibenderInhalt instanceof String))
			throw new IOException("Kein String!");
		String string = (String) zuSchreibenderInhalt;
		
		Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");
		if (regex.matcher(dateiPfad).find())
			throw new IOException("Ungueltiger Dateiname!");

		pw.print(string);
		
	}

}
