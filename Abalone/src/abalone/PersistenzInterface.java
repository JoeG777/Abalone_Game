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
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public void oeffnen(String dateiName, boolean lesen)
			throws FileNotFoundException, IOException, UnsupportedEncodingException;
	
	/**
	 * Schliesst eine Datei.
	 * 
	 * @throws IOException
	 */
	public void schliessen() throws IOException;
	
	/**
	 * Liest eine Datei aus.
	 * 
	 * @return Gelesenes Objekt
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Object lesen() throws ClassNotFoundException, IOException;
	
	/**
	 * Beschreibt eine Datei.
	 * 
	 * @param zuSchreiben Zu schreibendes Objekt
	 * @throws IOException
	 */
	public void schreiben(Object zuSchreiben) throws IOException;
	
}
