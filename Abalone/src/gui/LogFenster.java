package gui;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Diese Klasse erschafft ein Log-Fenster.
 * 
 * @author Gruppe A4
 *
 */
public class LogFenster {
	private JFrame frame;
	private String log;
	
	/**
	 * Der Konstruktor der Klasse Log-Fenster.
	 * 
	 * @param logObj Log-Objekt
	 */
	public LogFenster() {
		Log logObj = new Log();
		log = logObj.logLesen();
		setLog(log);
		
		frame = new JFrame();
		frame.setSize(720, 480);
		frame.setLayout(new GridLayout(25,1));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		logAusgeben(frame, log);
	}
	
	/**
	 * Gibt die Log-Informationen als String zurueck
	 * 
	 * @return String, der die Log-Informationen enthaelt
	 */
	public String getLog() {
		return log;
	}
	
	/**
	 * Setzt den Log.
	 * 
	 * @param log String, der die Log-Informationen enthaelt
	 */
	private void setLog(String log) {
		this.log = log;
	}
	
	/**
	 * Befuellt das uebergebene Fenster mit Labels, anhand eines
	 * uebergebenen Strings.
	 * 
	 * @param Log-Fenster als JFrame
	 * @param log String, der die Log-Informationen enthaelt
	 */
	private void logAusgeben(JFrame frame, String log) {
		String[] zeilen = log.split("\n");
		
		JLabel titel = new JLabel("---LOG---");
		frame.add(titel);
		
		for (String zeile : zeilen) {
			JLabel jl = new JLabel(zeile);
			frame.add(jl);
		}
		
	}

}
