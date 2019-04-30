package abalone;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface PersistenzInterface {
	
	/**
	 * Oeffnet eine Datei.
	 * 
	 * @param dateiName Name, der zu oeffnenden Datei
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void oeffnen(String dateiName) throws FileNotFoundException, IOException;
	
	/**
	 * Schliesst eine Datei.
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
	 * @param zuSchreiben Zu schreibendes Objekt
	 * @throws IOException
	 */
	public void schreiben(Object zuSchreiben) throws IOException;
	
}
