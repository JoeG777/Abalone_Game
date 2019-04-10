package abalone;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/** 
 * <h1>PersistenzImplSerialisiert</h1>
 * Die Klasse PersistenzImplSerialisiert implementiert das
 * PersistenzInterface vollständig und ermoeglicht das Speichern
 * und Laden des Spiel-Status als serialisierte Datei
 * @author Gruppe A4
 */
public class PersistenzImplSerialisiert implements PersistenzInterface, java.io.Serializable {
	
	private static final long serialVersionUID = 100L;
	String dateiName;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	
	/** Diese Methode bekommt einen Spiel-Status in Form einer Datei
	 * uebergeben und oeffnet diese
	 * @param dateiName String, welcher den Dateipfad des Spiel-Status enthaelt
	 */
	@Override
	public void oeffnen(String dateiName) throws FileNotFoundException, IOException {
		this.dateiName = dateiName;
	}

	/**
	 * Diese Methode beendet den Speicher- und Ladevorgang vollstaendig
	 */
	@Override
	public void schliessen() throws IOException {
		oos.close();
		ois.close();
	}

	/**
	 * Diese Methode liest die Datei aus und gibt das Resultat als Objekt zurueck
	 * @return gelesenes Objekt
	 */
	@Override
	public Object lesen() throws ClassNotFoundException, IOException {
		ois = new ObjectInputStream(new FileInputStream(dateiName));
		try {
			Object gelesenesObjekt = (Spieler)ois.readObject();
			return gelesenesObjekt;
		}catch(NullPointerException e) {
			throw new IOException("Datei nicht gefunden");
		}
		catch(NumberFormatException e) {
			throw new IOException("Falsches Format");
		}
		catch(IndexOutOfBoundsException e) {
			throw new IOException("Zu wenig Elemente");
		}
		finally {
			oos.close();
		}
	}

	/**
	 * Diese Methode beschreibt ein uebergebenes Objekt
	 * @param zuSchreibendesObjekt Das Objekt, welches beschrieben werden soll
	 */
	@Override
	public void schreiben(Object zuSchreibendesObjekt) throws IOException {
		oos = new ObjectOutputStream(new FileOutputStream(dateiName));
		try {
			oos.writeObject(zuSchreibendesObjekt);
		}catch(FileNotFoundException e) {
			throw new IOException("Datei nicht gefunden");
		}
		catch(IOException e) {
			throw new IOException("Irgendwas ist schief gelaufen " + e.getMessage());
		}
		finally {
			oos.close();
		
		}
		
	}
}
