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
	private String dateiName;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	
	/**
	 * Diese Methode bekommt einen Spielstand in Form eines Dateinamen
	 * uebergeben und oeffnet diese
	 * @param dateiName String, welcher den Dateinamen des Spielstandes enthaelt
	 */
	@Override
	public void oeffnen(String dateiName) {
		this.dateiName = dateiName;
	}

	/**
	 * Diese Methode beendet den Speicher- und Ladevorgang vollstaendig
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
	 * @return gelesenes Objekt
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@Override
	public Object lesen() throws ClassNotFoundException, IOException {
		ois = new ObjectInputStream(new FileInputStream("sav/" + dateiName + ".ser"));
		try {
			Object gelesenesObjekt = ois.readObject();
			return gelesenesObjekt;
		} catch (NullPointerException e) {
			throw new IOException("Datei nicht gefunden");
		} catch (NumberFormatException e) {
			throw new IOException("Falsches Format");
		} catch(IndexOutOfBoundsException e) {
			throw new IOException("Zu wenig Elemente");
		} finally {
			ois.close();
		}
	}

	/**
	 * Diese Methode beschreibt ein uebergebenes Objekt
	 * @param zuSchreibendesObjekt Das Objekt, welches beschrieben werden soll
	 */
	@Override
	public void schreiben(Object zuSchreibendesObjekt) throws IOException {
		File f = new File("sav");
		if (!f.exists())
			f.mkdir();
		
		oos = new ObjectOutputStream(new FileOutputStream("sav/" + dateiName + ".ser"));
		try {
			oos.writeObject(zuSchreibendesObjekt);
		} catch (FileNotFoundException e) {
			throw new IOException("Datei nicht gefunden");
		} catch (IOException e) {
			throw new IOException("Irgendwas ist schief gelaufen " + e.getMessage());
		} finally {
			oos.close();
		}
	}
}
