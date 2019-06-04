package abalone;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Oberklasse aller Exceptions, welche von dem Spiel geworfen werden.
 *
 */
public class SpielException extends Exception{
	private static final long serialVersionUID = 21L;
	private int id;
	
	/**
	 * Erstellt eine neue Instanz der Klasse SpielException
	 * @param id ID des Fehlers
	 * @param meldung Die gesetzte Message der Exception
	 */
	public SpielException(int id, String meldung) {
		super(meldung);
		setId(id);
	}
	
	/**
	 * Setter fuer den int-Wert ID
	 * @param id int Wert des Fehlers
	 */
	private void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Getter fuer die ID
	 * @return id gibt die ID als int zurueck
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * toString Methode der Klasse 
	 * @return Die in der Instanz gesetzten Variablen als String
	 */
	@Override
	public String toString() {
		return "Fehler ID " + getId() + " aufgetreten: " + getMessage();
	}
	
	/**
	 * Schreibt eine Exception in mit Datum und Uhrzeit in die log.txt
	 * 
	 */
	protected void log() {
		BufferedWriter logger = null;
		File file = new File("log.txt");
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e1) {
		}
		try {
			logger = new BufferedWriter(new FileWriter(file, true));
		} catch (FileNotFoundException e1) {
		} catch (IOException e1) {
		}
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		Date date = new Date();
		try {
			logger.newLine();
			logger.write("Fehler am " + dateFormat.format(date) + ": ");
			logger.newLine();
			logger.write(this.toString());
			logger.newLine();
			logger.flush();
		} catch (FileNotFoundException fehler) {

		} catch (IOException fehler) {

		}
		try {
			logger.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		sc.close();
	}
}

