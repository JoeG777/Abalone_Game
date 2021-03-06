package abalone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/** 
 * <h1>PersistenzImplSerialisiert</h1>
 * Die Klasse PersistenzImplSerialisiert implementiert das
 * PersistenzInterface vollstaendig und ermoeglicht das Speichern
 * und Laden des Spiel-Status als serialisierte Datei
 * @author Gruppe A4
 */
public class PersistenzImplSerialisiert implements PersistenzInterface, java.io.Serializable {
	
	private static final long serialVersionUID = 100L;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	
	/**
	 * Diese Methode bekommt einen Dateinamen uebergeben,
	 * ueberprueft dessen Existenz und oeffnet diese gegebenenfalls
	 * 
	 * @param dateiName String, welcher den Dateinamen des Spielstandes enthaelt
	 * @param lesen Info, ob Datei gelesen oder beschrieben werden soll
	 * @throws FileNotFoundException Wenn die Datei nicht gefunden werden kann
	 */
	@Override
	public void oeffnen(String dateiPfad, boolean lesen) throws FileNotFoundException, IOException {		
		if (lesen)
			ois = new ObjectInputStream(new FileInputStream(dateiPfad));
		else {
			File f = new File("sav");
			if (!f.exists())
				f.mkdir();
			
			oos = new ObjectOutputStream(new FileOutputStream(dateiPfad));
		}
	}

	/**
	 * Diese Methode beendet den Speicher und Ladevorgang vollstaendig
	 * 
	 * @throws IOException Wenn ein Problem beim schliessen auftritt
	 */
	@Override
	public void schliessen() throws IOException {
		if(oos != null)
			oos.close();
		if(ois != null)
			ois.close();
	}

	/**
	 * Diese Methode liest die Datei aus und gibt das Resultat als Objekt zurueck
	 * 
	 * @return gelesenes Objekt
	 * @throws ClassNotFoundException Wenn eine Klasse nicht gefunden werden kann
	 * @throws IOException Wenn ein Problem beim Lesevorgang auftritt
	 */
	@Override
	public Object lesen() throws ClassNotFoundException, IOException {
		try {
			Object gelesenesObjekt = ois.readObject();
			return gelesenesObjekt;
		} catch (NullPointerException e) {
			throw new IOException("Datei nicht gefunden");
		} catch (NumberFormatException e) {
			throw new IOException("Falsches Format");
		} catch(IndexOutOfBoundsException e) {
			throw new IOException("Zu wenig Elemente");
		}
	}

	/**
	 * Diese Methode beschreibt ein uebergebenes Objekt
	 * 
	 * @param zuSchreibendesObjekt Das Objekt, welches beschrieben werden soll
	 * @throws IOException Wenn ein Problem beim Schreibvorgang auftritt
	 */
	@Override
	public void schreiben(Object zuSchreibendesObjekt) throws IOException {			
		oos.writeObject(zuSchreibendesObjekt);

	}
}
