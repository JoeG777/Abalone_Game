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
public class PersistenzImplSerialisiert implements PersistenzInterface {
	
	ObjectInputStream ois = null;
	ObjectOutputStream oos = null;
	
	/** Diese Methode bekommt einen Spiel-Status in Form einer Datei
	 * uebergeben und oeffnet diese
	 * @param dateiName String, welcher den Dateipfad des Spiel-Status enthaelt
	 */
	@Override
	public void oeffnen(String dateiName) throws FileNotFoundException, IOException {
		ois = new ObjectInputStream(new FileInputStream(dateiName));
		oos = new ObjectOutputStream(new FileOutputStream(dateiName));
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
		Object gelesenesObjekt = ois.readObject();
		return gelesenesObjekt;
	}

	/**
	 * Diese Methode beschreibt ein uebergebenes Objekt
	 * @param zuSchreibendesObjekt Das Objekt, welches beschrieben werden soll
	 */
	@Override
	public void schreiben(Object zuSchreibendesObjekt) throws IOException {
		oos.writeObject(zuSchreibendesObjekt);
	}

}
