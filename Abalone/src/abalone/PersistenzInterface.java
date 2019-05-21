package abalone;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Schnittstelle zwischen den Persistenz-Implementierungen und dem Spiel mit der Spiellogik.
 * 
 * @author Gruppe A4
 * 
 */
public interface PersistenzInterface {
	
	/**
	 * Oeffnet eine Datei.
	 * 
	 * @param dateiName Name, der zu oeffnenden Datei
	 * @param lesen Info, ob Datei gelesen oder beschrieben werden soll
	 * @throws FileNotFoundException Wenn die Datei nicht gefunden werden kann
	 * @throws IOException Wenn es einen Fehler beim oeffnen der Datei gibt
	 * @throws UnsupportedEncodingException Wenn das Encoding der Datei nicht unterstuetzt wird
	 */
	public void oeffnen(String dateiPfad, boolean lesen)
			throws FileNotFoundException, IOException, UnsupportedEncodingException;
	
	/**
	 * Schliesst eine Datei.
	 * 
	 * @throws IOException Wenn eine Datei nicht geschlossen werden kann
	 */
	public void schliessen() throws IOException;
	
	/**
	 * Liest eine Datei aus.
	 * 
	 * @return Gelesenes Objekt
	 * @throws ClassNotFoundException Wenn die Klasse eines Objektes nicht existiert
	 * @throws IOException Wenn die Datei nicht gelesen werden kann
	 */
	public Object lesen() throws ClassNotFoundException, IOException;
	
	/**
	 * Beschreibt eine Datei.
	 * 
	 * @param zuSchreiben Zu schreibendes Objekt
	 * @throws IOException Wenn die Datei nicht beschrieben werden kann
	 */
	public void schreiben(Object zuSchreiben) throws IOException;
	
}
